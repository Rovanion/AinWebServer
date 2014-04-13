package gov.polisen.ainwebserver;

import java.sql.Connection;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

public class MigrationHandler {
	public enum Context {
		WITH_TEST_DATA, DEPLOYMENT
	}

	public MigrationHandler(Connection connection, Context context) {
		try {
			Database database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(new JdbcConnection(connection));
			Liquibase liquibase = new Liquibase(
					"src/main/sql/gov/polisen/migrations/changelog-master.xml",
					new FileSystemResourceAccessor(), database);
		} catch (LiquibaseException e) {
			// TODO: Do something useful here
			e.printStackTrace();
		}
	}
}
