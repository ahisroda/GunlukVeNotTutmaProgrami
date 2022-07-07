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

public class AccountDeleteGui extends JDialog {

	public AccountDeleteGui() {
		initFrame();
	}

	public void initFrame() {
		add(initPanel());
		setSize(400, 230);
		setTitle("Hesap Silme");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel upPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		JPanel downPanel = new JPanel();

		JLabel identityLabel = new JLabel("Kimlik");
		JTextField identityText = new JTextField(11);

		JLabel signatureLabel = new JLabel("İmza");
		JTextField signatureText = new JTextField(10);

		JLabel passwordLabel = new JLabel("Şifre");
		JTextField passwordText = new JTextField(10);

		upPanel.add(identityLabel);
		upPanel.add(identityText);
		upPanel.add(signatureLabel);
		upPanel.add(signatureText);
		upPanel.add(passwordLabel);
		upPanel.add(passwordText);

		mainPanel.add(upPanel);

		ImageIcon saveIcon = new ImageIcon("modulAraButonIkonlari/sil_24_Ikon.png");
		JButton saveButon = new JButton("Sil", saveIcon);
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
				boolean accountInfoControl = UserDao.deleteAccountInfoControl(identityText.getText(),
						signatureText.getText(), passwordText.getText());

				if (accountExistControl == true) {

					if (accountInfoControl == true) {

						int lastQuestion = JOptionPane.showConfirmDialog(null,
								"Gerçekten Hesabı silmek istiyor musunuz?\n"
										+ "Unutmayın, içindekiler de silinecektir");

						if (lastQuestion == 0) {
							JOptionPane.showMessageDialog(null,
									"Hesabınız ve içindekileri silinmiştir\n" + "Gidişin beni üzüyor :(");
							UserDao.accountDelete(identityText.getText(), signatureText.getText());

							System.exit(0);

						}
						{
							JOptionPane.showMessageDialog(null, "Gitmeni istemem, o halde devam edelim");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Girdiğiniz bilgiler yanlıştır");
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

}
