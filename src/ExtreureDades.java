import com.google.gson.JsonArray;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ExtreureDades {
    private Menu menu;
    private LlegirJson llegirjson = new LlegirJson();
    private Pokemon[] pokemons;
    private Pokeball[] pokeballs;
    //User user = new User(pokeballs.length);
    private User user;
    private JsonArray legend;
    private DecimalFormat df = new DecimalFormat("#0.00");

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

    public Pokeball getPokeball(int i){
        return pokeballs[i];
    }

    public void setPokeballs(){
        this.pokeballs = llegirjson.getPokeball();
    }

    public void creaUsuari(){
       user = new User(pokeballs.length);
    }

//TODO CAMBIAR ID -1 PER I
    public void afegirLlegendari(int i, int id){
        pokemons[id - 1] = llegirjson.getLlegendari(id, i, pokemons[id - 1], legend);
    }
    public void afegirMitic(int i, int id){
        pokemons[id - 1] = llegirjson.getMitic(id, i, pokemons[id - 1], legend);
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void execute(Menu menu) {
        int opcio = menu.getOpcio();
        switch (opcio) {

            case 1:

                opcio1();
                break;

            case 2:

                opcio2(menu);
                break;

            case 3:

                opcio3();
                break;

            case 4:

                opcio4();
                break;

            case 5:

                //opcio5();
                break;

            case 6:

                System.out.println("Fi programa.");
                break;
        }
    }

    public void opcio1() {
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
        System.out.println("El preu total és de " + df.format(preutotal/100) + "€. Confirma la compra? (Y/N)");
        confirmar = sc.next();
        System.out.println(" ");

        if (confirmar.equalsIgnoreCase("y")) {
            user.setsumarMonedes(buycoins);
            System.out.println("S'han afegit " + buycoins + " monedes al seu compte.");
            System.out.println(" ");
        } else {
            if (confirmar.equalsIgnoreCase("n")) {
                System.out.println("Compra cancel·lada.");
                System.out.println(" ");
            }

        }
    }

    public void opcio2(Menu menu) {
        char op;
        int unitats;
        boolean error = false;

        System.out.println(" ");
        System.out.println("Teniu " + user.getMonedes() + " monedes.");
        System.out.println(" ");
        menu.mostraMenu2(pokeballs);
        op = sc.next().charAt(0);

        if(Character.isLetter(op)){
            if(Character.isUpperCase(op)){
                op = Character.toLowerCase(op);
            }
            System.out.println("Quantes unitats en vol comprar?");
            unitats = sc.nextInt();
            System.out.println(" ");
            if(unitats < 0){
                System.out.println("Error, el nombre d'unitats no pot ser negatiu!");
                System.out.println(" ");
            }else{
                error = actualitzaInventari(unitats, op);
                if(error){
                    System.out.println("Ho sentim, però no disposa de suficients monedes");
                    System.out.println(" ");
                }
            }
        }else{
            if(op == 'e'){
                System.out.println("Sortint de l'opció 2");
                System.out.println(" ");
            }else{
                System.out.println("Error, aquesta opció no existeix");
                System.out.println(" ");
            }
        }

    }
    public boolean actualitzaInventari(int unitats, char op){
        boolean error;
        if (unitats * pokeballs[op-97].getPrice() > user.getMonedes()) {
            error = true;
        }else{
            user.setrestarMonedes(unitats * pokeballs[op-97].getPrice());
            user.setcomprarInventari(unitats, op-97);
            System.out.println("S'han afegit " + unitats + " " + pokeballs[op-97].getName() + " al seu compte a canvi de " + unitats * pokeballs[op-97].getPrice() + " monedes.");
            System.out.println(" ");
            error = false;
        }
        return error;
    }

    public void opcio3(){
        //dubte amb printar el nom de la pokeball ja que no és clavat al enunciat (majúscules, accents..) diferent al json
        System.out.println("Inventari: ");
        for(int i = 0; i < user.getInventari().length; i++){
            if(user.getInventari()[i] != 0){
                System.out.println("    - " + user.getInventari()[i] + "x " + pokeballs[i].getName());
            }
        }
        System.out.println(" ");

    }

    public void opcio4(){
      if(!pokemonDisponible()){

          System.out.println("Ho sentim, però no té Pokéballs disponibles, pel que no pot buscar Pokémons.");
          System.out.println(" ");
      }else{
          System.out.println("Quin Pokémon vol buscar?");
      }
    }
    public boolean pokemonDisponible() {
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
