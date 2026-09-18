
/**
 * Write a description of QueryRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class QueryRater {
    //String source="Data/ratedmovies_short.csv";
    //String source="Data/ratedmoviesfull.csv";
    //String source="Data/ratings_short.csv";
    String source="Data/ratings.csv";
    
    public void numberOfRatingByUser(String RaterID){
        ArrayList<Rater> arr=new FirstRatings().loadRaters(source);
        Rater search=new EfficientRater(RaterID);
        int index=arr.indexOf(search);
        int num=arr.get(index).numRatings();
        System.out.println("Number of ratings for RaterID= "+RaterID +" is ### "+num);
    }
    
    public void maximumRatingsByRater(){
        ArrayList<Rater> arr=new FirstRatings().loadRaters(source);
        int maxIndex=0;
        int maxRating=arr.get(0).numRatings();
        
        for(int i=0;i<arr.size();i++){
            Rater r=arr.get(i);
            if(r.numRatings()>maxRating){
                maxIndex=i;
                maxRating=r.numRatings();
            }
        }
        
        System.out.println("Maximum Rating for an given User is= "+maxRating);
        System.out.println("Rater ID's for such Raters are ::");
        
        for(int i=0;i<arr.size();i++){
            Rater r=arr.get(i);
            if(r.numRatings()==maxRating){
                System.out.println(r.getID());
            }
        }
    }
    
    public void numberOfRatingForMovie(String MovieID){
        ArrayList<Rater> arr=new FirstRatings().loadRaters(source);
        int num=0;
        for(Rater r: arr){
            if(r.hasRating(MovieID)) num++;
        }
        System.out.println("Number of ratings for Movie= "+MovieID +" is ### "+num);
    }
    
    public void numberOfDifferentMovies(){
        ArrayList<Rater> arr=new FirstRatings().loadRaters(source);
        ArrayList<String> movie=new ArrayList<>();
    
        for(Rater r: arr){
            ArrayList<String> temp=r.getItemsRated();
            for(String movieID: temp){
                if(!movie.contains(movieID)) movie.add(movieID);
            }
        }
        System.out.println("Number of Different Movies Rated= "+movie.size());
    }
}
