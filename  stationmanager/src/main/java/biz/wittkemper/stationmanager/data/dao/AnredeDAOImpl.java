package biz.wittkemper.stationmanager.data.dao;

import java.util.List;

import biz.wittkemper.stationmanager.data.entity.Anrede;

public class AnredeDAOImpl extends AbstractDAOImpl<Anrede, Integer> implements
		AnredeDAO {

	
	
	@Override
	protected Class<Anrede> getDomainClass() {
		return Anrede.class;
	}

	@Override
	public Anrede getAnredeByText(String text) {
		String hsql = "FROM Anrede a where a.anrede = '" + text + "' ";
		List<Anrede> list = super.findByQueryString(hsql);
		if (list.size()> 0){
			return list.get(0);
		}
		return null;
	}

}
