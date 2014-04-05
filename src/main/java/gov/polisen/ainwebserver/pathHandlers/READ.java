package gov.polisen.ainwebserver.pathHandlers;

import gov.polisen.ainwebserver.readHandlers.READCase;
import gov.polisen.ainwebserver.readHandlers.READDeployment;
import gov.polisen.ainwebserver.readHandlers.READStatus;
import gov.polisen.ainwebserver.readHandlers.READUser;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;

public class READ extends PathHandler {
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception{
		super.addPrefixPath("/user", new READUser());
		super.addPrefixPath("/status", new READStatus());
		super.addPrefixPath("/deployment", new READDeployment());
		super.addPrefixPath("/case", new READCase());
		super.handleRequest(exchange);
	}
}
