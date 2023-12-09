function buyHelmet() {

    var quantity = document.getElementById('inputHelmet').value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    var priceLabel = document.querySelector("label[for='inputHelmet']");
    var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);
    // var loggedInUsername = sessionStorage.getItem('loggedInUsername');
    var orderPrice = 34.0;
    var orderTime = new Date().toISOString();  // Пример за текущата дата и час
    var customer = {loggedInUsername: 'John Doe'};
    var quantityValue = quantity ? parseFloat(quantity) : 0;
    // console.log('customer:', JSON.stringify(customer));
    if (quantityValue !== 0) {
        var ordersDTO = {
            orderedTools: [
                {
                    toolName: "Helmet",
                    description: "Строителна каска",
                    price: 34.0
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
                addHelmetToOrder();
                updateTotalOrderPrice();

            })
            .catch(error => {
                console.error(error);

            });
    } else {
        console.log('Quantity is 0. Order not processed.');
    }


    function addHelmetToOrder() {
        var quantity = document.getElementById('inputHelmet').value;
        var ordersSumBtn = document.getElementById('ordersSumBtn');
        ordersSumBtn.innerHTML += '<span> Строителна каска: ' + quantity + ' бр.</span><br>';
    }

}