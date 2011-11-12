package biz.wittkemper.stationmanager.forms.fmain;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;

import javax.swing.JOptionPane;

import biz.wittkemper.stationmanager.forms.fmitgliederverwaltung.FMitgliederVerwaltungController;
import biz.wittkemper.stationmanager.utils.FrameUtils;

public class FMainController {
	FrameUtils frameUtils = new FrameUtils();
	FMitgliederVerwaltungController mitgliederVerwaltungController;

	FMainView view;

	public FMainController() {
		this.view = new FMainView();
		initListener();
	}

	private void initListener() {
		this.view.setBeendenListener(new BeendenListener());
		this.view.setMitgliederListener(new MitgliederListener());
		this.view.addWindowListener(new WindowList());
	}

	public void showView() {
		this.view.setSize(1024, 768);
		this.view.setVisible(true);

	}

	private void AppClose() {
		if (JOptionPane.showConfirmDialog(view,
				"Wollen sie wirklich beenden?", "Frage",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			view.setVisible(false);
			System.exit(0);
		}
	}
	class WindowList implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			AppClose();
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BeendenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AppClose();
		}

		
	}

	class MitgliederListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (mitgliederVerwaltungController == null) {
				mitgliederVerwaltungController = new FMitgliederVerwaltungController(
						10L);
			}

			try {
				view.addFrame(mitgliederVerwaltungController.getFrame());
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			view.repaint();
		}
	}

}
