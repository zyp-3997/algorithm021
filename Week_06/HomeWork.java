package GeekUniversity.week06;

import GeekUniversity.TreeNode;
import leetcode.easy.Factorial;

import java.util.*;

/**
 * @author 朱雨鹏
 * @create 2021-01-13 10:23
 */
public class HomeWork {
    //最小路径和-合并判断+无额外空间
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] = grid[i][j] + grid[i][j - 1];
                else if (j == 0) grid[i][j] = grid[i][j] + grid[i - 1][j];
                else grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
            }
        }
        return grid[row - 1][column - 1];
    }

    //最小路径和-二维dp
    public int minPathSum2(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int dp[][] = new int[row][column];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < column; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[row - 1][column - 1];
    }

    //回文子串
    public int countSubstrings(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int result = s.length();
        for (int i = 0; i < n; i++) dp[i][i] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;

    }


    //最长回文子串-动态规划
    public String longestPalindrome(String s) {
        if (s.length() < 2) return s;
        char[] chars = s.toCharArray();
        int maxLength = 1;
        int start = 0;
        boolean dp[][] = new boolean[s.length()][s.length()];
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else if (j - i > 3) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i - 1][j + 1];
                }
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    //最长回文子串-暴力法
    public String longestPalindrome2(String s) {
        if (s.length() < 2) return s;
        char[] chars = s.toCharArray();
        int length = 1;
        int start = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (j - i + 1 > length && isValid(chars, i, j)) {
                    length = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + length);

    }

    private boolean isValid(char[] chars, int i, int j) {
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public int rob(int[] nums) {
        int[] array = new int[nums.length];
        return dfs(nums, 0, array);
    }

    private int dfs(int[] nums, int level, int[] array) {
        if (level == nums.length) {
            return 0;
        }
        if (array[level] != 0) return array[level];
        int left = dfs(nums, level + 1, array);
        int right = dfs(nums, level + 2, array) + nums[level];
        array[level] = Math.max(left, right);
        return array[level];
    }

    //任务调度器
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

    //青蛙过河
    public class Solution {
        public boolean canCross(int[] stones) {
            return can_Cross(stones, 0, 0);
        }

        public boolean can_Cross(int[] stones, int ind, int jumpsize) {
            for (int i = ind + 1; i < stones.length; i++) {
                int gap = stones[i] - stones[ind];
                if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                    if (can_Cross(stones, i, gap)) {
                        return true;
                    }
                }
            }
            return ind == stones.length - 1;
        }
    }


}
