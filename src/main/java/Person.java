import java.util.ArrayList;
import java.util.Scanner;
/**
 * Defines a person
 * @author Emad Tarek
 *
 */
public class Person {
    Scanner in = new Scanner(System.in);
    private String password;
    private String ID;
    private String email;
    private String phone;
    private String name;
    private ArrayList<String> address; /// first => Governorate, second => city, third => street
    /**
     * gets user's password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets user's password
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets user's id
     * @return user's is
     */
    public String getID() {
        return ID;
    }

    /**
     * sets user's id
     * @param ID user's id
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * gets user's email
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets user's email
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets user's phone
     * @return user's phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets user's phone
     * @param phone user's phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * gets user's address
     * @return user's address
     */
    public ArrayList<String> getAddress() {
        return address;
    }

    /**
     * sets user's address
     * @param address user's address
     */
    public void setAddress(ArrayList<String> address) {
        this.address = address;
    }

    /**
     * gets user's name
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * sets user's name
     * @param name user's name
     */
    public void setName(String name) {
        this.name = name;
    }
}
