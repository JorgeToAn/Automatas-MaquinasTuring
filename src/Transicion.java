public class Transicion {
    public int estado;
    public char sim;
    public char mov;

    // crea una transicion a partir de una cadena "{estado,sim,mov}"
    public Transicion(String s) throws Exception {
        String[] elems = s.substring(1, s.length()-1).split(",(?!,,)");

        if (elems.length <= 3) {
            try {
                this.estado = Integer.parseInt(elems[0]);
            } catch (NumberFormatException e) {
                throw new Exception("El estado sucesor debe ser un numero");
            }
            
            if(elems[1].length() == 1) {
                this.sim = elems[1].charAt(0);
            } else {
                throw new Exception("El segundo elemento de la transicion debe ser un simbolo");
            }

            if(elems[2].length() == 1 && (elems[2].charAt(0) == 'D' || elems[2].charAt(0) == 'I' || elems[2].charAt(0) == 'S')) {
                this.mov = elems[2].charAt(0);
            } else {
                throw new Exception("El tercer elemento debe ser un simbolo entre (D)erecha, (I)zquierda o (S)top");
            }
        } else {
            throw new Exception("La transicion debe de ser formada por 3 elementos (estado sucesor, simbolo, accion)");
        }
    }
}
