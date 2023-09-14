package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.PackingOrderException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.utility.Packable;
import mms.utility.Size;

import static mms.utility.Size.*;

/**
 * A moving truck to facilitate moving different items on a road.
 * A moving truck has two sections, the cab and the storage space.
 * The cab is 15 m in length.
 */
public class MovingTruck extends Storage {
    /**
     * length of moving truck cab in cms
     */
    private int cabLength = 1500;

    /**
     * Creates an empty large-sized moving truck with the specified width,
     * height and length.
     * @param width width of the moving truck in cm
     * @param height height of the moving truck in cm
     * @param length length of the moving truck in em
     * @throws IllegalArgumentException if length < 1500 i.e. cab length
     */
    public MovingTruck(double width,
                       double height,
                       double length)
            throws IllegalArgumentException {
        super(width, height, length, LARGE);

        if (length < cabLength) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates an empty moving truck with the specified width, height, length
     * and size.
     * @param width width of the moving truck in cm
     * @param height height of the moving truck in cm
     * @param length length of the moving truck in cm
     * @param size size of the moving truck
     * @throws IllegalArgumentException if length < 1500 i.e. cab length
     */
    public MovingTruck(double width,
                       double height,
                       double length,
                       Size size)
            throws IllegalArgumentException {
        super(width, height, length, size);

        if (length < cabLength) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the multiplier of a moving truck.
     * The value of the multiplier for a moving truck is four (4).
     * @return multiplier of this box
     */
    protected int getMultiplier() {
        return 4;
    }

    /**
     * Returns the volume of the storage area of the moving truck in cm3.
     * The volume of the storage area of a moving truck is the width by the
     * height by the length less 1500.
     * @return volume of the storage area of this moving truck
     */
    public double getVolume() {
        return getWidth() * getHeight() * (getLength() - cabLength);
    }

    /**
     * Adds an item to the moving trucks internal list.
     * <p>
     * If the moving truck is at capacity or adding the item to this moving
     * truck causes any two of the sum of the elements' width, height or length
     * to be greater than the width, height of length (respectively) of the
     * moving truck, then an exception should be thrown and no action should be
     * taken.
     * <p>
     * The length of the moving truck should be 1500 less than the given length
     * as that is the true length of the storage space.
     * <p>
     * Once an item of the Furniture class has been added to the list, only
     * other Furniture items may be added until all the Furniture items have
     * been removed. That is, if the number of furniture items in the truck is
     * greater than zero, then only furniture items may be added, else any
     * Packable item can be added.
     * @param item item to add to moving trucks internal list
     * @throws PackingException
     *     throws PackingOrderException if there is a furniture item on the
     *         moving truck and a non-furniture item is being added
     *     throws StorageFullException if SUM lengths of items in moving truck
     *         + length of new item > moving truck storage area
     *         i.e. storage length less cabin length
     *     throws StorageFullException if the current number of items currently
     *         inside this storage (previously packed items) >= the capacity of
     *         the storage or if two of the following are true:
     *             SUM widths of items in storage + width of new item
     *                 > storage width
     *             SUM heights of items in storage + height of new item
     *                 > storage height
     *             SUM lengths of items in storage + length of new item
     *                 > moving struck storage area
     */
    public void pack(Packable item)
            throws PackingException {
        // furniture previously packed and new item not furniture
        if (furniturePreviouslyPacked() && !(item instanceof Furniture)) {
            throw new PackingOrderException();
        } else {
            // total length of items in the moving truck
            int totalItemsLength = 0;
            for (Packable element : getElements()) {
                totalItemsLength += element.getLength();
            }
            if (totalItemsLength + item.getLength() > (getLength() - cabLength)) {
                throw new StorageFullException();
            }
        }
        // otherwise, pack in accordance with superclass
        super.pack(item);
    }

    /**
     * if furniture is previously packed in the moving truck
     */
    private boolean furniturePreviouslyPacked() {
        boolean furniturePreviouslyPacked = false;
        for (Packable element : getElements()) {
            // element instance of furniture class
            if (element instanceof Furniture) {
                furniturePreviouslyPacked = true;
                break;
            }
        }
        return furniturePreviouslyPacked;
    }

    /**
     * Removes an item from the moving trucks internal list.
     * <p>
     * The moving truck is unpacked in a particular order.
     * First, all furniture is removed in First In, First Out format.
     * After all the furniture is removed, the remaining items should be
     * unpacked in First In, Last Out (FILO) format.
     * @return item from the storage internal list
     */
    public Packable unpack() {
        // furniture previously packed, unpack first in, last out (last in, first out)
        if (furniturePreviouslyPacked()) {
            // unpack and re-pack each item except for last furniture item
            // i.e. last furniture item should be moved to first index to be unpacked
            for (int index = 0; index < getElements().size() - 1; index++) {
                // item to unpack and repack from the moving truck
                Packable itemToUnpackRepack = getElements().get(0);
                // unpack item
                super.unpack();
                try {
                    // re-pack item
                    super.pack(itemToUnpackRepack);
                } catch (PackingException exception) {
                    // item previously packed, unpacked, then re-packed
                }
            }
        }
        // unpack item at first index, first in, first out in accordance with superclass
        return super.unpack();
    }

    /**
     * Returns the human-readable string representation of the moving truck.
     * <p>
     * The format of the string to return is:
     *     MovingTruck ('capacity'/'totalCapacity')
     * where,
     *     'capacity' is the current number of items on board this moving truck
     *     'totalCapacity' is the maximum capacity of this moving truck
     * Example:
     *     MovingTruck (14/20)
     * @return string representation of this moving truck
     */
    public String toString() {
        return getClass().getSimpleName() + " ("
                + getOccupiedCapacity() + "/"
                + getCapacity() + ")";
    }
}