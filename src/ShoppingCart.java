import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    private double booksPrice;


    public ShoppingCart(List<Book> books) {
        this.booksPrice = new HarryPotterBooks(books).getTotalPrice();
    }

    public String formattedPrice() {
        return formatPriceToTwoDigits(this.booksPrice) + " EUR";
    }


    private HashMap<BookType, Integer> emptyBookTypes() {
        HashMap<BookType, Integer> emptyBookTypes = new HashMap<BookType, Integer>();
        for (BookType bookType : BookType.values()) {
            emptyBookTypes.put(bookType, 0);
        }
        return emptyBookTypes;
    }

    private String formatPriceToTwoDigits(double booksPrice) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(booksPrice);
    }
}
