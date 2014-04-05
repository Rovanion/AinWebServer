package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.PermissionsOnCaseMapper;
import gov.polisen.orm.models.PermissionsOnCase;
import gov.polisen.orm.models.PermissionsOnCaseKey;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetPerMissionsOnCase extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		PermissionsOnCaseMapper mapper = session
				.getMapper(PermissionsOnCaseMapper.class);

		String[] arg = exchange.getRequestPath().split("/");

		int deviceID = Integer.parseInt(arg[2]);
		int caseID = Integer.parseInt(arg[3]);
		int userID = Integer.parseInt(arg[4]);

		PermissionsOnCase result = mapper
				.selectByPrimaryKey(new PermissionsOnCaseKey(deviceID, caseID, userID));
		return result;
	}
}
