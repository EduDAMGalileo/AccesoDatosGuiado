package ejercicios.cuaderno0;

public class Ej01_Static {

    private static int visitas = 0;

    public static void main(String[] args) {
        visitas++;
        System.out.println("Visitas: " + visitas);
    }
}
