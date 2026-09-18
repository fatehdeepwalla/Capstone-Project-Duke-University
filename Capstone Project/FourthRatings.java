
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        RaterDatabase.initialize("Data/ratings.csv");
    }
    
    private double dotProduct(Rater me, Rater other){
        double dot=0;
        ArrayList<String> meList=me.getItemsRated();
        ArrayList<String> otherList=other.getItemsRated();
        
        for(String s: meList){
            if(other.hasRating(s)){
                dot+=(me.getRating(s)-5)*(other.getRating(s)-5);
            }
        
        }
        return dot;
    }
    
        
    private ArrayList<Rating> getSimilarities(String raterID){
        Rater me = RaterDatabase.getRater(raterID);
        ArrayList<Rating> arr=new ArrayList<>();
        for(Rater other:RaterDatabase.getRaters()){
            if(!other.equals(me) && dotProduct(me,other)>0){
                arr.add(new Rating (other.getID(),dotProduct(me,other)));
            }
        }
        
        
        //System.out.println("#######Similarity Calculation Start##########");
        //System.out.println("People with positive similarity with RaterID "+raterID+" = "+arr.size());
        //printArr(arr);
        //System.out.println("#######Similarity Calculation End##########");
        //System.out.println();
        
        Collections.sort(arr,Collections.reverseOrder());
        return arr;
    }
    
    public ArrayList<Rating> getSimilarRating(String raterID, int minRaters, int numSimilarRaters){
        AllFilters F=new AllFilters();
        F.addFilter(new TrueFilter());
        
        F.addFilter(new YearAfterFilter(1975));
        //F.addFilter(new GenreFilter("Drama"));
        F.addFilter(new MinutesFilter(70,200));
        //F.addFilter(new DirectorFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        
        ArrayList<String> myMovies      = MovieDatabase.filterBy(F);
        ArrayList<Rating> similarRaters = getSimilarities(raterID);
        ArrayList<Rating> result        = new ArrayList<>();
        
        for(String m: myMovies){
            double WeightedSum=0;
            int num=0;
            for(int i=0;i<Math.min(numSimilarRaters, similarRaters.size());i++){
                Rating rating       = similarRaters.get(i);
                String ID           = rating.getItem();
                double similarity   = rating.getValue();
                
                Rater rater         = RaterDatabase.getRater(ID);
                double movieRating  = rater.getRating(m);
                
                if(movieRating!=-1) {
                    WeightedSum+=movieRating*similarity;
                    num++;
                }
            }
            //System.out.println("MusicID= "+m+" ## Average Rating= "+avg);
            if(num>=minRaters) result.add(new Rating(m, WeightedSum/num));
        }
        
        //System.out.println("Weighted Average for RaterID= "+raterID);
        //System.out.println("Number of Movies with rating more than "+minRaters+" Raters");
        //System.out.println("With Filters"+F);
        //System.out.println("#### "+result.size()+"\n");
        
        Collections.sort(result,Collections.reverseOrder());
        return result;
    }

    
    public void printAverageRatingALL(){
        
        ArrayList<Rating> arr=getSimilarRating("735",5,10);
        for(Rating r: arr){
            StringBuilder sb=new StringBuilder();
            
            //sb.append(r.getValue());
            
            /*
            sb.append("\t");
            sb.append("[");
            sb.append(MovieDatabase.getYear(r.getItem()));
            sb.append("]");
            
            sb.append("\t");
            sb.append("[");
            sb.append(MovieDatabase.getMinutes(r.getItem()));
            sb.append("]");
            */
           
            //sb.append("\t");
            //sb.append("[");
            sb.append(MovieDatabase.getTitle(r.getItem()));
            //sb.append("]");
            
            /*
            sb.append("[");
            sb.append(MovieDatabase.getGenres(r.getItem()));
            sb.append("]");
            
            sb.append("[");
            sb.append(MovieDatabase.getDirector(r.getItem()));
            sb.append("]");
            
            sb.append("[");
            sb.append(r.getItem());
            sb.append("]");
            */
           
            System.out.println(sb); 
        }
    }
    
    private <T> void printArr(ArrayList<T> genericArray){
       for(T t: genericArray){
            System.out.println(t);
        } 
    }
}
    
   