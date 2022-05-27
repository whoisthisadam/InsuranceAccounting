import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws ClassNotFoundException {
        Derivative list = new Derivative(new ArrayList<>(), 0);
        list.firstFill(10, list);
        System.out.println("Here is the derivative we have:");
        Derivative.printDerivative(list.getListOfObligations());
        String menu="""
                    1-Print all obligations
                    2-Add new obligation
                    3-See the derivative sorted by risk level(High-Low)
                    4-See obligations in your diapason cost
                    5-Delete obligation
                    6-Write all data in file
                    7-Read data from file
                    0-FINISH WORK
                    """;
        int choose;
        boolean finish = false;
        Scanner scan = new Scanner(System.in);
        while (!finish) {
            System.out.println(menu);
            while(true) {
                try {
                    choose = scan.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.err.println("INVALID INPUT");
                    System.out.println("Re-enter");
                    scan.nextLine();
                }
            }
            switch (choose) {
                case 0 -> {
                    scan.nextLine();
                    System.out.println("""
                            Are you sure you want to finish your work and exit?
                            To finish press ENTER
                            To get back to menu press any other button""");
                    String exit = scan.nextLine();
                    if (exit.equals("")) {
                        finish=true;
                    }
                }
                case 1->{
                    System.out.println("Print all obligations.....");
                    Derivative.printDerivative(list.getListOfObligations());
                    System.out.println("Cost per year for all obligations:"+list.getDollarsPerYear());
                }
                case 2 -> {
                    System.out.println("Add new obligation...");
                    Derivative.addInsurance(scan, list.getListOfObligations(), list);
                    System.out.println("Obligation added!");
                }
                case 3 -> {
                    System.out.println("Sort derivative by risk level...");
                    ArrayList<InsuranceObligation> newList = list.sortPerRisk(list.getListOfObligations());
                    System.out.println("Sorted derivative:");
                    Derivative.printDerivative(newList);
                }
                case 4 -> {
                    System.out.println("Find obligations in your custom diapason...");
                    int from, to;
                    while(true) {
                        try {
                            while (true) {
                                try {
                                    System.out.print("FROM:");
                                    from = scan.nextInt();
                                    break;
                                } catch (InputMismatchException ex) {
                                    System.err.println("Invalid input");
                                    System.out.println("");
                                    System.out.println("Try again");
                                    scan.nextLine();
                                }
                            }
                            while (true) {
                                try {
                                    System.out.print("TO:");
                                    to = scan.nextInt();
                                    break;
                                } catch (InputMismatchException ex) {
                                    System.err.println("Invalid input");
                                    System.out.println("");
                                    System.out.println("Try again");
                                    scan.nextLine();
                                }
                            }

                            if(to<from) throw new ToIsLessThanFromException();
                            break;
                        }
                        catch (ToIsLessThanFromException exex){
                            scan.nextLine();
                            System.err.println("<TO> has to be more that <FROM>");
                            System.out.println("Re-enter");
                            scan.nextLine();
                        }
                    }
                    ArrayList<InsuranceObligation> diapasonList = new ArrayList<>();
                    diapasonList = Derivative.inDiapason(list.getListOfObligations(), from, to);
                    Derivative.printByDiapason(diapasonList);
                }
                case 5->{
                    System.out.println("Delete obligation.....");
                    System.out.println("Enter an ID of an obligation you want to delete");
                    int ID;
                    while(true) {
                        try {
                            ID= scan.nextInt();
                            if(ID<=0||ID>=list.getListOfObligations().toArray().length)throw new ArrayIndexOutOfBoundsException();
                            break;
                        } catch (ArrayIndexOutOfBoundsException | InputMismatchException ex) {
                            ex.printStackTrace();
                            System.err.println("Error!!!Invalid input!\n<<<ID=[1,"+
                                    list.getListOfObligations().toArray().length+"]>>>");
                            System.out.println("Try again");
                            scan.nextLine();
                        }
                    }
                            Derivative.deleteObligation(list, ID);

                    System.out.println("Obligation deleted!");
                }
                case 6->{
                    list.writeDataToFile("Insurance.txt");
                }
                case 7->{
                    list.readDataFromFile("Insurance.txt");
                }
                default -> {
                    System.err.println("WRONG NUMBER!------------------->");
                    System.out.println("Re-enter");
                }
            }
        }
    }
}
