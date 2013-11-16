package com.me.mygdxgame;

import aurelienribon.tweenengine.TweenAccessor;

import com.brashmonkey.spriter.player.SpriterPlayer;

public class SpriterAccessor implements TweenAccessor<SpriterPlayer> {
	public static final int POS_XY = 1;
	public static final int ROTATION = 4;

	@Override
	public int getValues(SpriterPlayer target, int tweenType, float[] returnValues) {
		switch (tweenType) {
			case POS_XY:
				returnValues[0] = target.getPivotX();
				returnValues[1] = target.getPivotY();
				return 2;

			case ROTATION: returnValues[0] = target.getAngle(); return 1;

			default: assert false; return -1;
		}
	}

	@Override
	public void setValues(SpriterPlayer target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case POS_XY: target.update(newValues[0], newValues[1]); break;
			case ROTATION: target.setAngle(newValues[0]); break;
			default: assert false;
		}
	}
}
