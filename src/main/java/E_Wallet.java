/**
 * Defines user's wallet
 * @author Emad Tarek, Mohammed Salah
 *
 */
public class E_Wallet {
   private double money;
   /**
    * Constructs the e-wallet
    * @param money the initial money for the wallet
    */
   public E_Wallet(double money){
       this.money = money;
   }
   /**
    * gets the amount of money
    * @return the amount of money
    */
    public double getMoney() {
        return money;
    }
    
    /**
     * sets the money
     * @param money the money
     */
    public void setMoney(double money) {
        this.money = money;
    }
    /**
     * deposits some money
     * @param money the amount
     */
    public void deposit(double money){
        if (money <= 0) {
            System.out.println("You can't deposit money with negative number");
            return;
        }
        setMoney(getMoney()+money);
    }
    /**
     * withdraws some money
     * @param money the amount
     */
    public void withdraw(double money){
        if(getMoney()<money){
            return;
        }
        setMoney(getMoney()-money);
    }
    /**
     * checks how much money in the wallet
     */
    public void checkBalance(){
       System.out.println("/-----------------------------------------------/");
       System.out.println("/----\tYour balance is: "+this.getMoney()+"\t------/");
       System.out.println("/------------------------------------------------/");
    }
}
