package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
	
	static Scanner s;
	static Database database;

	public static void main(String[] args) {
		
		// Veritabanı nesnesi oluştur
		database = new Database();
		
		 // Ana pencere (frame) oluştur
		JFrame frame = frame(500, 300);
		
		// Ana panel oluştur
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);

		// Başlık etiketi oluştur ve panele ekle
		JLabel title = label("Kütüphane Sistemine Hoş Geldiniz");

		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		// Kullanıcı girişi için gerekli bileşenler oluştur
		JLabel label1 = label("Telefon Numarası:");
		JLabel label2 = label("Email:");
		JTextField phonenumber = textfield();
		JTextField email = textfield();
		JButton login = button("Giriş Yap");
		JButton newUser = button("Yeni Kullanıcı Girişi");
		
		// Giriş ve Yeni Kullanıcı Girişi butonlarına action listener ekle
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
				login(phonenumber.getText().toString(), email.getText().toString(), frame);
			}	
		});
		newUser.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				newuser();
				frame.dispose();
			}	
		});
		// Bileşenler panele eklenir
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);	
		panel.add(login);
		panel.add(newUser);
		
		// Ana pencereye panel eklenir
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
    // Giriş metodu, veritabanında kullanıcı kontrolü yapar
	private static void login(String phonenumber, String email, JFrame frame) {
		int n = database.login(phonenumber, email);
		if (n != -1) {
			User user = database.getUser(n);
			user.menu(database, user);
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı Mevcut değil");
		}
	}
	// Yeni kullanıcı metodu, yeni kullanıcı oluştur
	private static void newuser() {

		// Yeni pencere (frame) oluştur
		JFrame frame = frame(500, 400);
		
		// Yeni panel oluştur
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		// Başlık etiketi oluştur ve panele ekle
		JLabel title = label("Yeni Kullanıcı Oluştur");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JLabel label0 = label("Kullanıcı Adı:");
		JLabel label1 = label("Telefon Numarası:");
		JLabel label2 = label("Email:");
		JTextField name = textfield();
		JTextField phonenumber = textfield();
		JTextField email = textfield();
		JRadioButton admin = radioButton("Admin");
		JRadioButton normaluser = radioButton("Normal Kullanıcı");
		JButton createacc = button("Hesap Oluştur");
		JButton cancel = button("Çıkış");
		
		// Admin ve Normal Kullanıcı seçeneklerini kontrol etme işlemleri
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
		// Bileşenler panele ekle
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
		
		// Hesap oluştur ve İptal butonlarına action listener ekle
		createacc.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {

                
				// Kullanıcı adı boş olmamalı
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı adı boş olamaz!");
					return;
				}
				// Telefon numarası boş olmamalı
				if (phonenumber.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Telefon numarası boş olamaz!");
					return;
				}
				// Email boş olmamalı
				if (email.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Email boş olamaz!");
					return;
				}
				// Hesap türü seç
				if (!admin.isSelected() && !normaluser.isSelected()) {
					JOptionPane.showMessageDialog(new JFrame(), "Lütfen bir hesap türü seçin!");
					return;
				}
				// Kullanıcı adının varlığını kontrol et
				if (database.userExists(name.getText().toString())) {
					JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı adı hali hazırda kullanılıyor!\nBaşka bir Kullanıcı adı deneyin");
					return;
				}
				// Telefon numarası bir tamsayı olmalı
				try {
					int phoneNumber = Integer.parseInt(phonenumber.getText().toString());
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "Telefon numarası geçerli bir tamsayı olmalıdır! Hata: " + ex.getMessage());
					return;
				}

				// Kullanıcı oluştur
				User user;
				if (admin.isSelected()) {
					user = new Admin(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				} else {
					user = new NormalUser(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				}
				
				// Frame kapatılır
				frame.dispose();
				// Kullanıcı veritabanına ekle
				database.AddUser(user);
				// Kullanıcının ana menüsüne geç
				user.menu(database, user);		
						
			}
		});
		// İptal butonuna action listener ekle
		cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//frame kapatılır
				frame.dispose();
			}
		});
		
		//Panel ana panele ekleniyor ve frame görünür hale getirilir
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	// Yeni bir JFrame oluşturup özelliklerini belirleyen metot
	public static JFrame frame(int width, int height) {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Kütüphane Yönetim Sistemi");
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.white);
		frame.getContentPane().setBackground(Color.white);
		return frame;
	}
	// Belirli özelliklere sahip bir JLabel oluşturan metot
	public static JLabel label(String text) {
		JLabel label1 = new JLabel(text);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 17));
		label1.setForeground(Color.black);
		return label1;
	}
	// Belirli özelliklere sahip bir JTextField oluşturan metot
	public static JTextField textfield() {
		JTextField textfield1 = new JTextField();
		textfield1.setFont(new Font("Tahoma", Font.BOLD, 17));
		textfield1.setForeground(Color.black);
		textfield1.setHorizontalAlignment(SwingConstants.CENTER);
		return textfield1;
	}
	// Belirli özelliklere sahip bir JButton oluşturan metot
	public static JButton button(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setForeground(Color.white);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setBackground(Color.decode("#1da1f2"));
		button.setBorder(null);
		return button;
	}
	// Belirli özelliklere sahip bir JRadioButton oluşturan metot
	public static JRadioButton radioButton(String text) {
		JRadioButton btn = new JRadioButton();
		btn.setForeground(Color.black);
		btn.setText(text);
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn.setBackground(null);
		return btn;
	}
	// Belirli özelliklere sahip bir JLabel (başlık) oluşturan metot
	public static JLabel title(String text) {
		JLabel title = Main.label(text);
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		return title;
	}

}
