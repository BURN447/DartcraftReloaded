package burn447.dartcraftReloaded.advancements;

public class ModTriggers {

    public static final CustomTrigger ADDS_FORCE_MODIFER = new CustomTrigger("added_forceMod");
    public static final CustomTrigger ADDS_DAMAGE_MODIFIER = new CustomTrigger("added_damageMod");
    public static final CustomTrigger ADDS_HEAT_MODIFIER = new CustomTrigger("added_heatMod");
    public static final CustomTrigger ADDS_LUMBER_MODIFIER = new CustomTrigger("added_lumberMod");
    public static final CustomTrigger ADDS_SPEED_MODIFIER = new CustomTrigger("added_speedMod");

    //Array to Register All the Criteria

    public static final CustomTrigger[] TRIGGER_ARRAY = new CustomTrigger[] {
            ADDS_FORCE_MODIFER,
            ADDS_DAMAGE_MODIFIER,
            ADDS_HEAT_MODIFIER,
            ADDS_LUMBER_MODIFIER,
            ADDS_SPEED_MODIFIER
    };


}
