package burn447.dartcraftReloaded.Potion.Potions;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionMagnet extends Potion {
    public PotionMagnet() {
        super(false, 0);
        this.setRegistryName("magnet");
        this.setPotionName("magnet");
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }

    @Override
    public boolean hasStatusIcon() {
        return true;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
