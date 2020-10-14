import java.util.ArrayList;

/**
 * Defines the playground owner
 * @author Emad Tarek, Mohammed Salah
 *
 */
public class PlaygroundOwner extends Person {
    private E_Wallet wallet;
    private ArrayList<Playground> playgrounds;
    /**
     * Constructs the owner's data
     */
    public PlaygroundOwner(){
        playgrounds = new ArrayList<>();
        wallet = new E_Wallet(100);
    }
    /**
     * show all playgrounds for this owner
     */
    public void viewPlaygrounds() {
        for (Playground playground : playgrounds) {
            playground.printPlaygroundInfo();
        }
        System.out.println();
    }

    /**
     * gets owner's playgrounds
     * @return owner's grounds
     */
    public ArrayList<Playground> getPlaygrounds(){
        return this.playgrounds;
    }

    /**
     * adds a playground
     */
    public void addPlayground() {
        Playground playground = new Playground();
        System.out.print("Enter ID: ");
        String ID = in.nextLine();
        if(!system.validPlaygroundID(ID)){
            System.out.println("This ID already exists");
            System.out.println("To cancel the process, press 'c' ");
            String choice = in.nextLine();
            if(choice.equalsIgnoreCase("c")){
                return;
            }
            addPlayground();
            return;
        }
        playground.setID(ID);
        System.out.print("Enter name: ");
        String name = in.nextLine();
        playground.setName(name);
        System.out.print("Enter length: ");
        double length = in.nextDouble();
        in.nextLine();
        playground.setLength(length);
        System.out.print("Enter width: ");
        double width = in.nextDouble();
        in.nextLine();
        playground.setWidth(width);
        System.out.print("Enter hour price: ");
        double price = in.nextDouble();
        in.nextLine();
        playground.setHourPrice(price);
        System.out.print("Enter cancellation period(in hour): ");
        int period = in.nextInt();
        in.nextLine();
        playground.setCancellationPeriod(period);
        System.out.println("Please, Enter location: ");
        System.out.print("First, Enter Governorate: ");
        String Governorate = in.nextLine();
        System.out.print("Second, Enter city: ");
        String city = in.nextLine();
        System.out.print("Third, Enter street: ");
        String street = in.nextLine();
        ArrayList<String> location = new ArrayList<>();
        location.add(Governorate);
        location.add(city);
        location.add(street);
        playground.setLocation(location);
        System.out.println("Enter description: ");
        String description = in.nextLine();
        playground.setDescription(description);
        System.out.print("Enter website link: ");
        String link = in.nextLine();
        playground.setLink(link);
        for (int i = 0; i < 24; i++) {
            System.out.print("Enter status of " + i + "-th hour slot(1 for available,otherwise for unavailable): ");
            Slot slot = new Slot();
            String status = in.nextLine();
            slot.setStartHour(i);
            slot.setEndHour(i + 1);
            if (status.equals("1")) {
                slot.setAvailable(true);
            } else {
                slot.setAvailable(false);
            }
            playground.getSlots().add(slot);
        }
        playgrounds.add(playground);
        system.getAdmin().get(0).receiveRequest(playground); /// send request to admin
        System.out.println("/-----------------------------------------/");
        System.out.println("/---- Playground created successfully ----/");
        System.out.println("/-----------------------------------------/");
    }

    /**
     * removes a playground by id
     * @param ID ground's id
     */
    public void removePlayground(String ID) {
        for (Playground playground : playgrounds){
            if (playground.getID().equals(ID)) {
                playgrounds.remove(playground);
                System.out.println("Removed successfully...");
                return;
            }
        }
        System.out.println("There is no playground with this ID");
    }

    /**
     * gets owner's wallet
     * @return owner's wallet
     */
    public E_Wallet getWallet() {
        return wallet;
    }


}
