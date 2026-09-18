
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    //String source="Data/ratedmovies_short.csv";
    //String source="Data/ratedmoviesfull.csv";
    //String source="Data/ratings_short.csv";
    //String source="Data/ratings.csv";
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> arr=new ArrayList<>();
        FileResource fr=new FileResource(filename);
        CSVParser parser=fr.getCSVParser(true);
        for(CSVRecord r: parser){ 
            String id       =r.get(0);
            String title    =r.get(1);
            String year     =r.get(2);
            String genres   =r.get(4);
            String director =r.get(5);
            String country  =r.get(3);
            String poster   =r.get(7);
            int minutes     =Integer.parseInt(r.get(6));
            arr.add(new Movie(id,title,year,genres,director,country,poster,minutes));
        }
        //System.out.println("Number of movies loaded= "+arr.size());
        return arr;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> arr=new ArrayList<>();
        FileResource fr=new FileResource(filename);
        CSVParser parser=fr.getCSVParser(true);
        for(CSVRecord rec: parser){ 
            String rater_id =rec.get(0);
            String item_id  =rec.get(1);
            Double value    =Double.parseDouble(rec.get(2));
            Rater r=new EfficientRater(rater_id);
            if(arr.contains(r)){
                //r.addRating(item_id,value); // Will not work
                int index=arr.indexOf(r);
                arr.get(index).addRating(item_id,value);
            }
            else{
                arr.add(r);
                r.addRating(item_id,value);
            }
        }
        //System.out.println("Number of Raters loaded= "+arr.size());
        return arr;
    }
    
    /*
    public void testLoadMovies(){
        ArrayList<Movie> arr=loadMovies(source);
        printArr(arr);
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> arr=loadRaters(source);
        printArr(arr);
    }
    
    private <T> void printArr(ArrayList<T> genericArray){
       for(T t: genericArray){
            System.out.println(t);
        } 
    }
    */
}
