package test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.UserDao;
import ui.LoginGui;
import ui.MainScreenGui;
import ui.ShowSaveDailyGui;

public class Test {

	public static void main(String[] args) {

		// Arayüzler
		// com.jtattoo.plaf.graphite.GraphiteLookAndFeel
		// javax.swing.plaf.nimbus.NimbusLookAndFeel

		// Tablolar
		UserDao.createTable();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * boolean authorizationCheck = userDao.authorizedControl(); if
				 * (authorizationCheck) { new authorizedUserGuý(); } else { new entranceGuý(); }
				 */

			}

		});

		new LoginGui();

	}

}
