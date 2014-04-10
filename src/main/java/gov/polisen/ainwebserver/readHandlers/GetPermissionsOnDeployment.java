package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.PermissionsOnDeploymentMapper;
import gov.polisen.orm.models.PermissionsOnDeployment;
import gov.polisen.orm.models.PermissionsOnDeploymentKey;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;


public class GetPermissionsOnDeployment extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		PermissionsOnDeploymentMapper mapper = session
				.getMapper(PermissionsOnDeploymentMapper.class);

		String[] arg = exchange.getRequestPath().split("/");

		int deploymentID = Integer.parseInt(arg[2]);
		int userID = Integer.parseInt(arg[3]);

		PermissionsOnDeployment result = mapper
				.selectByPrimaryKey(new PermissionsOnDeploymentKey(deploymentID, userID));

		return result;
	}

}
