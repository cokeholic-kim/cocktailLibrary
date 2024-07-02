
const ingredientContainer = document.querySelector("#ingredientsContainer");
const addRow = (container) => {
    container.appendChild(createIngredientRow());
}
const createIngredientRow = () => {
    const row = document.createElement("div");
    row.classList.add("row");

    const ingredient = document.createElement("input");
    ingredient.placeholder = "재료이름"
    ingredient.classList.add("col-6", "ingredient-name")

    const volume = document.createElement("input");
    volume.placeholder = "용량"
    volume.classList.add("col-6", "ingredient-volume")

    row.appendChild(ingredient);
    row.appendChild(volume);

    return row;
}
const getIngredientsData = () => {
    const ingredientData = [];
    Array.from(ingredientRows.children).forEach((row) => {
        const ingredientName = row.children[0].value;
        const volume = row.children[1].value;
        const unit = row.children[2].value;

        ingredientData.push({
            name: ingredientName,
            volume: volume,
            unit: unit
        });
    });
    return ingredientData;
}

const submitForm = (e) => {
    e.preventDefault();
    // 1. 기존 form 데이터 가져오기
    const formData = new FormData(document.querySelector('#cocktail-form'));

    // 4. 기존 form 데이터에 ingredients 데이터 추가하기
    const ingredientData = getIngredientsData();
    for(let i=0;i<ingredientData.length;i++){
        formData.append(`ingredients[${i}]`, JSON.stringify(ingredientData[i]));
    }

    // 5. 서버로 form 데이터 전송하기
    fetch('/cockTail/register', {
        method: 'POST',
        body: formData,
    })
        .then(response => {
            // 응답 처리
            response.json().then(data => {
                console.log('Submitted JSON data:', data)
                alert('Form submitted successfully!');
                location.reload();
            })
        })
        .catch(error => {
            // 에러 처리
            alert('Error submitting form:', error);
            location.reload();
        });
};

const onClick = (e) => {
    const id = e.target.parentElement.dataset.id;
    const name = e.target.parentElement.dataset.name;
    fetch(`/cockTail/delete?id=${id}&&name=${name}`)
        .then(data => {
            // 응답 데이터 처리
            console.log(data);
            e.target.parentElement.remove();
        })
        .catch(error => {
            // 에러 처리
            console.error(error);
        });
}

document.querySelectorAll(".cocktailDelete").forEach(item => {
    item.addEventListener('click', (e) => {
        onClick(e);
    });
});

document.querySelectorAll(".openDetail").forEach(item => {
    item.addEventListener('click', (e) => {
        let url = `/cockTail/Detail/${e.target.parentElement.dataset.id}`
        let option = "width = 850, height = 500, top = 100, left = 200, location = no"
        window.open(url, e.target.innerText, option)
    })
})

document.querySelector("#cocktail-form").addEventListener("submit", submitForm)