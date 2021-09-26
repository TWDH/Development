//5.conclusion
//1051.Height Checker    
class Solution {
    public int heightChecker(int[] heights) {
        //1.边界条件
        if(heights == null || heights.length == 0){
            return 0;
        }
        //2.拷贝原数组
        int[] correctHeight = Arrays.copyOf(heights, heights.length);
        //3.对拷贝完的数组correctHeightipjo排序
        Arrays.sort(correctHeight);
        //4.比较原数组和排序后数组的区别
        int count = 0;
        for(int i = 0; i < heights.length; i++){
            if(heights[i] != correctHeight[i]){
                count++;
            }
        }
        return count;
    }
}