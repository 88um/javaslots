import java.util.*;

class Slots{ 
    public static int betMultiplier; // These values are constatntly modified
    public static int bet;
    public static int totalCredits=100;
    public static Random rand = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static enum Critters {EWOK, JAWA, BANTHA, DEWBACK, YODA, PORG, WOOKIE}
    public static void main(String[] args){
        clearScreen();
        showMenu();
    }

    public static void clearScreen() {  //clears the console
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
    
    public static Critters spin(){ //spins the wheel 
        Critters critter;
        critter = Critters.values()[rand.nextInt(Critters.values().length)];
        return critter;
    }

    public static void makeBet(){
        bet = 0;
        System.out.format("[+] Enter a bet between 1-%d: ",totalCredits);
        bet = sc.nextInt();
        if (bet > totalCredits || bet == 0){System.out.println("Invalid Input!");}
        else{
            boolean didWin = winOrLose();
            if (didWin){addBalance();}
            else{minusBalance();}}
    }

    public static void showPayout(){
        System.out.println("===========\nPAYOUT VALUES\n===========\n\n");
        System.out.println("Any one YODA  - 2x Bet");
        System.out.println("Match any two creatues - 3x Bet");
        System.out.println("Match any three creatues- 4x Bet");
        System.out.println("Match no creatures - Lose your bet");
        System.out.print("\nPress any key then ENTER key to return...");
        sc.next();
    }

    public static void addBalance(){ //adds values to totalcredits
        int amountWon = bet*betMultiplier;
        totalCredits+=amountWon;
        System.out.format("\nYou won %d imperial credits!!\n", bet*betMultiplier);
        System.out.format("Number of imperial credits available: "+totalCredits);
        System.out.print("\nPress any key then ENTER key to return...");
        sc.next();
    }

    public static void minusBalance(){ //decreases total credit value
        totalCredits-=bet;
        System.out.format("\nYou lost %d imperial credits!\n", bet);
        System.out.format("Number of imperial credits available: "+totalCredits);
        if (totalCredits == 0){
            System.out.println("\n\nGame Over! You have lost all credits!");
            System.out.print("\nPress any key then ENTER key to exit...");
            sc.next();
            System.exit(0);}
        System.out.print("\nPress any key then ENTER key to return...");
        sc.next();
    }
    
    public static boolean winOrLose(){
        clearScreen();
        betMultiplier = 1;
        List<Critters> list = new ArrayList<>();
        for (int i = 0;i<3;i++){list.add(spin());} //add 3 spins
        if (list.contains(Critters.YODA)){betMultiplier+=1;} //check for any yodas
        for (int i = 0; i< list.size(); i++){ // check for any matches
            for (int j = i+1; j<list.size();j++){
                if (list.get(i).equals(list.get(j))){betMultiplier+=1;} //if match increase bet multiplier
            }}
        System.out.format("%s         %s         %s\n",list.get(0),list.get(1),list.get(2)); //Printing results
        if (betMultiplier == 1){return false;} //return false if lost the bet
        return true; //return true if bet was won
    }

    public static void showMenu(){ //Showing the menu
       clearScreen();
       System.out.format("Gay Nigga Slots\n==============================\nYou start with 100 imperial credits\nCurrent Credit Balance: %d\nWhen the balace hist zero you lose\n",totalCredits);
       System.out.println("==============================\n\n1. Play\n2. See Payout Amounts\n3. Exit\n    ");
       System.out.print("\n[+] Enter choice: ");
       int choice = sc.nextInt();
       switch(choice){
            case 1:
                makeBet();
                winOrLose();
                break;
            case 2:
                showPayout();
                break;
            case 3:
                System.exit(0);
                break;
            default:{
                System.out.println("Invalid Choice Number");
                break;
            }}
      showMenu();
    }
        

}
