package gov.polisen.ainwebserver.createHandlers;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

import gov.polisen.ainwebserver.Main;
import gov.polisen.orm.maps.CaseMapper;
import gov.polisen.orm.models.Case;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class CreateCase implements HttpHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {

		SqlSession session = Main.getSessionFactory().openSession();
		CaseMapper mapper = session.getMapper(CaseMapper.class);

		try {
			Gson gson = new Gson();
			exchange.startBlocking();
			InputStream input = exchange.getInputStream();
			String inputString = convertStreamToString(input);
			Case recievedCase = gson.fromJson(inputString, Case.class);
			mapper.insert(recievedCase);
			session.commit();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("resource")
	static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
