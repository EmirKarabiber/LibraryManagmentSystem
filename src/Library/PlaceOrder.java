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

public class PlaceOrder implements IOOperation {
	// Sipariş verme işlemini gerçekleştiren metot
	@Override
	public void oper(Database database, User user) {
		
		// Sipariş verme penceresi oluştur
		JFrame frame = Main.frame(400, 270);
		frame.setLayout(new BorderLayout());
		
		// Pencere başlığı oluştur
		JLabel title = Main.title("Sipariş ver");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		// Panel oluştur
		JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Kitap ismi:");
		JTextField name = Main.textfield();
		JLabel label2 = Main.label("Kopya Sayısı:");
		JTextField qty = Main.textfield();
		JButton placeorder = Main.button("Sipariş ver");
		JButton cancel = Main.button("Geri");
		panel.add(label);
		panel.add(name);
		panel.add(label2);
		panel.add(qty);
		panel.add(placeorder);
		panel.add(cancel);
		
		// Sipariş verme butonuna action listener ekle
		placeorder.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Gerekli alanların boş olup olmadığı kontrol et
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap ismi boş olamaz!");
					return;
				}
				if (qty.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Satın alınacak kopya sayısı boş olamaz!");
					return;
				}
				// Satın alınacak kopya sayısının sayı olup olmadığı kontrol et
				try {
					Integer.parseInt(qty.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Satın alınacak kopya sayısı değeri sayı türünde olmalı!");
					return;
				}
				// Sipariş oluştur
				Order order = new Order();
				int i = database.getBook(name.getText().toString());
				// Kitap mevcut değilse hata mesajı ver
				if (i<=-1) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap mevcut değil!");
				} else {
					Book book = database.getBook(i);
					order.setBook(book);
					order.setUser(user);
					int qt = Integer.parseInt(qty.getText().toString());
					order.setQty(qt);
					order.setPrice(book.getPrice()*qt);
					int bookindex = database.getBook(book.getName());
					
					if(book.getQty() < qt)
					{
						JOptionPane.showMessageDialog(new JFrame(), "İstediğiniz sayıda satılık kitap bulunmamaktadır.");
						return;
					}

					book.setQty(book.getQty()-qt);
					database.addOrder(order, book, bookindex);
					JOptionPane.showMessageDialog(new JFrame(), "Siparişiniz başarıyla gerçekleşmiştir!");
					frame.dispose();
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
		// Panel pencereye ekle
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}

}
