package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Bu sınıf, kütüphane uygulamasındaki veritabanı işlemlerini yönetir.
public class Database {
	
	// Kullanıcılar, kitaplar, siparişler ve ödünç alınan kitaplar listeleri
	private ArrayList<User> users;
	private ArrayList<String> usernames;
	private ArrayList<Book> books;
	private ArrayList<String> booknames;
	private ArrayList<Order> orders;
	private ArrayList<Borrowing> borrowings;
	
	// Dosya yolları ve dosya objeleri
	private File usersfile = new File("C:\\Library Management System\\Data\\Users");
	private File booksfile = new File("C:\\Library Management System\\Data\\Books");
	private File ordersfile = new File("C:\\Library Management System\\Data\\Orders");
	private File borrowingsfile = new File("C:\\Library Management System\\Data\\Borrowings");
	private File folder = new File("C:\\Library Management System\\Data");
	
	 //Database sınıfının yapıcı metodu.
	public Database() {
		 // Dosya ve klasör kontrolü

		if (!folder.exists()) {
			folder.mkdirs();
		}
		if (!usersfile.exists()) {
			try {
				usersfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!booksfile.exists()) {
			try {
				booksfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!ordersfile.exists()) {
			try {
				ordersfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!borrowingsfile.exists()) {
			try {
				borrowingsfile.createNewFile();
			} catch (Exception e) {}
		}
		
		// Listelerin oluşturulması
		users = new ArrayList<User>();
		usernames = new ArrayList<String>();
		books = new ArrayList<Book>();
		booknames = new ArrayList<String>();
		orders = new ArrayList<Order>();
		borrowings = new ArrayList<Borrowing>();
		
		// Dosyalardan verilerin okunması
		getUsers();
		getBooks();
		getOrders();
		getBorrowings();
	}
	
	public void AddUser(User s) {
		users.add(s);
		usernames.add(s.getName());
		saveUsers(); //kullanıcı listesini dosyaya kaydeder.
	}
	
	public int login(String phonenumber, String email) {
		int n = -1;
		//Kullanıcıları kontrol et ve giriş yap.
		for (User s : users) {
			if (s.getPhoneNumber().matches(phonenumber) && s.getEmail().matches(email)) {
				n = users.indexOf(s);
				break;
			}
		}
		return n;
	}
	//Belirtilen idexteki kullanıcıyı getirir.
	public User getUser(int n) {
		return users.get(n);
	}
	
	public void AddBook(Book book) {
		 // Kitap listesine kitap ekler ve dosyaya kaydeder.
		books.add(book);
		booknames.add(book.getName());
		saveBooks();
	}
	
	//Kullanıcıları dosyadan okur ve liste oluşturur.
	private void getUsers() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			//Hata durumunda hata mesajını yazdırır
			System.err.println(e.toString());
		}
		
		 // Dosyadan okunan kullanıcı bilgilerini parse eder ve listeye ekler
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewUser/>");
			for (String s : a1) {
				String[] a2 = s.split("<N/>");
				if (a2[3].matches("Admin")) {
					User user = new Admin(a2[0], a2[1], a2[2]);
					users.add(user);
					usernames.add(user.getName());
				} else {
					User user = new NormalUser(a2[0], a2[1], a2[2]);
					users.add(user);
					usernames.add(user.getName());
				}
			}
		}
	}
	
	//Kullanıcıları dosyaya kaydeder.
	private void saveUsers() {
		String text1 = "";
		// Kullanıcıları dosyaya yazmak için string oluşturur
		for (User user : users) {
			text1 = text1 + user.toString()+"<NewUser/>\n";
		}
		try {
			// Dosyaya yazma işlemini gerçekleştirir
			PrintWriter pw = new PrintWriter(usersfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
            //Hata durumunda hata mesajını yazdırır. 
			System.err.println(e.toString());
		}
	}
	
	/*Kitapları dosyaya kaydeder. */

	private void saveBooks() {
		// Kitapları dosyaya yazmak için string oluşturur
		String text1 = "";
		for (Book book : books) {
			text1 = text1 + book.toString2()+"<NewBook/>\n";
		}
		try {
			 // Dosyaya yazma işlemini gerçekleştirir
			PrintWriter pw = new PrintWriter(booksfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			 // Hata durumunda hata mesajını yazdırır
			System.err.println(e.toString());
		}
	}
	
	/*Kitapları dosyadan okur ve liste oluşturur. */

	private void getBooks() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			// Hata durumunda hata mesajını yazdırır
			System.err.println(e.toString());
		}
		 // Dosyadan okunan kitap bilgilerini parse eder ve listeye ekler
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewBook/>");
			for (String s : a1) {
				Book book = parseBook(s);
				books.add(book);
				booknames.add(book.getName());
			}
		}
	}
	
	/*Verilen stringi kullanarak kitap nesnesini oluşturur */

	public Book parseBook(String s) {
		String[] a = s.split("<N/>");
		Book book = new Book();
		book.setName(a[0]);
		book.setAuthor(a[1]);
		book.setPublisher(a[2]);
		book.setAdress(a[3]);
		book.setQty(Integer.parseInt(a[4]));
		book.setPrice(Double.parseDouble(a[5]));
		book.setBrwcopies(Integer.parseInt(a[6]));
		return book;
	}
	/*Tüm kitapları içeren bir liste döndürür. */

	public ArrayList<Book> getAllBooks() {
		return books;
	}
	
	//Belirtilen kitap adına göre kitabın indeksini getirir.

	public int getBook(String bookname) {
		int i = -1;
		for (Book book : books) {
			if (book.getName().matches(bookname)) {
				i = books.indexOf(book);
			}
		}
		return i;
	}
	//Belirtilen indeksteki kitabı getirir.
	
	public Book getBook(int i) {
		return books.get(i);
	}
	
	//Belirtilen indeksteki kitabı siler.
	public void deleteBook(int i) {
		books.remove(i);
		booknames.remove(i);
		saveBooks();
	}
	//Tüm verileri içeren dosyaları siler.

	public void deleteAllData() {
		 // Kullanıcı, kitap, sipariş ve ödünç verme dosyalarını kontrol eder ve siler
		if (usersfile.exists()) {
			try {
				usersfile.delete();
			} catch (Exception e) {}
		}
		if (booksfile.exists()) {
			try {
				booksfile.delete();
			} catch (Exception e) {}
		}
		if (ordersfile.exists()) {
			try {
				ordersfile.delete();
			} catch (Exception e) {}
		}
		if (borrowingsfile.exists()) {
			try {
				borrowingsfile.delete();
			} catch (Exception e) {}
		}
	}
	//Sipariş ekler.
	
	public void addOrder(Order order, Book book, int bookindex) {
		orders.add(order);
		books.set(bookindex, book);
		saveOrders();
		saveBooks();
	}
	//Sİparişleri dosyaya kaydeder.
	
	private void saveOrders() {
		String text1 = "";
		// Siparişleri dosyaya yazmak için string oluşturur

		for (Order order : orders) {
			text1 = text1 + order.toString2()+"<NewOrder/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(ordersfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) { 
			// Hata durumunda hata mesajını yazdırır
			System.err.println(e.toString());
		}
	}
	
	/*Siparişleri dosyadan okur ve liste oluşturur. */
	private void getOrders() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(ordersfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			//Hata durumunda hata mesajını yazdırır.
			System.err.println(e.toString());
		}
		
		 // Dosyadan okunan sipariş bilgilerini parse eder ve listeye ekler
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewOrder/>");
			for (String s : a1) {
				Order order = parseOrder(s);
				orders.add(order);
			}
		}
	}
	/*Kullanıcı adına göre kullanıcının varlığını kontrol eder.*/

	public boolean userExists(String name) {
		boolean f = false;
		for (User user : users) {
			if (user.getName().toLowerCase().matches(name.toLowerCase())) {
				f = true;
				break;
			}
		}
		return f;
	}
	
	//Kullanıcı adına göre kullanıcı nesnesini getirir.
	private User getUserByName(String name) {
		User u = new NormalUser("");
		for (User user : users) {
			if (user.getName().matches(name)) {
				u = user;
				break;
			}
		}
		return u;
	}
	
	//Verilen stringi kullanarak sipariş nesnesi oluşturur.

	private Order parseOrder(String s) {
		String[] a = s.split("<N/>");
		Order order = new Order(books.get(getBook(a[0])), getUserByName(a[1]),
				Double.parseDouble(a[2]), Integer.parseInt(a[3]));
		return order;
	}
	//Tüm siparişleri içeren bir liste döndürür.
	public ArrayList<Order> getAllOrders() {
		return orders;
	}
	
	//Ödünç verme bilgilerini dosyaya kaydeder.
	private void saveBorrowings() {
		String text1 = "";
		//Ödünç verme bilgilerini dosyaya yazmak için string oluşturur.

		for (Borrowing borrowing : borrowings) {
			text1 = text1 + borrowing.toString2()+"<NewBorrowing/>\n";
		}
		try {
			//Dosya yazma işlemini gerçekleştirir.
			PrintWriter pw = new PrintWriter(borrowingsfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			//Hata durumunda hata mesajını yazdırır.
			System.err.println(e.toString());
		}
	}
	//Ödünç verme bilgilerini dosyadan okur ve liste oluşturur.
	private void getBorrowings() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(borrowingsfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) { // Hata durumunda hata mesajını yazdırır
			System.err.println(e.toString());
		}
		// Dosyadan okunan ödünç verme bilgilerini parse eder ve listeye ekler
		
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewBorrowing/>");
			for (String s : a1) {
				Borrowing borrowing = parseBorrowing(s);
				borrowings.add(borrowing);
			}
		}
	}
	
	//Verilen stringi kullanarak ödünç verme nesnesini oluşturur
	private Borrowing parseBorrowing(String s) {
		String[] a = s.split("<N/>");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(a[0], formatter);
		LocalDate finish = LocalDate.parse(a[1], formatter);
		Book book = getBook(getBook(a[3]));
		User user = getUserByName(a[4]);
		Borrowing brw = new Borrowing(start, finish, book, user);
		return brw;
	}
	
	//Kitap ödünç alma işlemini gerçekleştirir.
	public void borrowBook(Borrowing brw, Book book, int bookindex) {
		borrowings.add(brw);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
	}
	//Tüm ödünç verme işlemlerini içeren bir liste döndürür.
	public ArrayList<Borrowing> getBrws() {
		return borrowings;
	}
	//Kitabın iadesini gerçekleştirir.
	public void returnBook(Borrowing b, Book book, int bookindex) {
		borrowings.remove(b);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
	}

}
