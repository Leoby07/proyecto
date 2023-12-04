package Proyecto;

public class Main {
    public static void main(String[] args) {
        PantallaCarga pantallaCarga = new PantallaCarga();
        pantallaCarga.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Cine v1 = new Cine();
        v1.setVisible(true);
    }
}
