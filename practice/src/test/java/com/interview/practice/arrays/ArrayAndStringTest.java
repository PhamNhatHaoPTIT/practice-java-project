package com.interview.practice.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haopn
 */
@RunWith(SpringRunner.class)
public class ArrayAndStringTest {

    // Implement an algorithm to determine if a string has all unique characters
    private boolean checkDuplicateCharInString(String str) {
        boolean[] char_set = new boolean[256];
        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if(char_set[val]) return false;
            char_set[val] = true;
        }
        return true;
    }

    @Test
    public void testCheckDuplicateCharInString() {
        String str = "hello";
        boolean result = checkDuplicateCharInString(str);
        Assert.assertFalse(result);
    }
}
