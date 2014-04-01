--liquibase formatted sql

-- changeset testdata:1 context:WITH_TEST_DATA
INSERT INTO Users(userName, realName)
	VALUES("bengt666", "Bengan Gta");
-- rollback DELETE FROM Users WHERE userName="bengt666";

-- changeset testdata:2 context:WITH_TEST_DATA
INSERT INTO Classifications
	VALUES(0, "Snatteri", "Ett fall av stöld men av så ringa grad att det inte är att räkna som stöld.")
-- rollback DELETE FROM Classifications WHERE id=0;
	
-- changeset testdata:3 context:WITH_TEST_DATA
INSERT INTO Devices(lastSeen, battery, longitude, latitude)
	VALUES (CURRENT_TIMESTAMP, 100, 10.0, 82.3);
-- rollback DELETE FROM Devices WHERE battery=100 AND longitude=10.0 AND latitude=82.3;

-- changeset testdata:4 context:WITH_TEST_DATA
INSERT INTO Statuses
	VALUES (0, "Uppklarat", "Brottet är uppklarat.");
-- rollback DELETE FROM Statuses WHERE id=0;
	
-- changeset testdata:5 context:WITH_TEST_DATA
INSERT INTO Cases(author, firstRevisionCaseID, firstRevisionDeviceID, 
		classification, status, owner, description)
	VALUES (0, 0, 0,
			0, 0, 0, "Snatteri på skånskgatan, skåning misstänkt.");
-- rollback DELETE FROM Cases WHERE description="Snatteri på skånskgatan, skåning misstänkt."


