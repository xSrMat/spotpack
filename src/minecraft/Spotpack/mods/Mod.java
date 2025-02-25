package Spotpack.mods;

import Spotpack.Client;
import Spotpack.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {
   public boolean isEnabled = false;
   protected final Minecraft mc;
   protected final FontRenderer font;
   protected final Client client;
   private Category category;

   public Mod() {
      this.isEnabled = true;
      this.mc = Minecraft.getMinecraft();
      this.font = this.mc.fontRendererObj;
      this.client = Client.getInstance();
      this.setEnabled(this.isEnabled);
   }

   public Category getCategory() {
      return this.category;
   }

   public void setEnabled(boolean isEnabled) {
      this.isEnabled = isEnabled;
      if (isEnabled) {
         EventManager.register(this);
      } else {
         EventManager.unregister(this);
      }

   }

   public boolean isEnabled() {
      return this.isEnabled;
   }
}
