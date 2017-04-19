package financialcore.config;

/**
 * Created by ganzo on 4/13/17.
 */
public class Sequence {

    private static int tranNo = 1;
    private static int custNo = 1;


    public static int getTranNo(){
        return ++tranNo;
    }
    public static int getCustNo(){
        return ++custNo;
    }
}
