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

public class Search implements IOOperation {
// Kitap arama işlemini gerçekleştiren metot

	@Override
	public void oper(Database database, User user) {
		
		// Arama penceresi oluştur
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		// Pencere başlığı oluştur
		JLabel title = Main.title("Ara");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		// Panel oluştur
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Kitap İsmi:");
		JTextField name = Main.textfield();
		JButton search = Main.button("Ara");
		JButton cancel = Main.button("Geri");
		panel.add(label);
		panel.add(name);
		panel.add(search);
		panel.add(cancel);
		
		// Ara butonuna action listener ekle
		search.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Gerekli alanların boş olup olmadığı kontrol ed
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap ismi boş olamaz!");
					return;
				}
				// Kitap adına göre veritabanında arama yap
				int i = database.getBook(name.getText().toString());
				if (i>-1) {
					// Kitap bulunduysa bilgileri göster
					JOptionPane.showMessageDialog(new JFrame(),database.getBook(i).toString());
					frame.dispose();
				} else {
					// Kitap bulunamadıysa uyarı ver
					JOptionPane.showMessageDialog(new JFrame(), "Bu isimde bir kitap mevcut değil!");
				}
			}
		});
		// İptal butonuna action listener ekle
		cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		// Panel pencereye eklenir.
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}

}
