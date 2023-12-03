function buyGloves() {

    var quantity = document.getElementById('inputGloves').value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    var ordersDTO = {
        orderedTools: "Gloves",

        quantity: quantity
    };
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

        })
        .catch(error => {
            console.error(error);

        });
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