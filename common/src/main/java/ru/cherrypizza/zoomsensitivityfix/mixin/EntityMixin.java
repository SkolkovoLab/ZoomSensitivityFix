package ru.cherrypizza.zoomsensitivityfix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public class EntityMixin {
    @Unique
    private final Minecraft zoomsensitivityfix$mc = Minecraft.getInstance();

    @ModifyVariable(method = "turn(DD)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private double injectX(double d) {
        return zoomsensitivityfix$multipleValue(d);
    }

    @ModifyVariable(method = "turn(DD)V", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private double injectY(double d) {
        return zoomsensitivityfix$multipleValue(d);
    }

    @Unique
    private double zoomsensitivityfix$multipleValue(double value) {
        Entity thisEntity = ((Entity) (Object) this);
        if (!thisEntity.equals(zoomsensitivityfix$mc.player)) return value;
        // In 26.1 the FOV modifier field moved from GameRenderer to Camera;
        // in 26.2 the GameRenderer camera getter was renamed getMainCamera() -> mainCamera().
        var camera = zoomsensitivityfix$mc.gameRenderer.mainCamera();
        var mul = Math.min(((CameraAccessor) camera).getFovModifier(), 1.0);
        return value * mul;
    }
}
