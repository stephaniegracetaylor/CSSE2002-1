package mms.utility;

/**
 * Interface to represent any items that can be packed into a container.
 */
public interface Packable {
    /**
     * Returns the width of the object in cm.
     * @return width of this object
     */
    double getWidth();

    /**
     * Returns the height of the object in cm.
     * @return height of this object
     */
    double getHeight();

    /**
     * Returns the length of the object in cm.
     * @return length of this object
     */
    double getLength();

    /**
     * Returns the volume of the object in cm3.
     * @return volume of this object
     */
    default double getVolume() {
        return getWidth() * getHeight() * getLength();
    }
}