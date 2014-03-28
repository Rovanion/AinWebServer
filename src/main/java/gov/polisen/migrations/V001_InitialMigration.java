package gov.polisen.migrations;
import java.math.BigDecimal;

import org.apache.ibatis.migration.MigrationScript;

public class V001_InitialMigration implements MigrationScript {
	/**
	 * Unique ID of the migration script. Newer scripts must return larger
	 * numbers.
	 */
	public BigDecimal getId() {
		return BigDecimal.valueOf(1L);
	}

	/**
	 * Short description of the script.
	 */
	public String getDescription() {
		return "Create changelog.";
	}

	/**
	 * The script which upgrades the schema.
	 */
	public String getUpScript() {
		return "CREATE TABLE changelog (" + "ID NUMERIC(20,0) NOT NULL,"
				+ "APPLIED_AT VARCHAR(25) NOT NULL,"
				+ "DESCRIPTION VARCHAR(255) NOT NULL); "

				+ "ALTER TABLE changelog " + "ADD CONSTRAINT PK_changelog "
				+ "PRIMARY KEY (id);";
	}

	/**
	 * The script which returns the database schema to it's previous version.
	 */
	public String getDownScript() {
		return "DROP TABLE changelog;";
	}
}
