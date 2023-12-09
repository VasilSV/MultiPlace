
    // Вземане на текущата дата и час
    var currentDate = new Date();

    // Форматиране на датата и часа (пример: "ден-месец-година час:минута:секунда")
    var formattedDateTime = currentDate.toLocaleString();

    // Визуализиране на стойността в елемента
    var ordElement = document.getElementById('orderTime');
    ordElement.textContent = 'Вашата поръчка беше успешно приета на: ' + formattedDateTime;
