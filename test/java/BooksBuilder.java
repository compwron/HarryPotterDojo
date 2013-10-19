import java.util.ArrayList;
import java.util.List;

public class BooksBuilder {
    private List<HarryPotterBookType> books = new ArrayList<HarryPotterBookType>();

    public BooksBuilder withBook(HarryPotterBookType book) {
        books.add(book);
        return this;
    }

    public List<HarryPotterBookType> build() {
        return books;
    }
}
