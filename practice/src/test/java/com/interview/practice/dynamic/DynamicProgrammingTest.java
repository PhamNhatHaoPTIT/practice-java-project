package com.interview.practice.dynamic;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DynamicProgrammingTest {
    // longest increasing subsequence

    // 0 1 2 3 4 5 6
    // 1 2 6 4 7 2 4
    // return: 1-2-4-7 or 1-2-6-7: length = 4

    // 1 2 3 3 4 2 3
    // 0 1 2 3 4 5 6

    // i: 3
    // j: 2

    @Test
    public void testLongestIncreasing() {
        int[] a = {1,2,6,4,7,2,4};
        Assert.assertEquals(4, countLongestIncrease(a));
    }

    private int countLongestIncrease(int[] a) {
        int[] subArrayIncrease = new int[a.length];
        Arrays.fill(subArrayIncrease, 1);

        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < i; j++) {
                if(a[i] > a[j]) {
                    subArrayIncrease[i] = Math.max(subArrayIncrease[i], subArrayIncrease[j] + 1);
                }
            }
        }
        return Arrays.stream(subArrayIncrease).max().getAsInt();
    }

}
