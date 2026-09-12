--UTENTI
INSERT INTO utente (id, name, surname) VALUES (0, 'admin', 'admin') ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, name, surname) VALUES (1, 'jonny', 'test') ON CONFLICT (id) DO NOTHING;
--REGISTA
INSERT INTO regista (id, name,surname, country,birth) VALUES (0,'Henry','Orrore','Usa','1/2/93') ON CONFLICT (id) DO NOTHING;
INSERT INTO regista (id, name,surname, country,birth) VALUES (1,'Carlo','Commadia','Italy','5/11/03') ON CONFLICT (id) DO NOTHING;
INSERT INTO regista (id, name,surname, country,birth) VALUES (2,'Anitha','Azione','Uk','28/6/78') ON CONFLICT (id) DO NOTHING;
--SALE
INSERT INTO sala (id, name, address, capacity) VALUES (0,'Absalom films','via Roma 13',50) ON CONFLICT (id) DO NOTHING;
INSERT INTO sala (id, name, address, capacity) VALUES (1,'Cinetastic','via fausto coppi 27a',80) ON CONFLICT (id) DO NOTHING;
INSERT INTO sala (id, name, address, capacity) VALUES (2,'Grand Theater','largo stringber 129',120) ON CONFLICT (id) DO NOTHING;
--FILM
INSERT INTO film (id, title, year, length, genre, country, director_id) VALUES (0,'Il ritorno',2025,'1:30','Horror','Usa',0) ON CONFLICT (id) DO NOTHING;
INSERT INTO film (id, title, year, length, genre, country, director_id) VALUES (1,'La sala dei coltelli',2018,'0:50','Horror','Uk',0) ON CONFLICT (id) DO NOTHING;
INSERT INTO film (id, title, year, length, genre, country, director_id) VALUES (2,'Una strana giornata',2026,'1:40','Comedy','France',1) ON CONFLICT (id) DO NOTHING;
INSERT INTO film (id, title, year, length, genre, country, director_id) VALUES (3,'Tactical assoult',1999,'2:20','Action','Usa',2) ON CONFLICT (id) DO NOTHING;
INSERT INTO film (id, title, year, length, genre, country, director_id) VALUES (4,'Calibro 45',2004,'2:00','Action','Usa',2) ON CONFLICT (id) DO NOTHING;
--FESTIVAL
INSERT INTO festival (id, name, city, year, description, starting_date, ending_date) VALUES (0,'Tutti in sala','Corcumello',2026,'Festival del cinema internazionale di Corcumello e dintorni 26esima edizione','10 settembre','15 ottobre') ON CONFLICT (id) DO NOTHING;
INSERT INTO festival (id, name, city, year, description, starting_date, ending_date) VALUES (1,'Cinema summer festival','Avezzano',2026,'Festival del cinema estivo','1 luglio','21 settembre') ON CONFLICT (id) DO NOTHING;
--RECENSIONI
INSERT INTO recensione (id, text, vote, date,user_id,film_id) VALUES (0,'bello, ma troppo mentale e poca azione',3,'3/11/2025',1,0) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, text, vote, date,user_id,film_id) VALUES (1,'solo jumpscare visti e stravisti, evitabile e dimenticabile',1,'4/11/2025',0,0) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, text, vote, date,user_id,film_id) VALUES (2,'mi ha fatto ricredere sul genere',4,'24/6/2019',1,1) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, text, vote, date,user_id,film_id) VALUES (3,'divertente e leggera',4,'30/4/2026',0,2) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, text, vote, date,user_id,film_id) VALUES (4,'esattamente quello che serviva alla scena action, Anitha non delude mai',5,'6/6/2006',1,4) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, text, vote, date,user_id,film_id) VALUES (5,'film onesto, non un capolavoro, ma fa passare due ore intrattenendo bene',3,'7/8/2014',0,4) ON CONFLICT (id) DO NOTHING;
--PROIEZIONI
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (0,'11 set','19:50','COMPLETED',0,0,0) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (1,'18 set','22:00','SCHEDULED',1,1,0) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (2,'21 set','20:25','SCHEDULED',1,2,0) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (3,'13 set','16:45','COMPLETED',0,0,1) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (4,'10 ott','21:30','CANCELLED',0,0,1) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (5,'20 set','23:00','SCHEDULED',1,1,1) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (6,'30 lug','10:30','COMPLETED',1,2,2) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (7,'13 lug','15:20','COMPLETED',1,1,2) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (8,'28 set','16:50','SCHEDULED',0,0,2) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (9,'10 set','13:45','COMPLETED',0,0,3) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (10,'4 ott','20:00','SCHEDULED',0,0,3) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (11,'7 ago','9:00','COMPLETED',1,2,3) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (12,'17 ago','18:45','COMPLETED',1,2,4) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (13,'26 ago','19:15','CANCELLED',1,1,4) ON CONFLICT (id) DO NOTHING;
INSERT INTO proiezione (id, date, time, state, festival_id, room_id, film_id) VALUES (14,'20 set','11:40','SCHEDULED',0,0,4) ON CONFLICT (id) DO NOTHING;

-- film<->festival
INSERT INTO film_festival (film_id,festival_id) VALUES (0,0);
INSERT INTO film_festival (film_id,festival_id) VALUES (1,0);
INSERT INTO film_festival (film_id,festival_id) VALUES (2,0);
INSERT INTO film_festival (film_id,festival_id) VALUES (3,0);
INSERT INTO film_festival (film_id,festival_id) VALUES (4,0);
INSERT INTO film_festival (film_id,festival_id) VALUES (0,1);
INSERT INTO film_festival (film_id,festival_id) VALUES (1,1);
INSERT INTO film_festival (film_id,festival_id) VALUES (2,1);
INSERT INTO film_festival (film_id,festival_id) VALUES (3,1);
INSERT INTO film_festival (film_id,festival_id) VALUES (4,1);
--CREDENZIALI
INSERT INTO credenziali (id, username, password, user_role, user_id) VALUES (0, 'kylix', '$2a$12$nyfe3EpguXvYduWqt46YbuzkNvgPAelIboSi2WT.DXE0OL7XWSI.G', 'ADMIN', 0) ON CONFLICT (id) DO NOTHING;
INSERT INTO credenziali (id, username, password, user_role, user_id) VALUES (1, 'test', '$2a$12$nyfe3EpguXvYduWqt46YbuzkNvgPAelIboSi2WT.DXE0OL7XWSI.G', 'USER', 1) ON CONFLICT (id) DO NOTHING;


SELECT setval('utente_seq', GREATEST(COALESCE((SELECT MAX(id) FROM utente), 0) + 1, 20), false);
SELECT setval('recensione_seq', GREATEST(COALESCE((SELECT MAX(id) FROM recensione), 0) + 1, 20), false);
SELECT setval('film_seq', GREATEST(COALESCE((SELECT MAX(id) FROM film), 0) + 1, 20), false);
SELECT setval('regista_seq', GREATEST(COALESCE((SELECT MAX(id) FROM regista), 0) + 1, 20), false);
SELECT setval('proiezione_seq', GREATEST(COALESCE((SELECT MAX(id) FROM proiezione), 0) + 1, 20), false);
SELECT setval('festival_seq', GREATEST(COALESCE((SELECT MAX(id) FROM festival), 0) + 1, 20), false);
SELECT setval('sala_seq', GREATEST(COALESCE((SELECT MAX(id) FROM sala), 0) + 1, 20), false);
SELECT setval('credenziali_seq', GREATEST(COALESCE((SELECT MAX(id) FROM credenziali), 0) + 1, 20), false);