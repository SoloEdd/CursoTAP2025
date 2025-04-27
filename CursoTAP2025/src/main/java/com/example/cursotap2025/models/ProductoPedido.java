package com.example.cursotap2025.models;

public class ProductoPedido {
    private ProductoDAO producto;
    private int cantidad;

    public ProductoPedido(ProductoDAO producto) {
        this.producto = producto;
        this.cantidad = 1;
    }


    public ProductoDAO getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
