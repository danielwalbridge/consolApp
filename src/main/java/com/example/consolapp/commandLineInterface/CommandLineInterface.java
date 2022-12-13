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
                for (String option: userHelpOptions) {
                    System.out.println(option);
                }
                continue;
            }
            if (line.equalsIgnoreCase("-h") || line.equalsIgnoreCase("--host")) {
                String hostInfo = "";
                try {
                     hostInfo = communication.getHostInformation();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(hostInfo);
                continue;
            }
            if (line.equalsIgnoreCase("-s") || line.equalsIgnoreCase("--search")) {
                System.out.println("you have selected the search function. \n please enter the search term to be sent to GoodReads");

                // grab search term to be use in call.
                String searchTerm = scanner.nextLine();
                String serverResults = communication.getResultsFromServer(searchTerm);
                System.out.println("You are getting back " +serverResults);

                // need to call communication to get the info back.

                continue;
            }
            if (line.equalsIgnoreCase("-sort")) {
                //todo ability to sort already searched calls
                System.out.println("here are your sort options");
                continue;
            }
            if (line.equalsIgnoreCase("stop")) {
                //todo add an are you sure function to this
                System.exit(0);
            }
            else {
                System.out.println("enter a valid input,  for a list of valid inputs please enter --help");
            }

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
}
