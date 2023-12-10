
var totalOrderPrice = 0;

function addGlovesToOrder() {
    var quantity = document.getElementById('inputGloves').value;

    var priceLabel = document.querySelector("label[for='inputGloves']");
    var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);
    var ordersSumBtn = document.getElementById('ordersSumBtn');

    var lineItemPrice = price * quantity;
    ordersSumBtn.innerHTML += '<span> Работни ръкавици: ' + quantity + ' бр. - ' + lineItemPrice.toFixed(2) + ' лв.</span><br>';
    totalOrderPrice += lineItemPrice;

    showTotalOrderPrice();
}

function showTotalOrderPrice() {
    var totalOrderPriceElement = document.getElementById('totalOrderPrice');
    totalOrderPriceElement.textContent = 'Обща цена на поръчката: ' + totalOrderPrice.toFixed(2) + ' лв.';
}











// function addGlovesToOrder() {
//     var quantity = document.getElementById('inputGloves').value;
//     var ordersSumBtn = document.getElementById('ordersSumBtn');
//     var totalOrderPriceSpan = document.getElementById('totalOrderPrice');
//
//     var priceLabel = document.querySelector("label[for='inputGloves']");
//     var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);
//
//     var totalOrderPrice = parseFloat(ordersSumBtn.dataset.totalOrderPrice) || 0;
//
//     totalOrderPrice += price * quantity;
//
//     ordersSumBtn.dataset.totalOrderPrice = totalOrderPrice;
//     ordersSumBtn.innerHTML = `Списък със заявени продукти: ${totalOrderPrice.toFixed(2)} лв.`;
//
//     totalOrderPriceSpan.textContent = `Обща сума: ${totalOrderPrice.toFixed(2)} лв.`;

    // var quantity = document.getElementById('inputGloves').value;
    //
    //
    // var ordersSumBtn = document.getElementById('ordersSumBtn');
    // ordersSumBtn.innerHTML += '<span> Работни ръкавици: ' + quantity + ' бр.</span><br>';

