package mms.furniture;

/**
 * Enum to represent the size of various different types of furniture in the
 * simulation.
 * <p>
 * The characteristics of a type of furniture include width, height and length.
 *     Size (m)      Width   Height  Length
 *     BED            1.5     2.0     0.5
 *     CHAIR          0.5     1.5     0.5
 *     DESK           1.2     2.0     1.0
 *     TABLE          3.0     5.0     1.0
 *     TELEVISION     1.3     0.75    0.1
 */
public enum FurnitureType {
    /**
     * a bed that you can sleep on
     */
    BED(1.5, 2.0,  0.5),

    /**
     * a chair that you can sit on
     */
    CHAIR(0.5, 1.5,  0.5),

    /**
     * a desk that you can program at
     */
    DESK(1.2, 2.0,  1.0),

    /**
     * a dining room table
     */
    TABLE(3.0, 5.0,  1.0),

    /**
     * a television that you can relax at
     */
    TELEVISION(1.3, 0.75, 0.1);

    /**
     * width of this furniture type in metres
     */
    public final double width;

    /**
     * height of this furniture type in metres
     */
    public final double height;

    /**
     * length of this furniture type in metres
     */
    public final double length;

    /**
     * Creates FurnitureType of the specified width, height and length.
     * @param width width of furniture type in cm
     * @param height height of furniture type in cm
     * @param length length of furniture type in cm
     */
    FurnitureType(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }
}