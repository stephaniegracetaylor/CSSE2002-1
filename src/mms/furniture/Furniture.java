package mms.furniture;

import mms.utility.Packable;

/**
 * Represents some household furniture that needs to be moved.
 */
public class Furniture extends Object implements Packable {
    /**
     * furniture type of this furniture
     */
    private FurnitureType type;

    /**
     * conversion ratio for dimensions from metres to centimetres
     */
    private int conversionRatio = 100;

    /**
     * Creates Furniture of the specified type.
     * By default, furniture has no specified location.
     * @param type type of the furniture
     */
    public Furniture(FurnitureType type) {
        this.type = type;
    }

    /**
     * Returns the type of the furniture.
     * @return type of this furniture
     */
    public FurnitureType getType() {
        return type;
    }

    /**
     * Returns the width of the furniture in cm.
     * @return width of this object
     */
    public double getWidth() {
        return type.width * conversionRatio;
    }

    /**
     * Returns the height of the furniture in cm.
     * @return height of this object
     */
    public double getHeight() {
        return type.height * conversionRatio;
    }

    /**
     * Returns the length of the furniture in cm.
     * @return length of this object
     */
    public double getLength() {
        return type.length * conversionRatio;
    }

    /**
     * Returns the human-readable string representation of the furniture.
     * <p>
     * The format of the string to return is:
     *     Furniture ('type')
     * where,
     *     'type' is the type of this furniture.
     * Example:
     *     Furniture (CHAIR)
     * @return string representation of this furniture
     */
    public String toString() {
        return getClass().getSimpleName() + " ("
                + getType() + ")";
    }
}