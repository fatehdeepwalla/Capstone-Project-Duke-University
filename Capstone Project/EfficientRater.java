

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> map;

    public EfficientRater(String id) {
        myID = id;
        map = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        map.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(map.containsKey(item)) return true;  
        return false;
    }

    public double getRating(String item) {
        if(map.containsKey(item)) return map.get(item).getValue();   
        return -1;
    }

    public int numRatings() {
        return map.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s: map.keySet()){
            list.add(s);
        }    
        return list;
    }
    
    public String getID() {
        return myID;
    }
    
    public boolean equals(Object other){
        EfficientRater r=(EfficientRater)other;
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
        sb.append("Number of ratings = "+ map.size()+"\n");
        for(Rating r: map.values()){
            sb.append(r+"\n");
        }
        sb.append("---------------------------------------------");
        return sb.toString();
    }
}


