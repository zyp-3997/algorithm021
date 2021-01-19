package GeekUniversity.week07;

import java.util.*;

/**
 * @author 朱雨鹏
 * @create 2021-01-18 0:05
 */
public class HomeWork {
    //爬楼梯-dp方式
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n < 3) {
            return n;
        }
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //爬楼梯-滚动数组
    public int climbStairs2(int n) {
        int first = 1;
        int second = 1;
        int third = 2;
        for (int i = 0; i < n; i++) {
            first = second;
            second = third;
            third = first + second;
        }
        return third;
    }

    //爬楼梯：备忘录+递归
    public int climbStairs3(int n) {
        int[] array = new int[n + 1];
        return dfs(n, array);
    }

    private int dfs(int n, int[] array) {
        if (n < 3) {
            return n;
        }
        if (array[n] != 0) return array[n];

        array[n] = dfs(n - 1, array) + dfs(n - 2, array);
        return array[n];
    }

    //实现Trie（前缀树）
    public class Trie {

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                if (!node.containKey(key)) {
                    node.put(key, new TrieNode());
                }
                node = node.get(key);
            }
            node.setEnd();
        }

        public TrieNode searchPre(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                if (!node.containKey(key)) return null;
                node = node.get(key);
            }
            return node;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = searchPre(word);
            return node != null && node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = searchPre(prefix);
            return node != null;
        }
    }

    class TrieNode {
        private TrieNode[] trieNodes;
        private int n = 26;
        private boolean isEnd;

        public TrieNode() {
            trieNodes = new TrieNode[n];
        }

        public void put(char key, TrieNode node) {
            trieNodes[key - 'a'] = node;
        }

        public TrieNode get(char key) {
            return trieNodes[key - 'a'];
        }

        public boolean containKey(char key) {
            return trieNodes[key - 'a'] != null;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }


    //n皇后
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) return result;
        int[] queen = new int[n];
        Arrays.fill(queen, -1);
        Set<Integer> colunm = new HashSet<>();
        Set<Integer> pie = new HashSet<>();
        Set<Integer> na = new HashSet<>();
        dfs(0, n, queen, result, colunm, pie, na);
        return result;
    }

    private void dfs(int row, int n, int[] queen, List<List<String>> result, Set<Integer> colunm, Set<Integer> pie, Set<Integer> na) {
        if (row == n) {
            List<String> list = generate(queen, n);
            result.add(list);
        } else {
            for (int i = 0; i < n; i++) {
                if (colunm.contains(i)) continue;
                if (pie.contains(row + i)) continue;
                if (na.contains(row - i)) continue;
                queen[row] = i;
                colunm.add(i);
                pie.add(row + i);
                na.add(row - i);
                dfs(row + 1, n, queen, result, colunm, pie, na);
                queen[row] = -1;
                colunm.remove(i);
                pie.remove(row + i);
                na.remove(row - i);
            }
        }
    }

    private List<String> generate(int[] queen, int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queen[i]] = 'Q';
            list.add(new String(row));
        }
        return list;
    }

    //括号生成
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(n, 0, 0, "", result);
        return result;
    }

    private void dfs(int n, int left, int right, String s, List<String> result) {
        if (left == n && right == n) {
            result.add(s);
            return;
        }
        if (left < n) {
            dfs(n, left + 1, right, s + "(", result);
        }
        if (right < left) {
            dfs(n, left, right + 1, s + ")", result);
        }
    }

    //岛屿数量-深度优先
    public int numIslands2(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    //岛屿数量-广度优先
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> list = new LinkedList<>();
        list.add(new int[]{i, j});
        while (!list.isEmpty()) {
            int[] cur = list.remove();
            i = cur[0];
            j = cur[1];
            if (0 <= i && i < grid.length && 0 <= j && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                list.add(new int[]{i + 1, j});
                list.add(new int[]{i - 1, j});
                list.add(new int[]{i, j + 1});
                list.add(new int[]{i, j - 1});
            }
        }
    }

    //被围绕的区域
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        int row = board.length;
        int col = board[0].length;
        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') dfs(0, j, board, row, col);
            if (board[row - 1][j] == 'O') dfs(row - 1, j, board, row, col);
        }

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') dfs(i, 0, board, row, col);
            if (board[i][col - 1] == 'O') dfs(i, col - 1, board, row, col);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'B') board[i][j] = 'O';
            }
        }

    }

    private void dfs(int i, int j, char[][] board, int row, int col) {
        board[i][j] = 'B';
        for (int[] dir : dirs) {
            int tmp_i = dir[0] + i;
            int tmp_j = dir[1] + j;
            if (tmp_i < 0 || tmp_i >= row || tmp_j < 0 || tmp_j >= col || board[tmp_i][tmp_j] != 'O') continue;
            dfs(tmp_i, tmp_j, board, row, col);
        }
    }

    //解数独
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }

}
