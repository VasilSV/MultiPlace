function buyCoser() {

    var quantity = document.getElementById('inputCoser').value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    var priceLabel = document.querySelector("label[for='inputCoser']");
    var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);

    var orderPrice = 22.0;
    var orderTime = new Date().toISOString();
    var customer = { loggedInUsername: "John Doe"};

    var ordersDTO = {
        orderedTools: [
            {
                toolName: "Coser",
                description: "Косер",
                price: 22.0
            }
        ],
        orderPrice: price * quantity ,
        orderTime: orderTime,
        quantity: quantity,
        customer: customer
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
            addCoserToOrder();
            updateTotalOrderPrice();

        })
        .catch(error => {
            console.error(error);

        });
}
function addCoserToOrder() {
    var quantity = document.getElementById('inputCoser').value;
    var ordersSumBtn = document.getElementById('ordersSumBtn');
    ordersSumBtn.innerHTML += '<span> Косер: ' + quantity + ' бр.</span><br>';
}

