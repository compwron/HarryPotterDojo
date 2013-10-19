import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


public class HarryPotterBooks {
    private double booksPrice;

    public HarryPotterBooks(List<Book> books) {
        this.booksPrice = calculateTotalPrice(books);
    }

    private double calculateTotalPrice(List<Book> books) {
        int totalBooks = 0;
        for(Book book : books){
            totalBooks += book.getBookCount();
        }
        return totalBooks * Book.STANDARD_PRICE;
    }

    public String formattedPrice() {
        return formatPrice(this.booksPrice) + " EUR";
    }

    private String formatPrice(double booksPrice) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(booksPrice);
    }
}
