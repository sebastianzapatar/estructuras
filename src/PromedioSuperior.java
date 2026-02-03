import java.util.Arrays;
import java.util.Scanner;

class Estudiante{
    private String nombre;
    private int edad;
    private double promedio;
    public Estudiante(String nombre, int edad, double promedio) {
        super();
        this.nombre = nombre;
        this.edad = edad;
        this.promedio = promedio;
    }
    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }
    // Completa las funciones que hagan falta

}

class Aula{
    private Estudiante[] estudiantes;

    public Aula(Estudiante[] estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante[] promediosuperior4() {
        int total = 0;
        for (int i = 0; i < estudiantes.length; i++) {
            if(estudiantes[i].getPromedio()>=4){
                total++;
            }
        }
        Estudiante[] aula = new Estudiante[total];
        int index = 0;
        for (int i = 0; i < estudiantes.length; i++) {

            if(estudiantes[i].getPromedio()>=4){
                aula[index++] = estudiantes[i];
            }
        }
        return aula;
    }

}

public class PromedioSuperior {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        Estudiante[] lista=new Estudiante[0];
        while ((line=in.nextLine()).compareTo("")!=0) {
            String[] lineSplit=line.split(",");
            Estudiante e=new Estudiante(lineSplit[0],Integer.parseInt(lineSplit[1]),Double.parseDouble(lineSplit[2]));
            lista=Arrays.copyOf(lista, lista.length+1);
            lista[lista.length-1]=e;
        }
        Aula a=new Aula(lista);
        Estudiante[] listaP=a.promediosuperior4();
        for (Estudiante e:listaP) {
            System.out.println(e.getNombre());
        }

        in.close();


    }

}