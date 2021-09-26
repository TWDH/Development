class Solution {
    public boolean checkIfExist(int[] arr) {
        //1.边界情况
        if(arr == null || arr.length == 0){
            return false;
        }
        //2.将数值存入HashMap
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            //3.先判断当前arr[i],再将其加入map。（特殊情况0）
            //当前数arr[i]的2倍，或者一半曾将出现过，就会被存在hashmap中并找到            
            if(map.containsKey(arr[i] * 2) || (map.containsKey(arr[i] / 2) && arr[i] % 2 == 0)){  
                return true;
            }
            map.put(arr[i], i);
        }
        return false;
    }
}