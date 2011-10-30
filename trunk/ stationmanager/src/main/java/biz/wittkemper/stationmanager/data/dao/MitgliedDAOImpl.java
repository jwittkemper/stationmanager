package biz.wittkemper.stationmanager.data.dao;

import java.util.Date;

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
		// TODO Auto-generated method stub
		return ;
	}

}
