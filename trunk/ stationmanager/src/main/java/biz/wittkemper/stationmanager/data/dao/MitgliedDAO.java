package biz.wittkemper.stationmanager.data.dao;

import biz.wittkemper.stationmanager.data.entity.Mitglied;

public interface MitgliedDAO extends
		AbstractDAO<biz.wittkemper.stationmanager.data.entity.Mitglied, Long> {

	Mitglied findByNameVorname(String name, String vorname);

}
