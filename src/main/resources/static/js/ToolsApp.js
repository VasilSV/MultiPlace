let toolListButton = document.getElementById('toolLoadList');
toolListButton.addEventListener('click', toolLoadList)


function toolLoadList() {

    let toolContainer = document.getElementById('tool-container');
    toolContainer.innerHTML = ''

    fetch('http://localhost:8080/api/tools')
        .then(response => response.json())
        .then(json => json.forEach(tool => {
            console.log(tool)
            let toolRow = document.createElement("tr")

            let toolNameCol = document.createElement("td")
            let descriptionCol = document.createElement("td")
            let priceCol = document.createElement("td")


            let operationCol = document.createElement('td')

            toolNameCol.textContent = tool.toolName
            descriptionCol.textContent = tool.description
            priceCol.textContent = tool.price


            let deleteToolBtn = document.createElement('button')
            deleteToolBtn.innerHTML = 'DELETE'
            deleteToolBtn.dataset.id = tool.id
            deleteToolBtn.addEventListener('click', deleteBtnClicked)

            operationCol.appendChild(deleteToolBtn)

            toolRow.appendChild(toolNameCol)
            toolRow.appendChild(descriptionCol)
            toolRow.appendChild(priceCol)

            toolRow.appendChild(operationCol)

            toolContainer.append(toolRow)

        }))

    function deleteBtnClicked(event) {
        let thisId = event.target.dataset.id;
        let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

        let requestOptions = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken  // Поставяне на CSRF токена в хедъра
            }

        }

        fetch(`http://localhost:8080/api/tools/${thisId}`, requestOptions)
            .then(_ => toolLoadList())
            .catch(error => console.log('error', error))

    }

}