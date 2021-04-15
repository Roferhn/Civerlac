/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civerlac;

/**
 *
 * @author khaleb
 */
public class producto {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPreciol() {
        return preciol;
    }

    public void setPreciol(double preciol) {
        this.preciol = preciol;
    }
    int  id,stock;
    String nombre;
    double preciol;
}
