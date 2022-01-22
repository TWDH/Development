//4.in-place operation
//977.Squares of a Sorted Array
class Solution {
    public int[] sortedSquares(int[] A) {
        //1.边界情况
        if(A == null || A.length == 0){
            return new int[0];
        }
        //2.初始化存储结果的数组res
        int[] res = new int[A.length];
        
        //3.双指针i，j指原数组向头尾
        int i = 0;
        int j = A.length - 1;
        //k指向res尾部，因为绝对值最大的一定是在原数组的头尾。无法预测原数组中最小的并向两侧扩展(麻烦)
        for(int k = A.length - 1; k >= 0; k--){
            //原数组 尾>头
            if(Math.abs(A[i]) < Math.abs(A[j])){
                res[k] = A[j] * A[j];
                j--;
            }
            //头 >= 尾
            else{
                res[k] = A[i] *A[i];
                i++;
            }
        }
        return res;
    }
}