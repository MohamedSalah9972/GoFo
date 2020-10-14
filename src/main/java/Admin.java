import java.util.ArrayList;
/**
 * Defines the admin
 * @author Emad Tarek, Mohammed Salah
 *
 */
public class Admin extends Person{
    private ArrayList<Playground> newPlaygrounds; /// ArrayList of Requests to add playgrounds
    /**
     * constructs the admin
     */
    public Admin(){
        newPlaygrounds = new ArrayList<>();
    }
    /**
     * views all the requests to activate playgrounds in the system
     */
    public void viewRequests(){
        if(newPlaygrounds.size()==0){
            System.out.println("There are no requests");
            return;
        }
        for (Playground playground:newPlaygrounds){
            playground.printPlaygroundInfo();
            System.out.println("\nPress 1 to approve this playground, otherwise to reject");
            String response = in.nextLine();
            playground.setActive(response.equals("1"));
        }
        newPlaygrounds = new ArrayList<>(); /// remove all playground owner requests after responding  all playground
    }

    /**
     * receives any playground' requests
     * @param playground the one to be checked
     */
    public void receiveRequest(Playground playground){
        newPlaygrounds.add(playground);
    }



}
