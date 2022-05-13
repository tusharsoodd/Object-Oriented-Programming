package dataStructures;


public class CashCount implements ICashCount {

    public int NrNotes_20pounds;
    public int NrNotes_10pounds;
    public int NrNotes_5pounds;
    public int NrCoins_2pounds;
    public int NrCoins_1pound;
    public int NrCoins_50p;
    public int NrCoins_20p;
    public int NrCoins_10p;

    public CashCount() {
        this.setNrNotes_20pounds(0);
        this.setNrNotes_10pounds(0);
        this.setNrNotes_5pounds(0);
        this.setNrCoins_2pounds(0);
        this.setNrCoins_1pound(0);
        this.setNrCoins_50p(0);
        this.setNrCoins_20p(0);
        this.setNrCoins_10p(0);

    }


    /** Adds two given CashCount instances
     *
     * @param x first CashCount
     * @param y second CashCount
     * @return x updated with y
     */

    // ---------------- OPERATIONS ----------------

    public CashCount addCashCounts(CashCount x, CashCount y) {
        x.setNrNotes_20pounds(y.getNrNotes_20pounds());
        x.setNrNotes_10pounds(y.getNrNotes_10pounds());
        x.setNrNotes_5pounds(y.getNrNotes_5pounds());
        x.setNrCoins_2pounds(y.getNrCoins_2pounds());
        x.setNrCoins_1pound(y.getNrCoins_1pound());
        x.setNrCoins_50p(y.getNrCoins_50p());
        x.setNrCoins_20p(y.getNrCoins_20p());
        x.setNrCoins_10p(y.getNrCoins_10p());

        return x;
    }


    // ---------------- SETTERS ----------------
    public int howManyPoundsInZooCashSupply() {
        return ((this.NrNotes_20pounds*20)+(this.NrNotes_10pounds*10)+(this.NrNotes_5pounds*5)+(this.NrCoins_2pounds*2)+(this.NrCoins_1pound));
    }

    public int howManyPenceInZooCashSupply() {
        return ((this.NrCoins_50p*50)+(this.NrCoins_20p*20)+(this.NrCoins_10p*10));
    }
    
    
    public void setNrNotes_20pounds(int noteCount) {
        this.NrNotes_20pounds += noteCount;
    }

    public void setNrNotes_10pounds(int noteCount) {
        this.NrNotes_10pounds += noteCount;
    }


    public void setNrNotes_5pounds(int noteCount){
        this.NrNotes_5pounds += noteCount;
    }


    public void setNrCoins_2pounds(int coinCount){ this.NrCoins_2pounds += coinCount;}


    public void setNrCoins_1pound(int coinCount) { this.NrCoins_1pound += coinCount;}


    public void setNrCoins_50p(int coinCount) {
        this.NrCoins_50p += coinCount;}


    public void setNrCoins_20p(int coinCount) {
        this.NrCoins_20p += coinCount;}


    public void setNrCoins_10p(int coinCount) {{
        this.NrCoins_10p += coinCount;}}

        // ---------------- GETTERS ----------------


    public int getNrNotes_20pounds(){
        return this.NrNotes_20pounds;
    }

    public int getNrNotes_10pounds(){
        return this.NrNotes_10pounds;
    }

    public int getNrNotes_5pounds() {
        return this.NrNotes_5pounds;
    }

    public int getNrCoins_2pounds(){
        return this.NrCoins_2pounds;
    }

    public int getNrCoins_1pound(){
        return this.NrCoins_1pound;
    }

    public int getNrCoins_50p(){
        return this.NrCoins_50p;
    }

    public int getNrCoins_20p(){
        return this.NrCoins_20p;
    }

    public int getNrCoins_10p(){
        return this.NrCoins_10p;
    }


}

