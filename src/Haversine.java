//Classe que serveix per calcular la distancia entre dos punts a traves de la formula de haversine. Els punts són el que introdueix el usuari i l’altre seran les coordenades de tots els gimnasos.
public class Haversine {
    //Constant que emmagatzema el radi equatorial de la terra en km.
    public static final float r=6378;
    //Atribut que emmagatzema la distancia entre dos punts. El resultat de la fórmula de haversine.
    private double distancia;
    //Constructor que ens permet crear objectes d’aquesta classe.
    public Haversine() {
    }
    //Mètode que calcula la distancia entre dos punts situats a la esfera terrestre mitjançant la formula de haversine.
    public void calcularDistancia(double latitud_actual, double longitud_actual, double latitudg, double longitudg){
        double diflat = latitudg - latitud_actual;
        double diflong = longitudg - longitud_actual;

        double a = Math.pow(Math.sin(diflat/2),2) + Math.cos(latitud_actual)*Math.cos(latitudg)*Math.pow(Math.sin(diflong/2),2);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        setDistancia(c*r);
    }
    //Mètode que retorna la distancia entre dos punts.
    public double getDistancia() {
        return distancia;
    }
    //Mètode que s’utilitza per modificar el valor de la distància.
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
