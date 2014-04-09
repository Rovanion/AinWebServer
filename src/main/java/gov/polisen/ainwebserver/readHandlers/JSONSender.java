package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.ainwebserver.Main;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

public abstract class JSONSender implements HttpHandler {
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();

		try {
			Object result = getResult(exchange, session);
			if (result == null)
				exchange.setResponseCode(404);
			else {
				exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,
						"text/json;charset=utf-8");
				Gson gson = new Gson();
				exchange.getResponseSender().send(gson.toJson(result));
			}
		} finally {
			session.close();
		}
	}

	protected abstract Object getResult(HttpServerExchange exchange,
			SqlSession session);
}
