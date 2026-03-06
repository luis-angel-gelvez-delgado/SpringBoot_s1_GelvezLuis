function login() {
    fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        })
    })
    .then(response => {
        if (!response.ok) throw new Error("Credenciales no válidas");
        return response.json();
    })
    .then(data => {
        localStorage.setItem("token", data.token);
        alert("Login exitoso");
        window.location.href = "/view/vistas.html";
    })
    .catch(error => {
        localStorage.removeItem("token");
        alert(error.message);
    });
}