public class Haversine {
    public static final float r=6378;
    private double distancia;
    public Haversine() {
    }

    public void calcularDistancia(float latitud_actual, float longitud_actual, float latitudg, float longitudg){
        float diflat = latitudg - latitud_actual;
        float diflong = longitudg - longitud_actual;

        double a = Math.pow(Math.sin(diflat/2),2) + Math.cos(latitud_actual)*Math.cos(latitudg)*Math.pow(Math.sin(diflong/2),2);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        setDistancia(c*r);
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
