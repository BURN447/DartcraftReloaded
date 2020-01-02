package burn447.dartcraftReloaded.capablilities.PlayerModifier;

/**
 * Created by BURN447 on 6/21/2018.
 */
public interface IPlayerModifier {

    //Damage
    float getAttackDamage();
    void setAttackDamage(float newDamage);
    void addAttackDamage(float newDamage);

    //Wing
    float getWingPower();
    void setWingPower(float newWingPower);

    float getFlightTimer();
    void subtractFlightTimer();
    void setFlightTimer(float newFlightCounter);

    //Heat
    float getHeatDamage();
    void setHeatDamage(float newDamage);
    void addHeatDamage(float newDamage);

    float getDamage();
    void setDamage(float newDamage);

    //Luck
    int getLuckLevel();
    void setLuckLevel(int newLuck);
    void incrementLuckLevel(int newLuck);
}
