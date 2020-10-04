package com.unsch.collection.dinamicas.lineal;

/**
 * Se usa para construir una lista con nodos de doble enlace.
 * El hecho de que el nodo posea doble enlace, lo hace mas sencillo de trabajar.
 *
 * @author jhon
 * @version 1.0
 **/
public class ListaDoble<Tipo> extends ListaSimple<Tipo> {

    /**
     * Aqui se almacena el primero nodo de la lista.
     **/
    private NodoDoble<Tipo> cabeza;

    /**
     * Constructor por defecto.
     **/
    public ListaDoble() {
        super();
    }

    /**
     * Este metodo coloca el valor pasado por el paramentro dentro de un nodo para luego
     * enlazarse con los nodos de la lista, pero en este caso el nodo se enlazara por la
     * izquierda es decir se colocara al inicio de la lista.
     *
     * @param item elemto que se colocara dentro de un nodo.
     **/
    @Override
    public void agregarInicio(final Tipo item) {
        NodoDoble<Tipo> nuevoNodo = new NodoDoble<>(null,item,null);
        if (this.cabeza != null) {
            this.cabeza.setAnterior(nuevoNodo);
            nuevoNodo.setSiguiente(this.cabeza);
        }
        this.cabeza = nuevoNodo;
        this.incrementarContador();
    }

    /**
     * Este metodo pone el valor dentro de un nodo de doble enlace para luego unirlo al final de la lista.
     * Internamente se usa el ciclo while para llegar hasta el ultimo nodo y asi poder unir el nuevo
     * nodo al finel de la lista.
     *
     * @param item item que sera colocado dentro de un nodo.
     **/
    @Override
    public void agregarFinal(final Tipo item) {
        NodoDoble<Tipo> nuevoNodo = new NodoDoble<>(null,item,null);
        if (this.cabeza != null) {
            NodoDoble<Tipo> temporal = this.cabeza;
            while (temporal.getSiguiente() != null) {
                temporal = (NodoDoble<Tipo>) temporal.getSiguiente();
            }
            temporal.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(temporal);
        } else {
            this.cabeza = nuevoNodo;
        }
        this.incrementarContador();
    }

    /**
     * Este metodo sirve como wrapper para el metodo agrgar final.
     * Este es un metodo que toda estrcutura deberia de soportar, en este caso
     * este metodo lo que hace es llamar al metodo agregarFinal para agregarlo a la lista.
     *
     * @see ListaDoble#agregarFinal(Object)
     * @param item objeto a agregar.
     **/
    @Override
    public void agregar(final Tipo item) {
        this.agregarFinal(item);
    }

    /**
     * Este metodo se encarga de remover al nodo y su valor, para ello se le debe de
     * indicar la posicion del nodo en la lista.
     *
     * Internamente se usa un ciclo while y un contador para posicionarse en el nodo
     * buscado y asi poder removerlo.
     *
     * @param posicion indica la posicion del nodo contenedor del valor.
     **/
    @Override
    public void remover(final int posicion) {
        if (this.cabeza != null) {
            if (posicion < this.getLongitudLista()) {
                if (posicion == 0) {
                    this.removerInicio();
                } else if (posicion == this.getLongitudLista() - 1) {
                    this.removerFinal();
                } else {
                    int contador = 0;
                    NodoDoble<Tipo> temporal = this.cabeza;
                    while (contador < posicion) {
                        temporal = (NodoDoble<Tipo>) temporal.getSiguiente();
                        contador++;
                    }
                    ((NodoDoble<Tipo>) (temporal.getAnterior())).setSiguiente(temporal.getSiguiente());
                    ((NodoDoble<Tipo>) (temporal.getSiguiente())).setAnterior(temporal.getAnterior());
                    temporal.destruir();
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
     * Este metodo se encarga de retirar el primer nodo de la lista.
     * Aqui no se requieren de ciclos para poder remover el primer nodo.
     **/
    @Override
    public void removerInicio() {
        if (this.cabeza != null) {
            NodoDoble<Tipo> temporal = this.cabeza;
            this.cabeza = (NodoDoble<Tipo>) temporal.getSiguiente();
            ((NodoDoble<Tipo>) temporal.getSiguiente()).setAnterior(null);
            temporal.destruir();
            this.decrementarContador();
        } else {
            throw new IndexOutOfBoundsException("No puedes intentar remover elementos de una lista vacia.");
        }
    }

    /**
     * Este metodo se encarga de remover el ultimo nodo contenedor de la lista.
     * Para realizar este proceso es necesario usar el ciclo while para llegar al
     * ultimo nodo de la lista.
     **/
    @Override
    public void removerFinal() {
        if (this.cabeza != null) {
            NodoDoble<Tipo> temporal = this.cabeza;
            while (temporal.getSiguiente() != null) {
                temporal = (NodoDoble<Tipo>) temporal.getSiguiente();
            }
            ((NodoDoble<Tipo>) temporal.getAnterior()).setSiguiente(null);
            temporal.destruir();
            this.decrementarContador();
        } else {
            throw new IndexOutOfBoundsException("No puedes intentar remover elementos de una lista vacia.");
        }
    }

    /**
     * Este metodo se encargara de remover la primera ocurrencia del parametro.
     * Se recorren todos los nodos de la lista buscando algun nodo que contenga el valor
     * pasado por parametro.
     * Si existiesen mas de 2 valores iguales en la lista, solamente se eliminara la primera
     * ocurrencia.
     *
     * @param item elemento a quitarse de la lista.
     **/
    @Override
    public void remover(final Tipo item) {
        if (this.cabeza != null) {
            NodoDoble<Tipo> temporal = this.cabeza;
            while (temporal != null) {
                if (temporal.getValor().equals(item)) {
                    ((NodoDoble<Tipo>) (temporal.getAnterior())).setSiguiente(temporal.getSiguiente());
                    ((NodoDoble<Tipo>) (temporal.getSiguiente())).setAnterior(temporal.getAnterior());
                    temporal.destruir();
                    break;
                } else {
                    temporal = (NodoDoble<Tipo>) temporal.getSiguiente();
                }
            }
            this.decrementarContador();
        } else {
            throw new IndexOutOfBoundsException("No puedes intentar remover elementos de una lista vacia.");
        }
    }

    /**
     * Este metodo se encarga de quitar todos los elementos de la lista.
     * Se recorren todos los nodos de la lista para remover los punteros y el valor
     * contenido dentro de cada nodo.
     * Como ultimos pasos se elimina la cabeza y se reinicia el contador del tamaño de la lista.
     **/
    @Override
    public void limpiar() {
        if (this.cabeza != null) {
            new Thread(() -> {
                NodoDoble<Tipo> temporal = this.cabeza;
                while (temporal != null) {
                    final NodoDoble<Tipo> next = (NodoDoble<Tipo>) temporal.getSiguiente();
                    temporal.destruir();
                    temporal = next;
                }
                this.cabeza = null;
                this.reiniciarContador();
            }).start();
        }
    }

    /**
     * Devuelve el valor de un determinado nodo, Para lograr esto se usa el ciclo while y un
     * contador con el cual nos podremos posicionar en el nodo que buscamos y asi poder obetener
     * su valor y retornarlo.
     *
     * @param posicion indica la posicion en la lista del elemento que se quiere obtener.
     * @return elemento de la posicion establecida.
     **/
    @Override
    public Tipo getItem(final int posicion) {
        if (this.cabeza != null) {
            if (posicion < this.getLongitudLista()) {
                NodoDoble<Tipo> temporal = this.cabeza;
                int counter = 0;
                while (counter < posicion) {
                    temporal = (NodoDoble<Tipo>) temporal.getSiguiente();
                    counter++;
                }
                return temporal.getValor();
            } else {
                throw new IndexOutOfBoundsException(posicion + " fuera de rango.");
            }
        } else {
            throw new IndexOutOfBoundsException("Lista vacia.");
        }
    }

    /**
     * Este metodo se encarga de verificar la exisencia de un valor detro de algun nodo ddentro de la lista.
     * Si el valor bucado se encuantra dentro de algun nodo, se rompe el ciclo while y se retorna true,
     * en caso de no existir dicho valor dentro de la lista el ciclo while continuara hasta llegar al ultimo
     * nodo de la lista y al haber terminado de revisar todos los nodos se retornara false.
     *
     * @param item elemento que se quiere buscar dentro de la lista.
     * @return true si el lemento se encuentra en la lista.
     **/
    @Override
    public boolean existe(final Tipo item) {
        if (this.cabeza != null) {
            boolean existe = false;
            NodoDoble<Tipo> temporal = this.cabeza;
            while (temporal != null) {
                if (temporal.getValor().equals(item)) {
                    existe = true;
                    break;
                } else {
                    temporal = (NodoDoble<Tipo>) temporal.getSiguiente();
                }
            }
            return existe;
        } else {
            return false;
        }
    }

    /**
     * Se recorren todos los nodos de la lista para obtener sus valores y unirlos en una cadena literal
     * haciendo uso del metodo toString() de cada valor del nodo.
     * Para optimizar la memoria se usa la clase StringBuilder y asi unir todas las representacones
     * de los objetos.
     *
     * @return la representacion lineal de los objetos contenidos en la lista.
     **/
    @Override
    public String toString() {
        if (this.cabeza != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            NodoDoble<Tipo> temporal = this.cabeza;
            while (temporal != null) {
                builder.append(temporal.getValor().toString()).append(",").append(" ");
                temporal = (NodoDoble<Tipo>) temporal.getSiguiente();
            }
            builder.append("]");
            return builder.toString().replace(", ]", "]");
        } else {
            return "[]";
        }
    }

    /**
     * Clase usada para moldear un nodo con doble enlace.
     * Este nodo almacena la referencia del nodo que le precede y del que le sucede, ademas
     * de almacenar el valor.
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
            this.anterior = anterior;
            this.siguiente = siguiente;
        }

        /**
         * @return retorna la referencia del siguinete nodo.
         **/
        @Override
        public Nodo<Tipo> getSiguiente() {
            return this.siguiente;
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
