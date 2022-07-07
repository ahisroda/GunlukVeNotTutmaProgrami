package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.UserDao;
import domain.UserInformationAndUtilDomain;

public class MainScreenGui extends JFrame {

	public MainScreenGui() {
		initFrame();
	}

	public void initFrame() {

		add(initPanel());
		setJMenuBar(initMenu());
		setSize(700, 600);
		setTitle("Ana Sayfa");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel downPanel = new JPanel(new BorderLayout());
		JPanel dowUpPanel = new JPanel();
		JPanel downDownPanel = new JPanel();

		JEditorPane readFieldArea = new JEditorPane();
		readFieldArea.setBorder(BorderFactory.createTitledBorder("Yazınız"));
		JScrollPane readFilePane = new JScrollPane(readFieldArea);
		readFilePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel.add(readFilePane);

		JLabel tittleLabel = new JLabel("Konu Başlığı");
		JTextField tittleText = new JTextField(40);

		dowUpPanel.add(tittleLabel);
		dowUpPanel.add(tittleText);

		downPanel.add(dowUpPanel, BorderLayout.NORTH);
		downPanel.add(downDownPanel, BorderLayout.SOUTH);

		ImageIcon exitIcon = new ImageIcon("modulAraButonIkonlari/exit_24_Ikon.png");
		JButton exitButon = new JButton("Çıkış", exitIcon);

		ImageIcon noteIcon = new ImageIcon("modulAraButonIkonlari/note_24_icon.png");
		JButton noteButon = new JButton("Kaydedilenler", noteIcon);

		ImageIcon saveIcon = new ImageIcon("modulAraButonIkonlari/save_24_Ikon.png");
		JButton saveButon = new JButton("Kaydet", saveIcon);

		JLabel pageNumberLabel = new JLabel("   Sayfa Sayısı");
		JTextField pageNumberText = new JTextField(3);

		JLabel signatureLabel = new JLabel("  İmza");
		JTextField signatureText = new JTextField(6);

		downDownPanel.add(exitButon);
		downDownPanel.add(noteButon);
		downDownPanel.add(pageNumberLabel);
		downDownPanel.add(pageNumberText);
		downDownPanel.add(signatureLabel);
		downDownPanel.add(signatureText);
		downDownPanel.add(saveButon);

		mainPanel.add(downPanel, BorderLayout.SOUTH);

		saveButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				UserInformationAndUtilDomain dailyAdd = new UserInformationAndUtilDomain();

				boolean signatureControl = UserDao.signatureExistControl(signatureText.getText());

				dailyAdd.setEsasGunluk(readFieldArea.getText());
				dailyAdd.setImza(signatureText.getText());
				dailyAdd.setSayfaSayisi(pageNumberText.getText());
				dailyAdd.setKonuBasligi(tittleText.getText());

				if (signatureControl == true) {
					
					JOptionPane.showMessageDialog(null, "Günlük başarılı bir şekilde kaydedildi");
				} else {
					JOptionPane.showMessageDialog(null, "Hatalı imza!!");
				}

			}
		});

		noteButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowSaveDailyGui();

			}
		});

		exitButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserDao.activityPasive();
				dispose();
				new LoginGui();
			}
		});

		return mainPanel;
	}

	public JMenuBar initMenu() {
		JMenuBar mainMenuBar = new JMenuBar();
		JMenu useMenu = new JMenu("Etkileşimler");
		mainMenuBar.add(useMenu);
		JMenuItem friendsItem = new JMenuItem("Arkadaşlar");
		JMenuItem accountDeleteItem = new JMenuItem("Hesap sil");
		useMenu.add(friendsItem);
		useMenu.add(accountDeleteItem);

		accountDeleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AccountDeleteGui();

			}
		});

		return mainMenuBar;
	}

}
