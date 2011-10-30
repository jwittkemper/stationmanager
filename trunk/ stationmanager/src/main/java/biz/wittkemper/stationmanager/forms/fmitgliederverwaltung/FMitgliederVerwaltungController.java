package biz.wittkemper.stationmanager.forms.fmitgliederverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;

import com.jgoodies.validation.ValidationResult;

import biz.wittkemper.stationmanager.data.dao.DAOFactory;
import biz.wittkemper.stationmanager.data.validation.MitgliedValidator;
import biz.wittkemper.stationmanager.utils.FrameUtils;

public class FMitgliederVerwaltungController {

	FrameUtils frameUtils = new FrameUtils();
	FMitgliederVerwaltungView view;
	MitgliedModel model;
	
	public FMitgliederVerwaltungController(Long id) {
		super();
		try {
			initClass();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadData(id);
	}

	public FMitgliederVerwaltungController() {
		
			try {
				initClass();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void initClass() throws PropertyVetoException {

		this.view = new FMitgliederVerwaltungView();
		this.model = view.getModel();
		initListener();
	}

	private void loadData(Long id) {

		model.setMitglied(DAOFactory.getInstance().getMitgliedDAO().load(1L));

	}

	private void initListener() {
		this.view.setBeendenListener(new BeendenListener());
		this.view.setSaveListener(new SaveListener());

	}

	public JInternalFrame getFrame() throws PropertyVetoException {
		this.view.show();
		this.view.setSelected(true);

		try {
			this.view.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.view;
	}

	class SaveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.trigger.triggerCommit();
			MitgliedValidator validator = new MitgliedValidator();
			ValidationResult res = validator.validate(model);
			
			System.out.println(res.getMessagesText());
		}
		
	}
	class BeendenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.setVisible(false);
			view.dispose();

		}

	}

}
