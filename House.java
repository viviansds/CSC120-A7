/*
 * House class 
 * Assignment 6: Use What Your Parent (Class) Gave You
 * @author Vivian Wei
 * @version 4 November 2022
 */
import java.util.ArrayList;

public class House extends Building {

    /* Subclass attributes */
    private ArrayList<String> residents; // An ArrayList containing the residents' name living in the house
    private boolean hasDiningRoom;//Whether this house has a dining room
    private boolean hasElevator;//Whether this house has an elevator

     /* Overloaded constructor with name, address, nFloors only */
    public House(String name,String address,int nFloors) {
      super(name, address, nFloors);//Inherited attributes from parent class
      this.residents = new ArrayList<String>();//initialize an empty arraylist
    }

    /* Full Constructor
     * @param name
     * @param address
     * @param nFloors
     * @param hasDR
     * @param hasElevator
     */
    public House(String name,String address,int nFloors, boolean hasDR, boolean elevator) {
      super(name, address, nFloors);//Inherited attributes from parent class
      this.residents = new ArrayList<String>();//initialize an empty arraylist
      this.hasDiningRoom = hasDR;
      this.hasElevator=elevator;
    }
    
    /* Accesor for hasDiningRoom
     * @return true if the house has dining room, false otherwise
     */
    public boolean hasDiningRoom(){
      return this.hasDiningRoom;
    }

    /* Accesor for nResidents
     * @return number of residents living in the house
     */
    public int nResidents(){
      return this.residents.size();
    }

    /* Add resident's name to the house
     * @param name
     */
    public void moveIn(String name){
      this.residents.add(name);
      System.out.println(name+" has moved in");
    }
    /* Overload moveIn method with a move in date
     * @param name
     * @param date
     */
    public void moveIn(String name, String date, int roomNum){
      this.residents.add(name);
      System.out.println(name+" has moved in on"+date+ "living in room"+ roomNum);
    }
    
    /* Remove resident's name from the house
     * @param name 
     * @return the name of the person who moved out  
     */
    public String moveOut(String name){
      this.residents.remove(name);
      System.out.println(name+" has moved out");
      return name;
    } 

    /* Overload moveOut method with a move out date
     * @param name
     * @param date
     */
    public String moveOut(String name, String date){
      this.residents.remove(name);
      System.out.println(name+" has moved out on"+date);
      return name;
    } 
    
    /* Check whether or not a given person is a resident of the House
     * @param person
     * @return true if the person's name is contained in the residents arraylist, false otherwise
     */
    public boolean isResident(String person){
      if(this.residents.contains(person)){
        System.out.println(person+" is a resident here.");
        return true;
      }else{
        System.out.println(person+" is not a resident here.");
        return false;
      }
    }
    /*override parent showOptions() */
    public void showOptions() {
      super.showOptions();
      System.out.println(" + hasDiningRoom()\n + nResidents()\n + moveIn()\n + moveOut()\n + isResident()");
    }
    
    /*override parent goToFloor() */
    public void goToFloor(int floorNum) {
      if(hasElevator){
        if (this.activeFloor == -1) {
          throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
      if (floorNum < 1 || floorNum > this.nFloors) {
          throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
        }
      System.out.println("You are now on floor #" + floorNum + " of " + this.name);
      this.activeFloor = floorNum;
      }else{
        throw new RuntimeException("This "+this.name+" does not have an elevator, cannot move to non-adjacent floors.");
      }
    }


    /* Main method for testing */
    public static void main(String[] args) {
      House chapin = new House("Chapin House","3 Chapin Way",4,true,false);
      House cutter = new House("Cutter House","1 Henshaw Ave",4,true,true);
      System.out.println("You have built a house: 🏠");
      System.out.println(chapin);
      System.out.println("Does this house has a dining room?");
      System.out.println(chapin.hasDiningRoom());
      System.out.println("There are "+chapin.nResidents()+" residents");
      chapin.moveIn("Vivian");
      chapin.isResident("Vivian");
      chapin.moveOut("Vivian");
      chapin.isResident("Vivian");
      chapin.showOptions();
      cutter.showOptions();
      cutter.enter();
      cutter.goToFloor(4);
      chapin.enter();
      chapin.goToFloor(2);
    }
}