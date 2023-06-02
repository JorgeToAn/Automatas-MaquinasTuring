public class ListaDoble {
    public Nodo cabeza;

    public ListaDoble() {
        cabeza = null;
    }

    public void insertarAlInicio(char dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            nuevoNodo.sig = cabeza;
            cabeza.ant = nuevoNodo;
            cabeza = nuevoNodo;
        }
    }

    public void insertarAlFinal(char dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevoNodo;
            nuevoNodo.ant = actual;
        }
    }

    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.sig;
        }
        System.out.println();
    }
}
