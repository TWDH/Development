//方法一：双指针，写法简单
class Solution {
    public int removeElement(int[] nums, int val) {
        //1.设置左指针i，右指针n（表示最后数组的长度）
        int i = 0;
        int n = nums.length;
        while(i < n){
            //当nums[i]找到val，将其与末尾元素交换，然后舍去最后一个元素(n--)。
            if(nums[i] == val){
                nums[i] = nums[n - 1];
                n--;
            }
            else{
                //如果没有找到val，则继续移动指针i
                i++;
            }
        }
        return n;
    }
}


//方法二：双指针，写法复杂
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //1.双指针
        int i = 0;
        int j= nums.length - 1;
        
        //2.头尾交换,j指向最后一个不是val的数
        while(i < j){
            //数组尾部已经是val
            if(nums[j] == val){
                j--;
                continue;
            }
            //当做指针i指向val
            if(nums[i] == val){
                //交换
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                //更新指针
                i++;
                j--;
            }
            else{
                //当i指向的数不是val
                i++;
            }
        }
        
        //分析i=j和i<j的情况
        if(i == j && nums[i] == val){
            return j;
        }
        else if(i == j && nums[i] != val ||j < i){
            return j + 1;
        }
        return j;
    }
}