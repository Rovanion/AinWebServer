--liquibase formatted sql

-- changeset testdata:1 context:WITH_TEST_DATA
INSERT INTO PermissionsOnCases
	VALUES(1, 1, 1, true, true, true, true);
-- rollback DELETE FROM PermissionsOnCases WHERE id=1;

