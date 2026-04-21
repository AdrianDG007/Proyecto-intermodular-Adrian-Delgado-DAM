--Datos de prueba para la tienda

USE tienda_videojuegos;

INSERT INTO categoria (nombre, descripcion) VALUES
('Acción',     'Juegos de acción y combate en tiempo real'   ),
('RPG',        'Juegos de rol con desarrollo de personaje'   ),
('Deportes',   'Simuladores y competición deportiva'         ),
('Aventura',   'Exploración y resolución de puzzles'         ),
('Estrategia', 'Planificación táctica y gestión de recursos' ),
('Terror',     'Horror y supervivencia'                      );

INSERT INTO proveedor (nombre, telefono, email) VALUES
('Koch Media España', '912345678', 'pedidos@kochmedia.es'  ),
('Bandai Namco ES',   '934567890', 'ventas@bandainamco.es' ),
('Sony Interactive',  '915678901', 'distribucion@sony.es'  ),
('Nintendo España',   '916789012', 'pedidos@nintendo.es'   );

INSERT INTO videojuego (titulo, plataforma, precio, stock, id_categoria, id_proveedor) VALUES
('God of War Ragnarok',         'PS5',    69.99, 15, 1, 3 ),
('The Witcher 3',               'PC',     29.99, 30, 2, 1 ),
('FIFA 24',                     'PS5',    59.99, 20, 3, 1 ),
('Zelda: Tears of the Kingdom', 'Switch', 59.99, 25, 4, 4 ),
('Elden Ring',                  'PC',     49.99, 18, 2, 2 ),
('Resident Evil 4',             'PS5',    49.99, 12, 6, 3 ),
('Age of Empires IV',           'PC',     39.99, 10, 5, 1 ),
('Spider-Man 2',                'PS5',    59.99,  8, 1, 3 ),
('Mario Kart 8 Deluxe',         'Switch', 49.99, 22, 3, 4 ),
('Hogwarts Legacy',             'PC',     49.99, 14, 2, 2 ),
('The Last of Us Part I',       'PC',     39.99,  9, 1, 3 ),
('Pikmin 4',                    'Switch', 49.99, 11, 4, 4 );

INSERT INTO cliente (nombre, apellidos, email, telefono ) VALUES
('Carlos', 'Garcia Lopez',     'carlos.garcia@email.com',    '611223344' ),
('Laura',  'Martinez Ruiz',    'laura.martinez@email.com',   '622334455' ),
('Miguel', 'Fernandez Sanz',   'miguel.fernandez@email.com', '633445566' ),
('Sofia',  'Lopez Hernandez',  'sofia.lopez@email.com',      '644556677' ),
('Andres', 'Sanchez Mora',     'andres.sanchez@email.com',   '655667788' ),
('Elena',  'Diaz Romero',      'elena.diaz@email.com',       '666778899' );

INSERT INTO pedido (fecha, total, estado, id_cliente ) VALUES
('2024-03-01', 129.98, 'pagado',    1 ),
('2024-03-05',  49.99, 'enviado',   2 ),
('2024-03-10', 109.98, 'pagado',    3 ),
('2024-03-15',  59.99, 'pendiente', 4 ),
('2024-03-20',  89.98, 'pagado',    1 ),
('2024-03-22',  59.99, 'enviado',   5 ),
('2024-03-25', 149.97, 'pagado',    6 ),
('2024-03-28',  49.99, 'cancelado', 2 );

INSERT INTO linea_pedido (cantidad, precio_unidad, id_pedido, id_videojuego) VALUES
-- pedido 1: God of War + 3x Witcher
(1, 69.99, 1,  1 ),
(3, 29.99, 1,  2 ),
-- pedido 2: Elden Ring
(1, 49.99, 2,  5 ),
-- pedido 3: FIFA + Resident Evil
(1, 59.99, 3,  3 ),
(1, 49.99, 3,  6 ),
-- pedido 4: Zelda
(1, 59.99, 4,  4 ),
-- pedido 5: Age of Empires + Hogwarts
(1, 39.99, 5,  7 ),
(1, 49.99, 5, 10 ),
-- pedido 6: Spider-Man 2
(1, 59.99, 6,  8 ),
-- pedido 7: Mario Kart + Pikmin + Last of Us
(1, 49.99, 7,  9 ),
(1, 49.99, 7, 12 ),
(1, 39.99, 7, 11 ),
-- pedido 8: cancelado
(1, 59.99, 8,  4 );