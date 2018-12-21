import com.google.gson.JsonArray;
/**
 * Classe procediment principal de la pràctica.
 *
 * @author Juli Carballo i Arnaud Arenz
 * @version 1.0
 * @since 1.0
 */

public final class Main {

    public static void main(String args[]) {
        LlegirJson json = new LlegirJson();
        Menu menu = new Menu();
        ExtreureDades extreuDades = new ExtreureDades();


        extreuDades.setPokemons();
        extreuDades.setLegend();
        extreuDades.setPokeballs();
        extreuDades.creaUsuari();
        JsonArray jsonllegenmitics = extreuDades.getLegend();

        for (int i = 0; i < jsonllegenmitics.size(); i++) {
            int id = json.extreureid(jsonllegenmitics, i);
            boolean kind = json.llegendariMitic(jsonllegenmitics, i);
            if (kind) {
                extreuDades.afegirLlegendari(i, id);

            } else {
                extreuDades.afegirMitic(i, id);

            }
        }
        do {

            do {

                menu.mostraMenu();
            } while (menu.demanaOpcio());

            extreuDades.execute(menu);

        } while (menu.continua());

    }

}
