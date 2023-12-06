
let toolUpdateButton = document.getElementById('toolAddList');
toolUpdateButton.addEventListener('click', function () {


    let toolNameInput = document.getElementById('toolName');
    if (!toolNameInput || !toolNameInput.value) {
        console.error('Tool name is missing or empty.');
        return;
    }

    let toolDescriptionInput = document.getElementById('toolDescription');
    let toolPriceInput = document.getElementById('toolPrice');
    let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');


    var toolName = toolNameInput.value;
    var toolDescription = toolDescriptionInput.value;
    var toolPrice = toolPriceInput.value;


    var data = {
        toolName: toolName,
        description: toolDescription,
        price: toolPrice

    };



    fetch('http://localhost:8080/api/tools', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(newTool => {
            // Обновяване на интерфейса
            console.log('New tool added:', newTool);
            // toolName.innerHTML = '';
            // toolDescriptionInput.value = '';
            // toolPriceInput.value = '';
        })
        .catch(error => {
            console.error('Error adding new tool:', error);
        });
});