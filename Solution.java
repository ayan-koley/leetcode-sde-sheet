package Solution;
import java.util.*;
public class Solution {
    public static boolean ways(int[] nums, int i, int j) {
        boolean flag = true;

        for (; i < j; i++) {
            if (nums[i] == 0) {
                nums[i] = 1;
            } else {
                nums[i] = 0;
                flag = false;
            }
        }
        return flag;
    }

    public static int totalWays(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i <= n - k; i++) {
            if (nums[i] == 0) {
                if (!ways(nums, i, i + k)) {
                    count++;
                } else {
                    count++;
                    i = i + k - 1;
                }
            }
        }
        if (!check(nums)) {
            return -1;
        }
        return count;
    }

    public static boolean check(int[] nums) {
        boolean flag = true;

        for (int val : nums) {
            if (val == 0) {
                return false;
            }
        }
        return true;
    }
    // ================================================= 3192 ===============================================
    public static void flip(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                for (int j = i; j < n; j++) {
                    nums[j] = nums[j] ^ 1;
                }
                count++;
            }
        }
        System.out.println(count);
    }
    // <--------------------- Optimal Approach ---------------------->
    public static int flipOptimal(int[] nums) {
        int count = 0;
        int n = nums.length;
        int flip;
        for (int i = 0; i < n; i++) {
            flip = (nums[i] + count) % 2;
            if (flip == 0) {
                count++;
            }
        }
        return count;
    }
    // =================================================== 995 ======================================
    public static int kBitFlip(int[] nums, int k) {
        int n = nums.length;
        Queue<Integer> q = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cnt = q.size();
            if (nums[i] == 0 && cnt % 2 == 0) {
                ans++;
                q.add(i + k - 1);
            }
            if (nums[i] == 1 && cnt % 2 == 1) {
                ans++;
                q.add(i + k - 2);
            }
            if (!q.isEmpty() && i >= q.peek()) {
                q.poll();
            }
        }
        if (q.isEmpty()) {
            return ans;
        }
        return -1;
    }
    // ====================================== 73 =======================================
    public static int[] zeroxImage(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        int[][] help = new int[n][m];
        copy(help, nums);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums[i][j] == 0) {
                    fillZero(nums, i, j, n, m, help);
                }
            }
        }
        copy(nums, help);
        return new int[] {};
    }

    public static void fillZero(int[][] nums, int i, int j, int n, int m, int[][]help) {
        for (int l = i; l < n; l++) {
            help[l][j] = 0;
        }
        for (int l = i; l >= 0; l--) {
            help[l][j] = 0;
        }

        for (int l = j; l < m; l++) {
            help[i][l] = 0;
        }
        for (int l = j; l >= 0; l--) {
            help[i][l] = 0;
        }
    }

    public static void copy(int[][] nums, int[][] help) {
        int n = nums.length;
        int m = nums[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = help[i][j];
            }
        }
    }
    // ============================================== 1038 ==============================================
 

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    // use for inorder traversal and store values in inorderTraversal ArrayList
    public static void inorder(TreeNode root, List<Integer> inorderTraversal) {
        if (root == null) {
            return;
        }
        inorder(root.left, inorderTraversal);
        inorderTraversal.add(root.val);
        inorder(root.right, inorderTraversal);
    }
    // main operation that replace value 
    private static void replaceValue(TreeNode root, List<Integer> inorderTraversal) {
        if (root == null) {
            return;
        }

        // change first left side value
        replaceValue(root.left, inorderTraversal);
        // change right side value
        replaceValue(root.right, inorderTraversal);

        int nodeSum = 0;
        for (int i : inorderTraversal) {
            if (i > root.val) {
                nodeSum += i;
            } else {
                break;
            }
        }
        root.val += nodeSum;
    }

    // Create a TreeNode BST
    public TreeNode insertLevelOrder(Integer[] arr, TreeNode root, int i) {
        // Base case for recursion
        if (i < arr.length) {
            TreeNode temp = null;
            if (arr[i] != null) {
                temp = new TreeNode(arr[i]);
            }
            
            root = temp;
            
            // insert left child
            if (temp != null) {
                root.left = insertLevelOrder(arr, root.left, 2 * i + 1);
                root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
            }
        }
        return root;
    }

    public TreeNode BstToGst(TreeNode root) {
        List<Integer> inorderTraversal = new ArrayList<>();
        inorder(root, inorderTraversal);

        Collections.reverse(inorderTraversal);

        replaceValue(root, inorderTraversal);

        return root;
    }
    // inorder traversal
    public void inorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left);
        System.out.print(root.val + " ");
        inorderTraverse(root.right);
    }


    // ====================================================== 1052 ======================================================
    public int grumpyOwner(int[] customer, int[] grumpy, int minutes) {
        int n = customer.length;
        int unrealizedCustomer = 0;
         // add first mintues unsatisfied customer
        for (int i = 0; i < minutes; i++) {
            unrealizedCustomer += customer[i] * grumpy[i];
        }
        int maxUnrealizedCustomer = unrealizedCustomer;
        // minutes to lenghth of the array
        for (int i = minutes; i < n; i++) {
            unrealizedCustomer += customer[i] * grumpy[i];
            unrealizedCustomer -= customer[i - minutes] * grumpy[i - minutes];

            maxUnrealizedCustomer = Math.max(maxUnrealizedCustomer, unrealizedCustomer);
        }
        // for special texhnique the unrealized customer are satisfied
        int totStaisfiedCustomer = maxUnrealizedCustomer;

        for (int i = 0; i < n; i++) {
            totStaisfiedCustomer += customer[i] * (1 - grumpy[i]);
        }
        return totStaisfiedCustomer;
    }
    // ================================================== 36 ==================================================
    public boolean isvalidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                if (board[i][j] != '.') {
                    if (!seen.add(number + " is row " + i) ||
                            !seen.add(number + " is column " + j) ||
                            !seen.add(number + " is block " + ((i / 3) * 3) + "-" + ((j / 3) * 3))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    // ================================================== 1382 ==================================================
    public static TreeNode balanceBST(TreeNode root) {
        List<Integer> inorderAL = new ArrayList<>();
        inorder(root, inorderAL);
        
        // Create TreeNode and return
        Solution solution = new Solution();
        return solution.createTree(inorderAL, 0, inorderAL.size()-1);
    }

    private TreeNode createTree(List<Integer> inorderAL, int si, int ei) {
        if (si > ei) {
            return null;
        }

        int mid = (si + ei) / 2;

        TreeNode leftSubtree = createTree(inorderAL, si, mid - 1);
        TreeNode rightSubtree = createTree(inorderAL, mid + 1, ei);

        TreeNode node = new TreeNode(inorderAL.get(mid), leftSubtree, rightSubtree);

        return node;
    }

    // ================================================== 56 ==================================================
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {

            int currVal = intervals[i][0];
            int prevLast = list.get(list.size() - 1)[1];
            
            if (currVal <= prevLast) {
                
                list.get(list.size() - 1)[1] =
                    Math.max(prevLast, intervals[i][1]);

            } else {

                list.add(intervals[i]);
            }
        }
        int[][] ans = new int[list.size()][2];
        list.toArray(ans);
        return ans;
    }
    // ================================================== 57 ==================================================
    public int[][] mergeII(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for(int[] interval : intervals) {
            if(newInterval == null || interval[1] < newInterval[0]) res.add(interval);
            else if(interval[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(interval);
                newInterval = null;
            }   else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        if(newInterval != null) res.add(newInterval);
        return res.toArray(new int[res.size()][]);

    }
    // ================================================== 1791 ==================================================
    public int findCenter(int[][] edges) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(edges[0][0], map.getOrDefault(map, 0) + 1);
        map.put(edges[0][1], map.getOrDefault(map, 0) + 1);
        for (int i = 1; i < edges.length; i++) {
            int rowVal = edges[i][0];
            int colVal = edges[i][1];
            if (map.containsKey(rowVal)) {
                map.put(rowVal, map.get(rowVal) + 1);
            }
            if (map.containsKey(colVal)) {
                map.put(colVal, map.get(colVal) + 1);
            }
        }
        if (map.get(edges[0][0]) > map.get(edges[0][1])) {
            return edges[0][0];
        } else {
            return edges[0][1];
        }
    }


    public static void print(int[] nums) {
        for (int val : nums) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
    
    public static void print2(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] nums = { { 1, 2 }, { 5, 1 }, { 1, 3 }, { 1, 4 } };
        Solution solution = new Solution();
        System.out.println(solution.findCenter(nums));
    }
}