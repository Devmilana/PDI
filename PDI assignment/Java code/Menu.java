/***************************************************************************************************************************************************************************************
 * Author: Prashantha Fernando *                                                                                                                                                    
 
 * Purpose: Allow users to interrogate data and understand Indigenous community infrastructure projects throughout different Canadian provinces *                                                                                                      
 * Created on: 12/05/2023 *                                                                                                                                                       
 
 * Last edited on: 20/05/2023 *
 
***************************************************************************************************************************************************************************************/

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu 
{
    private static final String FILENAME = "First_Nation_Infrastructure_Investment.csv"; // Set to global as the file is not changed 
    private static Project[] projects; // Projects array set to global as after projects have been added to it, the contents will not be changed
    
    public static void main(String[] args) 
    {
        // Taking in current date and local time to add to file name
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String logFileName = "log_" + formattedDateTime + ".txt";

        try
        {   
            // THIS CONSOLE DUPLICATION AND WRITE METHOD IS ADAPTED FROM: https://stackoverflow.com/questions/1994255/how-to-write-console-output-to-a-txt-file
            
            // Creates a PrintStream object that writes to the console
            PrintStream consoleOut = System.out;

            // Creates a PrintStream object that writes to a file
            FileOutputStream fileOut = new FileOutputStream(logFileName);
            PrintStream filePrintStream = new PrintStream(fileOut);

            // Creates a PrintStream object that duplicates output to both console and file
            PrintStream duplicateOut = new PrintStream(new OutputStream() 
            {
                public void write(int b) throws IOException 
                {
                    consoleOut.write(b);
                    filePrintStream.write(b);
                }
            });

            // Redirects System.out to the duplicateOut stream
            System.setOut(duplicateOut);

            // Run main program
            initialiseArray();
            addProjectsToArray();
            displayMenuSelection();

            fileOut.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /************************************************************************************************
    * METHOD: Initialise projects array size according to number of
              elements in csv file

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    private static void initialiseArray()
    {
        FileInputStream filestream = null;
        InputStreamReader rdr;
        BufferedReader br;
        String line;

        try
        {
            filestream = new FileInputStream(FILENAME);
            rdr = new InputStreamReader(filestream);
            br = new BufferedReader(rdr);
            line = br.readLine(); // Skip reading header row
            int count = 0;

            while ((line = br.readLine()) != null)
            {
                count++;
            }

            br.close();

            projects = new Project[count]; // Initialise projects array
        }
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        catch (Exception e)
        {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    /************************************************************************************************
    * METHOD: Add objects to projects array from csv file

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    private static void addProjectsToArray()
    {
        FileInputStream filestream = null;
        InputStreamReader rdr;
        BufferedReader br;
        String line;

        try
        {
            filestream = new FileInputStream(FILENAME);
            rdr = new InputStreamReader(filestream);
            br = new BufferedReader(rdr);
            line = br.readLine(); // Skip reading header row
            int count = 0;

            while ((line = br.readLine()) != null) // Read through csv file while entry is not null and allocate column variables to array elements
            {
                String[] data = line.split(",");
                String province = data[0];
                String beneficiary = data[1];
                String beneficiaryNum = data[2];
                String assetClass = data[3];
                String name = data[4];
                String stage = data[6];
                double latitude = Double.parseDouble(data[7]);
                double longitude = Double.parseDouble(data[8]);
                String coordinateSystem = data[9];
                Location location = new Location(latitude, longitude, coordinateSystem);
                Project project = new Project(province, beneficiary, beneficiaryNum, assetClass, name, stage, location);
                projects[count] = project;
                count++;
            }

            br.close();
        }
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error parsing number: " + e.getMessage());
            return;
        }
    }

    /************************************************************************************************
    * METHOD: Display menu selection and process user selection

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    private static void displayMenuSelection()
    {
        int choice = -1;
        final int MAX_INVALID_CHOICES = 3; // Variable to set the number of invalid entries that can be taken in before program auto-exits
        int invalidChoices = 0;
        Scanner sc = new Scanner(System.in);
        
        while (choice != 0) // Loops menu until the user enters a valid selection or chooses to exit the program
        {
            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            System.out.println("\nWelcome to the Investments in Indigenous community infrastructure Program. There are a total of " + projects.length + " projects throughout Canada.");
            System.out.println("\nPlease make a selection from the Menu below.");
            System.out.println("> 1. All of Canada");
            System.out.println("> 2. Alberta");
            System.out.println("> 3. British Columbia");
            System.out.println("> 4. Manitoba");
            System.out.println("> 5. New Brunswick");
            System.out.println("> 6. Newfoundland and Labrador");
            System.out.println("> 7. Nova Scotia");
            System.out.println("> 8. Ontario");
            System.out.println("> 9. Prince Edward Island");
            System.out.println("> 10. Quebec");
            System.out.println("> 11. Saskatchewan");
            System.out.println("> 12. Northwest Territories");
            System.out.println("> 13. Nunavut");
            System.out.println("> 14. Yukon");
            System.out.println("> 0. Exit Program");

            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            System.out.println("\nPlease type in your selection: ");
            
            try
            {
                choice = Integer.parseInt(sc.nextLine()); // Parse input to integer

                if (choice < 0 || choice > 14)
                {
                    System.out.println("\nInvalid selection number. Please re-enter your selection.");
                    invalidChoices++;
                }
                else
                {
                    switch (choice)
                    {
                        case 1:
                            displayAllProjects();
                            break;

                        case 2:
                            displayProvincesMenu("Alberta");
                            break;

                        case 3:
                            displayProvincesMenu("British Columbia");
                            break;

                        case 4:
                            displayProvincesMenu("Manitoba");
                            break;

                        case 5:
                            displayProvincesMenu("New Brunswick");
                            break;

                        case 6:
                            displayProvincesMenu("Newfoundland and Labrador");
                            break;

                        case 7:
                            displayProvincesMenu("Nova Scotia");
                            break;

                        case 8:
                            displayProvincesMenu("Ontario");
                            break;

                        case 9:
                            displayProvincesMenu("Prince Edward Island");
                            break;

                        case 10:
                            displayProvincesMenu("Quebec");
                            break;

                        case 11:
                            displayProvincesMenu("Saskatchewan");
                            break;

                        case 12:
                            displayProvincesMenu("Northwest Territories");
                            break;

                        case 13:
                            displayProvincesMenu("Nunavut");
                            break;

                        case 14:
                            displayProvincesMenu("Yukon");
                            break;

                        case 0:
                            System.out.println("\nThank you for using this program! Exiting now...");
                            System.out.println();
                            sc.close();
                            break;

                        default:
                            System.out.println("\nInvalid selection number. Please re-enter your selection.");
                            invalidChoices++;
                    }
                }
                // Auto exit program if invalid entry count is greater than or equal to three
                if (invalidChoices >= MAX_INVALID_CHOICES)
                {
                    System.out.println("\nExceeded number of invalid selections. Exiting the program...");
                    return;
                }
            }
            catch (NumberFormatException e)
            {
                 System.out.println("\nError! Selection needs to be an Integer!");
                 invalidChoices++;
                 if (invalidChoices >= MAX_INVALID_CHOICES)
                 {
                     System.out.println("\nExceeded number of invalid selections. Exiting the program...");
                     return;
                 }
            }
            catch (Exception e)
            {
                 System.out.println("\nAn error occurred: " + e.getMessage());
                 return;
            }
        }
    }

    /************************************************************************************************
    * METHOD: Displays all projects in Canada, ongoing and completed

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    private static void displayAllProjects()
    {
        int totProjects = projects.length;
        int ongProjects = 0;
        int compProjects = 0;
        double compPercentage = 0.0;

        for (int i = 0; i <projects.length; i++)
        {
            Project project = projects[i];

            if (project.getStage().equalsIgnoreCase("Ongoing")) // Count the number of projects that are ongoing
            {
                ongProjects++;
            }
            else if (project.getStage().equalsIgnoreCase("Completed")) // Count the number of projects that have been completed
            {
                compProjects++;
            }
        }

        compPercentage = ((double) compProjects / totProjects) * 100; // Calculate the percentage of completed projects

        System.out.println("\nTotal number of projects in Canada: " + totProjects);
        System.out.println("Total number of ongoing projects: " + ongProjects);
        System.out.println("Total number of completed projects: " + compProjects);
        System.out.println("Percentage of completed projects: " + compPercentage + "%");
    }

    /************************************************************************************************
    * METHOD: Displays the province data selection menu and processes user input selection 

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    private static void displayProvincesMenu(String province)
    {
        int choice = -1;
        final int MAX_INVALID_CHOICES = 3; // Variable to set the number of invalid entries that can be taken in before program auto-exits
        int invalidChoices = 0;
        Scanner sc = new Scanner(System.in);

        while (choice != 0) // Loops menu until the user enters a valid selection or chooses to exit the program
        {
            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            System.out.println("\nPlease make a choice from the statistics below for " + province + ":");
            System.out.println("> 1. Number of projects");
            System.out.println("> 2. Percentage of all projects located in this province/territory");
            System.out.println("> 3. Total number and percentage of Ongoing projects");
            System.out.println("> 4. Total number and percentage of Completed projects");
            System.out.println("> 5. All of the above statistics");
            System.out.println("> 0. Return to main menu");

            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            System.out.println("\nPlease type in your selection: ");

            try
            {
                choice = Integer.parseInt(sc.nextLine());

                if (choice < 0 || choice > 5)
                {
                    System.out.println("\nInvalid selection number. Please re-enter your selection.");
                    invalidChoices++;
                }
                else
                {
                    switch (choice)
                    {
                        case 1:
                            totalProjectsInProvince(province);
                            break;
                        case 2:
                            percentageProjectsInProvince(province);
                            break;
                        case 3:
                            percentageOngoingInProvince(province);
                            break;
                        case 4:
                            percentageCompletedInProvince(province);
                            break;
                        case 5:
                            displayAllStats(province);
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("\nInvalid selection number. Please re-enter your selection.");
                            invalidChoices++;
                            break;
                    }
                }
                // Auto exit program if invalid entry count is greater than or equal to three
                if (invalidChoices >= MAX_INVALID_CHOICES) 
                {
                    System.out.println("\nExceeded number of invalid selections. Exiting the program...");
                    return;
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nError! Selection needs to be an Integer!");
                invalidChoices++;
                if (invalidChoices >= MAX_INVALID_CHOICES)
                {
                    System.out.println("\nExceeded number of invalid selections. Exiting the program...");
                    return;
                }
            }
            catch (Exception e)
            { 
                System.out.println("\nAn error occurred: " + e.getMessage());
                return;
            }
        }
    }

    /************************************************************************************************
    * METHOD: Displays total number of provincial projects

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    private static void totalProjectsInProvince(String province)
    {
        int count = 0;

        for (int i = 0; i <projects.length; i++) // Counts the number of entries with made for the province
        {
            Project project = projects[i];

            if (project.getProvince().equalsIgnoreCase(province))
            {
                count++;
            }
        }

        System.out.println("\nThe total number of projects in the " + province + " province: " + count);
    }

    /************************************************************************************************
    * METHOD: Displays percentage of provincial projects out of all Canadian projects

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    private static void percentageProjectsInProvince(String province)
    {
        int totalProjCount = 0;
        int numProjProvince = 0;
        double projPercentage = 0;

        for (int i = 0; i < projects.length; i++)
        {
            Project project = projects[i];

            if (project.getStage().equalsIgnoreCase("ongoing") || project.getStage().equalsIgnoreCase("completed")) // Count total projects in Canada
            {
                totalProjCount++;
            }
            if (project.getProvince().equalsIgnoreCase(province))
            {
                if (project.getStage().equalsIgnoreCase("ongoing") || project.getStage().equalsIgnoreCase("completed")) // Count total projects in province
                {
                    numProjProvince++;
                }
            }
        }

        if (numProjProvince == 0)
        {
            System.out.println("\nThe percentage of all projects located in the " + province + " province: 0%");
        }
        else
        {
            projPercentage = ((double) numProjProvince / totalProjCount) * 100; // Percentage of provincial projects out of all Canadian projects

            System.out.println("\nThe percentage of all projects located in the " + province + " province: " + projPercentage + "%");
        }
    }

    /************************************************************************************************
    * METHOD: Displays the number and percentage of ongoing provincial projects 
              out of all ongoing Canadian projects

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    public static void percentageOngoingInProvince(String province)
    {
        int totalProjCount = 0;
        int numOngoing = 0;
        double ongPercentage = 0;

        for (int i = 0; i < projects.length; i++)
        {
            Project project = projects[i];

            if (project.getStage().equalsIgnoreCase("ongoing")) // Count number of ongoing projects across Canada
            {
                totalProjCount++;
            }
            if (project.getProvince().equalsIgnoreCase(province))
            {
                if (project.getStage().equalsIgnoreCase("ongoing")) // Count number of ongoing provincial projects
                {
                    numOngoing++;
                }
            }
        }

        if (numOngoing == 0)
        {
            System.out.println("\nThe number of ongoing projects located in the " + province + " province: " + numOngoing);
            System.out.println("\nThe percentage of all ongoing projects located in the " + province + " province out of all ongoing projects in Canada: 0%");
        }
        else
        {
            ongPercentage = ((double) numOngoing / totalProjCount) * 100; // Percentage of ongoing provincial projects out of all ongoing Canadian projects

            System.out.println("\nThe number of ongoing projects located in the " + province + " province: " + numOngoing);
            System.out.println("The percentage of all ongoing projects located in the " + province + " province out of all ongoing projects in Canada: " + ongPercentage);
        }
    }

    /************************************************************************************************
    * METHOD: Displays the number and percentage of completed provincial projects 
              out of all completed projects across Canada

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    public static void percentageCompletedInProvince(String province)
    {
        int totalProjCount = 0;
        int numCompleted = 0;
        double compPercentage = 0;

        for (int i = 0; i < projects.length; i++)
        {
            Project project = projects[i];

            if (project.getStage().equalsIgnoreCase("completed")) // Count number of completed projects across Canada
            {
                totalProjCount++;
            }
            if (project.getProvince().equalsIgnoreCase(province))
            {
                if (project.getStage().equalsIgnoreCase("completed")) // Count number of completed provincial projects
                {
                    numCompleted++;
                }
            }
        }

        if (numCompleted == 0)
        {
            System.out.println("\nThe number of completed projects located in the " + province + " province: " + numCompleted);
            System.out.println("The percentage of all completed projects located in the " + province + " province out of all completed projects in Canada: " + "0%");
        }
        else
        {
            compPercentage = ((double) numCompleted / totalProjCount) * 100; // Percentage of completed provincial projects out of all completed projects across Canada

            System.out.println("\nThe number of completed projects located in the " + province + " province: " + numCompleted);
            System.out.println("The percentage of all completed projects located in the " + province + " province out of all completed projects in Canada: " + compPercentage);
        }
    }

    /************************************************************************************************
    * METHOD: Displays all provincal projects statistics

    * IMPORT: None

    * EXPORT: None
    *************************************************************************************************/
    public static void displayAllStats(String province)
    {
        totalProjectsInProvince(province);
        percentageProjectsInProvince(province);
        percentageOngoingInProvince(province);
        percentageCompletedInProvince(province);
    }
}
