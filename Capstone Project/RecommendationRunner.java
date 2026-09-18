
/**
 * Write a description of RecommenderRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate (){
        ThirdRatings obj=new ThirdRatings();
        ArrayList<Rating> arr = obj.getAverageRatingALL();
        
        ArrayList<String> result=new ArrayList<>();
        for(int i=0;i<10;i++){
            result.add(arr.get(i).getItem());
        }
        return result;
    }
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings obj=new FourthRatings();
        ArrayList<Rating> arr = obj.getSimilarRating(webRaterID,3,20);
        if(arr.isEmpty()) {
            System.out.println("Nothing Found #### Your rating was very orthodox ##### Kindly Change it & Retry ####");
            System.out.println("#### This happens when you give Really Low ratings #### Kindly Increase Rating for few Movies for System to recommend");
            return;
        }
        
        ArrayList<Rating> result=new ArrayList<>();
        for(int i=0;i<Math.min(20,arr.size());i++){
            result.add(arr.get(i));
        }
        
        // Printing
        System.out.println("<h2>Recommended Movies</h2>");
        System.out.println("<table border='1'>");
        System.out.println(
            "<tr>" +
            "<th>Title</th>" +
            "<th>Year</th>" +
            "<th>Genre</th>" +
            "<th>Minutes</th>" +
            "<th>Directors</th>" +
            "<th>Predicted Rating</th>" +
            "</tr>"
        );

        for(int i = 0; i < Math.min(15, arr.size()); i++) {
            Rating r = arr.get(i);
            String id = r.getItem();
            System.out.println("<tr>");

            System.out.println(
                "<td>" + MovieDatabase.getTitle(id) + "</td>"
            );

            System.out.println(
                "<td>" + MovieDatabase.getYear(id) + "</td>"
            );

            System.out.println(
                "<td>" + MovieDatabase.getGenres(id) + "</td>"
            );

            System.out.println(
                "<td>" + MovieDatabase.getMinutes(id) + "</td>"
            );

            System.out.println(
                "<td>" + MovieDatabase.getDirector(id) + "</td>"
            );

            System.out.println(
                "<td>" + String.format("%.2f", r.getValue()) + "</td>"
            );

            System.out.println("</tr>");
        }
        System.out.println("</table>");
    }
}

