package GeekUniversity.week08;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 朱雨鹏
 * @create 2021-01-20 21:07
 */
public class HomeWork {
    //位运算-方法1
    public int hammingWeight(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    //位运算-方法2
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    //2的幂-方法1
    class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n == 0) return false;
            long x = (long) n;
            return (x & (x - 1)) == 0;
        }
    }

    //2的幂-方法2
    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    //颠倒的二进制位
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            //取出n的第(31-31)，0位,往左移i，31位
            result = result | (((n >> (31 - i)) & 1) << i);
        }
        return result;
    }

    //有效的字母异位词
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }

    //数组的相对排序
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int num1 : arr1) {
            count[num1]++;
        }
        int i = 0;
        for (int num2 : arr2) {
            while (count[num2] > 0) {
                arr1[i++] = num2;
                count[num2]--;
            }
        }
        for (int num1 = 0; num1 < count.length; num1++) {
            while (count[num1] > 0) {
                arr1[i++] = num1;
                count[num1]--;
            }
        }
        return arr1;
    }

    //LRU 缓存机制
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    //排序数组-冒泡排序
    public int[] sortArray1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    //排序数组-选择排序
    public int[] sortArray2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
        return nums;
    }

    //排序数组-插入排序
    public int[] sortArray3(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i;
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }

    //排序数组-快速排序
    public int[] sortArray4(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int left, int right) {
        if (left > right) return;
        int i = left;
        int j = right;
        int middle = nums[i];
        while (i != j) {
            while (i < j && nums[j] >= middle) j--;
            while (i < j && nums[i] <= middle) i++;
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[left] = nums[i];
        nums[i] = middle;
        sort(nums, left, i - 1);
        sort(nums, i + 1, right);
    }

    //排序数组-归并排序
    public int[] sortArray5(int[] nums) {
        dfs(nums, 0, nums.length - 1);
        return nums;
    }

    private void dfs(int[] nums, int left, int right) {
        if (left >= right) return;
        int middle = (left + right) >> 1;
        dfs(nums, left, middle);
        dfs(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    private void merge(int[] nums, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= right) {
            temp[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= middle) temp[k++] = nums[i++];
        while (j <= middle) temp[k++] = nums[j++];
        for (int p = 0; p < temp.length; p++) {
            nums[left + p] = temp[p];
        }
        //  System.arraycopy(temp, 0, nums, left, temp.length);
    }

    //排序数组-堆排序
    public int[] sortArray6(int[] nums) {
        if (nums.length == 0) return nums;
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapify(nums, nums.length, i);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            heapify(nums, i, 0);
        }
        return nums;
    }

    private void heapify(int[] nums, int length, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < length && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < length && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            heapify(nums, length, largest);
        }
    }
}
