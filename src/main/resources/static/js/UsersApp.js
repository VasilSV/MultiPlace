let customerLoadListButton = document.getElementById('customerLoadList');
customerLoadListButton.addEventListener('click', customerLoadList)


function customerLoadList() {

    let userContainer = document.getElementById('user-container');
    userContainer.innerHTML = ''

    fetch('http://localhost:8080/api/users')
        .then(response => response.json())
        .then(json => json.forEach(user => {
            console.log(user)
            let userRow = document.createElement("tr")

            let idCol = document.createElement("td")
            let usernameCol = document.createElement("td")
            let identificationNumberCol = document.createElement("td")
            let emailCol = document.createElement("td")

            let operationCol = document.createElement('td')

            idCol.textContent = user.id
            usernameCol.textContent = user.username
            identificationNumberCol.textContent = user.identificationNumber
            emailCol.textContent = user.email


            let deleteUserBtn = document.createElement('button')
            deleteUserBtn.innerHTML = 'DELETE'
            deleteUserBtn.dataset.id = user.id
            deleteUserBtn.addEventListener('click', deleteBtnClicked)

            operationCol.appendChild(deleteUserBtn)

            userRow.appendChild(idCol)
            userRow.appendChild(usernameCol)
            userRow.appendChild(identificationNumberCol)
            userRow.appendChild(emailCol)

            userRow.appendChild(operationCol)

            userContainer.append(userRow)

        }))

    function deleteBtnClicked(event) {

        let userId = event.target.dataset.id;
        let requestOptions = {
            method: "DELETE"
        }

        fetch(`http://localhost:8080/api/users/${userId}`, requestOptions)
            .then(_ => customerLoadList())
            .catch(error => console.log('error', error))

    }
}