import java.util.ArrayList;

/**
 * Defines a playground
 * @author Emad Tarek, Mohammed Salah
 *
 */
public class Playground {
    private String name;
    private String ID;
    private double length;
    private double width;
    private String link;
    private String description;
    private boolean isActive;
    private int cancellationPeriod;
    private double hourPrice;
    private ArrayList<String> location;
    private ArrayList<Slot> slots;

    /**
     * gets all playground's slots
     * @return ground's slots
     */
    public ArrayList<Slot> getSlots() {
        return slots;
    }

    /**
     * Construct a playground
     */
    public Playground() {
        this.setActive(false);
        slots = new ArrayList<>();
        name=ID=link=description=null;
        length=width=hourPrice=0;
        location =new ArrayList<String>();
    }

    /**
     * sets playground's name
     * @return playground's name
     */
    public String getName() {
        return name;
    }

    /**
     * sets playground's name
     * @param name playground's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets playground's id
     * @return playground's id
     */
    public String getID() {
        return ID;
    }

    /**
     * sets playground's id
     * @param ID playground's id
     */
    public void setID(String ID){
        this.ID = ID;
    }

    /**
     * gets playground's width
     * @return playground's width
     */
    public double getWidth() {
        return width;
    }

    /**
     * sets playground's width
     * @param width playground's width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * gets playground's length
     * @return playground's length
     */
    public double getLength() {
        return length;
    }

    /**
     * sets playground's length
     * @param length playground's length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * gets playground's link
     * @return playground's link
     */
    public String getLink() {
        return link;
    }

    /**
     * sets playground's link
     * @param link playground's link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * gets playground's description
     * @return playground's description
     */
    public String getDescription() {
        return description;
    }
    
	/**
	 * sets playground's description
	 * @param description playground's description
	 */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * checks if a slot is available or not
     * @param startHour starting hour
     * @param endHour finishing hour
     * @return state of the slot
     */
    public boolean isAvailableSlot(int startHour,int endHour){
        boolean available = true;
        for (Slot slot:slots){
            if(slot.getStartHour()==startHour && slot.getEndHour()==endHour){
                available = slot.isAvailable();
                break;
            }
        }
        return available;
    }

    /**
     * checks if ground is active or not
     * @return playground's state
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * sets playground's state
     * @param active playground's state
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * gets playground's cancellation Period
     * @return playground's cancellation Period
     */
    public int getCancellationPeriod() {
        return cancellationPeriod;
    }

    /**
     * sets playground's cancellation Period
     * @param cancellationPeriod playground's cancellation Period
     */
    public void setCancellationPeriod(int cancellationPeriod) {
        this.cancellationPeriod = cancellationPeriod;
    }

    /**
     * gets playground's hour's price
     * @return playground's hour's price
     */
    public double getHourPrice() {
        return hourPrice;
    }

    /**
     * sets playground's hour's price
     * @param hourPrice playground's hour's price
     */
    public void setHourPrice(double hourPrice) {
        this.hourPrice = hourPrice;
    }

    /**
     * books a slot 
     * @param startHour starting hour
     * @param endHour finishing hour
     * @return if you can book this slot or not
     */
    public boolean bookSlot(int startHour,int endHour){ /// end hour not included in booking period
        boolean can = true;
        int st = startHour;
        while (st<endHour) {
            if (!isAvailableSlot(st,st+1)) {
                can = false;
                break;
            }
            
            st++;
        }
        if(!can)
        {
            System.out.println("some slots are not available");
            return false;
        }
        else{
            while(startHour<endHour){
                for(Slot slot: slots) {
                	if(slot.getStartHour()==startHour&&slot.getEndHour()==startHour+1) {
                		slot.setAvailable(false);
                		break;
                	}
                }
                startHour++;
            }
        }
        System.out.println("Booked Successfully...");
        return true;
    }

    public void printPlaygroundInfo() {
        System.out.println("/-----------------------------------------------------------/");
        System.out.println("\tName: " + this.getName()+"\t ID: "+this.getID());
        System.out.println("\tDescription: " + this.getDescription());
        System.out.println("\tLength: " + this.getLength() + "\t Width: " + this.getWidth());
        System.out.println("\tHour price: " + this.getHourPrice());
        System.out.println("\tStatus: " + (this.isActive() ? "Available" : "Unavailable"));
        System.out.println("\tCancellation period: " + this.getCancellationPeriod());
        System.out.println("\twebsite link: " + this.getLink());
        System.out.println("/-----------------------------------------------------------/");

    }

    /**
     * gets playground's location
     * @return ground's location
     */
    public ArrayList<String> getLocation() {
        return location;
    }

    /**
     * sets playground's location
     * @param location playground's location
     */
    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }
}
