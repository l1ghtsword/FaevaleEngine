package ca.lightnet.FaevaleEngine.libraries.utilities;

public class BoolUtils {
    public static Boolean toggleBool(Boolean b){
        if (b == null) { return false; }
        return !b;
    }
}
