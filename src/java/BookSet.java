import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

public class BookSet {
    @Getter
    private BookSetType bookSetType;

    private HashMap<BookType, Boolean> booksPresent = new HashMap<BookType, Boolean>();

    public BookSet(ArrayList<Book> books) {
        findBooksPresent(books);
        this.bookSetType = bookSetTypeOf(books);
    }

    private BookSetType bookSetTypeOf(ArrayList<Book> books) {
        int numberOfTypesOfBooks = 0;
        for (BookType bookType : booksPresent.keySet()) {
            if (booksPresent.get(bookType)) {
                numberOfTypesOfBooks++;
            }
        }
        return BookSetType.valueOf(numberOfTypesOfBooks);
    }

    private void findBooksPresent(ArrayList<Book> books) {
        for (Book book : books) {
            booksPresent.put(book.getBookType(), true);
        }
    }
}
