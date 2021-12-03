INSERT INTO usuarios(username, password, email) VALUES ('kikovilapavon', '123admin', 'fravilpav@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (1,'kikovilapavon','admin');
INSERT INTO usuarios(username, password, email) VALUES ('chemaccs', 'admin123', 'josconsan1@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (2,'chemaccs','admin');
INSERT INTO usuarios(username, password, email) VALUES ('Juangr4', '123', 'juagarrui@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (3,'Juangr4','admin');
INSERT INTO usuarios(username, password, email) VALUES ('martarl11', '1ad2min3', 'marreylop@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (4,'martarl11','admin');
INSERT INTO usuarios(username, password, email) VALUES ('miguegomgom', '4dm1n', 'miggomgom@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (5,'miguegomgom','admin');
INSERT INTO usuarios(username, password, email) VALUES ('mma12', '4dm1n123', 'marmarave@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (6,'mma12','admin');

INSERT INTO card_Types(id, iniciative, name) VALUES (1, 0, '0');
INSERT INTO card_Types(id, iniciative, name) VALUES (2, 1, '1');
INSERT INTO card_Types(id, iniciative, name) VALUES (3, 2, '2');
INSERT INTO card_Types(id, iniciative, name) VALUES (4, 2, '2b');
INSERT INTO card_Types(id, iniciative, name) VALUES (5, 3, '3');
INSERT INTO card_Types(id, iniciative, name) VALUES (6, 4, '4');
INSERT INTO card_Types(id, iniciative, name) VALUES (7, 5, '5');
INSERT INTO card_Types(id, iniciative, name) VALUES (8, -1, 'start'); -- Esta representa la carta de salida

INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'NORTH');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'WEST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (2, 'NORTH');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (3, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (4, 'WEST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (8, 'NORTH');

--INSERT INTO Statistics(id, duration, numGames, numPlayers, usuario_username) VALUES (1, 15, 25, 4, 'kikovilapavon');
INSERT INTO achievements(id, name, type, description) VALUES (1, 'Jugar 5 partidas', 'BRONCE', 'el jugador debe haber jugado 5 partidas para poder conseguir este logro' );
INSERT INTO achievements(id, name, type, description) VALUES (2, 'Jugar 10 partidas', 'BRONCE', 'el jugador debe haber jugado 10 partidas para poder conseguir este logro' );
INSERT INTO achievements(id, name, type, description) VALUES (3, 'Jugar 50 partidas', 'PLATA', 'el jugador debe haber jugado 50 partidas para poder conseguir este logro' );
