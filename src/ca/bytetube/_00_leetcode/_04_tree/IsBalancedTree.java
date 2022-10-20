package ca.bytetube._00_leetcode._04_tree;

public class IsBalancedTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(9,null,null);
        root.left = new TreeNode(6,null,null);
        root.right = new TreeNode(15,null,null);
        root.left.left = new TreeNode(4,null,null);
        root.left.right = new TreeNode(8,null,null);
        root.right.left = new TreeNode(14,null,null);
        root.right.right = new TreeNode(16,null,null);
        root.right.left.left = new TreeNode(12,null,null);
        root.right.left.left.right = new TreeNode(13,null,null);
        System.out.println(isBalanced(root));
    }

    //domain, pojo (Student,Course) ---> table
    //dto: data transfer obj
    public static class ReturnData {
        public boolean isB;
        public int height;


        public ReturnData(boolean isB, int height) {
            this.isB = isB;
            this.height = height;
        }
    }


    public static boolean isBalanced(TreeNode root) {
        return isB(root).isB;
    }

    public static ReturnData isB(TreeNode root) {
        if (root == null) return new ReturnData(true, 0);
        //recursion (divide)
        ReturnData leftData = isB(root.left);
        if (!leftData.isB) return new ReturnData(false, 0);

        ReturnData rightData = isB(root.right);
        if (!rightData.isB) return new ReturnData(false, 0);
        //左右都平，但是高度差大于1
        if (Math.abs(leftData.height - rightData.height) > 1) return new ReturnData(false, 0);

        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);
    }


    public static boolean isBalanced2(TreeNode root){
        if(root == null) return true;
        if(Math.abs(height(root.left) - height(root.right)) > 1) return false;

        return isBalanced2(root.left) && isBalanced2(root.right);

    }

    public static int height(TreeNode root){
        if(root == null) return 0;
        return (Math.max(height(root.left),height(root.right)) + 1);
    }


}
