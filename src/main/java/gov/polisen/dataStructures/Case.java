package gov.polisen.dataStructures;

import java.sql.Date;

public class Case {
	// deviceID and caseID is together the primary key for the case
	private int			deviceID;
	private final int			caseID;

	// The type of crime, i.e. robbery or murder.
	String					crimeClassification;

	// The leader of the investigation
	private int			commander;
	private Date		timeOfCrime;
	private String	description;

	public Case(int caseID) {
		this.caseID = caseID;
	}
}
