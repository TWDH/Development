//4.in-place operation
//905.Sort Array By Parity
class Solution {
    public int[] sortArrayByParity(int[] A) {
        //1.边界条件
        if(A == null || A.length == 0){
            return new int[0];
        }
        //2.双指针i，j指向数组头尾
        int i = 0; 
        int j = A.length - 1;
        //控制两指针不相碰
        while(i < j){
            if(A[i] % 2 != 0){
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                //此时nums[j]一定是基数，但是还回去的nums[i]不一定
                j--;
            }
            else{
                //只有确定当前数是基数才++
                i++;
            }
        }
        return A;
    }
}