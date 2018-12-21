
//Classe que emmagatzema tota la informació del gimnàs de un pokémon llegendari concret.
public class Gym {
    //Atribut que guardarà el nom del gimnàs del seu corresponent pokémon llegendari.
    private String name;
    //Atribut que emmagatzemarà la informació de la localització del gimnàs del pokémon llegendari corresponent.
    private Location location;
    //Constructor que guardarà tota la informació del gimnàs de un pokémon quan es creï l’objecte.
    public Gym(String name, Location location) {
        this.name = name;
        this.location = location;
    }
    //Mètode que retorna el nom del gimnàs.
    public String getName() {
        return name;
    }
    //Mètode que retorna la informació corresponent a la localització del gimnàs
    public Location getLocation() {
        return location;
    }

}
