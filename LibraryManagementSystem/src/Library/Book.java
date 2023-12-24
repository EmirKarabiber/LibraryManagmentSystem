package Library;

// Kitap sınıfı, kütüphane uygulamasında kullanılan kitap nesnesini temsil eder
public class Book {
	
	private String name;		//Kitabın başlığı
	private String author;		//Kitabın yazarı
	private String publisher;	//Kitabın yayıncısı
	private String adress;		//Kitabın bulunduğu koleksiyon adresi 
	private String status;		//Kitabın ödünç alınma durumu
	private int qty;			//Satıs için kopya sayısı
	private double price;		//Kitabın fiyatı
	private int brwcopies;		//Ödünc alınabilen kopya sayısı
	
	public Book() {};
	
	// Parametreli yapıcı metot, kitap bilgilerini alır
	public Book(String name, String author, String publisher,
			String adress, int qty, double price, int brwcopies) {
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.adress = adress;
		this.qty = qty;
		this.price = price;
		this.brwcopies = brwcopies;
	}
	// Kitap bilgilerini düzenli bir şekilde string olarak döndüren metot
	public String toString() {
		String text = "Kitap İsmi: " + name+"\n"+
				"Kitap Yazarı: " + author+"\n"+
				"Kitap Yayıncısı: " + publisher+"\n"+
				"Koleksiyon Adresi: " + adress+"\n"+
				"Satılık Kopyalar: " + String.valueOf(qty)+"\n"+
				"Fiyatı: " + String.valueOf(price)+"\n"+
				"Ödünç Alınabilir Kopyalar: " + String.valueOf(brwcopies);
		return text;
	}

	// Getter ve Setter metotları
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBrwcopies() {
		return brwcopies;
	}

	public void setBrwcopies(int brwcopies) {
		this.brwcopies = brwcopies;
	}
	// Kitap bilgilerini özel bir formatta string olarak döndüren metot
	public String toString2() {
		String text = name+"<N/>"+author+"<N/>"+publisher+"<N/>"+adress+"<N/>"+String.valueOf(qty)+
				"<N/>"+String.valueOf(price)+"<N/>"+String.valueOf(brwcopies);
		return text;
	}

}
