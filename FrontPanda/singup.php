<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

require_once 'database.php';

$message = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Verificar que todos los campos estén completos
    if (!empty($_POST['nombre']) && !empty($_POST['username']) && !empty($_POST['correo']) && !empty($_POST['password'])) {

        $sql = "INSERT INTO usuarios (nombre, usuario, correo, contrasena) VALUES (?, ?, ?, ?)";
        $stmt = $conn->prepare($sql);

        $nombre = $_POST['nombre'];
        $usuario = $_POST['username'];
        $correo = $_POST['correo'];
        $password = password_hash($_POST['password'], PASSWORD_BCRYPT);

        $stmt->bind_param("ssss", $nombre, $usuario, $correo, $password);

        if ($stmt->execute()) {
            // Redirige automáticamente a index.html
            header("Location: index.html");
            exit();
        } else {
            $message = 'Error al registrar el usuario: ' . $stmt->error;
        }
    } else {
        $message = 'Todos los campos son obligatorios.';
    }
}
$conn->close();
?>

<p><?php echo $message; ?></p>
