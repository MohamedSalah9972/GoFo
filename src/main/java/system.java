import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * Defines the system
 * @author Mohammed Salah
 *
 */
public class system {
    static Scanner in = new Scanner(System.in);
    private static String verificationCode = "";
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<PlaygroundOwner> playgroundOwners = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();

    /**
     * gets player's data
     * @return all players in the system
     */
    public static ArrayList<Player> getPlayerData() {
        return players;
    }

    /**
     * gets owner's data
     * @return all owners in the system
     */
    public static ArrayList<PlaygroundOwner> getPlaygroundOwnerData() {
        return playgroundOwners;
    }

    /**
     * gets admin data
     * @return all admins in the system
     */
    public static ArrayList<Admin> getAdmin() {
        return admins;
    }

    /**
     * checks if id is valid
     * @param ID entered id
     * @return if id exists or not
     */
    public static boolean validID(String ID) {
        boolean valid = true;
        for (Player player : players) {
            if (player.getID().equals(ID)) {
                valid = false;
                break;
            }
        }
        for (PlaygroundOwner owner : playgroundOwners) {
            if (owner.getID().equals(ID)) {
                valid = false;
                break;
            }
        }
        for (Admin admin : admins) {
            if (admin.getID().equals(ID)) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * checks email validation
     * @param email entered email
     * @return if it's right or not 
     */
    public static boolean validEmail(String email) {
        String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
         if(!email.matches(pattern)){
             return false;
         }
         for (Player player:players){
             if(player.getEmail().equals(email))
                 return false;
         }
         for (PlaygroundOwner playgroundOwner:playgroundOwners){
             if(playgroundOwner.getEmail().equals(email))
                 return false;
         }
         return true;

    }

    /**
     * login a user
     */
    public static void login() {
        System.out.println("Please, Enter your ID");
        String ID = in.nextLine();
        System.out.println("Please, Enter your password");
        String password = in.nextLine();
        for (Player player : players) {
            if (player.getID().equals(ID) && player.getPassword().equals(password)) {
                playerMenu(player);
                return;
            }
        }
        for (PlaygroundOwner owner : playgroundOwners) {
            if (owner.getID().equals(ID) && owner.getPassword().equals(password)) {
                ownerMenu(owner);
                return;
            }
        }
        for (Admin admin : admins) {
            if (admin.getID().equals(ID) && admin.getPassword().equals(password)) {
                adminMenu(admin);
                return;
            }
        }
        System.out.println("ID or Password is wrong!");

    }

    /**
     * checks if ground's id is valid or not
     * @param ID  ground's id
     * @return if it's valid or not
     */
    public static boolean validPlaygroundID(String ID) {
        boolean valid = true;
        for (PlaygroundOwner owner : playgroundOwners) {
            ArrayList<Playground> playgrounds_ = owner.getPlaygrounds();
            for (Playground pl : playgrounds_) {
                if (pl.getID().equals(ID)) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    /**
     * registers a user
     */
    public static void register() {
        System.out.println("Please, choose your role(press 1 for player, otherwise for playground owner)");
        String role = in.nextLine();
        if (role.equalsIgnoreCase("1")) { /// registration for player
            Player newPlayer = new Player();
            System.out.print("Please, Enter your name: ");
            String name = in.nextLine();
            newPlayer.setName(name);
            System.out.print("Please, Enter your phone: ");
            String phone = in.nextLine();
            newPlayer.setPhone(phone);
            System.out.print("Please, Enter your email: ");
            String email = in.nextLine();
            while (!validEmail(email)) {
                System.out.println("This mail is invalid,enter it again, to cancel registration press 'c' ");
                email = in.nextLine();
                if (email.equalsIgnoreCase("c")) {
                    return;
                }
            }
            newPlayer.setEmail(email);

            ///first => Governorate, second => city, third => street
            System.out.println("Please, Enter your location: ");
            System.out.print("First, Enter your Governorate: ");
            String Governorate = in.nextLine();
            System.out.print("Second, Enter your city: ");
            String city = in.nextLine();
            System.out.print("Third, Enter your street: ");
            String street = in.nextLine();
            ArrayList<String> location = new ArrayList<>();
            location.add(Governorate);
            location.add(city);
            location.add(street);
            newPlayer.setAddress(location);
            System.out.print("Enter your ID: ");
            String ID = in.nextLine();
            while (!validID(ID)) {
                System.out.println("This ID is invalid,enter it again, to cancel registration press 'c' ");
                ID = in.nextLine();
                if (ID.equalsIgnoreCase("c")) {
                    return;
                }
            }
            newPlayer.setID(ID);
            System.out.println("Please, Enter your password");
            String password = in.nextLine();
            newPlayer.setPassword(password);
            sendVerificationCode(newPlayer.getEmail(), newPlayer.getName());
            System.out.println("Now, please enter your verification code: ");
            String code = in.nextLine();
            while (!code.equals(verificationCode)) {
                System.out.println("Wrong code!");
                System.out.println("please enter your verification code or press 'c' to cancel registration: ");
                code = in.nextLine();
                if (code.equalsIgnoreCase("c"))
                    return;
            }
            players.add(newPlayer);
            playerMenu(newPlayer);
        } else {      /// registration for playground owner
            PlaygroundOwner owner = new PlaygroundOwner();
            System.out.print("Please, Enter your name: ");
            String name = in.nextLine();
            owner.setName(name);
            System.out.print("Please, Enter your phone: ");
            String phone = in.nextLine();
            owner.setPhone(phone);
            System.out.print("Please, Enter your email: ");
            String email = in.nextLine();
            while (!validEmail(email)) {
                System.out.println("This mail is invalid,enter it again, to cancel registration press 'c' ");
                String tmp = in.nextLine();
                if (tmp.equalsIgnoreCase("c")) {
                    return;
                }
            }
            owner.setEmail(email);
            ///first => Governorate, second => city, third => street
            System.out.println("Please, Enter your location: ");
            System.out.print("First, Enter your Governorate: ");
            String Governorate = in.nextLine();
            System.out.print("Second, Enter your city: ");
            String city = in.nextLine();
            System.out.print("Third, Enter your street: ");
            String street = in.nextLine();
            ArrayList<String> location = new ArrayList<>();
            location.add(Governorate);
            location.add(city);
            location.add(street);
            owner.setAddress(location);
            System.out.print("Enter your ID: ");
            String ID = in.nextLine();
            while (!validID(ID)) {
                System.out.println("This ID is invalid,enter it again, to cancel registration press 'c' ");
                ID = in.nextLine();

                if (ID.equalsIgnoreCase("c")) {
                    return;
                }
            }
            owner.setID(ID);
            System.out.println("Please, Enter your password");
            String password = in.nextLine();
            owner.setPassword(password);
            sendVerificationCode(owner.getEmail(), owner.getName());
            System.out.println("Now, please enter your verification code: ");
            String code = in.nextLine();
            while (!code.equals(verificationCode)) {
                System.out.println("Wrong code!");
                System.out.println("please enter your verification code or press 'c' to cancel registration: ");
                code = in.nextLine();
                if (code.equalsIgnoreCase("c"))
                    return;
            }
            playgroundOwners.add(owner);
            ownerMenu(owner);
        }

    }

    /**
     * displays owner's menu
     * @param owner some owner
     */
    public static void ownerMenu(PlaygroundOwner owner) {
        System.out.println("hello " + owner.getName());
        System.out.println("Choose one of the following: ");
        System.out.println("1- Add playground");
        System.out.println("2- Remove playground");
        System.out.println("3- Check E-wallet");
        System.out.println("4- Logout");
        int choice = in.nextInt();
        in.nextLine();
        if (choice == 1) {
            owner.addPlayground();
        } else if (choice == 2) {
            System.out.println("Enter playground ID");
            String ID = in.nextLine();
            owner.removePlayground(ID);
        } else if (choice == 3) {
            owner.getWallet().checkBalance();
        } else if (choice == 4) {
            mainMenu();
            return;
        } else {
            System.out.println("Wrong choice!");
        }
        ownerMenu(owner);

    }

    /**
     * 
     * displays player's menu
     * @param player some player
     */
    public static void playerMenu(Player player) {
        System.out.println("hello " + player.getName());
        System.out.println("Choose one of the following: ");
        System.out.println("1- Create a team");
        System.out.println("2- Book a playground");
        System.out.println("3- View all playground");
        System.out.println("4- View playground by width and length");
        System.out.println("5- View playground in specific slot");
        System.out.println("6- View near playground");
        System.out.println("7- View invitations");
        System.out.println("8- Send invitation to single player");
        System.out.println("9- Send invitations to multiple player");
        System.out.println("10- Deposit money");
        System.out.println("11- Check e-wallet");
        System.out.println("12- Logout");
        int choice = in.nextInt();
        in.nextLine();

        if (choice == 1) {
            System.out.println("Enter team name");
            String teamName = in.nextLine();
            player.createTeam(teamName);
        } else if (choice == 2) {
            player.viewAllPlayground();
        } else if (choice == 3) {
            player.viewAllPlayground();
        } else if (choice == 4) {
            System.out.print("Enter playground width: ");
            double width = in.nextDouble();
            System.out.print("Enter playground length: ");
            double length = in.nextDouble();
            in.nextLine();
            player.viewPlayground(width, length);
        } else if (choice == 5) {
            System.out.print("Enter start hour: ");
            int startHour = in.nextInt();
            System.out.print("Enter end hour: ");
            int endHour = in.nextInt();
            in.nextLine();
            player.viewPlayground(startHour, endHour);
        } else if (choice == 6) {
            player.viewNearPlayground(player.getAddress());
        } else if (choice == 7) {
            player.viewInvitation();
        } else if (choice == 8) {
            if (player.getTeam() == null) {
                System.out.println("You have no team");
                playerMenu(player);
                return;
            }
            System.out.println("Enter player ID: ");
            String ID = in.nextLine();
            player.getTeam().sendInvitation(ID);
        } else if (choice == 9) {
            if (player.getTeam() == null) {
                System.out.println("You have no team");
                playerMenu(player);
                return;
            }
            ArrayList<String> playersID = new ArrayList<>();
            System.out.println("Enter the number of players who you want to invite (6 max)");
            int number = in.nextInt();
            in.nextLine();
            if(number>6) {
            	System.out.println("Maximum allowed number is 6");
            	playerMenu(player);
            	return;
            }
            while (number > 0) {
                System.out.print("Enter player ID:  ");
                String ID = in.nextLine();
                playersID.add(ID);
                number--;
            }
            player.getTeam().sendInvitation(playersID);
        } else if (choice == 10) {
            System.out.println("Enter money");
            double money = in.nextDouble();
            player.getWallet().deposit(money);
        } else if (choice == 11) {
            player.getWallet().checkBalance();
        } else if (choice == 12) {
            mainMenu();
            return;
        } else {
            System.out.println("Wrong choice!");
            playerMenu(player);
        }
        playerMenu(player);
    }

    /**
     * 
     * displays admin's menu
     * @param admin some admin
     */
    public static void adminMenu(Admin admin) {
        System.out.println("\t\t\t<< Welcome To your GoFo System >>\n");
        System.out.println("Choose one of the following: ");
        System.out.println("1- View playground requests");
        System.out.println("2- Logout");
        int choice = in.nextInt();
        in.nextLine();
        if (choice == 1) {
            admin.viewRequests();
        } else if (choice == 2) {
            mainMenu();
            return;

        } else {
            System.out.println("Wrong choice!");
        }
        adminMenu(admin);
    }

    /**
     * displays the main menu for the system
     */
    public static void mainMenu() {
        System.out.println("\t\t\t<< Welcome To GoFo System >>\n");
        System.out.println("Choose one of the following: ");
        System.out.println("1- Login");
        System.out.println("2- Register");
        System.out.println("3- Exit");
        String choice = in.nextLine();
        if (choice.equals("1")) {
            login();
        } else if (choice.equals("2")) {
            register();
        } else if (choice.equals("3")) {
            System.out.println("Exit...");
            System.exit(0);
        } else {
            System.out.println("choice:   " + choice);
            System.out.println("Wrong choice!, Enter again");
            mainMenu();
        }
        mainMenu();

    }

    /**
     * sends a verification code to confirm email
     * @param to receiver 
     * @param name name
     */
    public static void sendVerificationCode(String to, String name) {

        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("mohamed.hendy9972@gmail.com", "7111999ms");

            }

        });


        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress("mohamed.hendy9972@gmail.com"));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("verification code");
            Random random = new Random();
            verificationCode = random.ints(97, 123).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            message.setText("Hey " + name + "!\nyour verification code \n" + verificationCode);

            System.out.println("sending...");

            Transport.send(message);

            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    /**
     * main function to run the system
     * @param args arguments
     */
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setEmail("mohamed.salah9972@gmail.com");
        admin.setName("Mohamed Salah");
        admin.setID("20180227");
        admin.setPhone("01032329426");
        admin.setPassword("20180227");
        ArrayList<String> address = new ArrayList<>();
        address.add("Cairo");
        address.add("Badrashin");
        address.add("El-nile street");
        admin.setAddress(address);
        admins.add(admin); /// default admin

        mainMenu();
    }
}
