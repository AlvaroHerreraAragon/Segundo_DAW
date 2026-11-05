USE ajax;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL
);

CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    producto VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(id)
);

INSERT INTO clientes (nombre, ciudad) VALUES
('Carlos García', 'Sevilla'),
('María López', 'Dos Hermanas'),
('Antonio Pérez', 'Camas'),
('Laura Sánchez', 'Alcalá de Guadaíra');

INSERT INTO pedidos (idCliente, producto, cantidad, precio, fecha) VALUES
(1, 'Teclado mecánico', 2, 45.50, '2026-10-10'),
(1, 'Ratón inalámbrico', 1, 25.00, '2026-10-12'),
(1, 'Monitor 24 pulgadas', 1, 139.99, '2026-10-15'),
(2, 'Portátil', 1, 650.00, '2026-10-18'),
(2, 'Alfombrilla', 3, 8.50, '2026-10-20'),
(3, 'Disco SSD 1TB', 2, 75.00, '2026-10-22'),
(4, 'Memoria RAM 16GB', 2, 42.00, '2026-10-25');
