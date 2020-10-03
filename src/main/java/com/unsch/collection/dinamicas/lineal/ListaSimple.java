package com.unsch.collection.dinamicas.lineal;

/**
 * Esta es una lista simplemente enlazada.
 * @author fernando
 * @author jhon
 */
public class ListaSimple <Tipo> extends Lista<Tipo> {

    /** Aqui se almacena el primer nodo de la lista */
    private NodoSimple<Tipo> cabeza = null;

    /** Constructir por defecto */
    public ListaSimple() {
        super();
    }
    /**
     * Agrega un elemento al inicio de la lista envolviendolo en un nodo.
     * @param item es el elemento que se va a agregar a la lista.
     **/
    public void agregarInicio(Tipo item) {
        NodoSimple<Tipo> nuevoNodo;
        nuevoNodo = new NodoSimple<>(item, null);
        if (this.cabeza != null) {
            nuevoNodo.setSiguiente(this.cabeza);
        }
        this.cabeza = nuevoNodo;
        this.incrementarContador();
    }

    /**
     * Agrega un elemento al final de la lista. coloca el elemento dentro de un nodo para
     * asi poder se unido a losdemas nodos.
     *  @param item es el elemento que se va a agregar a la lista.
     **/
    public void agregarFinal(Tipo item) {
        NodoSimple<Tipo> nuevoNodo;
        nuevoNodo = new NodoSimple<>(item, null);
        if (this.cabeza != null) {
            NodoSimple<Tipo> temporal = this.cabeza;
            while (temporal.getSiguiente() != null) {
                temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
            }
            temporal.setSiguiente(nuevoNodo);
        } else {
            this.cabeza = nuevoNodo;
        }
        this.incrementarContador();
    }

    /**
     * Metodo por defecto de todas las listas en general.
     * Agrega el elemento al final de la lista, esto lo hace llamando al metodo correpondiente.
     *  @param item es el elemento que se va a agregar a la lista.
     **/
    @Override
    public void agregar(Tipo item) {
        this.agregarFinal(item);
    }

    /**
     * Retira un elemento de la lista.
     * @param posicion indica la posicion del elemento en la lista.
     **/
    @Override
    public void remover(int posicion) {
        if (this.getLongitudLista() > 0) {
            if (posicion < this.getLongitudLista()) {
                if (posicion == 0) {
                    this.removerInicio();
                } else if (posicion == this.getLongitudLista() - 1) {
                    this.removerFinal();
                } else {
                    int contador = 0;
                    NodoSimple<Tipo> temporal = this.cabeza;
                    while (contador < posicion - 1) {
                        temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
                        contador++;
                    }
                    NodoSimple<Tipo> posteriores = (NodoSimple<Tipo>) ((NodoSimple<Tipo>) temporal.getSiguiente()).getSiguiente();
                    temporal.getSiguiente().destruir();
                    temporal.setSiguiente(posteriores);
                    this.decrementarContador();
                }
            } else {
                throw new IndexOutOfBoundsException("No existe la posicion " + posicion);
            }
        } else {
            throw new IndexOutOfBoundsException("No puedes intentar remover elementos de una lista vacia.");
        }
    }

    /**
     * Remueve el primer nodo de la lista.
     * Si ubiese mas nodos despues del primero, el segundo nodo pasa a ser el primer nodo.
     **/
    public void removerInicio() {
        if (this.cabeza != null) {
            // se ejecuta si existe de 2 a mas nodos
            if (this.cabeza.getSiguiente() != null) {
                NodoSimple<Tipo> temporal = this.cabeza;
                this.cabeza = temporal.siguiente;
                temporal.destruir();
            } else {
                // se ejecuta si solamnte existe un solo nodo
                this.cabeza.destruir();
                this.cabeza = null;
            }
            this.decrementarContador();
        } else {
            throw new IndexOutOfBoundsException("No puedes intentar remover elementos de una lista vacia.");
        }
    }

    /**
     * Remuve el ultimo nodo de la lista.
     * Primeramente se ubica en el penultimo nodo para hacer que el puntero hacia el ultimo
     * nodo se remueva y por ultimo se quita el ultimo nodo de la lista incluyendo su valor.
     **/
    public void removerFinal() {
        if(this.cabeza != null) {
            NodoSimple<Tipo> temporal = this.cabeza;
            while (((NodoSimple<Tipo>) temporal.getSiguiente()).getSiguiente() != null) {
                temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
            }
            temporal.getSiguiente().destruir();
            temporal.setSiguiente(null);
            this.decrementarContador();
        } else {
            throw new IndexOutOfBoundsException("No puedes intentar remover elementos de una lista vacia.");
        }
    }

    /**
     * Remueve un elemento de la lista.
     * Remueve el nodo con la primera ocurrencia del item.
     * @param item indica cual es elemento que se quiere quitar de la lista
     **/
    @Override
    public void remover(Tipo item) {
        if(this.cabeza != null) {
            NodoSimple<Tipo> temporal = this.cabeza;
            if (temporal.getValor().equals(item)) {
                this.cabeza = (NodoSimple<Tipo>) temporal.getSiguiente();
                temporal.destruir();
            } else {
                while (temporal != null) {
                    if (temporal.getSiguiente().getValor().equals(item)) {
                        NodoSimple<Tipo> remover = (NodoSimple<Tipo>) temporal.getSiguiente();
                        temporal.setSiguiente(remover.getSiguiente());
                        remover.destruir();
                        break;
                    } else {
                        temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
                    }
                }
                this.decrementarContador();
            }
        } else {
            throw new IndexOutOfBoundsException("No puedes intentar remover elementos de una lista vacia.");
        }
    }

    /**
     * Quita todos los elementos de la lista y resetea el contador.
     **/
    @Override
    public void limpiar() {
        this.cabeza.destruir();
        this.cabeza = null;
        this.reiniciarContador();
    }

    /**
     * Obtiene un objeto de una determinada posicion.
     * @param posicion indica la posicion en la lista del objeto que se quiere obtener.
     * @return el objeto buscado en caso de haberse encontrado.
     **/
    @Override
    public Tipo getItem(int posicion) {
        if (this.cabeza != null) {
            if (posicion < this.getLongitudLista()) {
                int contador = 0;
                NodoSimple<Tipo> temporal = this.cabeza;
                while (contador < posicion) {
                    temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
                    contador++;
                }
                return temporal.getValor();
            } else {
                throw new IndexOutOfBoundsException("No existe la posicion " + posicion);
            }
        } else {
            throw new IndexOutOfBoundsException("La lista se encuentra vacia.");
        }
    }

    /**
     * Verifica si algun nodo de la lista contiene un deteminado objeto.
     * @param item es el objeto que se quiere buscar el algun nodo de la lista.
     **/
    @Override
    public boolean existe(Tipo item) {
        if (this.cabeza != null) {
            boolean existe = false;
            NodoSimple<Tipo> temporal = this.cabeza;
            while (temporal != null) {
                if (temporal.getValor().equals(item)) {
                    existe = true;
                    break;
                } else {
                    temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
                }
            }
            return existe;
        } else {
            return false;
        }
    }

    /**
     * @return la representacion de los valores de cada nodo de la lista.
     **/
    @Override
    public String toString() {
        if (this.cabeza != null) {
            StringBuilder tokens = new StringBuilder();
            NodoSimple<Tipo> temporal = this.cabeza;
            tokens.append("[");
            while (temporal != null) {
                tokens.append(temporal.getValor()).append(", ");
                temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
            }
            tokens.append("]");
            return tokens.toString().replace(", ]", "]");
        } else {
            return "[]";
        }
    }

    /**
     * Este nodo simple almacena un valor y la referencia al nodo siguiente.
     * @author jhon
     **/
    protected static class NodoSimple<Tipo> extends Nodo<Tipo> {

        /** Aqui se almacena la referencia al nodo siguiente. */
        private NodoSimple<Tipo> siguiente;

        /**
         * Este constructor inicia el valor del nodo y el puntero del nodo siguiente.
         *
         * @param valor este es el valor que se va a agregar a este nodo.
         * @param siguiente referencia del siguiente nodo.
         */
        NodoSimple(Tipo valor, NodoSimple<Tipo> siguiente) {
            super(valor);
            this.siguiente = siguiente;
        }

        /**
         * @return devuelve la referencia del nodo que sigue despues de este nodo.
         **/
        public Nodo<Tipo> getSiguiente() {
            return this.siguiente;
        }

        /**
         * Modifica la referencia del nodo que sigue despues de este nodo.
         * @param siguiente nueva referencia del siguiente nodo.
         **/
        public void setSiguiente(Nodo<Tipo> siguiente) {
            this.siguiente = (NodoSimple<Tipo>) siguiente;
        }

        /**
         * Este metodo se encarga de remover el valor del nodo y la referencia del siguiente nodo.
         **/
        @Override
        public void destruir() {
            super.destruir();
            this.siguiente = null;
        }
    }

}