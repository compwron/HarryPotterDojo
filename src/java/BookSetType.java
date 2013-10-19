public enum BookSetType {
    None(0), One(1);
    private int numberOfTypesOfBooks;

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
