
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    static String source1="Data/ratedmovies_short.csv";
    //static String source1="Data/ratedmoviesfull.csv";
    
    static String source2="Data/ratings_short.csv";
    //static String source2="Data/ratings.csv";
    
    public SecondRatings() {
        // default constructor
        this(source1, source2);
    }
    
    private SecondRatings(String movieFile, String ratingFile){
        myMovies=new FirstRatings().loadMovies(movieFile);
        myRaters=new FirstRatings().loadRaters(ratingFile);
    }
    
    private String getTitle(String movieID) {
        for(Movie m: myMovies){
            if(m.getID().equals(movieID)) return m.getTitle();
        }
        return "NOT FOUND";
    }
    
    private String getID(String movieTitle) {
        for(Movie m: myMovies){
            if(m.getTitle().equals(movieTitle)) return m.getID();
        }
        return "NOT FOUND";
    }
    
    private ArrayList<Rating> getAverageRatingALL(int minRaters){
        ArrayList<Rating> arr=new ArrayList<>();
        for(Movie m: myMovies){
            double avg=getAverageByID(m.getID(),minRaters);
            if(avg!=0){
                arr.add(new Rating(m.getID(),avg));
                System.out.println("MusicID= "+m.getID()+" ## Average Rating= "+avg);
            }
        }
        return arr;
    }
    
    public double getAverageByID(String movieID, int minRaters){
        double sum=0;
        int num=0;
        for(Rater r: myRaters){
            double temp=r.getRating(movieID);
            if(temp!=-1) {
                sum+=temp;
                num++;
            }
        }
        if(num>=minRaters) return sum/num;
        return 0;
    }
    
    public double getAverageByTitle(String movieTitle, int minRaters){
        return getAverageByID(getID(movieTitle),minRaters);
    }
    
    public void printAverageRating(){
        int minRaters=1;
        ArrayList<Rating> arr=getAverageRatingALL(minRaters);
        Collections.sort(arr);
        for(Rating r: arr){
            System.out.println(r.getValue()+" "+getTitle(r.getItem())); 
        }
        System.out.println("Number of Movies with rating more than "+minRaters+" ## "+arr.size());
    }
}
