package gov.polisen.ainwebserver.pathandlers;

import gov.polisen.ainwebserver.readHandlers.READCase;
import gov.polisen.ainwebserver.readHandlers.READUser;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;

public class READ extends PathHandler {
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception{
		super.addPrefixPath("/case", new READCase());
		super.addPrefixPath("/user", new READUser());
		super.handleRequest(exchange);
	}
}
