INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 405, 68, 'HEAVYWEIGHT', 'IDLE');
INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 234, 33, 'CRUISERWEIGHT', 'LOADING');
INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 145, 76, 'MIDDLEWEIGHT', 'LOADED');
INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 35, 18, 'LIGHTWEIGHT', 'IDLE');
INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 90, 25, 'LIGHTWEIGHT', 'IDLE');
INSERT INTO drones (serial_number, weight_limit, battery, model, state) VALUES ((SELECT UUID()), 36, 100, 'LIGHTWEIGHT', 'DELIVERING');

INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_123', 100, 'MED_1', 'med1.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_124', 35, 'MED_12', 'med2.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_122', 50, 'MED_3', 'med4.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_14', 19, 'MED_4', 'med6.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_13', 93, 'MED_5', 'med5.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_23', 152, 'MED_6', 'med7.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_52', 400, 'MED_7', 'med8.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_111', 123, 'MED_8', 'med9.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_22', 327, 'MED_9', 'med10.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_12', 209, 'MED_10', 'med11.jpg');
INSERT INTO medications (name, weight, code, image) VALUES ('Medicine_3', 508, 'MED_11', 'med12.jpg');
