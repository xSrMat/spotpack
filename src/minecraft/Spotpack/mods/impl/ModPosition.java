package Spotpack.mods.impl;

import Spotpack.gui.hud.ScreenPosition;
import Spotpack.mods.ModDraggable;
import net.minecraft.client.Minecraft;

public class ModPosition extends ModDraggable {
   public int getWidth() {
      return 100;
   }

   public int getHeight() {
      return 30;
   }

   public void render(ScreenPosition pos) {
      this.font.drawStringWithShadow("" + this.mc.thePlayer.getHorizontalFacing(), (float)(pos.getAbsoluteX() + 50), (float)(pos.getAbsoluteY() + 12), -1);
      this.font.drawStringWithShadow("X: " + Math.round(Minecraft.getMinecraft().thePlayer.posX * 1000.0D) / 1000L, (float)(pos.getAbsoluteX() + 2), (float)(pos.getAbsoluteY() + 2), -1);
      this.font.drawStringWithShadow("Y: " + Math.round(Minecraft.getMinecraft().thePlayer.posY * 1000.0D) / 1000L, (float)(pos.getAbsoluteX() + 2), (float)(pos.getAbsoluteY() + 12), -1);
      this.font.drawStringWithShadow("Z: " + Math.round(Minecraft.getMinecraft().thePlayer.posZ * 1000.0D) / 1000L, (float)(pos.getAbsoluteX() + 2), (float)(pos.getAbsoluteY() + 22), -1);
   }

   public void renderDummy(ScreenPosition pos) {
      this.font.drawStringWithShadow("" + this.mc.thePlayer.getHorizontalFacing(), (float)(pos.getAbsoluteX() + 50), (float)(pos.getAbsoluteY() + 12), -1);
      this.font.drawStringWithShadow("X: X", (float)(pos.getAbsoluteX() + 2), (float)(pos.getAbsoluteY() + 2), -1);
      this.font.drawStringWithShadow("Y: Y", (float)(pos.getAbsoluteX() + 2), (float)(pos.getAbsoluteY() + 12), -1);
      this.font.drawStringWithShadow("Z: Z", (float)(pos.getAbsoluteX() + 2), (float)(pos.getAbsoluteY() + 22), -1);
   }
}
