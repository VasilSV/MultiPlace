document.addEventListener('DOMContentLoaded', function () {
    var passwordInput = document.getElementById('password');
    var confirmPasswordInput = document.getElementById('confirmPassword');
    var errorContainer = document.getElementById('passwordError');

    confirmPasswordInput.addEventListener('input', function () {
        var password = passwordInput.value;
        var confirmPassword = confirmPasswordInput.value;

        if (password !== confirmPassword) {
            confirmPasswordInput.setCustomValidity('Passwords do not match');
            errorContainer.textContent = 'Passwords do not match';
        } else {
            confirmPasswordInput.setCustomValidity('');
            errorContainer.textContent = ''; // Изчистваме грешката
        }
    });
});