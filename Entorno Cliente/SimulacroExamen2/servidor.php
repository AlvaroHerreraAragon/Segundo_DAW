<?php

// Conectamos con la base de datos
$conexion = new mysqli("localhost", "root", "", "ajax");

// Comprobamos la conexión
if ($conexion->connect_error) {
    die("Error de conexión: " . $conexion->connect_error);
}

// Recibimos los datos enviados desde JavaScript
$datos = json_decode(file_get_contents("php://input"), true);

// Obtenemos el servicio solicitado
$servicio = $datos["servicio"];

// Función que devuelve las calificaciones de un alumno
function obtenerCalificaciones($conexion, $idAlumno) {

    $sql = "SELECT * FROM calificaciones WHERE idAlumno = ?";

    $consulta = $conexion->prepare($sql);
    $consulta->bind_param("i", $idAlumno);
    $consulta->execute();

    $resultado = $consulta->get_result();

    return $resultado->fetch_all(MYSQLI_ASSOC);
}

// Ejecutamos el servicio solicitado
switch ($servicio) {

    // Obtener todos los alumnos
    case "alumnos":

        $sql = "SELECT * FROM alumnos ORDER BY nombre";

        $resultado = $conexion->query($sql);

        echo json_encode($resultado->fetch_all(MYSQLI_ASSOC));

        break;


    // Obtener las calificaciones de un alumno
    case "calificaciones":

        $idAlumno = $datos["idAlumno"];

        echo json_encode(obtenerCalificaciones($conexion, $idAlumno));

        break;


    // Añadir una calificación
    case "anade":

        $idAlumno = $datos["idAlumno"];
        $asignatura = $datos["asignatura"];
        $nota = $datos["nota"];
        $fecha = $datos["fecha"];

        $sql = "INSERT INTO calificaciones
                (idAlumno, asignatura, nota, fecha)
                VALUES (?, ?, ?, ?)";

        $consulta = $conexion->prepare($sql);
        $consulta->bind_param("isds", $idAlumno, $asignatura, $nota, $fecha);
        $consulta->execute();

        echo json_encode(obtenerCalificaciones($conexion, $idAlumno));

        break;


    // Obtener una calificación concreta
    case "selCalificacionID":

        $id = $datos["id"];

        $sql = "SELECT * FROM calificaciones WHERE id = ?";

        $consulta = $conexion->prepare($sql);
        $consulta->bind_param("i", $id);
        $consulta->execute();

        $resultado = $consulta->get_result();

        echo json_encode($resultado->fetch_assoc());

        break;


    // Modificar una calificación
    case "modificar":

        $id = $datos["id"];
        $idAlumno = $datos["idAlumno"];
        $asignatura = $datos["asignatura"];
        $nota = $datos["nota"];
        $fecha = $datos["fecha"];

        $sql = "UPDATE calificaciones
                SET asignatura = ?, nota = ?, fecha = ?
                WHERE id = ?";

        $consulta = $conexion->prepare($sql);
        $consulta->bind_param("sdsi", $asignatura, $nota, $fecha, $id);
        $consulta->execute();

        echo json_encode(obtenerCalificaciones($conexion, $idAlumno));

        break;


    // Borrar una calificación
    case "borra":

        $id = $datos["id"];
        $idAlumno = $datos["idAlumno"];

        $sql = "DELETE FROM calificaciones WHERE id = ?";

        $consulta = $conexion->prepare($sql);
        $consulta->bind_param("i", $id);
        $consulta->execute();

        echo json_encode(obtenerCalificaciones($conexion, $idAlumno));

        break;
}

$conexion->close();

?>