package com.zycj.tcc.server.dispatcher;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.server.domain.ServiceInfo;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.util.ServiceDispatcherUtil;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.Response;

/**
 * 默认的消息分发器
 * @author 洪捃能
 *
 */
@Component
public class DefaultDispatcher implements Dispatcher {
	private final static Logger log = Logger.getLogger(DefaultDispatcher.class); 
	
	@Override
	public Response doWork(Request req) throws Exception {
		try {
			if(StringUtils.isBlank(req.getT_code())){
				return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
			}
			ServiceInfo si=ServiceDispatcherUtil.getServiceInfoByType(req.getT_code());
			if(si==null){
				return ResponseUtil.createResponse(ResultCode.INTERFACE_NO_FOUND_ERROR);
			}
			si.getMethod().setAccessible(true);
			Response res=(Response) si.getMethod().invoke(si.getObj(),req.getParameter());
			return res;
//			TcodeResponse result=new TcodeResponse();
//			result.setTcode(req.getT_code());
//			result.setResponse(res);
//			return (Response)result;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			throw e;
		}
	}
}
