package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UserDao;

public class LoginGui extends JFrame {

	public LoginGui() {
		initPencere();
	}

	public void initPencere() {
		setTitle("Giris Ekrani");
		add(initPanel());
		setSize(450, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());

		UserDao.activityPasive();

		JPanel ustPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		JPanel altPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		JLabel identityLabel = new JLabel("Kimlik");
		JTextField identityText = new JTextField(15);

		JLabel sifreLabel = new JLabel("�ifre");
		JTextField sifreText = new JTextField(15);

		ustPanel.add(identityLabel);
		ustPanel.add(identityText);
		ustPanel.add(sifreLabel);
		ustPanel.add(sifreText);

		anaPanel.add(ustPanel);

		ImageIcon girisIcon = new ImageIcon("modulAraButonIkonlari/giris_24_Ikon.png");
		JButton girisButon = new JButton("Giris", girisIcon);
		getRootPane().setDefaultButton(girisButon);

		ImageIcon yeniHesapIcon = new ImageIcon("modulAraButonIkonlari/yeniKullanici_24_Ikon.png");
		JButton yeniHesapButon = new JButton("Yeni Hesap Olu�tur", yeniHesapIcon);

		ImageIcon cikisIcon = new ImageIcon("modulAraButonIkonlari/exit_24_Ikon.png");
		JButton cikisButon = new JButton("Cikis", cikisIcon);

		ImageIcon noRememberPasswordIcon = new ImageIcon("modulAraButonIkonlari/malzeme_24_Ikon.png");
		JButton noRememberPasswordButon = new JButton("Şifremi Unuttum", noRememberPasswordIcon);

		altPanel.add(girisButon);
		altPanel.add(yeniHesapButon);
		altPanel.add(noRememberPasswordButon);
		altPanel.add(cikisButon);

		anaPanel.add(altPanel, BorderLayout.SOUTH);

		girisButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean existControl = UserDao.accountExistControl(identityText.getText());
				boolean accountAccessControl = UserDao.accessControl(identityText.getText(), sifreText.getText());

				if (identityText.getText().equals("") || sifreText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Boş alan bırakmayınız...");
				} else {

					if (existControl == true) {
						if (accountAccessControl == true) {
							JOptionPane.showMessageDialog(null, "Giriş Başarılı, iyi eğlenceler");
							UserDao.activityActive(identityText.getText());
							dispose();
							new MainScreenGui();
						} else {
							JOptionPane.showMessageDialog(null, "Hatalı pin veya şifre!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Böyle bir hesabın varlığı şüpheli  :/  ");
					}

				}
			}
		});

		yeniHesapButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new NewAccountGui();
			}
		});

		cikisButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		noRememberPasswordButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new NoRememberGui();
			}
		});

		return anaPanel;

	}
}
