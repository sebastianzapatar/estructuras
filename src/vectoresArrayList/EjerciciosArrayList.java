package vectoresArrayList;

import java.util.Arrays;

public class EjerciciosArrayList {
    public static int sumar(int[] array){
        int sum = 0;// O(1)
        for(int i = 0; i < array.length; i++){ //O(n)
            sum += array[i]; //O(1)
        }
        return sum; //O(1)
    }
    public static long multiplicar(int[] array){
        long product = 1;
        for(int i = 0; i < array.length; i++){
            product *= array[i];
        }
        return product;
    }
    //Pairs of given array
    public static void pair(int[] array){

        for(int i = 0; i < array.length; i++){ // O(n)
            for(int j = 0; j < array.length; j++){ // O(n)
                System.out.println(array[i]+" "+array[j]); //O(1)
            }
        }
        //O(N*N)
    }
    public static void reverse(int[] array){
        for(int i = 0; i < array.length/2; i++){
            int temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
        }

    }

    static void main() {
        int[] array = {1,2,3,4,5};
        reverse(array);
        System.out.println(Arrays.toString(array));
    }

}
