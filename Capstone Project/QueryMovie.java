
/**
 * Write a description of QueryMovie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class QueryMovie {
    //String source="Data/ratedmovies_short.csv";
    String source="Data/ratedmoviesfull.csv";
    //String source="Data/ratings_short.csv";
    //String source="Data/ratings.csv";
    
    public interface Filter {
        public boolean test(Movie m);
    }
    
    private ArrayList<Movie> Query(ArrayList<Movie> in, Filter f){
        ArrayList<Movie> out=new ArrayList<>();
        for(Movie m: in){
            if(f.test(m)) out.add(m);
        }
        return out;
    }
    
    public void testQuery(){
        ArrayList<Movie> arr=new FirstRatings().loadMovies(source);
        //Query for genres
        //arr=Query(arr,(m)-> m.getGenres().contains("Comedy"));
        //Query for Minutes
        arr=Query(arr,(m)-> m.getMinutes()>150);
        for(Movie m: arr){
            System.out.println(m);
        }
        System.out.println("Number of movies= "+arr.size());
    }
    
    private HashMap<String, Integer> DirectorMovieCount(ArrayList<Movie> arr){
        HashMap<String, Integer> map = new HashMap<>();
        for(Movie m: arr){
            String s = m.getDirector();
            String[] darr = s.split(",");
            for(String d: darr){
                if(!map.containsKey(d)) map.put(d,1);
                else map.put(d,map.get(d)+1);
            }
        }
        return map;
    }
    
    public void maxDirectorMovieCount(){
        ArrayList<Movie> arr=new FirstRatings().loadMovies(source);
        
        HashMap<String, Integer> map = DirectorMovieCount(arr);
        //System.out.println(map);
        System.out.println("Number of Director= "+map.size());
        
        int max=0;
        for(String director: map.keySet()){
            if(map.get(director)>max) max=map.get(director);
        }
        System.out.println("Maximum number of movies directed by a single director is= "+max);
        
        System.out.println("And those directors are ::");
        int i=1;
        for(String director: map.keySet()){
            if(map.get(director)==max){ 
                System.out.println(i+". "+director);
                i++;
            }
        }
    }
    
}
