package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.CaseMapper;
import gov.polisen.orm.models.Case;
import gov.polisen.orm.models.CaseKey;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetCase extends JSONSender implements HttpHandler {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		CaseMapper caseMapper = session.getMapper(CaseMapper.class);

		String[] arg = exchange.getRequestPath().split("/");
		// TODO: Implement length checking of the array to prevent crashing.
		int deviceID = Integer.parseInt(arg[2]);
		int caseID = Integer.parseInt(arg[3]);

		Case result = caseMapper.selectByPrimaryKey(new CaseKey(deviceID,
				caseID));

		return result;
	}
}
