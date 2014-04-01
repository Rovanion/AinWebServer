--liquibase formatted sql

-- changeset setup:1
CREATE TABLE Users (
	id serial PRIMARY KEY,
	userName character(8) UNIQUE,
	realName varchar(50)	
);
-- rollback drop table Users;

-- changeset setup:2
CREATE TABLE Deployments (
	id serial PRIMARY KEY,
	author integer REFERENCES Users(id) NOT NULL,
	modificationTime timestamp WITH TIME ZONE NOT NULL,
	firstRevision int NOT NULL REFERENCES Deployments(id),
	timeDeleted timestamp WITH TIME ZONE,
	priority smallint,
	longitude real NOT NULL,
	latitude real NOT NULL,
	description text
);
-- rollback drop table Deployments;

-- changeset setup:3
CREATE TABLE Classifications (
	id serial PRIMARY KEY,
	shorthand varchar(20) NOT NULL,
	description text NOT NULL
);
-- rollback drop table Classifications;

-- changeset setup:4
CREATE TABLE Statuses (
	id serial PRIMARY KEY,
	shorthand varchar(20) NOT NULL,
	description text NOT NULL
);
-- rollback drop table Statuses;

-- changeset setup:5
CREATE TABLE Cases (
	deviceID int NOT NULL,
	caseID int NOT NULL,
	author int REFERENCES Users(id) NOT NULL,
	modificationTime timestamp WITH TIME ZONE NOT NULL,
	firstRevisionCaseID int NOT NULL,
	firstRevisionDeviceID int NOT NULL,
	timeDeleted timestamp WITH TIME ZONE,
	classification int NOT NULL REFERENCES Classifications(id),
	priority smallint,
	longitude real,
	latitude real,
	status int NOT NULL REFERENCES Statuses(id),
	owner int NOT NULL REFERENCES Users(id),
	description text,
	timeOfCrime timestamp WITH TIME ZONE,
	FOREIGN KEY (firstRevisionCaseID, firstRevisionDeviceID) REFERENCES Cases(caseID, deviceID),
	PRIMARY KEY (deviceID, caseID)
);
-- rollback drop table Cases;

-- changeset setup:6
CREATE TABLE Images (
	id serial PRIMARY KEY,
	author int REFERENCES Users(id) NOT NULL,
	path text
);
-- rollback drop table Images;

-- changeset setup:7
CREATE TABLE DeploymentImages (
	imageID int REFERENCES Images(id) NOT NULL,
	deploymentID int REFERENCES Deployments (id) NOT NULL
);
-- rollback drop table DeploymentImages;

-- changeset setup:8
CREATE TABLE CaseImages (
	imageID int REFERENCES Images(id) NOT NULL,
	caseID int NOT NULL,
	deviceID int NOT NULL,
	FOREIGN KEY (caseID, deviceID) REFERENCES Cases(caseID, deviceID)
);
-- rollback drop table CaseImages;
