package GeekUniversity.week09;

import java.util.*;

/**
 * @author 朱雨鹏
 * @create 2021-01-29 19:11
 */
public class HomeWork {
    //转换成小写字母
    public String toLowerCase(String str) {
        if (str == null) return str;
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c >= 'A' && c <= 'Z') {
                stringBuilder.append((char) (c + 32));
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    //最后一个单词的的长度
    public int lengthOfLastWord(String s) {
        int last = s.length() - 1;
        while (last >= 0 && s.charAt(last) == ' ') last--;
        if (last < 0) return 0;
        int fron = last;
        while (fron >= 0 && s.charAt(fron) != ' ') fron--;
        return last - fron;
    }

    //宝石与石头-哈希表
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        char[] chars = stones.toCharArray();
        for (char stone : chars) {
            if (set.contains(stone)) count++;
        }
        return count;
    }

    //宝石与石头-蛮力法
    public int numJewelsInStones2(String jewels, String stones) {
        int count = 0;
        for (int i = 0; i < jewels.length(); i++) {
            char str = jewels.charAt(i);
            for (int j = 0; j < stones.length(); j++) {
                if (str == stones.charAt(j)) count++;
            }
        }
        return count;
    }

    //字符串中的第一个唯一字符-哈希表
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return 0;
    }

    //最长公共前缀-方法1
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int size = strs.length;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            for (int j = 1; j < size; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return prefix.substring(0, i);
                }
            }
        }
        return prefix;
    }

    //最长公共前缀-方法2
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = common(prefix, strs[i]);
            if (prefix.length() == 0) break;
        }
        return prefix;
    }
    private String common(String prefix, String str) {
        int index = 0;
        int minLength = Math.min(prefix.length(), str.length());
        while (index < minLength && prefix.charAt(index) == str.charAt(index)) {
            index++;
        }
        return prefix.substring(0, index);
    }


    //反转字符串
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }

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

    //字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs.length == 0) return result;
        Map<String, List<String>> map = new HashMap<>();
        int[] table = new int[26];
        for (String str : strs) {
            Arrays.fill(table, 0);
            for (int i = 0; i < str.length(); i++) {
                table[str.charAt(i) - 'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                builder.append("#");
                builder.append(table[i]);
            }
            String key = new String(builder);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    //最长公共子序列
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c1 = text1.charAt(i), c2 = text2.charAt(j);
                if (c1 == c2) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[m][n];
    }

    //验证回文串-方法1
    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stringBuilder.append(Character.toLowerCase(c));
            }
        }
        int left = 0;
        int right = stringBuilder.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(stringBuilder.charAt(left)) != Character.toLowerCase(stringBuilder.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    //验证回文串-方法2
    public boolean isPalindrome2(String s) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if (Character.isLetterOrDigit(c)){
                stringBuilder.append(Character.toLowerCase(c));
            }
        }
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }

}
