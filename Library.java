/*
 * Library class 
 * Assignment 6: Use What Your Parent (Class) Gave You
 * @author Vivian Wei
 * @version 4 November 2022
 */
import java.util.Hashtable;
import java.util.Set; //import Set to iterate with for loops

public class Library extends Building{
    /* Subclass attributes */
    private Hashtable<String, Boolean> collection;//An hashtable containing each book's title and author and its availability to check out
    private boolean hasElevator;//Whether this library has an elevator
    
    /* Overloaded constructor with name, address, nFloors only */
    public Library(String name, String address,int nFloors){
      super(name, address, nFloors);//Inherited attributes from parent class
      this.collection = new Hashtable<String, Boolean>();//initialize an empty hashtable
    }

    /* Full Constructor
     * @param name
     * @param address
     * @param nFloors
     * @param hasElevator
     */
    public Library(String name, String address, int nFloors, boolean elevator) {
      super(name, address, nFloors);//Inherited attributes from parent class
      this.collection = new Hashtable<String, Boolean>();//initialize an empty hashtable
      this.hasElevator = elevator;
    }

    /* Add book's author and title to the library's collection
     * @param title
     */
    public void addTitle(String title){
      this.collection.put(title,true);
      System.out.println("Adding "+title+" to collection...");
    }
    
    /* Remove the book from the  library's collection and return the title that we removed
     * @return title
     */
    public String removeTitle(String title){
      this.collection.remove(title);
      System.out.println("Removing "+title+"...");
      return title;
    } 

    /* Check out the book from the library's collection
     * @param title
     */
    public void checkOut(String title){
      this.collection.replace(title,false);//replace the bolean to false for the specified key
      System.out.println("Checking out "+title+"...");
    }

    /* Overload checkOut method with a specified days duration
     * @param name
     * @param days
     */
    public void checkOut(String title, int days){
      this.collection.replace(title,false);//replace the bolean to false for the specified key
      System.out.println("Checking out "+title+"for"+days+"days...");
    }

    /* Return the book to the library's collection
     * @param title
     */
    public void returnbook(String title){
      this.collection.replace(title,true);//replace the bolean to true for the specified key
      System.out.println("Returning "+title+"...");
    }

    /* Check if the collection contains the book
     * @param title
     * @return true if the title appears as a key in the Libary's collection, false otherwise 
     */
    public boolean containsTitle(String title){
      if(this.collection.containsKey(title)){
        System.out.println(title+" is in the collection.");
        return true;
      }else{
        System.out.println(title+" is not in the collection.");
        return false;
      }
    } 

    /* Check if the book is available for check out
     * @param title
     * @return true if the title is currently available, false otherwise 
     * @throw RuntimeException
     */
    public boolean isAvailable(String title){
      if (containsTitle(title)){
        if (this.collection.get(title)){
          System.out.println("This book is available to checkout.");
          return true;
        }else{
          System.out.println("This book is not available to checkout.");
          return false;
        }
      }else{
        throw new RuntimeException("Unable to check for availability because the library does not contain the requested book.");
      }
    }
      
    /* prints out the entire collection in an easy-to-read way (including checkout status) */
    public void printCollection(){
        Set<String> books = this.collection.keySet(); //Create a set containing only the keys from the hashtable
        System.out.println("*** Here is the entire collection ***");
        //iterate the set with for loop
        for(String title: books){
          //use if-else to separate the true/false value
          if(this.collection.get(title)){
            System.out.println(title + " (Available)");
          }else{
            System.out.println(title + " (Currently checked out)");
          }
        }
    }
    /*override parent showOptions() */
    public void showOptions() {
      super.showOptions();
      System.out.println(" + addTitle()\n + removeTitle()\n + checkOut()\n + returnbook()\n + containsTitle()\n + isAvailable()\n + printCollection()");
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
      Library myLibrary = new Library("Nielson Library", "7 Nielson Drive",4, true);
      System.out.println("You have built a library: 📖");
      System.out.println(myLibrary);
      myLibrary.addTitle("The Lorax by Dr. Seuss");
      myLibrary.addTitle("Harry Potter by J.K.Rowling");
      myLibrary.addTitle("Little Prince by Antoine de Saint-Exupéry");
      System.out.println(myLibrary.collection);
      myLibrary.printCollection();
      myLibrary.containsTitle("The Lorax by Dr. Seuss");
      myLibrary.checkOut("The Lorax by Dr. Seuss");
      myLibrary.isAvailable("The Lorax by Dr. Seuss");
      myLibrary.returnbook("The Lorax by Dr. Seuss");
      myLibrary.removeTitle("The Lorax by Dr. Seuss");
      myLibrary.printCollection();
      myLibrary.isAvailable("Harry Potter by J.K.Rowling");
      //This last code test to throw run time exception when trying to check the avalability of a book outside the collection
      // myLibrary.isAvailable("The Lorax by Dr. Seuss"); 
      myLibrary.showOptions();
      myLibrary.enter();
      myLibrary.goToFloor(3);
    }
  }