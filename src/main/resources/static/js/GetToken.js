function getLoggedInUsername() {
    // Предполагаме, че токенът се съхранява в localStorage
    const token = localStorage.getItem('accessToken'); // Променете това спрямо начина, по който съхранявате токена

    if (token) {
        // Декодиране на токена
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        const payload = JSON.parse(jsonPayload);

        // Предполагаме, че в токена има информация за потребителя с ключ "sub" (променете го спрямо вашата конфигурация)
        const loggedInUsername = payload.sub;

        return loggedInUsername;
    }

    return null;
}

// Пример как можете да използвате функцията
let loggedInUsername = getLoggedInUsername();
console.log(loggedInUsername);
