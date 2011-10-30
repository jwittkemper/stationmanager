package biz.wittkemper.stationmanager.data.dao;

import biz.wittkemper.stationmanager.data.entity.FoerderMitglied;

public class FoerderMitgliedDAOImpl extends
		AbstractDAOImpl<FoerderMitglied, Long> implements FoerderMitgliedDAO {

	@Override
	protected Class<FoerderMitglied> getDomainClass() {
		return FoerderMitglied.class;
	}

}
