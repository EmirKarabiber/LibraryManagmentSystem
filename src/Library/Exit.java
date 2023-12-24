package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Exit implements IOOperation {
	
	Database database;

	@Override
	public void oper(Database database, User user) {
		// Yeni bir JFrame oluşturulur
		JFrame frame = Main.frame(500, 300);
		
		// Database nesnesi başlatılır
		this.database = new Database();
		
		// Ana panel oluşturuluyor ve düzeni ayarlanır
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		// Başlık etiketi oluşturuluyor ve düzeni ayarlanır
		JLabel title = Main.label("Kütüphane yönetim sistemine hoş geldiniz");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		 // Kullanıcı girişi için gerekli bileşenler oluşturulur
		JLabel label1 = Main.label("Telefon Numarası:");
		JLabel label2 = Main.label("Email:");
		JTextField phonenumber = Main.textfield();
		JTextField email = Main.textfield();
		JButton login = Main.button("Giriş yap");
		JButton newUser = Main.button("Yeni Kullanıcı");
		
		// Giriş yap butonuna tıklanınca gerçekleşecek işlemler
		login.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (phonenumber.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Telefon numarası boş olamaz!");
					return;
				}
				if (email.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Email boş olamaz!");
					return;
				}
				// Giriş yapma işlemi
				login(phonenumber.getText().toString(), email.getText().toString(), frame);
			}	
		});

		// Yeni kullanıcı oluştur butonuna tıklanınca gerçekleşecek işlemler
		newUser.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// Yeni kullanıcı oluşturma ekranını açma
				newuser();
				frame.dispose();
			}	
		});
		// Bileşenler panel üzerine ekle
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);	
		panel.add(login);
		panel.add(newUser);
		
		// Ana panel JFrame'e eklenir
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		// JFrame görünür hale getirilir
		frame.setVisible(true);
	}
	// Kullanıcı girişi için işlemleri gerçekleştiren metot
	private void login(String phonenumber, String email, JFrame frame) {
		// Database üzerinden giriş kontrolü
		int n = database.login(phonenumber, email);
		if (n != -1) {
			 // Giriş başarılıysa ilgili kullanıcının menüsünü açma
			User user = database.getUser(n);
			user.menu(database, user);
			frame.dispose();
		} else {
			// Kullanıcı mevcut değilse hata mesajı gösterme
			JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı mevcut değil");
		}
	}
	// Yeni kullanıcı oluşturmak için ekranı açan metot
	private void newuser() {
		
		JFrame frame = Main.frame(500, 400);

		// Yeni kullanıcı oluşturmak için gerekli bileşenler oluşturulur
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		// Başlık etiketi oluşturuluyor ve düzeni ayarlanır
		JLabel title = Main.label("Yeni Hesap Oluştur");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		// Yeni kullanıcı oluşturmak için gerekli bileşenler oluşturulur
		JLabel label0 = Main.label("İsim:");
		JLabel label1 = Main.label("Telefon Numarası:");
		JLabel label2 = Main.label("Email:");
		JTextField name = Main.textfield();
		JTextField phonenumber = Main.textfield();
		JTextField email = Main.textfield();
		JRadioButton admin = Main.radioButton("Admin");
		JRadioButton normaluser = Main.radioButton("Normal Kullanıcı");
		JButton createacc = Main.button("Yeni Hesap Oluştur");
		JButton cancel = Main.button("Geri");
		
		// Admin ve normal kullanıcı seçeneklerinden sadece bir tanesinin seçilebilmesini sağlama
		admin.addActionListener(e -> {
			if (normaluser.isSelected()) {
				normaluser.setSelected(false);
			}
		});
		normaluser.addActionListener(e -> {
			if (admin.isSelected()) {
				admin.setSelected(false);
			}
		});
		
		// Bileşenler panel üzerine eklenir
		panel.add(label0);
		panel.add(name);
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);
		panel.add(admin);
		panel.add(normaluser);
		panel.add(createacc);
		panel.add(cancel);
		
		// Yeni hesap oluştur butonuna tıklanınca gerçekleşecek işlemler
		createacc.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// Kullanıcı adı daha önce kullanılmış mı diye kontrol
				if (database.userExists(name.getText().toString())) {
					JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı Adı daha önce kullanılmış!\nBaşka bir kullanıcı adı deneyiniz");
					return;
				}
				 // Kullanıcı adı boş olamaz kontrolü			
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı adı boş olamaz!");
					return;
				}
				 // Telefon numarası boş olamaz kontrolü
				if (phonenumber.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Telefon numarası boş olamaz!");
					return;
				}
				// Email boş olamaz kontrolü
				if (email.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Email boş olamaz!");
					return;
				}
				// Hesap türü seçilmiş mi kontrolü
				if (!admin.isSelected() && !normaluser.isSelected()) {
					JOptionPane.showMessageDialog(new JFrame(), "Lütfen bir hesap türü seçiniz!");
					return;
				}
				// Yeni kullanıcı oluştur
				User user;
				if (admin.isSelected()) {
					user = new Admin(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				} else {
					user = new NormalUser(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				}
				// Frame kapat ve kullanıcının menüsünü aç
				frame.dispose();
				database.AddUser(user);
				user.menu(database, user);		
			}
		});
		// İptal butonuna tıklanınca gerçekleşecek işlemler
		cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		// Yeni panel ana panel üzerine eklenir
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
