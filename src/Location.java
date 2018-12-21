//Aquesta classe emmagatzema les coordenades del gimnàs.
public class Location {
    //Atribut que serveix per emmagatzemar la coordenada longitud de un gimnàs.
    private double longitude;
    //Atribut que serveix per emmagatzemar la coordenada latitud de un gimnàs.
    private double latitude;
    //Constructor que et permet crear objectes d’aquesta classe
    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    //Mètode que retorna la coordenada longitud de un gimnàs, aquesta serà utilitzada en la opció 5.
    public double getLongitude() {
        return longitude;
    }
    //Mètode que retorna la coordenada latitud de un gimnàs, aquesta serà utilitzada en la opció 5.
    public double getLatitude() {
        return latitude;
    }

}
