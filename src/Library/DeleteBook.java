package Library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Kitap silme işlemini gerçekleştiren sınıf.
public class DeleteBook implements IOOperation {

	//Kitap silme işlemi için kullanıcı arayüzünü oluşturan metot
	@Override
	public void oper(Database database, User user) {
		
		// Yeni bir frame oluşturur
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		// Başlık etiketini oluşturur ve frame'e ekler
		JLabel title = Main.title("Kitap Sil");
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Paneli oluşturur ve frame'e ekler
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		//// Kitap ismi için etiketi ve metin kutusunu oluşturur
		JLabel label = Main.label("Kitap İsmi:");
		JTextField name = Main.textfield();
		// "Kitabı Sil" ve "Geri" butonlarını oluşturur
		JButton delete = Main.button("Kitabı Sil");
		JButton cancel = Main.button("Geri");

		// Oluşturulan bileşenleri panele ekler
		panel.add(label);
		panel.add(name);
		panel.add(delete);
		panel.add(cancel);
		
		// "Kitabı Sil" butonuna tıklandığında yapılacak işlemler
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Kitap ismi boşsa uyarı verir
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap ismi boş olamaz!");
					return;
				}
				// Kitabın veritabanındaki indeksini bulur
				int i = database.getBook(name.getText().toString());
				 // Kitap bulunduysa siler, bulunamazsa uyarı verir
				if (i>-1) {
					database.deleteBook(i);
					JOptionPane.showMessageDialog(new JFrame(), "kitap başarıyla silindi!");
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Bu isimde bir kitap mevcut değil!");
				}
			}
		});
		// "Geri" butonuna tıklandığında frame'i kapatır
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		// Paneli frame'e ekler
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		// Frame'i görünür yapar
		frame.setVisible(true);
		
	}

}
