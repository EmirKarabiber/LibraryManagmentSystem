package Library;

import javax.swing.JFrame;

public class NormalUser extends User {

	// Tek parametreli kurucu metod, sadece ismi içerir
	public NormalUser(String name) {
		super(name);
		// Normal kullanıcının gerçekleştirebileceği işlemler tanımlanır
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new PlaceOrder(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit()
		};
	}
	// Üç parametreli kurucu metod, isim, email ve telefon numarasını içerir
	public NormalUser(String name, String email, String phonenumber) {
		super(name, email, phonenumber);
		// Normal kullanıcının gerçekleştirebileceği işlemler tanımlanır
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new PlaceOrder(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit()
		};
	}
	// Menüyü oluşturan metot, kullanıcının gerçekleştirebileceği işlemleri içerir
	@Override
	public void menu(Database database, User user) {
		
		// Menü seçenekleri oluşturuluyor
		String[] data = new String[7];
		data[0] = "Kitapları Görüntüle";
		data[1] = "Kitap Ara";
		data[2] = "Sipariş ver";
		data[3] = "Kitap Ödünç Al";
		data[4] = "Ceza Hesapla";
		data[5] = "Kitap iade et";
		data[6] = "Çıkış";
		
		// Menüyü oluşturan frame oluşturuluyor ve görünür hale getirilir
		JFrame frame = this.frame(data, database, user);
		frame.setVisible(true);
	}
	// Nesnenin String temsilini döndüren metot
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+"Normal";
	}
	
}
