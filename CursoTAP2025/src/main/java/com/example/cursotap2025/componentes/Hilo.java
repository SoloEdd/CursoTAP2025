package com.example.cursotap2025.componentes;

import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;

import java.util.Random;

public class Hilo extends Thread {

    private ProgressBar pgbRuta;
    private Alert alert;

    public Hilo(String nombre, ProgressBar pgbRuta) {
        super(nombre);
        this.pgbRuta = pgbRuta;
    }

    @Override
    public void run() {
        super.run();
        double avance = 0;
        while (avance < 1) {
            avance += Math.random() / 10;
            this.pgbRuta.setProgress(avance);
            try {
                sleep((long) (Math.random()*2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
