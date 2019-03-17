package models;

import io.ebean.Finder;
import io.ebean.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import play.data.validation.Constraints.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String bookTitle;

    public Integer price;

    public String author;

    public Book(String bookTitle, Integer price, String author) {
        this.bookTitle = bookTitle;
        this.price = price;
        this.author = author;
    }

    public static final Finder<Long, Book> find = new Finder<>(Book.class);

}
