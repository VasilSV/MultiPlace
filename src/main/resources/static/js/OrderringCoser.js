function addCoserToOrder() {

        var quantity = document.getElementById('inputCoser').value;
        var priceLabel = document.querySelector("label[for='inputCoser']");
        var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);

        var ordersSumBtn = document.getElementById('ordersSumBtn');
        var lineItemPrice = price * quantity;

        ordersSumBtn.innerHTML += '<span> Косер: ' + quantity + ' бр. - ' + lineItemPrice.toFixed(2) + ' лв.</span><br>';


        totalOrderPrice += lineItemPrice;


        showTotalOrderPrice();
}

function showTotalOrderPrice() {
        var totalOrderPriceElement = document.getElementById('totalOrderPrice');
        totalOrderPriceElement.textContent = 'Обща цена на поръчката: ' + totalOrderPrice.toFixed(2) + ' лв.';

}
