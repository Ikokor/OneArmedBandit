class Money {

    private static float balance = 5;
    private static float winnings;
    private static int credit;

    static void addCredit(){
        if(balance >= 1) {
            credit += 5;
            balance--;
        }
    }

    static void spin(){
        if(credit >= 1){
            credit--;
        }
    }

    static float balance(){
        return balance;
    }

    static int credit(){
        return credit;
    }

    static float winnings(){
        return winnings;
    }

    static void singleWin(){
        winnings += 0.2;
    }

    static void doubleWin(){
        winnings += 0.5;
    }

    static void tripleWin(){
        winnings ++;
    }
    
    static void triple7() {
    	winnings += 2;
    }

    static void tripleDiamond() {
    	winnings += 1.5;
    }
    
    static void cashIn(){
        balance += winnings;
        winnings = 0;
    }

    static void reCredit(){
        credit ++;
        if (winnings <= 0.21){
            winnings = 0;
        }
        if (winnings > 0.2){
            winnings -= 0.2;
        }

    }
}