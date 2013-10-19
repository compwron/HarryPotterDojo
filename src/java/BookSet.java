import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode
public class BookSet {
    private final List<Book> books;
    @Getter
    private BookSetType bookSetType;

    public BookSet(List<Book> books) {
        this.books = books;
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
        return new BookSet(removeOneOfEachBook(books));
    }

    private List<Book> removeOneOfEachBook(List<Book> books) {
        List<Book> reducedBookList = new ArrayList<Book>();
        for (Book book : books) {
            reducedBookList.add(new Book(book.getBookType(), decrementBookCount(book)));
        }
        return reducedBookList;
    }

    private int decrementBookCount(Book book) {
        int reducedCount = book.getBookCount() - 1;
        return reducedCount >= 0 ? reducedCount : 0;
    }

    public double priceOfLargestSet() {
        return DiscountPercentages.get(bookSetType) * Book.STANDARD_PRICE * bookSetType.numberOfTypesOfBooks;
    }

    public String toString() {
        return "[BookSetType: " + bookSetType + "]";
    }

    public boolean hasNoMoreMultiSets() {
        return bookSetType.numberOfTypesOfBooks <= 1;
    }
}
