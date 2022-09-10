//Node.java

import java.util.Optional;
import java.util.Scanner;

public class Node {

    protected String description;
    protected Node next;
    protected Node previous;
    protected Node nodeChoice1;
    protected Node nodeChoice2;
    protected String option1;
    protected String option2;

    public String getDescription() {
        return description;
    }

    public Node description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Node getNext() {
        return next;
    }

    public Node next(Node next) {
        this.setNext(next);
        return this;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node previous(Node previous) {
        this.setPrevious(previous);
        return this;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNodeChoice1() {
        return nodeChoice1;
    }

    public Node nodeChoice1(Node nodeChoice1) {
        this.setNodeChoice1(nodeChoice1);
        return this;
    }

    public void setNodeChoice1(Node nodeChoice1) {
        this.nodeChoice1 = nodeChoice1;
    }

    public Node getNodeChoice2() {
        return nodeChoice2;
    }

    public Node nodeChoice2(Node nodeChoice2) {
        this.setNodeChoice2(nodeChoice2);
        return this;
    }

    public void setNodeChoice2(Node nodeChoice2) {
        this.nodeChoice2 = nodeChoice2;
    }

    public String getOption1() {
        return option1;
    }

    public Node option1(String option1) {
        this.setOption1(option1);
        return this;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public Node option2(String option2) {
        this.setOption2(option2);
        return this;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public Node process() {
        boolean valid = false;
        Optional<String> choice = Optional.empty();
        Optional<Node> chosenNode = Optional.empty();

        Scanner inputCollector = new Scanner(System.in); // Create a Scanner object
        while (!valid) {
            Game.putText(this.getDescription());
            if (this.option1 != null && this.option1 != "WIN") {
                System.out.println("\t" + Game.ANSI_YELLOW + "[1]: " + Game.ANSI_RESET + this.option1);
            }
            if (this.option2 != null && this.option2 != "WIN") {
                System.out.println("\t" + Game.ANSI_YELLOW + "[2]: " + Game.ANSI_RESET + this.option2);
            }
            if ((this.option1 != null && this.option1 != "WIN") && (this.option2 != null && this.option2 != "WIN")) {
                System.out.print("\t" + Game.ANSI_RED + "[What do you choose? (1 or 2)]: " + Game.ANSI_RESET);
            }
            if (this.option1 == null || this.option2 == null) {
                System.out.print("\t" + Game.ANSI_RED + "[Press Enter to restart]: " + Game.ANSI_RESET);
            }
            if (this.option1 == "WIN") {
                return (this);
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
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (next == null) {
            if (other.next != null)
                return false;
        } else if (!next.equals(other.next))
            return false;
        if (nodeChoice1 == null) {
            if (other.nodeChoice1 != null)
                return false;
        } else if (!nodeChoice1.equals(other.nodeChoice1))
            return false;
        if (nodeChoice2 == null) {
            if (other.nodeChoice2 != null)
                return false;
        } else if (!nodeChoice2.equals(other.nodeChoice2))
            return false;
        if (option1 == null) {
            if (other.option1 != null)
                return false;
        } else if (!option1.equals(other.option1))
            return false;
        if (option2 == null) {
            if (other.option2 != null)
                return false;
        } else if (!option2.equals(other.option2))
            return false;
        if (previous == null) {
            if (other.previous != null)
                return false;
        } else if (!previous.equals(other.previous))
            return false;
        return true;
    }

}