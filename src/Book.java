import lombok.Getter;

public class Book {
    @Getter
    private final int bookCount;

    public Book(BookType bookType, int bookCount) {
        this.bookCount = bookCount;
    }

    public int eurosCost() {
        return 8 * bookCount;
    }
}
