package biz.wittkemper.stationmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import biz.wittkemper.stationmanager.data.dao.DAOFactory;
import biz.wittkemper.stationmanager.data.entity.Anrede;
import biz.wittkemper.stationmanager.data.entity.FoerderMitglied;
import biz.wittkemper.stationmanager.data.entity.Mitglied;
import biz.wittkemper.stationmanager.data.entity.MitgliedStatus;
import biz.wittkemper.stationmanager.utils.DateUtils;

public class ExcelImport {

	@Test
	public void test() throws IOException {

		Anrede frau = new Anrede();
		frau.setAnrede("Frau");

		Anrede herr = new Anrede();
		herr.setAnrede("Herr");
		DAOFactory.getInstance().getAnredeDAO().save(frau);
		DAOFactory.getInstance().getAnredeDAO().save(herr);

		MitgliedStatus statusAL = new MitgliedStatus();
		statusAL.setBezeichnungLang("Aktiv Löschzug");
		statusAL.setBezeichnungKurz("AL");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusAL);

		MitgliedStatus statusAR = new MitgliedStatus();
		statusAR.setBezeichnungLang("Aktiv Reservezug");
		statusAR.setBezeichnungKurz("AR");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusAR);

		JFileChooser chooser = new JFileChooser();

		int ret = chooser.showDialog(null, "übernehmen");

		if (ret == JFileChooser.APPROVE_OPTION) {
			InputStream filein = new FileInputStream(chooser.getSelectedFile());

			HSSFWorkbook wb = new HSSFWorkbook(filein);

			for (int id = 0; id < 10; id++) {
				if (id == 0 || id == 1) {
					HSSFSheet sheet = wb.getSheetAt(id);

					int max = sheet.getLastRowNum();

					for (int i = 1; i < max; i++) {
						HSSFRow row = sheet.getRow(i);

						Mitglied mitglied = getMitgliedFromRow(row);
						mitglied.setAnrede(herr);
						if (id == 0) {
							mitglied.setStatus(statusAL);
						} else if (id == 1) {
							mitglied.setStatus(statusAR);
						}
						if (mitglied.getName().length() > 0) {
							DAOFactory.getInstance().getMitgliedDAO()
									.save(mitglied);
						}

					}
				}
			}
			inputFoerderVerein(wb.getSheetAt(2));
		}
	}

	private void inputFoerderVerein(HSSFSheet sheet) {

		int max = sheet.getLastRowNum();
		System.out.println(sheet.toString());
		for (int i = 1; i < max; i++) {
			HSSFRow row = sheet.getRow(i);
			Mitglied mitglied = getFoerderMitgliedFromRow(row);
			if (mitgliedExist(mitglied)){
				FoerderMitglied foerderMitglied = new FoerderMitglied();
				foerderMitglied.setMitglied(mitglied);
				DAOFactory.getInstance().getFoerderMitgliedDAO().save(foerderMitglied);
			}
		}

	}

	private boolean mitgliedExist(Mitglied mitglied) {
		Mitglied md = DAOFactory.getInstance().getMitgliedDAO().findByNameVorname(mitglied.getName(), mitglied.getVorname());
		
		return false;
	}

	private Mitglied getFoerderMitgliedFromRow(HSSFRow row) {
		Mitglied mitglied = new Mitglied();

		for (int j = 1; j <= 11; j++) {
			switch (j) {

			case 1:
				mitglied.setAnrede(getAnrede(row.getCell(j)));
				System.out.println(mitglied.getName());
				break;
			case 2:
				mitglied.setName(row.getCell(j).toString());
				break;
			case 3:
				mitglied.setVorname(row.getCell(j).toString());
				break;
			case 4:
				mitglied.setStrasseNr(row.getCell(j).toString());
				break;
			case 5:
				String plz =row.getCell(j).toString().substring(0, 5);
				
				mitglied.setPlz(Integer.parseInt(plz));
				break;

			case 6:
				mitglied.setOrt(row.getCell(j).toString());
				break;
			case 7:
				String datum = row.getCell(j).toString();

				mitglied.setGebDatum(DateUtils.getDate(datum));
				break;
			case 8:
				if (null != row.getCell(j).toString()) {
					mitglied.setEintritt(DateUtils.getDate(row.getCell(j)
							.toString()));
				}
				break;
			case 9:
				String telefon = row.getCell(j).toString();
				String[] tel = telefon.split("/");
				mitglied.setTelefonPrivatFest(tel[0].trim());
				if (tel.length > 1)
					mitglied.setTelefonPrivatMobil(tel[1].trim());
				break;
			case 10:
				if (null != row.getCell(j).toString()) {
					mitglied.setTelefonDienst(row.getCell(j).toString().trim());
				}
				break;

			}

		}
		return mitglied;
	}

	private Anrede getAnrede(HSSFCell cell) {
		String text = cell.toString();

		return DAOFactory.getInstance().getAnredeDAO().getAnredeByText(text);
	}

	private Mitglied getMitgliedFromRow(HSSFRow row) {
		Mitglied mitglied = new Mitglied();

		for (int j = 0; j <= 11; j++) {
			switch (j) {

			case 0:
				mitglied.setName(row.getCell(j).toString());
				System.out.println(mitglied.getName());
				break;
			case 1:
				mitglied.setVorname(row.getCell(j).toString());
				break;
			case 2:
				mitglied.setStrasseNr(row.getCell(j).toString());
				break;
			case 3:
				mitglied.setPlz(59071);
				break;
			case 4:
				mitglied.setOrt("Hamm");
				break;

			case 7:
				String datum = row.getCell(j).toString();

				mitglied.setGebDatum(DateUtils.getDate(datum));
				break;
			case 8:
				String telefon = row.getCell(j).toString();
				String[] tel = telefon.split("/");
				mitglied.setTelefonPrivatFest(tel[0].trim());
				if (tel.length > 1)
					mitglied.setTelefonPrivatMobil(tel[1].trim());
				break;
			case 9:
				if (null != row.getCell(j).toString()) {
					mitglied.setTelefonDienst(row.getCell(j).toString().trim());
				}
				break;
			case 10:
				String mail = row.getCell(j).toString();
				if (mail != null) {
					mitglied.seteMail(mail);
				}
				break;
			case 11:
				if (null != row.getCell(j).toString()) {
					mitglied.setEintritt(DateUtils.getDate(row.getCell(j)
							.toString()));
				}
				break;
			}

		}
		return mitglied;
	}

}
