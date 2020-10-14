import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Defines a player
 * @author Emad Tarek, Mohammed Salah
 *
 */
public class Player extends Person {
    private Team team;
    private ArrayList<Pair<String, Team>> invitations;
    private E_Wallet wallet;

    /**
     * Constructs player's data
     */
    public Player() {
        team = null;
        wallet = new E_Wallet(100);
        invitations = new ArrayList<>();
    }

    /**
     * shows all invitations for a player
     */
    public void viewInvitation() {
        if (invitations.size() == 0) {
            System.out.println("There are no invitations");
            return;
        }
        for (int i = 0; i < invitations.size(); i++) {
            System.out.println((i + 1) + ") " + invitations.get(i).getKey());
        }
        System.out.println("choose a number from list to join this team or press '-1' to not join any team");
        int choice = in.nextInt();
        in.nextLine();
        if (choice == -1) {
            return;
        }
        if (choice < 1 || choice > invitations.size()) {
            System.out.println("invalid input");
            return;
        }
        choice--; /// to convert it to zero based
        if (this.getTeam() != null) {
            System.out.println("You already in team");
            return;
        }
        this.setTeam(invitations.get(choice).getValue());
        invitations.get(choice).getValue().addPlayer(this);
        invitations.remove(choice);
        
    }

    /**
     * gets player's team
     * @return player's team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * sets player's team
     * @param team player's team
     */
    public void setTeam(Team team) {
        this.team = team;

    }


    /**
     * receives invitation from other players
     * @param invitation invitation data
     */
    public void receiveInvitation(Pair<String, Team> invitation) {
        invitations.add(invitation);
    }

    /**
     * creates a team
     * @param teamName team's name
     */
    public void createTeam(String teamName) {
        if (this.getTeam() != null) {
            System.out.println("You already in team, you can't create a team");
            return;
        }
        team = new Team(teamName);
        setTeam(team);
        team.addPlayer(this);
    }

    /**
     * show all playgrounds
     */
    public void viewAllPlayground() {
        ArrayList<PlaygroundOwner> owners = system.getPlaygroundOwnerData();
        boolean print = false;
        for (PlaygroundOwner owner : owners) {
            owner.viewPlaygrounds();
            if(owner.getPlaygrounds().size()>0)
                print = true;
        }
        if(!print){
            System.out.println("There is no any playground");
            return;
        }
        System.out.println("If you want book a playground press '1'");
        String choice = in.nextLine();
        if (choice.equals("1")) {
            System.out.println("Please, Enter playground ID");
            String ID = in.nextLine();
            System.out.println("Please, Enter start hour");
            int startHour = in.nextInt();
            System.out.println("Please, Enter end hour");
            int endHour = in.nextInt();
            in.nextLine();
            this.bookPlayground(ID, startHour, endHour);
        }

    }

    /**
     * gets the e-wallet
     * @return player's wallet
     */
    public E_Wallet getWallet() {
        return this.wallet;
    }
    
    /**
     * books a playground in a certain time 
     * @param ID ground's id
     * @param startHour starting hour
     * @param endHour finishing hour
     */
    public void bookPlayground(String ID, int startHour, int endHour) {
        if (startHour >= endHour) {
            System.out.println("invalid input");
            return;
        }
        ArrayList<PlaygroundOwner> owners = system.getPlaygroundOwnerData();
        for (PlaygroundOwner owner : owners) {
            ArrayList<Playground> playgrounds = owner.getPlaygrounds();
            for (Playground playground : playgrounds) {
                if (playground.getID().equals(ID)) {
                    if ((endHour - startHour) * playground.getHourPrice() > wallet.getMoney()) {
                        System.out.println("You don't have enough money");
                        return;
                    }

                    if (!playground.isActive()) {
                        System.out.println("This playground is unavailable");
                        return;
                    }
                    if (playground.bookSlot(startHour, endHour)) {
                        this.getWallet().withdraw((endHour - startHour) * playground.getHourPrice());
                        owner.getWallet().deposit((endHour - startHour) * playground.getHourPrice());
                    }
                    return;
                }
            }
        }
        System.out.println("there is no playground with this ID");
    }

    /**
     * show playgrounds with certain length and width
     * @param length ground's length
     * @param width ground's width
     */
    public void viewPlayground(double length, double width) {
        ArrayList<PlaygroundOwner> owners = system.getPlaygroundOwnerData();
        for (PlaygroundOwner owner : owners) {
            ArrayList<Playground> playgrounds = owner.getPlaygrounds();
            for (Playground playground : playgrounds) {
                if (playground.getLength() == length && playground.getWidth() == width) {
                    playground.printPlaygroundInfo();
                }
            }
        }
        System.out.println("If you want book a playground press '1'");
        String choice = in.nextLine();
        if (choice.equals("1")) {
            System.out.println("Please, Enter playground ID");
            String ID = in.nextLine();
            System.out.println("Please, Enter start hour");
            int startHour = in.nextInt();
            System.out.println("Please, Enter end hour");
            int endHour = in.nextInt();
            in.nextLine();
            this.bookPlayground(ID, startHour, endHour);
        }
    }

    /**
     * show all playgrounds available at a certain time
     * @param startHour starting hour
     * @param endHour finishing hour
     */
    public void viewPlayground(int startHour, int endHour) {
        ArrayList<PlaygroundOwner> owners = system.getPlaygroundOwnerData();
        for (PlaygroundOwner owner : owners) {
            ArrayList<Playground> playgrounds = owner.getPlaygrounds();
            for (Playground playground : playgrounds) {
                ArrayList<Slot> slots = playground.getSlots();
                boolean print = true;
                for (;endHour>startHour;startHour++){
                    if(!slots.get(startHour).isAvailable())
                    {
                        print = false;
                        break;
                    }
                }
                if(print)
                    playground.printPlaygroundInfo();
            }
        }
        System.out.println("If you want book a playground press '1'");
        String choice = in.nextLine();
        if (choice.equals("1")) {
            System.out.println("Please, Enter playground ID");
            String ID = in.nextLine();
            System.out.println("Please, Enter start hour");
            startHour = in.nextInt();
            System.out.println("Please, Enter end hour");
            endHour = in.nextInt();
            in.nextLine();
            this.bookPlayground(ID, startHour, endHour);
        }
    }

    /*
     * show playgrounds at certain location
     */
    public void viewNearPlayground(ArrayList<String> address){
        ArrayList<PlaygroundOwner> owners = system.getPlaygroundOwnerData();
        for (PlaygroundOwner owner : owners) {
            ArrayList<Playground> playgrounds = owner.getPlaygrounds();
            for (Playground playground : playgrounds) {
                if(playground.getLocation().get(0).equalsIgnoreCase(address.get(0)) && playground.getLocation().get(1).equalsIgnoreCase(address.get(1))){
                    playground.printPlaygroundInfo();
                }
            }
        }
        System.out.println("If you want book a playground press '1'");
        String choice = in.nextLine();
        if (choice.equals("1")) {
            System.out.println("Please, Enter playground ID");
            String ID = in.nextLine();
            System.out.println("Please, Enter start hour");
            int startHour = in.nextInt();
            System.out.println("Please, Enter end hour");
            int endHour = in.nextInt();
            in.nextLine();
            this.bookPlayground(ID, startHour, endHour);
        }
    }


}
