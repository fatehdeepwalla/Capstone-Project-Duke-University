
/**
 * Write a description of Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public interface Rater {
    public  void addRating(String item, double rating);
    public boolean hasRating(String item);
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();
    public String getID();
    public boolean equals(Object other);
    public int hashcode();
    public String toString();
}
