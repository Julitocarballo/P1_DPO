import java.util.Scanner;

public final class Menu {
    private Scanner sc;
    private String opcio;
    private int opcion;
    public Menu() {
        sc = new Scanner(System.in);
        opcio = "-";
    }
    public int getOpcio(){
        return opcion;
    }

    public void setOpcion(int opcionn) {
        opcion = opcionn;
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
        System.out.println("Seleccioni una opcio:");
        opcio = sc.nextLine();
        int error=0;
        switch(opcio){
            case "1":
               opcion = 1;
               setOpcion(opcion);
               break;
            case "2":
                opcion = 2;
                setOpcion(opcion);
                break;
            case "3":
                opcion = 3;
                setOpcion(opcion);
                break;
            case "4":
                opcion = 4;
                setOpcion(opcion);
                break;
            case "5":
                opcion = 5;
                setOpcion(opcion);
                break;
            case "6":
                opcion = 6;
                setOpcion(opcion);
                break;
            case "7":
                opcion = 7;
                setOpcion(opcion);
                break;
            case "8":
                opcion = 8;
                setOpcion(opcion);
                break;
            case "9":
                opcion = 9;
                setOpcion(opcion);
                break;
            default:
                error=1;
                break;
        }
        return (error!=0)?false:true;

    }
    public boolean continua () {

        if (getOpcio() != 9) {
            return true;
        }
        return false;
    }

}
