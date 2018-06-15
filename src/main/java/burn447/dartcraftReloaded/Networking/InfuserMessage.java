package burn447.dartcraftReloaded.Networking;

import burn447.dartcraftReloaded.container.ContainerBlockInfuser;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;

/**
 * Created by BURN447 on 5/29/2018.
 */
public class InfuserMessage extends MessageBase<InfuserMessage> {

    public InfuserMessage(){}

    public boolean isButtonPressed = false;
    private BlockPos pos;

    public InfuserMessage(boolean buttonPressed){
        this.isButtonPressed = buttonPressed;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isButtonPressed = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isButtonPressed);
    }

    @Override
    public void handleClientSide(InfuserMessage message, EntityPlayer player) {
    }

    @Override
    public void handleServerSide(InfuserMessage message, EntityPlayerMP player) {
        isButtonPressed = message.isButtonPressed;

        if(isButtonPressed){
            Container c = player.openContainer;
            if(c instanceof ContainerBlockInfuser){
                ((ContainerBlockInfuser) c).setButtonPressed(message.isButtonPressed);
            }
        }

    }

}
