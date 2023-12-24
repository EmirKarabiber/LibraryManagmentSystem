package Library;

import javax.swing.JFrame;

// İsim bilgisini alan yapıcı metot
public class Admin extends User {
	
	public Admin(String name) {
		super(name);
		// Admin işlemlerini içeren IOOperation dizisini oluştur
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new DeleteBook(),
				new Search(),
				new DeleteAllData(),
				new ViewOrders(),
				new Exit()
		};
	}
	// İsim, e-posta ve telefon numarası bilgilerini alan yapıcı metot
	public Admin(String name, String email, String phonenumber) {
		super(name, email, phonenumber);

		// Admin işlemlerini içeren IOOperation dizisini oluştur
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new DeleteBook(),
				new Search(),
				new DeleteAllData(),
				new ViewOrders(),
				new Exit()
		};
	}
	
	// Kullanıcıya özel menüyü oluşturan metot
	@Override
	public void menu(Database database, User user) {
		String[] data = new String[7];
		data[0] = "Kitapları Görüntüle";
		data[1] = "Kitap Ekle";
		data[2] = "Kitap Sil";
		data[3] = "Kitap Ara";
		data[4] = "Tüm Datayı Sil";
		data[5] = "Siparişleri Görüntüle";
		data[6] = "Çıkış";
		
		// Menüyü oluşturan pencereyi al
		JFrame frame = this.frame(data, database, user);
		frame.setVisible(true);
	}
	
	// Admin sınıfının özel toString metodu
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+"Admin";
	}

}
