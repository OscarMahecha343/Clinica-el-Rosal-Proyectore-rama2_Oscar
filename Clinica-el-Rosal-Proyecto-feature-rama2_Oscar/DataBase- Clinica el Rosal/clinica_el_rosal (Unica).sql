-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-01-2025 a las 00:02:52
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
-- Base de datos: `cosas por mejorar bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `afiliacion`
--

CREATE TABLE `afiliacion` (
  `id` int(11) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `tipo_identificacion` enum('TI','CC','PASAPORTE','EXTRANJERIA','REGISTRO CIVIL') NOT NULL,
  `identificacion` varchar(20) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `id_municipio` int(11) NOT NULL,
  `tipo_afiliacion` enum('CONTRIBUTIVO','SUBSIDIADO','PARTICULAR') NOT NULL,
  `id_seguro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `afiliacion`
--

INSERT INTO `afiliacion` (`id`, `nombres`, `apellidos`, `tipo_identificacion`, `identificacion`, `fecha_nacimiento`, `telefono`, `correo`, `direccion`, `id_municipio`, `tipo_afiliacion`, `id_seguro`) VALUES
(1, 'brian', 'alfonso', 'TI', '1342352425', '2013-10-09', '34537353', 'brian@gmail.com', 'calle90#56', 107, 'SUBSIDIADO', 1),
(2, 'fernanda', 'rodriguez', 'TI', '1107589326', '2014-06-13', '878352521', 'Fernanda125@gmail.com', 'calle67#56sur', 107, 'CONTRIBUTIVO', 2),
(3, 'laura', 'garcia', 'CC', '878656534', '1998-10-16', '9896465433', 'laura@gmail.com', 'calle90#70', 9, 'PARTICULAR', 3),
(4, 'valentina', 'orduz', 'TI', '5436353736', '2016-10-03', '787534222', 'valentina@gmail.com', 'calle67#80', 107, 'CONTRIBUTIVO', 4),
(5, 'omar', 'montes', 'PASAPORTE', '563572623', '2016-10-18', '9837325242', 'omar@gmail.com', 'calle90#90', 9, 'PARTICULAR', 1),
(6, 'Oscar', 'Jimenez', 'CC', '1102587423', '1995-12-20', '3104780415', 'Oscarjimenez@Hotmail.com', 'Calle28#7e-02', 150, 'SUBSIDIADO', 10),
(7, 'Antonio', 'Sanchez', 'REGISTRO CIVIL', '1102987235', '2024-01-03', '3045987324', 'mamadeAntonio@gmail.com\r\n', 'Calle24#7e-05\r\n', 914, 'CONTRIBUTIVO', 3),
(8, 'Xiomara', 'Sevilla', 'CC', '1103478963', '2000-04-01', '3247859214', 'Xiomi32427@gmail.com', 'Calle47#4e-2', 177, 'PARTICULAR', 7),
(9, 'Cesar', 'Lopez', 'TI', '1104651329', '2008-07-21', '310269754', 'Cesar145@gmail.com', 'Calle26#4z-12\r\n', 24, 'SUBSIDIADO', 6),
(10, 'Felipe', 'Gomez', 'TI', '1102395784', '2010-09-10', '3001782548', 'Felipe@Hotmail.com', 'Calle15#7s-14\r\n', 1097, 'SUBSIDIADO', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agendamiento`
--

CREATE TABLE `agendamiento` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `id_medico` int(11) NOT NULL,
  `id_especialidad` int(11) NOT NULL,
  `sede` varchar(100) NOT NULL,
  `estado` enum('DISPONIBLE','OCUPADO','BLOQUEADO') NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `id_usuario_creador` int(11) NOT NULL,
  `tipo_creador` enum('AUXILIAR','PACIENTE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `agendamiento`
--

INSERT INTO `agendamiento` (`id`, `fecha`, `hora`, `id_paciente`, `id_medico`, `id_especialidad`, `sede`, `estado`, `motivo`, `id_usuario_creador`, `tipo_creador`) VALUES
(1, '2024-06-14', '10:00:00', 1, 1, 1, 'Sede Principal', 'DISPONIBLE', 'NULL', 1, 'PACIENTE'),
(2, '2024-06-14', '17:00:00', 2, 1, 1, 'Sede Principal', 'OCUPADO', 'Capacitacion Medica', 21, 'AUXILIAR'),
(3, '2024-09-10', '15:30:00', 3, 3, 8, 'Sede Principal', 'DISPONIBLE', 'NULL', 3, 'PACIENTE'),
(4, '2024-11-08', '08:50:00', 4, 7, 7, 'Sede Principal', 'DISPONIBLE', 'NULL', 27, 'AUXILIAR'),
(5, '2024-10-24', '11:10:00', 5, 5, 5, 'Sede Principal', 'DISPONIBLE', 'NULL', 24, 'AUXILIAR'),
(6, '2024-10-29', '18:40:00', 6, 2, 2, 'Sede Principal', 'OCUPADO', 'Reunion Interna', 25, 'AUXILIAR'),
(7, '2024-09-19', '08:00:00', 7, 1, 1, 'Sede Principal', 'DISPONIBLE', 'NULL', 7, 'PACIENTE'),
(8, '2024-12-23', '13:40:00', 8, 6, 6, 'Sede Principal', 'DISPONIBLE', 'NULL', 23, 'AUXILIAR'),
(9, '2024-11-22', '13:00:00', 9, 8, 8, 'Sede Principal ', 'BLOQUEADO', 'Mantenimiento del consultorio', 28, 'AUXILIAR'),
(10, '2024-10-16', '07:00:00', 10, 4, 4, 'Sede Principal', 'DISPONIBLE', 'NULL', 22, 'AUXILIAR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auxiliar`
--

CREATE TABLE `auxiliar` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `identificacion` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `direccion` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `auxiliar`
--

INSERT INTO `auxiliar` (`id`, `nombre`, `apellido`, `identificacion`, `telefono`, `correo`, `direccion`) VALUES
(1, 'Juan', 'Pérez', '1234567890', '3001234567', 'juan.perez@hotmail.com', 'Calle 10 #23-45'),
(2, 'María', 'Gómez', '9876543210', '3009876543', 'maria.gomez@gmail.com', 'Carrera 50 #12-78'),
(3, 'Carlos', 'Ramírez', '1122334455', '3011122334', 'carlos.ramirez@gmail.com', 'Avenida 6 #45-67'),
(4, 'Ana', 'López', '5566778899', '3025566778', 'ana.lopez@hotmail.com', 'Calle 15 #89-12'),
(5, 'Pedro', 'Martínez', '6677889900', '3036677889', 'pedro.martinez@gmail.com', 'Carrera 3 #34-56'),
(6, 'Laura', 'Rodríguez', '9988776655', '3049988776', 'laura.rodriguez@hotmail.com', 'Calle 7 #56-89'),
(7, 'Luis', 'Fernández', '1231231231', '3051231231', 'luis.fernandez@gmail.com', 'Avenida 9 #78-12'),
(8, 'Sofía', 'Torres', '2233445566', '3062233445', 'sofia.torres@hotmail.com', 'Calle 2 #22-34'),
(9, 'Jorge', 'Díaz', '3344556677', '3073344556', 'jorge.diaz@gmail.com', 'Carrera 8 #45-23'),
(10, 'Patricia', 'González', '4455667788', '3084455667', 'patricia.gonzalez@gmail.com', 'Calle 11 #67-45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `id` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `id_medico` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Estado` enum('AGENDAR','REPROGRAMAR','CANCELAR') NOT NULL,
  `id_especialidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`id`, `id_paciente`, `id_medico`, `fecha`, `Hora`, `Estado`, `id_especialidad`) VALUES
(1, 1, 1, '2024-06-14', '10:00:00', 'AGENDAR', 1),
(2, 2, 1, '2024-06-14', '17:00:00', 'REPROGRAMAR', 1),
(3, 3, 3, '2024-09-10', '15:30:00', 'AGENDAR', 8),
(4, 4, 7, '2024-11-08', '08:50:00', 'AGENDAR', 7),
(5, 5, 5, '2024-10-24', '11:10:00', 'AGENDAR', 5),
(6, 6, 2, '2024-10-29', '18:40:00', 'REPROGRAMAR', 2),
(7, 7, 1, '2024-09-19', '08:00:00', 'AGENDAR', 1),
(8, 8, 6, '2024-12-23', '13:40:00', 'AGENDAR', 6),
(9, 9, 8, '2024-11-22', '13:00:00', 'CANCELAR', 8),
(10, 10, 4, '2024-10-16', '07:00:00', 'AGENDAR', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consultorio`
--

CREATE TABLE `consultorio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ubicacion` varchar(100) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `telefono` varchar(14) NOT NULL,
  `especialidad` varchar(100) NOT NULL,
  `estado` enum('activo','inactivo','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `consultorio`
--

INSERT INTO `consultorio` (`id`, `nombre`, `ubicacion`, `capacidad`, `telefono`, `especialidad`, `estado`) VALUES
(1, 'Consultorio 9', 'tercer piso', 10, '3125464546', 'pediatria', 'activo'),
(2, 'consultorio 1', 'primer piso', 10, '3135678797', 'medicina interna', 'activo'),
(3, 'consultorio 3', 'primer piso', 10, '3144444444', 'Dietetica y nutricion', 'inactivo'),
(4, 'consultorio 4', 'primer piso', 20, '3125464546', 'fisioterapia', 'activo'),
(5, 'Consultorio 5', 'segundo piso', 5, '3135678797', 'Psicologia', 'activo'),
(6, 'Consultorio 6', 'segundo piso', 16, '2342342324', 'Ortopedia y Traumatologia', 'activo'),
(7, 'Consultorio 10', 'segundo piso', 10, '32123232', 'odontologia', 'activo'),
(8, 'Consultorio 2', 'segundo piso ', 10, '3144343565', 'Medicina General', 'activo'),
(9, 'consultorio 8', 'tercer piso', 10, '3135678797', 'oftalmología', 'activo'),
(10, 'consultorio 7 ', 'tercer piso', 7, '3224545267', 'ginecología', 'activo'),
(11, 'Consultorio 11', 'Primer piso', 50, '3104780395', 'Medicina General', 'activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `id` int(11) NOT NULL,
  `nombre_departamento` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id`, `nombre_departamento`) VALUES
(5, 'ANTIOQUIA'),
(8, 'ATLÁNTICO'),
(11, 'BOGOTÁ, D.C.'),
(13, 'BOLÍVAR'),
(15, 'BOYACÁ'),
(17, 'CALDAS'),
(18, 'CAQUETÁ'),
(19, 'CAUCA'),
(20, 'CESAR'),
(23, 'CÓRDOBA'),
(25, 'CUNDINAMARCA'),
(27, 'CHOCÓ'),
(41, 'HUILA'),
(44, 'LA GUAJIRA'),
(47, 'MAGDALENA'),
(50, 'META'),
(52, 'NARIÑO'),
(54, 'NORTE DE SANTANDER'),
(63, 'QUINDIO'),
(66, 'RISARALDA'),
(68, 'SANTANDER'),
(70, 'SUCRE'),
(73, 'TOLIMA'),
(76, 'VALLE DEL CAUCA'),
(81, 'ARAUCA'),
(85, 'CASANARE'),
(86, 'PUTUMAYO'),
(88, 'ARCHIPIÉLAGO DE SAN ANDRÉS, PROVIDENCIA Y SANTA CATALINA'),
(91, 'AMAZONAS'),
(94, 'GUAINÍA'),
(95, 'GUAVIARE'),
(97, 'VAUPÉS'),
(99, 'VICHADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_examenes`
--

CREATE TABLE `detalle_examenes` (
  `id` int(11) NOT NULL,
  `id_tipo_examen` int(11) NOT NULL,
  `fecha_examen` date NOT NULL,
  `archivo_examen` varchar(255) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `id_auxiliar` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_examenes`
--

INSERT INTO `detalle_examenes` (`id`, `id_tipo_examen`, `fecha_examen`, `archivo_examen`, `id_paciente`, `id_auxiliar`, `created_at`) VALUES
(1, 2, '2024-06-17', 'archivos/No-disponible.pdf', 1, 1, '2025-01-27 16:10:56'),
(2, 11, '2024-06-26', 'archivos/No-disponible.pdf\r\n', 2, 2, '2025-01-27 16:28:45'),
(3, 11, '2024-09-17', 'archivos/No-disponible.pdf\r\n', 3, 3, '2025-01-27 16:41:25'),
(4, 4, '2024-11-15', 'archivos/No-disponible.pdf', 4, 4, '2025-01-27 16:51:38'),
(5, 12, '2024-10-31', 'archivos/No-disponible.pdf\r\n', 5, 5, '2025-01-27 17:04:02'),
(6, 6, '2024-10-31', 'archivos/No-disponible.pdf\r\n', 6, 6, '2025-01-27 17:18:18'),
(7, 2, '2024-09-23', 'archivos/No-disponible.pdf\r\n', 7, 7, '2025-01-27 21:57:10'),
(8, 5, '2024-12-27', 'archivos/No-disponible.pdf\r\n', 8, 8, '2025-01-27 22:18:36'),
(9, 3, '2024-11-29', 'archivos/No-disponible.pdf\r\n', 9, 9, '2025-01-27 22:28:39'),
(10, 10, '2024-10-23', 'archivos/No-disponible.pdf\r\n', 10, 10, '2025-01-27 22:41:28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

CREATE TABLE `especialidad` (
  `id` int(11) NOT NULL,
  `nombre_especialidad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `especialidad`
--

INSERT INTO `especialidad` (`id`, `nombre_especialidad`) VALUES
(1, 'Pediatria'),
(2, 'Medicina Interna'),
(3, 'Dietetica y nutricion'),
(4, 'Fisioterapeuta'),
(5, 'Psicologia'),
(6, 'Ortopedia y Traumatologia'),
(7, 'Odontologia'),
(8, 'Medicina General'),
(9, 'Oftamologia'),
(10, 'Ginecologia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_afiliacion`
--

CREATE TABLE `estado_afiliacion` (
  `id` int(11) NOT NULL,
  `id_afiliacion` int(11) NOT NULL,
  `estado` enum('ACTIVO','INACTIVO','PENDIENTE') NOT NULL,
  `fecha_activacion` date NOT NULL,
  `fecha_certificado` date NOT NULL,
  `observaciones` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_afiliacion`
--

INSERT INTO `estado_afiliacion` (`id`, `id_afiliacion`, `estado`, `fecha_activacion`, `fecha_certificado`, `observaciones`) VALUES
(1, 1, 'ACTIVO', '2024-10-20', '2024-11-15', 'CERTIFICA QUE:\nEl (La) Señor(a) Brian Alfonso identificado(a) con TI 1342352425 se encuentra afiliado(a) a la CLÍNICA en condición de SUBSIDIADO.\n20/10/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 15 días del mes Noviembre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nACTIVO\nA\nCLINICA EL ROSAL\n'),
(2, 2, 'PENDIENTE', '2024-11-05', '2024-12-07', 'CERTIFICA QUE:\nEl (La) Señor(a) Fernanda Rodriguez identificado(a) con TI 1107589326 se encuentra afiliado(a) a la CLÍNICA en condición de CONTRIBUTIVO.\n05/11/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 07 días del mes Diciembre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nPENDIENTE\nP\nCLINICA EL ROSAL\n'),
(3, 3, 'INACTIVO', '2024-09-14', '2024-12-24', 'CERTIFICA QUE:\nEl (La) Señor(a) Laura Garcia identificado(a) con CC 878656534 se encuentra afiliado(a) a la CLÍNICA en condición de PARTICULAR.\n14/09/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 24 días del mes Diciembre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nINACTIVO\nI\nCLINICA EL ROSAL\n'),
(4, 4, 'ACTIVO', '2024-06-17', '2024-09-20', 'CERTIFICA QUE:\nEl (La) Señor(a) Valentina Orduz identificado(a) con TI 5436353736 se encuentra afiliado(a) a la CLÍNICA en condición de CONTRIBUTIVO.\n17/06/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 20 días del mes Septiembre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nACTIVO\nA\nCLINICA EL ROSAL\n'),
(5, 5, 'ACTIVO', '2024-04-20', '2024-07-13', 'CERTIFICA QUE:\nEl (La) Señor(a) Omar Montes identificado(a) con PASAPORTE 563572623 se encuentra afiliado(a) a la CLÍNICA en condición de PARTICULAR.\n20/04/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 13 días del mes Julio del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nACTIVO\nA\nCLINICA EL ROSAL\n'),
(6, 6, 'INACTIVO', '2024-01-28', '2024-11-27', 'CERTIFICA QUE:\nEl (La) Señor(a) Oscar Jimenez identificado(a) con CC 1102587423 se encuentra afiliado(a) a la CLÍNICA en condición de SUBSIDIADO.\n28/01/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 27 días del mes Noviembre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nINACTIVO\nI\nCLINICA EL ROSAL'),
(7, 7, 'ACTIVO', '2024-06-25', '2024-12-28', 'CERTIFICA QUE:\nEl (La) Señor(a) Antonio Sanchez identificado(a) con REGISTRO CIVIL 1102987235 se encuentra afiliado(a) a la CLÍNICA en condición de CONTRIBUTIVO.\n25/06/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 28 días del mes Diciembre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nACTIVO\nA\nCLINICA EL ROSAL'),
(8, 8, 'PENDIENTE', '2024-02-28', '2024-09-19', 'CERTIFICA QUE:\nEl (La) Señor(a) Xiomara Sevilla identificado(a) con CC 1103478963 se encuentra afiliado(a) a la CLÍNICA en condición de PARTICULAR.\n28/02/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 19 días del mes Septiembre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nPENDIENTE\nP\nCLINICA EL ROSAL\n'),
(9, 9, 'ACTIVO', '2024-04-18', '2024-10-15', 'CERTIFICA QUE:\nEl (La) Señor(a) Cesar Lopez identificado(a) con TI 1104651329 se encuentra afiliado(a) a la CLÍNICA en condición de SUBSIDIADO.\n18/04/2024\n\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 15 días del mes Octubre del 2024.\n\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\n\nCordialmente,\nCLÍNICA EL ROSAL\n\nACTIVO\nA\nCLINICA EL ROSAL\n'),
(10, 10, 'INACTIVO', '2024-03-30', '2024-12-14', 'CERTIFICA QUE:\r\nEl (La) Señor(a) Felipe Gomez identificado(a) con TI 1102395784 se encuentra afiliado(a) a la CLÍNICA en condición de SUBSIDIADO.\r\n30/03/2024\r\n\r\nLa presente certificación se expide a solicitud del(los) interesado(s) en QUIEN 14 días del mes Diciembre del 2024.\r\n\r\nEsta certificación tiene validez de un mes con respecto a la fecha de generación.\r\n\r\nCordialmente,\r\nCLÍNICA EL ROSAL\r\n\r\nINACTIVO\r\nI\r\nCLINICA EL ROSAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturacion`
--

CREATE TABLE `facturacion` (
  `id` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `monto` decimal(10,0) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `facturacion`
--

INSERT INTO `facturacion` (`id`, `id_paciente`, `id_servicio`, `monto`, `fecha`) VALUES
(1, 1, 12, 30000, '2024-06-17'),
(2, 2, 21, 60000, '2024-06-26'),
(3, 3, 8, 100000, '2024-09-10'),
(4, 4, 14, 40000, '2024-11-15'),
(5, 5, 5, 80000, '2024-10-24'),
(6, 6, 16, 200000, '2024-10-31'),
(7, 7, 12, 20000, '2024-09-23'),
(8, 8, 6, 70000, '2024-12-23'),
(9, 9, 13, 400000, '2024-11-29'),
(10, 10, 20, 50000, '2024-10-23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `farmaceutico`
--

CREATE TABLE `farmaceutico` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `numero_licencia` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `direccion` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `farmaceutico`
--

INSERT INTO `farmaceutico` (`id`, `nombre`, `apellido`, `numero_licencia`, `telefono`, `correo`, `direccion`) VALUES
(1, 'José', 'Mendoza', 'L12345', '3001234567', 'jose.mendoza@gmail.com', 'Calle 10 #23-45'),
(2, 'Ana', 'García', 'L67890', '3009876543', 'ana.garcia@gmail.com', 'Carrera 50 #12-78'),
(3, 'Luis', 'Pérez', 'L11223', '3011122334', 'luis.perez@hotmail.com', 'Avenida 6 #45-67'),
(4, 'María', 'Rodríguez', 'L44570', '3025566778', 'maria.rodriguez@gmail.com', 'Calle 15 #89-12'),
(5, 'Carlos', 'Martínez', 'L88901', '3036677889', 'carlos.martinez@hotmail.com', 'Carrera 3 #34-56'),
(6, 'Laura', 'Jiménez', 'L11234', '3041122334', 'laura.jimenez@hotmail.com', 'Avenida 7 #56-89'),
(7, 'Pedro', 'López', 'L22345', '3052233445', 'pedro.lopez@gmail.com', 'Calle 9 #67-45'),
(8, 'Bianca', 'Torres', 'L33456', '3063344556', 'Bianca.torres@hotmail.com', 'Calle 8 #45-23'),
(9, 'Jorge', 'Díaz', 'L44567', '3074455667', 'jorge.diaz@gmail.com', 'Carrera 2 #22-34'),
(10, 'Denis', 'Lopez', 'L55678', '3085566778', 'DenisL@hotmail.com', 'Calle 24 #04-99');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historia`
--

CREATE TABLE `historia` (
  `id` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `id_medico` int(11) NOT NULL,
  `fecha_consulta` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `motivo_consulta` text NOT NULL,
  `historial_clinico` text NOT NULL,
  `diagnostico` text NOT NULL,
  `tratamiento` text NOT NULL,
  `alergias` text NOT NULL,
  `antecedentes` text NOT NULL,
  `signos_vitales` text NOT NULL,
  `examenes_solicitado` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historia`
--

INSERT INTO `historia` (`id`, `id_paciente`, `id_medico`, `fecha_consulta`, `motivo_consulta`, `historial_clinico`, `diagnostico`, `tratamiento`, `alergias`, `antecedentes`, `signos_vitales`, `examenes_solicitado`) VALUES
(1, 1, 1, '2024-06-14 15:00:00', 'Fiebre persistente y tos durante los últimos 5 días.', 'N/A', 'Infección respiratoria aguda (IRA).', 'Se enviará medicación para la mejora del paciente y una observación y seguimiento en caso de empeoramiento', 'AINES', 'El Padre Sufre de Asma Y la Madre sana, sin antecedentes relevantes.', 'Frecuencia cardíaca: 100 lpm\r\nFrecuencia respiratoria: 24 rpm\r\nTemperatura: 38.5 °C\r\nPresión arterial: 90/60 mmHg', 'Se le Solicita un Hemograma con el fin de revisar como se encuentra el paciente'),
(2, 2, 1, '2024-06-14 22:00:00', 'La paciente presenta episodios de dolor abdominal en la región periumbilical, que se han intensificado en las últimas semanas.', 'Sin antecedentes de enfermedades crónicas. Vacunas al día.', 'Síndrome de intestino irritable (posible)', 'Se hara envio de tratamiento medico y se le pide a la madre una reduccion de alimentos irritantes (grazas, azucares y alimentos picantes)\r\n\r\nY un control en 1 mes para evaluar la evolucion.', 'N/A', 'Personales: Sin antecedentes quirúrgicos. No alergias conocidas.\r\nFamiliares: Madre con gastritis, padre sano.', 'Frecuencia cardíaca: 85 latidos por minuto\r\nFrecuencia respiratoria: 20 respiraciones por minuto\r\nTemperatura: 36.8 °C\r\nPresión arterial: 100/60 mmHg', 'Se le enviara una Ecografía abdominal Para descartar anomalías estructurales.'),
(3, 3, 3, '2024-09-10 20:30:00', 'La paciente se presenta a consulta por dolor abdominal persistente en la región inferior derecha, que ha aumentado en intensidad en los últimos tres días.', 'Antecedentes personales:\r\n\r\nEnfermedades crónicas: [Reflujo Gastrico]\r\nAlergias: [N/A]\r\nMedicamentos actuales: [Lansoprazol 30mg]\r\n\r\nAntecedentes familiares:\r\nEnfermedades relevantes en familiares: [Ninguna]', 'Posible apendicitis aguda (a confirmar con estudios).', 'Se le envía Reposo, muchas Hidratación y también se le dará Analgésicos para calmar el dolor mientras salen los resultados', 'N/A', 'Enfermedades crónicas: [Reflujo Gástrico]\r\n\r\nAntecedentes familiares:\r\nEnfermedades relevantes en familiares: [Ninguna]', 'Frecuencia cardíaca: 85 latidos por minuto\r\nFrecuencia respiratoria: 20 respiraciones por minuto\r\nTemperatura: 36.8 °C\r\nPresión arterial: 100/60 mmHg', 'Se le Enviara un examen para una Ecografía Abdominal con el fin de identificar como esta.'),
(4, 4, 7, '2024-11-08 13:50:00', 'Paciente refiere dolor dental en el molar superior derecho, especialmente al masticar y al consumir alimentos fríos.', 'N/A', 'Se diagnostica Caries dental en el molar superior derecho.', 'Se realizará una radiografía periapical también una restauración de la caries con material compuesto.\r\n\r\nY unas instrucciones sobre higiene bucal y seguimiento.', 'ALERGICA A LOS ACAROS', 'Sin antecedentes médicos relevantes.', 'Frecuencia cardíaca: 85 latidos por minuto\r\nFrecuencia respiratoria: 20 respiraciones por minuto\r\nTemperatura: 36.8 °C\r\nPresión arterial: 100/60 mmHg', 'Se realizara una Radiografía periapical del molar afectado.'),
(5, 5, 5, '2024-10-24 16:10:00', 'El paciente se presenta por síntomas de ansiedad y depresión, que han interferido con su vida diaria y relaciones interpersonales. Refirió sentirse abrumado por el estrés laboral y problemas familiares.', 'Sin antecedentes psiquiátricos significativos. No ha recibido tratamiento psicológico anteriormente.', 'Se le Diagnostico \r\nF41.1 -> (Trastorno de ansiedad generalizada)\r\nF32.0 -> (Episodio depresivo leve)', 'Se le Recomienda: \r\n* Terapia cognitivo-conductual (TCC) semanal.\r\n* Ejercicios de relajación y mindfulness.', 'N/A', 'Familiares: Madre con antecedentes de depresión.\r\nPersonales: Estrés laboral reciente, separación reciente de pareja.', 'Frecuencia cardíaca: 75 bpm\r\nPresión arterial: 120/80 mmHg\r\nTemperatura: 36.5 °C\r\nFrecuencia respiratoria: 16 rpm', 'Se le Solicita un examen de Evaluación psicológica completa.'),
(6, 6, 2, '2025-01-27 17:21:07', 'Paciente masculino de 29 años presenta dolor torácico intermitente y dificultad para respirar.', 'Antecedentes Médicos:\r\n* Hipertensión arterial controlada con medicamentos todavía.\r\n\r\nAntecedentes Quirúrgicos:\r\n* Hernia Umbilical hace 10 años.\r\n\r\n', 'Posible angina de pecho debido a la presentación de dolor torácico y factores de riesgo cardiovascular.', 'Se le enviara tratamiento médico y también se le pide haga cambios en el estilo de vida (dieta y ejercicio) y \r\nControl regular de glucosa y presión arterial.', 'N/A', 'Antecedentes Quirúrgicos:\r\n* Hernia Umbilical hace 10 años.\r\n\r\nAntecedentes Médicos:\r\n* Hipertensión arterial con medicamentos todavia.\r\n', 'Presión Arterial: 130/85 mmHg\r\nFrecuencia Cardíaca: 78 lpm\r\nFrecuencia Respiratoria: 16 rpm\r\nTemperatura: 36.8 °C\r\nSaturación de Oxígeno: 95%', 'Se le enviara un Electrocardiograma (ECG) para descartar cualquier otro problema'),
(7, 7, 1, '2024-09-19 13:00:00', 'Consulta por fiebre persistente los padres reportan que el bebé ha tenido fiebre de hasta 39°C durante los últimos tres días, acompañado de irritabilidad y disminución del apetito.', 'Edad: 1 año\r\nPeso: 9 kg\r\nTalla: 70 cm\r\nDesarrollo: Desarrollo psicomotor adecuado para la edad.\r\nVacunas: Al día según el esquema nacional de vacunación.\r\nAlergias: No se reportan alergias conocidas.', 'Infección viral (posible infección respiratoria superior).', 'Se recomienda tenerlo bastante hidratado monitorear la fiebre y si llega a ser necesario baños tibios y por ende también se recetará unos medicamentos por si llega a ser necesario', 'N/A', 'Antecedentes familiares: Sin antecedentes relevantes de enfermedades hereditarias.\r\n\r\nAntecedentes personales: Nacido a término, sin complicaciones en el parto. Sin hospitalizaciones previas.', 'Frecuencia cardiaca: 120 latidos por minuto\r\nFrecuencia respiratoria: 30 respiraciones por minuto\r\nTemperatura: 38.5°C\r\n', 'Se le solicita un hemograma para evaluar algun signo de infeccion.'),
(8, 8, 6, '2024-12-23 18:40:00', 'Dolor en la rodilla derecha que ha persistido durante las últimas 3 semanas, especialmente al caminar y subir escaleras.', 'N/A', 'Lesión de ligamento cruzado anterior (LCA) o meniscopatía.', 'Se recomienda reposo y también un antiinflamatorio que se le enviara para que se le alivie un poco el dolor ', 'RINITIS ALERGICA', 'Familiares: Padre con antecedentes de artritis.\r\n\r\nPersonales: Actividad física moderada, corre ocasionalmente.', 'Presión Arterial: 120/80 mmHg\r\nFrecuencia Cardíaca: 72 latidos por minuto\r\nTemperatura: 36.8 °C\r\nFrecuencia Respiratoria: 16 respiraciones por minuto\r\n', 'Se le solicitara una Resonancia magnética (RM) de rodilla'),
(9, 9, 8, '2024-11-22 18:00:00', 'Dolor de cabeza intenso que ha persistido por más de 2 días.', 'Antecedentes Médicos:\r\nSin antecedentes de migrañas o cefaleas previas.\r\nNo alergias conocidas.\r\nSin enfermedades crónicas relevantes.', 'Cefalea Tensional o Migraña (se requiere evaluación adicional para determinar el tipo).', 'se recomienda evitar desencadenantes como estrés, falta de sueño y ciertos alimentos además se le suministrara un medicamento que le podrá servir para aliviar un poco ese dolor de cabeza intenso', 'N/A', 'Personales:\r\nEstilo de vida de manera sedentaria, dieta irregular y Estrés laboral reciente.\r\n\r\nSociales:\r\nNo fumador, consumo ocasional de alcohol.', 'Presión Arterial: 120/80 mmHg\r\nFrecuencia Cardíaca: 75 bpm\r\nFrecuencia Respiratoria: 16 rpm\r\nTemperatura: 36.8 °C', 'se le envia una Tomografía computarizada (TC) para revisar y ver si todo esta bien '),
(10, 10, 4, '2024-10-16 12:00:00', 'Paciente se presenta con dolor persistente en la articulación de la rodilla derecha, que ha aumentado en intensidad en las últimas semanas.', 'N/A', 'Sospecha de tendinitis en el tendón rotuliano derecho.', 'Se le enviara un tratamiento, pero antes que nada debe tener presente que debe tener \r\n1) un Reposo relativo de la extremidad afectada.\r\n\r\n2) Aplicación de hielo en la zona afectada.\r\n\r\n3) se le enviara una Prescripción de antiinflamatorios', 'N/A', 'Sin Antecedentes relevantes', 'Presión Arterial: 120/80 mmHg\r\nFrecuencia Cardíaca: 72 bpm\r\nFrecuencia Respiratoria: 16 rpm\r\nTemperatura: 36.5 °C', 'Se le solicita una Ecografía musculoesquelética de la rodilla derecha para poder dar un díctame.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_medicamentos`
--

CREATE TABLE `inventario_medicamentos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `descripción` text NOT NULL,
  `categoria` varchar(100) NOT NULL,
  `unidad_medida` varchar(20) NOT NULL,
  `precio_unitario` decimal(10,0) NOT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `proveedor` varchar(100) NOT NULL,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` enum('DISPONIBLE','AGOTADO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario_medicamentos`
--

INSERT INTO `inventario_medicamentos` (`id`, `nombre`, `Cantidad`, `descripción`, `categoria`, `unidad_medida`, `precio_unitario`, `fecha_vencimiento`, `proveedor`, `fecha_actualizacion`, `estado`) VALUES
(1, 'Cetirizina 10mg', 100, 'Antihistamínico para alergias', 'Antihistamínicos', 'Tabletas', 5, '2026-05-20', 'Laboratorios XYZ', '2024-08-28 19:30:00', 'DISPONIBLE'),
(2, 'Acetaminofén 500mg', 500, 'Analgésico y antipirético', 'Analgésicos', 'Tabletas', 4, '2028-12-15', 'Farmacéutica ABC', '2024-01-28 19:30:00', 'DISPONIBLE'),
(3, 'Liposomal Vitamin C', 1000, 'Vitamina C liposomal de alta absorción', 'Suplementos', 'Cápsulas', 10, '2026-01-10', 'Laboratorios Naturales', '2025-01-28 19:30:00', 'AGOTADO'),
(4, 'Systane Gel Drops Gotas Oftálmicas Lubricantes', 50, 'Lubricante ocular para ojos secos', 'Oftalmología', 'Gotas', 15, '2028-08-30', 'VisionCare Ltd.', '2025-01-28 19:45:04', 'DISPONIBLE'),
(5, 'Naproxeno 250mg', 300, 'Antiinflamatorio no esteroideo', 'Antiinflamatorios', 'Tabletas', 4, '2026-11-01', 'PharmaCorp', '2025-01-28 19:45:33', 'DISPONIBLE'),
(6, 'Diclofenaco 75mg', 400, 'Analgésico antiinflamatorio', 'Antiinflamatorios', 'Tabletas', 5, '2025-07-20', 'Farmacéutica ABC', '2025-01-28 19:37:14', 'AGOTADO'),
(7, 'Losartán y Metoprolol 50mg', 800, 'Combinación para hipertensión arterial', 'Antihipertensivos', 'Tabletas', 7, '2030-10-10', 'CardioLab', '2025-01-28 19:45:51', 'DISPONIBLE'),
(8, 'Ibuprofeno 400mg', 90, 'Antiinflamatorio y analgésico', 'Antiinflamatorios', 'Tabletas', 3, '2027-06-30', 'Laboratorios XYZ', '2025-01-28 19:30:00', 'DISPONIBLE'),
(9, 'Active Burner', 14, 'Suplemento para el control de peso', 'Suplementos', 'Cápsulas', 20, '2025-09-15', 'NaturLife', '2025-01-28 19:37:26', 'AGOTADO'),
(10, 'Clorfeniramina solución oral 2MG/5 ML', 600, 'Antihistamínico en solución oral', 'Antihistamínicos', 'Líquido', 3, '2026-08-01', 'Laboratorios XYZ', '2025-01-28 19:46:43', 'DISPONIBLE'),
(11, 'Alprazolam (Xanax)', 200, 'Ansiolítico para trastornos de ansiedad', 'Ansiolíticos', 'Tabletas', 12, '2029-11-30', 'PharmaCorp', '2025-01-28 19:46:55', 'DISPONIBLE'),
(12, 'Aspirina', 300, 'Analgésico y antipirético', 'Analgésicos', 'Tabletas', 2, '2026-01-15', 'Farmacéutica ABC', '2025-01-28 19:30:00', 'DISPONIBLE'),
(13, 'Dolex Niño', 90, 'Suspensión pediátrica para fiebre y dolor', 'Antipiréticos', 'Líquido', 5, '2025-12-01', 'Laboratorios XYZ', '2025-01-28 19:30:00', 'DISPONIBLE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `licencia_medica` varchar(100) NOT NULL,
  `id_especialidad` int(11) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `id_consultorio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`id`, `nombre`, `apellidos`, `telefono`, `licencia_medica`, `id_especialidad`, `correo`, `direccion`, `id_consultorio`) VALUES
(1, 'brayan', 'parra', '111222333', '1153665363', 1, 'brayan@outlook.com', 'calle90#09', 1),
(2, 'lina', 'lopez', '111111111', '1213121321', 2, 'lina123@gmail.com', 'calle 67#6575', 2),
(3, 'mailin', 'serrano', '3145656451', '1902102910', 8, 'mailin123@gmail.com', 'calle65#676', 8),
(4, 'alejandro', 'alfonso', '32123232', '1234345344', 4, 'alendro@gmail.com', 'calle45#87este', 4),
(5, 'allison', 'perez', '3135678793', '121232145', 5, 'allison@gmail.com', 'calle56#65', 5),
(6, 'leon', 'panche', '321232454', '1004565678', 6, 'leon@gmail.com', 'calle45#87sur', 6),
(7, 'jhon', 'tovar', '3135467565', '999999999', 7, 'jhon@gmail.com', 'calle45#78', 7),
(8, 'stiven', 'leon', '321235467', '1234567891', 8, 'stiven@gmail.com', 'calle45#99', 11),
(9, 'camilo', 'uribe', '123432154654', '454567678', 9, 'camilo@gmail.com', 'calle65#676', 9),
(10, 'luz', 'lopez', '34565789', '4356565676', 10, 'luz@gmail.com', 'calle59sur#78b-17', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipio`
--

CREATE TABLE `municipio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `municipio`
--

INSERT INTO `municipio` (`id`, `nombre`, `id_departamento`, `estado`) VALUES
(1, 'Abriaquí', 5, 0),
(2, 'Acacías', 50, 0),
(3, 'Acandí', 27, 0),
(4, 'Acevedo', 41, 0),
(5, 'Achí', 13, 0),
(6, 'Agrado', 41, 0),
(7, 'Agua de Dios', 25, 0),
(8, 'Aguachica', 20, 0),
(9, 'Aguada', 68, 0),
(10, 'Aguadas', 17, 0),
(11, 'Aguazul', 85, 0),
(12, 'Agustín Codazzi', 20, 0),
(13, 'Aipe', 41, 0),
(14, 'Albania', 18, 0),
(15, 'Albania', 44, 0),
(16, 'Albania', 68, 0),
(17, 'Albán', 25, 0),
(18, 'Albán (San José)', 52, 0),
(19, 'Alcalá', 76, 0),
(20, 'Alejandria', 5, 0),
(21, 'Algarrobo', 47, 0),
(22, 'Algeciras', 41, 0),
(23, 'Almaguer', 19, 0),
(24, 'Almeida', 15, 0),
(25, 'Alpujarra', 73, 0),
(26, 'Altamira', 41, 0),
(27, 'Alto Baudó (Pie de Pato)', 27, 0),
(28, 'Altos del Rosario', 13, 0),
(29, 'Alvarado', 73, 0),
(30, 'Amagá', 5, 0),
(31, 'Amalfi', 5, 0),
(32, 'Ambalema', 73, 0),
(33, 'Anapoima', 25, 0),
(34, 'Ancuya', 52, 0),
(35, 'Andalucía', 76, 0),
(36, 'Andes', 5, 0),
(37, 'Angelópolis', 5, 0),
(38, 'Angostura', 5, 0),
(39, 'Anolaima', 25, 0),
(40, 'Anorí', 5, 0),
(41, 'Anserma', 17, 0),
(42, 'Ansermanuevo', 76, 0),
(43, 'Anzoátegui', 73, 0),
(44, 'Anzá', 5, 0),
(45, 'Apartadó', 5, 0),
(46, 'Apulo', 25, 0),
(47, 'Apía', 66, 0),
(48, 'Aquitania', 15, 0),
(49, 'Aracataca', 47, 0),
(50, 'Aranzazu', 17, 0),
(51, 'Aratoca', 68, 0),
(52, 'Arauca', 81, 0),
(53, 'Arauquita', 81, 0),
(54, 'Arbeláez', 25, 0),
(55, 'Arboleda (Berruecos)', 52, 0),
(56, 'Arboledas', 54, 0),
(57, 'Arboletes', 5, 0),
(58, 'Arcabuco', 15, 0),
(59, 'Arenal', 13, 0),
(60, 'Argelia', 5, 0),
(61, 'Argelia', 19, 0),
(62, 'Argelia', 76, 0),
(63, 'Ariguaní (El Difícil)', 47, 0),
(64, 'Arjona', 13, 0),
(65, 'Armenia', 5, 0),
(66, 'Armenia', 63, 0),
(67, 'Armero (Guayabal)', 73, 0),
(68, 'Arroyohondo', 13, 0),
(69, 'Astrea', 20, 0),
(70, 'Ataco', 73, 0),
(71, 'Atrato (Yuto)', 27, 0),
(72, 'Ayapel', 23, 0),
(73, 'Bagadó', 27, 0),
(74, 'Bahía Solano (Mútis)', 27, 0),
(75, 'Bajo Baudó (Pizarro)', 27, 0),
(76, 'Balboa', 19, 0),
(77, 'Balboa', 66, 0),
(78, 'Baranoa', 8, 0),
(79, 'Baraya', 41, 0),
(80, 'Barbacoas', 52, 0),
(81, 'Barbosa', 5, 0),
(82, 'Barbosa', 68, 0),
(83, 'Barichara', 68, 0),
(84, 'Barranca de Upía', 50, 0),
(85, 'Barrancabermeja', 68, 0),
(86, 'Barrancas', 44, 0),
(87, 'Barranco de Loba', 13, 0),
(88, 'Barranquilla', 8, 0),
(89, 'Becerríl', 20, 0),
(90, 'Belalcázar', 17, 0),
(91, 'Bello', 5, 0),
(92, 'Belmira', 5, 0),
(93, 'Beltrán', 25, 0),
(94, 'Belén', 15, 0),
(95, 'Belén', 52, 0),
(96, 'Belén de Bajirá', 27, 0),
(97, 'Belén de Umbría', 66, 0),
(98, 'Belén de los Andaquíes', 18, 0),
(99, 'Berbeo', 15, 0),
(100, 'Betania', 5, 0),
(101, 'Beteitiva', 15, 0),
(102, 'Betulia', 5, 0),
(103, 'Betulia', 68, 0),
(104, 'Bituima', 25, 0),
(105, 'Boavita', 15, 0),
(106, 'Bochalema', 54, 0),
(107, 'Bogotá D.C.', 11, 0),
(108, 'Bojacá', 25, 0),
(109, 'Bojayá (Bellavista)', 27, 0),
(110, 'Bolívar', 5, 0),
(111, 'Bolívar', 19, 0),
(112, 'Bolívar', 68, 0),
(113, 'Bolívar', 76, 0),
(114, 'Bosconia', 20, 0),
(115, 'Boyacá', 15, 0),
(116, 'Briceño', 5, 0),
(117, 'Briceño', 15, 0),
(118, 'Bucaramanga', 68, 0),
(119, 'Bucarasica', 54, 0),
(120, 'Buenaventura', 76, 0),
(121, 'Buenavista', 15, 0),
(122, 'Buenavista', 23, 0),
(123, 'Buenavista', 63, 0),
(124, 'Buenavista', 70, 0),
(125, 'Buenos Aires', 19, 0),
(126, 'Buesaco', 52, 0),
(127, 'Buga', 76, 0),
(128, 'Bugalagrande', 76, 0),
(129, 'Burítica', 5, 0),
(130, 'Busbanza', 15, 0),
(131, 'Cabrera', 25, 0),
(132, 'Cabrera', 68, 0),
(133, 'Cabuyaro', 50, 0),
(134, 'Cachipay', 25, 0),
(135, 'Caicedo', 5, 0),
(136, 'Caicedonia', 76, 0),
(137, 'Caimito', 70, 0),
(138, 'Cajamarca', 73, 0),
(139, 'Cajibío', 19, 0),
(140, 'Cajicá', 25, 0),
(141, 'Calamar', 13, 0),
(142, 'Calamar', 95, 0),
(143, 'Calarcá', 63, 0),
(144, 'Caldas', 5, 0),
(145, 'Caldas', 15, 0),
(146, 'Caldono', 19, 0),
(147, 'California', 68, 0),
(148, 'Calima (Darién)', 76, 0),
(149, 'Caloto', 19, 0),
(150, 'Calí', 76, 0),
(151, 'Campamento', 5, 0),
(152, 'Campo de la Cruz', 8, 0),
(153, 'Campoalegre', 41, 0),
(154, 'Campohermoso', 15, 0),
(155, 'Canalete', 23, 0),
(156, 'Candelaria', 8, 0),
(157, 'Candelaria', 76, 0),
(158, 'Cantagallo', 13, 0),
(159, 'Cantón de San Pablo', 27, 0),
(160, 'Caparrapí', 25, 0),
(161, 'Capitanejo', 68, 0),
(162, 'Caracolí', 5, 0),
(163, 'Caramanta', 5, 0),
(164, 'Carcasí', 68, 0),
(165, 'Carepa', 5, 0),
(166, 'Carmen de Apicalá', 73, 0),
(167, 'Carmen de Carupa', 25, 0),
(168, 'Carmen de Viboral', 5, 0),
(169, 'Carmen del Darién (CURBARADÓ)', 27, 0),
(170, 'Carolina', 5, 0),
(171, 'Cartagena', 13, 0),
(172, 'Cartagena del Chairá', 18, 0),
(173, 'Cartago', 76, 0),
(174, 'Carurú', 97, 0),
(175, 'Casabianca', 73, 0),
(176, 'Castilla la Nueva', 50, 0),
(177, 'Caucasia', 5, 0),
(178, 'Cañasgordas', 5, 0),
(179, 'Cepita', 68, 0),
(180, 'Cereté', 23, 0),
(181, 'Cerinza', 15, 0),
(182, 'Cerrito', 68, 0),
(183, 'Cerro San Antonio', 47, 0),
(184, 'Chachaguí', 52, 0),
(185, 'Chaguaní', 25, 0),
(186, 'Chalán', 70, 0),
(187, 'Chaparral', 73, 0),
(188, 'Charalá', 68, 0),
(189, 'Charta', 68, 0),
(190, 'Chigorodó', 5, 0),
(191, 'Chima', 68, 0),
(192, 'Chimichagua', 20, 0),
(193, 'Chimá', 23, 0),
(194, 'Chinavita', 15, 0),
(195, 'Chinchiná', 17, 0),
(196, 'Chinácota', 54, 0),
(197, 'Chinú', 23, 0),
(198, 'Chipaque', 25, 0),
(199, 'Chipatá', 68, 0),
(200, 'Chiquinquirá', 15, 0),
(201, 'Chiriguaná', 20, 0),
(202, 'Chiscas', 15, 0),
(203, 'Chita', 15, 0),
(204, 'Chitagá', 54, 0),
(205, 'Chitaraque', 15, 0),
(206, 'Chivatá', 15, 0),
(207, 'Chivolo', 47, 0),
(208, 'Choachí', 25, 0),
(209, 'Chocontá', 25, 0),
(210, 'Chámeza', 85, 0),
(211, 'Chía', 25, 0),
(212, 'Chíquiza', 15, 0),
(213, 'Chívor', 15, 0),
(214, 'Cicuco', 13, 0),
(215, 'Cimitarra', 68, 0),
(216, 'Circasia', 63, 0),
(217, 'Cisneros', 5, 0),
(218, 'Ciénaga', 15, 0),
(219, 'Ciénaga', 47, 0),
(220, 'Ciénaga de Oro', 23, 0),
(221, 'Clemencia', 13, 0),
(222, 'Cocorná', 5, 0),
(223, 'Coello', 73, 0),
(224, 'Cogua', 25, 0),
(225, 'Colombia', 41, 0),
(226, 'Colosó (Ricaurte)', 70, 0),
(227, 'Colón', 86, 0),
(228, 'Colón (Génova)', 52, 0),
(229, 'Concepción', 5, 0),
(230, 'Concepción', 68, 0),
(231, 'Concordia', 5, 0),
(232, 'Concordia', 47, 0),
(233, 'Condoto', 27, 0),
(234, 'Confines', 68, 0),
(235, 'Consaca', 52, 0),
(236, 'Contadero', 52, 0),
(237, 'Contratación', 68, 0),
(238, 'Convención', 54, 0),
(239, 'Copacabana', 5, 0),
(240, 'Coper', 15, 0),
(241, 'Cordobá', 63, 0),
(242, 'Corinto', 19, 0),
(243, 'Coromoro', 68, 0),
(244, 'Corozal', 70, 0),
(245, 'Corrales', 15, 0),
(246, 'Cota', 25, 0),
(247, 'Cotorra', 23, 0),
(248, 'Covarachía', 15, 0),
(249, 'Coveñas', 70, 0),
(250, 'Coyaima', 73, 0),
(251, 'Cravo Norte', 81, 0),
(252, 'Cuaspud (Carlosama)', 52, 0),
(253, 'Cubarral', 50, 0),
(254, 'Cubará', 15, 0),
(255, 'Cucaita', 15, 0),
(256, 'Cucunubá', 25, 0),
(257, 'Cucutilla', 54, 0),
(258, 'Cuitiva', 15, 0),
(259, 'Cumaral', 50, 0),
(260, 'Cumaribo', 99, 0),
(261, 'Cumbal', 52, 0),
(262, 'Cumbitara', 52, 0),
(263, 'Cunday', 73, 0),
(264, 'Curillo', 18, 0),
(265, 'Curití', 68, 0),
(266, 'Curumaní', 20, 0),
(267, 'Cáceres', 5, 0),
(268, 'Cáchira', 54, 0),
(269, 'Cácota', 54, 0),
(270, 'Cáqueza', 25, 0),
(271, 'Cértegui', 27, 0),
(272, 'Cómbita', 15, 0),
(273, 'Córdoba', 13, 0),
(274, 'Córdoba', 52, 0),
(275, 'Cúcuta', 54, 0),
(276, 'Dabeiba', 5, 0),
(277, 'Dagua', 76, 0),
(278, 'Dibulla', 44, 0),
(279, 'Distracción', 44, 0),
(280, 'Dolores', 73, 0),
(281, 'Don Matías', 5, 0),
(282, 'Dos Quebradas', 66, 0),
(283, 'Duitama', 15, 0),
(284, 'Durania', 54, 0),
(285, 'Ebéjico', 5, 0),
(286, 'El Bagre', 5, 0),
(287, 'El Banco', 47, 0),
(288, 'El Cairo', 76, 0),
(289, 'El Calvario', 50, 0),
(290, 'El Carmen', 54, 0),
(291, 'El Carmen', 68, 0),
(292, 'El Carmen de Atrato', 27, 0),
(293, 'El Carmen de Bolívar', 13, 0),
(294, 'El Castillo', 50, 0),
(295, 'El Cerrito', 76, 0),
(296, 'El Charco', 52, 0),
(297, 'El Cocuy', 15, 0),
(298, 'El Colegio', 25, 0),
(299, 'El Copey', 20, 0),
(300, 'El Doncello', 18, 0),
(301, 'El Dorado', 50, 0),
(302, 'El Dovio', 76, 0),
(303, 'El Espino', 15, 0),
(304, 'El Guacamayo', 68, 0),
(305, 'El Guamo', 13, 0),
(306, 'El Molino', 44, 0),
(307, 'El Paso', 20, 0),
(308, 'El Paujil', 18, 0),
(309, 'El Peñol', 52, 0),
(310, 'El Peñon', 13, 0),
(311, 'El Peñon', 68, 0),
(312, 'El Peñón', 25, 0),
(313, 'El Piñon', 47, 0),
(314, 'El Playón', 68, 0),
(315, 'El Retorno', 95, 0),
(316, 'El Retén', 47, 0),
(317, 'El Roble', 70, 0),
(318, 'El Rosal', 25, 0),
(319, 'El Rosario', 52, 0),
(320, 'El Tablón de Gómez', 52, 0),
(321, 'El Tambo', 19, 0),
(322, 'El Tambo', 52, 0),
(323, 'El Tarra', 54, 0),
(324, 'El Zulia', 54, 0),
(325, 'El Águila', 76, 0),
(326, 'Elías', 41, 0),
(327, 'Encino', 68, 0),
(328, 'Enciso', 68, 0),
(329, 'Entrerríos', 5, 0),
(330, 'Envigado', 5, 0),
(331, 'Espinal', 73, 0),
(332, 'Facatativá', 25, 0),
(333, 'Falan', 73, 0),
(334, 'Filadelfia', 17, 0),
(335, 'Filandia', 63, 0),
(336, 'Firavitoba', 15, 0),
(337, 'Flandes', 73, 0),
(338, 'Florencia', 18, 0),
(339, 'Florencia', 19, 0),
(340, 'Floresta', 15, 0),
(341, 'Florida', 76, 0),
(342, 'Floridablanca', 68, 0),
(343, 'Florián', 68, 0),
(344, 'Fonseca', 44, 0),
(345, 'Fortúl', 81, 0),
(346, 'Fosca', 25, 0),
(347, 'Francisco Pizarro', 52, 0),
(348, 'Fredonia', 5, 0),
(349, 'Fresno', 73, 0),
(350, 'Frontino', 5, 0),
(351, 'Fuente de Oro', 50, 0),
(352, 'Fundación', 47, 0),
(353, 'Funes', 52, 0),
(354, 'Funza', 25, 0),
(355, 'Fusagasugá', 25, 0),
(356, 'Fómeque', 25, 0),
(357, 'Fúquene', 25, 0),
(358, 'Gachalá', 25, 0),
(359, 'Gachancipá', 25, 0),
(360, 'Gachantivá', 15, 0),
(361, 'Gachetá', 25, 0),
(362, 'Galapa', 8, 0),
(363, 'Galeras (Nueva Granada)', 70, 0),
(364, 'Galán', 68, 0),
(365, 'Gama', 25, 0),
(366, 'Gamarra', 20, 0),
(367, 'Garagoa', 15, 0),
(368, 'Garzón', 41, 0),
(369, 'Gigante', 41, 0),
(370, 'Ginebra', 76, 0),
(371, 'Giraldo', 5, 0),
(372, 'Girardot', 25, 0),
(373, 'Girardota', 5, 0),
(374, 'Girón', 68, 0),
(375, 'Gonzalez', 20, 0),
(376, 'Gramalote', 54, 0),
(377, 'Granada', 5, 0),
(378, 'Granada', 25, 0),
(379, 'Granada', 50, 0),
(380, 'Guaca', 68, 0),
(381, 'Guacamayas', 15, 0),
(382, 'Guacarí', 76, 0),
(383, 'Guachavés', 52, 0),
(384, 'Guachené', 19, 0),
(385, 'Guachetá', 25, 0),
(386, 'Guachucal', 52, 0),
(387, 'Guadalupe', 5, 0),
(388, 'Guadalupe', 41, 0),
(389, 'Guadalupe', 68, 0),
(390, 'Guaduas', 25, 0),
(391, 'Guaitarilla', 52, 0),
(392, 'Gualmatán', 52, 0),
(393, 'Guamal', 47, 0),
(394, 'Guamal', 50, 0),
(395, 'Guamo', 73, 0),
(396, 'Guapota', 68, 0),
(397, 'Guapí', 19, 0),
(398, 'Guaranda', 70, 0),
(399, 'Guarne', 5, 0),
(400, 'Guasca', 25, 0),
(401, 'Guatapé', 5, 0),
(402, 'Guataquí', 25, 0),
(403, 'Guatavita', 25, 0),
(404, 'Guateque', 15, 0),
(405, 'Guavatá', 68, 0),
(406, 'Guayabal de Siquima', 25, 0),
(407, 'Guayabetal', 25, 0),
(408, 'Guayatá', 15, 0),
(409, 'Guepsa', 68, 0),
(410, 'Guicán', 15, 0),
(411, 'Gutiérrez', 25, 0),
(412, 'Guática', 66, 0),
(413, 'Gámbita', 68, 0),
(414, 'Gámeza', 15, 0),
(415, 'Génova', 63, 0),
(416, 'Gómez Plata', 5, 0),
(417, 'Hacarí', 54, 0),
(418, 'Hatillo de Loba', 13, 0),
(419, 'Hato', 68, 0),
(420, 'Hato Corozal', 85, 0),
(421, 'Hatonuevo', 44, 0),
(422, 'Heliconia', 5, 0),
(423, 'Herrán', 54, 0),
(424, 'Herveo', 73, 0),
(425, 'Hispania', 5, 0),
(426, 'Hobo', 41, 0),
(427, 'Honda', 73, 0),
(428, 'Ibagué', 73, 0),
(429, 'Icononzo', 73, 0),
(430, 'Iles', 52, 0),
(431, 'Imúes', 52, 0),
(432, 'Inzá', 19, 0),
(433, 'Inírida', 94, 0),
(434, 'Ipiales', 52, 0),
(435, 'Isnos', 41, 0),
(436, 'Istmina', 27, 0),
(437, 'Itagüí', 5, 0),
(438, 'Ituango', 5, 0),
(439, 'Izá', 15, 0),
(440, 'Jambaló', 19, 0),
(441, 'Jamundí', 76, 0),
(442, 'Jardín', 5, 0),
(443, 'Jenesano', 15, 0),
(444, 'Jericó', 5, 0),
(445, 'Jericó', 15, 0),
(446, 'Jerusalén', 25, 0),
(447, 'Jesús María', 68, 0),
(448, 'Jordán', 68, 0),
(449, 'Juan de Acosta', 8, 0),
(450, 'Junín', 25, 0),
(451, 'Juradó', 27, 0),
(452, 'La Apartada y La Frontera', 23, 0),
(453, 'La Argentina', 41, 0),
(454, 'La Belleza', 68, 0),
(455, 'La Calera', 25, 0),
(456, 'La Capilla', 15, 0),
(457, 'La Ceja', 5, 0),
(458, 'La Celia', 66, 0),
(459, 'La Cruz', 52, 0),
(460, 'La Cumbre', 76, 0),
(461, 'La Dorada', 17, 0),
(462, 'La Esperanza', 54, 0),
(463, 'La Estrella', 5, 0),
(464, 'La Florida', 52, 0),
(465, 'La Gloria', 20, 0),
(466, 'La Jagua de Ibirico', 20, 0),
(467, 'La Jagua del Pilar', 44, 0),
(468, 'La Llanada', 52, 0),
(469, 'La Macarena', 50, 0),
(470, 'La Merced', 17, 0),
(471, 'La Mesa', 25, 0),
(472, 'La Montañita', 18, 0),
(473, 'La Palma', 25, 0),
(474, 'La Paz', 68, 0),
(475, 'La Paz (Robles)', 20, 0),
(476, 'La Peña', 25, 0),
(477, 'La Pintada', 5, 0),
(478, 'La Plata', 41, 0),
(479, 'La Playa', 54, 0),
(480, 'La Primavera', 99, 0),
(481, 'La Salina', 85, 0),
(482, 'La Sierra', 19, 0),
(483, 'La Tebaida', 63, 0),
(484, 'La Tola', 52, 0),
(485, 'La Unión', 5, 0),
(486, 'La Unión', 52, 0),
(487, 'La Unión', 70, 0),
(488, 'La Unión', 76, 0),
(489, 'La Uvita', 15, 0),
(490, 'La Vega', 19, 0),
(491, 'La Vega', 25, 0),
(492, 'La Victoria', 15, 0),
(493, 'La Victoria', 17, 0),
(494, 'La Victoria', 76, 0),
(495, 'La Virginia', 66, 0),
(496, 'Labateca', 54, 0),
(497, 'Labranzagrande', 15, 0),
(498, 'Landázuri', 68, 0),
(499, 'Lebrija', 68, 0),
(500, 'Leiva', 52, 0),
(501, 'Lejanías', 50, 0),
(502, 'Lenguazaque', 25, 0),
(503, 'Leticia', 91, 0),
(504, 'Liborina', 5, 0),
(505, 'Linares', 52, 0),
(506, 'Lloró', 27, 0),
(507, 'Lorica', 23, 0),
(508, 'Los Córdobas', 23, 0),
(509, 'Los Palmitos', 70, 0),
(510, 'Los Patios', 54, 0),
(511, 'Los Santos', 68, 0),
(512, 'Lourdes', 54, 0),
(513, 'Luruaco', 8, 0),
(514, 'Lérida', 73, 0),
(515, 'Líbano', 73, 0),
(516, 'López (Micay)', 19, 0),
(517, 'Macanal', 15, 0),
(518, 'Macaravita', 68, 0),
(519, 'Maceo', 5, 0),
(520, 'Machetá', 25, 0),
(521, 'Madrid', 25, 0),
(522, 'Magangué', 13, 0),
(523, 'Magüi (Payán)', 52, 0),
(524, 'Mahates', 13, 0),
(525, 'Maicao', 44, 0),
(526, 'Majagual', 70, 0),
(527, 'Malambo', 8, 0),
(528, 'Mallama (Piedrancha)', 52, 0),
(529, 'Manatí', 8, 0),
(530, 'Manaure', 44, 0),
(531, 'Manaure Balcón del Cesar', 20, 0),
(532, 'Manizales', 17, 0),
(533, 'Manta', 25, 0),
(534, 'Manzanares', 17, 0),
(535, 'Maní', 85, 0),
(536, 'Mapiripan', 50, 0),
(537, 'Margarita', 13, 0),
(538, 'Marinilla', 5, 0),
(539, 'Maripí', 15, 0),
(540, 'Mariquita', 73, 0),
(541, 'Marmato', 17, 0),
(542, 'Marquetalia', 17, 0),
(543, 'Marsella', 66, 0),
(544, 'Marulanda', 17, 0),
(545, 'María la Baja', 13, 0),
(546, 'Matanza', 68, 0),
(547, 'Medellín', 5, 0),
(548, 'Medina', 25, 0),
(549, 'Medio Atrato', 27, 0),
(550, 'Medio Baudó', 27, 0),
(551, 'Medio San Juan (ANDAGOYA)', 27, 0),
(552, 'Melgar', 73, 0),
(553, 'Mercaderes', 19, 0),
(554, 'Mesetas', 50, 0),
(555, 'Milán', 18, 0),
(556, 'Miraflores', 15, 0),
(557, 'Miraflores', 95, 0),
(558, 'Miranda', 19, 0),
(559, 'Mistrató', 66, 0),
(560, 'Mitú', 97, 0),
(561, 'Mocoa', 86, 0),
(562, 'Mogotes', 68, 0),
(563, 'Molagavita', 68, 0),
(564, 'Momil', 23, 0),
(565, 'Mompós', 13, 0),
(566, 'Mongua', 15, 0),
(567, 'Monguí', 15, 0),
(568, 'Moniquirá', 15, 0),
(569, 'Montebello', 5, 0),
(570, 'Montecristo', 13, 0),
(571, 'Montelíbano', 23, 0),
(572, 'Montenegro', 63, 0),
(573, 'Monteria', 23, 0),
(574, 'Monterrey', 85, 0),
(575, 'Morales', 13, 0),
(576, 'Morales', 19, 0),
(577, 'Morelia', 18, 0),
(578, 'Morroa', 70, 0),
(579, 'Mosquera', 25, 0),
(580, 'Mosquera', 52, 0),
(581, 'Motavita', 15, 0),
(582, 'Moñitos', 23, 0),
(583, 'Murillo', 73, 0),
(584, 'Murindó', 5, 0),
(585, 'Mutatá', 5, 0),
(586, 'Mutiscua', 54, 0),
(587, 'Muzo', 15, 0),
(588, 'Málaga', 68, 0),
(589, 'Nariño', 5, 0),
(590, 'Nariño', 25, 0),
(591, 'Nariño', 52, 0),
(592, 'Natagaima', 73, 0),
(593, 'Nechí', 5, 0),
(594, 'Necoclí', 5, 0),
(595, 'Neira', 17, 0),
(596, 'Neiva', 41, 0),
(597, 'Nemocón', 25, 0),
(598, 'Nilo', 25, 0),
(599, 'Nimaima', 25, 0),
(600, 'Nobsa', 15, 0),
(601, 'Nocaima', 25, 0),
(602, 'Norcasia', 17, 0),
(603, 'Norosí', 13, 0),
(604, 'Novita', 27, 0),
(605, 'Nueva Granada', 47, 0),
(606, 'Nuevo Colón', 15, 0),
(607, 'Nunchía', 85, 0),
(608, 'Nuquí', 27, 0),
(609, 'Nátaga', 41, 0),
(610, 'Obando', 76, 0),
(611, 'Ocamonte', 68, 0),
(612, 'Ocaña', 54, 0),
(613, 'Oiba', 68, 0),
(614, 'Oicatá', 15, 0),
(615, 'Olaya', 5, 0),
(616, 'Olaya Herrera', 52, 0),
(617, 'Onzaga', 68, 0),
(618, 'Oporapa', 41, 0),
(619, 'Orito', 86, 0),
(620, 'Orocué', 85, 0),
(621, 'Ortega', 73, 0),
(622, 'Ospina', 52, 0),
(623, 'Otanche', 15, 0),
(624, 'Ovejas', 70, 0),
(625, 'Pachavita', 15, 0),
(626, 'Pacho', 25, 0),
(627, 'Padilla', 19, 0),
(628, 'Paicol', 41, 0),
(629, 'Pailitas', 20, 0),
(630, 'Paime', 25, 0),
(631, 'Paipa', 15, 0),
(632, 'Pajarito', 15, 0),
(633, 'Palermo', 41, 0),
(634, 'Palestina', 17, 0),
(635, 'Palestina', 41, 0),
(636, 'Palmar', 68, 0),
(637, 'Palmar de Varela', 8, 0),
(638, 'Palmas del Socorro', 68, 0),
(639, 'Palmira', 76, 0),
(640, 'Palmito', 70, 0),
(641, 'Palocabildo', 73, 0),
(642, 'Pamplona', 54, 0),
(643, 'Pamplonita', 54, 0),
(644, 'Pandi', 25, 0),
(645, 'Panqueba', 15, 0),
(646, 'Paratebueno', 25, 0),
(647, 'Pasca', 25, 0),
(648, 'Patía (El Bordo)', 19, 0),
(649, 'Pauna', 15, 0),
(650, 'Paya', 15, 0),
(651, 'Paz de Ariporo', 85, 0),
(652, 'Paz de Río', 15, 0),
(653, 'Pedraza', 47, 0),
(654, 'Pelaya', 20, 0),
(655, 'Pensilvania', 17, 0),
(656, 'Peque', 5, 0),
(657, 'Pereira', 66, 0),
(658, 'Pesca', 15, 0),
(659, 'Peñol', 5, 0),
(660, 'Piamonte', 19, 0),
(661, 'Pie de Cuesta', 68, 0),
(662, 'Piedras', 73, 0),
(663, 'Piendamó', 19, 0),
(664, 'Pijao', 63, 0),
(665, 'Pijiño', 47, 0),
(666, 'Pinchote', 68, 0),
(667, 'Pinillos', 13, 0),
(668, 'Piojo', 8, 0),
(669, 'Pisva', 15, 0),
(670, 'Pital', 41, 0),
(671, 'Pitalito', 41, 0),
(672, 'Pivijay', 47, 0),
(673, 'Planadas', 73, 0),
(674, 'Planeta Rica', 23, 0),
(675, 'Plato', 47, 0),
(676, 'Policarpa', 52, 0),
(677, 'Polonuevo', 8, 0),
(678, 'Ponedera', 8, 0),
(679, 'Popayán', 19, 0),
(680, 'Pore', 85, 0),
(681, 'Potosí', 52, 0),
(682, 'Pradera', 76, 0),
(683, 'Prado', 73, 0),
(684, 'Providencia', 52, 0),
(685, 'Providencia', 88, 0),
(686, 'Pueblo Bello', 20, 0),
(687, 'Pueblo Nuevo', 23, 0),
(688, 'Pueblo Rico', 66, 0),
(689, 'Pueblorrico', 5, 0),
(690, 'Puebloviejo', 47, 0),
(691, 'Puente Nacional', 68, 0),
(692, 'Puerres', 52, 0),
(693, 'Puerto Asís', 86, 0),
(694, 'Puerto Berrío', 5, 0),
(695, 'Puerto Boyacá', 15, 0),
(696, 'Puerto Caicedo', 86, 0),
(697, 'Puerto Carreño', 99, 0),
(698, 'Puerto Colombia', 8, 0),
(699, 'Puerto Concordia', 50, 0),
(700, 'Puerto Escondido', 23, 0),
(701, 'Puerto Gaitán', 50, 0),
(702, 'Puerto Guzmán', 86, 0),
(703, 'Puerto Leguízamo', 86, 0),
(704, 'Puerto Libertador', 23, 0),
(705, 'Puerto Lleras', 50, 0),
(706, 'Puerto López', 50, 0),
(707, 'Puerto Nare', 5, 0),
(708, 'Puerto Nariño', 91, 0),
(709, 'Puerto Parra', 68, 0),
(710, 'Puerto Rico', 18, 0),
(711, 'Puerto Rico', 50, 0),
(712, 'Puerto Rondón', 81, 0),
(713, 'Puerto Salgar', 25, 0),
(714, 'Puerto Santander', 54, 0),
(715, 'Puerto Tejada', 19, 0),
(716, 'Puerto Triunfo', 5, 0),
(717, 'Puerto Wilches', 68, 0),
(718, 'Pulí', 25, 0),
(719, 'Pupiales', 52, 0),
(720, 'Puracé (Coconuco)', 19, 0),
(721, 'Purificación', 73, 0),
(722, 'Purísima', 23, 0),
(723, 'Pácora', 17, 0),
(724, 'Páez', 15, 0),
(725, 'Páez (Belalcazar)', 19, 0),
(726, 'Páramo', 68, 0),
(727, 'Quebradanegra', 25, 0),
(728, 'Quetame', 25, 0),
(729, 'Quibdó', 27, 0),
(730, 'Quimbaya', 63, 0),
(731, 'Quinchía', 66, 0),
(732, 'Quipama', 15, 0),
(733, 'Quipile', 25, 0),
(734, 'Ragonvalia', 54, 0),
(735, 'Ramiriquí', 15, 0),
(736, 'Recetor', 85, 0),
(737, 'Regidor', 13, 0),
(738, 'Remedios', 5, 0),
(739, 'Remolino', 47, 0),
(740, 'Repelón', 8, 0),
(741, 'Restrepo', 50, 0),
(742, 'Restrepo', 76, 0),
(743, 'Retiro', 5, 0),
(744, 'Ricaurte', 25, 0),
(745, 'Ricaurte', 52, 0),
(746, 'Rio Negro', 68, 0),
(747, 'Rioblanco', 73, 0),
(748, 'Riofrío', 76, 0),
(749, 'Riohacha', 44, 0),
(750, 'Risaralda', 17, 0),
(751, 'Rivera', 41, 0),
(752, 'Roberto Payán (San José)', 52, 0),
(753, 'Roldanillo', 76, 0),
(754, 'Roncesvalles', 73, 0),
(755, 'Rondón', 15, 0),
(756, 'Rosas', 19, 0),
(757, 'Rovira', 73, 0),
(758, 'Ráquira', 15, 0),
(759, 'Río Iró', 27, 0),
(760, 'Río Quito', 27, 0),
(761, 'Río Sucio', 17, 0),
(762, 'Río Viejo', 13, 0),
(763, 'Río de oro', 20, 0),
(764, 'Ríonegro', 5, 0),
(765, 'Ríosucio', 27, 0),
(766, 'Sabana de Torres', 68, 0),
(767, 'Sabanagrande', 8, 0),
(768, 'Sabanalarga', 5, 0),
(769, 'Sabanalarga', 8, 0),
(770, 'Sabanalarga', 85, 0),
(771, 'Sabanas de San Angel (SAN ANGEL)', 47, 0),
(772, 'Sabaneta', 5, 0),
(773, 'Saboyá', 15, 0),
(774, 'Sahagún', 23, 0),
(775, 'Saladoblanco', 41, 0),
(776, 'Salamina', 17, 0),
(777, 'Salamina', 47, 0),
(778, 'Salazar', 54, 0),
(779, 'Saldaña', 73, 0),
(780, 'Salento', 63, 0),
(781, 'Salgar', 5, 0),
(782, 'Samacá', 15, 0),
(783, 'Samaniego', 52, 0),
(784, 'Samaná', 17, 0),
(785, 'Sampués', 70, 0),
(786, 'San Agustín', 41, 0),
(787, 'San Alberto', 20, 0),
(788, 'San Andrés', 68, 0),
(789, 'San Andrés Sotavento', 23, 0),
(790, 'San Andrés de Cuerquía', 5, 0),
(791, 'San Antero', 23, 0),
(792, 'San Antonio', 73, 0),
(793, 'San Antonio de Tequendama', 25, 0),
(794, 'San Benito', 68, 0),
(795, 'San Benito Abad', 70, 0),
(796, 'San Bernardo', 25, 0),
(797, 'San Bernardo', 52, 0),
(798, 'San Bernardo del Viento', 23, 0),
(799, 'San Calixto', 54, 0),
(800, 'San Carlos', 5, 0),
(801, 'San Carlos', 23, 0),
(802, 'San Carlos de Guaroa', 50, 0),
(803, 'San Cayetano', 25, 0),
(804, 'San Cayetano', 54, 0),
(805, 'San Cristobal', 13, 0),
(806, 'San Diego', 20, 0),
(807, 'San Eduardo', 15, 0),
(808, 'San Estanislao', 13, 0),
(809, 'San Fernando', 13, 0),
(810, 'San Francisco', 5, 0),
(811, 'San Francisco', 25, 0),
(812, 'San Francisco', 86, 0),
(813, 'San Gíl', 68, 0),
(814, 'San Jacinto', 13, 0),
(815, 'San Jacinto del Cauca', 13, 0),
(816, 'San Jerónimo', 5, 0),
(817, 'San Joaquín', 68, 0),
(818, 'San José', 17, 0),
(819, 'San José de Miranda', 68, 0),
(820, 'San José de Montaña', 5, 0),
(821, 'San José de Pare', 15, 0),
(822, 'San José de Uré', 23, 0),
(823, 'San José del Fragua', 18, 0),
(824, 'San José del Guaviare', 95, 0),
(825, 'San José del Palmar', 27, 0),
(826, 'San Juan de Arama', 50, 0),
(827, 'San Juan de Betulia', 70, 0),
(828, 'San Juan de Nepomuceno', 13, 0),
(829, 'San Juan de Pasto', 52, 0),
(830, 'San Juan de Río Seco', 25, 0),
(831, 'San Juan de Urabá', 5, 0),
(832, 'San Juan del Cesar', 44, 0),
(833, 'San Juanito', 50, 0),
(834, 'San Lorenzo', 52, 0),
(835, 'San Luis', 73, 0),
(836, 'San Luís', 5, 0),
(837, 'San Luís de Gaceno', 15, 0),
(838, 'San Luís de Palenque', 85, 0),
(839, 'San Marcos', 70, 0),
(840, 'San Martín', 20, 0),
(841, 'San Martín', 50, 0),
(842, 'San Martín de Loba', 13, 0),
(843, 'San Mateo', 15, 0),
(844, 'San Miguel', 68, 0),
(845, 'San Miguel', 86, 0),
(846, 'San Miguel de Sema', 15, 0),
(847, 'San Onofre', 70, 0),
(848, 'San Pablo', 13, 0),
(849, 'San Pablo', 52, 0),
(850, 'San Pablo de Borbur', 15, 0),
(851, 'San Pedro', 5, 0),
(852, 'San Pedro', 70, 0),
(853, 'San Pedro', 76, 0),
(854, 'San Pedro de Cartago', 52, 0),
(855, 'San Pedro de Urabá', 5, 0),
(856, 'San Pelayo', 23, 0),
(857, 'San Rafael', 5, 0),
(858, 'San Roque', 5, 0),
(859, 'San Sebastián', 19, 0),
(860, 'San Sebastián de Buenavista', 47, 0),
(861, 'San Vicente', 5, 0),
(862, 'San Vicente del Caguán', 18, 0),
(863, 'San Vicente del Chucurí', 68, 0),
(864, 'San Zenón', 47, 0),
(865, 'Sandoná', 52, 0),
(866, 'Santa Ana', 47, 0),
(867, 'Santa Bárbara', 5, 0),
(868, 'Santa Bárbara', 68, 0),
(869, 'Santa Bárbara (Iscuandé)', 52, 0),
(870, 'Santa Bárbara de Pinto', 47, 0),
(871, 'Santa Catalina', 13, 0),
(872, 'Santa Fé de Antioquia', 5, 0),
(873, 'Santa Genoveva de Docorodó', 27, 0),
(874, 'Santa Helena del Opón', 68, 0),
(875, 'Santa Isabel', 73, 0),
(876, 'Santa Lucía', 8, 0),
(877, 'Santa Marta', 47, 0),
(878, 'Santa María', 15, 0),
(879, 'Santa María', 41, 0),
(880, 'Santa Rosa', 13, 0),
(881, 'Santa Rosa', 19, 0),
(882, 'Santa Rosa de Cabal', 66, 0),
(883, 'Santa Rosa de Osos', 5, 0),
(884, 'Santa Rosa de Viterbo', 15, 0),
(885, 'Santa Rosa del Sur', 13, 0),
(886, 'Santa Rosalía', 99, 0),
(887, 'Santa Sofía', 15, 0),
(888, 'Santana', 15, 0),
(889, 'Santander de Quilichao', 19, 0),
(890, 'Santiago', 54, 0),
(891, 'Santiago', 86, 0),
(892, 'Santo Domingo', 5, 0),
(893, 'Santo Tomás', 8, 0),
(894, 'Santuario', 5, 0),
(895, 'Santuario', 66, 0),
(896, 'Sapuyes', 52, 0),
(897, 'Saravena', 81, 0),
(898, 'Sardinata', 54, 0),
(899, 'Sasaima', 25, 0),
(900, 'Sativanorte', 15, 0),
(901, 'Sativasur', 15, 0),
(902, 'Segovia', 5, 0),
(903, 'Sesquilé', 25, 0),
(904, 'Sevilla', 76, 0),
(905, 'Siachoque', 15, 0),
(906, 'Sibaté', 25, 0),
(907, 'Sibundoy', 86, 0),
(908, 'Silos', 54, 0),
(909, 'Silvania', 25, 0),
(910, 'Silvia', 19, 0),
(911, 'Simacota', 68, 0),
(912, 'Simijaca', 25, 0),
(913, 'Simití', 13, 0),
(914, 'Sincelejo', 70, 0),
(915, 'Sincé', 70, 0),
(916, 'Sipí', 27, 0),
(917, 'Sitionuevo', 47, 0),
(918, 'Soacha', 25, 0),
(919, 'Soatá', 15, 0),
(920, 'Socha', 15, 0),
(921, 'Socorro', 68, 0),
(922, 'Socotá', 15, 0),
(923, 'Sogamoso', 15, 0),
(924, 'Solano', 18, 0),
(925, 'Soledad', 8, 0),
(926, 'Solita', 18, 0),
(927, 'Somondoco', 15, 0),
(928, 'Sonsón', 5, 0),
(929, 'Sopetrán', 5, 0),
(930, 'Soplaviento', 13, 0),
(931, 'Sopó', 25, 0),
(932, 'Sora', 15, 0),
(933, 'Soracá', 15, 0),
(934, 'Sotaquirá', 15, 0),
(935, 'Sotara (Paispamba)', 19, 0),
(936, 'Sotomayor (Los Andes)', 52, 0),
(937, 'Suaita', 68, 0),
(938, 'Suan', 8, 0),
(939, 'Suaza', 41, 0),
(940, 'Subachoque', 25, 0),
(941, 'Sucre', 19, 0),
(942, 'Sucre', 68, 0),
(943, 'Sucre', 70, 0),
(944, 'Suesca', 25, 0),
(945, 'Supatá', 25, 0),
(946, 'Supía', 17, 0),
(947, 'Suratá', 68, 0),
(948, 'Susa', 25, 0),
(949, 'Susacón', 15, 0),
(950, 'Sutamarchán', 15, 0),
(951, 'Sutatausa', 25, 0),
(952, 'Sutatenza', 15, 0),
(953, 'Suárez', 19, 0),
(954, 'Suárez', 73, 0),
(955, 'Sácama', 85, 0),
(956, 'Sáchica', 15, 0),
(957, 'Tabio', 25, 0),
(958, 'Tadó', 27, 0),
(959, 'Talaigua Nuevo', 13, 0),
(960, 'Tamalameque', 20, 0),
(961, 'Tame', 81, 0),
(962, 'Taminango', 52, 0),
(963, 'Tangua', 52, 0),
(964, 'Taraira', 97, 0),
(965, 'Tarazá', 5, 0),
(966, 'Tarqui', 41, 0),
(967, 'Tarso', 5, 0),
(968, 'Tasco', 15, 0),
(969, 'Tauramena', 85, 0),
(970, 'Tausa', 25, 0),
(971, 'Tello', 41, 0),
(972, 'Tena', 25, 0),
(973, 'Tenerife', 47, 0),
(974, 'Tenjo', 25, 0),
(975, 'Tenza', 15, 0),
(976, 'Teorama', 54, 0),
(977, 'Teruel', 41, 0),
(978, 'Tesalia', 41, 0),
(979, 'Tibacuy', 25, 0),
(980, 'Tibaná', 15, 0),
(981, 'Tibasosa', 15, 0),
(982, 'Tibirita', 25, 0),
(983, 'Tibú', 54, 0),
(984, 'Tierralta', 23, 0),
(985, 'Timaná', 41, 0),
(986, 'Timbiquí', 19, 0),
(987, 'Timbío', 19, 0),
(988, 'Tinjacá', 15, 0),
(989, 'Tipacoque', 15, 0),
(990, 'Tiquisio (Puerto Rico)', 13, 0),
(991, 'Titiribí', 5, 0),
(992, 'Toca', 15, 0),
(993, 'Tocaima', 25, 0),
(994, 'Tocancipá', 25, 0),
(995, 'Toguí', 15, 0),
(996, 'Toledo', 5, 0),
(997, 'Toledo', 54, 0),
(998, 'Tolú', 70, 0),
(999, 'Tolú Viejo', 70, 0),
(1000, 'Tona', 68, 0),
(1001, 'Topagá', 15, 0),
(1002, 'Topaipí', 25, 0),
(1003, 'Toribío', 19, 0),
(1004, 'Toro', 76, 0),
(1005, 'Tota', 15, 0),
(1006, 'Totoró', 19, 0),
(1007, 'Trinidad', 85, 0),
(1008, 'Trujillo', 76, 0),
(1009, 'Tubará', 8, 0),
(1010, 'Tuchín', 23, 0),
(1011, 'Tulúa', 76, 0),
(1012, 'Tumaco', 52, 0),
(1013, 'Tunja', 15, 0),
(1014, 'Tunungua', 15, 0),
(1015, 'Turbaco', 13, 0),
(1016, 'Turbaná', 13, 0),
(1017, 'Turbo', 5, 0),
(1018, 'Turmequé', 15, 0),
(1019, 'Tuta', 15, 0),
(1020, 'Tutasá', 15, 0),
(1021, 'Támara', 85, 0),
(1022, 'Támesis', 5, 0),
(1023, 'Túquerres', 52, 0),
(1024, 'Ubalá', 25, 0),
(1025, 'Ubaque', 25, 0),
(1026, 'Ubaté', 25, 0),
(1027, 'Ulloa', 76, 0),
(1028, 'Une', 25, 0),
(1029, 'Unguía', 27, 0),
(1030, 'Unión Panamericana (ÁNIMAS)', 27, 0),
(1031, 'Uramita', 5, 0),
(1032, 'Uribe', 50, 0),
(1033, 'Uribia', 44, 0),
(1034, 'Urrao', 5, 0),
(1035, 'Urumita', 44, 0),
(1036, 'Usiacuri', 8, 0),
(1037, 'Valdivia', 5, 0),
(1038, 'Valencia', 23, 0),
(1039, 'Valle de San José', 68, 0),
(1040, 'Valle de San Juan', 73, 0),
(1041, 'Valle del Guamuez', 86, 0),
(1042, 'Valledupar', 20, 0),
(1043, 'Valparaiso', 5, 0),
(1044, 'Valparaiso', 18, 0),
(1045, 'Vegachí', 5, 0),
(1046, 'Venadillo', 73, 0),
(1047, 'Venecia', 5, 0),
(1048, 'Venecia (Ospina Pérez)', 25, 0),
(1049, 'Ventaquemada', 15, 0),
(1050, 'Vergara', 25, 0),
(1051, 'Versalles', 76, 0),
(1052, 'Vetas', 68, 0),
(1053, 'Viani', 25, 0),
(1054, 'Vigía del Fuerte', 5, 0),
(1055, 'Vijes', 76, 0),
(1056, 'Villa Caro', 54, 0),
(1057, 'Villa Rica', 19, 0),
(1058, 'Villa de Leiva', 15, 0),
(1059, 'Villa del Rosario', 54, 0),
(1060, 'Villagarzón', 86, 0),
(1061, 'Villagómez', 25, 0),
(1062, 'Villahermosa', 73, 0),
(1063, 'Villamaría', 17, 0),
(1064, 'Villanueva', 13, 0),
(1065, 'Villanueva', 44, 0),
(1066, 'Villanueva', 68, 0),
(1067, 'Villanueva', 85, 0),
(1068, 'Villapinzón', 25, 0),
(1069, 'Villarrica', 73, 0),
(1070, 'Villavicencio', 50, 0),
(1071, 'Villavieja', 41, 0),
(1072, 'Villeta', 25, 0),
(1073, 'Viotá', 25, 0),
(1074, 'Viracachá', 15, 0),
(1075, 'Vista Hermosa', 50, 0),
(1076, 'Viterbo', 17, 0),
(1077, 'Vélez', 68, 0),
(1078, 'Yacopí', 25, 0),
(1079, 'Yacuanquer', 52, 0),
(1080, 'Yaguará', 41, 0),
(1081, 'Yalí', 5, 0),
(1082, 'Yarumal', 5, 0),
(1083, 'Yolombó', 5, 0),
(1084, 'Yondó (Casabe)', 5, 0),
(1085, 'Yopal', 85, 0),
(1086, 'Yotoco', 76, 0),
(1087, 'Yumbo', 76, 0),
(1088, 'Zambrano', 13, 0),
(1089, 'Zapatoca', 68, 0),
(1090, 'Zapayán (PUNTA DE PIEDRAS)', 47, 0),
(1091, 'Zaragoza', 5, 0),
(1092, 'Zarzal', 76, 0),
(1093, 'Zetaquirá', 15, 0),
(1094, 'Zipacón', 25, 0),
(1095, 'Zipaquirá', 25, 0),
(1096, 'Zona Bananera (PRADO - SEVILLA)', 47, 0),
(1097, 'Ábrego', 54, 0),
(1098, 'Íquira', 41, 0),
(1099, 'Úmbita', 15, 0),
(1100, 'Útica', 25, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `genero` enum('M','F','O') NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `tipo_identificacion` enum('CC','TI','EXTRANJERIA','PASAPORTE','REGISTRO CIVIL') NOT NULL,
  `identificacion` varchar(14) NOT NULL,
  `id_seguro` int(11) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `grupo_sangineo` varchar(20) NOT NULL,
  `alergias` enum('SI','NO') NOT NULL,
  `Tipo_de_Alergia` text NOT NULL,
  `id_municipio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id`, `nombre`, `apellido`, `genero`, `fecha_nacimiento`, `tipo_identificacion`, `identificacion`, `id_seguro`, `telefono`, `correo`, `direccion`, `grupo_sangineo`, `alergias`, `Tipo_de_Alergia`, `id_municipio`) VALUES
(1, 'brian', 'alfonso', 'O', '2013-10-09', 'TI', '1342352425', 1, '34537353', 'brian@gmail.com', 'calle90#56', 'O+', 'SI', 'AINES', 107),
(2, 'fernanda', 'rodriguez', 'F', '2014-06-13', 'TI', '1107589326', 2, '878352521', 'Fernanda125@gmail.com', 'calle67#56sur', 'A+', 'NO', 'N/A', 107),
(3, 'laura', 'garcia', 'F', '1998-10-16', 'CC', '878656534', 3, '9896465433', 'laura@gmail.com', 'calle90#70', 'O-', 'NO', 'N/A', 9),
(4, 'valentina', 'orduz', 'F', '2016-10-03', 'TI', '5436353736', 4, '787534222', 'valentina@gmail.com', 'calle67#80', 'A+', 'SI', 'ALERGICA A LOS ACAROS', 107),
(5, 'omar', 'montes', 'M', '2016-10-18', 'PASAPORTE', '563572623', 1, '9837325242', 'omar@gmail.com', 'calle90#90', 'O+', 'NO', 'N/A', 9),
(6, 'Oscar ', 'Jimenez', 'M', '1995-12-20', 'CC', '1102587423', 10, '3104780415', 'Oscarjimenez@Hotmail.com', 'Calle28#7e-02', 'O+', 'NO', 'N/A', 150),
(7, 'Antonio', 'Sanchez', 'O', '2024-01-03', 'REGISTRO CIVIL', '1102987235', 3, '3045987324', 'mamadeAntonio@gmail.com', 'Calle24#7e-05', 'O-', 'NO', 'N/A', 914),
(8, 'Xiomara', 'Sevilla', 'F', '2000-04-01', 'CC', '1103478963', 7, '3247859214', 'Xiomi32427@gmail.com', 'Calle47#4e-2', 'A-', 'SI', 'RINITIS ALERGICAS', 177),
(9, 'Cesar', 'Lopez', 'O', '2008-07-21', 'TI', '1104651329', 6, '310269754', 'Cesar145@gmail.com', 'Calle26#4z-12', 'O+', 'NO', 'N/A', 24),
(10, 'Felipe', 'Gomez', 'M', '2010-09-10', 'TI', '1102395784', 9, '3001782548', 'Felipe@Hotmail.com', 'Calle15#7s-14', 'A-', 'NO', 'N/A', 1097);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prescripción medica`
--

CREATE TABLE `prescripción medica` (
  `id` int(11) NOT NULL,
  `id_historia` int(11) NOT NULL,
  `id_medicamentos` int(11) NOT NULL,
  `cantidad_total` int(11) NOT NULL,
  `presentacion` varchar(100) NOT NULL,
  `indicaciones` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prescripción medica`
--

INSERT INTO `prescripción medica` (`id`, `id_historia`, `id_medicamentos`, `cantidad_total`, `presentacion`, `indicaciones`) VALUES
(1, 1, 2, 30, 'Tableta', 'administrar una tableta cada 6 horas x 10 Dias'),
(2, 2, 5, 20, 'Capsula', 'Suministrar una capsula cada 8 Horas x 5 Dias'),
(3, 3, 2, 30, 'Tabletas', 'administrar una tableta cada 6 horas x 10 Dias'),
(4, 4, 5, 20, 'Capsula', 'Suministrar una capsula cada 8 Horas x 5 Dias'),
(5, 5, 11, 40, 'Tabletas', 'se administrará 1 tableta por vía oral con un vaso de agua cada 24 Horas x 40 Dias '),
(6, 6, 12, 30, 'Tabletas ', 'Suministrar 1 tableta cada 24 Horas x 30 Dias '),
(7, 7, 13, 1, 'Jarabe 120ml', 'Se debe Suministar 4.5cc 4 veces al dia x 10 dias '),
(8, 8, 8, 30, 'Tabletas ', 'Suministrar una table cada 12 Horas x 10 Dias'),
(9, 9, 8, 20, 'Tabletas ', 'Suministrar una table cada 12 Horas x 5 Dias'),
(10, 10, 5, 30, 'Tabletas ', 'Suministrar 1 tableta cada 6 horas x 10 dias ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `nombre`) VALUES
(1, 'Perfil Usuario'),
(2, 'Perfil Medico'),
(3, 'Perfil Auxiliar'),
(4, 'Perfil Farmaceutico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro`
--

CREATE TABLE `seguro` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seguro`
--

INSERT INTO `seguro` (`id`, `nombre`) VALUES
(1, 'colsanitas'),
(2, 'colmedica'),
(3, 'compensar'),
(4, 'medisanitas'),
(5, 'sura prepagada'),
(6, 'coomeva'),
(7, 'seguros volibar'),
(8, 'saludtotal'),
(9, 'unisalud'),
(10, 'Mutual ser');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id` int(11) NOT NULL,
  `descripcion_servicio` varchar(200) NOT NULL,
  `tipo_servicio` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `descripcion_servicio`, `tipo_servicio`) VALUES
(1, 'Pediatria', 'Consulta Ambulatoria'),
(2, 'Medicina interna', 'Consulta Ambulatoria'),
(3, 'Dietetica y nutricion', 'Consulta Ambulatoria'),
(4, 'Fisioterapeuta', 'Consulta Ambulatoria'),
(5, 'Psicologia', 'Consulta Ambulatoria'),
(6, 'Ortopedia y Traumatologia', 'Consulta Ambulatoria'),
(7, 'Odontologia', 'Consulta Ambulatoria'),
(8, 'Medicina General', 'Consulta Ambulatoria'),
(9, 'Oftamologia', 'Consulta Ambulatoria'),
(10, 'Ginecologia', 'Consulta Ambulatoria'),
(11, 'Radiografia', 'Consulta Ambulatoria'),
(12, 'Hemograma', 'Consulta Ambulatoria'),
(13, 'Tomografia computarizada (TC)', 'Consulta Ambulatoria'),
(14, 'Radiografía periapical ', 'Consulta Ambulatoria'),
(15, 'Resonancia Magnetica (RMN)', 'Consulta Ambulatoria'),
(16, 'Electrocardiograma', 'Consulta Ambulatoria'),
(17, 'Insulina', 'Consulta Ambulatoria'),
(18, 'Electromiografía (EMG)', 'Consulta Ambulatoria'),
(19, 'Hemoglobina glicosilada', 'Consulta Ambulatoria'),
(20, 'Ecografía musculoesquelética', 'Consulta Ambulatoria'),
(21, 'Ecografía Abdominal', 'Consulta Ambulatoria'),
(22, 'Evaluacion psicologica completa', 'Consulta Ambulatoria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_examen`
--

CREATE TABLE `tipo_examen` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_examen`
--

INSERT INTO `tipo_examen` (`id`, `nombre`) VALUES
(1, 'Radiografia'),
(2, 'Hemograma'),
(3, 'Tomografia computarizada (TC)'),
(4, 'Radiografía periapical '),
(5, 'Resonancia Magnetica (RMN)'),
(6, 'Electrocardiograma'),
(7, 'Insulina'),
(8, 'Electromiografía (EMG)'),
(9, 'Hemoglobina glicosilada'),
(10, 'Ecografía musculoesquelética'),
(11, 'Ecografía Abdominal'),
(12, 'Evaluacion psicologica completa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `id_paciente` int(11) DEFAULT NULL,
  `id_medico` int(11) DEFAULT NULL,
  `id_auxiliar` int(11) DEFAULT NULL,
  `id_farmaceutico` int(11) DEFAULT NULL,
  `id_rol` int(11) NOT NULL,
  `codigo_restablecimiento` varchar(6) NOT NULL,
  `expiracion_codigo` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `ultima_solicitud` timestamp NOT NULL DEFAULT current_timestamp(),
  `intentos_fallidos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `password`, `id_paciente`, `id_medico`, `id_auxiliar`, `id_farmaceutico`, `id_rol`, `codigo_restablecimiento`, `expiracion_codigo`, `ultima_solicitud`, `intentos_fallidos`) VALUES
(1, 'brian@gmail.com', 'brian123', 1, NULL, NULL, NULL, 1, '123456', '2025-01-30 20:05:55', '2024-09-26 01:15:00', 0),
(2, 'Fernanda125@gmail.com', 'Fernanda128*', 2, NULL, NULL, NULL, 1, '234567', '2025-01-30 20:06:07', '2024-09-26 02:15:00', 0),
(3, 'laura@gmail.com', 'Laura741#', 3, NULL, NULL, NULL, 1, '345678', '2025-01-30 20:06:19', '2024-09-26 03:20:00', 0),
(4, 'valentina@gmail.com', 'Valentina7895&', 4, NULL, NULL, NULL, 1, '456789', '2025-01-30 20:06:37', '2024-09-26 04:10:00', 0),
(5, 'omar@gmail.com', 'Omar8595%', 5, NULL, NULL, NULL, 1, '567890', '2025-01-30 20:07:24', '2024-09-26 05:05:00', 0),
(6, 'Oscarjimenez@Hotmail.com', 'Oscarcito45', 6, NULL, NULL, NULL, 1, '678901', '2025-01-30 20:07:36', '2024-09-26 06:15:00', 0),
(7, 'mamadeAntonio@gmail.com', 'Antonito45**', 7, NULL, NULL, NULL, 1, '789012', '2025-01-30 20:07:43', '2024-09-26 07:10:00', 0),
(8, 'Xiomi32427@gmail.com', 'Xiomi47', 8, NULL, NULL, NULL, 1, '890123', '2025-01-30 20:07:51', '2024-09-26 08:20:00', 0),
(9, 'Cesar145@gmail.com', 'cesar134', 9, NULL, NULL, NULL, 1, '901234', '2025-01-30 20:07:59', '2024-09-26 09:10:00', 0),
(10, 'Felipe@Hotmail.com', 'Felipee98', 10, NULL, NULL, NULL, 1, '012345', '2025-01-30 20:08:06', '2024-09-26 10:15:00', 0),
(11, 'brayan@outlook.com', 'Brayan20058*', NULL, 1, NULL, NULL, 2, '123456', '2025-01-30 20:08:16', '2024-01-24 22:00:00', 0),
(12, 'lina123@gmail.com', 'linita4578@', NULL, 2, NULL, NULL, 2, '654321', '2025-01-30 20:08:25', '2024-01-24 23:00:00', 1),
(13, 'mailin123@gmail.com', 'Mailincita124/', NULL, 3, NULL, NULL, 2, '789012', '2025-01-30 20:08:36', '2024-01-25 00:00:00', 0),
(14, 'alendro@gmail.com', 'Alejandro7547*', NULL, 4, NULL, NULL, 2, '345678', '2025-01-30 20:08:46', '2024-01-25 01:00:00', 0),
(15, 'allison@gmail.com', 'Alison00257()', NULL, 5, NULL, NULL, 2, '567890', '2025-01-30 20:08:56', '2024-01-25 02:00:00', 0),
(16, 'leon@gmail.com', 'Leoncito129%', NULL, 6, NULL, NULL, 2, '234567', '2025-01-30 20:09:04', '2024-01-25 03:00:00', 1),
(17, 'jhon@gmail.com', 'jhon7536{', NULL, 7, NULL, NULL, 2, '987654', '2025-01-30 20:09:19', '2024-01-25 04:00:00', 0),
(18, 'stiven@gmail.com', 'Stiven32427*', NULL, 8, NULL, NULL, 2, '654789', '2025-01-30 20:09:30', '2024-01-25 05:00:00', 0),
(19, 'camilo@gmail.com', 'Camilo00785', NULL, 9, NULL, NULL, 2, '321654', '2025-01-30 20:09:39', '2024-01-25 06:00:00', 0),
(20, 'luz@gmail.com', 'Luz96325/', NULL, 10, NULL, NULL, 2, '456123', '2025-01-30 20:09:51', '2024-01-25 07:00:00', 1),
(21, 'juan.perez@hotmail.com', 'Juancito1247*', NULL, NULL, 1, NULL, 3, '123456', '2025-01-30 20:10:03', '2024-05-02 00:30:00', 0),
(22, 'maria.gomez@gmail.com', 'Mariagomez0175@', NULL, NULL, 2, NULL, 3, '654321', '2025-01-30 20:10:15', '2024-06-02 01:30:00', 0),
(23, 'carlos.ramirez@gmail.com', 'Carlitos4875***', NULL, NULL, 3, NULL, 3, '789012', '2025-01-30 20:10:24', '2024-07-02 02:15:00', 1),
(24, 'ana.lopez@hotmail.com', 'AnalopeZ32429<>', NULL, NULL, 4, NULL, 3, '345678', '2025-01-30 20:10:34', '2024-08-02 03:45:00', 0),
(25, 'pedro.martinez@gmail.com', 'Pedro2058', NULL, NULL, 5, NULL, 3, '567890', '2025-01-30 20:10:42', '2024-09-02 04:30:00', 0),
(26, 'laura.rodriguez@hotmail.com', 'Laurita45%', NULL, NULL, 6, NULL, 3, '234567', '2025-01-30 20:10:54', '2024-10-02 05:15:00', 0),
(27, 'luis.fernandez@gmail.com', 'Luisfernandito1036#', NULL, NULL, 7, NULL, 3, '987654', '2025-01-30 20:11:03', '2024-11-02 06:45:00', 2),
(28, 'sofia.torres@hotmail.com', 'SofiaTorres1078/', NULL, NULL, 8, NULL, 3, '654789', '2025-01-30 20:11:11', '2024-12-02 07:15:00', 0),
(29, 'jorge.diaz@gmail.com', 'Jorgediaz5089++', NULL, NULL, 9, NULL, 3, '321654', '2025-01-30 20:11:22', '2024-12-16 08:30:00', 0),
(30, 'patricia.gonzalez@gmail.com', 'Patricia5078@', NULL, NULL, 10, NULL, 3, '456123', '2025-01-30 20:11:29', '2024-12-20 09:45:00', 0),
(31, 'jose.mendoza@gmail.com', 'Jose10009@', NULL, NULL, NULL, 1, 4, 'ABC123', '2025-01-30 20:11:40', '2024-02-15 23:30:00', 0),
(32, 'ana.garcia@gmail.com', 'Anita8887**', NULL, NULL, NULL, 2, 4, 'XYZ789', '2025-01-30 20:11:49', '2024-02-16 00:30:00', 0),
(33, 'luis.perez@hotmail.com', 'Luis5414#', NULL, NULL, NULL, 3, 4, 'LMN456', '2025-01-30 20:11:59', '2024-02-16 01:30:00', 1),
(34, 'maria.rodriguez@gmail.com', 'MariaR10047$', NULL, NULL, NULL, 4, 4, 'DEF123', '2025-01-30 20:12:08', '2024-02-16 02:30:00', 0),
(35, 'carlos.martinez@hotmail.com', 'carlos5978', NULL, NULL, NULL, 5, 4, 'GHI456', '2025-01-30 20:12:17', '2024-02-16 03:30:00', 0),
(36, 'laura.jimenez@hotmail.com', 'Laurita3687**', NULL, NULL, NULL, 6, 4, 'JKL789', '2025-01-30 20:12:25', '2024-02-16 04:30:00', 0),
(37, 'pedro.lopez@gmail.com', 'Pedrito20789&', NULL, NULL, NULL, 7, 4, 'MNO012', '2025-01-30 20:12:34', '2024-02-16 05:30:00', 0),
(38, 'Bianca.torres@hotmail.com', 'Biankis117854T$', NULL, NULL, NULL, 8, 4, 'PQR345', '2025-01-30 20:12:44', '2024-02-16 06:30:00', 0),
(39, 'jorge.diaz@gmail.com', 'Jorgito20#', NULL, NULL, NULL, 9, 4, 'STU678', '2025-01-30 20:12:53', '2024-02-16 07:30:00', 0),
(40, 'DenisL@hotmail.com', 'DenisL1457@', NULL, NULL, NULL, 10, 4, 'VWX901', '2025-01-30 20:13:00', '2024-02-16 08:30:00', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `afiliacion`
--
ALTER TABLE `afiliacion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `identificacion` (`identificacion`),
  ADD KEY `fk_afiliacion_municipio` (`id_municipio`),
  ADD KEY `fk_afiliacion_seguro` (`id_seguro`);

--
-- Indices de la tabla `agendamiento`
--
ALTER TABLE `agendamiento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_agendamiento_persona` (`id_paciente`),
  ADD KEY `fk_agendamiento_medico` (`id_medico`),
  ADD KEY `fk_agendamiento_especialidad` (`id_especialidad`),
  ADD KEY `fk_agendamiento_usuario` (`id_usuario_creador`);

--
-- Indices de la tabla `auxiliar`
--
ALTER TABLE `auxiliar`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `identificacion` (`identificacion`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Hora` (`Hora`),
  ADD KEY `fk_cita_medico` (`id_medico`),
  ADD KEY `fk_cita_especialidad` (`id_especialidad`),
  ADD KEY `fk_cita_persona` (`id_paciente`);

--
-- Indices de la tabla `consultorio`
--
ALTER TABLE `consultorio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle_examenes`
--
ALTER TABLE `detalle_examenes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_detalle_examenes_tipo_examen` (`id_tipo_examen`),
  ADD KEY `fk_detalle_examenes_auxiliar` (`id_auxiliar`),
  ADD KEY `fk_detalle_examenes_paciente` (`id_paciente`);

--
-- Indices de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estado_afiliacion`
--
ALTER TABLE `estado_afiliacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_estado_afiliacion_afiliacion` (`id_afiliacion`);

--
-- Indices de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_facturacion_servicio` (`id_servicio`),
  ADD KEY `fk_facturacion_paciente` (`id_paciente`);

--
-- Indices de la tabla `farmaceutico`
--
ALTER TABLE `farmaceutico`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `numero_licencia` (`numero_licencia`);

--
-- Indices de la tabla `historia`
--
ALTER TABLE `historia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_historia_medico` (`id_medico`),
  ADD KEY `fk_historia_paciente` (`id_paciente`);

--
-- Indices de la tabla `inventario_medicamentos`
--
ALTER TABLE `inventario_medicamentos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_modo_uso` (`Cantidad`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `licencia_medica` (`licencia_medica`),
  ADD KEY `id_especialidad` (`id_especialidad`),
  ADD KEY `fk_medico_consultorio` (`id_consultorio`);

--
-- Indices de la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_departamento` (`id_departamento`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `identificacion` (`identificacion`),
  ADD KEY `id_municipio` (`id_municipio`),
  ADD KEY `id_seguro` (`id_seguro`);

--
-- Indices de la tabla `prescripción medica`
--
ALTER TABLE `prescripción medica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_medicamentos` (`id_medicamentos`),
  ADD KEY `fk_prescripcion_medica_historia` (`id_historia`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_examen`
--
ALTER TABLE `tipo_examen`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_paciente`),
  ADD KEY `fk_usuario_medico` (`id_medico`),
  ADD KEY `fk_usuario_auxiliar` (`id_auxiliar`),
  ADD KEY `fk_usuario_farmaceutico` (`id_farmaceutico`),
  ADD KEY `fk_usuario_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `afiliacion`
--
ALTER TABLE `afiliacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `auxiliar`
--
ALTER TABLE `auxiliar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `consultorio`
--
ALTER TABLE `consultorio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `departamento`
--
ALTER TABLE `departamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT de la tabla `detalle_examenes`
--
ALTER TABLE `detalle_examenes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `estado_afiliacion`
--
ALTER TABLE `estado_afiliacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `farmaceutico`
--
ALTER TABLE `farmaceutico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `historia`
--
ALTER TABLE `historia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `inventario_medicamentos`
--
ALTER TABLE `inventario_medicamentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `medico`
--
ALTER TABLE `medico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `municipio`
--
ALTER TABLE `municipio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1101;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `prescripción medica`
--
ALTER TABLE `prescripción medica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `seguro`
--
ALTER TABLE `seguro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `tipo_examen`
--
ALTER TABLE `tipo_examen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `afiliacion`
--
ALTER TABLE `afiliacion`
  ADD CONSTRAINT `fk_afiliacion_municipio` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_afiliacion_seguro` FOREIGN KEY (`id_seguro`) REFERENCES `seguro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `agendamiento`
--
ALTER TABLE `agendamiento`
  ADD CONSTRAINT `fk_agendamiento_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_agendamiento_medico` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_agendamiento_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_agendamiento_usuario` FOREIGN KEY (`id_usuario_creador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `fk_cita_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cita_medico` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cita_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_examenes`
--
ALTER TABLE `detalle_examenes`
  ADD CONSTRAINT `fk_detalle_examenes_auxiliar` FOREIGN KEY (`id_auxiliar`) REFERENCES `auxiliar` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_detalle_examenes_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_detalle_examenes_tipo_examen` FOREIGN KEY (`id_tipo_examen`) REFERENCES `tipo_examen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `estado_afiliacion`
--
ALTER TABLE `estado_afiliacion`
  ADD CONSTRAINT `fk_estado_afiliacion_afiliacion` FOREIGN KEY (`id_afiliacion`) REFERENCES `afiliacion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `facturacion`
--
ALTER TABLE `facturacion`
  ADD CONSTRAINT `fk_facturacion_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_facturacion_servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `historia`
--
ALTER TABLE `historia`
  ADD CONSTRAINT `fk_historia_medico` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_historia_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `medico`
--
ALTER TABLE `medico`
  ADD CONSTRAINT `fk_medico_consultorio` FOREIGN KEY (`id_consultorio`) REFERENCES `consultorio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_medico_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD CONSTRAINT `fk_municipio_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `fk_paciente_municipio` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_paciente_seguro` FOREIGN KEY (`id_seguro`) REFERENCES `seguro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `prescripción medica`
--
ALTER TABLE `prescripción medica`
  ADD CONSTRAINT `fk_prescripcion_medica_historia` FOREIGN KEY (`id_historia`) REFERENCES `historia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_prescripcion_medica_medicamentos` FOREIGN KEY (`id_medicamentos`) REFERENCES `inventario_medicamentos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_auxiliar` FOREIGN KEY (`id_auxiliar`) REFERENCES `auxiliar` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_farmaceutico` FOREIGN KEY (`id_farmaceutico`) REFERENCES `farmaceutico` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_medico` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuario_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
