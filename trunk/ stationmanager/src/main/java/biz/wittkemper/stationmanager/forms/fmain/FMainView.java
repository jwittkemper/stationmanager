package biz.wittkemper.stationmanager.forms.fmain;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import biz.wittkemper.stationmanager.utils.FrameUtils;

public class FMainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863693692877441385L;

	FrameUtils frameUtils = new FrameUtils();
	JMenuBar menuBar;
	JMenu mnDatei;
	JMenuItem mntmBeenden;
	JMenu mnVerwaltung;
	JMenuItem mntmMitgliederverwaltung;
	JDesktopPane pane = new JDesktopPane();
	/**
	 * Create the frame.
	 */
	public FMainView() {
		initForm();
	}

	private void initForm() {
		setTitle("StationManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 617);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		mntmBeenden = new JMenuItem("beenden");
		mnDatei.add(mntmBeenden);

		mnVerwaltung = new JMenu("Verwaltung");
		menuBar.add(mnVerwaltung);

		mntmMitgliederverwaltung = new JMenuItem("Mitgliederverwaltung");
		mnVerwaltung.add(mntmMitgliederverwaltung);
		frameUtils.MaximiseFrame(this);
		setContentPane(pane);

	}

	public void addFrame(JInternalFrame iframe) {
		pane.add(iframe);
		pane.getDesktopManager().maximizeFrame(iframe);
		try {
			iframe.setSelected(true);
			iframe.setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


	protected void setBeendenListener(ActionListener al) {
		mntmBeenden.addActionListener(al);
	}

	protected void setMitgliederListener(ActionListener al) {
		mntmMitgliederverwaltung.addActionListener(al);
	}
}
