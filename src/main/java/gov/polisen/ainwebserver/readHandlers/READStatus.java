package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.ainwebserver.Main;
import gov.polisen.orm.maps.StatusMapper;
import gov.polisen.orm.models.Status;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

public class READStatus implements HttpHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();
		try {
			exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/json");
			StatusMapper statusMapper = session.getMapper(StatusMapper.class);

			Status result = statusMapper.selectByPrimaryKey((short) 1);

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
