package mms.display;

import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.furniture.Furniture;
import mms.personal.Book;
import mms.personal.Clothes;
import mms.personal.Laptop;
import mms.storage.Bag;
import mms.storage.Box;
import mms.storage.MovingTruck;
import mms.storage.Storage;
import mms.utility.Packable;

import static mms.utility.Size.SMALL;
import static mms.utility.Size.MEDIUM;
import static mms.utility.Size.LARGE;

import static mms.personal.ClotheType.PANTS;
import static mms.personal.ClotheType.SHIRT;
import static mms.personal.ClotheType.SHORTS;
import static mms.personal.ClotheType.SOCKS;

import static mms.furniture.FurnitureType.BED;
import static mms.furniture.FurnitureType.CHAIR;
import static mms.furniture.FurnitureType.DESK;
import static mms.furniture.FurnitureType.TABLE;
import static mms.furniture.FurnitureType.TELEVISION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SimpleDisplay {
    public static void main(String[] args) {

        // TEST: CLASS BOOK
        System.out.println("TEST: CLASS BOOK .............................................");

        // Book 1
        System.out.println("    Book 1");
        Book book1 = new Book("Kimberley Taylor", "Horses in the Wild", false);
        System.out.println("        Owner: " + book1.getOwner());
        System.out.println("        Title: " + book1.getTitle());
        System.out.println("        Dimensions: ("
                + book1.getWidth() + ", "
                + book1.getHeight() + ", "
                + book1.getLength() + ")");

        // Book 2
        System.out.println("    Book 2");
        Book book2 = new Book("Imaad Thassim", "Brown Boy Things", true);
        System.out.println("        Owner: " + book2.getOwner());
        System.out.println("        Title: " + book2.getTitle());
        System.out.println("        Dimensions: ("
                + book2.getWidth() + ", "
                + book2.getHeight() + ", "
                + book2.getLength() + ")");

        // Book 3
        System.out.println("    (EXCEPTION) Book 3");
        try {
            Book book3 = new Book("", "Hello, World!", true);
        }
        catch (IllegalArgumentException exception) {
            System.out.println("        IllegalArgumentException: Null OR Empty Owner");
        }

        // Book 4
        System.out.println("    (EXCEPTION) Book 4");
        try {
            Book book4 = new Book("David Taylor", null, false);
        }
        catch (IllegalArgumentException exception) {
            System.out.println("        IllegalArgumentException: Null OR Empty Title");
        }

        // METHOD Get Title
        System.out.println("    METHOD: getTitle");
        System.out.println("        Book 1: " + book1.getTitle());
        System.out.println("        Book 2: " + book2.getTitle());

        // METHOD To String
        System.out.println("    METHOD: toString");
        System.out.println("        " + book1.toString());
        System.out.println("        " + book2.toString());


        // TEST: CLASS CLOTHES
        System.out.println("TEST: CLASS CLOTHES ..........................................");

        // Clothes 1
        System.out.println("    Clothes 1");
        Clothes clothes1 = new Clothes("Lucy Igoe", SMALL, PANTS);
        System.out.println("        Owner: " + clothes1.getOwner());
        System.out.println("        Size: " + clothes1.getSize());
        System.out.println("        Type: " + clothes1.getType());
        System.out.println("        Dimensions: ("
                + clothes1.getWidth() + ", "
                + clothes1.getHeight() + ", "
                + clothes1.getLength() + ")");

        // Clothes 2
        System.out.println("    Clothes 2");
        Clothes clothes2 = new Clothes("Lucy Igoe", MEDIUM, SHIRT);
        System.out.println("        Owner: " + clothes2.getOwner());
        System.out.println("        Size: " + clothes2.getSize());
        System.out.println("        Type: " + clothes2.getType());
        System.out.println("        Dimensions: ("
                + clothes2.getWidth() + ", "
                + clothes2.getHeight() + ", "
                + clothes2.getLength() + ")");

        // Clothes 3
        System.out.println("    Clothes 3");
        Clothes clothes3 = new Clothes("Lucy Igoe", SMALL, SHORTS);
        System.out.println("        Owner: " + clothes3.getOwner());
        System.out.println("        Size: " + clothes3.getSize());
        System.out.println("        Type: " + clothes3.getType());
        System.out.println("        Dimensions: ("
                + clothes3.getWidth() + ", "
                + clothes3.getHeight() + ", "
                + clothes3.getLength() + ")");

        // Clothes 4
        System.out.println("    Clothes 4");
        Clothes clothes4 = new Clothes("Lucy Igoe", LARGE, SOCKS);
        System.out.println("        Owner: " + clothes4.getOwner());
        System.out.println("        Size: " + clothes4.getSize());
        System.out.println("        Type: " + clothes4.getType());
        System.out.println("        Dimensions: ("
                + clothes4.getWidth() + ", "
                + clothes4.getHeight() + ", "
                + clothes4.getLength() + ")");

        // Clothes 5 (Missing Size, Missing Type)
        System.out.println("    Clothes 5 (Missing Size, Missing Type)");
        Clothes clothes5 = new Clothes("Lucy Igoe", null, null);
        System.out.println("        Owner: " + clothes5.getOwner());
        System.out.println("        Size: " + clothes5.getSize());
        System.out.println("        Type: " + clothes5.getType());
        System.out.println("        Dimensions: ("
                + clothes5.getWidth() + ", "
                + clothes5.getHeight() + ", "
                + clothes5.getLength() + ")");

        // Clothes 6
        System.out.println("    (EXCEPTION) Clothes 6");
        try {
            Clothes clothes6 = new Clothes(null, null, null);
        }
        catch (IllegalArgumentException exception) {
            System.out.println("        IllegalArgumentException: Null OR Empty Owner");
        }

        // METHOD Get Type
        System.out.println("    METHOD: getType");
        System.out.println("        Clothes 1: " + clothes1.getType());
        System.out.println("        Clothes 2: " + clothes2.getType());
        System.out.println("        Clothes 3: " + clothes3.getType());
        System.out.println("        Clothes 4: " + clothes4.getType());
        System.out.println("        Clothes 5: " + clothes5.getType());

        // METHOD Get Size
        System.out.println("    METHOD: getSize");
        System.out.println("        Clothes 1: " + clothes1.getSize());
        System.out.println("        Clothes 2: " + clothes2.getSize());
        System.out.println("        Clothes 3: " + clothes3.getSize());
        System.out.println("        Clothes 4: " + clothes4.getSize());
        System.out.println("        Clothes 5: " + clothes5.getSize());

        // METHOD To String
        System.out.println("    METHOD: toString");
        System.out.println("        " + clothes1.toString());
        System.out.println("        " + clothes2.toString());
        System.out.println("        " + clothes3.toString());
        System.out.println("        " + clothes4.toString());
        System.out.println("        " + clothes5.toString());


        // TEST: CLASS LAPTOP
        System.out.println("TEST: CLASS LAPTOP ...........................................");

        // Laptop 1
        System.out.println("    Laptop 1");
        Laptop laptop1 = new Laptop("Emily Taylor", 5);
        System.out.println("        Owner: " + laptop1.getOwner());
        System.out.println("        Age: " + laptop1.getAge());
        System.out.println("        Dimensions: ("
                + laptop1.getWidth() + ", "
                + laptop1.getHeight() + ", "
                + laptop1.getLength() + ")");

        // Laptop 2
        System.out.println("    Laptop 1");
        Laptop laptop2 = new Laptop("Stephanie Taylor", 8);
        System.out.println("        Owner: " + laptop2.getOwner());
        System.out.println("        Age: " + laptop2.getAge());
        System.out.println("        Dimensions: ("
                + laptop2.getWidth() + ", "
                + laptop2.getHeight() + ", "
                + laptop2.getLength() + ")");

        // Laptop 3
        System.out.println("    (EXCEPTION) Laptop 3");
        try {
            Laptop laptop3 = new Laptop("Kimberley Taylor", -2);
        }
        catch (IllegalArgumentException exception) {
            System.out.println("        IllegalArgumentException: Age Less Than Zero");
        }

        // METHOD Get Age
        System.out.println("    METHOD: getAge");
        System.out.println("        Clothes 1: " + laptop1.getAge());

        // METHOD To String
        System.out.println("    METHOD: toString");
        System.out.println("        " + laptop1.toString());
        System.out.println("        " + laptop2.toString());


        // TEST: CLASS FURNITURE
        System.out.println("TEST: CLASS FURNITURE ........................................");

        // Furniture 1
        System.out.println("    Furniture 1");
        Furniture furniture1 = new Furniture(BED);
        System.out.println("        Type: " + furniture1.getType());
        System.out.println("        Dimensions: ("
                + furniture1.getWidth() + ", "
                + furniture1.getHeight() + ", "
                + furniture1.getLength() + ")");

        // Furniture 2
        System.out.println("    Furniture 2");
        Furniture furniture2 = new Furniture(CHAIR);
        System.out.println("        Type: " + furniture2.getType());
        System.out.println("        Dimensions: ("
                + furniture2.getWidth() + ", "
                + furniture2.getHeight() + ", "
                + furniture2.getLength() + ")");

        // Furniture 3
        System.out.println("    Furniture 3");
        Furniture furniture3 = new Furniture(DESK);
        System.out.println("        Type: " + furniture3.getType());
        System.out.println("        Dimensions: ("
                + furniture3.getWidth() + ", "
                + furniture3.getHeight() + ", "
                + furniture3.getLength() + ")");

        // Furniture 4
        System.out.println("    Furniture 4");
        Furniture furniture4 = new Furniture(TABLE);
        System.out.println("        Type: " + furniture4.getType());
        System.out.println("        Dimensions: ("
                + furniture4.getWidth() + ", "
                + furniture4.getHeight() + ", "
                + furniture4.getLength() + ")");

        // Furniture 5
        System.out.println("    Furniture 5");
        Furniture furniture5 = new Furniture(TELEVISION);
        System.out.println("        Type: " + furniture5.getType());
        System.out.println("        Dimensions: ("
                + furniture5.getWidth() + ", "
                + furniture5.getHeight() + ", "
                + furniture5.getLength() + ")");

        // METHOD Get Type
        System.out.println("    METHOD: getType");
        System.out.println("        Furniture 1: " + furniture1.getType());
        System.out.println("        Furniture 2: " + furniture2.getType());
        System.out.println("        Furniture 3: " + furniture3.getType());
        System.out.println("        Furniture 4: " + furniture4.getType());
        System.out.println("        Furniture 5: " + furniture5.getType());

        // METHOD To String
        System.out.println("    METHOD: toString");
        System.out.println("        " + furniture1.toString());
        System.out.println("        " + furniture2.toString());
        System.out.println("        " + furniture3.toString());
        System.out.println("        " + furniture4.toString());
        System.out.println("        " + furniture5.toString());


        // TEST: CLASS BAG
        System.out.println("TEST: CLASS BAG ..............................................");

        // Bag 1
        System.out.println("    Bag 1");
        Bag bag1 = new Bag(15, 30, 20, SMALL);
        System.out.println("        Dimensions: ("
                + bag1.getWidth() + ", "
                + bag1.getHeight() + ", "
                + bag1.getLength() + ")");
        System.out.println("        Size: " + bag1.getSize());

        // Bag 2
        System.out.println("    Bag 2");
        Bag bag2 = new Bag(40, 35, 30);
        System.out.println("        Dimensions: ("
                + bag2.getWidth() + ", "
                + bag2.getHeight() + ", "
                + bag2.getLength() + ")");
        System.out.println("        Size: " + bag2.getSize());

        // Bag 3
        System.out.println("    Bag 2");
        Bag bag3 = new Bag(15, 50, 50, LARGE);
        System.out.println("        Dimensions: ("
                + bag3.getWidth() + ", "
                + bag3.getHeight() + ", "
                + bag3.getLength() + ")");
        System.out.println("        Size: " + bag3.getSize());

        // METHOD Get Size
        System.out.println("    METHOD: getSize");
        System.out.println("        Bag 1: " + bag1.getSize());
        System.out.println("        Bag 2: " + bag2.getSize());
        System.out.println("        Bag 3: " + bag3.getSize());

        // METHOD Get Capacity
        System.out.println("    METHOD: getCapacity");
        System.out.println("        Bag 1: " + bag1.getCapacity());
        System.out.println("        Bag 2: " + bag2.getCapacity());
        System.out.println("        Bag 3: " + bag3.getCapacity());

        // METHOD Pack
        System.out.println("    METHOD: pack");

        // Bag 1
        System.out.println("        BAG 1 ......................................");
        System.out.println("            Capacity: " + bag1.getCapacity());
        System.out.println("        PACK: Furniture 1 to Bag 1");
        try {
            bag1.pack(furniture1);
        }
        catch (PackingException exception) {
            System.out.println("            BadItemException: NOT Instance of Personal Class");
        }

        System.out.println("        ELEMENTS");
        System.out.println("            " + bag1.getElements());

        // METHOD Unpack
        System.out.println("    METHOD: unpack");
        System.out.println("        BAG 2 ......................................");
        System.out.println("            ELEMENTS: " + bag2.getElements());
        System.out.println("        UNPACK: " + bag2.unpack());
        System.out.println("            ELEMENTS: " + bag2.getElements());
        System.out.println("        UNPACK: " + bag2.unpack());
        System.out.println("            ELEMENTS: " + bag2.getElements());
        System.out.println("        UNPACK: " + bag2.unpack());
        System.out.println("            ELEMENTS: " + bag2.getElements());
        System.out.println("        UNPACK: " + bag2.unpack());
        System.out.println("            ELEMENTS: " + bag2.getElements());

        // METHOD To String
        System.out.println("    METHOD: toString");
        System.out.println("        " + bag1.toString());
        System.out.println("        " + bag2.toString());
        System.out.println("        " + bag3.toString());


        // TEST: CLASS BOX
        System.out.println("TEST: CLASS BOX ..............................................");

        // Box 1
        System.out.println("    Box 1");
        Box box1 = new Box(40, 40, 40, SMALL, "Bedroom");
        System.out.println("        Dimensions: ("
                + box1.getWidth() + ", "
                + box1.getHeight() + ", "
                + box1.getLength() + ")");
        System.out.println("        Size: " + box1.getSize());

        // Box 2
        System.out.println("    Box 2");
        Box box2 = new Box(40, 40, 40, "Kitchen");
        System.out.println("        Dimensions: ("
                + box2.getWidth() + ", "
                + box2.getHeight() + ", "
                + box2.getLength() + ")");
        System.out.println("        Size: " + box2.getSize());

        // Box 3 (Comment = Empty String)
        System.out.println("    Box 3");
        Box box3 = new Box(300, 350, 350, LARGE, "");
        System.out.println("        Dimensions: ("
                + box3.getWidth() + ", "
                + box3.getHeight() + ", "
                + box3.getLength() + ")");
        System.out.println("        Size: " + box3.getSize());

        // METHOD Is Fragile
        System.out.println("    METHOD: isFragile");
        System.out.println("        Box 1: " + box1.isFragile());
        System.out.println("        Box 2: " + box2.isFragile());
        System.out.println("        Box 3: " + box3.isFragile());

        // METHOD Get Comment
        System.out.println("    METHOD: getComment");
        System.out.println("        Box 1: " + box1.getComment());
        System.out.println("        Box 2: " + box2.getComment());
        System.out.println("        Box 3: " + box3.getComment());

        // METHOD Pack
        System.out.println("    METHOD: pack");
        // Box 3
        System.out.println("        BOX 3 ......................................");
        System.out.println("            FRAGILE:  " + box3.isFragile());
        try {
            System.out.println("        PACK: Furniture 1 to Box 3");
            box3.pack(furniture1);
            System.out.println("            ELEMENTS: " + box3.getElements());
            System.out.println("            FRAGILE:  " + box3.isFragile());
            System.out.println("        PACK: Laptop 1 to Box 3");
            box3.pack(laptop1);
            System.out.println("            ELEMENTS: " + box3.getElements());
            System.out.println("            FRAGILE:  " + box3.isFragile());
            System.out.println("        PACK: Furniture 5 to Box 3");
            box3.pack(furniture5);
            System.out.println("            ELEMENTS: " + box3.getElements());
            System.out.println("            FRAGILE:  " + box3.isFragile());
        }
        catch (PackingException exception) {
            // Do Nothing
        }

        // METHOD Unpack
        System.out.println("    METHOD: unpack");
        System.out.println("        BOX 3 ......................................");
        System.out.println("            ELEMENTS: " + box3.getElements());
        System.out.println("        UNPACK: " + box3.unpack());
        System.out.println("            ELEMENTS: " + box3.getElements());
        System.out.println("        UNPACK: " + box3.unpack());
        System.out.println("            ELEMENTS: " + box3.getElements());
        System.out.println("        UNPACK: " + box3.unpack());
        System.out.println("            ELEMENTS: " + box3.getElements());
        System.out.println("        UNPACK: " + box3.unpack());
        System.out.println("            ELEMENTS: " + box3.getElements());

        // METHOD To String
        System.out.println("    METHOD: toString");
        // Pack Fragile Items
        try {
            box3.pack(laptop1);
            box3.pack(furniture5);
        }
        catch (PackingException exception) {
            // Do Nothing
        }
        System.out.println("        " + box1.toString());
        System.out.println("        " + box2.toString());
        System.out.println("        " + box3.toString());


        // TEST: CLASS MOVING TRUCK
        System.out.println("TEST: MOVING TRUCK ...........................................");

        // Moving Truck 1
        System.out.println("    Moving Truck 1");
        MovingTruck movingTruck1 = new MovingTruck(600, 1200, 1700);
        System.out.println("        Dimensions: ("
                + movingTruck1.getWidth() + ", "
                + movingTruck1.getHeight() + ", "
                + movingTruck1.getLength() + ")");
        System.out.println("        Size: " + movingTruck1.getSize());

        // Moving Truck 2
        System.out.println("    Moving Truck 2");
        MovingTruck movingTruck2 = new MovingTruck(250, 300, 1500, MEDIUM);
        System.out.println("        Dimensions: ("
                + movingTruck2.getWidth() + ", "
                + movingTruck2.getHeight() + ", "
                + movingTruck2.getLength() + ")");
        System.out.println("        Size: " + movingTruck2.getSize());

        // Moving Truck 3
        System.out.println("    Moving Truck 3");
        try {
            MovingTruck movingTruck3 = new MovingTruck(200, 200, 1000, SMALL);
        }
        catch (IllegalArgumentException exception) {
            System.out.println("        IllegalArgumentException: Length Less Than 1500");
        }

        // METHOD Get Volume
        System.out.println("    METHOD: getVolume");
        System.out.println("        Moving Truck 1: " + String.format("%.2f", movingTruck1.getVolume()));
        System.out.println("        Moving Truck 2: " + String.format("%.2f", movingTruck2.getVolume()));

        // METHOD Pack
        System.out.println("    METHOD: pack");
        // Moving Truck 1
        System.out.println("        MOVING TRUCK 1 .............................");
        System.out.println("            Dimensions: ("
                + movingTruck1.getWidth() + ", "
                + movingTruck1.getHeight() + ", "
                + movingTruck1.getLength() + ")");
        System.out.println("        PACK: Laptop 1 to Moving Truck 1");
        try {
            movingTruck1.pack(laptop1);
            System.out.println("            Dimensions: ("
                    + laptop1.getWidth() + ", "
                    + laptop1.getHeight() + ", "
                    + laptop1.getLength() + ")");
        }
        catch (PackingException exception) {
            // Do Nothing
        }System.out.println("        PACK: Laptop 2 to Moving Truck 1");
        try {
            movingTruck1.pack(laptop2);
            System.out.println("            Dimensions: ("
                    + laptop2.getWidth() + ", "
                    + laptop2.getHeight() + ", "
                    + laptop2.getLength() + ")");
        }
        catch (PackingException exception) {
            // Do Nothing
        }
        System.out.println("        PACK: Furniture 3 to Moving Truck 1");
        try {
            movingTruck1.pack(furniture3);
            System.out.println("            Dimensions: ("
                    + furniture3.getWidth() + ", "
                    + furniture3.getHeight() + ", "
                    + furniture3.getLength() + ")");
        }
        catch (PackingException exception) {
            // Do Nothing
        }
        System.out.println("        PACK: Laptop 1 to Moving Truck 1");
        try {
            movingTruck1.pack(laptop1);
        }
        catch (PackingException exception) {
            System.out.println("            PackingOrderException: Only Furniture");
        }
        System.out.println("        PACK: Furniture 4 to Moving Truck 1");
        try {
            movingTruck1.pack(furniture4);
            System.out.println("            Dimensions: ("
                    + furniture4.getWidth() + ", "
                    + furniture4.getHeight() + ", "
                    + furniture4.getLength() + ")");
        }
        catch (PackingException exception) {
            // Do Nothing
        }
        System.out.println("        PACK: Furniture 4 to Moving Truck 1");
        try {
            movingTruck1.pack(furniture4);
            System.out.println("            Dimensions: ("
                    + furniture4.getWidth() + ", "
                    + furniture4.getHeight() + ", "
                    + furniture4.getLength() + ")");
        }
        catch (PackingException exception) {
            System.out.println("            StorageFullException: Over Width, Over Height");
        }
        System.out.println("        ELEMENTS: " + movingTruck1.getElements());

        // METHOD Unpack
        System.out.println("    METHOD: unpack");
        System.out.println("        MOVING TRUCK 1 .............................");
        System.out.println("            ELEMENTS: " + movingTruck1.getElements());
        System.out.println("        UNPACK: " + movingTruck1.unpack());
        System.out.println("            ELEMENTS: " + movingTruck1.getElements());
        System.out.println("        UNPACK: " + movingTruck1.unpack());
        System.out.println("            ELEMENTS: " + movingTruck1.getElements());
        System.out.println("        UNPACK: " + movingTruck1.unpack());
        System.out.println("            ELEMENTS: " + movingTruck1.getElements());
        System.out.println("        UNPACK: " + movingTruck1.unpack());
        System.out.println("            ELEMENTS: " + movingTruck1.getElements());
        System.out.println("        UNPACK: " + movingTruck1.unpack());
        System.out.println("            ELEMENTS: " + movingTruck1.getElements());

        // METHOD To String
        System.out.println("    METHOD: toString");
        // Moving Truck 1
        try {
            movingTruck1.pack(box2);
            movingTruck1.pack(furniture4);
            movingTruck1.pack(furniture5);
        }
        catch (PackingException exception) {
            // Do Nothing
        }
        try {
            box2.pack(box1);
            box1.pack(laptop1);
        }
        catch (PackingException exception) {
            // Do Nothing
        }
        System.out.println("        " + movingTruck1.toString());
        System.out.println("        " + movingTruck2.toString());

        // METHOD Get Elements Of Type
        System.out.println("    METHOD: toString");
        System.out.println("        ELEMENTS: " + movingTruck1.getElements());

        // METHOD To String (Level)
        System.out.println("    METHOD: toString(level)");
        System.out.println("        ELEMENTS: " + movingTruck1.getElements());
        System.out.println("           BOX 2: " + box2.getElements());
        System.out.print(movingTruck1.toString(2));

        System.out.println("\n");

        // MOVING TRUCK TEST CLASS
        System.out.println("MOVING TRUCK TEST CLASS ......................................");

        MovingTruck movingTruckA = new MovingTruck(1000, 1000, 2500);
        MovingTruck movingTruckB = new MovingTruck(900, 900, 2000, LARGE);
        MovingTruck movingTruck3 = new MovingTruck(750, 750, 1750, MEDIUM);
        MovingTruck movingTruck4 = new MovingTruck(500, 600, 1500, SMALL);

        // create packable storage
        Bag bag = new Bag(50, 50 ,50);
        Box boxA = new Box(100, 100, 100, "Comment1");
        Box boxB = new Box(300, 300, 300, LARGE, "Comment2");

        // create personal items
        Book book = new Book("BookOwner", "BookTitle", false);
        Laptop laptop = new Laptop("LaptopOwner", 10);
        Clothes pants = new Clothes("PantsOwner", LARGE, PANTS);
        Clothes shirt = new Clothes("ShirtOwner", SMALL, SHIRT);
        Clothes shorts = new Clothes("ShortsOwner", MEDIUM, SHORTS);
        Clothes socks = new Clothes("SocksOwner", SMALL, SOCKS);

        // create furniture
        Furniture bed = new Furniture(BED);
        Furniture chair = new Furniture(CHAIR);
        Furniture desk = new Furniture(DESK);
        Furniture table = new Furniture(TABLE);
        Furniture television = new Furniture(TELEVISION);

        try {
            movingTruck3.pack(laptop);
            movingTruck3.pack(bed);
            movingTruck3.pack(laptop);
        } catch (PackingException exception) {
            // Empty
        }
        assertEquals("[Laptop (LaptopOwner) - 10, Furniture (BED)]",
                movingTruck3.getElements().toString());

        System.out.println(movingTruck3.getElements().toString());

        movingTruck3.unpack();
        movingTruck3.unpack();

        for (int count = 0; count < movingTruck3.getCapacity() + 1; count++) {
            try {
                movingTruck3.pack(laptop);
            } catch (PackingException exception) {
                // Do Nothing
            }
        }
        assertEquals(movingTruck3.getOccupiedCapacity(), movingTruck3.getCapacity());
        assertEquals("[Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10, " +
                        "Laptop (LaptopOwner) - 10]",
                movingTruck3.getElements().toString());
        System.out.println(movingTruck3.getOccupiedCapacity());
        System.out.println(movingTruck3.getCapacity());
        System.out.println(movingTruck3.getElements().toString());

    }
}