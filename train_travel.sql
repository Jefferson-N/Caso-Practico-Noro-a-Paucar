-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2024 a las 20:36:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `train_travel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boletos`
--

CREATE TABLE `boletos` (
  `bo_id` int(11) NOT NULL,
  `bo_codigo` varchar(30) DEFAULT NULL,
  `bo_descripcion` varchar(255) DEFAULT NULL,
  `bo_valor` double DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `boletos`
--

INSERT INTO `boletos` (`bo_id`, `bo_codigo`, `bo_descripcion`, `bo_valor`, `id_reserva`) VALUES
(1, 'BOLETO-001-001', 'ruta', 10, NULL),
(2, 'BOLETO-001-02', 'ruta', 10, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cl_id` int(11) NOT NULL,
  `cl_apellido` varchar(30) DEFAULT NULL,
  `cl_cedula` varchar(255) DEFAULT NULL,
  `cl_direccion` varchar(255) DEFAULT NULL,
  `cl_email` varchar(255) DEFAULT NULL,
  `cl_nombre` varchar(30) DEFAULT NULL,
  `cl_rol` varchar(255) DEFAULT NULL,
  `cl_telefono` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cl_id`, `cl_apellido`, `cl_cedula`, `cl_direccion`, `cl_email`, `cl_nombre`, `cl_rol`, `cl_telefono`) VALUES
(1, 'prueba', NULL, NULL, 'prueba@mail.com', 'prueba', 'ADMIN', '098321456'),
(2, 'Noroña', '1724185655', NULL, 'jeffer@mail.com', 'Jefferson ', 'USER', '0983177636');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_viaje`
--

CREATE TABLE `detalle_viaje` (
  `dv_id` int(11) NOT NULL,
  `dv_ruta` varchar(255) DEFAULT NULL,
  `dv_posicion` varchar(255) DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL,
  `id_tren` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_viaje`
--

INSERT INTO `detalle_viaje` (`dv_id`, `dv_ruta`, `dv_posicion`, `id_reserva`, `id_tren`) VALUES
(1, 'RUTA', 'POSICIOON', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `info_calendario`
--

CREATE TABLE `info_calendario` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `horario` int(11) DEFAULT NULL,
  `idcliente` int(11) DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `info_calendario`
--

INSERT INTO `info_calendario` (`id`, `date`, `horario`, `idcliente`, `id_reserva`) VALUES
(1, '2024-03-14', 10, 0, 1),
(2, '2024-03-15', 10, 0, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `me_id` int(11) NOT NULL,
  `me_mensaje` varchar(255) DEFAULT NULL,
  `me_tipo` varchar(255) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`me_id`, `me_mensaje`, `me_tipo`, `id_cliente`) VALUES
(1, 'Mensaje operacion', 'Operaciones', NULL),
(2, 'Mensaje Seguridad', 'Seguridad y Servicio al cliente', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `re_id` int(11) NOT NULL,
  `re_descripcion` varchar(255) DEFAULT NULL,
  `vt_fecha` date DEFAULT NULL,
  `re_codigo` varchar(30) DEFAULT NULL,
  `id_boleto` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`re_id`, `re_descripcion`, `vt_fecha`, `re_codigo`, `id_boleto`, `id_cliente`) VALUES
(1, 'Direccion', '2024-03-14', 'Reserva viaje - Jefferson ', 1, 2),
(2, 'Direccion', '2024-03-14', 'Reserva viaje - Jefferson ', 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tren`
--

CREATE TABLE `tren` (
  `tr_id` int(11) NOT NULL,
  `tr_capacidad` int(11) DEFAULT NULL,
  `tr_codigo` varchar(30) DEFAULT NULL,
  `tr_modelo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tren`
--

INSERT INTO `tren` (`tr_id`, `tr_capacidad`, `tr_codigo`, `tr_modelo`) VALUES
(1, 100, 'ABCD', '2024'),
(2, 2020, 'RTFG-01', 'TRENN-2000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `us_id` int(11) NOT NULL,
  `us_status` varchar(1) DEFAULT NULL,
  `us_pass` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','EMPLEADO','USER') DEFAULT NULL,
  `us_user` varchar(20) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`us_id`, `us_status`, `us_pass`, `role`, `us_user`, `id_cliente`) VALUES
(1, 'V', '$2a$10$KCQZirSrIXC95DY1POYZp.jOV6PoSlCOCZtCkYlIPc7VGEQQ5neOq', 'ADMIN', 'prueba@mail.com', 1),
(2, 'V', '$2a$10$1atEzwe.ajHozR4gIl9O1.yQvCToixa6Elgx7K1A4blVCczQrlADC', 'USER', 'jeffer@mail.com', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `boletos`
--
ALTER TABLE `boletos`
  ADD PRIMARY KEY (`bo_id`),
  ADD KEY `FKmtlekx829ar1fy4e8klmebv2e` (`id_reserva`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cl_id`);

--
-- Indices de la tabla `detalle_viaje`
--
ALTER TABLE `detalle_viaje`
  ADD PRIMARY KEY (`dv_id`),
  ADD KEY `FKoetwvdop8kxb03jc4ismt12no` (`id_reserva`),
  ADD KEY `FKt1asmeas2xhs7kf447gbrvf29` (`id_tren`);

--
-- Indices de la tabla `info_calendario`
--
ALTER TABLE `info_calendario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3ikgqnd1cljf9pymjv4htacui` (`id_reserva`);

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`me_id`),
  ADD KEY `FKbcrr0qx23p0yayo5lyldk2eau` (`id_cliente`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`re_id`),
  ADD KEY `FKcp18vvq9o0fyfgiaqwlk36d0x` (`id_boleto`),
  ADD KEY `FK33h3i3oapwncc68x5n11si6dc` (`id_cliente`);

--
-- Indices de la tabla `tren`
--
ALTER TABLE `tren`
  ADD PRIMARY KEY (`tr_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`us_id`),
  ADD UNIQUE KEY `UK_31n5rmgetc22vo2un5iyf5r7b` (`us_user`),
  ADD KEY `FKhnhbeu7rhg1k2lip1jxomdwhs` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `boletos`
--
ALTER TABLE `boletos`
  MODIFY `bo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cl_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalle_viaje`
--
ALTER TABLE `detalle_viaje`
  MODIFY `dv_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `info_calendario`
--
ALTER TABLE `info_calendario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `me_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `re_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tren`
--
ALTER TABLE `tren`
  MODIFY `tr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `us_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `boletos`
--
ALTER TABLE `boletos`
  ADD CONSTRAINT `FKmtlekx829ar1fy4e8klmebv2e` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`re_id`);

--
-- Filtros para la tabla `detalle_viaje`
--
ALTER TABLE `detalle_viaje`
  ADD CONSTRAINT `FKoetwvdop8kxb03jc4ismt12no` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`re_id`),
  ADD CONSTRAINT `FKt1asmeas2xhs7kf447gbrvf29` FOREIGN KEY (`id_tren`) REFERENCES `tren` (`tr_id`);

--
-- Filtros para la tabla `info_calendario`
--
ALTER TABLE `info_calendario`
  ADD CONSTRAINT `FK3ikgqnd1cljf9pymjv4htacui` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`re_id`);

--
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `FKbcrr0qx23p0yayo5lyldk2eau` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`cl_id`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `FK33h3i3oapwncc68x5n11si6dc` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`cl_id`),
  ADD CONSTRAINT `FKcp18vvq9o0fyfgiaqwlk36d0x` FOREIGN KEY (`id_boleto`) REFERENCES `boletos` (`bo_id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKhnhbeu7rhg1k2lip1jxomdwhs` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`cl_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
