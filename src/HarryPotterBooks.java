import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class HarryPotterBooks {
    @Getter
    private final double totalPrice;
    private HashMap<BookType, Integer> bookTypes = emptyBookTypes();

    public HarryPotterBooks(List<Book> books) {
        calculateBookTypes(books);
        this.totalPrice = calculateTotalPrice(books);
    }

    private void calculateBookTypes(List<Book> books) {
        for (Book book : books) {
            bookTypes.put(book.getBookType(), bookTypes.get(book.getBookType()) + 1);
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
        for (BookType bookType : bookTypes.keySet()) {
            int numbersOfThisTypeOfBook = bookTypes.get(bookType);
            double totalPriceWithoutDiscountForBookType = numbersOfThisTypeOfBook * Book.STANDARD_PRICE;
            double salePriceOfAllBooksOfBookType = totalPriceWithoutDiscountForBookType * DiscountPercentages.get(numbersOfThisTypeOfBook);
            totalPrice += salePriceOfAllBooksOfBookType;
        }
        return totalPrice;
    }
}
