import lombok.Getter;

public class Book {
    public static int STANDARD_PRICE = 8;

    @Getter
    private final int bookCount;

    public Book(BookType bookType, int bookCount) {
        this.bookCount = bookCount;
    }

    public int eurosCost() {
        return 8 * bookCount;
    }
}
