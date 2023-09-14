package mms.personal;

/**
 * A laptop to do university work or gaming on.
 */
public class Laptop extends Personal {
    /**
     * age of this laptop in years
     */
    private int age;

    /**
     * Creates a laptop with the specified owner and age.
     * A laptop has a width of 35 cm, height of 20 cm and length of 2 cm.
     * @param owner owner of the laptop
     * @param age age of the laptop
     */
    public Laptop(String owner, int age)
            throws IllegalArgumentException {
        super(owner);

        if (age < 0) {
            throw new IllegalArgumentException();
        } else {
            this.age = age;
            setDimensions(35, 20, 2);
        }
    }

    /**
     * Returns the age of the laptop.
     * @return age of this laptop
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the human-readable string representation of the laptop
     * <p>
     * The format of the string to return is:
     *     Laptop ('owner') - 'age'
     * where,
     *     'owner' is the owner of this laptop, and
     *     'age' is the age of this laptop.
     * Example:
     *     Laptop (Thomas) - 2
     * @return string representation of this laptop
     */
    public String toString() {
        return getClass().getSimpleName() + " ("
                + getOwner() + ") - "
                + getAge();
    }
}