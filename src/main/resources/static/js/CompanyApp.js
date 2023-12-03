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

//
//     fetch('http://localhost:8080/api/users')
//         .then(response => response.json())
//         .then(json => json.forEach(user => {
//             if (loggedInUser && user.log() === loggedInUser.username) {
//                 console.log(user)
//                 let userRow = document.createElement("tr")
//
//                 let usernameCol = document.createElement("td")
//                 let identificationNumberCol = document.createElement("td")
//                 let emailCol = document.createElement("td")
//                 let roleCol = document.createElement("td")
//                 let operationCol = document.createElement('td')
//
//
//                 usernameCol.textContent = user.username
//                 identificationNumberCol.textContent = user.identificationNumber
//                 emailCol.textContent = user.email
//                 roleCol.textContent = user.role
//
//                 // let updateUserBtn = document.createElement('button')
//                 // updateUserBtn.innerHTML = 'DELETE'
//                 // updateUserBtn.dataset.id = user.id
//                 //    updateUserBtn.addEventListener('click', updateBtnClicked)
//
//                 //   operationCol.appendChild(updateBtnClicked)
//
//
//                 userRow.appendChild(usernameCol)
//                 userRow.appendChild(identificationNumberCol)
//                 userRow.appendChild(emailCol)
//                 userRow.appendChild(roleCol)
//                 userRow.appendChild(operationCol)
//
//                 profileContainer.append(userRow)
//
//             }
//         }));
// }

// function updateBtnClicked(event) {
//
//     let userId = event.target.dataset.id;
//     // Вземане на CSRF токена от мета тага
//     let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
//
//     let requestOptions = {
//         method: "DELETE",
//         headers: {
//             'Content-Type': 'application/json',
//             'X-CSRF-TOKEN': csrfToken  // Поставяне на CSRF токена в хедъра
//         }
//  }
//   fetch(`http://localhost:8080/api/users/${userId}`, requestOptions)
//       .then(_ => customerDetails())
//       .catch(error => console.log('error', error))

//  }

// Функция за логин, може да бъде асинхронна, ако използвате fetch към сървъра
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