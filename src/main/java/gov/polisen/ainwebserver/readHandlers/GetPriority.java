package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.PriorityMapper;
import gov.polisen.orm.models.Priority;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetPriority extends JSONSender {
	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		PriorityMapper mapper = session.getMapper(PriorityMapper.class);
		Priority result = mapper.selectByPrimaryKey(Short.parseShort(exchange
				.getRequestPath().substring(10)));

		return result;
	}
}
