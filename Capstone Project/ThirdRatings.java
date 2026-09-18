
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    
    public ThirdRatings() {
        // default constructor
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        RaterDatabase.initialize("Data/ratings.csv");
    }
    
    public ArrayList<Rating> getAverageRatingALL(){
        int minRaters=40;
        AllFilters F=new AllFilters();
        F.addFilter(new TrueFilter());
        
        //F.addFilter(new YearAfterFilter(1990));
        //F.addFilter(new GenreFilter("Drama"));
        //F.addFilter(new MinutesFilter(90,180));
        //F.addFilter(new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollac"));
        ArrayList<String> myMovies = MovieDatabase.filterBy(F);
         
        ArrayList<Rating> arr=new ArrayList<>();
        for(String m: myMovies){
            double avg=getAverageByID(m,minRaters);
            if(avg!=0){
                arr.add(new Rating(m,avg));
                //System.out.println("MusicID= "+m+" ## Average Rating= "+avg);
            }
        }
        //System.out.println("Number of Movies with rating more than "+minRaters);
        //System.out.println("With Filters"+F);
        //System.out.println("#### "+arr.size()+"\n");
        
        Collections.sort(arr,Collections.reverseOrder());
        return arr;
    }

    public double getAverageByID(String movieID, int minRaters){
        double sum=0;
        int num=0;
        for(Rater r: RaterDatabase.getRaters()){
            double temp=r.getRating(movieID);
            if(temp!=-1) {
                sum+=temp;
                num++;
            }
        }
        if(num>=minRaters) return sum/num;
        return 0;
    }
    
        
    private String getID(String movieTitle) {
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String m: myMovies){
            if(MovieDatabase.getTitle(m).equals(movieTitle)) return m;
        }
        return "NOT FOUND";
    }
    
    public double getAverageByTitle(String movieTitle, int minRaters){
        return getAverageByID(getID(movieTitle),minRaters);
    }
    
    public void printAverageRatingALL(){
        ArrayList<Rating> arr=getAverageRatingALL();
        for(Rating r: arr){
            StringBuilder sb=new StringBuilder();
            
            sb.append(r.getValue());
            
            sb.append("\t");
            sb.append("[");
            sb.append(MovieDatabase.getYear(r.getItem()));
            sb.append("]");
            
            sb.append("\t");
            sb.append("[");
            sb.append(MovieDatabase.getMinutes(r.getItem()));
            sb.append("]");
            
            sb.append("\t");
            sb.append("[");
            sb.append(MovieDatabase.getTitle(r.getItem()));
            sb.append("]");
            
            sb.append("[");
            sb.append(MovieDatabase.getGenres(r.getItem()));
            sb.append("]");
            
            sb.append("[");
            sb.append(MovieDatabase.getDirector(r.getItem()));
            sb.append("]");
            
            sb.append("[");
            sb.append(r.getItem());
            sb.append("]");
            
            System.out.println(sb); 
        }
    }
    
}

