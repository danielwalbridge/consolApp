package com.example.consolapp.commandLineInterface;

import com.example.consolapp.communication.ConsoleCommunication;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineInterface {

    private final List<String> userHelpOptions = populateUserHelpOptions();

    @Autowired
   private ConsoleCommunication communication;

    private boolean displayWelcomeMessage = true;

    @PostConstruct
    public void consoleMain() {
        while (true) {

            if (displayWelcomeMessage) {
                System.out.println("Welcome to the GoodReads search CLI: Please enter a function, or enter in --help for a list of approved functions");
                displayWelcomeMessage= false;
            }

            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("--help")) {
                printHelpOptions();
                continue;
            }

            if (line.equalsIgnoreCase("-h") || line.equalsIgnoreCase("--host")) {
                getHost();
                continue;
            }

            if (line.equalsIgnoreCase("-s") || line.equalsIgnoreCase("--search")) {
                search(scanner);
                continue;
            }
            if (line.equalsIgnoreCase("-sort")) {
               sort(scanner);
            }
            else {
                System.out.println("enter a valid input,  for a list of valid inputs please enter --help");
            }

        }

    }

    private void printHelpOptions() {
        for (String option: userHelpOptions) {
            System.out.println(option);
        }
    }

    private List<String> populateUserHelpOptions() {
        List<String> userOptions = new ArrayList<>();
        userOptions.add("--help : print out the accepted inputs ");
        userOptions.add("-h or --host : will print out the ip address where the server can be found");
        userOptions.add("-s or --search : will bring you to the prompt that will be used to search GoodReads");
        userOptions.add("--sort : sort the search options ");
        userOptions.add("stop : will stop the application");
        return userOptions;
    }

    private void getHost() {
        String hostInfo = "";
        try {
            hostInfo = communication.getHostInformation();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(hostInfo);
    }

    private void search(Scanner scanner) {
        System.out.println("you have selected the search function. \n please enter the search term to be sent to GoodReads");

        try {
            String searchTerm = scanner.nextLine();
            if (searchTerm.isBlank()) {
                System.out.println("You need to enter something in order for a search to happen");
                displayWelcomeMessage = true;
            }
            else {
                String serverResults = communication.getResultsFromServer(searchTerm);
                System.out.println(serverResults);
                displayWelcomeMessage = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sort(Scanner scanner) {
        System.out.println("you have selected the sort function. \n please enter the search term to be sent to GoodReads first");

        try {
            String searchTerm = scanner.nextLine();
            if (searchTerm.isBlank()) {
                System.out.println("You need to enter something in order for a search to happen");
                displayWelcomeMessage = true;
            }
            else {
                System.out.println(" the search term you want is: " + searchTerm + " \n" +
                        "now please enter author if you would like to search by the author \n" +
                        "or title if you would like to search by the title \n" +
                        "kindly note that if you enter anything else, your result will be sorted by title as a default");

                String sort = scanner.nextLine();

                if (sort.equalsIgnoreCase("author")) {
                    // send to new method with aut
                    String serverResults = communication.getResultsFromServerSort(searchTerm, sort);
                    System.out.println(serverResults);
                }
                else  {
                    sort ="title";
                    String serverResults = communication.getResultsFromServerSort(searchTerm, sort);
                    System.out.println(serverResults);
                }
                displayWelcomeMessage = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
