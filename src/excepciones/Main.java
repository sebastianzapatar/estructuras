package excepciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// ---------------- EXCEPCIONES ----------------

class CupoLlenoException extends Exception {
    public CupoLlenoException(String msg) {
        super(msg);
    }
}

class EstudianteYaInscritoException extends Exception {
    public EstudianteYaInscritoException(String msg) {
        super(msg);
    }
}

// ---------------- MODELOS ----------------

class Estudiante {
    private final String id;
    private final String nombre;
    private final String correo;

    public Estudiante(String id, String nombre, String correo) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id inválido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre inválido");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("correo inválido");
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Estudiante{id='" + id + "', nombre='" + nombre + "', correo='" + correo + "'}";
    }

    // Para evitar duplicados: dos estudiantes son iguales si tienen el mismo id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudiante)) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Profesor {
    private final String id;
    private final String nombre;
    private final String correo;

    public Profesor(String id, String nombre, String correo) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id inválido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre inválido");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("correo inválido");
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    @Override
    public String toString() {
        return "Profesor{id='" + id + "', nombre='" + nombre + "', correo='" + correo + "'}";
    }
}

class Curso {
    private final String codigo;
    private final String nombre;
    private final int cupoMaximo;
    private final Profesor profesor;
    private final List<Estudiante> inscritos;

    public Curso(String codigo, String nombre, int cupoMaximo, Profesor profesor) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("código inválido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre inválido");
        if (cupoMaximo <= 0) throw new IllegalArgumentException("cupoMaximo debe ser > 0");
        if (profesor == null) throw new IllegalArgumentException("profesor no puede ser null");

        this.codigo = codigo;
        this.nombre = nombre;
        this.cupoMaximo = cupoMaximo;
        this.profesor = profesor;
        this.inscritos = new ArrayList<>();
    }

    public boolean hayCupo() {
        return inscritos.size() < cupoMaximo;
    }

    public int cuposDisponibles() {
        return cupoMaximo - inscritos.size();
    }

    private boolean estaInscrito(Estudiante e) {
        return inscritos.contains(e); // usa equals() de Estudiante (por id)
    }

    public void inscribir(Estudiante e) throws CupoLlenoException, EstudianteYaInscritoException {
        if (e == null) throw new IllegalArgumentException("estudiante no puede ser null");

        if (estaInscrito(e)) {
            throw new EstudianteYaInscritoException(
                    "El estudiante con id=" + e.getId() + " ya está inscrito en el curso " + codigo
            );
        }

        if (!hayCupo()) {
            throw new CupoLlenoException(
                    "No hay cupo en el curso " + codigo + ". Cupo máximo=" + cupoMaximo
            );
        }

        inscritos.add(e);
    }

    public boolean retirar(Estudiante e) {
        if (e == null) return false;
        return inscritos.remove(e);
    }

    public String listarInscritos() {
        if (inscritos.isEmpty()) return "(Sin estudiantes inscritos)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inscritos.size(); i++) {
            sb.append(i + 1).append(". ").append(inscritos.get(i)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cupoMaximo=" + cupoMaximo +
                ", inscritos=" + inscritos.size() +
                ", cuposDisponibles=" + cuposDisponibles() +
                ", profesor=" + profesor +
                '}';
    }
}

// ---------------- MAIN DE PRUEBA ----------------

public class Main {
    public static void main(String[] args) {
        Profesor prof = new Profesor("P01", "Ana Gómez", "ana@uni.edu");
        Curso curso = new Curso("ED101", "Estructuras de Datos", 2, prof);

        Estudiante e1 = new Estudiante("E01", "Juan", "juan@uni.edu");
        Estudiante e2 = new Estudiante("E02", "María", "maria@uni.edu");
        Estudiante e3 = new Estudiante("E03", "Carlos", "carlos@uni.edu");

        try {
            curso.inscribir(e1);
            curso.inscribir(e2);

            // Intento que debería fallar por cupo lleno
            curso.inscribir(e3);

        } catch (EstudianteYaInscritoException ex) {
            System.out.println("⚠️ " + ex.getMessage());
        } catch (CupoLlenoException ex) {
            System.out.println("⚠️ " + ex.getMessage());
        }

        // Intento duplicado (debería fallar por ya inscrito)
        try {
            curso.inscribir(e1);
        } catch (Exception ex) {
            System.out.println("⚠️ " + ex.getMessage());
        }

        System.out.println("\n=== INFO DEL CURSO ===");
        System.out.println(curso);

        System.out.println("\n=== ESTUDIANTES INSCRITOS ===");
        System.out.print(curso.listarInscritos());
    }
}
