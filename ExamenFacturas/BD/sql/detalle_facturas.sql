-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-11-2025 a las 21:50:15
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ajax`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_facturas`
--

CREATE TABLE `detalle_facturas` (
  `id` int(11) NOT NULL,
  `id_factura` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `precio` double NOT NULL,
  `tipo_iva` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `detalle_facturas`
--

INSERT INTO `detalle_facturas` (`id`, `id_factura`, `cantidad`, `concepto`, `precio`, `tipo_iva`) VALUES
(2, 1, 4, 'Mortadela con olivas', 3.72, 4),
(3, 1, 3, 'Atún en escabeche', 4.83, 4),
(5, 2, 1, 'Jamón Bellota 7kg', 145, 4),
(7, 3, 2, 'Cuerda escalada 10.2 mm x 60 m.', 96.22, 21),
(8, 3, 10, 'Aseguradoras expres escalada', 7.4, 21),
(9, 4, 5, 'Gafas de sol pa chulearse', 67.5, 21),
(11, 4, 4, 'Vaselina pa los labios', 4.3, 9),
(12, 5, 100, 'Profilácticos barriguitas', 0.7, 14),
(13, 5, 2, 'Colonia pachuli', 11, 21),
(14, 7, 2, 'Consolador momentos de soledad', 37.84, 21),
(15, 7, 3, 'Gel de Masaje Estimulante Durex Play 200 ml.', 11.05, 21),
(16, 7, 2, 'Baby-Doll con flecos', 21.99, 21);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `detalle_facturas`
--
ALTER TABLE `detalle_facturas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalle_facturas`
--
ALTER TABLE `detalle_facturas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=545;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
