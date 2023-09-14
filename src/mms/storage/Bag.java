package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.personal.Personal;
import mms.utility.Packable;
import mms.utility.Size;

import static mms.personal.Personal.getBaseWeight;

/**
 * A bag to store different personal items.
 */
public class Bag extends Storage
        implements Packable {
    /**
     * maximum weight of a bag in grams
     */
    private int maxBagWeight = 1500;

    /**
     * Creates an empty medium-sized bag with the specified width, height and
     * length.
     * @param width width of the bag in cm
     * @param height height of the bag in cm
     * @param length length of the bag in cm
     */
    public Bag(double width,
               double height,
               double length) {
        super(width, height, length);
    }

    /**
     * Creates an empty bag with the specified width, height, length and size.
     * @param width width of the bag in cm
     * @param height height of the bag in cm
     * @param length length of the bag in cm
     * @param size size of the storage
     */
    public Bag(double width,
               double height,
               double length,
               Size size) {
        super(width, height, length, size);
    }

    /**
     * Returns the multiplier of a bag.
     * The value of the multiplier for a bag is one (1).
     * @return multiplier of this bag
     */
    protected int getMultiplier() {
        return 1;
    }

    /**
     * Adds an item to the bags internal list.
     * <p>
     * If the bag is at capacity or adding the item to this bag causes any two
     * of the sum of the elements' width, height, length or weight to be greater
     * than the width, height, length or weight (respectively) of the bag, then
     * an exception should be thrown and no action should be taken.
     * @param item item to add to the storages internal list
     * @throws PackingException
     *     throws BadItemException, if the item to be added to the bag is not an
     *         instance of the Personal class.
     *     throws StorageFullException, if SUM weight of items in the bag +
     *         weight of new item > maximum weight of the bag of 1.5 kg.
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
        if (!(item instanceof Personal)) {
            throw new BadItemException();
        } else {
            // total weight of items in storage
            int totalItemsWeight = 0;
            for (Packable element : getElements()) {
                totalItemsWeight += getBaseWeight();
            }
            if (totalItemsWeight + getBaseWeight() > maxBagWeight) {
                throw new StorageFullException();
            }
        }
        super.pack(item);
    }
}