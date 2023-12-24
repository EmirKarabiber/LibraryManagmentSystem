package Library;

public class Order {
	
	private Book book;
	private User user;
	private double price;
	private int qty;
	
	
    // Parametreli kurucu metod, sipariş bilgilerini alarak bir Order nesnesi oluşturur
	public Order(Book book, User user, double price, int qty) {
		this.book = book;
		this.user = user;
		this.price = price;
		this.qty = qty;
	}
   // Parametresiz kurucu metod, boş bir Order nesnesi oluşturur
	public Order() {}

	// Kitap bilgisini döndüren metot
	public Book getBook() {
		return book;
	}
    // Kitap bilgisini ayarlayan metot
	public void setBook(Book book) {
		this.book = book;
	}
    // Kullanıcı bilgisini döndüren metot
	public User getUser() {
		return user;
	}
    // Kullanıcı bilgisini ayarlayan metot
	public void setUser(User user) {
		this.user = user;
	}
    // Fiyat bilgisini döndüren metot
	public double getPrice() {
		return price;
	}
    // Fiyat bilgisini ayarlayan metot
	public void setPrice(double price) {
		this.price = price;
	}
    // Miktar bilgisini döndüren metot
	public int getQty() {
		return qty;
	}
    // Miktar bilgisini ayarlayan metot
	public void setQty(int qty) {
		this.qty = qty;
	}
	// Nesnenin bilgilerini içeren bir metot
	public String toString() {
		return "Kitap İsmi: "+book.getName()+"\n"+
				"Kullanıcı Adı: "+user.getName()+"\n"+
				"Ücret: "+String.valueOf(price)+"\n"+
				"Satılıp Kopyalar: "+String.valueOf(qty);
	}
	// Nesnenin bilgilerini string formatında döndüren bir metot
	public String toString2() {
		return book.getName()+"<N/>"+user.getName()+"<N/>"+String.valueOf(price)+"<N/>"+
				String.valueOf(qty);
	}

}
