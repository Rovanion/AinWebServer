package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.StatusMapper;
import gov.polisen.orm.models.Status;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetStatus extends JSONSender {
	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		StatusMapper statusMapper = session.getMapper(StatusMapper.class);
		Status result = statusMapper.selectByPrimaryKey(Short.parseShort(exchange
				.getRequestPath().substring(8)));

		return result;
	}
}
