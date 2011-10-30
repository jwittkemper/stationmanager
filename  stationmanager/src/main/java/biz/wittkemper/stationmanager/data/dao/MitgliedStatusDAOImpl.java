package biz.wittkemper.stationmanager.data.dao;

import biz.wittkemper.stationmanager.data.entity.MitgliedStatus;

public class MitgliedStatusDAOImpl extends
		AbstractDAOImpl<MitgliedStatus, Integer> implements MitgliedStatusDAO{

	@Override
	protected Class<MitgliedStatus> getDomainClass() {
		return MitgliedStatus.class;
	}

}
