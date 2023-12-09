
function saveChanges() {
    var newIdentificationNumber = document.getElementById("newIdentificationNumber").value;
    var newPassword = document.getElementById("newPassword").value;


    var csrfToken = document.querySelector('meta[name="_csrf"]').content;


    var profileContainer = document.getElementById("profile-container");
    if (profileContainer && profileContainer.children.length > 0) {

        var email = profileContainer.querySelector('td[data-label="Email"]').textContent;

        var data = {
            email: email,
            newIdentificationNumber: newIdentificationNumber,
            newPassword: newPassword
        };

        fetch('http://localhost:8080/api/company/update-profile', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Обновление на клиентската страна
                console.log("User details updated successfully!", data);
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);

                // Проверка за грешка от сървъра и наличие на тяло
                if (error instanceof Response && error.status === 400) {
                    const contentType = error.headers.get('content-type');
                    if (contentType && (contentType.includes('application/json') || contentType.includes('text/plain'))) {
                        return error.json().then(errorData => {
                            console.error('Server error message:', errorData);
                        }).catch(error => {
                            console.error('Error parsing server error message:', error);
                        });
                    }
                }

                console.error('Server error message is not of type application/json or text/plain.');
                return Promise.reject('Server error message is not of type application/json or text/plain.');
            });
    } else {
        console.error('#profile-container does not exist or has no children.');
    }

}

// Пример за добавяне на събитие на бутон
var updateButton = document.getElementById('updateUserDetail');
if (updateButton) {
    updateButton.addEventListener('click', saveChanges);
}



