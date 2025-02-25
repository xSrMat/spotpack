package Spotpack;

public class AnimationEngine {
   private long prevTime;
   private float animationValue;
   private float startValue;
   private float endValue;
   private boolean isIncreasing;
   private float changeValuePms;
   private boolean isDrawAnimation = false;
   private boolean resetUsingBackWardsAnimation = false;

   public AnimationEngine(float startValue, float endValue, long time) {
      this.prevTime = System.currentTimeMillis();
      this.startValue = startValue;
      this.endValue = startValue == endValue ? endValue + 1.0F : endValue;
      this.animationValue = startValue;
      this.isIncreasing = endValue > startValue;
      float animationDistance = Math.abs(startValue - endValue);
      this.changeValuePms = animationDistance / (float)time;
   }

   public AnimationEngine(float startValue, float endValue, long time, boolean instaIsDrawAnimation) {
      this.prevTime = System.currentTimeMillis();
      this.startValue = startValue;
      this.endValue = startValue == endValue ? endValue + 1.0F : endValue;
      this.animationValue = startValue;
      this.isIncreasing = endValue > startValue;
      float animationDistance = Math.abs(startValue - endValue);
      this.changeValuePms = animationDistance / (float)time;
      this.isDrawAnimation = instaIsDrawAnimation;
   }

   public AnimationEngine() {
   }

   public float getAnimationValue() {
      this.updateAnimationValue();
      return this.animationValue;
   }

   public boolean isAnimationDone() {
      return this.animationValue == this.endValue;
   }

   private void updateAnimationValue() {
      if (this.isDrawAnimation) {
         this.resetUsingBackWardsAnimation = false;
         if (this.animationValue != this.endValue) {
            if (this.isIncreasing) {
               if (this.animationValue >= this.endValue) {
                  this.animationValue = this.endValue;
               } else {
                  this.animationValue += this.changeValuePms * (float)(System.currentTimeMillis() - this.prevTime);
                  if (this.animationValue > this.endValue) {
                     this.animationValue = this.endValue;
                  }

                  this.prevTime = System.currentTimeMillis();
               }
            } else if (this.animationValue <= this.endValue) {
               this.animationValue = this.endValue;
            } else {
               this.animationValue -= this.changeValuePms * (float)(System.currentTimeMillis() - this.prevTime);
               if (this.animationValue < this.endValue) {
                  this.animationValue = this.endValue;
               }

               this.prevTime = System.currentTimeMillis();
            }
         }
      } else if (this.resetUsingBackWardsAnimation) {
         this.setIsDrawAnimation(false);
         if (this.animationValue == this.startValue) {
            this.reset();
            this.resetUsingBackWardsAnimation = false;
         } else if (this.isIncreasing && this.animationValue <= this.startValue) {
            this.reset();
         } else {
            this.animationValue -= this.changeValuePms * (float)(System.currentTimeMillis() - this.prevTime);
            if (this.animationValue < this.startValue) {
               this.reset();
            }

            this.prevTime = System.currentTimeMillis();
         }
      }
   }

   public void reset() {
      this.animationValue = this.startValue;
      this.prevTime = System.currentTimeMillis();
   }

   public void AnimationUpdateValue(float startValue, float endValue, long time) {
      this.reset();
      this.prevTime = System.currentTimeMillis();
      this.startValue = startValue;
      this.endValue = startValue == endValue ? endValue + 1.0F : endValue;
      this.animationValue = startValue;
      this.isIncreasing = endValue > startValue;
      float animationDistance = Math.abs(startValue - endValue);
      this.changeValuePms = animationDistance / (float)time;
   }

   public void AnimationUpdateValue(float startValue, float endValue, long time, boolean instaDrawAnimation) {
      this.prevTime = System.currentTimeMillis();
      this.startValue = startValue;
      this.endValue = startValue == endValue ? endValue + 1.0F : endValue;
      this.animationValue = startValue;
      this.isIncreasing = endValue > startValue;
      float animationDistance = Math.abs(startValue - endValue);
      this.changeValuePms = animationDistance / (float)time;
      this.isDrawAnimation = instaDrawAnimation;
   }

   public void setIsDrawAnimation(boolean drawAnimation) {
      this.isDrawAnimation = drawAnimation;
   }

   public boolean getIsDrawAnimation() {
      return this.isDrawAnimation;
   }

   public void resetUsingBackWardsAnimation() {
      this.prevTime = System.currentTimeMillis();
      this.setIsDrawAnimation(false);
      this.resetUsingBackWardsAnimation = true;
   }
}
