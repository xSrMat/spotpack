package Spotpack.gui.hud;

public interface IRenderer extends IRenderConfig {
   int getWidth();

   int getHeight();

   void render(ScreenPosition var1);

   default void renderDummy(ScreenPosition pos) {
      this.render(pos);
   }

   default boolean isEnabled() {
      return true;
   }
}
