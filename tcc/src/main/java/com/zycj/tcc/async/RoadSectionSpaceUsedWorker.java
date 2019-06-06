package com.zycj.tcc.async;

import org.apache.log4j.Logger;

import com.zycj.tcc.dao.RoadSectionDao;

public class RoadSectionSpaceUsedWorker implements Runnable {
	private final static Logger log = Logger.getLogger(RoadSectionSpaceUsedWorker.class);

	public RoadSectionSpaceUsedWorker(int type,RoadSectionDao roadSectionDao,String spaceNo){
		this.roadSectionDao=roadSectionDao;
		this.type=type;
		this.spaceNo=spaceNo;
	}
	private RoadSectionDao roadSectionDao;
	private int type;
	private String spaceNo;
	
	@Override
	public void run() {
		try {
			switch (type) {
			case 1://驶入
				roadSectionDao.addSpace(spaceNo);
				break;
			case 0://驶出
				roadSectionDao.subtractSpace(spaceNo);
				break;
			}
		} catch (Exception e) {
			log.warn("更新("+spaceNo+")车位对应的路段占用车位数 异常",e);
		}
	}

}
