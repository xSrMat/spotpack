package net.minecraft.client.gui;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import Spotpack.utils.fontRenderer.GlyphPageFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;

public class GuiButton extends Gui {
   private static GlyphPageFontRenderer ufr;
   protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
   protected int width;
   private ResourceLocation texture;
   private boolean temp;
   protected int height;
   public int xPosition;
   public int yPosition;
   public String displayString;
   public int id;
   public boolean enabled;
   public boolean visible;
   protected boolean hovered;

   public GuiButton(int buttonId, int x, int y, String buttonText) {
      this(buttonId, x, y, 200, 20, buttonText);
   }

   public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
      this.texture = null;
      this.temp = true;
      this.width = 200;
      this.height = 20;
      this.enabled = true;
      this.visible = true;
      this.id = buttonId;
      this.xPosition = x;
      this.yPosition = y;
      this.width = widthIn;
      this.height = heightIn;
      this.displayString = buttonText;
   }

   protected int getHoverState(boolean mouseOver) {
      int i = 1;
      if (!this.enabled) {
         i = 0;
      } else if (mouseOver) {
         i = 2;
      }

      return i;
   }

   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
      if (this.visible) {
         if (ufr == null) {
            ufr = GlyphPageFontRenderer.create("Spotpack/arial.ttf", 20, true, true, true);
         }

         mc.getTextureManager().bindTexture(buttonTextures);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         drawRect((float)this.xPosition, (float)this.yPosition, (float)(this.xPosition + this.width), (float)(this.yPosition + this.height), 1140850688);
         int outlineColour = 1146443093;
         this.drawHorizontalLine(this.xPosition, this.xPosition + this.width, this.yPosition, outlineColour);
         this.drawHorizontalLine(this.xPosition, this.xPosition + this.width, this.yPosition + this.height, outlineColour);
         this.drawVerticalLine(this.xPosition, this.yPosition + this.height, this.yPosition, outlineColour);
         this.drawVerticalLine(this.xPosition + this.width, this.yPosition + this.height, this.yPosition, outlineColour);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
         int i = this.getHoverState(this.hovered);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         this.mouseDragged(mc, mouseX, mouseY);
         int j = 14737632;
         if (!this.enabled) {
            j = 10526880;
         } else if (this.hovered) {
            j = 16777120;
         }

         ufr.drawCenteredString(this.displayString, (float)(this.xPosition + this.width / 2), (float)(this.yPosition + (this.height - 14) / 2), j);
      }

      if (this.id == 2626) {
         if (this.texture == null && this.temp) {
            Minecraft.getMinecraft().getSkinManager().loadProfileTextures(Minecraft.getMinecraft().getSession().getProfile(), new SkinManager.SkinAvailableCallback() {
               public void skinAvailable(Type p_180521_1_, ResourceLocation location, MinecraftProfileTexture profileTexture) {
                  if (p_180521_1_.equals(Type.SKIN)) {
                     GuiButton.this.texture = location;
                  }

               }
            }, true);
            this.temp = false;
         }

         if (this.texture != null) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
            drawModalRectWithCustomSizedTexture((float)(this.xPosition + 52), (float)(this.yPosition + 3), 14.0F, 14.0F, 14.0F, 14.0F, 112.0F, 112.0F);
         } else {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/entity/steve.png"));
            drawModalRectWithCustomSizedTexture((float)(this.xPosition + 52), (float)(this.yPosition + 3), 14.0F, 14.0F, 14.0F, 14.0F, 112.0F, 112.0F);
         }
      }

   }

   protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
   }

   public void mouseReleased(int mouseX, int mouseY) {
   }

   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
      return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
   }

   public boolean isMouseOver() {
      return this.hovered;
   }

   public void drawButtonForegroundLayer(int mouseX, int mouseY) {
   }

   public void playPressSound(SoundHandler soundHandlerIn) {
      soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
   }

   public int getButtonWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }
}