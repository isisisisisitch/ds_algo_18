package ca.bytetube._00_leetcode._04_tree;


public class TreeNode {
    int val;//4 bytes
    TreeNode left;//8 bytes
    TreeNode right;////8 bytes

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

