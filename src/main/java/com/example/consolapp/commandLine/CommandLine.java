package com.example.consolapp.commandLine;

import com.example.consolapp.communication.ConsoleCommunication;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLine  {

    private final List<String> userHelpOptions = populateUserHelpOptions();

    @Autowired
   private ConsoleCommunication communication;

    @PostConstruct
    public void consoleMain() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("--help")) {
                for (String option: userHelpOptions) {
                    System.out.println(option);
                }
                continue;
            }
            if (line.equalsIgnoreCase("-h") || line.equalsIgnoreCase("--host")) {
                String hostinfo = "";
                try {
                     hostinfo = communication.getHostInformation();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(hostinfo);
                continue;
            }
            if (line.equalsIgnoreCase("-s") || line.equalsIgnoreCase("--search")) {
                System.out.println("here are the search options");
                continue;
            }
            if (line.equalsIgnoreCase("-sort")) {
                System.out.println("here are your sort options");
                continue;
            }
            if (line.equalsIgnoreCase("stop")) {
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

//    @Override
//    public void run(String... args) throws Exception {
//
//    }
}
