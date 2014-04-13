package gov.polisen.ainwebserver.createHandlers;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import gov.polisen.ainwebserver.Main;
import gov.polisen.orm.maps.PermissionsOnCaseMapper;
import gov.polisen.orm.models.Case;
import gov.polisen.orm.models.PermissionsOnCase;

public class CREATEPermissionsOnCase implements HttpHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();
		PermissionsOnCaseMapper mapper = session
				.getMapper(PermissionsOnCaseMapper.class);

		try {
			Gson gson = new Gson();
			PermissionsOnCase recievedPermissions = gson.fromJson(exchange.getRequestChannel().toString(), PermissionsOnCase.class);
			mapper.insert(recievedPermissions);
		} finally {
			session.close();
		}
	}
}