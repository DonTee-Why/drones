INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 405, 68, 'HEAVYWEIGHT', 'IDLE');
INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 234, 33, 'CRUISERWEIGHT', 'LOADING');
INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 145, 76, 'MIDDLEWEIGHT', 'LOADED');

INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_123', 100, 'MED_1', 'med1.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_124', 35, 'MED_12', 'med2.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_122', 50, 'MED_3', 'med4.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_14', 19, 'MED_4', 'med6.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_13', 93, 'MED_5', 'med5.jpg');
