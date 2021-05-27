package emris.util;

public class StringUtils
{
    public static boolean strIsInteger(String str)
    {
        try
        {
            Integer.parseInt(str.trim());
            return true;
        }catch (Throwable e)
        {
            return false;
        }
    }
}
