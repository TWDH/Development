//方法一
class Solution {
    public boolean validMountainArray(int[] arr) {
        //1.边界情况
        if(arr == null || arr.length < 3){
            return false;
        }
        
        //2.遍历找到最大点
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
                maxIndex = i;
            }
        }
        
        //3.只有上山/下山
        if(maxIndex == 0 || maxIndex == arr.length - 1){
            return false;
        }
        
        //4.上山阶段
        for(int j = 1; j <= maxIndex; j++){
            if(arr[j] <= arr[j - 1]){
                return false;
            }
        }
        
        //5.下山阶段
        for(int k = maxIndex; k < arr.length - 1; k++){
            if(arr[k] <= arr[k + 1]){
                return false;
            }
        }
        return true;
    }
}

//方法二
class Solution {
    public boolean validMountainArray(int[] arr) {
        int N = arr.length;
        int i = 0;
        
        //1.上山阶段
        while(i < N - 1 && arr[i] < arr[i+1]){
            i++;
        }
        
        //2.i不能是山脚(5 4 3 2 1)或者山顶(1 2 3 4 5)
        //判断一定要在下山之前，因为如果正确一定会有 i=N-1。
        if(i == 0 || i == N - 1){
            return false;
        }
        
        //3.下山阶段
        while(i < N - 1 && arr[i] > arr[i+1]){
            i++;
        } 
        
        return i == N - 1;        
    }
}