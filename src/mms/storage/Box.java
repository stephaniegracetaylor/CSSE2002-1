package mms.storage;

import mms.exceptions.PackingException;
import mms.furniture.Furniture;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;

import static mms.furniture.FurnitureType.TELEVISION;

/**
 * A box to store different items in.
 * Boxes can be marked as fragile and have comments about their contents.
 * A box contains fragile items if it contains either a television or laptop.
 * If a box does not contain these items, then it is not considered fragile.
 */
public class Box extends Storage implements Packable {
    /**
     * comment about the contents of this box
     */
    private String comment;

    /**
     * if this box contains fragile items i.e. television or laptop
     */
    private boolean isFragile = false;

    /**
     * Creates an empty medium-sized box with the specified width, height,
     * length and comment.
     * A comment is not allowed to be 'null' but can be empty.
     * An empty box does not contain any fragile items.
     * @param width width of the box in cm
     * @param height height of the box in cm
     * @param length length of the box in cm
     * @param comment comment about the contents of the box
     * @throws IllegalArgumentException if comment == 'null'
     */
    public Box(double width,
               double height,
               double length,
               String comment)
            throws IllegalArgumentException {
        super(width, height, length);

        if (comment == null) {
            throw new IllegalArgumentException();
        } else {
            this.comment = comment;
        }
    }

    /**
     * Creates an empty box with specified width, height, length, size and
     * comment.
     * A comment is not allowed to be 'null' but can be empty.
     * An empty box does not contain any fragile items.
     * @param width width of the box in cm
     * @param height height of the box in cm
     * @param length length of the box in cm
     * @param size size of the box
     * @param comment comment about the contents of the box
     * @throws IllegalArgumentException if comment == 'null'
     */
    public Box(double width,
               double height,
               double length,
               Size size,
               String comment)
            throws IllegalArgumentException {
        super(width, height, length, size);

        if (comment == null) {
            throw new IllegalArgumentException();
        } else {
            this.comment = comment;
        }
    }

    /**
     * Returns if the box contains any fragile items.
     * @return true if this box contains fragile items, otherwise false
     */
    public boolean isFragile() {
        return isFragile;
    }

    /**
     * Returns the comment about the contents of the box
     * @return comment about the contents of this box
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the multiplier of a box.
     * The value of the multiplier for a box is two (2).
     * @return multiplier of this box
     */
    protected int getMultiplier() {
        return 2;
    }

    /**
     * Adds an item to the storages internal list.
     * <p>
     * If the storage is at capacity or adding the item to this storage causes
     * any two of the sum of the elements' width, height or length to be greater
     * than the width, height or length (respectively) of the storage, then an
     * exception should be thrown and no action should be taken.
     * <p>
     * That is, exceeding the one sum is fine, but exceeding two sums should
     * cause an exception.
     * <p>
     * This method declares to throw a PackingException to allow subclasses to
     * throw additional exceptions to the same type.
     * <p>
     * @param item item to add to the storages internal list
     * @throws PackingException
     *     throws StorageFullException, if the current number of items currently
     *         inside this storage (previously packed items) >= the capacity of
     *         the storage or if two of the following are true:
     *             SUM widths of items in storage + width of new item
     *                 > storage width
     *             SUM heights of items in storage + height of new item
     *                 > storage height
     *             SUM lengths of items in storage + length of new item
     *                 > storage length
     */
    public void pack(Packable item)
            throws PackingException {
        super.pack(item);

        if (item instanceof Laptop) {
            isFragile = true;
        } else if (item instanceof Furniture) {
            if (((Furniture) item).getType() == TELEVISION) {
                isFragile = true;
            }
        }
    }

    /**
     * Removes an item from the storages internal list.
     * The list should be unpacked in a First In, First Out (FIFO) format.
     * @return item at the first index in this storage internal list;
     *     null if the list is empty
     */
    public Packable unpack() {
        return super.unpack();
    }

    /**
     * Returns the human-readable string representation of the box.
     * <p>
     * The format of the string to return is:
     *     Box ('width', 'height', 'length') 'size' - 'comment'
     * where,
     *     'width' is the width of this bag in cm to two decimal places,
     *     'height' is the height of this bag in cm to two decimal places,
     *     'length' is the length of this bag in cm to two decimal places,
     *     'size' is the size of this storage, and
     *     'comment' is the comment about the contents of this box, if the box
     *         is fragile, then the string " FRAGILE" must be appended to the
     *         comment, if the comment is an empty string, then the string "'\0'"
     *         should be displayed instead.
     * Example:
     *     Box (5.60, 5.60, 5.00) MEDIUM - Kitchenware
     * @return string representation of this box
     */
    public String toString() {
        // string representation of this box for concatenation
        String boxToString;

        boxToString = getClass().getSimpleName() + " ("
                + String.format("%.2f", getWidth())  + ", "
                + String.format("%.2f", getHeight()) + ", "
                + String.format("%.2f", getLength()) + ") "
                + getSize()   + " - "
                + getComment();

        if (getComment().isEmpty()) {
            boxToString += "'\0'";
        }
        if (isFragile) {
            boxToString += " FRAGILE";
        }

        return boxToString;
    }
}