package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.ClassificationMapper;
import gov.polisen.orm.models.Classification;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetClassification extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		ClassificationMapper mapper = session.getMapper(ClassificationMapper.class);
		short primary = Short.parseShort(exchange.getRequestPath().substring(16));
		Classification result = mapper.selectByPrimaryKey(primary);

		return result;
	}
}
