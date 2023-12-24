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

public class BorrowBook implements IOOperation {

	 // IOOperation arayüzünden gelen oper() metodunu implement et.
	@Override
	public void oper(Database database, User user) {
		
		// Yeni bir pencere oluştur
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		// Sayfanın başlık kısmını belirle
		JLabel title = Main.title("Kitap Ödünç Al");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		 // Kitap ismi girişi için bir form oluştur
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Kitap İsmi:");
		JTextField name = Main.textfield();
		JButton borrow = Main.button("Kitap Ödünç Al");
		JButton cancel = Main.button("Geri");
		panel.add(label);
		panel.add(name);
		panel.add(borrow);
		panel.add(cancel);
		
		// "Kitap Ödünç Al" butonuna tıklandığında gerçekleşecek olayları tanımla
		borrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Kitap isminin boş olup olmadığını kontrol et
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap ismi boş olamaz!");
					return;
				}
				 // Veritabanında kitabı ara
				int i = database.getBook(name.getText().toString());
				if (i>-1) {
					Book book = database.getBook(i);
					boolean n = true;

					// Kullanıcının daha önce aynı kitabı ödünç almış olup olmadığını kontrol et
					for (Borrowing b : database.getBrws()) {
						if (b.getBook().getName().matches(name.getText().toString()) && 
								b.getUser().getName().matches(user.getName())) {
							n = false;
							JOptionPane.showMessageDialog(new JFrame(),
									"Bu kitabı daha önce ödünç almıştınız!");
						}
					}

					 // Eğer daha önce ödünç alınmamışsa işlemi gerçekleştir
					if (n) {
						if (book.getBrwcopies()>1) {
							Borrowing borrowing = new Borrowing(book, user);
							book.setBrwcopies(book.getBrwcopies()-1);
							database.borrowBook(borrowing, book, i);
							JOptionPane.showMessageDialog(new JFrame(),
									"Kitabı 14 gün içerisinde iade etmeniz gerekmektedir.\n"
											+ "Son Tarih: "+borrowing.getFinish()+"\nEğlenceler!\n");
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"Şuanlık belirtilen kitap ödünç alınamaz");
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap bulunamadı!");
				}
			}
		});

		// "Geri" butonuna tıklandığında gerçekleşecek olayları tanımla
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		// Panel ve bileşenleri ana pencereye ekle
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
