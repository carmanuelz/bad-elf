package com.me.mygdxgame;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class BodyAccessor implements TweenAccessor<Body> {
	public static final int POS_XY = 1;
	public static final int ROTATE = 2;

	@Override
	public int getValues(Body target, int tweenType, float[] returnValues) {
		switch (tweenType) {
			case POS_XY:
				returnValues[0] = target.getPosition().x;
				returnValues[1] = target.getPosition().y;
				return 2;
				
			case ROTATE:
				returnValues[0] = target.getAngle();
				return 1;
	
			default: assert false; return -1;
		}
	}

	@Override
	public void setValues(Body target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POS_XY: target.setTransform(newValues[0], newValues[1],target.getAngle()); break;
		
		case ROTATE:
			target.setTransform(target.getPosition(), newValues[0]);
			break;
			
		default: assert false;
	}
		
	}
}
