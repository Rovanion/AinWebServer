package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.maps.DeviceMapper;
import gov.polisen.orm.models.Device;
import io.undertow.server.HttpServerExchange;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

public class GetNewDevice extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		DeviceMapper mapper = session.getMapper(DeviceMapper.class);
		String[] arg = exchange.getRequestPath().split("/");

		short battery = Short.parseShort(arg[2]);
		float longitude = Float.parseFloat(arg[3]);
		float latitude = Float.parseFloat(arg[4]);
		Date now = new Date();

		// exchange.getConnection().getPeerAddress().toString()
		Device d = new Device(null, now, battery, longitude, latitude, null);
		mapper.insert(d);
		session.commit();

		return d.getId();
	}
}