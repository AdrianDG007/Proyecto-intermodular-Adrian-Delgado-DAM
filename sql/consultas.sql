--Tienda de Videojuegos - Consultas
--Módulo: Bases de Datos (0484) 
--Proyecto Intermodular DAM 1º

USE tienda_videojuegos;

--1. Listado completo de videojuegos con su categoría
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

--2. Videojuegos con su proveedor y categoría
SELECT
    v.titulo,
    v.plataforma,
    v.precio,
    c.nombre  AS categoria,
    p.nombre  AS proveedor
FROM videojuego v
JOIN categoria  c  ON v.id_categoria  = c.id_categoria
JOIN proveedor p  ON v.id_proveedor  = p.id_proveedor
ORDER BY c.nombre, v.titulo;

--3. Buscar videojuegos por plataforma
SELECT titulo, precio, stock
FROM videojuego
WHERE plataforma = 'PS5'
ORDER BY precio DESC;

--4. Videojuegos con stock bajo (menos de 10 unidades) 
SELECT titulo, plataforma, stock
FROM videojuego
WHERE stock < 10
ORDER BY stock ASC;

--5. Pedidos de un cliente concreto con su estado
SELECT
    p.id_pedido,
    p.fecha,
    p.total,
    p.estado,
    CONCAT (c.nombre, ' ', c.apellidos) AS cliente
FROM pedido  p
JOIN cliente c ON p.id_cliente = c.id_cliente
WHERE c.email = 'carlos.garcia@email.com'
ORDER BY p.fecha DESC;

--6. Detalle completo de un pedido (líneas + videojuegos) 
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

--7. Total gastado por cada cliente
SELECT
    CONCAT (c.nombre, ' ', c.apellidos) AS cliente,
    COUNT  (p.id_pedido)                AS num_pedidos,
    SUM    (p.total)                    AS total_gastado
FROM cliente c
LEFT JOIN pedido p ON c.id_cliente = p.id_cliente
                   AND p.estado != 'cancelado'
GROUP BY c.id_cliente, c.nombre, c.apellidos
ORDER BY total_gastado DESC;

--8. Videojuegos más vendidos (por unidades) 
SELECT
    v.titulo,
    v.plataforma,
    SUM (lp.cantidad) AS unidades_vendidas
FROM videojuego v
JOIN linea_pedido lp ON v.id_videojuego = lp.id_videojuego
JOIN pedido p        ON lp.id_pedido    = p.id_pedido
WHERE p.estado != 'cancelado'
GROUP BY v.id_videojuego, v.titulo, v.plataforma
ORDER BY unidades_vendidas DESC;

--9. Ingresos por categoría
SELECT
    c.nombre                               AS categoria,
    SUM (lp.cantidad * lp.precio_unidad)   AS ingresos_totales
FROM categoria c
JOIN videojuego   v  ON c.id_categoria   = v.id_categoria
JOIN linea_pedido lp ON v.id_videojuego  = lp.id_videojuego
JOIN pedido       p  ON lp.id_pedido     = p.id_pedido
WHERE p.estado != 'cancelado'
GROUP BY c.id_categoria, c.nombre
ORDER BY ingresos_totales DESC;

--10. Clientes que nunca han hecho un pedido
SELECT
    CONCAT (c.nombre, ' ', c.apellidos) AS cliente,
    c.email
FROM cliente c
LEFT JOIN pedido p ON c.id_cliente = p.id_cliente
WHERE p.id_pedido IS NULL;

--11. Pedidos del último mes
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

--12. Videojuegos que no se han vendido nunca
SELECT v.titulo, v.plataforma, v.precio
FROM videojuego v
LEFT JOIN linea_pedido lp ON v.id_videojuego = lp.id_videojuego
WHERE lp.id_linea IS NULL;
