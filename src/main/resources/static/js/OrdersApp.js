let ordersLoadListButton = document.getElementById('ordersLoadList');
ordersLoadListButton.addEventListener('click', ordersLoadList)


function ordersLoadList() {

    let ordersContainer = document.getElementById('orders-container');
    ordersContainer.innerHTML = ''


    fetch('http://localhost:8080/api/orders')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(json => json.forEach(order => {
            console.log(order)
            let orderRow = document.createElement("tr")

            let orderTimeCol = document.createElement("td")
            let customerCol = document.createElement("td")
            let orderedToolsCol = document.createElement("td")
            let orderPriceCol = document.createElement("td")

            let operationCol = document.createElement('td')

            orderTimeCol.textContent = order.orderTime
            customerCol.textContent = order.custmer? order.customer.toString() : '';
            orderedToolsCol.textContent = order.orderedTools
                .map(tool => tool.toolName ).join(", ");
            orderPriceCol.textContent = order.price ? order.price.toString() : '';


            let deleteOrderBtn = document.createElement('button')
            deleteOrderBtn.innerHTML = 'DELETE'
            deleteOrderBtn.dataset.id = order.id
            // deleteOrderBtn.setAttribute('data-id', order.id)
            deleteOrderBtn.addEventListener('click', deleteBtnClicked)

            operationCol.appendChild(deleteOrderBtn)

            orderRow.appendChild(orderTimeCol)
            orderRow.appendChild(customerCol)
            orderRow.appendChild(orderedToolsCol)
            orderRow.appendChild(orderPriceCol)

            orderRow.appendChild(operationCol)

            ordersContainer.append(orderRow)

        })).catch(error => console.error('Fetch error:', error));

    // .catch(error => console.log('error', error));
    function deleteBtnClicked(event) {

        let orderId = event.target.dataset.id;

        let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

        let requestOptions = {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            }
        }
        fetch(`http://localhost:8080/api/orders/${orderId}`, requestOptions)
            .then(_ => ordersLoadList())
            .catch(error => console.log('error', error))

    }
}