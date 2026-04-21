--Creacion de la base de datos y tablas para la tienda de videojuegos

CREATE DATABASE IF NOT EXISTS tienda_videojuegos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE tienda_videojuegos;

CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT          NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR (50) NOT NULL,
    descripcion  VARCHAR (200),
    PRIMARY KEY (id_categoria)
);

CREATE TABLE IF NOT EXISTS proveedor (
    id_proveedor INT          NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR (100) NOT NULL,
    telefono     VARCHAR (15),
    email        VARCHAR (100),
    PRIMARY KEY (id_proveedor)
);

CREATE TABLE IF NOT EXISTS videojuego (
    id_videojuego INT            NOT NULL AUTO_INCREMENT,
    titulo        VARCHAR (150)  NOT NULL,
    plataforma    VARCHAR (50)   NOT NULL,
    precio        DECIMAL (8,2)  NOT NULL CHECK (precio >= 0),
    stock         INT            NOT NULL DEFAULT 0 CHECK (stock >= 0),
    id_categoria  INT,
    id_proveedor  INT,
    PRIMARY KEY (id_videojuego),
    CONSTRAINT fk_vj_categoria FOREIGN KEY (id_categoria)
        REFERENCES categoria (id_categoria)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_vj_proveedor FOREIGN KEY (id_proveedor)
        REFERENCES proveedor (id_proveedor)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT           NOT NULL AUTO_INCREMENT,
    nombre     VARCHAR (75)  NOT NULL,
    apellidos  VARCHAR (100) NOT NULL,
    email      VARCHAR (100) NOT NULL UNIQUE,
    telefono   VARCHAR (15),
    PRIMARY KEY (id_cliente)
);

CREATE TABLE IF NOT EXISTS pedido (
    id_pedido  INT            NOT NULL AUTO_INCREMENT,
    fecha      DATE           NOT NULL,
    total      DECIMAL (10,2) NOT NULL DEFAULT 0.00,
    estado     ENUM ( 'pendiente','pagado','enviado','cancelado' ) NOT NULL DEFAULT 'pendiente',
    id_cliente INT            NOT NULL,
    PRIMARY KEY (id_pedido),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

--Tabla intermedia entre pedido y videojuego
CREATE TABLE IF NOT EXISTS linea_pedido (
    id_linea      INT           NOT NULL AUTO_INCREMENT,
    cantidad      INT           NOT NULL CHECK (cantidad > 0),
    precio_unidad DECIMAL (8,2) NOT NULL CHECK (precio_unidad >= 0),
    id_pedido     INT           NOT NULL,
    id_videojuego INT           NOT NULL,
    PRIMARY KEY (id_linea),
    CONSTRAINT fk_linea_pedido     FOREIGN KEY (id_pedido)
        REFERENCES pedido (id_pedido)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_linea_videojuego FOREIGN KEY (id_videojuego)
        REFERENCES videojuego (id_videojuego)
        ON DELETE RESTRICT ON UPDATE CASCADE
);