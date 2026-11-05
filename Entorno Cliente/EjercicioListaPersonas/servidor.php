<?php
header('Content-Type: application/json; charset=utf-8');
require_once "conexion.php";

$conn = conectar2("ajax", "root", "");

if (!$conn) {
    http_response_code(500);
    echo json_encode(["error" => "No se ha podido conectar con la base de datos"]);
    exit;
}

$datos = json_decode(file_get_contents("php://input"));

if (!$datos || !isset($datos->servicio)) {
    http_response_code(400);
    echo json_encode(["error" => "Solicitud no válida"]);
    exit;
}

try {
    switch ($datos->servicio) {
        case "listar":
            echo json_encode(listadoPersonas(), JSON_UNESCAPED_UNICODE);
            break;
        case "insertar":
            insertarPersona($datos);
            echo json_encode(listadoPersonas(), JSON_UNESCAPED_UNICODE);
            break;
        case "borrar":
            borrarPersona($datos->id ?? null);
            echo json_encode(listadoPersonas(), JSON_UNESCAPED_UNICODE);
            break;
        case "modificar":
            modificarPersona($datos);
            echo json_encode(listadoPersonas(), JSON_UNESCAPED_UNICODE);
            break;
        case "selPersonaID":
            echo json_encode(selPersonaID($datos->id ?? null), JSON_UNESCAPED_UNICODE);
            break;
        default:
            http_response_code(400);
            echo json_encode(["error" => "Servicio desconocido"]);
    }
} catch (Throwable $e) {
    http_response_code(500);
    echo json_encode(["error" => "Error en la operación", "detalle" => $e->getMessage()]);
}

function listadoPersonas() {
    global $conn;
    $stmt = $conn->query("SELECT * FROM personas ORDER BY ID");
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

function insertarPersona($datos) {
    global $conn;
    if (empty($datos->dni) || empty($datos->nombre) || empty($datos->apellidos)) {
        throw new Exception("Todos los campos son obligatorios");
    }
    $sql = "INSERT INTO personas (DNI, NOMBRE, APELLIDOS) VALUES (?, ?, ?)";
    $conn->prepare($sql)->execute([$datos->dni, $datos->nombre, $datos->apellidos]);
}

function borrarPersona($id) {
    global $conn;
    if ($id === null) throw new Exception("Falta el ID");
    $conn->prepare("DELETE FROM personas WHERE ID = ?")->execute([$id]);
}

function modificarPersona($datos) {
    global $conn;
    if (!isset($datos->id) || empty($datos->dni) || empty($datos->nombre) || empty($datos->apellidos)) {
        throw new Exception("Faltan datos para modificar");
    }
    $sql = "UPDATE personas SET DNI = ?, NOMBRE = ?, APELLIDOS = ? WHERE ID = ?";
    $conn->prepare($sql)->execute([$datos->dni, $datos->nombre, $datos->apellidos, $datos->id]);
}

function selPersonaID($id) {
    global $conn;
    $stmt = $conn->prepare("SELECT * FROM personas WHERE ID = ?");
    $stmt->execute([$id]);
    return $stmt->fetch(PDO::FETCH_ASSOC);
}
?>
