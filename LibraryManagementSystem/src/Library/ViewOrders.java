package Library;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class ViewOrders implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		// Siparişleri görüntülemek için pencerenin oluşturulması

		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		JLabel title = Main.title("Siparişleri görüntüle");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Kitap İsmi:");
		JTextField name = Main.textfield();
		JButton view = Main.button("Görüntüle");
		JButton cancel = Main.button("Geri");
		panel.add(label);
		panel.add(name);
		panel.add(view);
		panel.add(cancel);

		// Görüntüle butonuna action listener eklenmesi
		view.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Girilen kitap isminin boş olup olmadığının kontrolü
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap ismi boş olamaz!");
					return;
				}
				//Kitabın veritabanında bulunup bulunmadığının kontrolü
				int i = database.getBook(name.getText().toString());
				if (i>-1) {
					// Kitap varsa siparişleri görüntüleme işlemi
					viewOrders(name.getText().toString(), database);
					frame.dispose();
				}else {
					// Kitap yoksa bilgilendirme mesajı
					JOptionPane.showMessageDialog(new JFrame(), "Kitap mevcut değil!");
				}
			}
		});
        // İptal butonuna action listener eklenmesi
		cancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		}
	// Seçilen kitabın siparişlerini görüntüleme işlemi
	private void viewOrders(String bookname, Database database) {
		
		int rows = 1;
		// Kitaba ait sipariş sayısının hesaplanması
		for (Order order : database.getAllOrders()) {
			if (order.getBook().getName().matches(bookname)) {
				rows++;
			}
		}
		// Görüntüleme penceresinin boyutunun belirlenmesi
		int height = rows*55 + 100;
		JFrame frame = Main.frame(500, height);
		
		JLabel title = Main.title("Görüntüle");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows, 4, 0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);
		
		// Tablo başlığının eklenmesi
		String[] row1 = new String[] {"İsim", "Kullanıcı", "Adet", "Ücret"};
		for (String s : row1) {
			JLabel label = label(s);
			panel.add(label);
		}
		// Her bir siparişin bilgilerinin tabloya eklenmesi
		for (Order order : database.getAllOrders()) {
			if (order.getBook().getName().matches(bookname)) {
				JLabel label1 = label(order.getBook().getName());
				JLabel label2 = label(order.getUser().getName());
				JLabel label3 = label(String.valueOf(order.getQty()));
				JLabel label4 = label(String.valueOf(order.getPrice()));
				panel.add(label1);
				panel.add(label2);
				panel.add(label3);
				panel.add(label4);
			}
		}
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);	
	}
	// Tablo hücrelerini oluşturan yardımcı metot
	private JLabel label(String text) {
		JLabel label = Main.label(text);
		label.setBackground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		label.setOpaque(true);
		return label;
	}

}
