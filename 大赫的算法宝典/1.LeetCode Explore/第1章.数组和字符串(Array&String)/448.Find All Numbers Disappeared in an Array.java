//conclusion
//448.Find All Numbers Disappeared in an Array    
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //1.题目数组范围处于1~n,所以将数字所对应的index设为负数代表这个数字已经出现过。
        //相反，如果数字为正则证明此数字没出现过。
        for(int i : nums){
            //2.此处使用abs，防止之前的数字在设置index时将其变为负数。
            int index = Math.abs(i);
            if(nums[index - 1] > 0){
                nums[index - 1] *= -1; 
            }
        }
        //3.将正数放入res
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                res.add(i + 1);
            }
        }
        return res;
    }
}