public class MaquinaTuring {
    private char[] alfabeto;
    private char[] gamma;
    private int estados;
    private int[] finales;
    private Transicion[][] tabla;

    public MaquinaTuring(TextFile archivo) throws Exception {
        String linea = archivo.readLine();
        String[] aux = null;

        // lee el alfabeto
        if(linea != null) {
            if(linea.length() == 1) {
                this.alfabeto = linea.toCharArray();
            } else {
                this.alfabeto = linea.replaceAll(",(?!,,)", "").toCharArray();
                
                if(!MyUtils.validarAlfabeto(this.alfabeto)) throw new Exception("El alfabeto debe contener simbolos unicos");
            }
        } else {
            throw new Exception("Alfabeto no puede ser vacio");
        }

        // lee el numero de estados Q
        linea = archivo.readLine();
        if(linea != null) {
            int estados = -1;
            try {
                estados = Integer.parseInt(linea);
            } catch (Exception e) {
                throw new Exception("El numero de estados Q debe ser un numero");
            }

            if(estados > 0) {
                this.estados = estados;
            } else {
                throw new Exception("El numero de estados Q no puede ser menor de 1");
            }
        } else {
            throw new Exception("El numero de estados Q no puede ser vacio");
        }


        // lee gamma (simbolos de cinta)
        linea = archivo.readLine();
        if(linea != null) {
        if(linea.length() == 1) {
            //error?
        } else {
            this.gamma = linea.replaceAll(",(?!,,)", "").toCharArray();

            if(!MyUtils.validarGamma(this.gamma, this.alfabeto)) throw new Error("El alfabeto gamma debe contener todos los simbolos del alfabeto y los simbolos de cinta, todos unicos");
        }
        } else {
        throw new Exception("Alfabeto gamma no puede ser vacio");
        }

        // lee los estados finales
        linea = archivo.readLine();
        if(linea != null) {
            aux = linea.split(",");
            this.finales = new int[aux.length];
            for (int i = 0; i < aux.length; i++) {
                try {
                    int estado = Integer.parseInt(aux[i]);
                    if(estado >=0 && estado < this.estados) {
                        this.finales[i] = estado;
                    } else {
                        throw new Exception("Los estados finales deben de pertenecer al rango de estados 0 a Q-1");
                    }
                } catch (NumberFormatException e) {
                    throw new Exception("Los estados finales deben ser numeros separados por coma");
                } catch (Exception e) {
                    throw e;
                }
            }
        }

        // lee la tabla
        this.tabla = new Transicion[this.estados][this.gamma.length];
        int renglones = 0;
        while ((linea = archivo.readLine()) != null) {
            // divide cada par de llaves separados por coma
            aux = linea.split(",(?![^{]*})");
            if(renglones < this.estados) {
                if(aux.length == this.gamma.length) {
                    for(int j = 0; j < this.tabla[0].length; j++) {
                        try {
                            Transicion t = new Transicion(aux[j]);
                            if(t.estado < this.estados) {
                                this.tabla[renglones][j] = t;
                            } else {
                                throw new Exception("El estado sucesor en la transicion debe de pertenecer al rango de estados 0 a Q-1");
                            }
                        } catch (NumberFormatException e) {
                            throw new Exception("La tabla debe de ser formada por numeros separados por comas");
                        } catch (Exception e) {
                            throw e;
                        }
                    }
                } else {
                    throw new Exception("El numero de columnas en la tabla de transiciones debe de ser igual al numero de simbolos en el alfabeto");
                }
            } else {
                throw new Exception("El numero de renglones en la tabla de transiciones debe de ser uno menor al numero de estados");
            }
            renglones++;
        }
        if (renglones != this.estados) {
            throw new Exception("El numero de renglones en la tabla de transiciones debe de ser uno menor al numero de estados");
        }
    }

    private ListaDoble crearCinta(String cadena) throws Exception {
        // obtiene el primer simbolo auxiliar del alfabeto gamma
        char beta = this.gamma[this.alfabeto.length];
        ListaDoble cinta = new ListaDoble();

        cinta.insertarAlInicio(beta);
        for (char sim : cadena.toCharArray()) {
            if (!MyUtils.contains(sim, this.alfabeto)) throw new Exception("La cadena contiene simbolos que no pertenecen al alfabeto");
            cinta.insertarAlFinal(sim);
        }
        cinta.insertarAlFinal(beta);

        return cinta;
    }

    public void procesar(String input) throws Exception {
        ListaDoble cinta = crearCinta(input);
        char beta = this.gamma[this.alfabeto.length];
        Nodo ap = cinta.cabeza.sig; // apunta al primer simbolo de la cadena
        int estado = 0;
        char mov;

        do {
            int columna = MyUtils.indexOf(ap.dato, this.gamma);
            Transicion t = this.tabla[estado][columna];

            ap.dato = t.sim; // cambia el simbolo en cinta
            estado = t.estado;
            mov = t.mov;

            if (mov == 'D') ap = ap.sig;
            if (mov == 'I') ap = ap.ant;
        } while (mov != 'S');

        while (ap != null && ap.dato != beta) {
            System.out.print(ap.dato);
            ap = ap.sig;
        }
        System.out.println();
    }
}
