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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class User {
	
	// Kullanıcı bilgileri ve işlem dizisi
	protected String name;
	protected String email;
	protected String phonenumber;
	protected IOOperation[] operations;
	
	// Parametresiz kurucu
	public User() {}
	
	// İsim bilgisi ile kurucu
	public User(String name) {
		this.name = name;
	}
	// Tüm bilgileri içeren kurucu
	public User(String name, String email, String phonenumber) {
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
	}
    // İsim bilgisini getiren metot
	public String getName() {
		return name;
	}
	// Email bilgisini getiren metot
	public String getEmail() {
		return email;
	}
	// Telefon numarası bilgisini getiren metot
	public String getPhoneNumber() {
		return phonenumber;
	}
    
	// Soyut metotlar
	abstract public String toString();
	
	abstract public void menu(Database database, User user);
	
	// Kullanıcı menüsü penceresi oluşturan metot
	public JFrame frame(String[] data, Database database, User user) {
		JFrame frame = new JFrame();
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Kütüphane Yönetim Sistemi");
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(null);
		frame.setBackground(null);
		
		// Başlık etiketi
		JLabel label1 = Main.title(this.name+" Hoşgeldiniz");
		frame.getContentPane().add(label1, BorderLayout.NORTH);
		
		// Menü seçeneklerini içeren panel
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setLayout(new GridLayout(7, 1, 15, 15));
		panel.setBackground(null);
		
		// Menü seçeneklerini ekleyen döngü
		for (int i=0;i<7;i++) {
			JButton button = new JButton(data[i]);
			button.setFont(new Font("Tahoma", Font.BOLD, 17));
			button.setForeground(Color.white);
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setBackground(Color.decode("#1da1f2"));
			button.setBorder(null);
			int index = i;
			// Her bir düğmeye ActionListener ekleyen kısım
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {		
					operations[index].oper(database, user);

					// Çıkış ya da tüm datayı sil seçeneklerinde pencereyi kapatma
					if (data[index].matches("Çıkış") || data[index].matches("Tüm datayı sil")) {
						frame.dispose();
					}
				}
			});
			panel.add(button);
		}
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		return frame;
	}
	
}
