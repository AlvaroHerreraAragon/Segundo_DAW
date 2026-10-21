<?php
function Conectar($bd, $usuario, $clave) {
    try {
        $conn = new PDO(
            "mysql:host=localhost;dbname=" . $bd . ";charset=utf8mb4",
            $usuario,
            $clave,
            [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
             PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC]
        );
        return $conn;
    } catch (PDOException $e) {
        return null;
    }
}

function conectar2($bd, $usuario, $clave) {
    return Conectar($bd, $usuario, $clave);
}

function Consulta($conn, $sc) {
    return $conn->query($sc);
}
?>
