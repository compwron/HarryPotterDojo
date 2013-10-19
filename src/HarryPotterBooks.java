import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class HarryPotterBooks {
    @Getter
    private final double totalPrice;
    private final Integer numberOfDifferentBooks;
    private HashMap<BookType, Integer> bookTypesCount = emptyBookTypes();

    public HarryPotterBooks(List<Book> books) {
        calculateBookTypes(books);
        this.numberOfDifferentBooks = numberOfDifferentBookTypes(bookTypesCount);
        this.totalPrice = calculateTotalPrice();
    }

    private Integer numberOfDifferentBookTypes(HashMap<BookType, Integer> bookTypesCount) {
        int numberOfBookTypes = 0;
        for (BookType bookType : bookTypesCount.keySet()) {
            if (bookTypesCount.get(bookType) != 0) {
                numberOfBookTypes += 1;
            }
        }
        return numberOfBookTypes;
    }

    private void calculateBookTypes(List<Book> books) {
        for (Book book : books) {
            bookTypesCount.put(book.getBookType(), bookTypesCount.get(book.getBookType()) + book.getBookCount());
        }
    }

    private HashMap<BookType, Integer> emptyBookTypes() {
        HashMap<BookType, Integer> emptyBookTypes = new HashMap<BookType, Integer>();
        for (BookType bookType : BookType.values()) {
            emptyBookTypes.put(bookType, 0);
        }
        return emptyBookTypes;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (BookType bookType : bookTypesCount.keySet()) {
            double priceOfDiscountedBook = numberOfDiscountedBooksForBookType(bookType) * DiscountPercentages.get(numberOfDifferentBooks) * Book.STANDARD_PRICE;
            double priceOfNonDiscountedBooks = numberOfNonDiscountedBooks(bookType) * Book.STANDARD_PRICE;
            totalPrice += priceOfDiscountedBook + priceOfNonDiscountedBooks;
        }
        return totalPrice;
    }

    private int numberOfDiscountedBooksForBookType(BookType bookType) {
        if (bookTypesCount.get(bookType) > 0) {
            return numberOfDifferentBooks == 1 ? 0 : 1;
        }
        return 0;
    }

    private int numberOfNonDiscountedBooks(BookType bookType) {
        int nonDiscountedBooks = bookTypesCount.get(bookType) - numberOfDiscountedBooksForBookType(bookType);
        return nonDiscountedBooks >= 0 ? nonDiscountedBooks : 0;
    }
}
