public class MyUtils {
    public static boolean contains(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(char value, char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }

    // returns if an element is found only once in an array
    public static boolean isUnique(char symbol, char[] array) {
        int instances = 0;
        for(int i = 0; i < array.length; i++) {
            if(symbol == array[i]) instances++;
        }
        return instances == 1;
    }

    public static int indexOf(char symbol, char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (symbol == array[i]) return i;
        }
        return -1;
    }

    public static boolean validarAlfabeto(char[] alfabeto) {
        for(int i = 0; i < alfabeto.length; i++) {
            if(!MyUtils.isUnique(alfabeto[i], alfabeto)) return false;
        }

        return true;
    }

    public static boolean validarGamma(char[] gamma, char[] alfabeto) throws Exception {
        for(char sim : alfabeto) {
            if(!MyUtils.contains(sim, gamma)) return false;
        }

        for(char sim : gamma) {
            if(!MyUtils.isUnique(sim, gamma)) return false;
        }

        return true;
    }
}
