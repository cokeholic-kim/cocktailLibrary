
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

const submitForm = (e) => {
    e.preventDefault();
    // 1. 기존 form 데이터 가져오기
    const formData = new FormData(document.querySelector('#cocktail-form'));

    // 2. 재료와 용량 데이터 가져오기
    const ingredientNameInputs = document.querySelectorAll('.ingredient-name');
    const ingredientVolumeInputs = document.querySelectorAll('.ingredient-volume');

    // 3. 재료와 용량 데이터를 JSON 객체로 구성하기
    const ingredients = {};
    ingredientNameInputs.forEach((input, index) => {
        if(input.value !== ""){
            ingredients[input.value] = ingredientVolumeInputs[index].value;
        }
    });

    // 4. 기존 form 데이터에 ingredients 데이터 추가하기
    formData.append('ingredients', JSON.stringify(ingredients));

    // 5. 서버로 form 데이터 전송하기
    fetch('/cockTail/register', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            // 응답 처리
            console.log(response, formData)
            alert('Form submitted successfully!');
            location.reload();
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
        let option = "width = 500, height = 500, top = 100, left = 200, location = no"
        window.open(url, e.target.innerText, option)
    })
})

document.querySelector("#addRow").addEventListener("click", (e) => {
    e.preventDefault();
    addRow(ingredientContainer)
});

document.querySelector("#cocktail-form").addEventListener("submit", submitForm)