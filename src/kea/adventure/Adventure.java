package kea.adventure;

import java.util.Scanner;

public class Adventure {

    public static void main(String[] args) {

        //Initialise rooms

        //create all instances of room objects - probably should be an array - data very basic
        Room room1 = new Room("Room 1", "Looks like an entrance.");
        Room room2 = new Room("Room 2", "Not much to see.");
        Room room3 = new Room("Room 3", "The way straight seems blocked");
        Room room4 = new Room("Room 4", "Looks empty");
        Room room5 = new Room("Room 5", "This room looks special.");
        Room room6 = new Room("Room 6", "You see nothing interesting");
        Room room7 = new Room("Room 7 ", "What is here?...Oh, nothing.");
        Room room8 = new Room("Room 8", "Seems to be several exits");
        Room room9 = new Room("Room 9", "No beer here");

        Room currentRoom = room1;
        Room requestedRoom;

        //Make connections - auto 2 way but not with map - todo?
        room1.connectSouthNorth(room4);
        room1.connectEastWest(room2);
        room2.connectEastWest(room3);
        room3.connectSouthNorth(room6);
        room6.connectSouthNorth(room9);
        room8.connectEastWest(room9);
        room7.connectEastWest(room8);
        room5.connectSouthNorth(room8);
        room4.connectSouthNorth(room7);

        Scanner input = new Scanner(System.in);

        //Intro and description of start room
        System.out.println("\nWelcome to this text based Adventure.");

        //Get inputs until user types exit or x
        String menuOption = "Z";

        while (!menuOption.equals("X") && !menuOption.equals("EXIT")) {
            requestedRoom = currentRoom; //used to only print blocked if user tries a blocked route
            System.out.print("\n" + currentRoom.getRoomName() + ": ");
            System.out.println(currentRoom.getRoomDescription());
            System.out.print("What do you want to do? ");
            menuOption = input.nextLine().toUpperCase();

            //player choice with multiple command forms
            switch (menuOption) {
                case "GO NORTH", "NORTH", "N" -> requestedRoom = currentRoom.getNorthRoom();
                case "GO EAST", "EAST", "E" -> requestedRoom = currentRoom.getEastRoom();
                case "GO SOUTH", "SOUTH", "S" -> requestedRoom = currentRoom.getSouthRoom();
                case "GO WEST", "WEST", "W" -> requestedRoom = currentRoom.getWestRoom();
                case "EXPLORE", "LOOK", "L" -> System.out.print("\nLooking. ");
                case "HELP", "H" -> getHelp();
                case "EXIT", "X" -> endMessage();
                default -> unknownCommand(menuOption);
            }
            if (requestedRoom == null) {
                System.out.println("That way is blocked.");
            } else {
                currentRoom = requestedRoom;
            }
        }
    }

    //Help info, only with the short commands
    public static void getHelp() {
        System.out.println("\nYou can use these commands:");
        System.out.println("H - Help (this)");
        System.out.println("L - Look around (room description)");
        System.out.println("X - Exit");
        System.out.println("N - Go North");
        System.out.println("E - Go East");
        System.out.println("S - Go South");
        System.out.println("W - Go West");
    }

    //Give up
    public static void endMessage() {
        System.out.println("Really? Hope to see you again soon.");
    }

    //Invalid input
    public static void unknownCommand(String menuOption) {
        System.out.println("I do not understand \"" + menuOption + "\".");
    }
}
