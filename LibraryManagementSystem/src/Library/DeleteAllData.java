package Library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Tüm veriyi silme işlemini gerçekleştiren sınıf.
public class DeleteAllData implements IOOperation {

	// Veritabanından tüm veriyi silme işlemini başlatan metot.
	@Override
	public void oper(Database database, User user) {
		
		// Yeni bir frame oluşturur
		JFrame frame = Main.frame(600, 150);
		frame.setLayout(new BorderLayout());
		
		 // Başlık etiketini oluşturur ve frame'e ekler
		JLabel title = Main.title("Tüm datayı silmek istediğinize emin misiniz?");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		// Buton panelini oluşturur ve frame'e ekler
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
		panel.setBackground(null);
		
		// "Devam et" butonunu oluşturur ve panel'e ekler
		JButton del = Main.button("Devam et");
		 // "Geri" butonunu oluşturur ve panel'e ekler
		JButton cancel = Main.button("Geri");
		
		// Butonlara action listener ekler
		panel.add(del);
		panel.add(cancel);
		
		// "Devam et" butonuna tıklandığında yapılacak işlemler
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Veritabanından tüm veriyi siler
				database.deleteAllData();
				// Frame'i kapatır
				frame.dispose();
                // Çıkış işlemini başlatan sınıfı çağırır
				new Exit().oper(database, user);
			}
		});
		
		// "Geri" butonuna tıklandığında yapılacak işlemler
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Frame'i kapatır
				frame.dispose();
				// Kullanıcı menüsünü çağırır
				user.menu(database, user);
			}
		});
		// Paneli frame'e ekler
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		// Frame'i görünür yapar
		frame.setVisible(true);
		
	}
	
}
