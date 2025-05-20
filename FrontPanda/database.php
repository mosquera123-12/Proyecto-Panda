<?php
$servername = 'localhost:3306';
$username = 'root';
$password = '';
$database = 'MyPandabd';

// Crea una conexión a la base de datos
$conn = new mysqli($servername, $username, $password, $database);

// Verifica la conexión
if ($conn->connect_error) {
    // Si hay un error de conexión, muestra un mensaje de error amigable
    $error_message = "Error de conexión a la base de datos: " . $conn->connect_error;
    // Muestra el error en el navegador
    echo $error_message;
    // Si quieres registrar el error en un archivo de log:
    error_log($error_message, 3, "error.log");  // Esto guardará el error en un archivo llamado error.log
} else {
    // Si la conexión fue exitosa, muestra un mensaje de éxito
    echo "Conexión exitosa a la base de datos";
}

?>
