package gov.polisen.ainwebserver.pathandlers;

import gov.polisen.ainwebserver.READCase;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;

public class READ extends PathHandler {
	READCase caseHandler = new READCase();
	public void handleRequest(HttpServerExchange exchange) throws Exception{
		super.addPrefixPath("/case", caseHandler);
		super.handleRequest(exchange);
	}
}
