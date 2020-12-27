package GeekUniversity.week04;

import java.util.*;

/**
 * @author 朱雨鹏
 * @create 2020-12-26 8:53
 */
public class HomeWork {

    //柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) return false;
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five > 0) {
                    five--;
                    ten++;
                } else {
                    return false;
                }
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five = five - 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    //买卖股票的最佳时机 II
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i + 1] > prices[i]) {
                max = max+prices[i + 1]-prices[i];
            }
        }
        return max;
    }

    //分发饼干
    public int findContentChildren(int[] g, int[] s) {
        if (g.length <= 0 || s.length <= 0) return 0;
        int i = 0;
        int j = 0;
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                count++;
            }
            j++;
        }
        return count;
    }
    //岛屿数量-广度优先
    public int numIslands(char[][] grid) {
        if (grid == null && grid.length == 0) return 0;
        int count=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    count++;
                    bfs(grid,i,j);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        int row = grid.length;
        int column = grid[0].length;
        Queue<int[]> queue=new LinkedList();
        queue.add(new int[]{i,j});
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            int i1 = temp[0];
            int j1 = temp[1];
            if (i1<0||j1<0||i1>=row||j1>=column||grid[i1][j1]=='0')continue;
            grid[i1][j1]='0';
            queue.add(new int[]{i1-1,j1});
            queue.add(new int[]{i1+1,j1});
            queue.add(new int[]{i1,j1-1});
            queue.add(new int[]{i1,j1+1});
        }

    }

    //岛屿数量-深度优先
    public int numIslands2(char[][] grid) {
        if (grid == null && grid.length == 0) return 0;
        int count=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    count++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int row = grid.length;
        int column = grid[0].length;
        if (i<0||j<0||i>=row||j>=column||grid[i][j]=='0'){
            return;
        }
        grid[i][j]='0';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }

    //搜索旋转排序数组
    public int search(int[] nums, int target) {
        if (nums.length == 0 || nums == null) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left<=right){
            int middle=(right-left)/2+left;
            if (nums[middle]==target)return middle;
            //左边单调递增
            if (nums[0]<=nums[middle]){
                if (nums[0]<=target&&target<nums[middle]){
                    right=middle-1;
                }else {
                    left=middle+1;
                }
            }
            //右边单调递增
            else {
                if (nums[middle]<target&&target<=nums[nums.length-1]){
                    left=middle+1;
                }
                else {
                    right=middle-1;
                }
            }
        }
        return -1;
    }

    //寻找旋转排序数组中的最小值-排序
    public int findMin2(int[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }

    //寻找旋转排序数组中的最小值-二分查找
    public int findMin(int[] nums) {
        if (nums.length == 0 || nums == null) return -1;
        if (nums.length == 1) return nums[0];
        if (nums[nums.length - 1] > nums[0]) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle + 1] < nums[middle]) {
                return nums[middle + 1];
            }
            if (nums[middle - 1] > nums[middle]) {
                return nums[middle];
            }
            if (nums[0] < nums[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }
    //跳跃游戏-贪心
    public boolean canJump2(int[] nums) {
        int maxJump=0;
        for (int i = 0; i < nums.length; i++) {
            if (i>maxJump){
                return false;
            }
            maxJump=Math.max(maxJump,i+nums[i]);
        }
        return true;
    }
    //跳跃游戏-贪心-从后
    public boolean canJump(int[] nums) {
        int last=nums.length-1;
        for (int i = nums.length-2; i >=0; i--) {
            if (i+nums[i]>=last){
                last=i;
            }

        }
        return last==0;
    }
}
