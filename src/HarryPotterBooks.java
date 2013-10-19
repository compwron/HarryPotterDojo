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
        this.totalPrice = calculateTotalPrice(books);
    }

    private Integer numberOfDifferentBookTypes(HashMap<BookType, Integer> bookTypesCount) {
        int numberOfBookTypes = 0;
        for (BookType bookType : bookTypesCount.keySet()){
            if (bookTypesCount.get(bookType) != 0){
                numberOfBookTypes += 1;
            }
        }
        return numberOfBookTypes;
    }

    private void calculateBookTypes(List<Book> books) {
        for (Book book : books) {
            bookTypesCount.put(book.getBookType(), bookTypesCount.get(book.getBookType()) + 1);
        }
    }

    private HashMap<BookType, Integer> emptyBookTypes() {
        HashMap<BookType, Integer> emptyBookTypes = new HashMap<BookType, Integer>();
        for (BookType bookType : BookType.values()) {
            emptyBookTypes.put(bookType, 0);
        }
        return emptyBookTypes;
    }

    private double calculateTotalPrice(List<Book> books) {
        double totalPrice = 0.0;
        for (BookType bookType : bookTypesCount.keySet()){
            double priceOfOneDiscountedBook = bookTypesCount.get(bookType) * DiscountPercentages.get(numberOfDifferentBooks) * Book.STANDARD_PRICE;
            double priceOfNonDiscountedBooks =  numberOfNonDiscountedBooks(bookType) * Book.STANDARD_PRICE;
            totalPrice += priceOfOneDiscountedBook + priceOfNonDiscountedBooks;
        }
        return totalPrice;
    }

    private int numberOfNonDiscountedBooks(BookType bookType) {
        if (numberOfDifferentBooks < 2){
            return bookTypesCount.get(bookType);
        }
        return (bookTypesCount.get(bookType) - 1) > 0 ? bookTypesCount.get(bookType) - 1 : 0;
    }

//     Number of non discounted books for a book type is:
//    how many book types are there? If there are is 1, then discounted count = total count.
//    else, it is total count - 1
}
