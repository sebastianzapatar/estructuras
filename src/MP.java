import java.util.Arrays;
import java.util.Scanner;

public class MP {

    public static int mayorPositivo(int[] listN) {
        int mayor=listN[0];
        for(int i=1;i<listN.length;i++){
            if(listN[i]>mayor){
                mayor=listN[i];
            }
        }
        if(mayor<0) {
            System.out.println("Todos son negativos");
            return 0;
        }
        return mayor;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        int[] listN=new int[0];
        while ((line=in.nextLine()).compareTo("")!=0) {
            int numberLine=Integer.parseInt(line);
            listN=Arrays.copyOf(listN, listN.length+1);
            listN[listN.length-1]=numberLine;
        }
        System.out.println(mayorPositivo(listN));
        in.close();


    }

}