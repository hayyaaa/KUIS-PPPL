import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringUtilsTest {
    private final StringUtils stringUtils = new StringUtils();

    @Test
    public void testReverseString() {
        assertEquals("olleH", stringUtils.reverseString("Hello"));
        assertEquals("rusak", stringUtils.reverseString("kasur"));
        assertEquals("987654321", stringUtils.reverseString("123456789"));
        assertNull(stringUtils.reverseString(null));
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(stringUtils.isPalindrome("A man, a plan, a canal, Panama"));
        assertTrue(stringUtils.isPalindrome("Madam"));
        assertFalse(stringUtils.isPalindrome("Hello"));
        assertFalse(stringUtils.isPalindrome(null));
    }

    @Test
    public void testCountVowels() {
        assertEquals(2, stringUtils.countVowels("haha"));
        assertEquals(5, stringUtils.countVowels("hahihuheho"));
        assertEquals(0, stringUtils.countVowels(""));
        assertEquals(0, stringUtils.countVowels(null));
    }

}
