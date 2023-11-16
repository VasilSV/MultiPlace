let toolUpdateButton = document.getElementById('toolAddList');
toolUpdateButton.addEventListener('click', function () {
    let toolContainer = document.getElementById('tool-update-container');
    toolContainer.innerHTML = '';

    let toolNameInput = document.getElementById('toolName');
    if (!toolNameInput || !toolNameInput.value) {
        console.error('Tool name is missing or empty.');
        return;
    }

    let toolDescriptionInput = document.getElementById('toolDescription');
    let toolPriceInput = document.getElementById('toolPrice');
    let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

    // Вземане на данните от формата
    var toolName = toolNameInput.value;
    var toolDescription = toolDescriptionInput.value;
    var toolPrice = toolPriceInput.value;

    // Подготвяне на данните за изпращане към бекенда
    var data = {
        toolName: toolName,
        description: toolDescription,
        price: toolPrice
    };

    // Изпращане на данните на бекенда чрез fetch или друга библиотека за AJAX
    fetch('http://localhost:8080/api/tools', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken  // Поставяне на CSRF токена в хедъра
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(newTool => {
            // Обновяване на интерфейса или извършване на други операции
            console.log('New tool added:', newTool);
        })
        .catch(error => {
            console.error('Error adding new tool:', error);
        });
});