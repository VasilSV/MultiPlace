function addShovelToOrder() {

        var quantity = document.getElementById('inputShovel').value;
        var priceLabel = document.querySelector("label[for='inputShovel']");
        var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);

        var ordersSumBtn = document.getElementById('ordersSumBtn');
        var lineItemPrice = price * quantity;

        ordersSumBtn.innerHTML += '<span> Лопата : ' + quantity + ' бр. - ' + lineItemPrice.toFixed(2) + ' лв.</span><br>';


        totalOrderPrice += lineItemPrice;


        showTotalOrderPrice();
}

function showTotalOrderPrice() {
        var totalOrderPriceElement = document.getElementById('totalOrderPrice');
        totalOrderPriceElement.textContent = 'Обща цена на поръчката: ' + totalOrderPrice.toFixed(2) + ' лв.';

}
