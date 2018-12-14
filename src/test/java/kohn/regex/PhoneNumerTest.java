package kohn.regex;


import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumerTest {

    @Test
    public void testPhoneNumberMatching(){
        //given
        Pattern pattern = Pattern.compile("(\\d{3}[\\s\\-]?)?\\d{3}[\\s\\-]?\\d{4}");
        String phoneNumber = "this is a phone number 718 666 9090"
                + "another number is 888 2222";

        Matcher matcher = pattern.matcher(phoneNumber);
        assertTrue(matcher.find());
        assertEquals("718 666 9090", phoneNumber.substring(matcher.start(), matcher.end()));

        assertTrue(matcher.find());
        assertEquals("888 2222", phoneNumber.substring(matcher.start(), matcher.end()));
        assertFalse(matcher.find());
    }
}
