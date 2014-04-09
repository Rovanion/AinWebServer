package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.UserMapper;
import gov.polisen.orm.models.User;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class GetUser extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		UserMapper userMapper = session.getMapper(UserMapper.class);
		// The sixth character and forward should be the key.
		User result = userMapper.selectByPrimaryKey(Integer.parseInt(exchange
				.getRequestPath().substring(6)));

		return result;
	}
}
