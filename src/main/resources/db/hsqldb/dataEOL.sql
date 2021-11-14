INSERT INTO usuarios(username, password, email) VALUES ('kikovilapavon', '123admin', 'fravilpav@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (1,'kikovilapavon','admin');
INSERT INTO usuarios(username, password, email) VALUES ('chemaccs', 'admin123', 'josconsan1@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (2,'chemaccs','admin');
INSERT INTO usuarios(username, password, email) VALUES ('juagarrui', '3ad1min2', 'juagarrui@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (3,'juagarrui','admin');
INSERT INTO usuarios(username, password, email) VALUES ('martarl11', '1ad2min3', 'marreylop@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (4,'martarl11','admin');
INSERT INTO usuarios(username, password, email) VALUES ('miguegomgom', '4dm1n', 'miggomgom@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (5,'miguegomgom','admin');
INSERT INTO usuarios(username, password, email) VALUES ('mma12', '4dm1n123', 'marmarave@alum.us.es');
INSERT INTO authorities(id,username,authority) VALUES (6,'mma12','admin');

INSERT INTO card_Types(id, iniciative) VALUES (1, 0);
INSERT INTO card_Types(id, iniciative) VALUES (2, 1);
INSERT INTO card_Types(id, iniciative) VALUES (3, 2);
INSERT INTO card_Types(id, iniciative) VALUES (4, 2);
INSERT INTO card_Types(id, iniciative) VALUES (5, 3);
INSERT INTO card_Types(id, iniciative) VALUES (6, 4);
INSERT INTO card_Types(id, iniciative) VALUES (7, 5);

INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'NORTH');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'EAST');
INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (1, 'WEST');

INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (2, 'NORTH');

INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (3, 'EAST');

INSERT INTO card_Type_Directions(card_type_id, directions) VALUES (4, 'WEST');