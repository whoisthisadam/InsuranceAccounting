
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Derivative implements Serializable {
    private int dollarsPerYear;
    private ArrayList<InsuranceObligation> listOfObligations;

    public Derivative(ArrayList<InsuranceObligation> listOfObligations, int dollarsPerYear) {
        this.dollarsPerYear = dollarsPerYear;
        this.listOfObligations = listOfObligations;
    }

    public void setDollarsPerYear(int dollarsPerYear) {
        this.dollarsPerYear = dollarsPerYear;
    }

    public int getDollarsPerYear() {
        return dollarsPerYear;
    }

    public void setListOfObligations(ArrayList<InsuranceObligation> listOfObligations) {
        this.listOfObligations = listOfObligations;
    }

    public ArrayList<InsuranceObligation> getListOfObligations() {
        return listOfObligations;
    }

    public void firstFill(int number, Derivative Derivative) {
        int sum = 0;
        for (int i = 0; i < number; i++) {
            InsuranceObligation insurance = new InsuranceObligation("", 0, 0);
            insurance.setID(i + 1);
            int insureType = (int) (Math.random() * 8);
            switch (insureType) {
                case 0 -> insurance.setTitle("Health Insurance");
                case 1 -> insurance.setTitle("Life Insurance");
                case 2 -> insurance.setTitle("Travel Insurance");
                case 3 -> insurance.setTitle("Home Insurance");
                case 4 -> insurance.setTitle("Car Insurance");
                case 5 -> insurance.setTitle("Education Insurance");
                case 6 -> insurance.setTitle("Work Insurance");
                case 7 -> insurance.setTitle("Personal Items Insurance");
                case 8 -> insurance.setTitle("Sports Insurance");
                default -> System.out.println("ERROR WITH GENERATING!!!");
            }
            insurance.setDollarsPerYear(MyRandom.getRandomIntNumber(15000, 100000));
            sum += insurance.getDollarsPerYear();
            insurance.setRiskPercent(MyRandom.getRandomIntNumber(1, 100));
            Derivative.getListOfObligations().add(insurance);
        }
        Derivative.setDollarsPerYear(sum);
    }

    public static void printDerivative(ArrayList<InsuranceObligation> list) {
        for (InsuranceObligation insuranceObligation : list) {
            System.out.println("ID:" + insuranceObligation.getID() + "\nType: " + insuranceObligation.getTitle()
                    + "\nAnnual fee:" + insuranceObligation.getDollarsPerYear() + "$\n" + "Probability of risk:"
                    + insuranceObligation.getRiskPercent() + "%");
        }
    }

    public static void addInsurance (Scanner scan, ArrayList<InsuranceObligation> list, Derivative derivative) {
        scan.nextLine();
        InsuranceObligation newIns = new InsuranceObligation("", 0, 0);
        int newID = list.toArray().length + 1;
        newIns.setID(newID);
        System.out.print("Title:");
        String title = scan.nextLine();
        newIns.setTitle(title);
        System.out.print("How much per year to pay(dollars)? ");
        int newCost;
        while(true) {
            try {
                newCost = scan.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.err.println("Invalid input");
                System.out.println("Enter again");
                scan.nextLine();
            }
        }
        newIns.setDollarsPerYear(newCost);
        System.out.print("What is the probability of risk? Type in percent(0-100). ");
        int newRisk;
        while(true)
        try {
            newRisk = scan.nextInt();
            if(newRisk>100||newRisk<0) throw new PercentException();
            break;
        }
        catch(PercentException|InputMismatchException ex){
            System.err.println("INVALID INPUT");
            System.out.println("Enter again");
            scan.nextLine();
        }
        newIns.setRiskPercent(newRisk);
        list.add(newIns);
        scan.nextLine();
        derivative.setListOfObligations(list);
        int setCost = derivative.getDollarsPerYear() + newCost;
        derivative.setDollarsPerYear(setCost);
    }

    public ArrayList<InsuranceObligation> sortPerRisk(ArrayList<InsuranceObligation> originalList) {
        ArrayList<InsuranceObligation> newList = new ArrayList<>();
        newList = (ArrayList<InsuranceObligation>) originalList.clone();
        for (int i = 0; i < newList.size() - 1; i++) {
            for (int j = i + 1; j < newList.size(); j++) {
                if (newList.get(i).getRiskPercent() < newList.get(j).getRiskPercent()) {
                    Collections.swap(newList, i, j);
                }
            }
        }
        return newList;
    }

    public static ArrayList<InsuranceObligation> inDiapason(ArrayList<InsuranceObligation> originalList, int from, int to) {
        ArrayList<InsuranceObligation> newList = new ArrayList<>();
        for (InsuranceObligation insuranceObligation : originalList) {
            if (insuranceObligation.getDollarsPerYear() >= from && insuranceObligation.getDollarsPerYear() <= to) {
                newList.add(insuranceObligation);
            }
        }
        if (newList.isEmpty()) {
            System.out.println("There are no obligations in your diapason of cost");
        }
        return newList;
    }

    public static void printByDiapason(ArrayList<InsuranceObligation> list) {
        for (InsuranceObligation insuranceObligation : list) {
            System.out.println(insuranceObligation.getTitle() + "(" + insuranceObligation.getDollarsPerYear() + " dollars per year)");
        }
    }
    public static void deleteObligation(Derivative derivative, int ID){
        for(int i=0;i<derivative.getListOfObligations().size();i++){
            if(derivative.getListOfObligations().get(i).getID()==ID){
                derivative.setDollarsPerYear(derivative.getDollarsPerYear()-derivative.getListOfObligations().get(i).getDollarsPerYear());
                derivative.getListOfObligations().remove(i);
                for(int j=i;j<derivative.getListOfObligations().size();j++){
                    derivative.getListOfObligations().get(j).setID(derivative.getListOfObligations().get(j).getID()-1);
                }
            }
        }
    }

    public void writeDataToFile(String filename){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(getListOfObligations());
            System.out.println("Complete!");
        }
        catch(IOException ex){
            System.err.println("Cannot write data to file!");
        }
        System.out.println("");
    }
    public void readDataFromFile(String filename) throws ClassNotFoundException{
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))) {
            setListOfObligations((ArrayList<InsuranceObligation>) ois.readObject());
        }
        catch(IOException ex){
            System.err.println("Cannot read data!");
        }
        System.out.println("");
    }
}