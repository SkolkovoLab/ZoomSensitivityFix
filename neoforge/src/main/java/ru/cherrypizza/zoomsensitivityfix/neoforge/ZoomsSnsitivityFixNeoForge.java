package ru.cherrypizza.zoomsensitivityfix.neoforge;

import ru.cherrypizza.zoomsensitivityfix.ZoomSensitivityFix;
import net.neoforged.fml.common.Mod;

@Mod(ZoomSensitivityFix.MOD_ID)
public final class ZoomsSnsitivityFixNeoForge {
    public ZoomsSnsitivityFixNeoForge() {
        // Run our common setup.
        ZoomSensitivityFix.init();
    }
}
