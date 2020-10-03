package com.unsch.collection.dinamicas.lineal;

/**
 * Esta es una lista simplemente enlazada.
 * @autor fernando
 * @author jhon
 */
public class ListaSimple <Tipo> extends Lista<Tipo>{
    @Override
    public void agregar(Tipo item) {

    }

    @Override
    public void remover(int posicion) {

    }

    @Override
    public void remover(Tipo objeto) {

    }

    @Override
    public void limpiar() {

    }

    @Override
    public Tipo getItem(int posicion) {
        return null;
    }

    @Override
    public boolean existe(Tipo item) {
        return false;
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
        public NodoSimple<Tipo> getSiguiente() {
            return this.siguiente;
        }

        /**
         * Modifica la referencia del nodo que sigue despues de este nodo.
         * @param siguiente nueva referencia del siguiente nodo.
         **/
        public void setSiguiente(NodoSimple<Tipo> siguiente) {
            this.siguiente = siguiente;
        }
    }

}