package Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author biruk
 */
public class Validate {
    
    public static boolean isValidName(String name)
    {
        String NAME_PATTER = "^[a-zA-Z]$";
        Pattern pattern = Pattern.compile(NAME_PATTER);
        Matcher regexMather = pattern.matcher(name);
        
        if (!regexMather.matches())
        {
            return false;
        } 
        else
        {
            return true;
        }
    }
    
    
}
