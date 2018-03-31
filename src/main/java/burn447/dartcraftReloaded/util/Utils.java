package burn447.dartcraftReloaded.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class Utils {

    public static Logger logger;

    public static Logger getLogger(){
        if(logger == null){
            logger = LogManager.getFormatterLogger(References.modId);
        }
        return logger;
    }
}
