package md2html.functions;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParagraphTest {

    @Test
    public void testValid() {
        assertTrue(new Paragraph().find("This is a paragraph"));
    }

    @Test
    public void testInvalid() {
        assertFalse(new Paragraph().find(""));
    }
}