package Library;

public class Book {
    
    private String name;       //title
    private String author;      //author
    private String publisher;   //publisher
    private String adress;      //Collection location
    private String status;      //Borrowing Status
    private int qty;            //Copies for sale
    private double price;       //Price
    private int brwcopies;      //Copies for borrowing

    public Book() {};

    public Book(String name, String author, String publisher, String adress, int qty, double price, int brwcopies){

        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.adress = adress;
        this.brwcopies = brwcopies;
    }
    
    public String toString() {
        String text = "Book Name:" + name +
                      "Book Author:" + author +
                      "Book Publisher:" + publisher +
                      "Book Collection Adress:" + adress +
                      "Qty:" + String.valueOf(qty) +
                      "Price:" + String.valueOf(price) +
                      "Borrowing Copies" + String.valueOf(brwcopies);
        return text;
    }

    

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAdress() {
        return adress;
    }

    public String getStatus() {
        return status;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public int getBrwcopies() {
        return brwcopies;
    }
    
}
