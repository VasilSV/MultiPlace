
function addHammerToOrder() {
        var quantity = document.getElementById('inputHammer').value;
        var priceLabel = document.querySelector("label[for='inputHammer']");
        var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);

        var ordersSumBtn = document.getElementById('ordersSumBtn');
        var lineItemPrice = price * quantity;

        ordersSumBtn.innerHTML += '<span>  Чук кози крак: ' + quantity + ' бр. - ' + lineItemPrice.toFixed(2) + ' лв.</span><br>';


        totalOrderPrice += lineItemPrice;


        showTotalOrderPrice();
}

function showTotalOrderPrice() {
        var totalOrderPriceElement = document.getElementById('totalOrderPrice');
        totalOrderPriceElement.textContent = 'Обща цена на поръчката: ' + totalOrderPrice.toFixed(2) + ' лв.';
}


// function addHammerToOrder() {
//
//         var quantity = document.getElementById('inputHammer').value;
//         var ordersSumBtn = document.getElementById('ordersSumBtn');
//         var totalOrderPriceSpan = document.getElementById('totalOrderPrice');
//
//         var priceLabel = document.querySelector("label[for='inputHammer']");
//         var price = parseFloat(priceLabel.textContent.match(/\d+(\.\d+)?/)[0]);
//
//
//         var totalOrderPrice = parseFloat(ordersSumBtn.dataset.totalOrderPrice) || 0;
//
//
//         totalOrderPrice += price * quantity;
//
//
//         ordersSumBtn.dataset.totalOrderPrice = totalOrderPrice;
//         ordersSumBtn.innerHTML = `Списък със заявени продукти: ${totalOrderPrice.toFixed(2)} лв.`;
//
//         totalOrderPriceSpan.textContent = `Обща сума: ${totalOrderPrice.toFixed(2)} лв.`;
//
// }
