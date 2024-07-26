const handleSearch = (event) => {
    const searchValue = event.target.value;
    const filtered = ingredients.filter(ingredients => ingredients.name.includes(searchValue));

    searchResult.innerHTML = "";

    filtered.forEach(ingredients => {
        searchResult.appendChild(resultTemplate(ingredients));
    })
}

const handleChange =  (ingredient) => (e) => {
    const newValue = e.target.value; // 변경된 값
    const ingredientName = ingredient.name; // 현재 ingredient의 이름

    addedIngredientData.forEach(item => {
        if (item.name === ingredientName) {
            item[e.target.name] = newValue; // volume 필드 업데이트
        }
    });
}

const resultTemplate = (ingredient) => {
    const div = document.createElement("div")
    div.classList.add("d-flex","row","align-items-center","mb-4")
    div.addEventListener("click", onResultClick)
    div.dataset.ingredient = JSON.stringify(ingredient);

    div.addEventListener("mouseenter", () => {
        div.style.backgroundColor = "#f5f5f5"; // 마우스 hover 시 연한 회색 배경색 변경
    });

    div.addEventListener("mouseleave", () => {
        div.style.backgroundColor = "transparent"; // 마우스 hover 종료 시 배경색 초기화
    });

    const imageOuter = document.createElement("div")
    imageOuter.classList.add("position-relative","rounded-circle","col-2","overflow-hidden")
    imageOuter.style.height = "5rem"

    const imageInner = document.createElement("div")
    imageInner.classList.add("position-absolute","bg-secondary-subtle","d-flex","justify-content-center","align-items-center")
    imageInner.style.inset = "0"

    const imageDom = document.createElement("img")
    imageDom.classList.add("object-fit-contain","w-100","h-100")
    imageDom.src = ingredient.image

    const nameDom = document.createElement("p")
    nameDom.classList.add("col-10")
    nameDom.innerText = ingredient.name;


    imageInner.appendChild(imageDom)
    imageOuter.appendChild(imageInner)

    div.appendChild(imageOuter)
    div.appendChild(nameDom)
    return div;
}

const createRow = (ingredient) => {
    const row = document.createElement("div");
    row.classList.add("row","p-2","d-flex","align-items-center");

    const ingredientName = document.createElement("input");
    ingredientName.placeholder = "재료이름";
    ingredientName.disabled = "true";
    ingredientName.value = ingredient.name;
    ingredientName.classList.add("col-4");

    const volume = document.createElement("input");

    volume.type = "number";
    volume.placeholder = "용량";
    volume.name = "volume"
    if(ingredient.volume != null){
        volume.value = ingredient.volume;
    }
    volume.classList.add("col-3");
    volume.addEventListener("change",handleChange(ingredient))

    const unitDom = document.createElement("select");
    units.forEach(unit => {
        const optionElement = document.createElement('option');
        optionElement.value = unit;
        optionElement.textContent = unit;

        if (ingredient.unit && unit === ingredient.unit) {
            optionElement.selected = true; // 해당 옵션 선택
        }

        unitDom.add(optionElement);
    })

    unitDom.name = "unit"
    unitDom.classList.add("col-3");
    unitDom.addEventListener("change",handleChange(ingredient))

    const deleteBtn = document.createElement("p")
    deleteBtn.role="button"
    deleteBtn.classList.add("col-2");
    deleteBtn.innerText = "❌"
    deleteBtn.addEventListener("click", (e) => {
        deleteAddedIngredient(ingredient);
    })

    row.appendChild(ingredientName);
    row.appendChild(volume);
    row.appendChild(unitDom);
    row.appendChild(deleteBtn);

    return row;
}

const createIngredientImage = (ingredient) => {
    const imageOuter = document.createElement("div")
    imageOuter.classList.add("position-relative","rounded-circle","col-2","overflow-hidden")
    imageOuter.style.paddingTop = "15%"

    const imageInner = document.createElement("div")
    imageInner.classList.add("position-absolute","bg-secondary-subtle","d-flex","justify-content-center","align-items-center")
    imageInner.style.inset = "0"

    const imageDom = document.createElement("img")
    imageDom.classList.add("object-fit-contain","w-100","h-100")
    imageDom.src = ingredient.image

    imageInner.appendChild(imageDom)
    imageOuter.appendChild(imageInner)
    return imageOuter;
}

const deleteAddedIngredient = (ingredient) => {
    addedIngredientData = addedIngredientData.filter(item => item.name !== ingredient.name);
    renderAddedIngredient();
}

const renderAddedIngredient = () => {
    addedIngredient.innerHTML = "";
    ingredientRows.innerHTML = "";

    addedIngredientData.forEach(ingredient => {
        const row = createRow(ingredient);
        const addedIngredientCard = createIngredientImage(ingredient);

        ingredientRows.appendChild(row);
        addedIngredient.appendChild(addedIngredientCard);
    })
}

const preventDuplicate = (ingredient) => {
    return addedIngredientData.some(item => item.name === ingredient.name);
}

const onResultClick = (event) => {
    const ingredient = JSON.parse(event.target.dataset.ingredient);
    if(preventDuplicate(ingredient)) return;
    addedIngredientData.push(ingredient)
    renderAddedIngredient()
}

searchInput.addEventListener('keyup', handleSearch);