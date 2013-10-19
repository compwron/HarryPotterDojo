import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    private double booksPrice;
    private HashMap<BookType, Integer> bookTypes = emptyBookTypes();

    public ShoppingCart(List<Book> books) {
        calculateBookTypes(books);
        this.booksPrice = calculateTotalPrice(books);
    }

    public String formattedPrice() {
        return formatPriceToTwoDigits(this.booksPrice) + " EUR";
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

    private int numberOfBooks(List<Book> books) {
        int numberOfBooks = 0;
        for (Book book : books) {
            numberOfBooks += book.getBookCount();
        }
        return numberOfBooks;
    }

    private int typesOfBooks(List<Book> books) {
        int typesOfBooks = 0;
        for (BookType bookType : bookTypes.keySet()) {
            if (bookTypes.get(bookType) != 0) {
                typesOfBooks += 1;
            }
        }
        return typesOfBooks;
    }

    private String formatPriceToTwoDigits(double booksPrice) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(booksPrice);
    }
}
