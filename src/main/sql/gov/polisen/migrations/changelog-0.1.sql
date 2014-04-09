--liquibase formatted sql

-- changeset setup:1
CREATE TABLE Users (
	id serial PRIMARY KEY,
	userName character(8) UNIQUE,
	realName varchar(50)	
);
-- rollback drop table Users;

-- changeset setup:2
CREATE TABLE Statuses (
	id smallserial PRIMARY KEY,
	shorthand varchar(20) NOT NULL UNIQUE,
	description text NOT NULL
);
-- rollback drop table Statuses;

-- changeset setup:3
CREATE TABLE Priorities(
	id smallserial PRIMARY KEY,
	shorthand varchar(20) NOT NULL UNIQUE,
	description text NOT NULL	
);
-- rollback DROP TABLE Priorities

-- changeset setup:4
CREATE TABLE Classifications (
	id smallserial PRIMARY KEY,
	shorthand varchar(20) NOT NULL UNIQUE,
	description text NOT NULL
);
-- rollback drop table Classifications;

-- changeset setup:5
CREATE TABLE Deployments (
	id serial PRIMARY KEY,
	author integer REFERENCES Users(id) NOT NULL,
	modificationTime timestamp WITH TIME ZONE NOT NULL,
	firstRevision int NOT NULL REFERENCES Deployments(id),
	deletionTime timestamp WITH TIME ZONE,
	status smallint NOT NULL REFERENCES Statuses(id),
	priority smallint REFERENCES Priorities(id),
	longitude real NOT NULL,
	latitude real NOT NULL,
	description text
);
-- rollback drop table Deployments;

-- changeset setup:6
CREATE TABLE Cases (
	deviceID int NOT NULL,
	caseID int NOT NULL,
	author int REFERENCES Users(id) NOT NULL,
	modificationTime timestamp WITH TIME ZONE NOT NULL,
	firstRevisionCaseID int NOT NULL,
	firstRevisionDeviceID int NOT NULL,
	deletionTime timestamp WITH TIME ZONE,
	classification smallint NOT NULL REFERENCES Classifications(id),
	status smallint NOT NULL REFERENCES Statuses(id),
	priority smallint REFERENCES Priorities(id),
	longitude real,
	latitude real,
	timeOfCrime timestamp WITH TIME ZONE,
	description text,
	FOREIGN KEY (firstRevisionCaseID, firstRevisionDeviceID) REFERENCES Cases(caseID, deviceID),
	PRIMARY KEY (deviceID, caseID)
);
-- rollback drop table Cases;

-- changeset setup:7
CREATE TABLE Images (
	id serial PRIMARY KEY,
	author int REFERENCES Users(id) NOT NULL,
	path text
);
-- rollback drop table Images;

-- changeset setup:8
CREATE TABLE DeploymentImages (
	imageID int REFERENCES Images(id) NOT NULL,
	deploymentID int REFERENCES Deployments (id) NOT NULL
);
-- rollback drop table DeploymentImages;

-- changeset setup:9
CREATE TABLE CaseImages (
	imageID int REFERENCES Images(id) NOT NULL,
	caseID int NOT NULL,
	deviceID int NOT NULL,
	FOREIGN KEY (caseID, deviceID) REFERENCES Cases(caseID, deviceID)
);
-- rollback drop table CaseImages;

-- changeset setup:10
CREATE TABLE Devices(
	id serial PRIMARY KEY,
	lastSeen timestamp NOT NULL,
	battery smallint NOT NULL,
	longitude real NOT NULL,
	latitude real NOT NULL
);
-- rollback DROP TABLE Devices;

-- changeset fixes:11
CREATE TABLE PermissionsOnCases(
	deviceID int NOT NULL,
	caseID int NOT NULL,
	userID int NOT NULL REFERENCES Users(id),
	read boolean NOT NULL,
	addData boolean NOT NULL,
	modify boolean NOT NULL,
	changePermissions boolean NOT NULL,
	CONSTRAINT caseFK FOREIGN KEY (deviceID, caseID) REFERENCES Cases (deviceID, caseID),
	PRIMARY KEY (deviceID, caseID, userID)
);
-- rollback DROP TABLE PermissionsOnCases

--changeset fixes:12
CREATE TABLE PermissionsOnDeployments(
	deploymentID int NOT NULL,
	userID int NOT NULL REFERENCES Users(id),
	read boolean NOT NULL,
	addData boolean NOT NULL,
	modify boolean NOT NULL,
	changePermissions boolean NOT NULL,
	CONSTRAINT deploymentFK FOREIGN KEY (deploymentID) REFERENCES Deployments(id),
	PRIMARY KEY (deploymentID, userID)
);
-- rollback DROP TABLE PermissionsOnDeployments
