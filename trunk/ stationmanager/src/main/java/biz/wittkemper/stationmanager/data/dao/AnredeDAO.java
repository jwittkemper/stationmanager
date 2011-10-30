package biz.wittkemper.stationmanager.data.dao;

import biz.wittkemper.stationmanager.data.entity.Anrede;

public interface AnredeDAO extends AbstractDAO<Anrede, Integer> {

	Anrede getAnredeByText(String text);

}
