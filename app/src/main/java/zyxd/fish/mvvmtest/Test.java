package zyxd.fish.mvvmtest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf('中'));
    }

    /**
     * 字符串z形变换
     * @param s
     * @param rows
     * @return
     */
    public static String convertZ(String s,int rows){
        if (rows == 1){
            return s;
        }
        int len = Math.min(s.length(), rows);
        String[] curRow = new String[len];
        for (int i = 0;i < len;i++)
            curRow[i] = "";
        int loc=0;
        boolean down = false;
        for (int i = 0;i < s.length();i++){
            curRow[loc] += s.charAt(i);
            if (loc == 0 || loc == rows-1){
                down = !down;
            }
            loc += down?1:-1;
        }
        String ans = "";
        for (String str:curRow){
            ans += str;
        }
        return ans;
    }

    /**
     * 字符串中最大回文子串问题
     * @param s
     * @return
     */
    public static String longestPalindrome(String s){
        if (s == null || s.length() < 1) return "";
        int start = 0,end = 0;
        for (int i = 0;i < s.length();i++){
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);
            if (len > end-start){
                start = i - (len - 1) / 2;
                end = i + len /2;
            }
        }
        return s.substring(start,end+1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left,R = right;
        while (L >=0 && R < s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R - L -1;
    }

    /**
     * 查找两个有序数组的中位数
     * @param numbers1
     * @param numbers2
     * @return
     */
    public static float getTwoArraysCenterNumber(int[] numbers1,int[] numbers2){
        int n = numbers1.length;
        int m = numbers2.length;
        int left = (n+m+1)/2;
        int right = (n+m+2)/2;
        return (float) ((getKth(numbers1,0,n-1,numbers2,0,m-1,left)
                + getKth(numbers1,0,n-1,numbers2,0,m-1,right))*0.5);
    }

    private static int getKth(int[] numbers1,int start1,int end1,int[] number2,int start2,int end2,int k){
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 > len2){
            return getKth(number2,start2,end2,numbers1,start1,end1,k);
        }
        if (len1 == 0){
            return number2[start2+k-1];
        }
        if (k == 1){
            return Math.min(numbers1[start1],number2[start2]);
        }
        int i = start1 + Math.min(len1, k / 2) -1;
        int j = start2 + Math.min(len2,k/2) -1;
        if (numbers1[i] > number2[j]){
            return getKth(numbers1,start1,end1,number2,j+1,end2,k-(j+1));
        }else {
            return getKth(numbers1,i+1,end1,number2,start2,end2,k -(i+1));
        }
    }

    /**
     * 字符串的最大子串问题
     * @param str
     * @return
     */
    public static int strMaxSubStrLength(String str){
        if (str == null){
            return 0;
        }else {
            Set<Character>sub = new HashSet<>();
            int length = str.length();
            int rk = -1,ans = 0;
            for(int i =0;i < length;i++){
                if (i != 0){
                    sub.remove(str.charAt(i-1));
                }
                while (rk+1 < length && !sub.contains(str.charAt(rk+1))){
                    sub.add(str.charAt(rk+1));
                    rk += 1;
                }
                ans = Math.max(ans,rk-i+1);
            }
            return ans;
        }
    }

    /**
     * 求数组中两数之和的下标
     * @param numbers
     * @param target
     * @return
     */
    public static int[] getTwoNumAddIndex(int[] numbers,int target){
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for(int i =0;i < numbers.length;i++){
            if (indexMap.containsKey(target - numbers[i])){
                return new int[]{indexMap.get(target-numbers[i]),i};
            }else {
                indexMap.put(numbers[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    private static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 两数之和
     * @param node1
     * @param node2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode node1,ListNode node2){
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (node1 != null || node2 != null){
            int x = (node1 != null) ? node1.val : 0;
            int y = (node2 != null) ? node2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (node1 != null) node1 = node1.next;
            if (node2 != null) node2 = node2.next;
        }
        if (carry > 0){
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}
