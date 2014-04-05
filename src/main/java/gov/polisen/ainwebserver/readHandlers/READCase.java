package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.ainwebserver.Main;
import gov.polisen.orm.maps.CaseMapper;
import gov.polisen.orm.models.Case;
import gov.polisen.orm.models.CaseKey;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

public class READCase implements HttpHandler{

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();
		try {
			exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/json");
			CaseMapper caseMapper = session.getMapper(CaseMapper.class);
			int deviceID = Integer
					.parseInt(exchange.getRequestPath().substring(6, 7));
			int caseID = Integer.parseInt(exchange.getRequestPath().substring(8, 9));
			Case result = caseMapper
					.selectByPrimaryKey(new CaseKey(deviceID, caseID));

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
