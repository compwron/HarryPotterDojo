import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode
public class BookSet {
    @Getter
    private BookSetType bookSetType;

    public BookSet(List<Book> books) {
        this.bookSetType = bookSetTypeOf(findBooksPresent(books));
    }

    private BookSetType bookSetTypeOf(HashMap<BookType, Boolean> booksPresent) {
        int numberOfTypesOfBooks = 0;
        for (BookType bookType : booksPresent.keySet()) {
            if (booksPresent.get(bookType)) {
                numberOfTypesOfBooks++;
            }
        }
        return BookSetType.valueOf(numberOfTypesOfBooks);
    }

    private HashMap<BookType, Boolean> findBooksPresent(List<Book> books) {
        HashMap<BookType, Boolean> booksPresent = new HashMap<BookType, Boolean>();
        for (Book book : books) {
            booksPresent.put(book.getBookType(), true);
        }
        return booksPresent;
    }

    public int totalBooksCount() {
        return 0;
    }

    public BookSet removeLargestSet() {
        return new BookSet(new ArrayList<Book>());
    }

    public double priceOfLargestSet() {
        return 0;
    }
}
