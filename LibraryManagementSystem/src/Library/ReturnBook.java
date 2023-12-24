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

public class ReturnBook implements IOOperation {
    // Kitap iade işlemini gerçekleştiren metot
	@Override
	public void oper(Database database, User user) {
		
		// Kitap iade penceresi oluştur
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());

		// Pencere başlığı oluştur
		JLabel title = Main.title("Kitap İade Et");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		//Panel oluştur
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Kitap İsmi:");
		JTextField name = Main.textfield();
		JButton returnbook = Main.button("Kitabı İade Et");
		JButton cancel = Main.button("Geri");
		panel.add(label);
		panel.add(name);
		panel.add(returnbook);
		panel.add(cancel);
		
		// Kitap iade butonuna action listener ekle
		returnbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 // Gerekli alanların boş olup olmadığı kontrol et
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap ismi boş olamaz!");
					return;
				}
				// Kullanıcının ödünç aldığı kitapları kontrol et
				if (!database.getBrws().isEmpty()) {
					for (Borrowing b : database.getBrws()) {
						if (b.getBook().getName().matches(name.getText().toString()) && 
								b.getUser().getName().matches(user.getName())) {
							Book book = b.getBook();
							int i = database.getAllBooks().indexOf(book);
							// Ödünç alınan kitabın teslim tarihi geçmişse uyarı ver
							if (b.getDaysLeft()>0) {
								JOptionPane.showMessageDialog(new JFrame(), "Ödünç aldığınız kitabın son teslim tarihi geçmiş!\n"
										+ Math.abs(b.getDaysLeft()*50)+" tutarında bir gecikme ücreti ödemeniz gerekmektedir.");
							}
							// Kitabın ödünç alınan kopya sayısı güncelle
							book.setBrwcopies(book.getBrwcopies()+1);
							//Kitabı iade al
							database.returnBook(b, book, i);
							JOptionPane.showMessageDialog(new JFrame(), "Kitap iade edildi\nTeşekkürler!");
							frame.dispose();
							break;
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Bu kitabı ödünç almamışsınız!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Bu kitabı ödünç almamışsınız!");
				}
			}
		});
		// Butonların aktifliği için listener fonkisyonu ekle
		cancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		// Panel pencereye eklenir
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
