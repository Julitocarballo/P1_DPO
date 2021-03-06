import com.google.gson.JsonArray;
import java.text.DecimalFormat;
import java.util.Scanner;
//Classe que s’encarregarà de tota la lògica del joc així com executar i gestionar totes les opcions d’aquest.
public class ExtreureDades {
    //Atribut que permet utilitzar els mètodes de la classe atribut sense necessitat de crear un objecte. Ens permet accedir a tots els menús de la pràctica.
    private Menu menu;
    //Atribut que crea un nou objecte de la classe LlegirJson que ens permetrà emmagatzemar tota la informació dels fitxers Json.
    private LlegirJson llegirjson = new LlegirJson();
    //Array de la classe Pokémon en el qual es guardarà tota la informació de cada pokémon.
    private Pokemon[] pokemons;
    //Array de la classe Pokéball en la qual es guardarà tota la informació de cada tipus de pokéball.
    private Pokeball[] pokeballs;
    //Atribut que emmagatzema un objecte de la classe User que ens permetrà accedir a tots els seus mètodes i obtenir informació sobre el seu inventari, pokémons capturats i monedes.
    private User user;
    //Atribut que emmagatzema tota la informació del json de pokémons llegendaris i mítics que serà emmagatzemada juntament amb el array de pokémons (amb el seu pokémon corresponent).
    private JsonArray legend;
    //Objecte de la classe Haversine que s’utilitzarà per calcular quin gimnàs està més aprop de les nostres coordenades.
    Haversine haversine = new Haversine();
    //Objecte que s’utilitzarà per rebaixar en 2 el numero de decimals que apareix en una variable de tipus float.
    private DecimalFormat df = new DecimalFormat("#0.00");
    //Objecte de la classe captura que s’utilitzarà sempre que es vulgui capturar un pokémon.
    Captura captura = new Captura();
    //Objecte de la classe Html que s’utilitzarà per generar els fitxers html de les opcions 7 i 8.
    Html html = new Html();

    private Scanner sc;
    //Constructor que crea un objecte Scanner per poder introduir informació a traves del teclat quan es creï un objecte d’aquesta classe.
    public ExtreureDades() {
        sc = new Scanner(System.in);
    }

    //Mètode que guarda tota la informació del fitxer poke.json a l’array de pokémons.
    public void setPokemons(){
        this.pokemons = llegirjson.extreurePokemon();
    }
    //Mètode que extreu la informació del fitxer legends.json i la emmagatzema com un array que serà tractat posteriorment.
    public void setLegend(){
        this.legend = llegirjson.extreureArray();
    }
    //Mètode que guarda tota la informació del fitxer poke.json al array de pokéballs.
    public void setPokeballs(){
        this.pokeballs = llegirjson.getPokeball();
    }
    //Mètode que crea un objecte de la classe usuari.
    public void creaUsuari(){
       user = new User(pokeballs.length);
    }
    //Mètode que retorna la ubicació d’un gimnàs d’un pokémon llegendari concret.
    public Location getLocation(Pokemon pokemons){
        Legendary l= (Legendary) pokemons;
        Gym g = l.getGym();
        Location location= g.getLocation();
        return location;
    }
    //Mètode que retorna la informació de un gimnàs d’un pokémon llegendari concret.
    public Gym getGym(Pokemon pokemons){
        Legendary l= (Legendary) pokemons;
        Gym g = l.getGym();
        return g;
    }
    //Mètode que retorna la informació de una recerca especial de un pokémon mític concret.
    public Recerca getRecerca(Pokemon pokemons){
        Mythical mitic = (Mythical) pokemons;
        Recerca r = mitic.getSpecial_research();
        return  r;
    }
    //Mètode que introdueix la informació de un pokémon llegendari al seu pokémon corresponent en el array de pokémons.
    public void afegirLlegendari(int i, int id){
        pokemons[id - 1] = llegirjson.getLlegendari(id, i, pokemons[id - 1], legend);
    }
    //Mètode que introdueix la informació de un pokémon mític al seu pokémon corresponent en el array de pokémons.
    public void afegirMitic(int i, int id){
        pokemons[id - 1] = llegirjson.getMitic(id, i, pokemons[id - 1], legend);
    }

    //Mètode que rep tota la informació corresponent al menú, concretament s’utilitza per veure quina opció ha triat l’usuari, en conseqüència, gracies a un switch fa una crida a l’opció escollida.
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
                opcio7();
                break;

            case 8:
                opcio8();
                break;

            case 9:
                System.out.println("Fi programa.");
                break;
        }
    }
    //Mètode que demana al usuari quantes monedes vol comprar, comprova que el numero introduït sigui correcte, li mostra per pantalla quants diners li costarà adquirir aquestes monedes i li preguntarà al usuari si accepta o no.
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
    //Mètode que mostra al usuari quantes monedes té i tot seguit mostra un menú amb totes les pokéballs que hi ha disponibles. Tot seguit comprova que l’opció triada per l’usuari sigui correcte i li pregunta quantes unitats vol. Si el usuari s’ho pot permetre, el inventari de l’usuari serà actualitzat.
    public void opcio2(Menu menu) {
        char op, lletra;
        int unitats;
        boolean error = false;

        System.out.println(" ");
        System.out.println("Teniu " + user.getMonedes() + " monedes.");
        System.out.println(" ");
        lletra = menu.mostraMenu2(pokeballs);
        op = sc.next().charAt(0);

        if(Character.isLetter(op)){
            if(Character.isUpperCase(op)){
                op = Character.toLowerCase(op);
            }
            if(lletra == op){
                System.out.println("\nFins aviat!\n");
            }else {
                if(lletra<op){
                    System.out.println(("\nError, opcio incorrecte\n"));
                }else {
                    System.out.println("Quantes unitats en vol comprar?");
                    unitats = sc.nextInt();
                    System.out.println(" ");
                    if (unitats < 0) {
                        System.out.println("Error, el nombre d'unitats no pot ser negatiu!");
                        System.out.println(" ");
                    } else {
                        error = actualitzaInventari(unitats, op);
                        if (error) {
                            System.out.println("Ho sentim, però no disposa de suficients monedes");
                            System.out.println(" ");
                        }
                    }
                }
            }
        }else{
            System.out.println(("\nError, opcio incorrecte\n"));
        }

    }
    //Mètode que serveix per afegir el número de pokéballs comprades pel usuari a la quantitat del seu corresponent tipus.
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
    //Mètode que mostra per pantalla de quantes pokéballs de cada tipus disposa el usuari.
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
    //Mètode s’utilitza per capturar un pokémon salvatge i un mític si es dona la ocasió. En primer lloc mira si queden pokéballs disponibles. Tot seguit demanarà al usuari quin pokémon vol capturar i comprovarà que aquest no sigui llegendari ni mític. Tot seguit, si no hi ha hagut errors, es procedirà a capturar el pokémon. Si es capturà es comprovarà si s’ha completat una recerca especial, es a dir, si a d’aparèixer un pokémon mític.
    public void opcio4(){
        sc.nextLine();
        boolean trobat = false, mitic = false, llegendary= false, capturat=false;
        String pokename, pokeballname;
        int pokeid = 0, posiciopoke = 0;

      if(user.getNumPokeballs() == 0){
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
              pokename = pokename.toLowerCase();
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
                         pokeballname = pokeballname.toLowerCase();
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
                         capturat = true;
                         user.afegirPokemonCapturat(pokemons[posiciopoke]);
                         pokeid = apareixerMitic();
                         if(pokeid != -1){
                             System.out.println("Recerca Especial completada: Se t'apareix el mític "+pokemons[pokeid].getName()+"!\n");
                             System.out.println("\nQueden " + user.getNumPokeballs() + " Pokéballs i " + captura.getIntents() + "/5 intents. Quin tipus de Pokéball vol fer servir?");
                             do {
                                 pokeballname = sc.nextLine();
                                 pokeballname = pokeballname.toLowerCase();
                                 if (!existeixPokeball(pokeballname)) {
                                     System.out.println("Aquest tipus no existeix. Quin tipus de Pokéball vol fer servir?");
                                 }
                                 if(user.getInventari()[cercaPokeball(pokeballname)] == 0){
                                     System.out.println("No té Pokéballs d'aquest tipus, quin tipus vol fer servir?");
                                 }
                             } while (!existeixPokeball(pokeballname) || user.getInventari()[cercaPokeball(pokeballname)] == 0);
                             if(captura.capturaPokeMitic(pokemons[pokeid].getCapture_rate(),pokeballs[cercaPokeball(pokeballname)].getCapture_rate())){
                                 System.out.println("El Pokémon " + pokemons[pokeid].getName() + " ha estat capturat!");
                                 System.out.println(" ");
                                 user.afegirPokemonCapturat(pokemons[pokeid]);
                             }
                         }
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
                 }while(user.getNumPokeballs() > 0 && captura.getIntents() > 0 && !capturat);
                 if(user.getNumPokeballs() == 0) {
                     System.out.println("No queden Pokeballs...");
                     System.out.println(" ");
                 }
                 captura.setIntents();
             }
         }
      }
    }
    //Mètode que comprova si s’ha d’aparèixer un pokémon mític quan s’ha capturat un pokémon, tan salvatge com llegendari.
    public int apareixerMitic(){
        int q=0, questcompleta=-1;
        boolean mitic=true;
        for(int i=0; i<pokemons.length;i++){
            if(pokemons[i] instanceof Mythical){
                Recerca r = getRecerca(pokemons[i]);
                for(int z=0; z<r.getQuests().size();z++) {
                    for (int j = 0; j < user.getPokemonsCapturats().size(); j++) {
                        if (user.getPokemonsCapturats().get(j).getId() == r.getQuests().get(z).getTarget()) {
                            q++;
                        }
                    }
                    if(q < r.getQuests().get(z).getQuantity()){
                        mitic=false;
                    }
                    q=0;
                }
                if(mitic && !r.isCompleta()){
                    questcompleta=i;
                    r.setCompleta(true);
                }
            }
            mitic=true;
        }
        return questcompleta;
    }
    //Mètode que comprova si la pokéball triada per el usuari per capturar el pokémon existeix.
    public boolean existeixPokeball(String nompokeball){
        boolean trobat = false;
        for(int i = 0; i < pokeballs.length; i++){
            if(pokeballs[i].getName().equals(nompokeball)){
                trobat = true;
            }
        }
        return trobat;
    }
    //Mètode que retorna la posició de l’array en que es troba la pokéball triada per el usuari.
    public int cercaPokeball(String nompokeball){
        int posiciopokeball = 0;
        for(int i = 0; i < pokeballs.length; i++){
            if(pokeballs[i].getName().equals(nompokeball)){
               posiciopokeball = i;
            }
        }
        return posiciopokeball;
    }
    //Mètode que retorna la posició del array de pokémons en la que es troba el pokémon que vol capturar l’usuari.
    public int cercaPokemon(String nompokemon){
        int posiciopokemon = 0;
        for(int i = 0; i < pokemons.length; i++){
            if(pokemons[i].getName().equals(nompokemon)){
                posiciopokemon = i;
            }
        }
        return posiciopokemon;
    }
    //Mètode que mostra per pantalla si hi ha hagut algun error alhora de buscar el pokémon. Si es llegendari, mític o no existeix.
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
    //Mètode que comprova si la cadena introduïda es numèrica o no.
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    //Mètode que fa que l’usuari introdueixi les seves coordenades i et busca el gimnàs més proper a la teva posició. Tot seguit, si les coordenades s’han introduït correctament, es procedeix a capturar un pokémon llegendari. Si aquest es capturat es tornarà a comprovar si s’ha completat una especial request.
    public void opcio5(){
        boolean capturat=false;
        String pokeballname;
        double dminima=-1;
        int j=0, pokeid=0;
        System.out.println("Latitud actual?");
        double latitud_actual = sc.nextDouble();
        if(latitud_actual<=-90 || latitud_actual>=90){
            System.out.println("Error, latitud incorrecte (-90,90)");
        }else {
            System.out.println("Longitud actual?");
            double longitud_actual = sc.nextDouble();
            sc.nextLine();
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
                                if(user.getInventari()[cercaPokeball(pokeballname)] == 0){
                                    System.out.println("No té Pokéballs d'aquest tipus, quin tipus vol fer servir?");
                                }
                            } while (!existeixPokeball(pokeballname) || user.getInventari()[cercaPokeball(pokeballname)] == 0);
                            System.out.println(" ");
                            if (captura.capturaPokeLegendary(pokemons[j].getCapture_rate(), pokeballs[cercaPokeball(pokeballname)].getCapture_rate())) {
                                System.out.println("El Pokémon " + pokemons[j].getName() + " ha estat capturat!");
                                System.out.println(" ");
                                capturat=true;
                                user.afegirPokemonCapturat(pokemons[j]);
                                pokeid = apareixerMitic();
                                if(pokeid != -1){
                                    System.out.println("Recerca Especial completada: Se t'apareix el mític "+pokemons[pokeid].getName()+"!\n");
                                    System.out.println("\nQueden " + user.getNumPokeballs() + " Pokéballs i " + captura.getIntents() + "/5 intents. Quin tipus de Pokéball vol fer servir?");
                                    do {
                                        pokeballname = sc.nextLine();
                                        if (!existeixPokeball(pokeballname)) {
                                            System.out.println("Aquest tipus no existeix. Quin tipus de Pokéball vol fer servir?");
                                        }
                                        if(user.getInventari()[cercaPokeball(pokeballname)] == 0){
                                            System.out.println("No té Pokéballs d'aquest tipus, quin tipus vol fer servir?");
                                        }
                                    } while (!existeixPokeball(pokeballname) || user.getInventari()[cercaPokeball(pokeballname)] == 0);
                                    if(captura.capturaPokeMitic(pokemons[pokeid].getCapture_rate(),pokeballs[cercaPokeball(pokeballname)].getCapture_rate())){
                                        System.out.println("El Pokémon " + pokemons[pokeid].getName() + " ha estat capturat!");
                                        System.out.println(" ");
                                        user.afegirPokemonCapturat(pokemons[pokeid]);
                                    }
                                }
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
                        }while(user.getNumPokeballs() > 0 && captura.getIntents() > 0 && !capturat);
                        if(user.getNumPokeballs() == 0) {
                            System.out.println("No queden Pokeballs...");
                            System.out.println(" ");
                        }
                        captura.setIntents();
                    }
                }
            }
        }

    }
    //Mètode que mostrarà per pantalla totes les special request que estan en curs. Es recorrerà tot el array de pokémons i es mirarà el estat de les quests de tots els pokémons mítics.
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
    //Mètode que recorrerà tot el array de pokémons i tota la llista de pokémons capturats. Posteriorment generarà un fitxer html que mostrarà el total de pokémons capturats i cada pokémon capturat quantes vegades a estat capturat.
    public void opcio7(){
        html.getPokemons(pokemons);
        html.getPokemonsCapturats(user.getPokemonsCapturats());
        html.fitxerCapturats(user.pokemonsCapturats());

    }
    //Mètode que demanarà al usuari que introdueixi un pokémon, i si aquest existeix generarà un fitxer html amb informació detallada sobre aquest.
    public void opcio8(){
        int pokeid=0, posiciopoke=-1;
        boolean trobat=false;
        System.out.println("De quin Pokemon vol informació?");
        String pokename = sc.nextLine();
        if(isNumeric(pokename)){
            pokeid = Integer.parseInt(pokename);
            for (int i = 0; i < pokemons.length; i++) {
                if (pokemons[i].getId() == pokeid){
                    trobat = true;
                    posiciopoke = i;
                }
            }
        }else{
            for (int i = 0; i < pokemons.length; i++) {
                if (pokemons[i].getName().equals(pokename)) {
                    trobat = true;
                    posiciopoke = i;
                }
            }
        }
        if(!trobat) {
            System.out.println(" ");
            System.out.println("Ho sentim, però aquest Pokémon no existeix (encara).");

        }else{
            html.fitxerDetalls(pokemons[posiciopoke]);
            System.out.println(" ");
            System.out.println("Fitxer HTML generat.");

        }
    }
    //Mètode pertanyent a la opció 6 que controla si una quest en concret s’ha de printar per pantalla o no. Degut a que si aquesta esta en curs o no. Si una missió de una quest esta completa, es mostraran les altres missions.
    public boolean printarRecerca(Pokemon[] pokemons, User user, Recerca r){
        int z=0, q=0, t=0, trues=0;
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
                if((q*100)/r.getQuests().get(j).getQuantity()>0 && (q*100)/r.getQuests().get(j).getQuantity()<=100){
                    printar=true;
                    if((q*100)/r.getQuests().get(j).getQuantity()==100) {
                        trues += 1;
                    }
                }
            }
        }
        if(r.getQuests().size()==trues){
            printar=false;
        }
        return printar;
    }
    //Mètode que retorna el array amb la informació dels pokémons llegendaris i mítics
    public JsonArray getLegend() {
        return legend;
    }

}

