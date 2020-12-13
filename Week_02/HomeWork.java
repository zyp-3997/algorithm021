package GeekUniversity.week02;

import GeekUniversity.Node;
import GeekUniversity.TreeNode;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.awt.font.NumericShaper;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

/**
 * @author 朱雨鹏
 * @create 2020-12-06 23:46
 */
public class HomeWork {

    //简单
    //有效的字母异位词-方法1-映射哈希
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        for (int i = 0; i < chart.length; i++) {
            map.put(chart[i], map.getOrDefault(chart[i], 0) - 1);
            if (map.get(chart[i]) < 0) return false;
        }
        return true;
    }

    //有效的字母异位词-方法2-映射数组
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] arrays = new int[26];
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            arrays[chars[i] - 'a']++;
        }
        for (int i = 0; i < chart.length; i++) {
            arrays[chart[i] - 'a']--;
            if (arrays[chart[i] - 'a'] < 0) return false;
        }
        return true;
    }

    //有效的字母异位词-方法3-排序+比较
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chart);
        return Arrays.equals(chars, chart);
    }


    //两数之和-方法1-排序+双指针
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{};
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        Arrays.sort(copy);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            while (i < j && copy[i] + copy[j] < target) {
                i++;
            }
            while (i < j && copy[i] + copy[j] > target) {
                j--;
            }
            if (copy[i] + copy[j] == target) {
                break;
            }
        }
        int[] result = new int[2];
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == copy[i]) {
                result[0] = k;
                break;
            }
        }
        for (int k = 0; k < nums.length; k++) {
            if (result[0] != k && nums[k] == copy[j]) {
                result[1] = k;
            }
        }
        return result;
    }

    //两数之和-方法2-暴力
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return nums;
    }

    //两数之和-方法3-哈希
    public int[] twoSum3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{};
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    //N叉树的前序遍历-方法1-递归
    ArrayList<Integer> results = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) return results;
        results.add(root.val);
        for (Node child : root.children) {
            preorder(child);
        }
        return results;
    }

    //N叉树的前序遍历-方法2-迭代
    public List<Integer> preorder2(Node root) {
        if (root == null) return results;
        Deque stack = new LinkedList();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node temp = (Node) stack.pop();
            results.add(temp.val);
            List<Node> children = temp.children;
            Collections.reverse(children);
            for (Node child : children) {
                stack.push(child);
            }
        }
        return results;
    }

    //中等
    //二叉树的前序遍历-方法1-递归
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return result;
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return result;
    }

    //二叉树的前序遍历-方法2-迭代
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        while (root == null) return result;
        Deque stack = new LinkedList();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = (TreeNode) stack.pop();
            result.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return result;
    }

    //二叉树的中序遍历-方法1-递归
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return res;
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }

    //二叉树的中序遍历-方法2-迭代
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque stack = new LinkedList();
        TreeNode temp = root;
        while (!stack.isEmpty() || temp != null) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            TreeNode node = (TreeNode) stack.pop();
            result.add(node.val);
            if (node.right != null) {
                temp = node.right;
            }
        }
        return result;
    }

    //字母异位词分组-方法1-数组+哈希映射实现
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length < 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap();
        int[] array = new int[26];
        for (String str : strs) {
            Arrays.fill(array, 0);
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                array[chars[i] - 'a']++;
            }
            String s = "";
            for (int i = 0; i < array.length; i++) {
                s = s + '#' + array[i];
            }
            if (!map.containsKey(s)) map.put(s, new ArrayList<>());
            map.get(s).add(str);
        }
        return new ArrayList<>(map.values());
    }

    //字母异位词分组-方法2-排序+哈希映射实现
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length < 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    //前 K 个高频元素-方法1-哈希+优先队列
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < value) {
                    queue.poll();
                    queue.add(new int[]{key, value});
                }
            } else {
                queue.add(new int[]{key, value});
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll()[0];
        }
        return result;
    }

    //二叉树层序遍历-方法1-递归
    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return list;
        level(root, 0);
        return list;
    }

    private void level(Node root, int i) {
        if (list.size() <= i) {
            list.add(new ArrayList<>());
        }
        list.get(i).add(root.val);
        for (Node child : root.children) {
            level(child, i + 1);
        }
    }

    //二叉树层序遍历-方法2-迭代
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                temp.add(node.val);
                queue.addAll(node.children);
            }
            result.add(temp);
        }
        return result;
    }

    //丑数
    private int[] uglyNumber = {2, 3, 5};

    public int nthUglyNumber(int n) {
        //创建小根堆，每次出堆的都是最小值
        Queue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        //记录出堆的个数，出堆的元素完全按照从小到大排序
        int count = 0;
        while (!queue.isEmpty()) {
            long cut = queue.poll();

            //如果出堆的个数>=n,当前cut就是第n个丑数
            if (++count >= n) {
                return (int) cut;
            }
            for (int num : uglyNumber) {
                //排除重复的数字
                if (!queue.contains(num * cut)) {
                    queue.add(num * cut);
                }
            }
        }
        return -1;
    }
}
