package com.dirge.test;


import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * 这里是力扣的类型简单的题目
 */
public class LeetCode_Easy {
    public static void main(String[] args) {

    }

    /**
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    public static int exam_13(String s){
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        int sum = 0;
        for(int i=0;i<s.length();i++){
            String a1 = s.substring(i,i+1);
            String a2 = null;
            if(i!=s.length()-1){
                a2 = s.substring(i+1,i+2);
            }
            if(map.get(a2)!=null && map.get(a1)<map.get(a2)){
                sum+=map.get(a2)-map.get(a1);
                i++;
            }else {
                sum+=map.get(a1);
            }
        }
        return sum;
    }

    /**
     *
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * 输入：s = "{[]}"
     * 输出：true
     */
    public static boolean exam_20(String s){
        Map<String,String> map = new HashMap<>();
        map.put("{","}");
        map.put("[","]");
        map.put("(",")");
        return false;
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
     * 检查它是否是镜像对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * 输入:        1
     *            / \
     *           2   2
     *          / \ / \
     *         3  4 4  3
     * 输出: true
     */
    public static boolean exam_101(TreeNode p){
        if (p == null) {
            return true;
        }
        return exam_101_1(p.left,p.right);
    }

    public static boolean exam_101_1(TreeNode left,TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null || left.val != right.val){
            return false;
        }
        return  exam_101_1(left.left,right.right) && exam_101_1(left.right,right.left);
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * 返回3
     */
    public static int exam_104(TreeNode root){
        return root == null ? 0 : Math.max(exam_104(root.left), exam_104(root.right)) + 1;
    }

    /**
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其自底向上的层序遍历为：
     *
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     */
    public static List<List<Integer>> exam_107(TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> list1 = new ArrayList<>();
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list1.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            list.add(0,list1);
        }
        return list;
    }


    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * 给定有序数组: [-10,-3,0,5,9],
     *一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * @return
     */
    public static TreeNode exam_108(int[] nums){
        TreeNode treeNode = new TreeNode();

        return treeNode;
    }


    /**
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     *
     * 对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。
     * 如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
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
     * 对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果X = 1231，那么其数组形式为[1,2,3,1]。
     *
     * 给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
     *
     * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
     * 输出：[1,0,0,0,0,0,0,0,0,0,0]
     * 解释：9999999999 + 1 = 10000000000
     *
     */
    public List<Integer> exam_989(int[] A, int K){
        int KK = K;
        int temp =0;
        while (KK!=0){
            KK=KK/10;
            temp++;
        }
        int[] B = new int[temp];
        for(int i=0;i<temp;i++){
            B[temp-i-1]=K%10;
            K=K/10;
        }
        List<Integer> list = new ArrayList<>();
        int m = A.length;
        int tt=0;
        while (m!=0 || temp!=0){
            if(temp!=0&&m!=0){
                int b = A[--m]+B[--temp]+tt;
                if(b>=10){
                    b=b%10;
                    tt=1;
                }else {
                    tt=0;
                }
                list.add(0,b);
            }
            if(temp!=0&&m==0){
                int b=B[--temp]+tt;
                if(b>=10){
                    b=b%10;
                    tt=1;
                }else {
                    tt=0;
                }
                list.add(0,b);
            }
            if(temp==0&&m!=0){
                int b=A[--m]+tt;
                if(b>=10){
                    b=b%10;
                    tt=1;
                }else {
                    tt=0;
                }
                list.add(0,b);
            }
        }
        if(tt==1){
            list.add(0,1);
        }
        return list;
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

    /**
     * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     *
     * 输入：[2,7,4,1,8,1]
     * 输出：1
     * 解释：
     * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
     * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
     * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
     * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
     *
     */
    public static int exam_1046(int[] stones){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<stones.length;i++){
            list.add(stones[i]);
        }
        while (list.size()>1){
            Collections.sort(list);
            int a = list.get(list.size()-1);
            int b = list.get(list.size()-2);
            if(a==b){
                list.remove(list.size()-1);
                list.remove(list.size()-1);
            } else {
                list.remove(list.size()-1);
                list.remove(list.size()-1);
                list.add(a-b);
            }
        }
        if(list.size()==0){
            return 0;
        }else {
            return list.get(0);
        }
    }
}
