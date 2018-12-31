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
    public int fluidAmount;
    private BlockPos pos;

    public InfuserMessage(boolean buttonPressed){
        this.isButtonPressed = buttonPressed;
    }

    public InfuserMessage(int fluidAmount){
        this.fluidAmount = fluidAmount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isButtonPressed = buf.readBoolean();
        fluidAmount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isButtonPressed);
        buf.writeInt(fluidAmount);
    }

    @Override
    public void handleClientSide(InfuserMessage message, EntityPlayer player) {
        Container c = player.openContainer;
        if(c instanceof ContainerBlockInfuser){
            ((ContainerBlockInfuser) c).setFluidAmount(fluidAmount);
        }


    }

    @Override
    public void handleServerSide(InfuserMessage message, EntityPlayerMP player) {
        isButtonPressed = message.isButtonPressed;

        Container c = player.openContainer;
        if(isButtonPressed){
            if(c instanceof ContainerBlockInfuser){
                ((ContainerBlockInfuser) c).setButtonPressed(message.isButtonPressed);
            }
        }
        if(c instanceof ContainerBlockInfuser){
            this.fluidAmount = ((ContainerBlockInfuser) c).getFluidAmount();
            ((ContainerBlockInfuser) c).setFluidAmount(fluidAmount);

        }

    }

}
