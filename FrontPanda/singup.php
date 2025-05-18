<?php

ini_set('display_errors', 1);
error_reporting(E_ALL);

require_once 'database.php';

$message = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (!empty($_POST['username']) && !empty($_POST['password'])) {
        // Se omite la verificación de "confirm_password"
        $sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
        $stmt = $conn->prepare($sql);
        $password = password_hash($_POST['password'], PASSWORD_BCRYPT);

        // Se utilizan parámetros con "?" en lugar de ":nombre" para la consulta
        $stmt->bind_param("ss", $_POST['username'], $password);

        if ($stmt->execute()) {
            $message = 'Usuario registrado correctamente';
        } else {
            $message = 'Error al registrar el usuario';
        }
    } else {
        $message = 'Todos los campos son obligatorios.';
    }
}
$conn->close();

?>
<p><?php echo $message; ?></p>
