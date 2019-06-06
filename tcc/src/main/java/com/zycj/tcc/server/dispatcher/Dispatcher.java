package com.zycj.tcc.server.dispatcher;

import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.Response;

/**
 * 消息分发接口
 * @author 洪捃能
 *
 */
public interface Dispatcher {

	public Response doWork(Request req) throws Exception;
}
