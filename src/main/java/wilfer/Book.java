package wilfer;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Book extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String title;
    private int price;
    private int isbn;

    public Book(Long id, String author, String title, int price, int isbn) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
    }

    public Book(String author, String title, int price, int isbn) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
