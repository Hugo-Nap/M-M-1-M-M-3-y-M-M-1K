import java.util.Scanner;

public class Modelos {

    //variable global solo para esta clase
    //agregar static para que sea disponible para toda la clase
    static double lambda;
    static double mu;

    public static void main(String[] args) {

        
        byte option = 0;
        Scanner reader = new Scanner(System.in);
        do{
            System.out.println("|= = = = = = = = = = = = = = = |");
            System.out.println("|Selecione el modelo a utilizar|");
            System.out.println("|1........................M/M/1|");
            System.out.println("|2........................M/M/3|");
            System.out.println("|3.......................M/M/1K|");
            System.out.println("|4........................Salir|");
            System.out.println("|= = = = = = = = = = = = = = = |");
            System.out.println("opcion :                        ");
            option = reader.nextByte();
            System.out.println("|= = = = = = = = = = = = = = = |");

            switch (option) {
                case 1 -> {
                    //Modelo M/M/1
                    System.out.println("Ingrese el valor de Lambda: ");
                    lambda = reader.nextInt();
                    System.out.println("Ingrese el valor de mu: ");
                    mu = reader.nextInt();

                    //paso 1: Utilizacion del sistema
                    double p = lambda / mu;
                    System.out.println("Utilizacion del sistema: " + p);

                    //paso 2: Numero Promedio de Clientes en la cola
                    double Lq = (Math.pow(lambda, 2)) /(mu * (mu - lambda));
                    System.out.println("Numero Promedio de Clientes en la cola: " + Lq);

                    //paso 3: Tiempo Promedio de espera en la cola Wq
                    double Wq = Lq / lambda;
                    System.out.println("Tiempo Promedio de espera en la cola: " + Wq + " Horas"  + " ó " + Wq * 60 + " Minutos");

                    //paso 4: Tiempo total en el sistema
                    double W = Wq + 1/mu;
                    System.out.println("Tiempo total en el sistema: " + W + " Horas" + " ó " + W * 60 + " Minutos");
                }
                case 2 -> {
                    // Modelo M/M/3
                    //creacion de constante c
                    double c = 3;
                    System.out.println("Ingrese el valor de Lambda: ");
                    lambda = reader.nextInt();
                    System.out.println("Ingrese el valor de mu: ");
                    mu = reader.nextInt();

                    //paso 1: Constante a
                    double a = lambda / mu;

                    //paso 2: Utilizacion del servidor
                    double p = a / c;

                    //paso 3: Probablidad de que no haya nadie en el sistema
                    // paso 1 para p0 sumatoria
                    double sumatoria = 0;
                    for (int n = 0; n < c; n++) {
                        sumatoria = sumatoria + (Math.pow(a, n) / factorial(n));
                    }
                    // paso 2 para p0
                    double terminoEspera = (Math.pow(a, c)) / (factorial((int) c));
                    // paso 3 para p0
                    double sumaTotal = sumatoria + terminoEspera;
                    // paso 4 para p0
                    double pCero = 1 / sumaTotal;

                    // paso 4 Probabilidad de que un cliente tenga que esperar
                    double Pespera = (Math.pow(a, c)) / (factorial((int) c) * (1 - p));

                    // paso 5 Longitud promedio en cola
                    double Lq = (Pespera * p) / 1 - p;

                    //paso 6 Tiempo promedio de espera en la cola
                    double Wq = Lq / lambda;

                    //paso 7 Tiempo total en el sistema 
                    double W = Wq + 1/mu;

                    //paso 8 Numero promedio en el sistema
                    double L = lambda * W;

                    //nota mañana ya muestro los resultados, ya tengo sueño :(
                }
            }
        }while(option != 4);
    }
    //metodo para obtener el factorial de n , ocupando recursividad
    public static int factorial(int n){
        if (n == 0) {
            return 1;
        }else{
            return n * factorial(n - 1);
        }
    }
}
