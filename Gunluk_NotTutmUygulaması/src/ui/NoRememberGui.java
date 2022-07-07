package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UserDao;

public class NoRememberGui extends JDialog {

	public NoRememberGui() {
		initFrame();
	}

	public void initFrame() {
		add(initPanel());
		setSize(400, 250);
		setTitle("Şifremi Unuttum");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel upPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		JPanel downPanel = new JPanel();

		JLabel identityLabel = new JLabel("Kimlik");
		JTextField identityText = new JTextField(11);

		JLabel accountSaveNoLabel = new JLabel("Hesap Kurtarma No");
		JTextField accountSaveNoText = new JTextField(10);

		JLabel passwordLabel = new JLabel("Yeni Şifre");
		JTextField passwordText = new JTextField(10);

		JLabel paswwordReadLabel = new JLabel("Yeni Şifre Tekrar");
		JTextField passwordReadText = new JTextField(10);

		upPanel.add(identityLabel);
		upPanel.add(identityText);
		upPanel.add(accountSaveNoLabel);
		upPanel.add(accountSaveNoText);
		upPanel.add(passwordLabel);
		upPanel.add(passwordText);
		upPanel.add(paswwordReadLabel);
		upPanel.add(passwordReadText);

		mainPanel.add(upPanel);

		ImageIcon saveIcon = new ImageIcon("modulAraButonIkonlari/save_24_Ikon.png");
		JButton saveButon = new JButton("Kaydet", saveIcon);
		getRootPane().setDefaultButton(saveButon);

		ImageIcon exitIcon = new ImageIcon("modulAraButonIkonlari/geri_24_Ikon.png");
		JButton exitButon = new JButton("Çıkış", exitIcon);

		downPanel.add(saveButon);
		downPanel.add(exitButon);

		mainPanel.add(downPanel, BorderLayout.SOUTH);

		saveButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean accountExistControl = UserDao.accountExistControl(identityText.getText());
				boolean accountControl = UserDao.noRememberAccountControl(identityText.getText(),
						accountSaveNoText.getText());

				if (accountExistControl == true) {
					if (accountControl == true) {
						if (passwordText.getText().equals(passwordReadText.getText())) {

							UserDao.passwordUpdate(passwordText.getText(), identityText.getText());
							JOptionPane.showMessageDialog(null, "Şifreniz güncellenmiştir. Bir yere kaydediniz");
							clean(new JTextField[] { identityText, passwordText, passwordReadText, accountSaveNoText });
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Girdiğiniz şifreler aynı olmalı");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Hatalı bilgiler girdiniz, lütfen tekrar kontrol edin");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Kayıtlarımızda böyle bir hesap bulunmamaktadır");
				}

			}
		});

		exitButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		return mainPanel;
	}

	public void clean(JTextField[] textler) {
		for (int i = 0; i < textler.length; i++) {
			textler[i].setText("");
		}
	}

}
