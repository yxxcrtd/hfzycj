package com.tcc.park.api.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.service.PrePayCardService;
import com.tcc.park.api.util.DateUtil;
import com.tcc.park.api.vo.ListVo;
import com.tcc.park.api.vo.PrePayCardFeeVo;
import com.tcc.park.api.vo.PrePayCardVo;

import net.sf.json.JSONObject;

@Controller
public class PrePayCardController {
	@Autowired
	private PrePayCardService prePayCardService;

	@RequestMapping("/api/prepaycard/detail/{urlParams}")
	@ResponseBody
	public Object detail(@PathVariable String urlParams) {
		ListVo result = new ListVo();
		result.setResultCode(ResultCode.APP_SUCCESS);
		 List<PrePayCardFeeVo> fees = prePayCardService.getCardFeesByNo(urlParams);
		result.setList(fees);
		String string = JSONObject.fromObject(result).toString();
		return string;
	}

	@RequestMapping("/api/prepaycard/list/{urlParams}")
	@ResponseBody
	public Object list(@PathVariable String urlParams) throws UnsupportedEncodingException {
		List<PrePayCardVo> cards = prePayCardService.getCardListByPhone(urlParams);
		ListVo result = new ListVo();
		result.setResultCode(ResultCode.APP_SUCCESS);
		result.setList(cards);
		return JSONObject.fromObject(result).toString();
	}

	public Date getDate(String dateStr) {
		return DateUtil.getDateByFormate(dateStr, "yyyy-MM-dd");
	}

	public PrePayCardService getPrePayCardService() {
		return prePayCardService;
	}

	public void setPrePayCardService(PrePayCardService prePayCardService) {
		this.prePayCardService = prePayCardService;
	}
	
	
}
