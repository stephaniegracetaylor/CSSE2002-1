package mms.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import mms.furniture.Furniture;
import mms.personal.Book;
import mms.personal.Clothes;
import mms.personal.Laptop;

import mms.exceptions.PackingOrderException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;

import static mms.furniture.FurnitureType.*;
import static mms.personal.ClotheType.*;
import static mms.utility.Size.*;

public class MovingTruckTest {
    private MovingTruck movingTruck1;
    private MovingTruck movingTruck2;

    private Bag bag;
    private Box box1;
    private Box box2;

    private Book book;
    private Laptop laptop;
    private Clothes pants;
    private Clothes shirt;
    private Clothes shorts;
    private Clothes socks;

    private Furniture bed;
    private Furniture chair;
    private Furniture desk;
    private Furniture table;
    private Furniture television;

    @Before
    public void setUp() {
        // create moving trucks
        movingTruck1 = new MovingTruck(1000, 1000, 2500);
        movingTruck2 = new MovingTruck(500, 600, 1600, SMALL);

        // create packable storage
        bag = new Bag(50, 50 ,50);
        box1 = new Box(100, 100, 100, "Comment1");
        box2 = new Box(300, 300, 300, LARGE, "Comment2");

        // create personal items
        book = new Book("BookOwner", "BookTitle", false);
        laptop = new Laptop("LaptopOwner", 10);
        pants = new Clothes("PantsOwner", LARGE, PANTS);
        shirt = new Clothes("ShirtOwner", SMALL, SHIRT);
        shorts = new Clothes("ShortsOwner", MEDIUM, SHORTS);
        socks = new Clothes("SocksOwner", SMALL, SOCKS);

        // create furniture
        bed = new Furniture(BED);
        chair = new Furniture(CHAIR);
        desk = new Furniture(DESK);
        table = new Furniture(TABLE);
        television = new Furniture(TELEVISION);
    }

    @After
    public void packUp() {
        // unpack every item from moving truck 1
        for (int count = 0; count < movingTruck1.getOccupiedCapacity(); count++) {
            movingTruck1.unpack();
        }
        // unpack every item from moving truck 2
        for (int count = 0; count < movingTruck2.getOccupiedCapacity(); count++) {
            movingTruck2.unpack();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void widthLessThanZeroTest()
            throws IllegalArgumentException {
        MovingTruck movingTruck = new MovingTruck(-50, 1000, 2500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void widthEqualToZeroTest()
            throws IllegalArgumentException{
        MovingTruck movingTruck = new MovingTruck(0, 1000, 2500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightLessThanZeroTest()
            throws IllegalArgumentException {
        MovingTruck movingTruck = new MovingTruck(1000, -50, 2500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightEqualToZeroTest()
            throws IllegalArgumentException {
        MovingTruck movingTruck = new MovingTruck(1000, 0, 2500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lengthLessThan1500Test()
            throws IllegalArgumentException {
        MovingTruck movingTruck = new MovingTruck(1000, 1000, 1499.9);
    }

    @Test
    public void getWidthTest() {
        assertTrue(Math.abs(1000.0 - movingTruck1.getWidth()) < 0.001);
        assertTrue(Math.abs(500.0 - movingTruck2.getWidth()) < 0.001);
    }

    @Test
    public void getHeightTest() {
        assertTrue(Math.abs(1000.0 - movingTruck1.getHeight()) < 0.001);
        assertTrue(Math.abs(600.0 - movingTruck2.getHeight()) < 0.001);
    }

    @Test
    public void getLengthTest() {
        assertTrue(Math.abs(2500.0 - movingTruck1.getLength()) < 0.001);
        assertTrue(Math.abs(1600.0 - movingTruck2.getLength()) < 0.001);
    }

    @Test
    public void getSizeTest() {
        assertEquals(LARGE, movingTruck1.getSize());
        assertEquals(SMALL, movingTruck2.getSize());
    }

    @Test
    public void getCapacityTest() {
        assertEquals(40, movingTruck1.getCapacity());
        assertEquals(12, movingTruck2.getCapacity());
    }

    @Test
    public void getMultiplierTest() {
        assertEquals(4, movingTruck1.getMultiplier());
        assertEquals(4, movingTruck2.getMultiplier());
    }

    @Test
    public void getVolumeTest() {
        assertTrue(Math.abs(1.0E9 - movingTruck1.getVolume()) < 0.001);
        assertTrue(Math.abs(3.0E7 - movingTruck2.getVolume()) < 0.001);
    }

    @Test(expected = PackingOrderException.class)
    public void packingOrderTest()
            throws PackingException {
        movingTruck1.pack(laptop);
        movingTruck1.pack(socks);
        movingTruck1.pack(bed);
        movingTruck1.pack(laptop);
        assertEquals("[Laptop (LaptopOwner) - 10, Furniture (BED)]",
                movingTruck1.getElements().toString());
    }

    @Test(expected = StorageFullException.class)
    public void occupiedCapacityGreaterThanCapacityTest()
            throws PackingException {
        for (int count = 0; count < movingTruck1.getCapacity() + 1; count++) {
            movingTruck1.pack(laptop);
        }
    }

    @Test
    public void occupiedCapacityEqualToCapacityTest()
            throws PackingException {
        for (int count = 0; count < movingTruck1.getCapacity(); count++) {
            movingTruck1.pack(laptop);
        }
        assertEquals(movingTruck1.getOccupiedCapacity(), movingTruck1.getCapacity());
    }

    @Test(expected = StorageFullException.class)
    public void overWidthOverHeightNotOverLengthTest()
            throws PackingException {
        /*
         *                   Width    Height   Length Less Cabin Length
         * Moving Truck 1     1000     1000     1000 i.e. 2500 less 1500
         *     TABLE           300      500      100
         *     TABLE           300      500      100
         *     TABLE           300      500      100
         *     TABLE           300      500      100
         *         Total:     1200     2000      400
         *                  > 1000   > 1000   < 1000
         */
        movingTruck1.pack(table);
        movingTruck1.pack(table);
        movingTruck1.pack(table);
        movingTruck1.pack(table);
    }

    @Test(expected = StorageFullException.class)
    public void overLengthNotOverWidthNotOverHeightTest()
            throws PackingException {
        /*
         *                   Width    Height   Length Less Cabin Length
         * Moving Truck 2      500      600      100 i.e. 1600 less 1500
         *     DESK            120      200      100
         *     DESK            120      200      100
         *         Total:      240      400      200
         *                   < 500    < 600    > 100
         */
        movingTruck2.pack(desk);
        movingTruck2.pack(desk);
    }

    @Test
    public void unpackOnlyFurnitureTest()
            throws PackingException {
        movingTruck1.pack(bed);
        movingTruck1.pack(chair);
        movingTruck1.pack(desk);

        assertEquals("[Furniture (BED), " +
                        "Furniture (CHAIR), " +
                        "Furniture (DESK)]",
                movingTruck1.getElements().toString());

        // unpack Furniture (DESK)
        movingTruck1.unpack();
        assertEquals("[Furniture (BED), " +
                        "Furniture (CHAIR)]",
                movingTruck1.getElements().toString());

        // unpack Furniture (CHAIR)
        movingTruck1.unpack();
        assertEquals("[Furniture (BED)]",
                movingTruck1.getElements().toString());

        // unpack Furniture (BED)
        movingTruck1.unpack();
        assertEquals("[]",
                movingTruck1.getElements().toString());

        // unpack null, return null
        assertNull(movingTruck1.unpack());
    }

    @Test
    public void unpackOnlyNonFurnitureTest()
            throws PackingException {
        movingTruck1.pack(bag);
        movingTruck1.pack(book);
        movingTruck1.pack(pants);
        movingTruck1.pack(shirt);

        assertEquals("[Bag (50.00, 50.00, 50.00) MEDIUM, " +
                        "Book (BookOwner) Title: BookTitle (Non-Fiction), " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShirtOwner) (SMALL, SHIRT)]",
                movingTruck1.getElements().toString());

        // unpack Bag (50.00, 50.00, 50.00) MEDIUM
        movingTruck1.unpack();
        assertEquals("[Book (BookOwner) Title: BookTitle (Non-Fiction), " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShirtOwner) (SMALL, SHIRT)]",
                movingTruck1.getElements().toString());

        // unpack Book (BookOwner) Title: BookTitle (Non-Fiction)
        movingTruck1.unpack();
        assertEquals("[Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShirtOwner) (SMALL, SHIRT)]",
                movingTruck1.getElements().toString());

        // unpack Clothes (PantsOwner) (LARGE, PANTS)
        movingTruck1.unpack();
        assertEquals("[Clothes (ShirtOwner) (SMALL, SHIRT)]",
                movingTruck1.getElements().toString());

        // unpack Clothes (ShirtOwner) (SMALL, SHIRT)
        movingTruck1.unpack();
        assertEquals("[]",
                movingTruck1.getElements().toString());

        // unpack null, return null
        assertNull(movingTruck1.unpack());
    }

    @Test
    public void unpackFurnitureAndNonFurnitureTest()
            throws PackingException {
        bag.pack(laptop);
        box1.pack(chair);
        box2.pack(television);
        movingTruck1.pack(bag);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);

        assertEquals("[Bag (50.00, 50.00, 50.00) MEDIUM, " +
                        "Box (100.00, 100.00, 100.00) MEDIUM - Comment1, " +
                        "Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE, " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS), " +
                        "Furniture (TABLE), " +
                        "Furniture (TELEVISION)]",
                movingTruck1.getElements().toString());

        // unpack Furniture (TELEVISION)
        movingTruck1.unpack();
        assertEquals("[Bag (50.00, 50.00, 50.00) MEDIUM, " +
                        "Box (100.00, 100.00, 100.00) MEDIUM - Comment1, " +
                        "Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE, " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS), " +
                        "Furniture (TABLE)]",
                movingTruck1.getElements().toString());

        // unpack Furniture (TABLE)
        movingTruck1.unpack();
        assertEquals("[Bag (50.00, 50.00, 50.00) MEDIUM, " +
                        "Box (100.00, 100.00, 100.00) MEDIUM - Comment1, " +
                        "Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE, " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS)]",
                movingTruck1.getElements().toString());

        // unpack Bag (50.00, 50.00, 50.00) MEDIUM
        movingTruck1.unpack();
        assertEquals("[Box (100.00, 100.00, 100.00) MEDIUM - Comment1, " +
                        "Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE, " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS)]",
                movingTruck1.getElements().toString());

        // unpack Box (100.00, 100.00, 100.00) MEDIUM - Comment1
        movingTruck1.unpack();
        assertEquals("[Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE, " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS)]",
                movingTruck1.getElements().toString());

        // unpack Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE
        movingTruck1.unpack();
        assertEquals("[Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS)]",
                movingTruck1.getElements().toString());

        // unpack Clothes (PantsOwner) (LARGE, PANTS)
        movingTruck1.unpack();
        assertEquals("[Clothes (ShortsOwner) (MEDIUM, SHORTS)]",
                movingTruck1.getElements().toString());

        // unpack Clothes (ShortsOwner) (MEDIUM, SHORTS)
        movingTruck1.unpack();
        assertEquals("[]",
                movingTruck1.getElements().toString());

        // unpack null, return null
        assertNull(movingTruck1.unpack());
    }

    @Test
    public void getElementsTest()
            throws PackingException {
        bag.pack(laptop);
        box1.pack(chair);
        box2.pack(television);
        movingTruck1.pack(bag);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);

        assertEquals("[Bag (50.00, 50.00, 50.00) MEDIUM, " +
                        "Box (100.00, 100.00, 100.00) MEDIUM - Comment1, " +
                        "Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE, " +
                        "Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS), " +
                        "Furniture (TABLE), " +
                        "Furniture (TELEVISION)]",
                movingTruck1.getElements().toString());
    }

    @Test
    public void getElementsOfTypeTest()
            throws PackingException {
        bag.pack(laptop);
        box1.pack(chair);
        box2.pack(television);
        movingTruck1.pack(bag);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);

        // elements of Bag class
        assertEquals("[Bag (50.00, 50.00, 50.00) MEDIUM]",
                movingTruck1.getElementsOfType(bag).toString());

        // elements of Box class
        assertEquals("[Box (100.00, 100.00, 100.00) MEDIUM - Comment1, " +
                        "Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE]",
                movingTruck1.getElementsOfType(box1).toString());

        // elements of Clothes class
        assertEquals("[Clothes (PantsOwner) (LARGE, PANTS), " +
                        "Clothes (ShortsOwner) (MEDIUM, SHORTS)]",
                movingTruck1.getElementsOfType(pants).toString());

        // elements of Furniture class
        assertEquals("[Furniture (TABLE), " +
                        "Furniture (TELEVISION)]",
                movingTruck1.getElementsOfType(table).toString());
    }

    @Test
    public void getOccupiedCapacityTest()
            throws PackingException {
        bag.pack(laptop);
        box1.pack(chair);
        box2.pack(television);
        movingTruck1.pack(bag);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);
        assertEquals(7, movingTruck1.getOccupiedCapacity());
    }

    @Test
    public void toStringTest()
            throws PackingException {
        movingTruck1.pack(bag);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);

        assertEquals("MovingTruck (7/40)",
                movingTruck1.toString());
    }

    @Test
    public void toStringLevelNoElementsTest() {
        assertEquals("        MovingTruck (0/40)",
                movingTruck1.toString(2));
    }

    @Test
    public void toStringLevelElementsNoNestedElementsTest()
            throws PackingException {
        movingTruck1.pack(bag);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);

        assertEquals("        MovingTruck (7/40)\n" +
                "            Bag (50.00, 50.00, 50.00) MEDIUM\n" +
                "            Box (100.00, 100.00, 100.00) MEDIUM - Comment1\n" +
                "            Box (300.00, 300.00, 300.00) LARGE - Comment2\n" +
                "            Clothes (PantsOwner) (LARGE, PANTS)\n" +
                "            Clothes (ShortsOwner) (MEDIUM, SHORTS)\n" +
                "            Furniture (TABLE)\n" +
                "            Furniture (TELEVISION)",
                movingTruck1.toString(2));
    }

    @Test
    public void toStringLevelElementsAndNestedElementsTest()
            throws PackingException {
        bag.pack(laptop);
        box1.pack(chair);
        box2.pack(television);
        movingTruck1.pack(bag);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);

        assertEquals("        MovingTruck (7/40)\n" +
                "            Bag (50.00, 50.00, 50.00) MEDIUM\n" +
                "                Laptop (LaptopOwner) - 10\n" +
                "            Box (100.00, 100.00, 100.00) MEDIUM - Comment1\n" +
                "                Furniture (CHAIR)\n" +
                "            Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE\n" +
                "                Furniture (TELEVISION)\n" +
                "            Clothes (PantsOwner) (LARGE, PANTS)\n" +
                "            Clothes (ShortsOwner) (MEDIUM, SHORTS)\n" +
                "            Furniture (TABLE)\n" +
                "            Furniture (TELEVISION)",
                movingTruck1.toString(2));
    }

    @Test
    public void toStringLevelElementsAndMultipleNestedElementsTest()
            throws PackingException {
        bag.pack(laptop);
        box1.pack(bag);
        box2.pack(television);
        movingTruck1.pack(box1);
        movingTruck1.pack(box2);
        movingTruck1.pack(pants);
        movingTruck1.pack(shorts);
        movingTruck1.pack(table);
        movingTruck1.pack(television);

        assertEquals("    MovingTruck (6/40)\n" +
                        "        Box (100.00, 100.00, 100.00) MEDIUM - Comment1\n" +
                        "            Bag (50.00, 50.00, 50.00) MEDIUM\n" +
                        "                Laptop (LaptopOwner) - 10\n" +
                        "        Box (300.00, 300.00, 300.00) LARGE - Comment2 FRAGILE\n" +
                        "            Furniture (TELEVISION)\n" +
                        "        Clothes (PantsOwner) (LARGE, PANTS)\n" +
                        "        Clothes (ShortsOwner) (MEDIUM, SHORTS)\n" +
                        "        Furniture (TABLE)\n" +
                        "        Furniture (TELEVISION)",
                movingTruck1.toString(1));
    }
}