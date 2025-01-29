-- DELETE FROM Notes;
-- DELETE FROM Users;

INSERT INTO Users(name, password) VALUES ('user', '$2a$12$1p.0D/V5ZDP.EJduZvUKi.2N5e9yGLz5Hficm7a49mNod9dzIPqfu');
INSERT INTO Users(name, password)  VALUES  ('bunny', '$2a$12$tO.TE/U52rNwm0tJxkOU4un16/HzJfqgx3b/z3nsLhmHhg/.QJEzy');
INSERT INTO Users(name, password) VALUES ('panda', '$2a$12$GiiSW/nPU5mnPbEeIIIClOBeEg01JPn/0OHgHwatPwNx3EH7WANgq');

INSERT INTO Notes(userId, title, note) VALUES (2, "Hello world!", 'Hello world, my name is bunny love and I am leaving this message for everyone');
INSERT INTO Notes(userId, title, note) VALUES (2, "Cleaning Tasks", 'Make sure to clean up simba litter box');
INSERT INTO Notes(userId, title, note) VALUES (2, "Don't forget tasks!", 'Pay my bills today or else I get a late fee');
