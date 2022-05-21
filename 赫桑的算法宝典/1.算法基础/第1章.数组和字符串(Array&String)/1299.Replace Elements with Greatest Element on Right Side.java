//方法一：
class Solution {
    public int[] replaceElements(int[] arr) {
        //1.特殊情况
        if(arr == null || arr.length == 0){
            return null;
        }
        //2.HashMap<index,max>
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        //3.从后向前，记录每个数字右面最大的数
        for(int i = arr.length-2; i >= 0; i--){
            max = Math.max(arr[i+1], max);
            map.put(i, max);
        }
        //4.从头遍历，将每个数替换为HashMap中存放的对应index的最大数
        for(int j = 0; j < arr.length - 1; j++){
            arr[j] = map.get(j);
        }
        //5.将最后一个数换为-1
        arr[arr.length - 1] = -1;
        return arr;
    }
}

//方法二
class Solution {
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for(int i = arr.length - 1; i >= 0; i--){
            //1.将当前元素存入temp，因为后面需要将此元素一并比较，得出max
            int temp = arr[i];
            //2.max存放当前元素右方的最大值
            arr[i] = max;
            //3.比较当前元素，和其右面的max谁更大。在下一循环(i-1)中使用
            max = Math.max(temp, max);
        }
        return arr;
    }
}