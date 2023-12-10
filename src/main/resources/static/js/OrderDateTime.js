

    var currentDate = new Date();


    var formattedDateTime = currentDate.toLocaleString();


    var ordElement = document.getElementById('orderTime');
    ordElement.textContent = 'Вашата поръчка беше успешно приета на: ' + formattedDateTime;
