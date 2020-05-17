package md2html.functions;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinkTest {

    @Test
    public void testValid() {
        assertTrue(new Link().find("[MailChip Link](https://www.mailchimp.com)"));
    }

    @Test
    public void testInvalid() {
        assertFalse(new Link().find("This is a mailchimp URL"));
    }
}