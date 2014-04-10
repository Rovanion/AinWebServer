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

	public MigrationHandler(Connection connection, Context context)
			throws LiquibaseException {

		Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(new JdbcConnection(connection));
		Liquibase liquibase = new Liquibase(
				"lib/main/sql/migrations/changelog-master.xml",
				new FileSystemResourceAccessor(), database);
		liquibase.update(context.toString());
	}
}
