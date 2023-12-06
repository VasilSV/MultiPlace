function buyGloves() {

    var quantity = document.getElementById('inputGloves').value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    var ordersDTO = {
        orderedTools: [
            {
                toolName: "Gloves",
                description: "Работни ръкавици",
                price: 3.0
            }
        ],

        quantity: quantity
    };
    console.log('ordersDTO:', ordersDTO);
    fetch('http://localhost:8080/api/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(ordersDTO),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            addGlovesToOrder();

        })
        .catch(error => {
            console.error(error);

        });
}
function addGlovesToOrder() {
    var quantity = document.getElementById('inputGloves').value;
    var ordersSumBtn = document.getElementById('ordersSumBtn');
    ordersSumBtn.innerHTML += '<span> Работни ръкавици: ' + quantity + ' бр.</span><br>';
}

//     $.ajax({
//         url: 'http://localhost:8080/api/orders',
//         type: 'POST',
//         contentType: 'application/json',
//         data: JSON.stringify(ordersDTO),
//         success: function (response) {
//
//             console.log(response);
//         },
//         error: function (error) {
//
//             console.error(error);
//         }
//     });
// }