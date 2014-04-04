package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.ainwebserver.Main;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class READUser implements HttpHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();
		try {

		} finally {
			session.close();
		}

	}
}
