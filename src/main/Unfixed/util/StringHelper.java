package burn447.dartcraftReloaded.util;

import org.lwjgl.input.Keyboard;

/**
 * Created by BURN447 on 6/11/2018.
 */
public final class StringHelper {

    private StringHelper(){ }

    public static String toString(Object o, String nullDefault){
        return (o != null) ? o.toString() : nullDefault;
    }

    public static boolean isShiftKeyDown(){
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static boolean displayShiftForDetail = true;

}
