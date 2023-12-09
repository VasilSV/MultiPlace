
document.addEventListener('DOMContentLoaded', function () {
    var passwordInput = document.getElementById('password');
    var confirmPasswordInput = document.getElementById('confirmPassword');

    confirmPasswordInput.addEventListener('input', function () {
        var password = passwordInput.value;
        var confirmPassword = confirmPasswordInput.value;

        if (password !== confirmPassword) {
            confirmPasswordInput.setCustomValidity('Passwords do not match');
        } else {
            confirmPasswordInput.setCustomValidity('This Email is already used');
        }
    });
});
