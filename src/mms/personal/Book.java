package mms.personal;

/**
 * A book that can be read, includes both fiction and non-fiction.
 */
public class Book extends Personal {
    /**
     * title of this book
     */
    private String title;

    /**
     * if this book is fiction or non-fiction
     */
    private boolean isFiction;

    /**
     * Creates a book with the given owner and title.
     * Also, records if the book is fiction or non-fiction.
     * A book has a width of 20 cm, height of 20 cm and length of 5 cm.
     * @param owner owner of the book
     * @param title title of the book
     * @param isFiction if the book is fiction or non-fiction
     * @throws IllegalArgumentException if title is null or an empty string
     */
    public Book(String owner, String title, boolean isFiction)
            throws IllegalArgumentException {
        super(owner);
        if (title == null | title == "") {
            throw new IllegalArgumentException();
        } else {
            this.title = title;
            this.isFiction = isFiction;
            setDimensions(20, 20, 5);
        }
    }

    /**
     * Returns the title of the book.
     * @return title of this book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the human-readable string representation of the book.
     * <p>
     * The format of the string to return is:
     *     Book ('owner') Title: 'title' ('isFiction')
     * where,
     *     'owner' is the owner of this book,
     *     'title' is the title of this book, and
     *     'isFiction' is
     *         "Fiction" if the book is a work of fiction and
     *         "Non-Fiction" if the book is a work of non-fiction.
     * Example:
     *     Book (Jane) Title: Basics of Java Programming (Non-Fiction)
     * @return string representation of this book
     */
    public String toString() {
        // string representation of this book for concatenation
        String bookToString = getClass().getSimpleName() + " ("
                + getOwner() + ") Title: "
                + getTitle();
        if (isFiction) {
            // concatenate for fiction book
            bookToString += " (Fiction)";
        } else {
            // concatenate for non-fiction book
            bookToString += " (Non-Fiction)";
        }
        return bookToString;
    }
}