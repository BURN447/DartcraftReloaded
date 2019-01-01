package burn447.dartcraftReloaded.capablilities.PlayerModifier;

/**
 * Created by BURN447 on 6/21/2018.
 */
public interface IPlayerModifier {

    float getAttackDamage();
    void setAttackDamage(float newDamage);

    float getWingPower();
    void setWingPower(float newWingPower);

    float getFlightTimer();
    void subtractFlightTimer();
    void setFlightTimer(float newFlightCounter);
}
