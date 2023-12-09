// SumOrders.js

// Вземане на параметъра от URL
var urlParams = new URLSearchParams(window.location.search);
var totalOrderPrice = urlParams.get('totalOrderPrice');

// Актуализиране на общата сума
updateTotalOrderPrice();
