import javafx.util.Pair;

import java.util.ArrayList;
/**
 * Defines a team
 * @author Mohammed Salah
 *
 */
public class Team {
    private String name;
    private ArrayList<Player> players;
    /**
     * Constructs a team
     * @param name ream's name
     */
    public Team(String name){
        this.name = name;
        players = new ArrayList<>();
    }
    /**
     * gets team's name
     * @return team's name
     */
    public String getName() {
        return name;
    }
    /**
     * sends invitation to a player to join a team
     * @param playerID player's id to invite
     */
    public void sendInvitation(String playerID){
        ArrayList<Player> tmp = system.getPlayerData();
        int idx = -1;
        for (int i = 0; i < tmp.size(); i++) {
            if(tmp.get(i).getID().equals(playerID)){
                idx = i;
                break;
            }
        }
        if(idx==-1){
            System.out.println("There is no player with this ID");
            return;
        }
        if(players.contains(tmp.get(idx)) || tmp.get(idx).getTeam()!=null){
            System.out.println("you can't invite player already in team");
            return;
        }
        String msg = "hello "+tmp.get(idx).getName()+", join to our "+this.getName()+" team now";
        Pair<String,Team> invitation = new Pair<String,Team>(msg,this);
        tmp.get(idx).receiveInvitation(invitation);
        System.out.println("send successfully...");
    }
    /**
     * sends invitations to multiple players
     * @param playersID array contains all IDs to invite
     */
    public void sendInvitation(ArrayList<String> playersID){
        for(String ID:playersID){
            sendInvitation(ID);
        }
    }
    /**
     * adds a player to a team
     * @param player player to be added
     */
    public void addPlayer(Player player){
        if(players.size()==7){
            System.out.println("The team is complete, You can't join");
            return;
        }
        players.add(player);
    }
}
