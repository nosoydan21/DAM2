-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS tienda;
USE tienda;

-- Crear la tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL
);

-- Insertar 10 registros de ejemplo
INSERT INTO productos (nombre, precio, cantidad) VALUES 
('Camiseta', 19.99, 50),
('Pantalón', 39.99, 30),
('Zapatos', 59.90, 20),
('Gorra', 9.50, 100),
('Chaqueta', 79.99, 10),
('Calcetines', 4.99, 200),
('Bufanda', 12.75, 25),
('Guantes', 14.30, 40),
('Mochila', 34.95, 15),
('Lentes de sol', 24.99, 35);

--Procedimiento almacenado
DELIMITER //

CREATE FUNCTION fn_eliminar_producto(p_id INT)
RETURNS BOOLEAN
BEGIN
    DECLARE filas INT DEFAULT 0;

    -- Si ocurre cualquier error SQL, devolvemos FALSE
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        RETURN FALSE;
    END;

    DELETE FROM productos WHERE id = p_id;
    SET filas = ROW_COUNT();

    RETURN filas > 0; -- TRUE si se eliminó, FALSE si no existía
END//
DELIMITER ;
