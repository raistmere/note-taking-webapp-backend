DELETE FROM Users;
DELETE FROM Notes;

INSERT INTO Users(id, name, password) VALUES (101, 'user', '$2a$12$1p.0D/V5ZDP.EJduZvUKi.2N5e9yGLz5Hficm7a49mNod9dzIPqfu');
INSERT INTO Users(id, name, password)  VALUES  (102, 'bunny', '$2a$12$tO.TE/U52rNwm0tJxkOU4un16/HzJfqgx3b/z3nsLhmHhg/.QJEzy');
INSERT INTO Users(id, name, password) VALUES (103, 'panda', '$2a$12$GiiSW/nPU5mnPbEeIIIClOBeEg01JPn/0OHgHwatPwNx3EH7WANgq');

INSERT INTO Notes(id, userId, note) VALUES (301, 102, 'Hello world, my name is bunny love and I am leaving this message for everyone');
INSERT INTO Notes(id, userId, note) VALUES (302, 102, 'Make sure to clean up simba litter box');
INSERT INTO Notes(id, userId, note) VALUES (303, 102, 'Pay my bills today or else I get a late fee');
