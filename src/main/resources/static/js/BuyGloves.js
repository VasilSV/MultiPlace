function buyGloves() {

    var quantity = document.getElementById('inputGloves').value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    var priceLabel = document.querySelector("label[for='inputGloves']");
    var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);

    var orderPrice = 3.0;  // Пример за стойност, трябва да се промени спрямо вашия код
    var orderTime = new Date().toISOString();  // Пример за текущата дата и час
    var customer = {loggedInUsername: "John Doe"};
    var quantityValue = quantity ? parseFloat(quantity) : 0;
    if (quantityValue !== 0) {
        var ordersDTO = {
            orderedTools: [
                {
                    toolName: "Gloves",
                    description: "Работни ръкавици",
                    price: 3.0
                }
            ],
            orderPrice: price * quantityValue|| 0,
            orderTime: orderTime,
            quantity: quantityValue|| 0,
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
                addGlovesToOrder();
                updateTotalOrderPrice();

            })
            .catch(error => {
                console.error(error);

            });
    } else {
        console.log('Quantity is 0. Order not processed.');

    }

    function addGlovesToOrder() {
        var quantity = document.getElementById('inputGloves').value;
        var ordersSumBtn = document.getElementById('ordersSumBtn');
        ordersSumBtn.innerHTML += '<span> Работни ръкавици: ' + quantity + ' бр.</span><br>';
    }
}
