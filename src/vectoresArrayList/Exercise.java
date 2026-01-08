package vectoresArrayList;

import java.util.Arrays;

public class Exercise {
    public static int[] middle(int[] array) {
        if(array.length <= 2){
            return new int[0];

        }
        int newArray[]=new int[array.length-2];
        for(int i=1;i<array.length-1;i++){
            newArray[i-1]=array[i];
        }
        return newArray;
    }
    public static int sumDiagonalElements(int[][] array) {
        int sum=0;
        for(int i=0;i<array.length;i++){
            sum+=array[i][i];
        }
        return sum;
    }
    public static int[] findTopTwoScores(int[] array){
        int better2[]=new int[2];
        int better=Integer.MIN_VALUE;
        int sBetter=Integer.MIN_VALUE;
        for(int i=0;i<array.length;i++){
            if(array[i]>better){
                sBetter=better;
                better=array[i];
            }else if(array[i]>sBetter && array[i]!=better){

                sBetter=array[i];
            }

        }
        better2[0]=better;
        better2[1]=sBetter;
        return better2;
    }
    static int findMissingNumberInArray(int[] arr) {
        int tam=arr.length+1;
        int sum=tam*(tam+1)/2;
        for(int i=0;i<arr.length;i++){
            sum-=arr[i];
        }
        return sum;
    }
    public static int[] removeDuplicates2(int[] array) {
        int n = array.length;
        int[] uniqueArray = new int[n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            boolean isDuplicate = false;
            for (int j = i + 1; j < n; j++) {
                if (array[i] == array[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueArray[index++] = array[i];
            }
        }

        return Arrays.copyOf(uniqueArray, index);
    }
    public int removeDuplicates(int[] nums) {
        int size=0;
        int actual=nums[0];
        size++;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=actual){
                size++;
                actual=nums[i];
            }
        }
        return size;
    }
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {

            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int profit = prices[i] - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }

        return maxProfit;
    }
    public int[] twoSum(int[] nums, int target) {
        int solution[]=new int[2];
        for(int index=0;index<nums.length;index++){
            for(int i=index+1;i<nums.length;i++){
                if(nums[i]+nums[index]==target){
                    solution[0]=index;
                    solution[1]=i;
                }
            }
        }
        return solution;
    }
    public boolean isUnique(int[] intArray) {
        for(int index=0;index<intArray.length;index++){
            for(int i=index+1;i<intArray.length;i++){
                if(intArray[i]==intArray[index]){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean permutation(int[] array1, int[] array2){

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {

            int count1 = 0;
            int count2 = 0;

            for (int j = 0; j < array1.length; j++) {
                if (array1[j] == array1[i]) {
                    count1++;
                }
                if (array2[j] == array1[i]) {
                    count2++;
                }
            }

            if (count1 != count2) {
                return false;
            }
        }

        return true;
    }
    public void rotateMatrix(int[][] matrix) {
        //int rotate[][]=new int[matrix.length][matrix.length];
        int n = matrix.length;

        // Paso 1: Transponer la matriz
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Paso 2: Invertir cada fila
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }
    static void main() {
        int myArray[]={1, 2, 3, 4};
        IO.println(Arrays.toString(middle(myArray)));
    }

}