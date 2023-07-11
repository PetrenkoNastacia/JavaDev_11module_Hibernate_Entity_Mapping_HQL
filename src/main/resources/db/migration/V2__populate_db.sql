INSERT INTO client (name) VALUES
	('Anastasiia'),
	('Boris'),
	('Daria'),
	('Iryna'),
	('Peter'),
	('Andrew'),
	('Roman'),
	('Fernando'),
	('Renat'),
	('Annabell')
	;

INSERT INTO planet (id, name) VALUES
	('FIR', 'PlanetFirst'),
	('SEC', 'PlanetSecond'),
	('THI', 'PlanetThird'),
	('FOU', 'PlanetFourth'),
	('FIF', 'PlanetFifth')
	;

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
    ('2020-09-09 22:11:56', 1, 'FIR', 'SEC'),
    ('2013-03-10 04:36:24', 2, 'FIR', 'SEC'),
    ('2017-04-08 12:14:41', 3, 'SEC', 'FIR'),
    ('2014-01-07 10:36:58', 4, 'SEC', 'FIR'),
    ('2019-03-04 11:42:23', 5, 'THI', 'FOU'),
    ('2012-11-06 21:21:21', 6, 'THI', 'FOU'),
    ('2022-02-22 22:22:22', 7, 'FOU', 'THI'),
    ('2021-09-01 09:00:00', 8, 'FOU', 'THI'),
    ('2022-02-23 00:00:01', 9, 'FOU', 'FIF'),
    ('2012-12-12 21:12:21', 10, 'FIF', 'FIR')
    ;
