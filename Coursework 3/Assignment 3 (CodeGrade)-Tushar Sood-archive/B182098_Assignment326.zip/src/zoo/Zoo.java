package zoo;

import animals.*;
import areas.*;
import dataStructures.CashCount;
import dataStructures.ICashCount;

import java.util.*;


public class Zoo implements IZoo {

    private int ticketPricePounds;
    private int ticketPricePence;
    public CashCount cashSupplyOfTheZoo = new CashCount();
//    private ICashCount cashInserted;
//    private ICashCount cashInserted1;
//    private ICashCount iCashCount;


    public Zoo() {
        areaDetails.put(0, new Entrance());
    }

    private Hashtable<Integer, IArea> areaDetails = new Hashtable<>(); //stores list of area IDs and type of area


    /**
     * Adds a new area to the zoo.
     *
     * @param area The area to be added.
     * @return An ID by which the added area can be uniquely identified
     * NOT DONE
     */
    public int addArea(IArea area) {
        if (area instanceof Entrance) {
            return 0;
        } else {
            int maxAreaID = Collections.max(areaDetails.keySet());  //finding the largest area ID i.e. latest added area
            areaDetails.put(maxAreaID + 1, area);
            return (maxAreaID + 1);
        }
    }

    /**
     * Removes the specified area from the zoo.
     */
    public void removeArea(int areaId) {
        if (areaId == 0) {
        } else {
            for (int areaIDofAdjacent : areaDetails.get(areaId).getAdjacentAreas()) {
                areaDetails.get(areaIDofAdjacent).connections.remove(areaId);
            }
            areaDetails.remove(areaId);
        }
    }

    /**
     * Returns the area associated with the given ID.
     *
     * @param areaId The ID of the area to be fetched.
     * @return The area corresponding to the given ID.
     */
    public IArea getArea(int areaId) {
        return areaDetails.get(areaId);
    }

    /**
     * Tries to add the given animal to the specified area
     *
     * @param areaId The ID of the area the animal is to be added to.
     * @param animal The animal to be added.
     * @return Returns a code indicating success or failure. See {@link Codes}
     * NOT DONE.
     */
    public byte addAnimal(int areaId, Animal animal) {
        IArea area = areaDetails.get(areaId);
        Animal[] animalsHere;
        if (area instanceof AnimalHabitat) {
            animalsHere = ((AnimalHabitat) area).animalsHere;
        }
        else{return (byte) 1;}

        ArrayList<Integer> errors = new ArrayList<>();
        boolean atLeastOneEmptySpot = false;
        int whereToAddAnimal = 0;

        if (!(area instanceof AnimalHabitat)) { errors.add(1); }
        else if ((animal instanceof AerialAnimal && !(area instanceof Cage)) || (animal instanceof AquaticAnimal && !(area instanceof Aquarium)) || (animal instanceof TerrestrialAnimal && !(area instanceof Enclosure))) { errors.add(2); }
        else {
            for (int i = 0; i < animalsHere.length; i++) {
                if (!(animal.isCompatibleWith(animalsHere[i]))) {
                    errors.add(4);
                }
                else if (animalsHere[i] == null) {
                    atLeastOneEmptySpot = true;
                    whereToAddAnimal = i;
                }
            }
            if (atLeastOneEmptySpot == false) {
                errors.add(3);
            }
        }

        if (errors.isEmpty()) {
            ((AnimalHabitat) areaDetails.get(areaId)).animalsHere[whereToAddAnimal] = animal;
            return (byte) 0;
        } else {
            Integer maxINT = Collections.max(errors);
            //            if (findMaxInArray(errors) == 3 ) {
//                return (byte) 0;
//            }
//            else {
            return (byte) (int) maxINT;
        }
    }

    private byte findMaxInArray(ArrayList<Integer> array) {
        int max = array.get(0);  //get first element, convert to byte and set as max
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return (byte) max;
    }

    private boolean anyEmptySpaceInArray(Animal[] animalsHere) {
        int i =0;
        for (Animal animal: animalsHere) {
            if (animal==null) {
                return true;
            }
            else{ }
            i++;
        }
        return false;
    }

    // ---------------- INTERMEDIATE ----------------

    /**
     * Allows visitors to move between areas in the given direction (from -> to).
     *
     * @param fromAreaId The ID of the area from which the destination is to be accessible.
     * @param toAreaId   The ID of the destination area.
     */

    public void connectAreas(int fromAreaId, int toAreaId) {
        if (!(areaDetails.get(fromAreaId).getAdjacentAreas().contains(toAreaId))) {
            areaDetails.get(fromAreaId).addConnection(toAreaId, this);
        }
    }


    /**
     * Checks if the given path obeys the one-way system.
     *
     * @param areaIDs The path is provided as a list of area IDs. It starts with the area ID at index 0.
     * @return Returns true iff visitors are allowed to visit the areas in the order given by the passed in list.
     */

    public boolean isPathAllowed(ArrayList<Integer> areaIDs) {
        for (int i = 0; i < areaIDs.size(); i++) {
            if (i<areaIDs.size()-1) {
                // check if the current path contains the ID for the next path. If it does, it means the two are connected
                if (areaDetails.get(areaIDs.get(i)).connections.contains(areaIDs.get(i + 1))) {
                } else {
                    return false;
                }
            }
                else{}//maybe store this as a var?

            }

        return true;
    }




    /**
     * Visits the areas in the specified order and records the names of all animals seen.
     *
     * @param areaIdsVisited Areas IDs in the order they were visited.
     * @return Returns a list of the names of all animals seen during the visit in the order they were seen.
     */
    public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited) {
        ArrayList<String> animalsSeen = new ArrayList<>();
        if (!isPathAllowed(areaIdsVisited)) {
            return null;
        } else {
            for (Integer areaID : areaIdsVisited) {
                if (!(areaDetails.get(areaID) instanceof AnimalHabitat)) { }
                else{
                    for (Animal a : ((AnimalHabitat) areaDetails.get(areaID)).animalsHere) {
                        animalsSeen.add(a.getNickname());
                    }
                }
            }
            return animalsSeen;
        }
    }



    /**
     * Finds all areas that cannot be reached from the entrance of the zoo.
     *
     * @return The IDs of all inaccessible areas (unordered).
     */
    public ArrayList<Integer> findUnreachableAreas() {
        ArrayList<Integer> areasUnReachableByEntrance = new ArrayList<>();
        ArrayList<Integer> areasReachableByEntrance = areaDetails.get(0).connections;

        for (Integer areaID : areaDetails.keySet()) {
            if (areaID == 0) { } //if the current area is the entrance
            else if (!(areasReachableByEntrance.contains(areaID))) {
                areasUnReachableByEntrance.add(areaID);
            } //check if the arraylist of connections of the entrance contains current area. if it doesn't add it to the arraylist of areas not reachable from entrance
        }
        return areasUnReachableByEntrance;
    }


    // ---------------- ADVANCED ----------------

    /**
     * Sets a new ticket price in pounds and pence.
     *
     * @param pounds The first part of the cost before the point e.g. 17 for a ticket that costs £17.50
     * @param pence  The second part of the cost after the point e.g. 50 for a ticket that costs £17.50
     */
    public void setEntranceFee(int pounds, int pence) {
        ticketPricePence = pence;
        ticketPricePounds = pounds;
    }

    public String getEntranceFee() {
        String fee = "£" + this.ticketPricePence + "." + this.ticketPricePounds;
        System.out.println(fee);
        return fee;
    }

    /**
     * Stocks the ticket machine with the provided pool of cash.
     *
     * @param stock The number of notes and coins of different denominations available.
     */
    public void setCashSupply(ICashCount stock) {
        cashSupplyOfTheZoo.setNrNotes_20pounds(stock.getNrNotes_20pounds());
        cashSupplyOfTheZoo.setNrNotes_10pounds(stock.getNrNotes_10pounds());
        cashSupplyOfTheZoo.setNrNotes_5pounds(stock.getNrNotes_5pounds());
        cashSupplyOfTheZoo.setNrCoins_2pounds(stock.getNrCoins_2pounds());
        cashSupplyOfTheZoo.setNrCoins_1pound(stock.getNrCoins_1pound());
        cashSupplyOfTheZoo.setNrCoins_50p(stock.getNrCoins_50p());
        cashSupplyOfTheZoo.setNrCoins_10p(stock.getNrCoins_10p());
    }

    /**
     * Used to inspect the ticket machine's currently available pool of cash.
     *
     * @return The amount of each note and coin currently in the machine.
     */
    public ICashCount getCashSupply() {
        return cashSupplyOfTheZoo;
    }

    /**
     * Takes an amount of cash inserted into the ticket machine and returns the appropriate change
     * (if any) after deducting the amount of the entrance fee as set by @setEntranceFee.
     *
     * @param cashInserted The notes and coins inserted by the user buying a ticket.
     * @return The change returned to the user (see assignment instructions for precise specification).
     */

    public ICashCount payEntranceFee(ICashCount cashInserted) {
        int poundsInserted = ICashCountToPoundsAndPence(cashInserted)[0];
        int penceInserted = ICashCountToPoundsAndPence(cashInserted)[1];
        CashCount cashInsertedAsACashCount = (CashCount) cashInserted;

        //if the amount of money inserted is exactly the price of ticket
        if ((poundsInserted == ticketPricePounds) && (penceInserted == ticketPricePence)) {
            setCashSupply(cashInserted);
            return new CashCount();
        }

        //if not enough money is inserted
        else if ((poundsInserted < ticketPricePounds) || (poundsInserted == ticketPricePounds && penceInserted < ticketPricePence)) {
            return cashInserted;
        }

        //if too much money is inserted
        else if ((poundsInserted > ticketPricePounds) || (poundsInserted == ticketPricePounds && penceInserted > ticketPricePence)) {
            //calculating change
            int changePounds = ticketPricePounds - poundsInserted;
            int changePence = ticketPricePence - penceInserted;

            //if change is more than money in zoo's cashSupply
            if (changePounds > cashInsertedAsACashCount.howManyPoundsInZooCashSupply() || changePounds == cashInsertedAsACashCount.howManyPoundsInZooCashSupply() && changePence > cashInsertedAsACashCount.howManyPenceInZooCashSupply()) {
                return cashInserted;
            }

            //if change is payable
            else {
                setCashSupply(changeCalculatorAndOptimizer(ticketPricePounds, ticketPricePence));

                //how do we add the ticket profit to the zoo's cashsupply in denomination appropriate to the cash inserted by the customer\

                return changeCalculatorAndOptimizer(changePounds, changePence);
            }

        }
        return cashInserted; // ARE YOU SURE THAT THIS IS WHAT SHOULD BE RETURNED??

    }

    /**
     * Converts ICashCount inserted into equivalent amount of pounds and pence. Then, if more there are more than 100 pence,
     * calls on excessPenceToPoundsLogic to convert the excess into equivalent amount of pounds, and add to pounds.
     *
     * @param cashInserted ICashCount inserted
     * @return rReturn the updated Pounds and Pence in an Integer Array.
     */

    public Integer[] ICashCountToPoundsAndPence(ICashCount cashInserted) {
        int pounds_inserted = cashInserted.getNrNotes_20pounds() + cashInserted.getNrNotes_10pounds() + cashInserted.getNrNotes_5pounds() + cashInserted.getNrCoins_2pounds() + cashInserted.getNrCoins_1pound();
        int pence_inserted = cashInserted.getNrCoins_50p() + cashInserted.getNrCoins_20p() + cashInserted.getNrCoins_10p();


        if (pence_inserted < 100) {
        } else {
            Integer[] return_Array;
            return_Array = excessPenceToPoundsLogic(pounds_inserted, pence_inserted);
            return return_Array;
        }
        Integer[] return_Array = new Integer[2];
        return_Array[0] = pounds_inserted;
        return_Array[1] = pence_inserted;

        return return_Array;
    }

    /**
     * Given pounds and pence, Converts excess pence to equivalent amount of pounds. Updates the amount of Pounds and
     * amount of Pence
     *
     * @param pounds_inserted
     * @param pence_inserted
     * @return array with updated pounds and pence
     */

    private Integer[] excessPenceToPoundsLogic(Integer pounds_inserted, Integer pence_inserted) {
        Integer[] changeArray = new Integer[2];

        int twentyPound = Math.floorDiv(pence_inserted, 2000);                   // how many times 50 goes into change
        int twentyChange = pence_inserted - 2000 * twentyPound;   // remaining change = change - (50 * how many times 50 goes into change)
        int tenPound = Math.floorDiv(twentyChange, 1000);                 // see how many times 20 goes into the remaining change
        int tenChange = twentyChange - 1000 * tenPound;   // subtract how many 20s go into twentyChange & store as new var tenChange
        int fivePound = Math.floorDiv(tenChange, 500);
        int fiveChange = tenChange - 500 * fivePound;
        int twoPound = Math.floorDiv(fiveChange, 200);
        int twoChange = fiveChange - 200 * twoPound;
        int onePound = Math.floorDiv(twoChange, 100);
        int oneChange = twoChange - 100 * onePound;

        pounds_inserted += twentyPound * 20 + tenPound * 10 + fivePound * 5 + twoPound * 2 + onePound;

        changeArray[0] = pounds_inserted;
        changeArray[1] = oneChange;

        return changeArray;
    }

    /**
     * Converts given pounds and pence into pounds, then calculates change optimally, prioritizing big denominations
     *
     * @param pounds
     * @param pence
     * @return CashCount instance containing change ready for emission
     */

    public CashCount changeCalculatorAndOptimizer(int pounds, int pence) {
        int totalPenceInserted = (pounds * 100) + pence;

        CashCount changeToReturn = new CashCount();

        int twentyPound = Math.floorDiv(totalPenceInserted, 2000);                   // how many times 20 goes into pence
        changeToReturn.setNrNotes_20pounds(twentyPound);
        int moneyLeftAfterTakingAllTwentiesOut = totalPenceInserted - 2000 * twentyPound;   // remaining change = change - (50 * how many times 50 goes into change)
        int tenPound = Math.floorDiv(moneyLeftAfterTakingAllTwentiesOut, 1000);                 // see how many times 20 goes into the remaining change
        changeToReturn.setNrNotes_10pounds(tenPound);
        int moneyLeftAfterTakingAllTensOut = moneyLeftAfterTakingAllTwentiesOut - 1000 * tenPound;   // subtract how many 20s go into moneyLeftAfterTakingAllTwentiesOut & store as new var moneyLeftAfterTakingAllTensOut
        int fivePound = Math.floorDiv(moneyLeftAfterTakingAllTensOut, 500);
        changeToReturn.setNrNotes_5pounds(fivePound);
        int moneyLeftAfterTakingAllFivesOut = moneyLeftAfterTakingAllTensOut - 500 * fivePound;
        int twoPound = Math.floorDiv(moneyLeftAfterTakingAllFivesOut, 200);
        changeToReturn.setNrCoins_2pounds(twoPound);
        int moneyLeftAfterTakingAllTwosOut = moneyLeftAfterTakingAllFivesOut - 200 * twoPound;
        int onePound = Math.floorDiv(moneyLeftAfterTakingAllTwosOut, 100);
        changeToReturn.setNrCoins_1pound(onePound);
        int moneyLeftAfterTakingAllOnesOut = moneyLeftAfterTakingAllTwosOut - 100 * onePound;
        int fiftypence = Math.floorDiv(moneyLeftAfterTakingAllOnesOut, 50);
        changeToReturn.setNrCoins_50p(fiftypence);
        int moneyLeftAfterTakingAllFiftyPenceOut = moneyLeftAfterTakingAllOnesOut - 50 * fiftypence;
        int twentypence = Math.floorDiv(moneyLeftAfterTakingAllFiftyPenceOut, 20);
        changeToReturn.setNrCoins_20p(twentypence);
        int moneyLeftAfterTakingAllTwentyPenceOut = moneyLeftAfterTakingAllOnesOut - 20 * twentypence;
        int tenpence = Math.floorDiv(moneyLeftAfterTakingAllTwentyPenceOut, 10);
        changeToReturn.setNrCoins_10p(tenpence);

        return changeToReturn;
    }
}
