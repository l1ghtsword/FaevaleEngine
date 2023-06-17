package ca.lightnet.FaevaleEngine.libs.Utils;

public class BoolUtils {
    public static Boolean toggleBool(Boolean b){
        if (b == null) { return false; }
        return !b;
    }
}
