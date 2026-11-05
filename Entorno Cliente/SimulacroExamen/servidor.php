
<?php

$conexion = new mysqli("localhost", "root", "", "ajax");

if ($conexion->connect_error) {
    die("Error de conexión: " . $conexion->connect_error);
}

$datos = json_decode(file_get_contents("php://input"));

$servicio = $datos->servicio;

switch ($servicio) {

    case "clientes":
        $sql = "SELECT * FROM clientes ORDER BY nombre";
        $resultado = $conexion->query($sql);
        $clientes = [];

        while ($fila = $resultado->fetch_assoc()) {
            $clientes[] = $fila;
        }

        echo json_encode($clientes);
        break;

    case "pedidos":
        $idCliente = $datos->idCliente;

        $sql = "SELECT * FROM pedidos WHERE idCliente = $idCliente ORDER BY fecha";
        $resultado = $conexion->query($sql);
        $pedidos = [];

        while ($fila = $resultado->fetch_assoc()) {
            $pedidos[] = $fila;
        }

        echo json_encode($pedidos);
        break;

    case "anade":
        $idCliente = $datos->idCliente;
        $producto = $conexion->real_escape_string($datos->producto);
        $cantidad = $datos->cantidad;
        $precio = $datos->precio;
        $fecha = $datos->fecha;

        $sql = "INSERT INTO pedidos (idCliente, producto, cantidad, precio, fecha)
                VALUES ($idCliente, '$producto', $cantidad, $precio, '$fecha')";

        $conexion->query($sql);

        $sql = "SELECT * FROM pedidos WHERE idCliente = $idCliente ORDER BY fecha";
        $resultado = $conexion->query($sql);
        $pedidos = [];

        while ($fila = $resultado->fetch_assoc()) {
            $pedidos[] = $fila;
        }

        echo json_encode($pedidos);
        break;

    case "selPedidoID":
        $id = $datos->id;

        $sql = "SELECT * FROM pedidos WHERE id = $id";
        $resultado = $conexion->query($sql);

        echo json_encode($resultado->fetch_assoc());
        break;

    case "modificar":
        $id = $datos->id;
        $idCliente = $datos->idCliente;
        $producto = $conexion->real_escape_string($datos->producto);
        $cantidad = $datos->cantidad;
        $precio = $datos->precio;
        $fecha = $datos->fecha;

        $sql = "UPDATE pedidos SET
                producto = '$producto',
                cantidad = $cantidad,
                precio = $precio,
                fecha = '$fecha'
                WHERE id = $id";

        $conexion->query($sql);

        $sql = "SELECT * FROM pedidos WHERE idCliente = $idCliente ORDER BY fecha";
        $resultado = $conexion->query($sql);
        $pedidos = [];

        while ($fila = $resultado->fetch_assoc()) {
            $pedidos[] = $fila;
        }

        echo json_encode($pedidos);
        break;

    case "borra":
        $id = $datos->id;
        $idCliente = $datos->idCliente;

        $sql = "DELETE FROM pedidos WHERE id = $id";
        $conexion->query($sql);

        $sql = "SELECT * FROM pedidos WHERE idCliente = $idCliente ORDER BY fecha";
        $resultado = $conexion->query($sql);
        $pedidos = [];

        while ($fila = $resultado->fetch_assoc()) {
            $pedidos[] = $fila;
        }

        echo json_encode($pedidos);
        break;
}

$conexion->close();

?>
