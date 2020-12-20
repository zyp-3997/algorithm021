package GeekUniversity.week03;

import GeekUniversity.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @author 朱雨鹏
 * @create 2020-12-20 10:46
 */
public class HomeWork {

    //二叉树的最近公共祖先-方法1-哈希
    HashSet<Integer> set = new HashSet();
    HashMap<Integer, TreeNode> map = new HashMap();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            set.add(p.val);
            p = map.get(p.val);
        }
        while (q != null) {
            if (set.contains(q.val)) {
                return q;
            }
            q = map.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            map.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            dfs(root.right);
        }
    }

    //二叉树的最近公共祖先-方法2-递归
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }


    //从前序与中序遍历序列构造二叉树
    Map<Integer, Integer> buildMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) return null;
        for (int i = 0; i < inorder.length; i++) {
            buildMap.put(inorder[i], i);
        }
        return bt(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode bt(int[] preorder, int preLeft, int preRight, int[] inorder, int inoLeft, int inoRight) {
        if (preLeft > preRight || inoLeft > inoRight) return null;
        int head = preorder[preLeft];
        TreeNode treeNode = new TreeNode(head);
        int middle = buildMap.get(head);
        treeNode.left = bt(preorder, preLeft + 1, preLeft + middle - inoLeft, inorder, inoLeft, middle);
        treeNode.right = bt(preorder, preLeft + middle - inoLeft + 1, preRight, inorder, middle + 1, inoRight);
        return treeNode;
    }

    //电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        Map<Character, String> nMap = new HashMap<>();
        nMap.put('2', "abc");
        nMap.put('3', "def");
        nMap.put('4', "ghi");
        nMap.put('5', "jkl");
        nMap.put('6', "mno");
        nMap.put('7', "pqrs");
        nMap.put('8', "tuv");
        nMap.put('9', "wxyz");
        List<String> result = new ArrayList<>();
        reverse("", 0, digits, result, nMap);
        return result;
    }

    private void reverse(String s, int i, String digits, List<String> result, Map<Character, String> nMap) {
        if (i == digits.length()) {
            result.add(s);
        }
        char key = digits.charAt(i);
        String string = nMap.get(key);
        for (int i1 = 0; i1 < string.length(); i1++) {
            s = s + string.charAt(i1);
            reverse(s, i + 1, digits, result, nMap);
        }
    }

    //组合
    List<List<Integer>>res=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
   if (k<=0||n<k)return res;
   Deque queue=new ArrayDeque();
   dfs(n,k,1,queue);
   return res;
    }

    private void dfs(int n, int k, int start, Deque queue) {
        if (queue.size()==k){
            res.add(new ArrayList<>(queue));
            return;
        }
        for (int i = start; i <= n; i++) {
            queue.addLast(i);
            dfs(n,k,i+1,queue);
            queue.removeLast();
        }
    }
    //全排列（字节跳动在半年内面试常考）
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
         if (nums.length==0)return res;
         Deque queue=new ArrayDeque();
         boolean[]used=new boolean[nums.length];
        dfs( nums, nums.length,0,queue, res,used);
        return res;
    }

    private void dfs(int[] nums, int length, int depth, Deque queue, List<List<Integer>> res,boolean[]used) {
        if (depth==length){
            res.add(new ArrayList<>(queue));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                queue.push(nums[i]);
                used[i]=true;
                dfs(nums,length,depth+1,queue,res,used);
                used[i]=false;
                queue.pop();
            }
        }
    }

    //全排列 II
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; ++i) {
            if (used[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, len, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }


}
