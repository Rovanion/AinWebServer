package gov.polisen.ainwebserver.pathHandlers;

import gov.polisen.ainwebserver.updateHandlers.UpdateDevice;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;

public class UPDATE extends PathHandler {

	public void handleRequest(HttpServerExchange exchange) throws Exception {

		super.addPrefixPath("/updateDevice", new UpdateDevice());
		super.handleRequest(exchange);
	}
}
