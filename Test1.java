一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int missingNumber(int[] nums) {
        int res=nums.length;
        for(int i=0;i<nums.length;i++){
            res^=nums[i];
            res^=i;
        }
        return res;
    }
}

给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0) return new int[0];
        int len=nums.length-k+1;
        int[] res=new int[len];
        int tmp=0;
        int max=findIndex(nums,0,k-1);
        int i=0;
        int j=k-1;
        for(;j<nums.length;i++,j++){
            if(max<=j&&max>=i){
                if(nums[max]<=nums[j]) max=j;
                res[tmp++]=nums[max];
            }else{
                max=findIndex(nums,i,j);
                res[tmp++]=nums[max];
            }
        }
        return res;
    }
    private int findIndex(int[]nums,int left,int right){
        int max=left;
        for(int i=left+1;i<=right;i++){
            if(nums[max]<=nums[i]){
                max=i;
            }
        }
        return max;
    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0) return new int[0];
        Deque<Integer> queue=new LinkedList<>();
        int[] res=new int[nums.length-k+1];
        int index=0;
        for(int i=0;i<nums.length;i++){
            while(!queue.isEmpty()&&nums[queue.peekLast()]<=nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
            if(!queue.isEmpty()&&queue.peek()==i-k){
                queue.pollFirst();
            }
            if(i>=k-1){
                res[index++]=nums[queue.peek()];
            }
        }
        return res;
    }
}


输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        int row=matrix.length;
        if(row==0) return new int[0];
        int col=matrix[0].length;
        int[] res=new int[row*col];
        int index=0;
        int left=0,right=col-1,up=0,down=row-1;
        while(true){
            for(int i=left;i<=right;i++) res[index++]=matrix[up][i];
            if(++up>down) break;
            for(int i=up;i<=down;i++) res[index++]=matrix[i][right];
            if(--right<left) break;
            for(int i=right;i>=left;i--) res[index++]=matrix[down][i];
            if(--down<up) break;
            for(int i=down;i>=up;i--) res[index++]=matrix[i][left];
            if(++left>right) break;
        }
        return res;
    }
    

}

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int numWays(int n) {
        if(n==0) return 1;
        int f1=1;
        int f2=2;
        int f3=0;
        if(n<=2) return n;
        for(int i=3;i<=n;i++){
            f3=(f1+f2)%1000000007;
            f1=f2;
            f2=f3;
        }
        return f3;
    }
}

从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero=0;
        int tmp=0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==0){
                zero++;
            }else {
                if(nums[i]==nums[i+1]) return false;
                if(nums[i]+1!=nums[i+1]) tmp+=nums[i+1]-nums[i]-1;
            }
        }
        return zero>=tmp;
    }
}

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public String reverseWords(String s) {
        char[] c=s.toCharArray();
        for(int i=0,j=0;j<=c.length;j++){
            if(j==c.length||c[j]==' '){
                reverse(c,i,j-1);
                i=j+1;
            }
        }
        reverse(c,0,c.length-1);
        return clear(c);
    }
    private void reverse(char[] c,int left,int right){
        while(left<right){
            char tmp=c[left];
            c[left++]=c[right];
            c[right--]=tmp;
        }
    }
    private String clear(char[] c){
        int i=0;
        for(int j=0;j<c.length;j++){
            while(j<c.length&&c[j]==' ') j++;
            while(j<c.length&&c[j]!=' ') c[i++]=c[j++];
            while(j<c.length&&c[j]==' ') j++;
            if(j<c.length){
                c[i++]=' ';
                j--;
            } 
        }
        return new String(c).substring(0,i);
    }
}

