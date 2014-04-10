--liquibase formatted sql

-- changeset testdata:1 context:WITH_TEST_DATA
INSERT INTO Users(userName, realName)
	VALUES('bengt666', 'Bengan Gta');
-- rollback DELETE FROM Users WHERE userName='bengt666';

-- changeset testdata:2 context:WITH_TEST_DATA
INSERT INTO Classifications
	VALUES(1, 'Snatteri', 'Ett fall av stöld men av så ringa grad att det inte är att räkna som stöld.')
-- rollback DELETE FROM Classifications WHERE id=0;
	
-- changeset testdata:3 context:WITH_TEST_DATA
INSERT INTO Devices(lastSeen, battery, longitude, latitude)
	VALUES (CURRENT_TIMESTAMP, 100, 10.0, 82.3);
-- rollback DELETE FROM Devices WHERE battery=100 AND longitude=10.0 AND latitude=82.3;

-- changeset testdata:4 context:WITH_TEST_DATA
INSERT INTO Statuses
	VALUES (1, 'Uppklarat', 'Brottet är uppklarat.');
-- rollback DELETE FROM Statuses WHERE id=0;

INSERT INTO Priorities
	VALUES (1, 'Skitgöraviktigt', 'Det är helt enkelt skitgöraviktigt att klara av det här.');
	
-- changeset testdata:5 context:WITH_TEST_DATA
INSERT INTO Cases(deviceID, caseID, author, firstRevisionCaseID, 
		firstRevisionDeviceID, classification, status, description)
	VALUES (1, 1, 1, 1, 
		1, 1, 1, 'Snatteri på skånskgatan, skåning misstänkt.');
-- rollback DELETE FROM Cases WHERE description='Snatteri på skånskgatan, skåning misstänkt.'

-- changeset testdata:6 context:WITH_TEST_DATA
INSERT INTO Deployments(id, author, firstRevision, status,
		priority, longitude, latitude, description)
	VALUES (1, 1, 1, 1,
	1, 82.0, 42.2, 'Brandbomber i bromölla.');
-- rollback DELETE FROM Cases WHERE description='Snatteri på skånskgatan, skåning misstänkt.'

-- TODO: Add test data for the permissions and image tables.