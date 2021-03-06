public enum BookSetType {
    None(0), One(1), Two(2), Three(3), Four(4), Five(5);
    public int numberOfTypesOfBooks;

    BookSetType(int numberOfTypesOfBooks) {
        this.numberOfTypesOfBooks = numberOfTypesOfBooks;
    }

    public static BookSetType valueOf(int numberOfTypesOfBooks) {
        for (BookSetType bookSetType : BookSetType.values()) {
            if (bookSetType.numberOfTypesOfBooks == numberOfTypesOfBooks) {
                return bookSetType;
            }
        }
        return BookSetType.None;
    }
}
