-- El nombre de este archivo debe respetarse ya que H2 lo reconoce para hacer la carga
-- CURRENT_TIMESTAMP fecha en la que arranca a correr la app
-- Los atributos de dos palabras deben escribirse con _ si o si

INSERT INTO players (id, user_name, password, email, avatar, last_login, created_at, updated_at)
             VALUES(1000000, 'APP',NULL, NULL, NULL, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO players (id, user_name, password, email, avatar, last_login, created_at, updated_at)
VALUES(100, 'Gera','Password03#', 'email@email.com', NULL, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO games (id, code, name, description, rules)
VALUES(1000000, 'RPS','Rock Paper Sissors','Rock, Paper, Scissors" is a hand game played between two people.' ||
' Each player simultaneously chooses one of three options: Rock (R), Paper (P), or Scissors (S).','Rock (R) beats Scissors (S).
Scissors (S) beat Paper (P).
Paper (P) beats Rock (R). If both players choose the same option, it''s a tie. Enjoy playing "Rock, Paper, Scissors" by sending your choice as "R", "P", or "S"!');




INSERT INTO matches (id, game_id, player_id, created_at, updated_at, status)
VALUES(1000000, 1000000,100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'STARTED');

INSERT INTO matches (id, game_id, player_id, created_at, updated_at, status)
VALUES(1000001, 1000000,100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'FINISHED');

INSERT INTO matches (id, game_id, player_id, created_at, updated_at, status)
VALUES(1000002, 1000000,100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'CANCELED');



INSERT INTO matches_rps (id, number_of_plays, remainder_plays, player1score, player2score)
VALUES(1000000, 10,5, 3, 2);

INSERT INTO matches_rps (id, number_of_plays, remainder_plays, player1score, player2score, winner_id)
VALUES(1000001, 10,0, 6, 4,100);

INSERT INTO matches_rps (id, number_of_plays, remainder_plays, player1score, player2score)
VALUES(1000002, 10,5, 3, 2);






INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000000, 1000000,'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000001, 1000000,'PAPER', 'ROCK',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000002, 1000000,'PAPER', 'ROCK',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000003, 1000000,'ROCK', 'SCISSORS', 100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000004, 1000000,'ROCK', 'PAPER', 1000000);



INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000010, 1000001,'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000011, 1000001,'PAPER', 'ROCK',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000012, 1000001,'PAPER', 'ROCK',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000013, 1000001,'ROCK', 'SCISSORS', 100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000014, 1000001,'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(100015, 1000001, 'PAPER','ROCK', 100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000016, 1000001,'PAPER', 'ROCK',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000017, 1000001,'ROCK', 'SCISSORS',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000018, 1000001,'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000019, 1000001,'ROCK', 'PAPER', 1000000);





INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000005, 1000002,'ROCK', 'PAPER',1000000);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000006, 1000002,'PAPER', 'ROCK',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000007, 1000002,'PAPER', 'ROCK',100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000008, 1000002,'ROCK', 'SCISSORS', 100);
INSERT INTO plays_rps (id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES(1000009, 1000002, 'ROCK', 'PAPER', 1000000);


