import java.util.Optional;
import java.util.Scanner;

//UseItemNode.java

public class UseItemNode extends CollectionNode {
    protected String alternateDescription;
    protected String alternateOption1;
    protected String alternateOption2;
    protected Node alternateNodeChoice1;
    protected Node alternateNodeChoice2;

    public UseItemNode() {
    }

    public String getAlternateDescription() {
        return alternateDescription;
    }

    public UseItemNode alternateDescription(String alternateDescription) {
        this.setAlternateDescription(alternateDescription);
        return this;
    }

    public void setAlternateDescription(String alternateDescription) {
        this.alternateDescription = alternateDescription;
    }

    public String getAlternateOption1() {
        return alternateOption1;
    }

    public UseItemNode alternateOption1(String alternateOption1) {
        this.setAlternateOption1(alternateOption1);
        return this;
    }

    public void setAlternateOption1(String alternateOption1) {
        this.alternateOption1 = alternateOption1;
    }

    public String getAlternateOption2() {
        return alternateOption2;
    }

    public UseItemNode alternateOption2(String alternateOption2) {
        this.setAlternateOption2(alternateOption2);
        return this;
    }

    public void setAlternateOption2(String alternateOption2) {
        this.alternateOption2 = alternateOption2;
    }

    public Node getAlternateNodeChoice1() {
        return alternateNodeChoice1;
    }

    public UseItemNode alternateNodeChoice1(Node alternateNodeChoice1) {
        this.setAlternateNodeChoice1(alternateNodeChoice1);
        return this;
    }

    public void setAlternateNodeChoice1(Node alternateNodeChoice1) {
        this.alternateNodeChoice1 = alternateNodeChoice1;
    }

    public Node getAlternateNodeChoice2() {
        return alternateNodeChoice2;
    }

    public UseItemNode alternateNodeChoice2(Node alternateNodeChoice2) {
        this.setAlternateNodeChoice2(alternateNodeChoice2);
        return this;
    }

    public void setAlternateNodeChoice2(Node alternateNodeChoice2) {
        this.alternateNodeChoice2 = alternateNodeChoice2;
    }

    @Override
    public Node process() {
        boolean valid = false;
        Optional<String> choice = Optional.empty();
        Optional<Node> chosenNode = Optional.empty();
        Scanner inputCollector = new Scanner(System.in); // Create a Scanner object

        if (getPlayer().getInventory().contains(getItem())) {
            while (!valid) {
                Game.putText(this.getDescription());
                if (this.option1 != null) {
                    System.out.println("\t" + Game.ANSI_YELLOW + "[1]: " + Game.ANSI_RESET + this.option1);
                }
                if (this.option2 != null) {
                    System.out.println("\t" + Game.ANSI_YELLOW + "[2]: " + Game.ANSI_RESET + this.option2);
                }
                if (this.option1 != null && this.option2 != null) {
                    System.out.print("\t" + Game.ANSI_RED + "[What do you choose? (1 or 2)]: " + Game.ANSI_RESET);
                }
                if (this.option1 == null || this.option2 == null) {
                    System.out.print("\t" + Game.ANSI_RED + "[Press Enter to restart]: " + Game.ANSI_RESET);
                }
                String userInput = inputCollector.nextLine(); // Read user input

                choice = Optional.of(userInput.toString());
                if (this.nodeChoice1 == null) {
                    return (null);
                } else if (choice.get().equals("1")) {
                    chosenNode = Optional.of(getNodeChoice1());
                    this.next = chosenNode.get();
                    this.next.previous = this;
                    valid = true;
                } else if (choice.get().equals("2")) {
                    chosenNode = Optional.of(getNodeChoice2());
                    this.next = chosenNode.get();
                    this.next.previous = this;
                    valid = true;
                } else {
                    System.out.print(Game.CLEAR_SCREEN);
                    System.out.println(Game.ANSI_RED_BG + "\n\tInvalid Input (Type 1 or 2)" + Game.ANSI_RESET);
                }
            }
            return chosenNode.get();
        } else {
            while (!valid) {
                Game.putText(this.getAlternateDescription());
                if (this.alternateOption1 != null) {
                    System.out.println("\t" + Game.ANSI_YELLOW + "[1]: " + Game.ANSI_RESET + this.alternateOption1);
                }
                if (this.alternateOption2 != null) {
                    System.out.println("\t" + Game.ANSI_YELLOW + "[2]: " + Game.ANSI_RESET + this.alternateOption2);
                }
                if (this.alternateOption1 != null && this.alternateOption2 != null) {
                    System.out.print("\t" + Game.ANSI_RED + "[What do you choose? (1 or 2)]: " + Game.ANSI_RESET);
                }
                if (this.alternateOption1 == null || this.alternateOption2 == null) {
                    System.out.print("\t" + Game.ANSI_RED + "[Press Enter to restart]: " + Game.ANSI_RESET);
                }
                String userInput = inputCollector.nextLine(); // Read user input

                choice = Optional.of(userInput.toString());
                if (this.alternateNodeChoice1 == null) {
                    return (null);
                } else if (choice.get().equals("1")) {
                    chosenNode = Optional.of(getAlternateNodeChoice1());
                    this.next = chosenNode.get();
                    this.next.previous = this;
                    valid = true;
                } else if (choice.get().equals("2")) {
                    chosenNode = Optional.of(getAlternateNodeChoice2());
                    this.next = chosenNode.get();
                    this.next.previous = this;
                    valid = true;
                } else {
                    System.out.print(Game.CLEAR_SCREEN);
                    System.out.println(Game.ANSI_RED_BG + "\n\tInvalid Input (Type 1 or 2)" + Game.ANSI_RESET);
                }
            }
            return chosenNode.get();
        }
    }
}
