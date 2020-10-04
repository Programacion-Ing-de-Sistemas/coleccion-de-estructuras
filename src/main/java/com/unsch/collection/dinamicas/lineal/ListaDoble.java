package com.unsch.collection.dinamicas.lineal;

public class ListaDoble<Tipo> extends ListaSimple<Tipo> {

    public ListaDoble() {
        super();
    }

    @Override
    public void agregarInicio(Tipo item) {
        super.agregarInicio(item);
    }

    @Override
    public void agregarFinal(Tipo item) {
        super.agregarFinal(item);
    }

    @Override
    public void agregar(Tipo item) {
        super.agregar(item);
    }

    @Override
    public void remover(int posicion) {
        super.remover(posicion);
    }

    @Override
    public void removerInicio() {
        super.removerInicio();
    }

    @Override
    public void removerFinal() {
        super.removerFinal();
    }

    @Override
    public void remover(Tipo item) {
        super.remover(item);
    }

    @Override
    public void limpiar() {
        super.limpiar();
    }

    @Override
    public Tipo getItem(int posicion) {
        return super.getItem(posicion);
    }

    @Override
    public boolean existe(Tipo item) {
        return super.existe(item);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Clase usada para moldear un nodo con doble enlace.
     *
     * @author jhon
     **/
    protected static class NodoDoble<Tipo> extends NodoSimple<Tipo> {

        /** Almacena la referencia del nodo anterior */
        private NodoDoble<Tipo> anterior;

        /** Almacena la referencia del nodo siguiente */
        private NodoDoble<Tipo> siguiente;

        /**
         * Este constructor inicia la referencia anterior y posterior del nodo, ademas inicia el valor del nodo.
         *
         * @param valor     este es el valor que se va a agregar a este nodo.
         * @param anterior  referencia del anterior nodo
         * @param siguiente referencia del siguiente nodo.
         */
        NodoDoble(NodoDoble<Tipo> anterior, Tipo valor, NodoDoble<Tipo> siguiente) {
            super(valor, null);
        }

        /**
         * @return retorna la referencia del siguinete nodo.
         **/
        @Override
        public Nodo<Tipo> getSiguiente() {
            return super.getSiguiente();
        }

        /**
         * Modifica la referencia del sigueinte nodo.
         *
         * @param siguiente nueva referencia del siguiente nodo.
         **/
        @Override
        public void setSiguiente(Nodo<Tipo> siguiente) {
            this.siguiente = (NodoDoble<Tipo>) siguiente;
        }

        /**
         * @return retorna la referencia del nodo anterior.
         **/
        public Nodo<Tipo> getAnterior() {
            return this.anterior;
        }

        /**
         * Modifica la referencia del nodo anterior.
         *
         * @param anterior nueva referencia del nodo anterior.
         **/
        public void setAnterior(Nodo<Tipo> anterior) {
            this.anterior = (NodoDoble<Tipo>) anterior;
        }

        /**
         * limpia las referencias del nodo anterior y posterior ademas de eliminar el valor
         * que contenia el nodo.
         **/
        @Override
        public void destruir() {
            this.siguiente = null;
            this.anterior = null;
            this.setValor(null);
        }
    }

}
