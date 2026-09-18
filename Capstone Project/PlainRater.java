
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }    
        return false;
    }

    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }      
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }    
        return list;
    }
    
    public String getID() {
        return myID;
    }
    
    public boolean equals(Object other){
        PlainRater r=(PlainRater)other;
        if(myID.equals(r.myID)) return true;
        return false;
    }
    
    public int hashcode(){
        return this.toString().hashCode();
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("---------------------------------------------\n");
        sb.append("Rater ID= "+ myID+ " ## ");
        sb.append("Number of ratings = "+ myRatings.size()+"\n");
        for(Rating r: myRatings){
            sb.append(r+"\n");
        }
        sb.append("---------------------------------------------");
        return sb.toString();
    }
}
