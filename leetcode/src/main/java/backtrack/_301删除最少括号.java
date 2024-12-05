package backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _301删除最少括号 {
    public static void main(String[] args) {
        Solution301 solution = new Solution301();
    }
}
class Solution301 {
    public List<String> removeInvalidParentheses(String s) {
        int rr = 0;
        int left = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            }
            if (s.charAt(i) == ')') {
                if (left == 0) {
                    rr++;
                } else {
                    left--;
                }
            }
        }
        Set<String> res = new HashSet<>();
        backtrack(res, s, rr, left, 0);
        return new ArrayList<>(res);
    }

    void backtrack(Set<String> res, String cur, int r, int l, int start) {
        if (r == 0 && l == 0) {
            if (isRight(cur)) {
                res.add(cur);
            }
            return;
        }
        for (int i = start; i < cur.length(); i++) {
            if (cur.charAt(i) != '(' && cur.charAt(i) != ')') continue;
            // length 5 ; i 2
            if (r+l>cur.length()-i) break;
            if (cur.charAt(i) == '(' && l>0) {
                String temp = cur.substring(0, i) + cur.substring(i+1);
                backtrack(res, temp, r, l-1, i);
            } else if (cur.charAt(i) == ')' && r>0) {
                String temp = cur.substring(0, i) + cur.substring(i+1);
                backtrack(res, temp, r-1, l, i);
            }
        }

    }

    boolean isRight(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (--count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }
}


class Solution {
    public static void main(String[] args) {
        new Solution().removeInvalidParentheses(")))((()");

    }
    public List<String> removeInvalidParentheses(String s) {
        int rr = 0;
        int left = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            }
            if (s.charAt(i) == ')') {
                if (left == 0) {
                    rr++;
                } else {
                    left--;
                }
            }
        }
        List<String> res = new ArrayList<>();
        backtrack(res, s, rr, left, 0);
        return new ArrayList<String>(res);
    }

    void backtrack(List<String> res, String cur, int r, int l, int start) {
        if (r == 0 && l == 0) {
            if (isRight(cur)) {
                res.add(cur);
            }
            return;
        }
        for (int i = start; i < cur.length(); i++) {
            if (cur.charAt(i) != '(' && cur.charAt(i) != ')') continue;
            // length 5 ; i 2
            if (r+l>cur.length()-i) break;
            if (cur.charAt(i) == '(' && l>0) {
                while (cur.charAt(i) == '('  ) i++;
                String temp = cur.substring(0, i) + cur.substring(i+1);
                backtrack(res, temp, r, l-1, i);
            } else if (cur.charAt(i) == ')' && r>0) {
                String temp = cur.substring(0, i) + cur.substring(i+1);
                backtrack(res, temp, r-1, l, i);
            }
        }

    }

    boolean isRight(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (--count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }
}

