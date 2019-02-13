import java.util.Scanner;

public class Menu {

    private static final int MIN_MENU = 1;
    private static final int MAX_MENU = 9;
    private Scanner sc;
    private int opcio;
    private LlegirJson llegirJson;

    public Menu() {
        sc = new Scanner(System.in);
        opcio = -1;
    }
    public int getOpcio(){
        return opcio;
    }

    public void setOpcion(int opcio) {
        this.opcio = opcio;
    }

    public void mostraMenu() {

        System.out.println("1. Afegir Monedes");
        System.out.println("2. Comprar Objectes");
        System.out.println("3. Consultar Inventari");
        System.out.println("4. Buscar Pokémon Salvatge");
        System.out.println("5. Fer Raid");
        System.out.println("6. Recerques Especials Actuals");
        System.out.println("7. Informe de Capturats ");
        System.out.println("8. Informació Detallada");
        System.out.println("9. Sortir\n\n");


    }

    public boolean demanaOpcio(){
        int opcio;
        try {

            System.out.print("Seleccioni una opcio: ");

            opcio = sc.nextInt();

            if (opcio >= MIN_MENU && opcio <= MAX_MENU) {

                this.opcio = opcio;
                return false;
            }else{
                System.out.println("Error, l'opció ha d'estar entre 1 i 9.");
                System.out.println(" ");
                return true;
            }
        }catch (java.util.InputMismatchException e){
            System.out.println("Error, l'opció ha de ser un enter.");
            System.out.println(" ");
            sc.next();
            return true;
        }
    }

    public boolean continua () {

        if (getOpcio() != 9) {
            return true;
        }
        return false;
    }

    public char mostraMenu2(Pokeball[] pokeballs){
        char lletra = 97;
        System.out.println("Pokéballs disponibles:");
        for(int i = 0; i < pokeballs.length && lletra < 123; i++){
            System.out.println("    " + lletra + ") " + pokeballs[i].getName() + ":     " + pokeballs[i].getPrice() + " monedes");
            lletra++;
        }
        System.out.println(" ");
        System.out.println("    " + lletra + ") Sortir sense comprar");
        System.out.println(" ");
        System.out.println("Esculli una opció:");
        return lletra;
    }

}
