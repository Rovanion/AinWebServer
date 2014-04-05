package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.DeploymentMapper;
import gov.polisen.orm.models.Deployment;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetDeployment extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		DeploymentMapper mapper = session.getMapper(DeploymentMapper.class);
		Deployment result = mapper.selectByPrimaryKey(Integer.parseInt(exchange
				.getRequestPath().substring(12)));

		return result;
	}
}
