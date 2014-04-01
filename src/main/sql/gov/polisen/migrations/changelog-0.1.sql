--liquibase formatted sql

-- changeset setup:1
CREATE TABLE Users (
	id serial PRIMARY KEY,
	userName character(8) UNIQUE,
	realName varchar(50)	
)

-- changeset setup:2
CREATE TABLE Deployments (
	id serial PRIMARY KEY,
	author integer REFERENCES Users(id) NOT NULL,
	modificationTime timestamp WITH TIME ZONE NOT NULL,
	firstRevision int NOT NULL REFERENCES Deployments(id),
	priority smallint,
	longitude double NOT NULL,
	latitude double NOT NULL,
	timeDeleted timestamp,
	description text
)

-- changeset setup:3
CREATE TABLE Classifications (
	id serial PRIMARY KEY,
	shorthand varchar(20) NOT NULL,
	description text NOT NULL
)

-- changeset setup:4
CREATE TABLE Statuses (
	id serial PRIMARY KEY,
	shorthand varchar(20) NOT NULL,
	description text NOT NULL
)

-- changeset setup:5
CREATE TABLE Cases (
	deviceID int NOT NULL,
	caseID int NOT NULL,
	author int REFERENCES Users(id) NOT NULL,
	modificationTime timestamp WITH TIME ZONE NOT NULL,
	firstRevision int NOT NULL REFERENCES Cases(id),
	classification int NOT NULL REFERENCES Classification(id),
	priority smallint,
	longitude double,
	latitude double,
	status int NOT NULL REFERENCES Statuses(id),
	owner int NOT NULL REFERENCES Users(id),
	description text,
	timeOfCrime timestamp WITH TIME ZONE,
	PRIMARY KEY (deviceID, caseID)
)

-- changeset setup:6
CREATE TABLE Images (
	id serial PRIMARY KEY,
	author int REFERENCES Users(id) NOT NULL,
	path text
)

-- changeset setup:7
CREATE TABLE DeploymentImages (
	imageID int REFERENCES Images(id) NOT NULL,
	deploymentID int REFERENCES Deployments (id) NOT NULL
)

-- changeset setup:8
CREATE TABLE CaseImages (
	imageID int REFERENCES Images(id) NOT NULL,
	caseID int NOT NULL,
	deviceID int NOT NULL,
	FOREIGN KEY (caseID, deviceID) REFERENCES Cases(caseID, deviceID)
)