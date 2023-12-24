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

// Yeni bir kitap eklemek için kullanılan sınıf.
public class AddBook implements IOOperation {

	// IOOperation arayüzünden gelen oper() metodunu implement et.
	@Override
	public void oper(Database database, User user) {
		
		 // Yeni bir pencere oluştur.
		JFrame frame = Main.frame(500, 600);
		
		// Sayfanın başlık kısmı belirle.
		JLabel title = Main.title("Yeni Kitap Ekle");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		// Kitap bilgilerini girmek için bir form oluştur.
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);
		
		// Etiketler ve metin alanları oluştur.
		JLabel label1 = Main.label("Kitap İsmi:");
		JLabel label2 = Main.label("Kitap Yazarı:");
		JLabel label3 = Main.label("Kitap Yayıncısı:");
		JLabel label4 = Main.label("Koleksiyon Adresi:");
		JLabel label5 = Main.label("Satılık Kopyalar:");
		JLabel label6 = Main.label("Fiyatı:");
		JLabel label7 = Main.label("Ödünç için Kopyalar:");
		
		JTextField name = Main.textfield();
		JTextField author = Main.textfield();
		JTextField pub = Main.textfield();
		JTextField bca = Main.textfield();
		JTextField qty = Main.textfield();
		JTextField price = Main.textfield();
		JTextField brwcpis = Main.textfield();
		
		JButton addbook = Main.button("Kitabı Ekle");
		JButton cancel = Main.button("Geri");
		
		// Forma etiketleri ve metin alanlarını ekle.
		panel.add(label1);
		panel.add(name);
		panel.add(label2);
		panel.add(author);
		panel.add(label3);
		panel.add(pub);
		panel.add(label4);
		panel.add(bca);
		panel.add(label5);
		panel.add(qty);
		panel.add(label6);
		panel.add(price);
		panel.add(label7);
		panel.add(brwcpis);
		panel.add(addbook);
		panel.add(cancel);
		
		// "Kitabı Ekle" butonuna tıklandığında gerçekleşecek olayları tanımla.
		addbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 // Gerekli alanların boş olup olmadığını kontrol et.

				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap ismi boş olamaz!");
					return;
				}
				if (author.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap yazarı boş olamaz!");
					return;
				}
				if (pub.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap yayıncısı boş olamaz!");
					return;
				}
				if (bca.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "koleksiyon Adresi boş olamaz!");
					return;
				}
				if (qty.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Satılık kopyalar boş olamaz!");
					return;
				}
				if (price.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Ücret boş olamaz!");
					return;
				}
				if (brwcpis.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Ödünç Alınabilir kopyalar boş olamaz!");
					return;
				}
				// Satılık kopyalar, ücret ve ödünç alınabilir kopyaların sayısal olup olmadığını kontrol et.
				try {
					Integer.parseInt(qty.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Satılık kopyaların değeri sayı türünde olmalı!");
					return;
				}
				try {
					Double.parseDouble(price.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "ücret değeri sayı türünde olmalı");
					return;
				}
				try {
					Integer.parseInt(brwcpis.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Ödünç alınabilir kopyalar değeri sayı türünde olmalı!");
					return;
				}
				// Kitap objesini oluşturup veritabanına ekle.
				Book book = new Book();
				if (database.getBook(name.getText().toString())>-1) {
					JOptionPane.showMessageDialog(new JFrame(), "Bu isimde bir kitap mevcut!");
					return;
				} else {
					book.setName(name.getText().toString());
					book.setAuthor(author.getText().toString());
					book.setPublisher(pub.getText().toString());
					book.setAdress(bca.getText().toString());
					book.setQty(Integer.parseInt(qty.getText().toString()));
					book.setPrice(Double.parseDouble(price.getText().toString()));
					book.setBrwcopies(Integer.parseInt(brwcpis.getText().toString()));
					database.AddBook(book);
					JOptionPane.showMessageDialog(new JFrame(), "Kitap Başarıyla Eklendi");
					frame.dispose(); 
					// Pencereyi kapatma.
				}
			}
		});
		// "Geri" butonuna tıklandığında gerçekleşecek olayları tanımla.
		cancel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();	
			}
		});
		// Panel ve bileşenleri ana pencereye ekle.
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);  //Pencereyi görünür yapma.
	}
	
	

}
