package com.me.mygdxgame;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BitmapAccessor implements TweenAccessor<BitmapFont>{
	
	public static final int OPACITY = 1;

	@Override
	public int getValues(BitmapFont target, int tweenType, float[] returnValues) {
		switch (tweenType){
			case OPACITY:
				returnValues[0] = target.getColor().a; return 1;
			default: assert false; return -1;
		}
	}

	@Override
	public void setValues(BitmapFont target, int tweenType, float[] newValues) {
		switch (tweenType){
		case OPACITY:
			target.setColor(1,1,1,newValues[0]); break;
		default: assert false;
	   }
	}

}
