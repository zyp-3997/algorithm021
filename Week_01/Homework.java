package GeekUniversity.week01;

import GeekUniversity.ListNode;
import org.junit.Test;

import java.awt.font.NumericShaper;
import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author 朱雨鹏
 * @create 2020-12-05 19:19
 */
public class Homework {

    //简单题目

    //删除排序数组中的重复项-方法1-双指针
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                if (i != j) {
                    nums[i + 1] = nums[j];
                }
                i++;
            }
            j++;
        }
        return i + 1;
    }

    //删除排序数组中的重复项-方法2-双指针
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                if (i != j) {
                    nums[++i] = nums[j];
                }
            }
            j++;
        }
        return i + 1;
    }
    //旋转数组-方法1-暴力
    public void rotate(int[] nums, int k) {
        for (int j = 0; j <k ; j++) {
            int temp=nums[nums.length-1];
            for (int i = nums.length-1; i>0; i--) {
                nums[i]=nums[i-1];
            }
            nums[0]=temp;
        }
    }
    //旋转数组-方法2-加数组
    public void rotate2(int[] nums, int k) {
       int array[]=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[(k+i)%nums.length]=nums[i];
        }
        System.arraycopy(array,0,nums,0,nums.length);
    }

    //合并两个有序链表-方法1-加节点
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       ListNode preHead=new ListNode(0);
       ListNode pre=preHead;
       while (l1!=null&&l2!=null){
           if (l1.val<l2.val){
               pre.next=l1;
               l1=l1.next;
               pre=pre.next;
           }
           else {
               pre.next=l2;
               l2=l2.next;
               pre=pre.next;
           }
       }
       pre.next=l1==null?l2:l1;
       return preHead;
    }
    //合并两个有序链表-方法2-递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1==null)return l2;
        else if (l2==null)return l1;
        else if (l1.val<l2.val){
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }
        else {
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
    //合并两个有序数组-方法1-新开数组
    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
     int length=m+n;
     int result[]=new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i]=nums1[i];
        }
        for (int i = m; i <result.length ; i++) {
            result[i]=nums2[i-m];
        }
        Arrays.sort(result);
        return result;
    }
    //合并两个有序数组-方法2-调用API
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
    }
    //合并两个有序数组-方法3-调用双指针
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
       int copy[]=new int[m];
       System.arraycopy(nums1,0,copy,0,m);
       int i=0;
       int j=0;
       int k=0;
       while (j<m&&k<m){
           nums1[i++]=copy[j]<nums2[k]?copy[j++]:nums2[k++];
       }
       if (j<m){
           System.arraycopy(copy,j,nums1,j+i,m+n-i-j);
       }
       if (k<m){
           System.arraycopy(nums2,k,nums1,i+j,m+n-i-j);
       }
    }
    //合并两个有序数组-方法4-调用双指针优化
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        int i=m+n-1;
        int j=m-1;
        int k=n-1;
        while (j>=0&&k>=0){
            nums1[i--]=nums1[j]<nums2[k]?nums2[k--]:nums1[j--];
        }
        System.arraycopy(nums2,0,nums1,0,k+1);
    }
    //两数之和-方法1-哈希表
    public int[] twoSum(int[] nums, int target) {
       if (nums==null||nums.length==0)return new int[]{0};
        HashMap<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{0};
    }

    //两数之和-方法2-暴力
    public int[] twoSum2(int[] nums, int target) {
        if (nums==null||nums.length==0)return new int[]{0};
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0};
    }

    //两数之和-方法3-排序+双指针
    public int[] twoSum3(int[] nums, int target) {
        int[] copy = new int[nums.length];
        int[] result = new int[2];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        Arrays.sort(copy);
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int sum = copy[i] + copy[j];
            if (sum == target) break;
            else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == copy[i]) {
                result[0] = k;
                break;
            }
        }
        for (int k = 0; k < nums.length; k++) {
            if (k != result[0] && nums[k] == copy[j]) {
                result[1] = k;
            }
        }
        return result;
    }


    //移动零-方法1-双指针
    public void moveZeroes(int[] nums) {
        int i=0;
        int j=0;
        while (j<nums.length){
            if (nums[j]!=0){
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
                i++;
            }
            j++;
        }
    }

    //移动零-方法2-先赋值，后补零
    public void moveZeroes2(int[] nums) {
       int j=0;
        for (int i = 0; i < nums.length; i++) {
           if (nums[i]!=0){
               nums[j++]=nums[i];
           }
        }
        for (int i = j; i <nums.length ; i++) {
            nums[i]=0;
        }
    }

    //加一方法1-数学法
    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >=0;i--) {
            digits[i]++;
            digits[i]=digits[i]%10;
            if (digits[i]!=0)return digits;
        }
        int[] array=new int[digits.length+1];
        System.arraycopy(digits,0,array,1,digits.length);
        array[0]=1;
        return array;
    }

    //加一方法1-数学法
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length-1; i >=0;i--) {
            digits[i]++;
            digits[i]=digits[i]%10;
            if (digits[i]!=0)return digits;
        }
       digits=new int[digits.length+1];
        digits[0]=1;
        return digits;
    }

    @Test
    public void HomeworkTest(){
    }

    //困难

    //接雨水-方法-暴力法
    public int trap(int[] height) {
        int result=0;

        for (int i = 1; i < height.length-1; i++) {
            int left=0;
            int right=0;
            for (int j = i; j >=0; j--) {
                left=Math.max(height[j],left);
            }
            for (int j = i; j <height.length; j++) {
                right=Math.max(height[j],right);
            }
            result=result+Math.min(left,right)-height[i];
        }
        return result;
    }

}
//中等题目
class MyCircularDeque {
private int []val;
private int capacity;
private int front;
private int rear;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.capacity=k+1;
        val=new int[capacity];
        front=0;
        rear=0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull())return false;
        front=(front+capacity-1)%capacity;
        val[front]=value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
    if (isFull())return false;
    val[rear]=value;
    rear=(rear+1)%capacity;
    return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty())return false;
        front=(front+1)%capacity;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty())return false;
        rear=(rear+capacity-1)%capacity;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty())return -1;
        return val[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty())return -1;
        return val[(rear+capacity-1)%capacity];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
    return front==rear;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
   return (rear+1)%capacity==front;
    }
}


