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
INSERT INTO card_Types(id, iniciative, name) VALUES (9, -2, 'wall');

INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'NORTH');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'WEST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (2, 'NORTH');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (3, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (4, 'WEST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (5, 'NORTH');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (5, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (6, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (6, 'WEST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (7, 'NORTH');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (7, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (7, 'WEST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (8, 'NORTH');

--INSERT INTO Statistics(id, duration, numGames, numPlayers, usuario_username) VALUES (1, 15, 25, 4, 'kikovilapavon');
INSERT INTO achievements(id, name, type, description) VALUES (1, 'Jugar 5 partidas', 'BRONCE', 'el jugador debe haber jugado 5 partidas para poder conseguir este logro' );
INSERT INTO achievements(id, name, type, description) VALUES (2, 'Jugar 10 partidas', 'BRONCE', 'el jugador debe haber jugado 10 partidas para poder conseguir este logro' );
INSERT INTO achievements(id, name, type, description) VALUES (3, 'Jugar 50 partidas', 'PLATA', 'el jugador debe haber jugado 50 partidas para poder conseguir este logro' );

INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'kikovilapavon');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'chemaccs');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'Juangr4');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'martarl11');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'miguegomgom');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'mma12');





-- PUZZLES

-- Yellow
INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (1, 2, 2, 8);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (2, 2, 2, 8),
    (2, 2, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (3, 2, 2, 8),
    (3, 1, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (4, 2, 2, 8),
    (4, 1, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (5, 2, 2, 8),
    (5, 1, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (6, 2, 2, 8),
    (6, 3, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (7, 2, 2, 8),
    (7, 3, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (8, 2, 2, 8),
    (8, 3, 3, 9);

-- Orange
INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (9, 2, 2, 8),
    (9, 1, 2, 9),
    (9, 3, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (10, 2, 2, 8),
    (10, 1, 1, 9),
    (10, 3, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (11, 2, 2, 8),
    (11, 1, 1, 9),
    (11, 3, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (12, 2, 2, 8),
    (12, 1, 3, 9),
    (12, 3, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (13, 2, 2, 8),
    (13, 1, 3, 9),
    (13, 2, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (14, 2, 2, 8),
    (14, 1, 3, 9),
    (14, 3, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (15, 2, 2, 8),
    (15, 1, 2, 9),
    (15, 3, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (16, 2, 2, 8),
    (16, 1, 1, 9),
    (16, 3, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (17, 2, 2, 8),
    (17, 1, 3, 9),
    (17, 3, 2, 9);

-- Green
INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (18, 2, 2, 8),
    (18, 2, 3, 9),
    (18, 2, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (19, 2, 2, 8),
    (19, 2, 3, 9),
    (19, 1, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (20, 2, 2, 8),
    (20, 2, 3, 9),
    (20, 3, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (21, 2, 2, 8),
    (21, 1, 1, 9),
    (21, 1, 0, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (22, 2, 2, 8),
    (22, 1, 1, 9),
    (22, 2, 0, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (23, 2, 2, 8),
    (23, 3, 1, 9),
    (23, 2, 0, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (24, 2, 2, 8),
    (24, 3, 1, 9),
    (24, 3, 0, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (25, 2, 2, 8),
    (25, 1, 3, 9),
    (25, 1, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (26, 2, 2, 8),
    (26, 1, 3, 9),
    (26, 2, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (27, 2, 2, 8),
    (27, 3, 3, 9),
    (27, 2, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (28, 2, 2, 8),
    (28, 3, 3, 9),
    (28, 3, 4, 9);

-- Red
INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (29, 2, 2, 8),
    (29, 3, 1, 9),
    (29, 4, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (30, 2, 2, 8),
    (30, 3, 2, 9),
    (30, 4, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (31, 2, 2, 8),
    (31, 0, 1, 9),
    (31, 1, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (32, 2, 2, 8),
    (32, 1, 1, 9),
    (32, 0, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (33, 2, 2, 8),
    (33, 3, 3, 9),
    (33, 4, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (34, 2, 2, 8),
    (34, 3, 3, 9),
    (34, 4, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (35, 2, 2, 8),
    (35, 1, 3, 9),
    (35, 0, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (36, 2, 2, 8),
    (36, 1, 3, 9),
    (36, 0, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (37, 2, 2, 8),
    (37, 1, 3, 9),
    (37, 4, 0, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (38, 2, 2, 8),
    (38, 3, 1, 9),
    (38, 4, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (39, 2, 2, 8),
    (39, 1, 1, 9),
    (39, 0, 1, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (40, 2, 2, 8),
    (40, 1, 1, 9),
    (40, 0, 0, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (41, 2, 2, 8),
    (41, 3, 2, 9),
    (41, 4, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (42, 2, 2, 8),
    (42, 3, 2, 9),
    (42, 4, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (43, 2, 2, 8),
    (43, 1, 2, 9),
    (43, 0, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (44, 2, 2, 8),
    (44, 1, 2, 9),
    (44, 0, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (45, 2, 2, 8),
    (45, 3, 3, 9),
    (45, 4, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (46, 2, 2, 8),
    (46, 1, 3, 9),
    (46, 0, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (47, 2, 2, 8),
    (47, 2, 1, 4),
    (47, 1, 3, 9),
    (47, 0, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (48, 2, 2, 8),
    (48, 2, 1, 3),
    (48, 3, 3, 9),
    (48, 4, 4, 9);

-- Blue
INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (49, 2, 2, 8),
    (49, 1, 3, 9),
    (49, 3, 3, 9),
    (49, 4, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (50, 2, 2, 8),
    (50, 2, 3, 9),
    (50, 3, 3, 9),
    (50, 4, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (51, 2, 2, 8),
    (51, 1, 3, 9),
    (51, 2, 3, 9),
    (51, 0, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (52, 2, 2, 8),
    (52, 1, 3, 9),
    (52, 3, 3, 9),
    (52, 0, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (53, 2, 2, 8),
    (53, 1, 3, 9),
    (53, 3, 3, 9),
    (53, 4, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (54, 2, 2, 8),
    (54, 2, 3, 9),
    (54, 3, 3, 9),
    (54, 4, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (55, 2, 2, 8),
    (55, 1, 3, 9),
    (55, 2, 3, 9),
    (55, 0, 2, 9);

-- Faltan 56, 57, 58, 59
-- Es decir a partir de aqui para mapear con el pdf habra que sumarle 4 al id

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (56, 2, 2, 8),
    (56, 1, 3, 9),
    (56, 3, 3, 9),
    (56, 0, 2, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (57, 2, 2, 8),
    (57, 3, 2, 9),
    (57, 2, 3, 9),
    (57, 4, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (58, 2, 2, 8),
    (58, 3, 2, 9),
    (58, 3, 3, 9),
    (58, 4, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (59, 2, 2, 8),
    (59, 1, 2, 9),
    (59, 1, 3, 9),
    (59, 0, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (60, 2, 2, 8),
    (60, 1, 2, 9),
    (60, 2, 3, 9),
    (60, 0, 3, 9);