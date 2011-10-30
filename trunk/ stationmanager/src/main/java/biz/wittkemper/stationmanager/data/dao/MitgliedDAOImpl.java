package biz.wittkemper.stationmanager.data.dao;

import java.util.Date;
import java.util.List;

import biz.wittkemper.stationmanager.data.entity.Mitglied;
import biz.wittkemper.stationmanager.utils.ParameterUtils;

public class MitgliedDAOImpl extends AbstractDAOImpl<Mitglied, Long> implements
		MitgliedDAO {


	@Override
	public void merge (Mitglied mitglied){
		mitglied.setLastChange(new Date());
		super.merge(mitglied);
	}
	@Override
	public void update(Mitglied mitglied){
		mitglied.setLastChange(new Date());
		super.update(mitglied);
	}
	
	@Override
	public void save(Mitglied mitglied){
		if (ParameterUtils.isMasterDB()== true){
			mitglied.setMasterInsert(new Date());
		}
		mitglied.setLastChange(new Date());
		super.save(mitglied);
	}
	
	@Override
	protected Class<Mitglied> getDomainClass() {
		return Mitglied.class;
	}
	
	@Override
	public Mitglied findByNameVorname(String name, String vorname) {
		
		String hsql ="From Mitglied m ";
		hsql +=" Where m.name = '" + name + "' ";
		hsql += "AND m.vorname = '" + vorname + "' ";
		
		List<Mitglied> list = super.findByQueryString(hsql);
		
		if (list.size()> 0){
			return list.get(0);
		}
		return null;
	}

}
