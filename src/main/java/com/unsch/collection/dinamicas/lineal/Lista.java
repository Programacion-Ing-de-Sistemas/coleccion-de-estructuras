package com.unsch.collection.dinamicas.lineal;

/**
 * Esta clase es el molde para clases de tipo lista.
 *
 * @author jhon
 * @author fernando
 **/
public abstract class Lista<Tipo> {

    /** Aqui se almacena la longitud de la lista */
    private int longitudLista;

    /**
     * Este es el contructor por defecto.
     * Aqui inicializamos el contador de la lista en cero.
     **/
    public Lista() {
        this.longitudLista = 0;
    }

    /**
     * Este metodo se debe de implementar para poder agregar elementos a la lista.
     * @param item este es un objeto generico que se va a agregar a la lista.
     **/
    public abstract void agregar(Tipo item);

    /**
     * Este metodo se debe de implementar para poder remover un elemento de la lista.
     * @param posicion este parametro indica la posicion en la lista del elemento que se va a remover.
     **/
    public abstract void remover(int posicion);

    /**
     * Este metodo se debe de implementar para poder remover determinado elemento.
     * @param objeto este objeto de de ser un elemento que se encuentre en la lista,
     *               si el objeto se encuentra en la lista se procedera a retirarlo.
     **/
    public abstract void remover(Tipo objeto);

    /**
     * Este metodo se implementa para poder limpiar la lista, es decir retirar todos los elementos.
     **/
    public abstract void limpiar();

    /**
     * Este metodo se implementa para poder obtener un elemento de la lista de una determinada posicion.
     * @param posicion este numero indica la ubicacion del elemento en la lista.
     **/
    public abstract Tipo getItem(int posicion);

    /**
     * Este metodo se implementa para poder verificar que la lista contenga un determinado elemento.
     * @param item se usara para poder verificar que la lista li¿o contenga.
     **/
    public abstract boolean existe(Tipo item);

    /**
     * @return devuelve la cantidad de elementos existentes en la lista.
     **/
    public int getLongitudLista() {
        return this.longitudLista;
    }

    /**
     * Este metodo incrementa en uno el contador de elementos de la lista.
     **/
    protected void incrementarContador() {
        this.longitudLista += 1;
    }

    /**
     * Este metodo decreneta en uno el contador de elementos de la lista.
     **/
    protected void decrementarContador() {
        this.longitudLista -= 1;
    }

    protected static class Nodo<Tipo> {
        private Tipo valor;
        Nodo(Tipo valor) {
            this.valor = valor;
        }

        public Tipo getValor() {
            return this.valor;
        }

        public void setValor(Tipo valor) {
            this.valor = valor;
        }
    }

}
