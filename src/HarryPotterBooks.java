import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

public class HarryPotterBooks {
    private double booksPrice;
    private HashMap<BookType, Integer> bookTypes = emptyBookTypes();

    private HashMap<BookType, Integer> emptyBookTypes() {
        HashMap<BookType, Integer> emptyBookTypes = new HashMap<BookType, Integer>();
        for (BookType bookType : BookType.values()){
            emptyBookTypes.put(bookType, 0);
        }
        return emptyBookTypes;
    }

    public HarryPotterBooks(List<Book> books) {
        findBookTypes(books);
        this.booksPrice = calculateTotalPrice(books);
    }

    private void findBookTypes(List<Book> books) {
        for (Book book : books) {
            bookTypes.put(book.getBookType(), bookTypes.get(book.getBookType()) + 1);
        }
    }

    public String formattedPrice() {
        return formatPriceToTwoDigits(this.booksPrice) + " EUR";
    }

    private double calculateTotalPrice(List<Book> books) {
        double totalPriceWithoutDiscount =  books.size() * Book.STANDARD_PRICE;
        return totalPriceWithoutDiscount * DiscountPercentages.get(typesOfBooks(books));
    }

    private int typesOfBooks(List<Book> books) {
        int typesOfBooks = 0;
        for (BookType bookType : bookTypes.keySet()){
            if (bookTypes.get(bookType) != 0){
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
