package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.DeviceMapper;
import gov.polisen.orm.models.Device;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetDevice extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		DeviceMapper mapper = session.getMapper(DeviceMapper.class);
		Device result = mapper.selectByPrimaryKey(Integer.parseInt(exchange
				.getRequestPath().substring(8)));

		return result;
	}
}
