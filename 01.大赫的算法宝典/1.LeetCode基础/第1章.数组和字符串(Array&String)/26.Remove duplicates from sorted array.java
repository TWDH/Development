//方法一：双指针
class Solution {
    public int removeDuplicates(int[] nums) {
        //1.特殊情况
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        //2.双指针
        //j初始化为1，因为第一个值肯定需要计入数组；只有前后两数不相等时才移动j并存入数组。
        //j = result的指针
        //i = curr遍历时的指针
        int j = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i - 1]){
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}