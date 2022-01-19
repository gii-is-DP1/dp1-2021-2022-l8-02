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
--Usuarios de prueba
INSERT INTO usuarios(username, password, email) VALUES ('a', 'a', 'a@a.a');
INSERT INTO authorities(id, username, authority) VALUES (7, 'a', 'admin');
INSERT INTO usuarios(username, password, email) VALUES ('b', 'b', 'b@b.b');
INSERT INTO authorities(id, username, authority) VALUES (8, 'b', 'admin');
INSERT INTO usuarios(username, password, email) VALUES ('c', 'c', 'c@c.c');
INSERT INTO authorities(id, username, authority) VALUES (9, 'c', 'admin');
INSERT INTO usuarios(username, password, email) VALUES ('d', 'd', 'd@d.d');
INSERT INTO authorities(id, username, authority) VALUES (10, 'd', 'admin');

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


INSERT INTO powers(id, name) VALUES(1, 'Aceleron');
INSERT INTO powers(id, name) VALUES(2, 'Frenazo');
INSERT INTO powers(id, name) VALUES(3, 'Marcha atras');
INSERT INTO powers(id, name) VALUES(4, 'Gas extra');

INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'kikovilapavon');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'chemaccs');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'Juangr4');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'martarl11');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'miguegomgom');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'mma12');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'a');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'b');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'c');
INSERT INTO Statistics(duration, num_games, num_players, usuario_username) VALUES (0, 0, 0, 'd');

/*
INSERT INTO Games(id,game_mode,game_state,hidden,name) VALUES (1,'VERSUS','LOBBY',FALSE,'eightplayergame');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'kikovilapavon');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'miguegomgom');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'chemaccs');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'Juangr4');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'martarl11');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'mma12');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'd');
INSERT INTO Games_players(game_id,players_username) VALUES (1, 'c');
*/


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

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES --Este sería el puzzle 60 del pdf
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

--Este de abajo sería el 065 pdf

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (61, 2, 2, 8),
    (61, 1, 3, 9),
    (61, 3, 3, 9),
    (61, 4, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (62, 2, 2, 8),
    (62, 2, 3, 9),
    (62, 3, 3, 9),
    (62, 4, 4, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (63, 2, 2, 8),
    (63, 0, 4, 9),
    (63, 1, 3, 9),
    (63, 2, 3, 9);

INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (64, 2, 2, 8),
    (64, 0, 4, 9),
    (64, 1, 3, 9),
    (64, 2, 3, 9);
    
    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES --Este sería el 69
    (65, 2, 2, 8),
    (65, 0, 4, 9),
    (65, 1, 3, 9),
    (65, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (66, 2, 2, 8),
    (66, 0, 4, 9),
    (66, 1, 3, 9),
    (66, 3, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (67, 2, 2, 8),
    (67, 1, 1, 9),
    (67, 3, 3, 9),
    (67, 4, 4, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (68, 2, 2, 8),
    (68, 1, 2, 9),
    (68, 3, 3, 9),
    (68, 4, 4, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES --73
    (69, 2, 2, 8),
    (69, 0, 3, 9),
    (69, 1, 3, 9),
    (69, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (70, 2, 2, 8),
    (70, 0, 3, 9),
    (70, 1, 3, 9),
    (70, 3, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (71, 2, 2, 8),
    (71, 1, 1, 9),
    (71, 3, 3, 9),
    (71, 4, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (72, 2, 2, 8),
    (72, 1, 2, 9),
    (72, 3, 3, 9),
    (72, 4, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 77
    (73, 2, 2, 8),
    (73, 0, 2, 9),
    (73, 1, 3, 9),
    (73, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (74, 2, 2, 8),
    (74, 0, 2, 9),
    (74, 1, 3, 9),
    (74, 3, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (75, 2, 2, 8),
    (75, 1, 1, 9),
    (75, 3, 3, 9),
    (75, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (76, 2, 2, 8),
    (76, 1, 2, 9),
    (76, 3, 3, 9),
    (76, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 81
    (77, 2, 2, 8),
    (77, 3, 2, 9),
    (77, 4, 2, 9),
    (77, 4, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (78, 2, 2, 8),
    (78, 1, 1, 9),
    (78, 3, 2, 9),
    (78, 4, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (79, 2, 2, 8),
    (79, 0, 3, 9),
    (79, 1, 2, 9),
    (79, 3, 1, 9);
/*
    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (80, 2, 2, 8),
    (80, 0, 2, 9),
    (80, 1, 2, 9),
    (80, 0, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 85
    (81, 2, 2, 8),
    (81, 1, 2, 9),
    (81, 3, 2, 9),
    (81, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (82, 2, 2, 8),
    (82, 1, 3, 9),
    (82, 3, 2, 9),
    (82, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (83, 2, 2, 8),
    (83, 0, 2, 9),
    (83, 1, 2, 9),
    (83, 3, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (84, 2, 2, 8),
    (84, 0, 2, 9),
    (84, 1, 2, 9),
    (84, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 89
    (85, 2, 2, 8),
    (85, 2, 3, 9),
    (85, 3, 2, 9),
    (85, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (86, 2, 2, 8),
    (86, 3, 2, 9),
    (86, 3, 3, 9),
    (86, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (87, 2, 2, 8),
    (87, 1, 2, 9),
    (87, 1, 3, 9),
    (87, 0, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (88, 2, 2, 8),
    (88, 1, 2, 9),
    (88, 2, 3, 9),
    (88, 0, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 93
    (89, 2, 2, 8),
    (89, 4, 2, 9),
    (89, 4, 3, 9),
    (89, 3, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (90, 2, 2, 8),
    (90, 1, 1, 9),
    (90, 3, 2, 9),
    (90, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (91, 2, 2, 8),
    (91, 0, 1, 9),
    (91, 1, 2, 9),
    (91, 3, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (92, 2, 2, 8),
    (92, 1, 1, 9),
    (92, 0, 2, 9),
    (92, 0, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 97
    (93, 2, 2, 8),
    (93, 0, 3, 9),
    (93, 1, 2, 9),
    (93, 3, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (94, 2, 2, 8),
    (94, 0, 3, 9),
    (94, 1, 2, 9),
    (94, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (95, 2, 2, 8),
    (95, 1, 2, 9),
    (95, 4, 3, 9),
    (95, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (96, 2, 2, 8),
    (96, 1, 3, 9),
    (96, 4, 3, 9),
    (96, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 101
    (97, 2, 2, 8),
    (97, 0, 2, 9),
    (97, 1, 2, 9),
    (97, 1, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (98, 2, 2, 8),
    (98, 0, 2, 9),
    (98, 1, 2, 9),
    (98, 2, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (99, 2, 2, 8),
    (99, 2, 3, 9),
    (99, 3, 2, 9),
    (99, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (100, 2, 2, 8),
    (100, 3, 3, 9),
    (100, 3, 2, 9),
    (100, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 105
    (101, 2, 2, 8),
    (101, 0, 2, 9),
    (101, 1, 2, 9),
    (101, 3, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (102, 2, 2, 8),
    (102, 0, 1, 9),
    (102, 0, 3, 9),
    (102, 1, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (103, 2, 2, 8),
    (103, 4, 1, 9),
    (103, 3, 2, 9),
    (103, 4, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (104, 2, 2, 8),
    (104, 1, 1, 9),
    (104, 3, 2, 9),
    (104, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 109
    (105, 2, 2, 8),
    (105, 0, 1, 9),
    (105, 1, 2, 9),
    (105, 3, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (106, 2, 2, 8),
    (106, 0, 1, 9),
    (106, 1, 2, 9),
    (106, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (107, 2, 2, 8),
    (107, 1, 2, 9),
    (107, 4, 1, 9),
    (107, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (108, 2, 2, 8),
    (108, 1, 3, 9),
    (108, 3, 2, 9),
    (108, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 112
    (109, 2, 2, 8),
    (109, 0, 2, 9),
    (109, 1, 1, 9),
    (109, 2, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (110, 2, 2, 8),
    (110, 0, 2, 9),
    (110, 1, 1, 9),
    (110, 3, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (111, 2, 2, 8),
    (111, 1, 3, 9),
    (111, 3, 1, 9),
    (111, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (112, 2, 2, 8),
    (112, 2, 3, 9),
    (112, 3, 1, 9),
    (112, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 116
    (113, 2, 2, 8),
    (113, 0, 1, 9),
    (113, 1, 1, 9),
    (113, 1, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (114, 2, 2, 8),
    (114, 0, 1, 9),
    (114, 1, 1, 9),
    (114, 1, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (115, 2, 2, 8),
    (115, 3, 1, 9),
    (115, 3, 3, 9),
    (115, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (116, 2, 2, 8),
    (116, 3, 1, 9),
    (116, 3, 2, 9),
    (116, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 121
    (117, 2, 2, 8),
    (117, 0, 1, 9),
    (117, 3, 2, 9),
    (117, 1, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (118, 2, 2, 8),
    (118, 0, 1, 9),
    (118, 3, 1, 9),
    (118, 1, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (119, 2, 2, 8),
    (119, 1, 1, 9),
    (119, 3, 1, 9),
    (119, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (120, 2, 2, 8),
    (120, 1, 2, 9),
    (120, 3, 1, 9),
    (120, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 124
    (121, 2, 2, 8),
    (121, 0, 0, 9),
    (121, 1, 1, 9),
    (121, 2, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (122, 2, 2, 8),
    (122, 0, 0, 9),
    (122, 1, 1, 9),
    (122, 3, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (123, 2, 2, 8),
    (123, 1, 3, 9),
    (123, 3, 1, 9),
    (123, 4, 0, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (124, 2, 2, 8),
    (124, 2, 3, 9),
    (124, 3, 1, 9),
    (124, 4, 0, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 128
    (125, 2, 2, 8),
    (125, 1, 1, 9),
    (125, 3, 1, 9),
    (125, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (126, 2, 2, 8),
    (126, 1, 2, 9),
    (126, 3, 1, 9),
    (126, 4, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (127, 2, 2, 8),
    (127, 0, 2, 9),
    (127, 3, 2, 9),
    (127, 1, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (128, 2, 2, 8),
    (128, 0, 2, 9),
    (128, 3, 1, 9),
    (128, 1, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 132
    (129, 2, 2, 8),
    (129, 1, 3, 9),
    (129, 3, 1, 9),
    (129, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (130, 2, 2, 8),
    (130, 2, 3, 9),
    (130, 3, 1, 9),
    (130, 4, 1, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (131, 2, 2, 8),
    (131, 0, 1, 9),
    (131, 1, 1, 9),
    (131, 2, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (132, 2, 2, 8),
    (132, 0, 1, 9),
    (132, 1, 1, 9),
    (132, 3, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 136
    (133, 2, 2, 8),
    (133, 3, 1, 9),
    (133, 3, 3, 9),
    (133, 4, 0, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (134, 2, 2, 8),
    (134, 3, 1, 9),
    (134, 3, 2, 9),
    (134, 4, 0, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (135, 2, 2, 8),
    (135, 0, 0, 9),
    (135, 1, 1, 9),
    (135, 1, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (136, 2, 2, 8),
    (136, 0, 0, 9),
    (136, 1, 1, 9),
    (136, 1, 3, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES -- 140
    (137, 2, 2, 8),
    (137, 1, 1, 9),
    (137, 3, 1, 9),
    (137, 4, 0, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (138, 2, 2, 8),
    (138, 1, 2, 9),
    (138, 3, 1, 9),
    (138, 4, 0, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (139, 2, 2, 8),
    (139, 0, 0, 9),
    (139, 1, 1, 9),
    (139, 3, 2, 9);

    INSERT INTO Puzzles_Tiles(puzzle_id, x, y, card_type_id) VALUES 
    (140, 2, 2, 8),
    (140, 0, 0, 9),
    (140, 1, 1, 9),
    (140, 3, 1, 9);

*/