function buyHammer() {

    var quantity = document.getElementById('inputHammer').value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    var priceLabel = document.querySelector("label[for='inputHammer']");
    var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);

    var orderPrice = 34.0;
    var orderTime = new Date().toISOString();
    var customer = {loggedInUsername: "John Doe"};
    var quantityValue = quantity ? parseFloat(quantity) : 0;
    if (quantityValue !== 0) {
        var ordersDTO = {
            orderedTools: [
                {
                    toolName: "Hammer",
                    description: "Чук кози крак",
                    price: 34.0
                }
            ],
            orderPrice: price * quantityValue,
            orderTime: orderTime,
            quantity: quantityValue,
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
                addHammerToOrder();
                updateTotalOrderPrice();

            })
            .catch(error => {
                console.error(error);

            });
    } else {
        console.log('Quantity is 0. Order not processed.');
    }


    function addHammerToOrder() {
        var quantity = document.getElementById('inputHammer').value;
        var ordersSumBtn = document.getElementById('ordersSumBtn');
        ordersSumBtn.innerHTML += '<span>  Чук кози крак: ' + quantity + ' бр.</span><br>';
    }

}