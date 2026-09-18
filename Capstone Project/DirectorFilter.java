
import java.util.*;
public class DirectorFilter implements Filter {
    private String[] d;
    
    public DirectorFilter(String s) {
        d = s.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String directors = MovieDatabase.getDirector(id);
        for (String s : d) {
            if (directors.contains(s.trim())) {
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
        return "DirectorFilter with "+Arrays.toString(d);
    }
}