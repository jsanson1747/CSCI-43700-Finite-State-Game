import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_CYAN_BG = "\u001B[46m";

    public static final String CLEAR_SCREEN = "\033[H\033[2J";

    private Node head;
    private Node currentNode;
    private Character player;

    public Game() {
    }

    public Game(Node head, Node currentNode, Character player) {
        this.head = head;
        this.currentNode = currentNode;
        this.player = player;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    public static void putText(String message) {
        putText(true, message);
    }

    public static void putText(String message, int speed) {
        putText(true, message, speed);
    }

    public static void putText(String message, String color) {
        putText(true, message, 40, color);
    }

    public static void putText(Boolean newLine, String message) {
        putText(newLine, message, 40);
    }

    public static void putText(Boolean newLine, String message, String color) {
        putText(newLine, message, 40, color);
    }

    public static void putText(Boolean newLine, String message, int speed) {
        putText(newLine, message, speed, "");
    }

    public static void putText(Boolean newLine, String message, int speed, String color) {
        System.out.println("");
        System.out.print("\t");
        Integer wordCounter = 0;
        String output = "";
        for (int counter = 0; counter < message.length(); counter++) {
            output = message.substring(counter, counter + 1);
            if (output.equals(" ")) {
                wordCounter++;
            }
            if (wordCounter.equals(20)) {
                System.out.println("");
                System.out.print("\t");
                wordCounter = 0;
                output = "";
            }
            System.out.print(color + output + ANSI_RESET);
            pause(speed);
        }
        if (newLine) {
            System.out.println("");
        }
    }

    public void colorTitle(List<String> titleList) {
        System.out.print("\t");
        String currentChar = "";
        for (int index = 0; index < titleList.size(); index++) {
            for (int counter = 0; counter < titleList.get(index).length(); counter++) {
                currentChar = titleList.get(index).substring(counter, counter + 1);
                if (currentChar.equals("░")) {
                    System.out.print(ANSI_CYAN + currentChar + ANSI_RESET);
                } else if (currentChar.equals("█")) {
                    System.out.print(ANSI_WHITE + currentChar + ANSI_RESET);
                } else {
                    System.out.print(ANSI_YELLOW + currentChar + ANSI_RESET);
                }
            }
            System.out.println("");
            System.out.print("\t");
        }
    }

    /**
     * Start the Game
     */
    public void play(Node leaveRoom) {
        // Introduction
        final int PLAYERDIALOGSPEED = 150;
        System.out.print(CLEAR_SCREEN);
        putText("Darkness", 500);
        pause(500);
        putText("Light brightens and your vision comes into focus as you feel an intermitent water droplet hit your face. You wake up and find yourself in a room you have never seen before. You touch your head and wince in pain.");
        putText("\"When did I hit my head? How did I get here?\"", PLAYERDIALOGSPEED);
        putText("...", 1000);
        putText("\"Who am I?\"", PLAYERDIALOGSPEED);

        this.currentNode = head;
        // First Choice
        while (currentNode != null) {
            if (currentNode.equals(leaveRoom)) {
                currentNode.process();
                putText("You have beat Anonymity.", "\033[0;1m");
                putText("Thanks for playing!", "\033[0;1m");

                putText(false, "Press Enter to return to the Title Screen");
                Scanner gameStarter = new Scanner(System.in); // Create a Scanner object
                String startInput = gameStarter.nextLine(); // Read user input
                break;
            }
            currentNode = currentNode.process();
            System.out.print(Game.CLEAR_SCREEN);
        }
    }

    /**
     * Main Menu
     */
    public void mainMenu() {
        System.out.print(CLEAR_SCREEN);
        boolean isStarted = false;
        List<String> titleList = new ArrayList<>();
        titleList.add("░█████╗░███╗░░██╗░█████╗░███╗░░██╗██╗░░░██╗███╗░░░███╗██╗████████╗██╗░░░██╗");
        titleList.add("██╔══██╗████╗░██║██╔══██╗████╗░██║╚██╗░██╔╝████╗░████║██║╚══██╔══╝╚██╗░██╔╝");
        titleList.add("███████║██╔██╗██║██║░░██║██╔██╗██║░╚████╔╝░██╔████╔██║██║░░░██║░░░░╚████╔╝░");
        titleList.add("██╔══██║██║╚████║██║░░██║██║╚████║░░╚██╔╝░░██║╚██╔╝██║██║░░░██║░░░░░╚██╔╝░░");
        titleList.add("██║░░██║██║░╚███║╚█████╔╝██║░╚███║░░░██║░░░██║░╚═╝░██║██║░░░██║░░░░░░██║░░░");
        titleList.add("╚═╝░░╚═╝╚═╝░░╚══╝░╚════╝░╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░░░░╚═╝╚═╝░░░╚═╝░░░░░░╚═╝░░░");
        while (!isStarted) {
            System.out.println("");
            colorTitle(titleList);

            putText("Type \"About\" for details or \"Quit\" to quit");
            putText(false, "Type \"Start\" to start >>> ");

            Scanner gameStarter = new Scanner(System.in); // Create a Scanner object
            String startInput = gameStarter.nextLine(); // Read user input

            if (startInput.equals("Start") || startInput.equals("start")) {
                isStarted = true;
            } else if (startInput.equals("About") || startInput.equals("about")) {
                creditsMenu();
                System.out.print(CLEAR_SCREEN);
            } else if (startInput.equals("Quit") || startInput.equals("quit")) {
                putText("[Exiting Game]\n", ANSI_RED);
                System.exit(0);
            } else {
                System.out.print(CLEAR_SCREEN);
            }
        }

    }

    public void creditsMenu() {
        System.out.print(CLEAR_SCREEN);

        putText("Anonymity - 2022", "\033[0;1m");
        putText("Jacob Sanson", "\033[0;1m");
        putText("Its the year 4000. Humanity has spiraled into a worldwide dystopia. Technology is beyond advanced but society is crumbling. You play as an unnamed character who has woken up in a strange complex not remembering who they are or how they got there. In this text based adventure, you must find clues to find your way out of this mysterious complex and see what world awaits you beyond its walls.");
        putText(false, "Press Enter to return to the Title Screen ", ANSI_CYAN);

        Scanner gameStarter = new Scanner(System.in); // Create a Scanner object
        gameStarter.nextLine(); // Read user input
    }

    /**
     * Main Loop
     */
    public static void main(String args[]) {
        // Declare Nodes
        Node head = new Node();
        Node examineRoom = new Node();
        Node freakOut = new Node();
        Node succumbToAnxiety = new Node();
        Node inspectDoor = new Node();
        Node inspectWindow = new Node();
        CollectionNode pickUpPaperclip = new CollectionNode();
        UseItemNode grabbedPaperclip = new UseItemNode();
        Node tryToSootheTerror = new Node();
        CollectionNode obtainBall = new CollectionNode();
        Node grabBall = new Node();
        Node grabCube = new Node();
        UseItemNode lookAtPaper = new UseItemNode();
        UseItemNode tryToOpenDoorAgain = new UseItemNode();
        UseItemNode keepLookingOutWindow = new UseItemNode();
        CollectionNode keepPullingOnDoor = new CollectionNode();
        UseItemNode goToBrokenDoor = new UseItemNode();
        Node deathByBrokenDoor = new Node();
        Node giveIntoHoplessness = new Node();
        Node leaveRoom = new Node();
        Node stayInRoom = new Node();

        // Populate Head Node
        head
                .description("You decide to assess your situation")
                .option1("Examine the room")
                .nodeChoice1(examineRoom)
                .option2("Freak out")
                .nodeChoice2(freakOut);

        // Initialize game elements
        Character player = new Character("Charles", 100, 0, new ArrayList<String>());
        Node currentNode = head;
        Game game = new Game(head, currentNode, player);

        // Populate Nodes
        freakOut
                .description(
                        "You feel like your heart is beating through your chest. You try to calm down but you begin to hyperventilate.")
                .option1("Try to soothe your terror")
                .nodeChoice1(tryToSootheTerror)
                .option2("Succum to the anxiety")
                .nodeChoice2(succumbToAnxiety);
        succumbToAnxiety
                .description(
                        "You start shrieking in terror as the gravity of your situation sinks in. Your yelling triggers a noise activated alarm system and the walls begin to close in on you. \n\n\tYou look around frantically for a means to escape but the walls are closing in too fast. You let out one final gasp as you are crushed to death by the compressing walls \n\n\tYou have died.")
                .option1(null)
                .nodeChoice1(null)
                .option2(null)
                .nodeChoice2(null);
        tryToSootheTerror
                .description(
                        "You look around the room and you see a metal table. Atop this metal table you see a ball and a cube. You decide to pick one up. Which one do you choose?")
                .option1("Ball")
                .nodeChoice1(obtainBall)
                .option2("Cube")
                .nodeChoice2(grabCube);
        obtainBall
                .item("Ball")
                .player(game.getPlayer())
                .description("You pick up the ball.")
                .next(grabBall);
        grabCube
                .description(
                        "You collect the cube and embrace it. You sqeeze the cube in hope that doing something with your hands will help relax you.\n\n\t. . . inhale . . .\n\n\tThe cube spontaneously explodes and you are blown to bits\n\n\tYou are dead.")
                .option1(null)
                .nodeChoice1(null)
                .option2(null)
                .nodeChoice2(null);
        grabBall
                .description(
                        "You hold the ball and embrace it. You sqeeze the ball in hope that doing something with your hands will help relax you.\n\n\t. . . inhale . . .\n\n\tThe ball squishes pleasantly. It seems to be constructed out of soft foam. This stress relieving ball soothes you and you are able to calm yourself down.\n\n\tNow that you have composed yourself you decide to examine the room.\n\n\tYou painfully stand up and start looking around the room. You see a sturdy metal door and a barred window.")
                .option1("Inspect door")
                .nodeChoice1(inspectDoor)
                .option2("Inspect window")
                .nodeChoice2(inspectWindow);
        examineRoom
                .description(
                        "You painfully stand up and start looking around the room. You see a sturdy metal door and a barred window.")
                .option1("Inspect door")
                .nodeChoice1(inspectDoor)
                .option2("Inspect window")
                .nodeChoice2(inspectWindow);
        inspectDoor
                .description(
                        "You walk over to the door. You attempt to open it and find that it's locked. In dismay you look at the door and notice that there's a paper hanging on it.")
                .option1("Look at the paper")
                .nodeChoice1(lookAtPaper)
                .option2("Try to open the door again")
                .nodeChoice2(tryToOpenDoorAgain);
        lookAtPaper
                .alternateDescription(
                        "You look closer at the paper. It appears to be a sort of personnel file. The file apears to be about a man named Charles. There's a picture in the file and it seems that it's a picture of you.\n\n\t\"My name must be Charles\"\n\n\tYou are excited by this new knowledge and you continue trying to find a way out of the room.")
                .alternateOption1("Inspect window")
                .alternateNodeChoice1(inspectWindow)
                .alternateOption2("Try to open the door again")
                .alternateNodeChoice2(tryToOpenDoorAgain)

                .item("Door Handle")
                .player(game.getPlayer())

                .description(
                        "You look closer at the paper. It appears to be a sort of personnel file. The file apears to be about a man named Charles. There's a picture in the file and it seems that it's a picture of you.\n\n\t\"My name must be Charles\"\n\n\tYou are excited by this new knowledge and you continue trying to find a way out of the room.")
                .option1("Inspect window")
                .nodeChoice1(inspectWindow)
                .option2("Try to open the door again")
                .nodeChoice2(deathByBrokenDoor);
        inspectWindow
                .description(
                        "You walk over to the window. It's heavily barred but you can still see out of it. You look out and see a smog covered city full of lights and sirens. The sirens upset you and you hang your head in dismay. As you look down, you see a paperclip on the ground. It's one of those sturdy metal paperclips.")
                .option1("Pick up paperclip")
                .nodeChoice1(pickUpPaperclip)
                .option2("Keep looking out the window")
                .nodeChoice2(keepLookingOutWindow);
        pickUpPaperclip
                .item("Paperclip")
                .player(game.getPlayer())
                .description("You pick up the paperclip")
                .next(grabbedPaperclip);
        grabbedPaperclip
                .alternateDescription("You slip the paperclip into your pocket. What do you do next?")
                .alternateOption1("Keep looking out the window")
                .alternateNodeChoice1(keepLookingOutWindow)
                .alternateOption2("Try to open the door again")
                .alternateNodeChoice2(tryToOpenDoorAgain)

                .item("Door Handle")
                .player(game.getPlayer())

                .description("You slip the paperclip into your pocket. What do you do next?")
                .option1("Keep looking out the window")
                .nodeChoice1(keepLookingOutWindow)
                .option2("Try to open the door again")
                .nodeChoice2(deathByBrokenDoor);
        keepLookingOutWindow
                .alternateDescription(
                        "As you gaze out into the city you notice that what you previouslty thought were poeple walking around are actually robots.\n\n\t\"Thats right! Robots took over humanity 100 years ago.\"\n\n\tSurprised by this realization, you feel you now have a different perspective of your situation.")
                .alternateOption1("Try to open the door again")
                .alternateNodeChoice1(tryToOpenDoorAgain)
                .alternateOption2("Give in to the hoplessness")
                .alternateNodeChoice2(giveIntoHoplessness)

                .item("Door Handle")
                .player(game.getPlayer())

                .description(
                        "As you gaze out into the city you notice that what you previously thought were poeple walking around are actually robots.\n\n\t\"Thats right! Robots took over humanity 100 years ago.\"\n\n\tSurprised by this realization, you feel you now have a different perspective of your situation.")
                .option1("Try to open the door again")
                .nodeChoice1(deathByBrokenDoor)
                .option2("Give in to the hoplessness")
                .nodeChoice2(giveIntoHoplessness);
        giveIntoHoplessness
                .description(
                        "You slump down the wall as you accept the fact that you can't escape this strange place.\n\n\tDays go by and no one comes by to supply you with meals. You end up dying of starvation.\n\n\tYou are dead.")
                .option1(null)
                .nodeChoice1(null)
                .option2(null)
                .nodeChoice2(null);
        tryToOpenDoorAgain
                .alternateDescription(
                        "You tug on the door again and it still won't budge. It really is indeed locked. You need to find some way to open it.")
                .alternateOption1("Keep pulling on the door")
                .alternateNodeChoice1(keepPullingOnDoor)
                .alternateOption2("Inspect window")
                .alternateNodeChoice2(inspectWindow)

                .item("Paperclip")
                .player(game.getPlayer())

                .description(
                        "You attempt to open the door and it still won't budge. As you previously found, the door appears to be locked. You remember that you found a paperclip by the window and it gives you an idea. You use the paperclip to pick the lock on the door\n\n\t. . .\n\n\tafter about a minute of poking and prodding, you hear a click and the door swings open.\n\n\tBefore you stands a dark hallway.")
                .option1("Leave the room")
                .nodeChoice1(leaveRoom)
                .option2("Stay in the room")
                .nodeChoice2(stayInRoom);
        keepPullingOnDoor
                .item("Door Handle")
                .player(game.getPlayer())
                .description(
                        "In a blind fit of rage you continue to tug on the door. All of a sudden you hear a CRACK and the door handle breaks off of the door. Turns out the door handle was not as sturdy as the rest of the steel door.\n\n\tYou have obtained a door handle")
                .next(goToBrokenDoor);
        goToBrokenDoor
                .alternateDescription(
                        "With the door handle broken, there is no longer a way to open the door. You fall to the floor in defeat and begin to get anxious again.\n\n\tYou start shrieking in terror as the gravity of your situation sinks in. Your yelling triggers a noise activated alarm system and the walls begin to close in on you.n\n\n\tYou look around frantically for a means to escape but the walls are closing in too fast. You let out one final gasp as you are crushed to death by the compressing walls\n\n\tYou have died")
                .alternateOption1(null)
                .alternateNodeChoice1(null)
                .alternateOption2(null)
                .alternateNodeChoice2(null)

                .item("Ball")
                .player(game.getPlayer())

                .description(
                        "With the door handle broken, there is no longer a way to open the door. You fall to the floor in defeat and begin to get anxious again.\n\n\tYou remember that you have that sweet sweet stress relieving ball. You pull it out and start sqeezing it again.\n\n\t. . . inhale . . .\n\n\tOnce again your nerves are soothed and you start to think about how you can get out of your problematic situation.")
                .option1("Look at the paper on the door")
                .nodeChoice1(lookAtPaper)
                .option2("Inspect window")
                .nodeChoice2(inspectWindow);
        deathByBrokenDoor
                .description(
                        "The door no longer has a handle and is impossible to open.\n\n\tYou slump down the wall as you accept the fact that you can't escape this strange place.\n\n\tDays go by and no one comes by to supply you with meals. You end up dying of starvation.\n\n\tYou are dead.")
                .option1(null)
                .nodeChoice1(null)
                .option2(null)
                .nodeChoice2(null);
        stayInRoom
                .description(
                        "You look down the dark hallway and decide you actually prefer the room you are currently in.\n\n\tDays pass and you go insane from the solitude. You start pretending you have a family in the isolated room and rotate between pretending to be each member of the family.\n\n\tEventually you fall into an irreversible state of psychosis and meet your demise from a lack of food and water.\n\n\tYou are dead.")
                .option1(null)
                .nodeChoice1(null)
                .option2(null)
                .nodeChoice2(null);
        leaveRoom
                .description(
                        "You step out into the dark hallway unsure of what you might encounter. Who knows what might be contained within the walls of this mysterious facility.")
                .option1("WIN")
                .nodeChoice1(null)
                .option2("WIN")
                .nodeChoice2(null);
        while (true) {
            game.mainMenu();
            game.play(leaveRoom);
        }
    }
}
