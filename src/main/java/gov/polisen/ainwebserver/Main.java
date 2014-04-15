package gov.polisen.ainwebserver;

import gov.polisen.ainwebserver.pathHandlers.CREATE;
import gov.polisen.ainwebserver.pathHandlers.DELETE;
import gov.polisen.ainwebserver.pathHandlers.READ;
import gov.polisen.ainwebserver.pathHandlers.UPDATE;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {
	// The connection factory
	private static SqlSessionFactory	sessionFactory;
	// The following are classes extending PathHandler.
	private static CREATE							create				= new CREATE();
	private static READ								read					= new READ();
	private static UPDATE							update				= new UPDATE();
	private static DELETE							delete				= new DELETE();
	private static Properties					webProperties	= new Properties();


	public static void main(final String[] args) throws IOException, SQLException {
		// Setup myBatis.
		String resource = "gov/polisen/mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// Make sure all database migrations have been applied to the database.
		new MigrationHandler(sessionFactory.getConfiguration().getEnvironment()
				.getDataSource().getConnection(),
				MigrationHandler.Context.WITH_TEST_DATA);

		// Read the settings for the web server
		webProperties.load(new FileInputStream("conf/web.properties"));
		int port = Integer.parseInt(webProperties.getProperty("port", "80"));
		String hostname = webProperties.getProperty("address", "localhost");

		// Start the Undertow server.
		Undertow server = Undertow.builder().addHttpListener(port, hostname)
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
