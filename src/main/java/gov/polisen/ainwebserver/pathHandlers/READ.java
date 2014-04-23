package gov.polisen.ainwebserver.pathHandlers;

import gov.polisen.ainwebserver.readHandlers.GetCase;
import gov.polisen.ainwebserver.readHandlers.GetClassification;
import gov.polisen.ainwebserver.readHandlers.GetDeployment;
import gov.polisen.ainwebserver.readHandlers.GetDevice;
import gov.polisen.ainwebserver.readHandlers.GetNewDevice;
import gov.polisen.ainwebserver.readHandlers.GetPerMissionsOnCase;
import gov.polisen.ainwebserver.readHandlers.GetPermissionsOnDeployment;
import gov.polisen.ainwebserver.readHandlers.GetPriority;
import gov.polisen.ainwebserver.readHandlers.GetStatus;
import gov.polisen.ainwebserver.readHandlers.GetUser;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;

public class READ extends PathHandler {
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception{
		super.addPrefixPath("/user", new GetUser());
		super.addPrefixPath("/status", new GetStatus());
		super.addPrefixPath("/priority", new GetPriority());
		super.addPrefixPath("/classification", new GetClassification());
		super.addPrefixPath("/deployment", new GetDeployment());
		super.addPrefixPath("/case", new GetCase());

		// TODO: Implement the get image http handlers.

		super.addPrefixPath("/device", new GetDevice());
		super.addPrefixPath("/permissionsOnDeployment",
				new GetPermissionsOnDeployment());
		super.addPrefixPath("/permissionsOnCase", new GetPerMissionsOnCase());

		super.addPrefixPath("/newDevice", new GetNewDevice());

		super.handleRequest(exchange);
	}
}
