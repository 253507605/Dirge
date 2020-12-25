package com.dirge.test;


import javax.sound.midi.Soundbank;
import java.util.*;

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
        /*ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        System.out.println(exam_83(listNode));*/
        //88
        /*int[] nums1 = {1,2,3};
        int[] nums2 = {2,5,6};
        exam_88(nums1,3,nums2,3);*/
        //100
        /*TreeNode treeNodeLeft1 = new TreeNode(2);
        TreeNode treeNodeRight1 = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(1, treeNodeLeft1, treeNodeRight1);
        TreeNode treeNodeLeft2 = new TreeNode(2);
        TreeNode treeNodeRight2 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(1, treeNodeLeft2, treeNodeRight2);
        System.out.println(exam_100(treeNode1, treeNode2));*/
        //101
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode.left = treeNode1;
        treeNode.right=treeNode2;
        TreeNode treeNode3 = null;
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = null;
        TreeNode treeNode6 = new TreeNode(3);
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;
        System.out.println(exam_101(treeNode));
        //1030
        //exam_1030(2,3,1,2);
    }

    /**
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * 输入: 8
     * 输出: 2
     * <p>
     * 2147395600
     */
    public static int exam_69(int n) {
        //第一种方法，直接使用Math函数
        //return (int)Math.sqrt(n);
        //第二种方法,牛顿迭代法
        //f(x)=x^2-n
        int x = n / 2 + 1;
        while ((long) x * x > n) {
            x = (x + n / x) / 2;
        }
        return x;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     */
    public static int exam_70(int x) {
        if (x == 1 || x == 2) {
            return x;
        }
        int[] a = new int[x];
        a[0] = 1;
        a[1] = 2;
        for (int i = 2; i < a.length; i++) {
            int j = i - 1;
            int k = i - 2;
            a[i] = a[j] + a[k];
        }
        return a[x - 2] + a[x - 3];
    }

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * <p>
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    public static ListNode exam_83(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = exam_83(head.next);
        if (head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * 输入：
     * nums1 = [3,4,5,0,0,0], m = 3    nums1.length = m + n ;
     * nums2 = [6,7,8],       n = 3    nums1.length = n ;
     * <p>
     * 输出：[1,2,2,3,5,6]
     */
    public static void exam_88(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        int[] nums = new int[m + n];
        for (int i = 0; i < m; i++) {
            nums[j++] = nums1[i];
        }
        for (int i = 0; i < n; i++) {
            if (nums2[i] != 0) {
                nums[j++] = nums2[i];
            }
        }
        Arrays.sort(nums);
    }


    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * 输入:       1         1
     * / \       / \
     * 2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * 输出: true
     */
    public static boolean exam_100(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
        }
        if (p != null && q == null || p == null && q != null) {
            return false;
        }
        boolean left = true;
        boolean right = true;
        if (p != null && q != null) {
            left = exam_100(p.left, q.left);
            right = exam_100(p.right, q.right);
        }

        return left && right;
    }

    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * 输入:       1         1
     *           / \       / \
     *         2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * 输出: true
     */
    public static boolean exam_101(TreeNode p){
        return false;
    }

    /**
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     *
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
     * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * @param g
     * @param s
     * @return
     */
    public int exam_455(int[] g,int[] s){
        int left=0;
        int right=0;
        int count =0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (right<s.length && left<g.length){
            if(s[right]>=g[left]){
                count++;
                right++;
                left++;
            } else {
                right++;
            }
        }
        return count;
    }

    /**
     * 按奇偶排序数组
     * 数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     */
    public int[] exam_922(int[] A) {
        int[] B = new int[A.length];
        int temp = -2;
        int k = -1;
        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 1) {
                if (A[i] % 2 == 1) {
                    k = k + 2;
                    B[k] = A[i];
                } else {
                    temp = temp + 2;
                    B[temp] = A[i];
                }
            } else {
                if (A[i] % 2 == 0) {
                    temp = temp + 2;
                    B[temp] = A[i];
                } else {
                    k = k + 2;
                    B[k] = A[i];
                }
            }
        }
        return B;
    }

    /**
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     *
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     *
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|
     *
     * 输入：R = 2, C = 3, r0 = 1, c0 = 2
     * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
     * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     *
     */
    public static int[][] exam_1030(int R, int C, int r0, int c0){
        int[][] ints = new int[1][R*C];
        HashMap<Integer, Map<Integer,Integer>> map = new HashMap<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int abs = Math.abs(i-r0)+Math.abs(j-c0);
                HashMap<Integer,Integer> hashMap = new HashMap<>();
                hashMap.put(i,j);
                map.put(abs,hashMap);
            }
        }
        return null;
    }
}
