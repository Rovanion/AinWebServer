package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.ainwebserver.Main;
import gov.polisen.orm.maps.DeploymentMapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.ibatis.session.SqlSession;

public class READDeployment implements HttpHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();
		try {
			exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/json");
			DeploymentMapper mapper = session.getMapper(DeploymentMapper.class);
			mapper.selectByPrimaryKey(Integer.parseInt(exchange.getRequestPath()
					.substring(10)));
		} finally {
			session.close();
		}
	}
}
