import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Contact> contactList = new ArrayList<Contact>();

    static {

        try(Scanner readFile = new Scanner(new BufferedReader(new FileReader("contacts.txt"))) ){

            ArrayList<String> contactListStrings = new ArrayList<String>();

            while (readFile.hasNextLine()){
                contactListStrings.add(readFile.nextLine());
            }

            for (String contact :  contactListStrings){
                String[] details = contact.split(",");
                contactList.add(new Contact(details[0],details[1]));
            }

            for (Contact contact : contactList){
                System.out.println(contact.getName()+"  " +contact.getNumber());
            }



        }
        catch (IOException e){
            System.out.println("IOException");
        }
    }



    public static void main(String[] args){

        boolean runStatus = true;

        while (runStatus){
            printOptions();
            int response = scanner.nextInt();
            scanner.nextLine();

            switch (response){
                case 1:
                    addContact();
                    break;
                case 2:
                    //deleteContact();
                    break;
                case 3:
                    //findContact();
                    break;
                case 4:
                    printContacts();
                    break;
                case 5:
                    runStatus=false;
                    break;
            }
        }

    }


    public static void printOptions(){
        System.out.println("Please select one of the below options\n"+
                "1: add new contact\n"+
                "2: delete contact\n"+
                "3: find contact\n"+
                "4: print Contacts\n"+
                "5: exit");
    }

    public static void addContact(){
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter Number:");
        String number = scanner.nextLine();
        try(BufferedWriter writeFile = new BufferedWriter (new FileWriter("contacts.txt"))){
    		for (Contact contact : contactList){
            	writeFile.write(contact.getName()+","+contact.getNumber()+"\n");
        	}

    		writeFile.write(name+","+number+"\n");
    		contactList.add(new Contact(name,number));

        }
        catch (IOException e){
            System.out.println("IOException");
        }
    }

    public static void printContacts(){
        for (Contact contact : contactList){
            System.out.println(contact.getName()+"  " +contact.getNumber());
        }
    }
}