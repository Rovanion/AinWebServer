package gov.polisen.ainwebserver;

import gov.polisen.ainwebserver.pathandlers.CREATE;
import gov.polisen.ainwebserver.pathandlers.DELETE;
import gov.polisen.ainwebserver.pathandlers.READ;
import gov.polisen.ainwebserver.pathandlers.UPDATE;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.migration.DataSourceConnectionProvider;
import org.apache.ibatis.migration.JavaMigrationLoader;
import org.apache.ibatis.migration.operations.UpOperation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {
	// The following are classes extending PathHandler.
	private static CREATE create = new CREATE();
	private static READ read = new READ();
	private static UPDATE update = new UPDATE();
	private static DELETE delete = new DELETE();

	// The connection factory
	private static SqlSessionFactory sessionFactory;

	public static void main(final String[] args) throws IOException, SQLException {
		// Setup myBatis.
		String resource = "gov/polisen/mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try{
			// Make sure all database migrations have been applied to the database.
			new UpOperation().operate(new DataSourceConnectionProvider(sessionFactory
					.getConfiguration().getEnvironment().getDataSource()),
					new JavaMigrationLoader("gov.polisen.migrations"), null, null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Attempting to create nonexisting database!");
			String url = sessionFactory.getConfiguration().getEnvironment()
					.getDataSource().getConnection().getMetaData().getURL();
			Connection c = DriverManager.getConnection(url.substring(0,
					url.indexOf("/polisen")));
			Statement statement = c.createStatement();
			statement.executeUpdate("CREATE DATABASE polisen");
			c.close();
		}

		// Start the Undertow server.
		Undertow server = Undertow.builder()
				.addHttpListener(1337, "localhost")
				.setHandler(new HttpHandler() {
					public void handleRequest(HttpServerExchange exchange) throws Exception {
						if(exchange.getRequestMethod() == Methods.GET)
							read.handleRequest(exchange);
						else if(exchange.getRequestMethod() == Methods.PUT)
							update.handleRequest(exchange);
						else if(exchange.getRequestMethod() == Methods.POST)
							create.handleRequest(exchange);
						else if(exchange.getRequestMethod() == Methods.DELETE)
							delete.handleRequest(exchange);
					}
				})
				.build();

		server.start();
	}

	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
