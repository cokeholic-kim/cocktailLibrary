const onClick = (e) => {
    const name = e.target.parentElement.dataset.title
    fetch(`/banner/delete?name=${name}`)
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

document.querySelectorAll(".openDetail").forEach(item => {
    item.addEventListener('click', (e) => {
        let url = `/banner/detail/${e.target.parentElement.dataset.title}`
        let option = "width = 850, height = 800, top = 100, left = 200, location = no"
        window.open(url, e.target.innerText, option)
    })
})

document.querySelectorAll(".bannerDelete").forEach(item => {
    item.addEventListener('click', (e) => {
        onClick(e);
    });
});