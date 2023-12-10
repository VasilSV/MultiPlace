let customerLoadListButton = document.getElementById('customerDetails');
customerLoadListButton.addEventListener('click', customerDetails)

let loggedInUser = null;

function customerDetails() {

    let profileContainer = document.getElementById('profile-container');
    profileContainer.innerHTML = '';

    if (loggedInUser) {
        let userRow = document.createElement("tr")

        let usernameCol = document.createElement("td")
        let identificationNumberCol = document.createElement("td")
        let emailCol = document.createElement("td")
        let roleCol = document.createElement("td")

        usernameCol.textContent = loggedInUser.username
        identificationNumberCol.textContent = loggedInUser.identificationNumber
        emailCol.textContent = loggedInUser.email
        roleCol.textContent = loggedInUser.role

        userRow.appendChild(usernameCol)
        userRow.appendChild(identificationNumberCol)
        userRow.appendChild(emailCol)
        userRow.appendChild(roleCol)

        profileContainer.append(userRow)
    }
}

async function login(username, password) {

    try {
        const response = await fetch('http://localhost:8080/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password,
            }),
        });

        if (response.ok) {
            // Вземане на информацията за логнатия потребител от отговора
            loggedInUser = await response.json();
            console.log('Успешен вход:', loggedInUser);
            // Можете да направите каквото и да е нужно след успешен вход
        } else {
            console.error('Неуспешен вход');
        }
    } catch (error) {
        console.error('Грешка при вход:', error);
    }


}