CREATE DATABASE tienda_online;
USE tienda_online;

-- Tabla para almacenar los productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('Electronico', 'Ropa') NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- Tabla para productos electrónicos con detalles adicionales
CREATE TABLE electronicos (
    id INT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    garantia INT NOT NULL, -- meses
    FOREIGN KEY (id) REFERENCES productos(id) ON DELETE CASCADE
);

-- Tabla para productos de ropa con detalles adicionales
CREATE TABLE ropa (
    id INT PRIMARY KEY,
    talla VARCHAR(10) NOT NULL,
    material VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES productos(id) ON DELETE CASCADE
);

-- Insertar 10 productos electrónicos
INSERT INTO productos (tipo, nombre, precio, stock) VALUES
('Electronico', 'Smartphone Samsung', 699.99, 20),
('Electronico', 'Laptop Dell', 899.50, 15),
('Electronico', 'Tablet Apple', 499.00, 25),
('Electronico', 'Auriculares Sony', 199.99, 30),
('Electronico', 'Monitor LG', 299.99, 10),
('Electronico', 'Teclado Mecánico', 89.99, 40),
('Electronico', 'Mouse Inalámbrico', 59.99, 35),
('Electronico', 'Smartwatch Garmin', 249.99, 12),
('Electronico', 'Cámara Nikon', 549.99, 8),
('Electronico', 'Impresora HP', 149.99, 20);

-- Insertar detalles en electronicos
INSERT INTO electronicos (id, marca, garantia) VALUES
(1, 'Samsung', 24),
(2, 'Dell', 36),
(3, 'Apple', 12),
(4, 'Sony', 18),
(5, 'LG', 24),
(6, 'Logitech', 12),
(7, 'Logitech', 12),
(8, 'Garmin', 24),
(9, 'Nikon', 36),
(10, 'HP', 18);

-- Insertar 10 productos de ropa
INSERT INTO productos (tipo, nombre, precio, stock) VALUES
('Ropa', 'Camiseta Adidas', 29.99, 50),
('Ropa', 'Pantalón Levi\'s', 59.99, 30),
('Ropa', 'Chaqueta North Face', 149.99, 15),
('Ropa', 'Zapatillas Nike', 99.99, 20),
('Ropa', 'Gorra Puma', 19.99, 40),
('Ropa', 'Sudadera Reebok', 49.99, 25),
('Ropa', 'Bufanda de Lana', 24.99, 35),
('Ropa', 'Vestido Zara', 89.99, 18),
('Ropa', 'Calcetines deportivos', 9.99, 60),
('Ropa', 'Guantes de Cuero', 39.99, 22);

-- Insertar detalles en ropa
INSERT INTO ropa (id, talla, material) VALUES
(11, 'M', 'Algodón'),
(12, 'L', 'Denim'),
(13, 'XL', 'Poliéster'),
(14, '42', 'Cuero sintético'),
(15, 'Única', 'Algodón'),
(16, 'M', 'Mezcla de Algodón'),
(17, 'L', 'Lana'),
(18, 'S', 'Seda'),
(19, 'M', 'Sintético'),
(20, 'L', 'Cuero genuino');

COMMIT;
