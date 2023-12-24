package Library;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// Kitap ödünç alma işlemini temsil eden sınıf
public class Borrowing {
	
	// Ödünç alma işleminin başlangıç ve bitiş tarihleri, kalan gün sayısı, ilgili kitap ve kullanıcı bilgileri
	LocalDate start;
	LocalDate finish;
	int daysleft;
	Book book;
	User user;
	
	// Tarih biçimlendirme için kullanılacak formater
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	// Parametre olarak kitap ve kullanıcı bilgisi alan bir ödünç alma nesnesi oluşturulduğunda çalışan constructor
	public Borrowing(Book book, User user) {
		start = LocalDate.now(); // Ödünç alma işlemi başlangıç tarihi, şu anki tarih
		finish = start.plusDays(14); // Ödünç alma işlemi bitiş tarihi, başlangıç tarihine 14 gün eklenmiş hali
		daysleft = Period.between(start, finish).getDays(); // Kalan gün sayısı, başlangıç ve bitiş tarihleri arasındaki gün farkı
		this.book = book; // Ödünç alınan kitap
		this.user = user; // Ödünç alan kullanıcı
	}
	
	// Parametre olarak başlangıç, bitiş, kitap ve kullanıcı bilgisi alan bir ödünç alma nesnesi oluşturulduğunda çalışan constructor
	public Borrowing(LocalDate start, LocalDate finish, Book book, User user) {
		this.start = start; // Ödünç alma işlemi başlangıç tarihi
		this.finish = finish; // Ödünç alma işlemi bitiş tarihi
		this.daysleft = Period.between(finish, LocalDate.now()).getDays(); // Kalan gün sayısı, bitiş tarihi ile şu anki tarih arasındaki gün farkı
		this.book = book; // Ödünç alınan kitap
		this.user = user; // Ödünç alan kullanıcı
	}
	
	// Başlangıç tarihini biçimlendirilmiş şekilde döndüren metot
	public String getStart() {
		return formatter.format(start);
	}
	
	// Bitiş tarihini biçimlendirilmiş şekilde döndüren metot
	public String getFinish() {
		return formatter.format(finish);
	}
	
	// Kalan gün sayısını döndüren metot
	public int getDaysLeft() {
		return Period.between(finish, LocalDate.now()).getDays();
	}
	
	// Ödünç alınan kitabı döndüren metot
	public Book getBook() {
		return book;
	}
	
	// Ödünç alınan kitabı ayarlayan metot
	public void setBook(Book book) {
		this.book = book;
	}
	
	// Ödünç alan kullanıcıyı döndüren metot
	public User getUser() {
		return user;
	}
	
	// Ödünç alan kullanıcıyı ayarlayan metot
	public void setUser(User user) {
		this.user = user;
	}
	
	// Ödünç alma bilgilerini bir metin olarak döndüren metot
	public String toString() {
		return "Ödünç alma tarihi: " + start + "\nSon tarih: " + finish + "\nGün kaldı: " + daysleft;
	}
	
	// Ödünç alma bilgilerini belirli bir formatta döndüren metot
	public String toString2() {
		return getStart() + "<N/>" + getFinish() + "<N/>" + getDaysLeft() + "<N/>" + book.getName() + "<N/>" +
				user.getName() + "<N/>";
	}

}
