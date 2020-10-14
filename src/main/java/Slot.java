/**
 * Defines a slot
 * @author Emad Tarek, Mohammed Salah
 *
 */
public class Slot {
    private int startHour;
    private int endHour;
    private boolean isAvailable;
    public Slot(){}
    /**
     * Constructs a slot
     * @param isAvailable slots's state
     */
    public Slot(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    /**
     * sets slot's state
     * @param available slot's state
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * checks slot's status
     * @return slot's status
     */
    public boolean isAvailable(){
        return this.isAvailable;
    }

    /**
     * sets slot's end hour
     * @param endHour slot's end hour
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    /**
     * sets slot's start hour
     * @param startHour slot's start hour
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
    
    /**
     * gets slot's start hour
     * @return slot's start hour
     */
    public int getStartHour() {
    	return this.startHour;
    }
    
    /**
     * gets slot's end hour
     * @return slot's end hour
     */
    public int getEndHour() {
    	return this.endHour;
    }

}
