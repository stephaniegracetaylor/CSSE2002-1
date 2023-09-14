package mms.personal;

import mms.utility.Size;

/**
 * Clothes owned by someone. Clothes come in many types and sizes.
 */
public class Clothes extends Personal {
    /**
     * size of this clothing item
     */
    private Size size;

    /**
     * type of this clothing item
     */
    private ClotheType type;

    /**
     * Creates clothes with a particular owner, size and type.
     * <p>
     * Clothing has the following dimensions determined by their size.
     *     Size (cm)    Width   Height  Length
     *     SMALL         40      65      10
     *     MEDIUM        50      70      10
     *     LARGE         55      75      10
     * @param owner owner of the clothing
     * @param size size of the clothing
     * @param type type of the clothing
     */
    public Clothes(String owner, Size size, ClotheType type) {
        super(owner);
        this.size = size;
        this.type = type;

        if (size == Size.SMALL) {
            setDimensions(40, 65, 10);
        } else if (size == Size.MEDIUM) {
            setDimensions(50, 70, 10);
        } else if (size == Size.LARGE) {
            setDimensions(55, 75, 10);
        }
    }

    /**
     * Returns the type of the clothing item.
     * @return type of this clothing item
     */
    public ClotheType getType() {
        return type;
    }

    /**
     * Returns the size of the clothing item.
     * @return size of this clothing item
     */
    public Size getSize() {
        return size;
    }

    /**
     * Returns the human-readable string representation of the clothing.
     * <p>
     * The format of the string to return is:
     *     Clothes ('owner') ('size', 'type')
     * where,
     *     'owner' is the owner of this clothing,
     *     'size' is the size of this clothing, and
     *     'type' is the type of this clothing.
     * Example:
     *     Clothes (Bob) (SMALL, SHIRT)
     * @return string representation of this clothing item
     */
    public String toString() {
        return getClass().getSimpleName() + " ("
                + getOwner() + ") ("
                + getSize() + ", "
                + getType() + ")";
    }
}
