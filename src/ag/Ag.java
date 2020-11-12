/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag;

/**
 *
 * @author lalog
 */
public class Ag {

    public static String[] poblacion;
    public static int generacion;
    public static String padre, madre;
    public static double mutacion = .30;
    public static double cruza = .70;
    public static int num_cromosomas;

    public static void main(String[] args) {
        // poblacionInicial(20);
        int tamaniopoblacion = 10;
        num_cromosomas = 4;
        poblacionInicial(tamaniopoblacion, num_cromosomas);
        for (int z = 0; z < tamaniopoblacion; z++) {
            torneo(poblacion);
            cross(padre, madre);
        }
        for (int i = 0; i < poblacion.length; i++) {
            System.out.println("Individuo :"+i+" "+poblacion[i]+" "+fitness(cB(poblacion[i])) );
        }
        System.out.println("Fitness ="+fitness(cB(poblacion[tamaniopoblacion-1])));
    }

    public static void cross(String pad, String mad) {
        //if(Math.random()<cruza){
        //System.out.println("Pad =" + pad + " madre =" + mad);
        String padr = pad;
        String madr = mad;
        String partePadre = "";
        String parteMadre = "";
        int div = 1;
        if (num_cromosomas >= 2) {
            div = num_cromosomas / 2;
        }
        for (int i = 0; i < div; i++) {
            partePadre += String.valueOf(padr.charAt(i));
            parteMadre += String.valueOf(madr.charAt(i));
        }
        String hijo = partePadre + parteMadre;
        double num2 = Math.random();
        if(num2 < .5) {
            if (hijo.charAt(0) == '0') {
                hijo = changeCharInPosition(0, '1', hijo);
            }else{
                 hijo = changeCharInPosition(0, '0', hijo);
            }
        }else if (num2 > 0.5) {
            if (hijo.charAt(1) == '0') {
                hijo = changeCharInPosition(1, '1', hijo);
            }else{
                hijo = changeCharInPosition(1, '0', hijo);
            }
        }
        poblacion[0] = hijo;
        
    }

    public static String changeCharInPosition(int position, char ch, String str) {
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }

    public static void torneo(String[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (fitness(cB(arreglo[j])) < fitness(cB(arreglo[j + 1]))) {
                    String tmp = arreglo[j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (i == arreglo.length - 2) {
                madre = arreglo[i];
            } else if (i == arreglo.length - 1) {
                padre = arreglo[i];
            }
        }
    }

    public static void poblacionInicial(int tamanio, int numcromosomas) {
        poblacion = new String[tamanio];
        double num;
        for (int i = 0; i < poblacion.length; i++) {
            String cadena = "";
            for (int j = 0; j < numcromosomas; j++) {
                num = Math.random();
                if (num < 0.25 || num> 0.75) {
                    cadena += "0";
                } else {
                    cadena += "1";
                }
            }
            poblacion[i] = cadena;
        }
    }

    public static double fitness(int x) {
        return 5 * (x * x) - 20 * x + 3;
    }

    public static int cB(String cadena) {
        int num = 0;
        int num1 = 1;
        for (int i = cadena.length() - 1; i >= 0; i--) {
            if (num == 0) {
                if (cadena.charAt(i) == '1') {
                    num = num1;
                } else {
                    num1 *= 2;
                }
            } else {
                if (cadena.charAt(i) == '1') {
                    num += num1 * 2;
                    num1 *= 2;
                } else {
                    num1 *= 2;
                }
            }
        }
        return num;
    }
}
