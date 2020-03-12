对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。

返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1)) return "";
        int len=divide(str1.length(),str2.length());
        return str1.substring(0,len);
    }
    private int divide(int a,int b){
    while(b!=0){
        int c=a%b;
        a=b;
        b=c;
    }
    return a;
}
}


把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public double[] twoSum(int n) {
        double[] res=new double[5*n+1];
        double[][] dp=new double[n+1][6*n+1];
        double tol=Math.pow(6,n);
        for(int i=1;i<=6;i++){
            dp[1][i]=1;
        }
        for(int i=2;i<=n;i++){
            for(int j=i;j<=6*n;j++){
                for(int k=1;k<=6;k++){
                    dp[i][j]+=j>=k?dp[i-1][j-k]:0;
                }
            }
        }
        for(int i=0;i<=5*n;i++){
            res[i]=dp[n][i+n]/tol;
        }
        return res;
    }
}

统计一个数字在排序数组中出现的次数。
class Solution {
    public int search(int[] nums, int target) {
        if(nums.length==0) return 0;
        int left=0;
        int right=nums.length-1;
        int mid=0;
        while(left<=right){
            mid=(left+right)>>>1;
            if(nums[mid]==target){
                break;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        if(nums[mid]!=target) return 0;
        int i=mid;
        int j=mid;
        for(;i>=0;i--){
            if(nums[i]!=target){
                break;
            }
        }
        for(;j<nums.length;j++){
            if(nums[j]!=target){
                break;
            }
        }
        return j-i-1;

    }
}

class Solution {
    public int search(int[] nums, int target) {
        if(Arrays.equals(new int[0],nums)) return 0;
        int left=0;
        int right=nums.length-1;
        while(left<right){
            int mid=(left+right)>>>1;
            if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        int i=left;
        left=0;
        right=nums.length-1;
        while(left<right){
            int mid=(left+right+1)>>>1;
            if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return nums[i]==target?left-i+1:0;
    }
}

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int minArray(int[] numbers) {
        int left=0;
        int right=numbers.length-1;
        while(left<right){
            int mid=(left+right)>>>1;
            if(numbers[mid]==numbers[right]){
                right--;
                continue;
            }
            if(numbers[mid]<numbers[right]){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return numbers[left];
    }
}
