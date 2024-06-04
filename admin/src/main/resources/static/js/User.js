const handleDeleteUser = (e) => {
    fetch(`/user/delete/${e.target.parentElement.dataset.id}/${e.target.parentElement.dataset.email}`)
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
document.querySelector(".joinButton").addEventListener("click", () => {
    let url = `/join`
    let option = "width = 500, height = 590, top = 100, left = 200, location = no"
    window.open(url, "회원가입", option)
})

document.querySelectorAll(".openUserDetail").forEach(item => {
    item.addEventListener('click', (e) => {
        let url = `/user/detail/${e.target.parentElement.dataset.id}`
        let option = "width = 500, height = 500, top = 100, left = 200, location = no"
        window.open(url, e.target.innerText, option)
    })
})

document.querySelectorAll(".userDelete").forEach(item => {
    item.addEventListener('click', (e) => {
        handleDeleteUser(e);
    });
});