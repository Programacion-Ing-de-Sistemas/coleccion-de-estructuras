package com.unsch.collection;

import com.unsch.collection.dinamicas.lineal.ListaDoble;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestListaDoble {

    public TestListaDoble() {/* empty */}

    @Test
    public void testAgregarInicio() {
        ListaDoble<Integer> integers = this.crearListaEnteros(10);
        Integer numero = 25;
        integers.agregarInicio(numero);
        System.out.println(integers.toString());
        assertEquals(numero, integers.getItem(0));
    }

    @Test
    public void testAgregarFinal() {
        ListaDoble<Integer> integers = this.crearListaEnteros(15);
        Integer numero = 50;
        integers.agregarFinal(numero);
        System.out.println(integers);
        assertEquals(numero, integers.getItem(integers.getLongitudLista() - 1));
    }

    @Test
    public void testRemoverInicio() {
        ListaDoble<Integer> integers = this.crearListaEnteros(13);
        Integer numero = 78;
        integers.agregarInicio(numero);
        System.out.println(integers);
        integers.removerInicio();
        System.out.println(integers);
        assertNotEquals(numero,integers.getItem(0));
    }

    @Test
    public void removerPosicionEspecifica() {
        ListaDoble<String> strings = this.crearListaString(15);
        String elemento = strings.getItem(10);
        strings.remover(10);
        assertFalse(strings.existe(elemento));
    }

    @Test
    public void testRemoverFinal() {
        ListaDoble<Integer> integers = this.crearListaEnteros(10);
        Integer numero = 78;
        integers.agregarFinal(numero);
        System.out.println(integers);
        integers.removerFinal();
        System.out.println(integers);
        assertNotEquals(numero,integers.getItem(integers.getLongitudLista() - 1));
    }

    @Test
    public void testRemoverElemento() {
        ListaDoble<String> strings = this.crearListaString(15);
        String elemento = strings.getItem(10);
        strings.remover(elemento);
        assertFalse(strings.existe(elemento));
    }

    @Test
    public void obtenerElemento() {
        ListaDoble<String> strings = this.crearListaString(20);
        System.out.println(strings);
        System.out.println(strings.getItem(19));
        assertNotNull(strings.getItem(19));
    }

    @Test
    public void testLimpiar() {
        ListaDoble<String> strings = this.crearListaString(5);
        System.out.println(strings);
        strings.limpiar();
        System.out.println(strings);
        assertEquals(0,strings.getLongitudLista());
    }

    @Test
    public void testExisteElemento() {
        ListaDoble<String> strings = this.crearListaString(5);
        System.out.println(strings);
        assertTrue(strings.existe("element 3"));
    }

    public ListaDoble<Integer> crearListaEnteros(final int cantidad) {
        ListaDoble<Integer> numeros = new ListaDoble<>();
        for (int i = 0; i < cantidad; i++) {
            numeros.agregar(i);
        }
        return numeros;
    }

    public ListaDoble<String> crearListaString(final int cantidad) {
        ListaDoble<String> strings = new ListaDoble<>();
        for (int i = 0; i < cantidad; i++) {
            strings.agregar("element " + String.valueOf(i));
        }
        return strings;
    }

}
