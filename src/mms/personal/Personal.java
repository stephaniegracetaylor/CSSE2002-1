package mms.personal;

import mms.utility.Packable;

/**
 * Represents some personal items. Personal items come in many shapes and sizes,
 * but all have a specified owner.
 */
public abstract class Personal extends Object implements Packable {
    /**
     * owner of this personal item
     */
    private String owner;

    /**
     * width of this personal item in cm
     */
    private double width;

    /**
     * height of this personal item in cm
     */
    private double height;

    /**
     * length of this personal item in cm
     */
    private double length;

    /**
     * base weight of this personal item in grams
     */
    private static int baseWeight = 250;

    /**
     * Creates a personal item with a width, height and length of 0 cm and a
     * specific owner.
     * @param owner owner of the personal item
     * @throws IllegalArgumentException if owner is 'null' or empty
     */
    public Personal(String owner)
            throws IllegalArgumentException {
        if (owner == null | owner == "") {
            throw new IllegalArgumentException();
        } else {
            this.owner = owner;
            width = 0;
            height = 0;
            length = 0;
        }
    }

    /**
     * Creates a personal item with a specific owner.
     * @param owner owner of the personal item
     * @param width width of the personal item
     * @param height height of the personal item
     * @param length length of the personal item
     * @throws IllegalArgumentException if owner is 'null' or owner is empty
     *     or if width, height, or length is less than zero
     */
    public Personal(String owner,
                    double width,
                    double height,
                    double length)
            throws IllegalArgumentException {
        if (owner == null | owner == "" | width < 0 | height < 0 | length < 0) {
            throw new IllegalArgumentException();
        } else {
            this.owner = owner;
            this.width = width;
            this.height = height;
            this.length = length;
        }
    }

    /**
     * Returns the owner of the personal item.
     * @return owner of this personal item
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the width of the personal item in cm.
     * @return width of this personal item in cm
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the personal item in cm.
     * @return height of this personal item in cm
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the length of the personal item in cm.
     * @return length of this personal item in cm
     */
    public double getLength() {
        return length;
    }

    /**
     * Returns the base weight of a personal item in grams.
     * This value is 250 grams.
     * @return base weight of this personal item in grams
     */
    public static int getBaseWeight() {
        return baseWeight;
    }

    /**
     * Updates the width, height and length of the personal items to new
     * values.
     * @param width new width of this personal item in cm
     * @param height new height of this personal item in cm
     * @param length new length of this personal item in cm
     */
    protected void setDimensions(double width,
                                 double height,
                                 double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Returns the human-readable string representation of the personal item.
     * <p>
     * The format of the string to return is:
     *     'class' ('owner')
     * where,
     *     'class' is the personal item's instance class simple name, and
     *     'owner' is this personal item's owner.
     * Example:
     *      Book (Bob) NOT Personal (Bob)
     * @return string representation of this personal item
     */
    public String toString() {
        return getClass().getSimpleName() + " ("
                + getOwner() + ")";
    }
}