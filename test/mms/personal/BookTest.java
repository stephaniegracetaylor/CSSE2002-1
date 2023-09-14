package mms.personal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookTest {
    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        book1 = new Book("Owner1", "Title1", true);
        book2 = new Book("Owner2", "Title2", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ownerNullTest()
            throws IllegalArgumentException {
        Book book = new Book(null, "Title", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ownerEmptyTest()
            throws IllegalArgumentException {
        Book book = new Book("", "Title", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void titleNullTest()
            throws IllegalArgumentException {
        Book book = new Book("Owner", null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void titleEmptyTest()
            throws IllegalArgumentException {
        Book book = new Book("Owner", "", true);
    }

    @Test
    public void getOwnerTest() {
        assertEquals("Owner1", book1.getOwner());
        assertEquals("Owner2", book2.getOwner());
    }

    @Test
    public void getTitleTest() {
        assertEquals("Title1", book1.getTitle());
        assertEquals("Title2", book2.getTitle());
    }

    @Test
    public void getWidthTest() {
        assertTrue(Math.abs(20.0 - book1.getWidth()) < 0.001);
        assertTrue(Math.abs(20.0 - book2.getWidth()) < 0.001);
    }

    @Test
    public void getHeightTest() {
        assertTrue(Math.abs(20.0 - book1.getHeight()) < 0.001);
        assertTrue(Math.abs(20.0 - book2.getHeight()) < 0.001);
    }

    @Test
    public void getLengthTest() {
        assertTrue(Math.abs(5.0 - book1.getLength()) < 0.001);
        assertTrue(Math.abs(5.0 - book2.getLength()) < 0.001);
    }

    @Test
    public void getBaseWeightTest() {
        assertEquals(250, book1.getBaseWeight());
        assertEquals(250, book2.getBaseWeight());
    }

    @Test
    public void setDimensionsTest() {
        book1.setDimensions(40, 40, 10);
        assertTrue(40 - book1.getWidth() < 0.001);
        assertTrue(40 - book1.getHeight() < 0.001);
        assertTrue(10 - book1.getLength() < 0.001);

        book2.setDimensions(35, 35, 15);
        assertTrue(35 - book2.getWidth() < 0.001);
        assertTrue(35 - book2.getHeight() < 0.001);
        assertTrue(15 - book2.getLength() < 0.001);
    }

    @Test
    public void toStringFictionTest() {
        assertEquals("Book (Owner1) Title: Title1 (Fiction)",
                book1.toString());
    }

    @Test
    public void toStringNonFictionTest() {
        assertEquals("Book (Owner2) Title: Title2 (Non-Fiction)",
                book2.toString());
    }
}