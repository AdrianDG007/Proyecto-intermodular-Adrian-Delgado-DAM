--Consultas para la aplicacion de la tienda

USE tienda_videojuegos;

--Todos los juegos con su categoria
SELECT
    v.id_videojuego,
    v.titulo,
    v.plataforma,
    v.precio,
    v.stock,
    c.nombre AS categoria
FROM videojuego v
JOIN categoria  c ON v.id_categoria = c.id_categoria
ORDER BY v.titulo;

--Juegos con proveedor y categoria
SELECT
    v.titulo,
    v.plataforma,
    v.precio,
    c.nombre AS categoria,
    p.nombre AS proveedor
FROM videojuego v
JOIN categoria c ON v.id_categoria = c.id_categoria
JOIN proveedor p ON v.id_proveedor = p.id_proveedor
ORDER BY c.nombre, v.titulo;

--Buscar juegos por plataforma
SELECT titulo, precio, stock
FROM videojuego
WHERE plataforma = 'PS5'
ORDER BY precio DESC;

--Juegos con poco stock
SELECT titulo, plataforma, stock
FROM videojuego
WHERE stock < 10
ORDER BY stock ASC;

--Pedidos de un cliente
SELECT
    p.id_pedido,
    p.fecha,
    p.total,
    p.estado,
    CONCAT (c.nombre, ' ', c.apellidos) AS cliente
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id_cliente
WHERE c.email = 'carlos.garcia@email.com'
ORDER BY p.fecha DESC;

--Detalle completo de un pedido
SELECT
    p.id_pedido,
    p.fecha,
    p.estado,
    v.titulo,
    v.plataforma,
    lp.cantidad,
    lp.precio_unidad,
     (lp.cantidad * lp.precio_unidad) AS subtotal
FROM pedido p
JOIN linea_pedido lp ON p.id_pedido      = lp.id_pedido
JOIN videojuego   v  ON lp.id_videojuego = v.id_videojuego
WHERE p.id_pedido = 1;

--Cuanto ha gastado cada cliente
SELECT
    CONCAT (c.nombre, ' ', c.apellidos) AS cliente,
    COUNT (p.id_pedido)                 AS num_pedidos,
    SUM (p.total)                       AS total_gastado
FROM cliente c
LEFT JOIN pedido p ON c.id_cliente = p.id_cliente
               AND p.estado != 'cancelado'
GROUP BY c.id_cliente, c.nombre, c.apellidos
ORDER BY total_gastado DESC;

--Juegos mas vendidos
SELECT
    v.titulo,
    v.plataforma,
    SUM (lp.cantidad) AS unidades_vendidas
FROM videojuego v
JOIN linea_pedido lp ON v.id_videojuego = lp.id_videojuego
JOIN pedido        p ON lp.id_pedido    = p.id_pedido
WHERE p.estado != 'cancelado'
GROUP BY v.id_videojuego, v.titulo, v.plataforma
ORDER BY unidades_vendidas DESC;

--Ingresos por categoria
SELECT
    c.nombre                             AS categoria,
    SUM (lp.cantidad * lp.precio_unidad) AS ingresos
FROM categoria c
JOIN videojuego   v  ON c.id_categoria  = v.id_categoria
JOIN linea_pedido lp ON v.id_videojuego = lp.id_videojuego
JOIN pedido       p  ON lp.id_pedido    = p.id_pedido
WHERE p.estado != 'cancelado'
GROUP BY c.id_categoria, c.nombre
ORDER BY ingresos DESC;

--Clientes que no han pedido nada
SELECT
    CONCAT (c.nombre, ' ', c.apellidos) AS cliente,
    c.email
FROM cliente c
LEFT JOIN pedido p ON c.id_cliente = p.id_cliente
WHERE p.id_pedido IS NULL;

--Pedidos del ultimo mes
SELECT
    p.id_pedido,
    p.fecha,
    p.total,
    p.estado,
    CONCAT (c.nombre, ' ', c.apellidos) AS cliente
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id_cliente
WHERE p.fecha >= DATE_SUB (CURDATE (), INTERVAL 1 MONTH) 
ORDER BY p.fecha DESC;

--Juegos que nunca se han vendido
SELECT v.titulo, v.plataforma, v.precio
FROM videojuego v
LEFT JOIN linea_pedido lp ON v.id_videojuego = lp.id_videojuego
WHERE lp.id_linea IS NULL;