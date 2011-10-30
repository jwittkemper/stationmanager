package biz.wittkemper.stationmanager;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import biz.wittkemper.stationmanager.data.dao.DAOFactory;
import biz.wittkemper.stationmanager.data.entity.FoerderMitglied;
import biz.wittkemper.stationmanager.data.entity.Mitglied;
import biz.wittkemper.stationmanager.data.entity.MitgliedStatus;
import biz.wittkemper.stationmanager.data.entity.Parameter;
import biz.wittkemper.stationmanager.utils.DateUtils;


/**
 * Unit test for simple App.
 */
public class AppTest{ 

	private static Logger log = Logger.getLogger(AppTest.class);
	@Test
	public void cheDB(){
		
		log.error("Mal sehen was passiert.....");
				
		MitgliedStatus status = new MitgliedStatus();
		status.setBezeichnungLang("Aktiv Löschzug");
		status.setBezeichnungKurz("AL");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(status);
		
		status = new MitgliedStatus();
		status.setBezeichnungLang("Aktiv Reservezug");
		status.setBezeichnungKurz("AR");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(status);
	
		status = new MitgliedStatus();
		status.setBezeichnungLang("Passiv");
		status.setBezeichnungKurz("PA");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(status);
		
		Mitglied mitglied = new Mitglied();
		mitglied.setName("Eckel");
		mitglied.setVorname("Thomas");
		mitglied.setStrasseNr("Feuerdirnstr. 97");
		mitglied.setPlz(59071);
		mitglied.setOrt("Hamm");
		mitglied.setGebDatum( DateUtils.getDate("06.10.1963"));
		mitglied.setTelefonPrivatFest("83386");
		mitglied.setTelefonPrivatMobil("01738137096");
		mitglied.setTelefonDienst("918-2823");
		mitglied.seteMail("feuertom@web.de");
		mitglied.setEintritt(DateUtils.getDate("01.08.1973"));
		mitglied.setStatus(DAOFactory.getInstance().getMitgliedStatusDAO().load(1));
		
		DAOFactory.getInstance().getMitgliedDAO().save(mitglied);
		
		FoerderMitglied fm = new FoerderMitglied();
		fm.setMitglied(mitglied);
		fm.setEintritt(DateUtils.getDate("01.01.1980"));
		
		DAOFactory.getInstance().getFoerderMitgliedDAO().save(fm);
		Assert.assertTrue(fm.getMitglied().getId() > 0);
		
		Parameter parameter = new Parameter();
		parameter.setBezeichnung("MAsterDB");
		parameter.setValue(Boolean.toString(true));
		
		DAOFactory.getInstance().getParameterDAO().save(parameter);
	}

}
