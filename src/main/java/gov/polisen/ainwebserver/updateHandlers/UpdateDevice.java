package gov.polisen.ainwebserver.updateHandlers;

import java.util.Date;

import gov.polisen.ainwebserver.Main;
import gov.polisen.orm.maps.DeviceMapper;
import gov.polisen.orm.models.Device;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import org.apache.ibatis.session.SqlSession;

public class UpdateDevice implements HttpHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {

		SqlSession session = Main.getSessionFactory().openSession();
		try {
			
			DeviceMapper mapper = session.getMapper(DeviceMapper.class);
			String[] arg = exchange.getRequestPath().split("/");
			
			

			Integer deviceID = Integer.parseInt(arg[2]);
			short battery = Short.parseShort(arg[3]);
			float longitude = Float.parseFloat(arg[4]);
			float latitude = Float.parseFloat(arg[5]);
			Date now = new Date();
			
			// kraschar om man skickar in ip= exchange.getConnection().getPeerAddress().toString()
			Device d = new Device(deviceID, now, battery, longitude, latitude,
					null);
			mapper.updateByPrimaryKey(d);
			session.commit();
		} finally {
			session.close();
		}

	}

}
