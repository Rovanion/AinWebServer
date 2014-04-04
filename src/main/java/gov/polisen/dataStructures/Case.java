package gov.polisen.dataStructures;

import java.sql.Date;
import java.sql.Timestamp;

public class Case {
	// deviceID and caseID is together the primary key for the case
	public final int		deviceID;
	// The device specific case identification number.
	public final int		caseID;
	public final int authorID;
	public final int firstRevisionCaseID;
	public final int firstRevisionDeviceID;
	public final Timestamp timeDeleted;
	// The type of crime, i.e. robbery or murder.
	public final short		classification;
	public final short		status;
	public final short priority;
	// Longitude and latitude of the where the crime was committed.
	public final float longitude;
	public final float latitude;
	public final Date		timeOfCrime;
	public final String	description;

	public Case(int deviceID, int caseID, int authorID, int firstRevisionCaseID,
			int firstRevisionDeviceID, Timestamp timeDeleted, short classification,
			short status, short priority, float longitude, float latitude,
			Date timeOfCrime, String description) {
		super();
		this.deviceID = deviceID;
		this.caseID = caseID;
		this.authorID = authorID;
		this.firstRevisionCaseID = firstRevisionCaseID;
		this.firstRevisionDeviceID = firstRevisionDeviceID;
		this.timeDeleted = timeDeleted;
		this.classification = classification;
		this.status = status;
		this.priority = priority;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timeOfCrime = timeOfCrime;
		this.description = description;
	}

}
