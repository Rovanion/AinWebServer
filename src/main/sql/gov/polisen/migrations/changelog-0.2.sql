--liquibase formatted sql

-- changeset fixes:1
ALTER TABLE Users(
	ALTER COLUMN userName SET NOT NULL,
	ALTER COLUMN realName SET NOT NULL,	
	ADD COLUMN passwordHash NOT NULL
);
-- rollback ALTER TABLE Users ( ALTER COLUMN userName DROP NOT NULL, ALTER COLUMN realName DROP NOT NULL, DROP COLUMN passwordHash ); 	

-- changeset fixes:2
ALTER TABLE Deployments (
	ALTER COLUMN modificationTime SET DEFAULT CURRENT_TIMESTAMP,
	ALTER COLUMN timeDeleted SET DEFAULT NULL
);
-- rollback ALTER TABLE Deployments ( ALTER COLUMN modificationTime DROP DEFAULT, ALTER COLUMN timeDeleted DROP DEFAULT );

-- changeset fixes:4
CREATE TABLE Devices (
	id serial PRIMARY,
	lastSeen timestamp NOT NULL,
	battery smallint NOT NULL,
	longitude real NOT NULL,
	latitude real NOT NULL,
)
-- rollback DROP TABLE Devices;

-- changeset fixes:5
ALTER TABLE Cases (
	ALTER COLUMN modificationTime SET DEFAULT CURRENT_TIMESTAMP,
	ALTER COLUMN timeDeleted SET DEFAULT NULL,
	ADD CONSTRAINT deviceFK FOREIGN KEY (deviceID) REFERENCES Devices(id)
);
-- rollback ALTER TABLE Cases ( ALTER CALUMN modificationTime DROP DEFAULT, ALTER COLUMN timeDeleted DROP DEFAULT, REMOVE CONSTRAINT deviceFK );

