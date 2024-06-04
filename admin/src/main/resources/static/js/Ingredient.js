const onDeleteIngredient = (e) => {
    fetch(`/ingredient/delete/${e.target.parentElement.dataset.id}`)
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

document.querySelectorAll(".ingredientDelete").forEach(item => {
    item.addEventListener('click',(e) => {
        onDeleteIngredient(e);
    })
})

document.querySelectorAll(".openIngredientDetail").forEach(item => {
    item.addEventListener('click', (e) => {
        let url = `/ingredient/detail/${e.target.parentElement.dataset.id}`
        let option = "width = 500, height = 500, top = 100, left = 200, location = no"
        window.open(url, e.target.innerText, option)
    })
})
