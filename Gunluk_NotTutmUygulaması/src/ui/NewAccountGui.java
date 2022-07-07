package ui;

import java.awt.BorderLayout;
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
import domain.UserInformationAndUtilDomain;

public class NewAccountGui extends JDialog {

	public NewAccountGui() {
		initFrame();
	}

	public void initFrame() {
		add(initPanel());
		setSize(400, 320);
		setTitle("Kaydol");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel upPanel = new JPanel(new GridLayout(6, 2, 5, 5));
		JPanel downPanel = new JPanel();

		JLabel nameLabel = new JLabel("Ad-Soyad");
		JTextField nameText = new JTextField(10);

		JLabel identityLabel = new JLabel("Kimlik No");
		JTextField identityText = new JTextField(11);

		JLabel passwordLabel = new JLabel("Şifre");
		JTextField passwordText = new JTextField(10);

		JLabel paswwordReadLabel = new JLabel("Şifre Tekrar");
		JTextField passwordReadText = new JTextField(10);

		JLabel accountSaveNoLabel = new JLabel("Hesap Kurtarma no");
		JTextField accountSaveNoText = new JTextField(10);

		JLabel signatureLabel = new JLabel("İmzanız");
		JTextField signatureText = new JTextField(10);

		upPanel.add(nameLabel);
		upPanel.add(nameText);
		upPanel.add(identityLabel);
		upPanel.add(identityText);
		upPanel.add(passwordLabel);
		upPanel.add(passwordText);
		upPanel.add(paswwordReadLabel);
		upPanel.add(passwordReadText);
		upPanel.add(accountSaveNoLabel);
		upPanel.add(accountSaveNoText);
		upPanel.add(signatureLabel);
		upPanel.add(signatureText);

		mainPanel.add(upPanel);

		ImageIcon applyIcon = new ImageIcon("modulAraButonIkonlari/incele_24_Ikon.png");
		JButton applyButon = new JButton("Kaydet", applyIcon);
		getRootPane().setDefaultButton(applyButon);

		ImageIcon exitIcon = new ImageIcon("modulAraButonIkonlari/geri_24_Ikon.png");
		JButton exitButon = new JButton("Geri", exitIcon);

		downPanel.add(applyButon);
		downPanel.add(exitButon);

		mainPanel.add(downPanel, BorderLayout.SOUTH);

		applyButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				UserInformationAndUtilDomain userAdd = new UserInformationAndUtilDomain();

				boolean accountExistControl = UserDao.accountExistControl(identityText.getText());
				boolean signatureControl = UserDao.signatureExistControl(signatureText.getText());

				userAdd.setAdi(nameText.getText());
				userAdd.setKimlik(identityText.getText());
				userAdd.setImza(signatureText.getText());
				userAdd.setHesapKurtarmaNo(accountSaveNoText.getText());
				userAdd.setSifre(passwordText.getText());
				userAdd.setAktiflik(2);

				if (accountExistControl == true || signatureControl == true) {
					JOptionPane.showMessageDialog(null, "Girdiğiniz kimliğe ait hesap mevcuttur");
				} else {

					if (passwordText.getText().equals(passwordReadText.getText())) {
						UserDao.createAccount(userAdd);

						clean(new JTextField[] { nameText, identityText, signatureText, accountSaveNoText,
								passwordReadText, passwordText });

						JOptionPane.showMessageDialog(null, "Hesabınız oluşmuştur\n" + "Bilgilerinizi kaybetmeyiniz");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Girdiğiniz şifreler aynı değildir");
					}
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
