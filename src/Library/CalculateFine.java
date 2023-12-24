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

// Gecikme ücreti hesaplama işlemini gerçekleştiren sınıf
public class CalculateFine implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		// Sayfanın başlık kısmını belirle
		JLabel title = Main.title("Gecikme Ücreti Hesapla");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		

		// Kitap ismini girmek için bir form oluştur
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Kitap İsmi:");
		JTextField name = Main.textfield();
		JButton calc = Main.button("Hesapla");
		JButton cancel = Main.button("Geri");
		panel.add(label);
		panel.add(name);
		panel.add(calc);
		panel.add(cancel);
		
		// "Hesapla" butonuna tıklandığında gerçekleşecek olayları tanımla
		calc.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Kitap ismi boş olamaz
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Kitap İsmi boş olamaz!");
					return;
				}		
				boolean g = true;
				// Kullanıcının ödünç aldığı kitaplar arasında gez	
				for (Borrowing b : database.getBrws()) {

					// Kitap ismi ve kullanıcı adı eşleşiyorsa

					if (b.getBook().getName().matches(name.getText().toString()) &&
							b.getUser().getName().matches(user.getName())) {
						
																								
                               // Gün sayısı kontrolü
								if (b.getDaysLeft()>0) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Son teslim tarihini geçirdiniz!\n"+b.getDaysLeft()*50+" tutarında gecikme ücreti ödemelisiniz");
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"Herhangi bir gecikme ücreti bulunmamakta");
							frame.dispose();
						}
						g = false; 
						break;
					}
				}
				
				// Kitap bulunamazsa bilgilendirme mesajı göster
				if (g) {
					JOptionPane.showMessageDialog(new JFrame(),"Bu kitabı ödünç almadınız!");
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
