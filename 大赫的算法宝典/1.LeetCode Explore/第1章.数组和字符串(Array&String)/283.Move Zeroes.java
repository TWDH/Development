//4.in-place operation
class Solution {
    public void moveZeroes(int[] nums) {
        //1.curPtr结果数组指针，将不为0的数字加入到curPtr
        int curPtr = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[curPtr] = nums[i];
                curPtr++;
            }
        }
        //2.完成数字!=0后,将其余位置填为0
        for(int j = curPtr; j <nums.length; j++){
            nums[j] = 0;
        }
    }
}