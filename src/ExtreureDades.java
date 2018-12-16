import com.google.gson.JsonArray;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ExtreureDades {
    private Menu menu;
    private Captura captura;
    private LlegirJson llegirjson = new LlegirJson();
    private Pokemon[] pokemons;
    private Pokeball[] pokeballs;
    private User user;
    private JsonArray legend;
    Haversine haversine = new Haversine();
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

    public Location getLocation(Pokemon pokemons){
        Legendary l= (Legendary) pokemons;
        Gym g = l.getGym();
        Location location= g.getLocation();
        return location;
    }
    public Gym getGym(Pokemon pokemons){
        Legendary l= (Legendary) pokemons;
        Gym g = l.getGym();
        return g;
    }
    public Recerca getRecerca(Pokemon pokemons){
        Mythical mitic = (Mythical) pokemons;
        Recerca r = mitic.getSpecial_research();
        return  r;
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

                opcio5();
                break;

            case 6:

                opcio6();
                break;

            case 7:

                break;

            case 8:

                break;

            case 9:
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
        boolean trobat = false, mitic = false, llegendary= false;
        String pokename, pokeballname;
        int pokeid = 0, posiciopoke = 0;
        captura = new Captura();


      if(!pokemonDisponible()){
          System.out.println(" ");
          System.out.println("Ho sentim, però no té Pokéballs disponibles, pel que no pot buscar Pokémons.");
          System.out.println(" ");
      }else{
          System.out.println("Quin Pokémon vol buscar?");
          pokename = sc.nextLine();
          if(isNumeric(pokename)){
             pokeid = Integer.parseInt(pokename);
              for (int i = 0; i < pokemons.length; i++) {
                  if (pokemons[i].getId() == pokeid && !(pokemons[i] instanceof Legendary) && !(pokemons[i] instanceof Mythical)){
                      trobat = true;
                      posiciopoke = i;
                  }else{
                      if(pokemons[i].getId() == pokeid && (pokemons[i] instanceof Mythical)){
                        trobat = true;
                        mitic = true;
                      }
                      if(pokemons[i].getId() == pokeid && (pokemons[i] instanceof Legendary)){
                          trobat = true;
                          llegendary = true;
                      }
                  }
              }
          }else{
              for (int i = 0; i < pokemons.length; i++) {
                  if (pokemons[i].getName().equals(pokename) && !(pokemons[i] instanceof Legendary) && !(pokemons[i] instanceof Mythical)) {
                      trobat = true;
                      posiciopoke = i;
                  }else{
                      if(pokemons[i].getName().equals(pokename) && (pokemons[i] instanceof Mythical)){
                          trobat = true;
                          mitic = true;
                      }
                      if(pokemons[i].getName().equals(pokename) && (pokemons[i] instanceof Legendary)){
                          trobat = true;
                          llegendary = true;
                      }
                  }
              }
          }
         controlErrorsop4(trobat, llegendary, mitic);
         if(trobat && !mitic && !llegendary) {
             System.out.println("Un " + pokemons[posiciopoke].getName() + " salvatge aparagué!");
             System.out.println(" ");
             if(user.getNumPokeballs() == 0){
                 System.out.println("No queden Pokeballs...");
                 System.out.println(" ");
             }else{
                 do {
                     System.out.println("Queden " + user.getNumPokeballs() + " Pokéballs i " + captura.getIntents() + "/5 intents. Quin tipus de Pokéball vol fer servir?");
                     do {
                         pokeballname = sc.nextLine();
                         if (!existeixPokeball(pokeballname)) {
                             System.out.println("Aquest tipus no existeix. Quin tipus de Pokéball vol fer servir?");
                         }
                         if(user.getInventari()[cercaPokeball(pokeballname)] == 0){
                             System.out.println("No té Pokéballs d'aquest tipus, quin tipus vol fer servir?");
                         }
                     } while (!existeixPokeball(pokeballname) || user.getInventari()[cercaPokeball(pokeballname)] == 0);
                     System.out.println(" ");
                     if (captura.capturaPokeSalvatge(pokemons[cercaPokemon(pokename)].getCapture_rate(), pokeballs[cercaPokeball(pokeballname)].getCapture_rate())) {
                         System.out.println("El Pokémon " + pokemons[posiciopoke].getName() + " ha estat capturat!");
                         System.out.println(" ");
                         user.afegirPokemonCapturat(pokemons[posiciopoke]);
                     } else {
                         System.out.println("La " + pokeballs[cercaPokeball(pokeballname)].getName() + " ha fallat!");
                         System.out.println(" ");
                     }
                     user.gastarPokeball(cercaPokeball(pokeballname));
                     captura.restaIntents();
                     if(captura.getIntents() == 0) {
                         System.out.println("El " + pokemons[posiciopoke].getName() + " ha escapat...");
                         System.out.println(" ");
                     }
                 }while(user.getNumPokeballs() > 0 && captura.getIntents() > 0 && !captura.capturaPokeSalvatge(pokemons[cercaPokemon(pokename)].getCapture_rate(), pokeballs[cercaPokeball(pokeballname)].getCapture_rate()));
                 if(user.getNumPokeballs() == 0) {
                     System.out.println("No queden Pokeballs...");
                     System.out.println(" ");
                 }
             }

         }
      }
    }

    public boolean existeixPokeball(String nompokeball){
        boolean trobat = false;
        for(int i = 0; i < pokeballs.length; i++){
            if(pokeballs[i].getName().equals(nompokeball)){
                trobat = true;
            }
        }
        return trobat;
    }
    public int cercaPokeball(String nompokeball){
        int posiciopokeball = 0;
        for(int i = 0; i < pokeballs.length; i++){
            if(pokeballs[i].getName().equals(nompokeball)){
               posiciopokeball = i;
            }
        }
        return posiciopokeball;
    }
    public int cercaPokemon(String nompokemon){
        int posiciopokemon = 0;
        for(int i = 0; i < pokemons.length; i++){
            if(pokemons[i].getName().equals(nompokemon)){
                posiciopokemon = i;
            }
        }
        return posiciopokemon;
    }

    public void controlErrorsop4(boolean trobat, boolean llegendary, boolean mitic){
        if (trobat && mitic){
            System.out.println(" ");
            System.out.println("Ho sentim, però aquest Pokémon és mític.");
            System.out.println(" ");
        }
        if (trobat && llegendary){
            System.out.println(" ");
            System.out.println("Ho sentim, però aquest Pokémon és llegendari.");
            System.out.println(" ");
        }
        if (!trobat){
            System.out.println(" ");
            System.out.println("Ho sentim, però aquest Pokémon no existeix(encara).");
            System.out.println(" ");
        }
    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void opcio5(){
        String pokeballname;
        double dminima=-1;
        int j=0;
        System.out.println("Latitud actual?");
        double latitud_actual = sc.nextDouble();
        if(latitud_actual<=-90 || latitud_actual>=90){
            System.out.println("Error, latitud incorrecte (-90,90)");
        }else {
            System.out.println("Longitud_actual");
            double longitud_actual = sc.nextDouble();
            if(longitud_actual<=-180 || longitud_actual>=180) {
                System.out.println("Error, longitud incorrecte (-180,180)");
            }else {
                for (int i = 0; i < pokemons.length; i++) {
                    if (pokemons[i] instanceof Legendary) {
                        Location l = getLocation(pokemons[i]);
                        haversine.calcularDistancia(latitud_actual, longitud_actual, l.getLatitude(), l.getLongitude());
                        if(dminima==-1 || dminima>haversine.getDistancia()){
                            dminima=haversine.getDistancia();
                            j=i;
                        }
                    }
                }
                if(dminima==-1){
                    System.out.println("No s'ha trobat cap gimnas");
                }else{
                    Gym g= getGym(pokemons[j]);
                    System.out.println("Gimnas mes proper: "+g.getName()+". Comencant raid...\n");
                    System.out.println("El boss de raid "+pokemons[j].getName()+" us repta!\n");
                    if(user.getNumPokeballs() == 0){
                        System.out.println("No queden Pokeballs...");
                        System.out.println(" ");
                    }else{
                        do {
                            System.out.println("Queden " + user.getNumPokeballs() + " Pokéballs i " + captura.getIntents() + "/5 intents. Quin tipus de Pokéball vol fer servir?");
                            do {
                                pokeballname = sc.nextLine();
                                if (!existeixPokeball(pokeballname)) {
                                    System.out.println("Aquest tipus no existeix. Quin tipus de Pokéball vol fer servir?");
                                }
                            } while (!existeixPokeball(pokeballname));
                            System.out.println(" ");
                            if (captura.capturaPokeLegendary(pokemons[j].getCapture_rate(), pokeballs[cercaPokeball(pokeballname)].getCapture_rate())) {
                                System.out.println("El Pokémon " + pokemons[j].getName() + " ha estat capturat!");
                                System.out.println(" ");
                                user.afegirPokemonCapturat(pokemons[j]);
                            } else {
                                System.out.println("La " + pokeballs[cercaPokeball(pokeballname)].getName() + " ha fallat!");
                                System.out.println(" ");
                            }
                            user.gastarPokeball(cercaPokeball(pokeballname));
                            captura.restaIntents();
                            if(captura.getIntents() == 0) {
                                System.out.println("El " + pokemons[j].getName() + " ha escapat...");
                                System.out.println(" ");
                            }
                        }while(user.getNumPokeballs() > 0 && captura.getIntents() > 0);
                        if(user.getNumPokeballs() == 0) {
                            System.out.println("No queden Pokeballs...");
                            System.out.println(" ");
                        }
                    }
                }
            }
        }

    }

    public void opcio6(){
        int z=0, t=0, q=0, primer=0;
        System.out.println("Recerques Especials:\n");
        for(int i =0; i<pokemons.length; i++){
            if(pokemons[i] instanceof Mythical){
                Recerca r = getRecerca(pokemons[i]);
                if(printarRecerca(pokemons, user, r)) {
                    System.out.println("- " + r.getName() + " (" + pokemons[i].getName() + "):");
                    for (int j = 0; j < r.getQuests().size(); j++) {
                        z = 0;
                        while (r.getQuests().get(j).getTarget() != pokemons[z].getId()) {
                            z++;
                        }
                        q = 0;
                        for (t = 0; t < user.getPokemonsCapturats().size(); t++) {

                            if (pokemons[z].getId() == user.getPokemonsCapturats().get(t).getId()) {
                                q++;
                            }
                        }
                        if ((q * 100) / r.getQuests().get(j).getQuantity() < 100) {
                            System.out.println("\t\t* Capturar " + pokemons[z].getName() + "s: " + q + "/" + r.getQuests().get(j).getQuantity() + " (" + (q * 100) / r.getQuests().get(j).getQuantity() + "%)");
                        }
                    }
                }
            }
        }
    }
    public boolean printarRecerca(Pokemon[] pokemons, User user, Recerca r){
        int z=0, q=0, t=0;
        boolean printar=false;
        for(int j=0; j<r.getQuests().size();j++){
            z=0;
            while(r.getQuests().get(j).getTarget() != pokemons[z].getId()){
                z++;
            }
            q=0;
            for(t=0;t<user.getPokemonsCapturats().size();t++) {

                if (pokemons[z].getId() == user.getPokemonsCapturats().get(t).getId()) {
                    q++;
                }
            }
            if(printar==false){
                if((q*100)/r.getQuests().get(j).getQuantity()>0 && (q*100)/r.getQuests().get(j).getQuantity()<100){
                    printar=true;
                }
            }
        }
        return printar;
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

