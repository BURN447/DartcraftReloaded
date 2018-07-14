package burn447.dartcraftReloaded.util;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;

/**
 * Created by BURN447 on 6/16/2018.
 */
public class MobUtil {

    public static void removeCreeperExplodeAI(EntityCreeper entity){
        for(Object a : entity.tasks.taskEntries.toArray()){
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;

            if(entity instanceof EntityCreeper && (ai instanceof EntityAICreeperSwell || ai instanceof EntityAIAttackMelee)){
                entity.tasks.removeTask(ai);
                entity.setCreeperState(-1);
            }
        }

        for(Object a : entity.targetTasks.taskEntries.toArray()){
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;

            if(entity instanceof EntityCreeper && ai instanceof EntityAINearestAttackableTarget){
                entity.targetTasks.removeTask(ai);
                entity.setCreeperState(-1);
            }
        }
    }
}
