//5.conclusion
//414.Third Maximum Number
class Solution {
    public int thirdMax(int[] nums) {
        //1.边界情况
        if(nums == null || nums.length == 0){
            return 0;
        }
        //2.排序
        Arrays.sort(nums);
        //3.去除重复数字
        int j = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i - 1]){
                nums[j] = nums[i];
                j++;
            }
        }
        //j为已排序，无重复数组的个数
        //当处理完的数组小于3个时
        if(j < 3){
            return nums[j - 1];
        }
        return nums[j - 3];
    }
}