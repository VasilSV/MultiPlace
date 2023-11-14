

// let toolUpdateButton = document.getElementById('toolAddList');
// toolUpdateButton.addEventListener('click', toolAddList);


function toolAddList(){
    let toolContainer = document.getElementById('tool-update-container');
    toolContainer.innerHTML = ''


    let toolNameInput = document.getElementById('toolName');
    let toolDescriptionInput = document.getElementById('toolDescription');
    let toolPriceInput = document.getElementById('toolPrice');

    document.getElementById('toolAddList').addEventListener('click',function () {
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
        fetch('/api/tools', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
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
    if (!toolNameInput) {
        console.error('Element with ID "toolName" not found.');
        return;
    }

}
