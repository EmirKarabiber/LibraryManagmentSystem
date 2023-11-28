package Library;

import java.io.File;
import java.util.ArrayList;

public class Database {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> booknames = new ArrayList<String>();

    private File usersfile = new File(Main.class.getClassLoader().getResource("data/Users").getFile());
    private File booksfile = new File(Main.class.getClassLoader().getResource("data/Books").getFile());
  
    public Database(){
        if (!usersfile.exists()){
            usersfile.mkdirs();
        }
        if(!booksfile.exists()){
            booksfile.mkdirs();
        }
    }

    public void AddUser(User s){
        users.add(s);
        usernames.add(s.getName());
    }

    public int login(String phonenumber, String email){
        int n = -1;
        for(User s : users){
            if(s.getPhoneNumber().matches(phonenumber) && s.getEmail().matches(email)){
                n = users.indexOf(s);
                break;
            }
        }
        return n;
    }

    public User getUser(int n){
        return users.get(n);
    }

    public void AddBook(Book book){
        books.add(book);
        booknames.add(book.getName());
    }

}
