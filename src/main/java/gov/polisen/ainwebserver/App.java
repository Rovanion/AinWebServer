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

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class App {
	// The following are classes extending PathHandler.
	private static CREATE create = new CREATE();
	private static READ read = new READ();
	private static UPDATE update = new UPDATE();
	private static DELETE delete = new DELETE();

	// The connection factory
	private static SqlSessionFactory sessionFactory;

	public static void main(final String[] args) throws IOException {
		// Setup myBatis.
		String resource = "gov/polisen/mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

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
