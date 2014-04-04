package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.ainwebserver.Main;
import gov.polisen.dataStructures.Case;
import gov.polisen.dataStructures.CaseMapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class READCase implements HttpHandler{

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		SqlSession session = Main.getSessionFactory().openSession();
		try {
			CaseMapper caseMapper = session.getMapper(CaseMapper.class);
			Case result = caseMapper.selectCase(1);
			System.out.println(result);
		} finally {
			session.close();
		}
	}
}
