function addGlovesToOrder() {

    var quantity = document.getElementById('inputGloves').value;


    var ordersSumBtn = document.getElementById('ordersSumBtn');
    ordersSumBtn.innerHTML += '<span> Работни ръкавици: ' + quantity + ' бр.</span><br>';
}
