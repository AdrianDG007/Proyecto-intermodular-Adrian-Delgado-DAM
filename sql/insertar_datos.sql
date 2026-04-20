--Tienda de Videojuegos - Script de inserción de datos
--Módulo: Bases de Datos (0484)
--Proyecto Intermodular DAM 1º

USE tienda_videojuegos;

--CATEGORIAS
INSERT INTO categoria (nombre, descripcion) VALUES
( 'Acción',        'Juegos de acción y combate en tiempo real'             ),
( 'RPG',           'Juegos de rol con desarrollo de personaje y narrativa' ),
( 'Deportes',      'Simuladores y juegos de competición deportiva'         ),
( 'Aventura',      'Juegos de exploración y resolución de puzzles'         ),
( 'Estrategia',    'Juegos de planificación táctica y gestión de recursos' ),
( 'Terror',        'Juegos de horror y supervivencia'                      );

--PROVEEDORES
INSERT INTO proveedor (nombre, telefono, email) VALUES
( 'Koch Media España',   '912345678', 'pedidos@kochmedia.es'  ),
( 'Bandai Namco ES',     '934567890', 'ventas@bandainamco.es' ),
( 'Sony Interactive',    '915678901', 'distribución@sony.es'  ),
( 'Nintendo España',     '916789012', 'pedidos@nintendo.es'   );

--VIDEOJUEGOS
INSERT INTO videojuego (titulo, plataforma, precio, stock, id_categoria, id_proveedor) VALUES
( 'God of War Ragnarok',         'PS5',      69.99, 15, 1, 3 ),
( 'The Witcher 3',               'PC',       19.99, 30, 2, 1 ),
( 'FIFA 24',                     'PS5',      59.99, 20, 3, 1 ),
( 'Zelda: Tears of the Kingdom', 'Switch',   59.99, 25, 4, 4 ),
( 'Elden Ring',                  'PC',       49.99, 18, 2, 2 ),
( 'Resident Evil 4',             'PS5',      49.99, 12, 6, 3 ),
( 'Age of Empires IV',           'PC',       39.99, 10, 5, 1 ),
( 'Spider-Man 2',                'PS5',      79.99,  8, 1, 3 ),
( 'Mario Kart 8 Deluxe',         'Switch',   49.99, 22, 3, 4 ),
( 'Hogwarts Legacy',             'PC',       49.99, 14, 2, 2 ),
( 'The Last of Us Part I',       'PC',       39.99,  9, 1, 3 ),
( 'Pikmin 4',                    'Switch',   49.99, 11, 4, 4 );

--CLIENTES
INSERT INTO cliente (nombre, apellidos, email, telefono) VALUES
( 'Carlos',  'García López',     'carlos.garcia@email.com',   '611223344 '),
( 'Laura',   'Martínez Ruiz',    'laura.martinez@email.com',  '622334455 '),
( 'Miguel',  'Fernández Sanz',   'miguel.fernandez@email.com', '633445566 '),
( 'Sofía',   'López Hernández',  'sofia.lopez@email.com',     '644556677 '),
( 'Andrés',  'Sánchez Mora',     'andres.sanchez@email.com',  '655667788 '),
( 'Elena',   'Díaz Romero',      'elena.diaz@email.com',      '666778899 ');

--PEDIDOS
INSERT INTO pedido (fecha, total, estado, id_cliente) VALUES
( '2024-03-01', 129.98, 'pagado',    1 ),
( '2024-03-05',  49.99, 'enviado',   2 ),
( '2024-03-10', 109.98, 'pagado',    3 ),
( '2024-03-15',  59.99, 'pendiente', 4 ),
( '2024-03-20',  89.98, 'pagado',    1 ),
( '2024-03-22',  79.99, 'enviado',   5 ),
( '2024-03-25', 149.97, 'pagado',    6 ),
( '2024-03-28',  49.99, 'cancelado', 2 );

--LINEAS DE PEDIDO
INSERT INTO linea_pedido (cantidad, precio_unidad, id_pedido, id_videojuego) VALUES
--Pedido 1: Carlos compra God of War + The Witcher 3
( 1, 69.99, 1,  1 ),
( 3, 19.99, 1,  2 ),
--Pedido 2: Laura compra Elden Ring
( 1, 49.99, 2,  5),
--Pedido 3: Miguel compra FIFA 24 + Resident Evil 4
( 1, 59.99, 3,  3 ),
( 1, 49.99, 3,  6),
--Pedido 4: Sofía compra Zelda
( 1, 59.99, 4,  4),
--Pedido 5: Carlos compra Age of Empires + Hogwarts Legacy
( 1, 39.99, 5,  7),
( 1, 49.99, 5, 10),
--Pedido 6: Andrés compra Spider-Man 2
( 1, 79.99, 6,  8),
--Pedido 7: Elena compra Mario Kart + Pikmin 4 + The Last of Us
( 1, 49.99, 7,  9),
( 1, 49.99, 7, 12 ),
( 1, 39.99, 7, 11 ),
--Pedido 8: Laura (cancelado) Zelda
( 1, 59.99, 8,  4);
