<?php
session_start();
include 'database.php';

// Validar datos del formulario
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $usuario = $_POST['username'];
    $contrasena = $_POST['password'];

    // Buscar usuario en la base de datos
    $sql = "SELECT * FROM usuarios WHERE usuario = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("s", $usuario);
    $stmt->execute();
    $resultado = $stmt->get_result();

    if ($resultado->num_rows === 1) {
        $fila = $resultado->fetch_assoc();

        if (password_verify($contrasena, $fila['contrasena'])) {
            // Inicio de sesión correcto
            $_SESSION['nombre_usuario'] = $fila['nombre']; // guardar nombre del usuario
            $_SESSION['usuario'] = $fila['usuario']; // opcional
            header("Location: home.php");
            exit();
        } else {
            // Contraseña incorrecta
            header("Location: index.html?error=1");
            exit();
        }
    } else {
        // Usuario no encontrado
        header("Location: index.html?error=1");
        exit();
    }
}
?>