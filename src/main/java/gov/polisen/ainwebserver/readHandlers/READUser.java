package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.ainwebserver.Main;
import gov.polisen.orm.maps.UserMapper;
import gov.polisen.orm.models.User;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

public class READUser implements HttpHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();
		try {
			exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/json");
			UserMapper userMapper = session.getMapper(UserMapper.class);
			// The sixth characterand and forward should be the key.
			User result = userMapper.selectByPrimaryKey(Integer.parseInt(exchange
					.getRequestPath().substring(6)));

			if (result == null)
				exchange.setResponseCode(404);
			else {
				Gson gson = new Gson();
				exchange.getResponseSender().send(gson.toJson(result));
			}
		} finally {
			session.close();
		}
	}
}
