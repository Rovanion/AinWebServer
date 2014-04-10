package gov.polisen.ainwebserver.pathHandlers;

import gov.polisen.ainwebserver.createHandlers.CREATEUser;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;

/**
 * CREATE, READ, UPDATE and DELETE. Selects handler depending on the request method.
 * @author Rovanion Luckey
 *
 */
public class CREATE extends PathHandler{
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception{
		super.addPrefixPath("/user", new CREATEUser());
		super.handleRequest(exchange);
	}
}
