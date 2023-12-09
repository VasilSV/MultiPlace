function buyMGHammer() {

    var quantity = document.getElementById('inputMGHammer').value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    var priceLabel = document.querySelector("label[for='inputMGHammer']");
    var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);

    var orderPrice = 49.0;
    var orderTime = new Date().toISOString();
    var customer = {loggedInUsername: "John Doe"};
    var quantityValue = quantity ? parseFloat(quantity) : 0;
    if (quantityValue !== 0) {
        var ordersDTO = {
            orderedTools: [
                {
                    toolName: "Magnetic Hammer",
                    description: "Чук магнитен",
                    price: 34.0
                }
            ],
            orderPrice: price * quantityValue,
            orderTime: orderTime,
            quantity: quantityValue,
            customer: customer,
            // price: price
        };
        //totalOrderPrice += ordersDTO.price * quantity;
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
                addMGHammerToOrder();
                //   showTotalOrderPrice();

            })
            .catch(error => {
                console.error(error);

            });
    } else {
        console.log('Quantity is 0. Order not processed.');
    }

    function addMGHammerToOrder() {
        var quantity = document.getElementById('inputMGHammer').value;
        var ordersSumBtn = document.getElementById('ordersSumBtn');
        ordersSumBtn.innerHTML += '<span> Чук магнитен: ' + quantity + ' бр.</span><br>';
    }

// }
// function showTotalOrderPrice() {
//     var totalOrderPriceElement = document.getElementById('totalOrderPrice');
//     totalOrderPriceElement.textContent = 'Обща цена на поръчката: ' + totalOrderPrice.toFixed(2) + ' лв.';
}