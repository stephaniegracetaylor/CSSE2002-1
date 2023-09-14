package mms.storage;

import java.util.ArrayList;
import java.util.List;

import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.utility.Packable;
import mms.utility.Size;

/**
 * Represents some form of storage entity. A storage class contains and manages
 * an internal inventory of Packable items. A storage entity can only contain
 * so much before the storage unit becomes full.
 */
public abstract class Storage extends Object {
    /**
     *  width of this storage in cm
     */
    private double width;
    /**
     * height of this storage in cm
     */
    private double height;
    /**
     * length of this storage in cm
     */
    private double length;

    /**
     *  size of this storage
     */
    private Size size;

    /**
     * list of elements in this storage
     */
    private List<Packable> elementsInStorage = new ArrayList<>();

    /**
     * Creates a new empty storage of medium Size with on contents.
     * @param width width of the storage in cm
     * @param height height of the storage in cm
     * @param length length of the storage in cm
     * @throws IllegalArgumentException if width, height or length are less than
     *     or equal to zero
     */
    public Storage(double width,
                   double height,
                   double length)
            throws IllegalArgumentException {
        if (width <= 0 | height <= 0 | length <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.width = width;
            this.height = height;
            this.length = length;
            size = Size.MEDIUM;
        }
    }

    /**
     * Creates a new empty storage of specified Size with on contents.
     * @param width width of the storage in cm
     * @param height height of the storage in cm
     * @param length length of the storage in cm
     * @param size size of the storage
     * @throws IllegalArgumentException if width, height or length are less than
     *     or equal to zero
     */
    public Storage(double width,
                   double height,
                   double length,
                   Size size)
            throws IllegalArgumentException {
        if (width <= 0 | height <= 0 | length <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.width = width;
            this.height = height;
            this.length = length;
            this.size = size;
        }
    }

    /**
     * Returns the width of the storage in cms.
     * @return width of this storage
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the storage in cms.
     * @return height of this storage
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the length of the storage in cms.
     * @return length of this storage
     */
    public double getLength() {
        return length;
    }

    /**
     * Returns a new list containing all the items in storage.
     * Adding or removing items from this list should not affect the storages
     * internal list of items.
     * @return new list containing all items in this storage
     */
    public List<Packable> getElements() {
        return new ArrayList<>(elementsInStorage);
    }

    /**
     * Returns a new list containing all the items in storage that are the same
     * class as the reference parameter.
     * Adding or removing items from this list should not affect the storages
     * internal list of items.
     * @param reference reference object to get the type
     * @return new list containing all items in this storage that are in the
     *     same class as the reference parameter
     */
    public List<Packable> getElementsOfType(Packable reference) {
        if (reference == null) {
            return null;
        }
        // list of elements in storage of reference type
        List<Packable> elementsOfType = new ArrayList<>();

        for (Packable element : elementsInStorage) {
            if (element.getClass() == reference.getClass()) {
                elementsOfType.add(element);
            }
        }
        return elementsOfType;
    }

    /**
     * Returns the size of the storage.
     * @return size of this storage
     */
    public Size getSize() {
        return size;
    }

    /**
     * Returns the capacity of the storage.
     * The capacity is determined by the following table, and is based on the
     * Size and multiplier set by the particular storage class.
     *     Storage Size   Associated Capacity
     *      SMALL           3 by 'multiplier'
     *      MEDIUM          5 by 'multiplier'
     *      LARGE          10 by 'multiplier'
     * where,
     *     'multiplier' is the value returned by getMultiplier().
     * @return capacity of this storage
     */
    public int getCapacity() {
        int capacity = 0;
        if (getSize() == Size.SMALL) {
            capacity = 3 * getMultiplier();
        } else if (getSize() == Size.MEDIUM) {
            capacity = 5 * getMultiplier();
        } else if (getSize() == Size.LARGE) {
            capacity = 10 * getMultiplier();
        }
        return capacity;
    }

    /**
     * Returns the multiplier of the particular storage type.
     * @return multiplier of this storage
     */
    protected abstract int getMultiplier();

    /**
     * Adds an item to the storages internal list.
     * <p>
     * If the storage is at capacity or adding the item to this storage causes
     * any two of the sum of the elements' width, height or length to be greater
     * than the width, height or length (respectively) of the storage, then an
     * exception should be thrown and no action should be taken.
     * <p>
     * That is, exceeding the one sum is fine,but exceeding two sums should
     * cause an exception.
     * <p>
     * This method declares to throw a PackingException to allow subclasses to
     * throw additional exceptions to the same type.
     * <p>
     * @param item item to add to the storages internal list
     * @throws PackingException throws StorageFullException, if the current
     *     number of items currently inside this storage (previously packed
     *     items) >= the capacity of the storage or if two of the following
     *     are true:
     *         SUM widths of items in storage + width of new item
     *             > storage width
     *         SUM heights of items in storage + height of new item
     *             > storage height
     *         SUM lengths of items in storage + length of new item
     *             > storage length
     */
    public void pack(Packable item)
            throws PackingException {
        if (getOccupiedCapacity() >= getCapacity()) {
            throw new StorageFullException();
        } else {
            // total width of items in the storage
            int totalItemsWidth = 0;
            // total height of items in the storage
            int totalItemsHeight = 0;
            // total length of items in the storage
            int totalItemsLength = 0;

            for (Packable element : elementsInStorage) {
                totalItemsWidth  += element.getWidth();
                totalItemsHeight += element.getHeight();
                totalItemsLength += element.getLength();
            }
            // count for StorageFullException
            int countStorageFullException = 0;
            if (totalItemsWidth  + item.getWidth() > getWidth()) {
                countStorageFullException++;
            }
            if (totalItemsHeight + item.getHeight() > getHeight()) {
                countStorageFullException++;
            }
            if (totalItemsLength + item.getLength() > getLength()) {
                countStorageFullException++;
            }
            if (countStorageFullException > 1) {
                throw new StorageFullException();
            }
        }
        elementsInStorage.add(item);
    }

    /**
     * Removes an item from the storages internal list.
     * The list should be unpacked in a First In, First Out (FIFO) format.
     * @return item at the first index of this storage internal list;
     *     null if the list is empty
     */
    public Packable unpack() {
        if (elementsInStorage.isEmpty()) {
            return null;
        } else {
            // item to unpack from the storage
            Packable itemToUnpack = elementsInStorage.get(0);
            // unpack in first in, first out
            elementsInStorage.remove(0);
            return itemToUnpack;
        }
    }

    /**
     * Returns how many elements are currently in the storages internal list.
     * @return number of elements in this storage
     */
    public int getOccupiedCapacity() {
        return elementsInStorage.size();
    }

    /**
     * Returns the human-readable string representation of the storage.
     * <p>
     * The format of the string to return is:
     *     'class' ('width', 'height', 'length') 'size'
     * where,
     *     'class' is this storage instance class simple name,
     *     'width' is the width of this storage in cm to two decimal places,
     *     'height' is the height of this storage in cm to two decimal places,
     *     'length' is the length of this storage in cm to two decimal places, and
     *     'size' is the size of this storage.
     * Example:
     *     Box (5.60, 5.60, 5.00) MEDIUM
     * @return string representation of this storage
     */
    public String toString() {
        return getClass().getSimpleName() + " ("
                + String.format("%.2f", getWidth()) + ", "
                + String.format("%.2f", getHeight()) + ", "
                + String.format("%.2f", getLength()) + ") "
                + getSize();
    }

    /**
     * Returns the human-readable string representation of the storage and its
     * elements.
     * <p>
     * The format of the string to return is:
     *     'tabs' 'class' ('width', 'height', 'length') 'size'
     *     'elementTabs' 'element1'
     *     'elementTabs' 'element2'
     *     ...
     *     'elementTabs' 'elementN'
     * where,
     *     'level' is the specified number of tab characters,
     *     'tabs' is 'level' of 'tab' character(s),
     *     'class' is this storage instance class simple name,
     *     'width' is the width of this storage in cm to two decimal places,
     *     'height' is the height of this storage in cm to two decimal places,
     *     'length' is the length of this storage in cm to two decimal places,
     *     'size' is the size of this storage, and
     *     'elementX' is the human-readable string representation of this
     *         storage's Xth element, if it exists, where elements are appended
     *         in insertion order (i.e. the order that items were added to this
     *         storage by calling pack(Packable item)).
     *         If the Xth element is an instance of the Storage class then the
     *         toString(int) method should be called with one higher level value
     *         as the parameter.
     *     'elementTabs' is 'level' + one 'tab' character(s), if the next
     *         'elementX' is a Storage class then no 'tab' characters are
     *         entered (as this is handled by the toString(int) as above).
     * Example:
     *     Box (5.00, 5.00, 5.00) MEDIUM
     *         Book (Jane) Title: Basics of Java Programming (Non-Fiction)
     *         Clothes (Bob) (SMALL, SHIRT)
     * or
     *     Box (5.00, 5.00, 5.00) MEDIUM
     *         Book (Jane) Title: Basics of Java Programming (Non-Fiction)
     *         Box (2.00, 2.00, 2.00) SMALL
     *             Clothes (Bob) (SMALL, SHIRT)
     * @param level number of tabs to indent
     * @return string representation of this storage
     * @throws IllegalArgumentException if level < 0
     */
    public String toString(int level)
            throws IllegalArgumentException {
        if (level < 0) {
            throw new IllegalArgumentException();
        } else {
            // string representation of this storage for concatenation
            String storageToString = "";

            // 'tab' character of four spaces
            String tab = "    ";

            // concatenate storage level tabs
            for (int tabs = 0; tabs < level; tabs++) {
                storageToString += tab;
            }
            // concatenate storage string representation
            storageToString += toString();

            for (Packable element : getElements()) {
                // element is storage and is not empty
                if (element instanceof Storage storageElement
                        && storageElement.getElements().size() > 0) {
                    storageToString += "\n" + storageElement.toString(level + 1);
                } else {
                    // concatenate new line
                    storageToString += "\n";
                    // concatenate element level plus one tabs
                    for (int elementTabs = 0; elementTabs < level + 1; elementTabs++) {
                        storageToString += tab;
                    }
                    // concatenate element string representation
                    storageToString += element.toString();
                }
            }

            return storageToString;
        }
    }
}