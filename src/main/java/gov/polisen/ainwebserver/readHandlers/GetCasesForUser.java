package gov.polisen.ainwebserver.readHandlers;

import gov.polisen.orm.examples.CaseExample;
import gov.polisen.orm.examples.PermissionsOnCaseExample;
import gov.polisen.orm.maps.CaseMapper;
import gov.polisen.orm.maps.PermissionsOnCaseMapper;
import gov.polisen.orm.models.Case;
import gov.polisen.orm.models.PermissionsOnCase;
import io.undertow.server.HttpServerExchange;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class GetCasesForUser extends JSONSender {

	@Override
	protected Object getResult(HttpServerExchange exchange, SqlSession session) {
		PermissionsOnCaseMapper permissionMapper = session
				.getMapper(PermissionsOnCaseMapper.class);

		int uid = Integer.parseInt(exchange.getRequestPath().substring(14));

		PermissionsOnCaseExample p = new PermissionsOnCaseExample();
		p.or().andUserIdEqualTo(uid);
		List<PermissionsOnCase> list = permissionMapper.selectByExample(p);

		CaseExample ce = new CaseExample();
		for (PermissionsOnCase permissionsOnCase : list) {
			ce.or().andCaseIdEqualTo(permissionsOnCase.getCaseId())
					.andDeviceIdEqualTo(permissionsOnCase.getDeviceId());
		}

		CaseMapper caseMapper = session.getMapper(CaseMapper.class);
		List<Case> caseList = caseMapper.selectByExample(ce);

		return caseList;
	}

}
