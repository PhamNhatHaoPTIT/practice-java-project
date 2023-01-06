package com.interview.practice.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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

    // Remove duplicate in sorted array and return new length
    // 1 2 3 5 6 6 6 7 10
    private int removeDuplicates(int[] array) {
        int length = 0;
        for(int i = 0; i < array.length; i++) {
            if(i < array.length - 1 && array[i] == array[i + 1]) {
                continue;
            }
            array[length] = array[i];
            length++;
        }
        return length;
    }

    // longest harmonious subsequence
    // {1,3,2,2,5,2,3,7}
    private int findLHS(int[] nums) {
        // key: number, value: the time that number present
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(int i : map.keySet()) {
            if(map.containsKey(i + 1)) {
                res = Math.max(res, map.get(i) + map.get(i + 1));
            }
        }
        return res;
    }

    // high five
    // Given a list of scores of different students,
    // return the average score of each student's top five scores in the order of each student's id.
    // Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.
    private int[][] highFive(int[][] items) {
        final int COUNT = 5;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        Set<Integer> studentSet = new TreeSet<>();

        for(int[] item : items) {
            int studentId = item[0];
            int studentScore = item[1];
            map.computeIfAbsent(studentId, k -> new PriorityQueue<>()).add(studentScore);
            if (map.get(studentId).size() > COUNT) {
                map.get(studentId).poll();
            }
            studentSet.add(studentId);
        }

        int[][] ans = new int[map.size()][2];
        int idx = 0;

        for (int studentId : studentSet) {
            PriorityQueue<Integer> scores = map.get(studentId);
            int sum = 0;
            while (!scores.isEmpty()) {
                sum += scores.poll();
            }
            ans[idx++] = new int[]{studentId, sum / COUNT};
        }

        return ans;
    }

    private int countNumberConsistentString(String allowed, String[] words) {
        return (int) Arrays.stream(words).filter(
                    word -> word.chars().mapToObj(c -> (char) c).allMatch(c -> allowed.indexOf(c) != -1)
                )
                .count();
    }

    // rotate array
    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // find all pairs in an array with minimum absolute difference
    // Input: arr[] = {4, 2, 1, 3}
    // Output: {1, 2}, {2, 3}, {3, 4}
    private List<List<Integer>> findMin(List<Integer> arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.size();

        Collections.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++)
            minDiff = Math.min(minDiff, Math.abs(arr.get(i) - arr.get(i + 1)));

        for (int i = 0; i < n - 1; i++) {
            List<Integer> pair = new ArrayList<>();
            if (Math.abs(arr.get(i) - arr.get(i + 1)) == minDiff) {
                pair.add(Math.min(arr.get(i), arr.get(i + 1)));
                pair.add(Math.max(arr.get(i), arr.get(i + 1)));
                result.add(pair);
            }
        }
        return result;
    }

    // find top k frequent elements in an array
    private int[] topK(int[] nums, int k) {
        if(k == nums.length) return nums;
        Map<Integer, Integer> countNumberAppearance = new HashMap<>();
        for(int i : nums) {
            countNumberAppearance.put(i, countNumberAppearance.getOrDefault(i,0) + 1);
        }
        Queue<Integer> heap = new PriorityQueue<>(
                Comparator.comparingInt(countNumberAppearance::get)
        );
        for(int i : countNumberAppearance.keySet()) {
            heap.add(i);
            if(heap.size() > k) {
                heap.poll();
            }
        }
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            if(!heap.isEmpty()) {
                top[i] = heap.poll();
            }
        }
        return top;
    }

    // Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
    private int longestConsecutiveSequence(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int longestCount = 0;
        int result = 0;
        for(int i = 0; i < length; i++) {
            if(i > 0 && nums[i] - nums[i-1] == 1) {
                longestCount++;
            } else {
                longestCount = 1;
            }
            result = Math.max(result, longestCount);
        }
        return result;
    }

    // product expect self
    private int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        return result;
    }

    @Test
    public void testCheckDuplicateCharInString() {
        String str = "hello";
        boolean result = checkDuplicateCharInString(str);
        Assert.assertFalse(result);
    }

    @Test
    public void testRemoveDuplicate() {
        int[] array = {0,0,1,2,2,2,3,4};
        int length = removeDuplicates(array);
        Assert.assertEquals(5, length);
    }

    @Test
    public void testFindLHS() {
        int[] nums = {1,3,2,2,5,2,3,7};
        int res = findLHS(nums);
        Assert.assertEquals(5, res);
    }

    @Test
    public void testHighFive() {
        int[][] items = {
                {1,7}, {1,7}, {1,7}, {1,7}, {1,7},
                {2,7}, {2,7}, {2,7}, {2,10}, {2,10}, {3,7}, {2,6}
        };

        int[][] res = highFive(items);

    }

    @Test
    public void testRotateArray() {
        int[] array = {0,1,2,3,4};
        reverseArray(array, 0, array.length-1);
        Assert.assertEquals(1,1);
    }

    @Test
    public void findMinimum() {
        List<Integer> arr = new ArrayList<>();
        arr.add(4);
        arr.add(2);
        arr.add(1);
        arr.add(3);

        List<List<Integer>> result = findMin(arr);
        for (List<Integer> v : result) {
            for (int w : v)
                System.out.print(w + " ");
            System.out.println("");
        }
    }

    @Test
    public void findK() {
        int[] array = {1,2,3,1,4,4,1};
        int[] top = topK(array, 2);
        System.out.println("top");
    }

    @Test
    public void longestCount() {
        int[] array = {1,2,3,4,7,8,10,13,14,15,16,17,18};
        int x = longestConsecutiveSequence(array);
        System.out.println("longestConsecutiveSequence: " + x);
    }
    
    @Test
    public void test() {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x,y)->Integer.compare(y,x));
        queue.add(3);
        queue.add(1);
        queue.add(7);
        queue.add(10);
        System.out.println("QUEUE====================");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

}
