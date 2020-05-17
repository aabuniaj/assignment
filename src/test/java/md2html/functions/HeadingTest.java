package md2html.functions;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class HeadingTest {

    @Test
    public void testValid() {
        assertTrue(new Heading().find("# Heading 1"));
    }

    @Test
    public void testInvalid() {
        assertFalse(new Heading().find("#Testing## Heading"));
    }

    @Test
    public void testFormatting() {
        Heading heading = new Heading();
        heading.setInput("## Heading 2 Test");
        assertEquals(heading.getInput(), "Heading 2 Test");
    }

    @Test
    public void testValidLevel() {
        assertTrue(new Heading().find("###### Heading 6"));
    }

    @Test
    public void testInvalidLevel() {
        assertFalse(new Heading().find("####### Heading 7"));
    }

    @Test
    public void testHeadingHashInMiddle() {
        assertFalse(new Heading().find("testing if heading finds#inmiddle"));
    }

    @Test
    public void testStartWhitespace() {
        assertFalse(new Heading().find("  ##"));
    }
}