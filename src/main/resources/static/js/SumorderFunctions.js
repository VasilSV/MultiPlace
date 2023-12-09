// Функция за изчисляване и визуализация на общата сума
function calculateTotalOrderPricesAndDisplay() {
    fetch('/api/orders/calculateTotalOrderPrices')
        .then(response => response.json())
        .then(data => {
            var totalOrderPrice = parseFloat(data.totalOrderPrices) || 0;
            showTotalOrderPrice(totalOrderPrice);
        })
        .catch(error => console.error('Грешка при извикване на заявка:', error));
}

// Функция за показване на общата сума
function showTotalOrderPrice(totalOrderPrice) {
    var totalOrderPriceElement = document.getElementById('totalOrderPrices');
    totalOrderPriceElement.textContent = 'Обща цена на поръчката: ' + totalOrderPrice.toFixed(2) + ' лв.';
}

// Извикване на функцията при зареждане на страницата
document.addEventListener('DOMContentLoaded', calculateTotalOrderPricesAndDisplay);
