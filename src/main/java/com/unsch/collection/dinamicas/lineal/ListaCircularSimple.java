package com.unsch.collection.dinamicas.lineal;

/**
 * Esta clase es usada para crear listas circulares de un enlace simple.
 *
 * @author jhon
 **/
public class ListaCircularSimple<Tipo> extends ListaSimple<Tipo> {

    /** Almacena el primer nodo dentro de la lista */
    private NodoSimple<Tipo> cabeza;

    /**
     * Constructor por defecto
     **/
    public ListaCircularSimple() {
        super();
    }

    /**
     * empty
     **/
    @Override
    public void agregarInicio(final Tipo item) {
        NodoSimple<Tipo> nuevoNodo = new NodoSimple<>(item,null);
        if (this.cabeza != null) {
            nuevoNodo.setSiguiente(this.cabeza);
        } else {
            nuevoNodo.setSiguiente(nuevoNodo);
        }
        this.cabeza = nuevoNodo;
        this.incrementarContador();
    }

    /**
     * empty
     **/
    @Override
    public void agregarFinal(final Tipo item) {
        NodoSimple<Tipo> nuevoNodo = new NodoSimple<>(item,null);
        if (this.cabeza != null) {
            NodoSimple<Tipo> temporal = this.cabeza;
            while (temporal.getSiguiente() != this.cabeza) {
                temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
            }
            temporal.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(this.cabeza);
        } else {
            nuevoNodo.setSiguiente(nuevoNodo);
            this.cabeza = nuevoNodo;
        }
        this.incrementarContador();
    }

    /**
     * empty
     **/
    @Override
    public void agregar(final Tipo item) {
        this.agregarFinal(item);
    }

    /**
     * empty
     **/
    @Override
    public void remover(final int posicion) {
        if(this.cabeza != null) {
            if (posicion < this.getLongitudLista()) {
                if (posicion == 0) {
                    this.removerInicio();
                } else if (posicion == this.getLongitudLista() - 1) {
                    this.removerFinal();
                } else {
                    this.decrementarContador();
                }
            } else {
                throw new IndexOutOfBoundsException(posicion + " fuera de rango.");
            }
        } else {
            throw new IndexOutOfBoundsException("La lista se encuentra vacia.");
        }
    }

    /**
     * empty
     **/
    @Override
    public void removerInicio() {
        if (this.cabeza != null) {
            if (this.getLongitudLista() == 1) {
                this.cabeza.destruir();
                this.cabeza = null;
            } else {
                NodoSimple<Tipo> temporal = this.cabeza;
                while (temporal.getSiguiente() != this.cabeza) {
                    temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
                }
                temporal.setSiguiente(this.cabeza.getSiguiente());
                this.cabeza.setValor(null);
                this.cabeza = (NodoSimple<Tipo>) this.cabeza.getSiguiente();
            }
            this.decrementarContador();
        } else {
            throw new IndexOutOfBoundsException("Lista vacia. No se puede remover elementos");
        }
    }

    /**
     * empty
     **/
    @Override
    public void removerFinal() {
        if (this.cabeza != null) {
            NodoSimple<Tipo> temporal = this.cabeza;
            while (((NodoSimple<Tipo>) temporal.getSiguiente()).getSiguiente() != this.cabeza) {
                temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
            }
            NodoSimple<Tipo> nodoFinal = (NodoSimple<Tipo>) temporal.getSiguiente();
            nodoFinal.destruir();
            temporal.setSiguiente(this.cabeza);
        } else {
            throw new IndexOutOfBoundsException("Lista vacia. No se puede remover elementos.");
        }
    }

    /**
     * empty
     **/
    @Override
    public void remover(final Tipo item) {
        super.remover(item);
    }

    /**
     * empty
     **/
    @Override
    public void limpiar() {
        if (this.cabeza != null) {
            this.cabeza.destruir();
            this.cabeza = null;
            this.reiniciarContador();
        }
    }

    /**
     * empty
     **/
    @Override
    public Tipo getItem(final int posicion) {
        if (this.cabeza != null) {
            if (posicion < this.getLongitudLista()) {
                NodoSimple<Tipo> temporal = this.cabeza;
                int contador = 0;
                while (contador < posicion) {
                    temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
                    contador++;
                }
                return temporal.getValor();
            } else {
                throw new IndexOutOfBoundsException(posicion + " fuera de rango.");
            }
        } else {
            throw new IndexOutOfBoundsException("La lista esta vacia.");
        }
    }

    /**
     * empty
     **/
    @Override
    public boolean existe(final Tipo item) {
        if (this.cabeza != null) {
            NodoSimple<Tipo> temporal = this.cabeza;
            boolean existe = false;
            while (temporal.getSiguiente() != this.cabeza) {
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
     * @return la forma literal de los elementos dentro del la lista.
     **/
    @Override
    public String toString() {
        if (this.cabeza != null) {
            StringBuilder stringShape = new StringBuilder();
            stringShape.append("[");
            NodoSimple<Tipo> temporal = this.cabeza;
            while (temporal.getValor() != this.cabeza.getValor()) {
                stringShape.append(temporal.getValor().toString()).append(", ").append(" ");
                temporal = (NodoSimple<Tipo>) temporal.getSiguiente();
            }
            stringShape.append("]");
            return stringShape.toString().replace(", ]", "]");
        } else {
            return "[]";
        }
    }
}
