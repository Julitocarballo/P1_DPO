import java.util.Scanner;

public class ExtreureDades {
    private Scanner sc;
    public ExtreureDades() {
        sc = new Scanner(System.in);
    }
    public void execute(int opcio, User user) {

        switch (opcio) {

            case 1:

                opcio1(user);
                break;

            case 2:

                //opcio2();
                break;

            case 3:

                //opcio3();
                break;

            case 4:

                //opcio4();
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
            user.setMonedes(buycoins);
            System.out.println(" ");
            System.out.println("S'han afegit " + buycoins + " monedes al seu compte.");
        } else {
            if (confirmar.equalsIgnoreCase("n")) {
                System.out.println("Compra cancel·lada.");
                System.out.println(" ");
            }

        }
    }

}
