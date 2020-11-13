package com.dirge.test;

/**
 * 这里是力扣的类型简单的题目
 */
public class LeetCode_Easy {
    public static void main(String[] args) {
        //69
        //System.out.println(exam_69(2147395599));
        //70
        //System.out.println(exam_70(45));
        //83
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        System.out.println(exam_83(listNode));
    }

    /**
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     *  由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *  输入: 8
     *  输出: 2
     *
     *  2147395600
     */
    public static int exam_69(int n){
        //第一种方法，直接使用Math函数
        //return (int)Math.sqrt(n);
        //第二种方法,牛顿迭代法
        //f(x)=x^2-n
        int x = n/2+1;
        while ((long)x*x>n){
            x = (x+n/x)/2;
        }
       return x;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     */
    public static int exam_70(int x){
        if(x==1 || x==2){
            return x;
        }
        int[] a = new int[x];
        a[0] = 1;
        a[1] = 2;
        for(int i = 2;i<a.length;i++){
            int j = i-1;
            int k = i-2;
            a[i] = a[j]+a[k];
        }
        return a[x-2]+a[x-3];
    }

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    public static ListNode exam_83(ListNode head){
        return null;
    }

    /**
     * 按奇偶排序数组
     * 数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     */
    public int[] exam_922(int[] A){
        int[] B = new int[A.length];
        int temp=-2;
        int k = -1;
        for(int i=0;i<A.length;i++){
            if(i%2==1){
                if(A[i]%2==1){
                    k = k+2;
                    B[k] = A[i];
                } else {
                    temp = temp+2;
                    B[temp] = A[i];
                }
            } else {
                if(A[i]%2==0){
                    temp = temp+2;
                    B[temp] = A[i];
                } else {
                    k = k+2;
                    B[k] = A[i];
                }
            }
        }
        return B;
    }
}
