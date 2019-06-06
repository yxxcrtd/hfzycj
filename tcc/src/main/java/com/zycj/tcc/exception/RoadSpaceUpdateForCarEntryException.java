package com.zycj.tcc.exception;

/**
 * 更新停车位异常 当车辆驶入驶出时 日终清算
 * @author Administrator
 *
 */
public class RoadSpaceUpdateForCarEntryException extends Exception {
	public RoadSpaceUpdateForCarEntryException(Throwable cause) {
		super("更新停车位停车信息 异常",cause);
	}
}
