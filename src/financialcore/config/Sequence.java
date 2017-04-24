package financialcore.config;

import databaseLayer.contextLayer.ContextLayer;

/**
 * Created by ganzo on 4/13/17.
 */
public class Sequence {

    //private static int tranNo = 0;


    public static int getTranNo(){
        return ContextLayer.Model().Transactions().getLastTransactionNumber();
    }
    
}
