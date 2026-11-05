CREATE TABLE IF NOT EXISTS alumnos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    curso VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS calificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAlumno INT NOT NULL,
    asignatura VARCHAR(100) NOT NULL,
    nota DECIMAL(4,2) NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (idAlumno) REFERENCES alumnos(id)
);

INSERT IGNORE INTO alumnos (id, nombre, curso) VALUES
(1, 'Carlos García', '2º DAW'),
(2, 'Laura Martínez', '2º DAW'),
(3, 'Pablo Sánchez', '1º DAW');

INSERT IGNORE INTO calificaciones
(id, idAlumno, asignatura, nota, fecha) VALUES
(1, 1, 'Desarrollo web en entorno cliente', 8.50, '2026-10-15'),
(2, 1, 'Desarrollo web en entorno servidor', 6.00, '2026-10-18'),
(3, 1, 'Despliegue de aplicaciones web', 4.00, '2026-10-20'),
(4, 2, 'Desarrollo web en entorno cliente', 9.00, '2026-10-15'),
(5, 2, 'Desarrollo web en entorno servidor', 7.50, '2026-10-18'),
(6, 3, 'Desarrollo web en entorno cliente', 3.50, '2026-10-15');