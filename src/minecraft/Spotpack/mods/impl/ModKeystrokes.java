package Spotpack.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Spotpack.gui.hud.ScreenPosition;
import Spotpack.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;

public class ModKeystrokes extends ModDraggable {
   int offset = 13;
   private ModKeystrokes.KeystrokesMode mode;
   private List<Long> clicks;
   private boolean wasPressed;
   private long lastPressed;
   private List<Long> clicks2;
   private boolean wasPressed2;
   private long lastPressed2;

   public ModKeystrokes() {
      this.mode = ModKeystrokes.KeystrokesMode.WASD_JUMP_MOUSE;
      this.clicks = new ArrayList();
      this.clicks2 = new ArrayList();
   }

   public void setMode(ModKeystrokes.KeystrokesMode mode) {
      this.mode = mode;
   }

   public int getWidth() {
      return this.mode.getWidth();
   }

   public int getHeight() {
      return this.mode.getHeight();
   }

   public void render(ScreenPosition pos) {
      boolean lpressed = Mouse.isButtonDown(0);
      boolean rpressed = Mouse.isButtonDown(1);
      if (lpressed != this.wasPressed) {
         this.lastPressed = System.currentTimeMillis() + 10L;
         this.wasPressed = lpressed;
         if (lpressed) {
            this.clicks.add(this.lastPressed);
         }
      }

      if (rpressed != this.wasPressed2) {
         this.lastPressed2 = System.currentTimeMillis() + 10L;
         this.wasPressed2 = rpressed;
         if (rpressed) {
            this.clicks2.add(this.lastPressed2);
         }
      }

      GL11.glPushMatrix();
      ModKeystrokes.Key[] var7;
      int var6 = (var7 = this.mode.getKeys()).length;

      for(int var5 = 0; var5 < var6; ++var5) {
         ModKeystrokes.Key key = var7[var5];
         int textWidth = this.font.getStringWidth(key.getName());
         Gui.drawRect((float)(pos.getAbsoluteX() + key.getX()), (float)(pos.getAbsoluteY() + key.getY()), (float)(pos.getAbsoluteX() + key.getX() + key.getWidth()), (float)(pos.getAbsoluteY() + key.getY() + key.getHeight()), key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 150)).getRGB());
         this.font.drawString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
         if (key.cps) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate((float)(pos.getAbsoluteX() + key.getX() + key.getWidth() / 2) - (float)textWidth / 2.0F, (float)(pos.getAbsoluteY() + key.getY() + key.getHeight() / 2) + 4.0F, 1.0F);
            key.getName().matches(ModKeystrokes.Key.LMB.getName());
            key.getName().matches(ModKeystrokes.Key.RMB.getName());
            GlStateManager.popMatrix();
         }
      }

      GL11.glPopMatrix();
   }

   public void renderDummy(ScreenPosition pos) {
      String s = "Keystokes";
      this.font.drawStringWithShadow(s, (float)(this.getWidth() / 2 + 1 - this.font.getStringWidth(s) / 2 + pos.getAbsoluteX()), (float)(pos.getAbsoluteY() + 71 + this.offset), 16777215);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPushMatrix();
      ModKeystrokes.Key[] var6;
      int var5 = (var6 = this.mode.getKeys()).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         ModKeystrokes.Key key = var6[var4];
         int textWidth = this.font.getStringWidth(key.getName());
         Gui.drawRect((float)(pos.getAbsoluteX() + key.getX()), (float)(pos.getAbsoluteY() + key.getY()), (float)(pos.getAbsoluteX() + key.getX() + key.getWidth()), (float)(pos.getAbsoluteY() + key.getY() + key.getHeight()), key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 150)).getRGB());
         this.font.drawString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
      }

      GL11.glPopMatrix();
   }

   private int getCPS() {
      long time = System.currentTimeMillis();
      this.clicks.removeIf((aLong) -> {
         return aLong + 1000L < time;
      });
      return this.clicks.size();
   }

   private int getCPS2() {
      long time2 = System.currentTimeMillis();
      this.clicks2.removeIf((aLong2) -> {
         return aLong2 + 1000L < time2;
      });
      return this.clicks2.size();
   }

   private static class Key {
      private static final ModKeystrokes.Key W;
      private static final ModKeystrokes.Key A;
      private static final ModKeystrokes.Key S;
      private static final ModKeystrokes.Key D;
      private static final ModKeystrokes.Key LMB;
      private static final ModKeystrokes.Key RMB;
      private final String name;
      private final KeyBinding keyBind;
      private final int x;
      private final int y;
      private final int width;
      private final int height;
      private final boolean cps;

      static {
         W = new ModKeystrokes.Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18, false);
         A = new ModKeystrokes.Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18, false);
         S = new ModKeystrokes.Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18, false);
         D = new ModKeystrokes.Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18, false);
         LMB = new ModKeystrokes.Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18, true);
         RMB = new ModKeystrokes.Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindPickBlock, 31, 41, 28, 18, true);
      }

      public Key(String name, KeyBinding keyBind, int x, int y, int width, int height, boolean cps) {
         this.name = name;
         this.keyBind = keyBind;
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         this.cps = cps;
      }

      public boolean isDown() {
         return this.keyBind.isKeyDown();
      }

      public int getHeight() {
         return this.height;
      }

      public String getName() {
         return this.name;
      }

      public int getWidth() {
         return this.width;
      }

      public int getX() {
         return this.x;
      }

      public int getY() {
         return this.y;
      }
   }

   public static enum KeystrokesMode {
      WASD(new ModKeystrokes.Key[]{ModKeystrokes.Key.W, ModKeystrokes.Key.A, ModKeystrokes.Key.S, ModKeystrokes.Key.D}),
      WASD_MOUSE(new ModKeystrokes.Key[]{ModKeystrokes.Key.W, ModKeystrokes.Key.A, ModKeystrokes.Key.S, ModKeystrokes.Key.A, ModKeystrokes.Key.LMB, ModKeystrokes.Key.RMB}),
      WASD_JUMP(new ModKeystrokes.Key[]{ModKeystrokes.Key.W, ModKeystrokes.Key.A, ModKeystrokes.Key.S, ModKeystrokes.Key.D, ModKeystrokes.Key.LMB, ModKeystrokes.Key.RMB, new ModKeystrokes.Key("�m-----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 10, false)}),
      WASD_JUMP_MOUSE(new ModKeystrokes.Key[]{ModKeystrokes.Key.W, ModKeystrokes.Key.A, ModKeystrokes.Key.S, ModKeystrokes.Key.LMB, ModKeystrokes.Key.RMB, ModKeystrokes.Key.D, new ModKeystrokes.Key("�m-----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 10, false)});

      private final ModKeystrokes.Key[] keys;
      private int width = 0;
      private int height = 0;

      private KeystrokesMode(ModKeystrokes.Key... keysIn) {
         this.keys = keysIn;
         ModKeystrokes.Key[] var7;
         int var6 = (var7 = this.keys).length;

         for(int var5 = 0; var5 < var6; ++var5) {
            ModKeystrokes.Key key = var7[var5];
            this.width = Math.max(this.width, key.getX() + key.getWidth());
            this.height = Math.max(this.height, key.getY() + key.getHeight());
         }

      }

      public int getHeight() {
         return this.height;
      }

      public int getWidth() {
         return this.width;
      }

      public ModKeystrokes.Key[] getKeys() {
         return this.keys;
      }
   }
}
