import lombok.Getter;

public class Book {
    public static int STANDARD_PRICE = 8;

    @Getter
    private final int bookCount;

    @Getter
    private final BookType bookType;

    public Book(BookType bookType, int bookCount) {
        this.bookType = bookType;
        this.bookCount = bookCount;
    }

    public int eurosCost() {
        return 8 * bookCount;
    }
}
