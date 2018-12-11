import com.google.gson.JsonArray;

import java.util.Scanner;

public class ExtreureDades {
    private Menu menu;
    LlegirJson llegirjson = new LlegirJson();
    private Pokemon[] pokemons;
    private Pokeball[] pokeballs;
    private JsonArray legend;

    private Scanner sc;
    public ExtreureDades() {
        sc = new Scanner(System.in);
    }

    public void setPokemons(){
        this.pokemons = llegirjson.extreurePokemon();
    }
    public void setLegend(){
        this.legend = llegirjson.extreureArray();
    }

    public String getPokemons(){
        return pokemons[2].getName();
    }

    public void setPokeballs(){
        this.pokeballs = llegirjson.getPokeball();
    }

    public void afegirLlegendari(int i, int id){
        pokemons[id - 1] = llegirjson.getLlegendari(id, i, pokemons[id - 1], legend);

    }
    public void afegirMitic(int i, int id){
        pokemons[id - 1] = llegirjson.getMitic(id, i, pokemons[id - 1], legend);
    }

    public void execute(int opcio, User user) {

        switch (opcio) {

            case 1:

                opcio1(user);
                break;

            case 2:

                opcio2(menu, user);
                break;

            case 3:

                opcio3(user);
                break;

            case 4:

                opcio4(user);
                break;

            case 5:

                //opcio5();
                break;

            case 6:

                System.out.println("Fi programa.");
                break;
        }
    }

    public void opcio1(User user) {
        int buycoins;
        double preutotal;
        String confirmar;

        do {
            System.out.println("Quantes monedes vol comprar?");
            buycoins = sc.nextInt();
            System.out.println(" ");
            if (buycoins <= 0) {
                System.out.println("Cal introduir un nombre estrictament positiu.");
                System.out.println(" ");
            }
        } while (buycoins <= 0);

        if (buycoins < 250) {
            preutotal = buycoins;
        } else {
            if (buycoins < 500) {
                preutotal = buycoins * 0.9;
            } else {
                if (buycoins < 1000) {
                    preutotal = buycoins * 0.7;
                } else {
                    if (buycoins < 10000) {
                        preutotal = buycoins * 0.5;
                    } else {
                        preutotal = buycoins * 0.25;
                    }
                }
            }
        }
        System.out.println("El preu total és de " + preutotal + "€. Confirma la compra? (Y/N)");
        confirmar = sc.next();
        System.out.println(" ");

        if (confirmar.equalsIgnoreCase("y")) {
            user.setsumarMonedes(buycoins);
            System.out.println(" ");
            System.out.println("S'han afegit " + buycoins + " monedes al seu compte.");
        } else {
            if (confirmar.equalsIgnoreCase("n")) {
                System.out.println("Compra cancel·lada.");
                System.out.println(" ");
            }

        }
    }

    public void opcio2(Menu menu, User user) {
        String op;
        char opcio;
        int unitats;
        boolean error = false;

        System.out.println("Teniu " + user.getMonedes() + " monedes.");
        menu.mostraMenu2();
        op = sc.next();

        if(op.equalsIgnoreCase("a") || op.equalsIgnoreCase("b") || op.equalsIgnoreCase("c") || op.equalsIgnoreCase("d") ){

            System.out.println("Quantes unitats en vol comprar?");
            unitats = sc.nextInt();
            System.out.println(" ");
            if(unitats < 0){
                System.out.println("Error, el nombre d'unitats no pot ser negatiu!");
                System.out.println(" ");
            }else{
                opcio = op.charAt(0);
                switch (opcio) {

                    case 'a':
                        error = actualitzaInventari(unitats, 0, user);
                        break;

                    case 'b':
                        error = actualitzaInventari(unitats, 1, user);
                        break;

                    case 'c':
                        error = actualitzaInventari(unitats, 2, user);
                        break;

                    case 'd':
                        error = actualitzaInventari(unitats, 3, user);
                        break;
                }
                if(error){
                    System.out.println("Ho sentim, però no disposa de suficients monedes");
                    System.out.println(" ");
                }
            }
        }else{
            if(op.equalsIgnoreCase("e")){
                System.out.println("Sortint de l'opció 2");
                System.out.println(" ");
            }else{
                System.out.println("Error, aquesta opció no existeix");
                System.out.println(" ");
            }
        }

    }
    public boolean actualitzaInventari(int unitats, int i, User user){
        boolean error;
        if (unitats * pokeballs[i].getPrice() > user.getMonedes()) {
            error = true;
        }else{
            user.setrestarMonedes(unitats * pokeballs[i].getPrice());
            user.setcomprarInventari(unitats, i);
            System.out.println("S'han afegit " + unitats + pokeballs[i].getName() + " al seu compte a canvi de " + unitats * pokeballs[i].getPrice() + " monedes.");
            System.out.println(" ");
            error = false;
        }
        return error;
    }

    public void opcio3(User user){
        //dubte amb printar el nom de la pokeball ja que no és clavat al enunciat (majúscules, accents..) diferent al json
        System.out.println("Inventari: ");
        for(int i = 0; i < user.getInventari().length; i++){
            if(user.getInventari()[i] != 0){
                System.out.println("    - " + user.getInventari()[i] + "x " + pokeballs[i].getName());
            }
        }

    }

    public void opcio4(User user){
      if(!pokemonDisponible(user)){

          System.out.println("Ho sentim, però no té Pokéballs disponibles, pel que no pot buscar Pokémons.");
          System.out.println(" ");
      }else{
          System.out.println("Quin Pokémon vol buscar?");
      }
    }
    public boolean pokemonDisponible(User user) {
        boolean pokdisp = false;
        for (int i = 0; i < user.getInventari().length && !pokdisp; i++) {
            if (user.getInventari()[i] != 0){
                pokdisp = true;
            }
        }
        return pokdisp;
    }


    public JsonArray getLegend() {
        return legend;
    }
}
