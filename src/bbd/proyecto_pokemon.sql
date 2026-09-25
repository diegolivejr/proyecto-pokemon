-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2024 a las 22:34:35
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
-- Base de datos: `proyecto_pokemon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combate`
--

CREATE TABLE `combate` (
  `ID_COMBATE` int(11) NOT NULL,
  `FECHA_HORA` datetime NOT NULL,
  `ID_ENTRENADOR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador`
--

CREATE TABLE `entrenador` (
  `ID_ENTRENADOR` int(11) NOT NULL,
  `NOM_ENTRENADOR` varchar(20) NOT NULL,
  `PASS` varchar(20) DEFAULT NULL,
  `POKEDOLLARS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrenador`
--

INSERT INTO `entrenador` (`ID_ENTRENADOR`, `NOM_ENTRENADOR`, `PASS`, `POKEDOLLARS`) VALUES
(1, 'abc', '123456', NULL),
(2, 'Jorgito', '123', NULL),
(3, '1', '1', 500),
(5, '123', '123', NULL),
(6, 'Robelto', '123', NULL),
(8, '2', '2', NULL),
(9, '2', '2', NULL),
(10, '3', '3', NULL),
(12, 'M', '1', NULL),
(14, 'x', 'x', NULL),
(15, 'z', 'z', NULL),
(16, '10', '10', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mochila`
--

CREATE TABLE `mochila` (
  `ID_ENTRENADOR` int(11) NOT NULL,
  `ID_OBJETO` int(11) NOT NULL,
  `NUM_OBJETO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE `movimientos` (
  `ID_MOVIMIENTO` int(11) NOT NULL,
  `NOM_MOVIMIENTO` varchar(20) NOT NULL,
  `POTENCIA` int(11) DEFAULT NULL,
  `TIPO` varchar(20) NOT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `QUITA` int(11) DEFAULT NULL,
  `TURNOS` int(11) DEFAULT NULL,
  `MEJORA` varchar(20) DEFAULT NULL,
  `CANT_MEJORA` int(11) DEFAULT NULL,
  `NIVEL_APRENDIZAJE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`ID_MOVIMIENTO`, `NOM_MOVIMIENTO`, `POTENCIA`, `TIPO`, `ESTADO`, `QUITA`, `TURNOS`, `MEJORA`, `CANT_MEJORA`, `NIVEL_APRENDIZAJE`) VALUES
(1, 'Ascuas', 40, 'Fuego', NULL, NULL, NULL, NULL, NULL, 6),
(2, 'Puño fuego', 75, 'Fuego', NULL, NULL, NULL, NULL, NULL, 26),
(3, 'LLamarada', 85, 'Fuego', NULL, NULL, NULL, NULL, NULL, 30),
(4, 'Lanzallamas', 90, 'Fuego', NULL, NULL, NULL, NULL, NULL, 15),
(5, 'Fuego fatuo ', NULL, 'Fuego', 'Quemado', NULL, NULL, NULL, NULL, 20),
(6, 'Dragoaliento', 60, 'Dragon', NULL, NULL, NULL, NULL, NULL, 15),
(7, 'Garra dragón', 80, 'Dragon', NULL, NULL, NULL, NULL, NULL, 22),
(8, 'Cometa draco', 110, 'Dragon', NULL, NULL, NULL, NULL, NULL, 35),
(9, 'Pulso dragón ', 85, 'Dragon', NULL, NULL, NULL, NULL, NULL, 23),
(10, 'Pistola agua', 40, 'Agua', NULL, NULL, NULL, NULL, NULL, 12),
(11, 'Hidrobomba', 110, 'Agua', NULL, NULL, NULL, NULL, NULL, 32),
(12, 'Surf', 90, 'Agua', NULL, NULL, NULL, NULL, NULL, 24),
(13, 'Burbuja', 40, 'Agua', NULL, NULL, NULL, NULL, NULL, 14),
(14, 'Corte furia', 40, 'Bicho', NULL, NULL, NULL, NULL, NULL, 12),
(15, 'Tijera X', 80, 'Bicho', NULL, NULL, NULL, NULL, NULL, 26),
(16, 'Zumbido', 90, 'Bicho', NULL, NULL, NULL, NULL, NULL, 26),
(17, 'Megacuerno', 120, 'Bicho', NULL, NULL, NULL, NULL, NULL, 36),
(18, 'Impactrueno', 40, 'Electrico', NULL, NULL, NULL, NULL, NULL, 14),
(19, 'Rayo', 90, 'Electrico', NULL, NULL, NULL, NULL, NULL, 26),
(20, 'Rayo', 90, 'Electrico', NULL, NULL, NULL, NULL, NULL, 26),
(21, 'Bola voltio', 110, 'Electrico', NULL, NULL, NULL, NULL, NULL, 32),
(22, 'Puño hielo', 75, 'Hielo', NULL, NULL, NULL, NULL, NULL, 20),
(23, 'Rayo hielo', 90, 'Hielo', NULL, NULL, NULL, NULL, NULL, 26),
(24, 'Ventisca', 110, 'Hielo', NULL, NULL, NULL, NULL, NULL, 34),
(25, 'Rayo aurora', 65, 'Hielo', NULL, NULL, NULL, NULL, NULL, 12),
(26, 'Doble patada', 60, 'Lucha', NULL, NULL, NULL, NULL, NULL, 15),
(27, 'Puño dinamico', 100, 'Lucha', NULL, NULL, NULL, NULL, NULL, 27),
(28, 'Tajo cruzado', 100, 'Lucha', NULL, NULL, NULL, NULL, NULL, 29),
(29, 'Ultrapuño', 40, 'Lucha', NULL, NULL, NULL, NULL, NULL, 6),
(30, 'A bocajarro', 120, 'Lucha', NULL, NULL, NULL, NULL, NULL, 34),
(31, 'Somnifero', NULL, 'Planta', 'Dormido', NULL, 3, NULL, NULL, 20),
(32, 'Hoja afilada ', 55, 'Planta', NULL, NULL, NULL, NULL, NULL, 12),
(33, 'Látigo cepa', 45, 'Planta', NULL, NULL, NULL, NULL, NULL, 9),
(34, 'Rayo solar', 120, 'Planta', NULL, NULL, NULL, NULL, NULL, 34),
(35, 'Hoja aguda', 90, 'Planta', NULL, NULL, NULL, NULL, NULL, 25),
(36, 'Lengüetazo', 30, 'Fantasma', NULL, NULL, NULL, NULL, NULL, 13),
(37, 'Bola sombra', 80, 'Fantasma', NULL, NULL, NULL, NULL, NULL, 25),
(38, 'Garra umbría', 70, 'Fantasma', NULL, NULL, NULL, NULL, NULL, 20),
(39, 'Golpe umbrío', 120, 'Fantasma', NULL, NULL, NULL, NULL, NULL, 36),
(40, 'Golpe umbrío', 120, 'Fantasma', NULL, NULL, NULL, NULL, NULL, 36),
(41, 'Psicoonda', 40, 'Psiquico', NULL, NULL, NULL, NULL, NULL, 14),
(42, 'Psicorrayo', 65, 'Psiquico', NULL, NULL, NULL, NULL, NULL, 20),
(43, 'Psíquico', 90, 'Psiquico', NULL, NULL, NULL, NULL, NULL, 26),
(44, 'Cabezazo zen', 80, 'Psiquico', NULL, NULL, NULL, NULL, NULL, 24),
(45, 'Avalancha', 75, 'Roca', NULL, NULL, NULL, NULL, NULL, 23),
(46, 'Lanzarrocas', 50, 'Roca', NULL, NULL, NULL, NULL, NULL, 16),
(47, 'Pedrada', 25, 'Roca', NULL, NULL, NULL, NULL, NULL, 10),
(48, 'Joya de luz', 80, 'Roca', NULL, NULL, NULL, NULL, NULL, 24),
(49, 'Huesomerang', 50, 'Tierra', NULL, NULL, NULL, NULL, NULL, 16),
(50, 'Terremoto', 100, 'Tierra', NULL, NULL, NULL, NULL, NULL, 28),
(51, 'Terratemblor', 60, 'Tierra', NULL, NULL, NULL, NULL, NULL, 19),
(52, 'Bofetón lodo', 20, 'Tierra', NULL, NULL, NULL, NULL, NULL, 10),
(53, 'Tóxico', NULL, 'Veneno', 'Envenenado', NULL, NULL, NULL, NULL, 20),
(54, 'Bomba lodo', 90, 'Veneno', NULL, NULL, NULL, NULL, NULL, 26),
(55, 'Bilis', 100, 'Veneno', NULL, NULL, NULL, NULL, NULL, 28),
(56, 'Veneno X', 70, 'Veneno', NULL, NULL, NULL, NULL, NULL, 21),
(57, 'Picotazo', 35, 'Volador', NULL, NULL, NULL, NULL, NULL, 8),
(58, 'Tornado', 40, 'Volador', NULL, NULL, NULL, NULL, NULL, 12),
(59, 'Vuelo', 90, 'Volador', NULL, NULL, NULL, NULL, NULL, 25),
(60, 'Pico taladro', 80, 'Volador', NULL, NULL, NULL, NULL, NULL, 19),
(61, 'Pájaro Osado', 110, 'Volador', NULL, NULL, NULL, NULL, NULL, 30),
(62, 'Cabezazo', 130, 'Normal', NULL, NULL, NULL, NULL, NULL, 38),
(63, 'Bomba huevo', 100, 'Normal', NULL, NULL, NULL, NULL, NULL, 28),
(64, 'Dia de pago', 40, 'Normal', NULL, NULL, NULL, NULL, NULL, 14),
(65, 'Escupir', 60, 'Normal', NULL, NULL, NULL, NULL, NULL, 19),
(66, 'Chaleco bomba', 1000, 'Normal', NULL, NULL, NULL, NULL, NULL, 10),
(67, 'Mochila bomba', 1000, 'Normal', NULL, NULL, NULL, NULL, NULL, 15),
(68, 'Placaje', 40, 'Normal', NULL, NULL, NULL, NULL, NULL, 1),
(69, 'Suspenso', 9999, 'Normal', NULL, NULL, NULL, NULL, NULL, 30),
(70, 'Amego Segarro', NULL, 'Normal', 'Defensa', 55, NULL, NULL, NULL, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento_pokemon`
--

CREATE TABLE `movimiento_pokemon` (
  `ID_MOVIMIENTO` int(11) NOT NULL,
  `ID_POKEMON` int(11) NOT NULL,
  `ACTIVO` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimiento_pokemon`
--

INSERT INTO `movimiento_pokemon` (`ID_MOVIMIENTO`, `ID_POKEMON`, `ACTIVO`) VALUES
(14, 170, 'S'),
(64, 170, 'N'),
(66, 170, 'S'),
(67, 170, 'S'),
(68, 165, 'S'),
(68, 167, 'S'),
(68, 170, 'S'),
(68, 172, 'S'),
(68, 176, 'S'),
(68, 178, 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objeto`
--

CREATE TABLE `objeto` (
  `ID_OBJETO` int(11) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `PRECIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `objeto`
--

INSERT INTO `objeto` (`ID_OBJETO`, `NOMBRE`, `PRECIO`) VALUES
(1, 'ANILLO UNICO', 4500),
(2, 'BASTON', 1500),
(3, 'CHALECO', 1500),
(4, 'PESA', 1500),
(5, 'ETER', 500),
(6, 'PILA', 1500),
(7, 'PLUMA', 1500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objeto_entrenador`
--

CREATE TABLE `objeto_entrenador` (
  `ID_OBJETO` int(11) NOT NULL,
  `ID_ENTRENADOR` int(11) NOT NULL,
  `CANTIDAD` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `objeto_entrenador`
--

INSERT INTO `objeto_entrenador` (`ID_OBJETO`, `ID_ENTRENADOR`, `CANTIDAD`) VALUES
(1, 3, 1),
(4, 3, 1),
(5, 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pokedex`
--

CREATE TABLE `pokedex` (
  `NUM_POKEDEX` int(11) NOT NULL,
  `NOM_POKEMON` varchar(30) NOT NULL,
  `TIPO1` varchar(20) NOT NULL,
  `TIPO2` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pokedex`
--

INSERT INTO `pokedex` (`NUM_POKEDEX`, `NOM_POKEMON`, `TIPO1`, `TIPO2`) VALUES
(1, 'Bulbasaur', 'Planta', 'Veneno'),
(2, 'Ivysaur', 'Planta', 'Veneno '),
(3, 'Venusaur', 'Planta', 'Veneno'),
(4, 'Charmander', 'Fuego', NULL),
(5, 'Charmeleon', 'Fuego', NULL),
(6, 'Charizard', 'Fuego', 'Volador'),
(7, 'Squirtle', 'Agua', NULL),
(8, 'Wartortle', 'Agua', NULL),
(9, 'Blastoise', 'Agua', NULL),
(10, 'Caterpie', 'Bicho', NULL),
(11, 'Metapod', 'Bicho', NULL),
(12, 'Butterfree', 'Bicho', 'Volador'),
(13, 'Weedle', 'Bicho', 'Veneno'),
(14, 'Kakuna', 'Bicho', 'Veneno '),
(15, 'Beedrill', 'Bicho', 'Veneno'),
(16, 'Pidgey', 'Normal', 'Volador'),
(17, 'Pidgeotto', 'Normal', 'Volador'),
(18, 'Pidgeot', 'Normal', 'Volador'),
(19, 'Rattata', 'Normal', ''),
(20, 'Raticate', 'Normal', ''),
(21, 'Exeggcute', 'Planta', 'Psiquico'),
(22, 'Exeggutor', 'Planta', 'Psiquico'),
(23, 'Pikachu', 'Electrico', NULL),
(24, 'Raichu', 'Electrico', NULL),
(25, 'Vulpix', 'Fuego', NULL),
(26, 'Ninetales', 'Fuego', NULL),
(27, 'Oddish', 'Planta', 'Veneno'),
(28, 'Gloom', 'Planta', 'Veneno'),
(29, 'Diglett', 'Tierra', NULL),
(30, 'Dugtrio', 'Tierra', NULL),
(31, 'Meowth', 'Normal', NULL),
(32, 'Persian', 'Normal', NULL),
(33, 'Psyduck', 'Agua', NULL),
(34, 'Growlithe', 'Fuego', NULL),
(35, 'Arcanine', 'Fuego', NULL),
(36, 'Abra', 'Psiquico', NULL),
(37, 'Kadabra', 'Psiquico', NULL),
(38, 'Alakazam', 'Psiquico', NULL),
(39, 'Gastly', 'Fantasma', 'Veneno'),
(40, 'Haunter', 'Fantasma', 'Veneno'),
(41, 'Gengar', 'Fantasma', 'Veneno'),
(42, 'Lapras', 'Agua', 'Hielo'),
(43, 'Snorlax', 'Normal', NULL),
(44, 'Machop', 'Lucha', NULL),
(45, 'Machoke', 'Lucha', NULL),
(46, 'Machamp', 'Lucha', NULL),
(47, 'Magikarp', 'Agua', NULL),
(48, 'Gyarados', 'Agua', 'Volador'),
(49, 'Scyther', 'Bicho', 'Volador'),
(50, 'Arceus', 'Normal', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pokemon`
--

CREATE TABLE `pokemon` (
  `ID_POKEMON` int(11) NOT NULL,
  `NUM_POKEDEX` int(11) DEFAULT NULL,
  `ID_ENTRENADOR` int(11) DEFAULT NULL,
  `CAJA` int(11) NOT NULL DEFAULT 1,
  `NOMBRE` varchar(15) DEFAULT NULL,
  `MOTE` varchar(30) DEFAULT NULL,
  `SALUD` int(11) NOT NULL,
  `ATAQUE` int(11) NOT NULL,
  `DEFENSA` int(11) NOT NULL,
  `VELOCIDAD` int(11) NOT NULL,
  `AT_ESPECIAL` int(11) NOT NULL,
  `DEF_ESPECIAL` int(11) NOT NULL,
  `NIVEL` int(11) NOT NULL,
  `FERTILIDAD` int(11) NOT NULL,
  `SEXO` varchar(1) NOT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `EXPERIENCIA` int(11) NOT NULL,
  `MOVIMIENTO1` text DEFAULT NULL,
  `MOVIMIENTO2` text DEFAULT NULL,
  `MOVIMIENTO3` text DEFAULT NULL,
  `MOVIMIENTO4` text DEFAULT NULL,
  `ID_OBJETO` int(11) DEFAULT NULL,
  `SALUD_MAXIMA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pokemon`
--

INSERT INTO `pokemon` (`ID_POKEMON`, `NUM_POKEDEX`, `ID_ENTRENADOR`, `CAJA`, `NOMBRE`, `MOTE`, `SALUD`, `ATAQUE`, `DEFENSA`, `VELOCIDAD`, `AT_ESPECIAL`, `DEF_ESPECIAL`, `NIVEL`, `FERTILIDAD`, `SEXO`, `ESTADO`, `EXPERIENCIA`, `MOVIMIENTO1`, `MOVIMIENTO2`, `MOVIMIENTO3`, `MOVIMIENTO4`, `ID_OBJETO`, `SALUD_MAXIMA`) VALUES
(165, 44, 3, 0, 'Machop', 'Machop', 78, 17, 29, 30, 19, 31, 4, 5, 'H', NULL, 32, NULL, NULL, NULL, NULL, NULL, 78),
(167, 44, 3, 0, 'Machop', 'macho', 12, 11, 15, 14, 13, 15, 2, 5, 'M', NULL, 15, NULL, NULL, NULL, NULL, NULL, 12),
(170, 11, 3, 0, 'Metapod', 'Precioso Mote', 221, 57, 76, 52, 69, 60, 16, 5, 'M', NULL, 155, NULL, NULL, NULL, NULL, NULL, 221),
(172, 48, 3, 0, 'Gyarados', 'Gyarados', 98, 27, 38, 18, 26, 40, 5, 5, 'H', NULL, 45, NULL, NULL, NULL, NULL, NULL, 98),
(176, 40, 16, 0, 'Haunter', 'pepe', -8, 26, 23, 20, 28, 18, 4, 5, 'H', NULL, 30, NULL, NULL, NULL, NULL, NULL, 72),
(178, 13, 3, 1, 'Weedle', 'Weedle', 47, 12, 22, 11, 10, 14, 1, 5, 'H', NULL, 1, NULL, NULL, NULL, NULL, NULL, 47);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turnos`
--

CREATE TABLE `turnos` (
  `ID_TURNO` int(11) NOT NULL,
  `ACCION_ENTRENADOR` varchar(150) NOT NULL,
  `ACCION_RIVAL` varchar(150) NOT NULL,
  `ID_COMBATE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `combate`
--
ALTER TABLE `combate`
  ADD PRIMARY KEY (`ID_COMBATE`),
  ADD KEY `ID_ENTRENADOR` (`ID_ENTRENADOR`);

--
-- Indices de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD PRIMARY KEY (`ID_ENTRENADOR`);

--
-- Indices de la tabla `mochila`
--
ALTER TABLE `mochila`
  ADD PRIMARY KEY (`ID_ENTRENADOR`,`ID_OBJETO`),
  ADD KEY `fk_objeto` (`ID_OBJETO`);

--
-- Indices de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`ID_MOVIMIENTO`);

--
-- Indices de la tabla `movimiento_pokemon`
--
ALTER TABLE `movimiento_pokemon`
  ADD PRIMARY KEY (`ID_MOVIMIENTO`,`ID_POKEMON`),
  ADD KEY `ID_POKEMON` (`ID_POKEMON`);

--
-- Indices de la tabla `objeto`
--
ALTER TABLE `objeto`
  ADD PRIMARY KEY (`ID_OBJETO`);

--
-- Indices de la tabla `objeto_entrenador`
--
ALTER TABLE `objeto_entrenador`
  ADD PRIMARY KEY (`ID_OBJETO`,`ID_ENTRENADOR`);

--
-- Indices de la tabla `pokedex`
--
ALTER TABLE `pokedex`
  ADD PRIMARY KEY (`NUM_POKEDEX`);

--
-- Indices de la tabla `pokemon`
--
ALTER TABLE `pokemon`
  ADD PRIMARY KEY (`ID_POKEMON`),
  ADD KEY `NUM_POKEDEX` (`NUM_POKEDEX`),
  ADD KEY `ID_ENTRENADOR` (`ID_ENTRENADOR`),
  ADD KEY `ID_OBJETO` (`ID_OBJETO`);

--
-- Indices de la tabla `turnos`
--
ALTER TABLE `turnos`
  ADD PRIMARY KEY (`ID_TURNO`),
  ADD KEY `ID_COMBATE` (`ID_COMBATE`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  MODIFY `ID_ENTRENADOR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `pokemon`
--
ALTER TABLE `pokemon`
  MODIFY `ID_POKEMON` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=179;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `combate`
--
ALTER TABLE `combate`
  ADD CONSTRAINT `combate_ibfk_1` FOREIGN KEY (`ID_ENTRENADOR`) REFERENCES `entrenador` (`ID_ENTRENADOR`);

--
-- Filtros para la tabla `mochila`
--
ALTER TABLE `mochila`
  ADD CONSTRAINT `fk_entrenador` FOREIGN KEY (`ID_ENTRENADOR`) REFERENCES `entrenador` (`ID_ENTRENADOR`),
  ADD CONSTRAINT `fk_objeto` FOREIGN KEY (`ID_OBJETO`) REFERENCES `objeto` (`ID_OBJETO`);

--
-- Filtros para la tabla `movimiento_pokemon`
--
ALTER TABLE `movimiento_pokemon`
  ADD CONSTRAINT `movimiento_pokemon_ibfk_1` FOREIGN KEY (`ID_MOVIMIENTO`) REFERENCES `movimientos` (`ID_MOVIMIENTO`),
  ADD CONSTRAINT `movimiento_pokemon_ibfk_2` FOREIGN KEY (`ID_POKEMON`) REFERENCES `pokemon` (`ID_POKEMON`);

--
-- Filtros para la tabla `pokemon`
--
ALTER TABLE `pokemon`
  ADD CONSTRAINT `pokemon_ibfk_1` FOREIGN KEY (`NUM_POKEDEX`) REFERENCES `pokedex` (`NUM_POKEDEX`),
  ADD CONSTRAINT `pokemon_ibfk_2` FOREIGN KEY (`ID_ENTRENADOR`) REFERENCES `entrenador` (`ID_ENTRENADOR`),
  ADD CONSTRAINT `pokemon_ibfk_3` FOREIGN KEY (`ID_OBJETO`) REFERENCES `objeto` (`ID_OBJETO`);

--
-- Filtros para la tabla `turnos`
--
ALTER TABLE `turnos`
  ADD CONSTRAINT `turnos_ibfk_1` FOREIGN KEY (`ID_COMBATE`) REFERENCES `combate` (`ID_COMBATE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
