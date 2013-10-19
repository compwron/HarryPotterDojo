import java.util.ArrayList;
import java.util.List;

public class BooksBuilder {
    private List<Book> books = new ArrayList<Book>();

    public BooksBuilder withBook(Book book) {
        books.add(book);
        return this;
    }

    public List<Book> build() {
        return books;
    }
}
