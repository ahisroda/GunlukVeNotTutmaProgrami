package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dao.UserDao;
import domain.UserInformationAndUtilDomain;

public class ShowSaveDailyGui extends JDialog {

	public ShowSaveDailyGui() {
		initFrame();
	}

	public void initFrame() {

		add(initPanel());
		setSize(900, 500);
		setTitle("Kayıtlı Notlar");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel downPanel = new JPanel();

		JList oldDailysList = new JList();
		JScrollPane oldDailysPane = new JScrollPane(oldDailysList);
		oldDailysPane.setBorder(BorderFactory.createTitledBorder("Kaydedilmiş Günlükler"));
		oldDailysPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel.add(oldDailysPane, BorderLayout.EAST);

		JEditorPane dailysPane = new JEditorPane();
		JScrollPane dailysScrollPane = new JScrollPane(dailysPane);
		dailysScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel.add(dailysScrollPane);

		ImageIcon saveIcon = new ImageIcon("modulAraButonIkonlari/save_24_Ikon.png");
		JButton saveButon = new JButton("Kaydet", saveIcon);

		ImageIcon deleteIcon = new ImageIcon("modulAraButonIkonlari/sil_24_Ikon.png");
		JButton deleteButon = new JButton("Sil", deleteIcon);

		ImageIcon backIcon = new ImageIcon("modulAraButonIkonlari/geri_24_Ikon.png");
		JButton backButon = new JButton("Geri", backIcon);

		downPanel.add(saveButon);
		downPanel.add(deleteButon);
		downPanel.add(backButon);

		mainPanel.add(downPanel, BorderLayout.SOUTH);

		backButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		return mainPanel;
	}

}
