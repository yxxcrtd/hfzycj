package com.hfzycj.service.impl;

import com.alibaba.fastjson.JSON;
import com.hfzycj.dao.ParkCommunalDao;
import com.hfzycj.dao.ParkDao;
import com.hfzycj.domain.Park;
import com.hfzycj.domain.ParkCache;
import com.hfzycj.domain.ParkCommunal;
import com.hfzycj.service.ParkService;
import com.hfzycj.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Park Service Implementation
 */
@Service
public class ParkServiceImpl extends BaseServiceImpl<Park, Integer> implements ParkService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ParkServiceImpl.class);

    @Autowired
    protected ParkDao parkDao;
    @Autowired
    protected RedisUtil redisUtil;
    @Autowired
    ParkCommunalDao parkCommunalDao;

    public Long saveReturnId(Park park) throws Exception {
        return ((ParkDao) baseDao).saveReturnId(park);
    }

    @Override
    public void saveParkInfo(Park park, List<Integer> communal_id) throws Exception {
        if (0 == park.getPark_id()) {
            park.setPark_create_time(new Date());
            park.setPark_update_time(new Date());
            park.setPark_surplus(0);
            long newestId = saveReturnId(park);
            park.setPark_id(new Long(newestId).intValue());
        } else {
            park.setPark_update_time(new Date());
            park.setPark_surplus(0);
            park.setPark_update_time(new Date());
            parkDao.update(park);
        }
        // 开始保存标签对应关系
        parkCommunalDao.deleteByParkId(park.getPark_id());
        for (int id : communal_id) {
            ParkCommunal parkCommunal = new ParkCommunal();
            parkCommunal.setParkcommunal_communalid(id);
            parkCommunal.setParkcommunal_parkid(park.getPark_id());
            parkCommunalDao.save(parkCommunal);
        }
    }

    @Override
    public void updateStatus(String[] ids, int status) {
        parkDao.updateStatus(ids, status);
        parkInit();
    }

    @Override
    public void updateSurplus(String code, int surplus, int total) {
        parkInit();
        Object values = redisUtil.hGet("parkInfoList", "park_" + code);
        System.out.println(values);
        if (null != values) {
            ParkCache parkCache = JSON.parseObject(values.toString(), ParkCache.class);
            parkCache.setSurplus(surplus);
            if (total > 0 && total > surplus) {
                parkCache.setTotal(total);
            }
            parkCache.setCountStatus(1);// 0:初始数据（离线状态），1：已经上传过车位数
            parkCache.setStatus(3);// 非离线
            redisUtil.hSet("parkInfoList", "park_" + parkCache.getParkCode(), JSON.toJSONString(parkCache));
            System.out.println("更新缓存成功parkInfoList");
            // 更新数据库
            parkDao.updateSurplus(code, surplus, total);
            System.out.println("更新数据库成功updateSurplus");
        }
    }

    /**
     * 获取redis里停车场信息
     *
     * @return
     */
    @Override
    public List<ParkCache> parkCacheList(int PARK_CACHE_PERCERT, int parkShort, int parkOffline) {
//        parkInit();
        List<ParkCache> parkCacheList = new LinkedList<>();
        Map<String, Object> parkCacheMap = redisUtil.hGetAll("parkInfoList");
        for (Map.Entry<String, Object> entry : parkCacheMap.entrySet()) {
            try {
                ParkCache parkCache = JSON.parseObject(String.valueOf(entry.getValue()), ParkCache.class);
                int total = parkCache.getTotal();
                int surplus = parkCache.getSurplus();
                if (surplus > 0) {
                    parkCache.setCountStatus(1);
                }
                NumberFormat numberFormat = NumberFormat.getInstance();
                float bl = ((float) surplus / (float) total) * 100;
                float fPARK_CACHE_PERCERT = (float) PARK_CACHE_PERCERT;
                if (parkCache.getStatus() != 3) {
                    // 离线
                    parkCache.setCountStatus(0);
                } else {
                    if (bl >= fPARK_CACHE_PERCERT) {
                        // 空闲
                        parkCache.setCountStatus(1);
                    }
                    if (bl < fPARK_CACHE_PERCERT && bl > 0) {
                        // 紧张
                        parkCache.setCountStatus(2);
                    }
                }
                // 0离线，1空闲，2紧张
                // System.out.println(bl);
                String result = numberFormat.format(bl);
                // System.out.println("surplus和total的百分比为:" + result + "%");
                parkCacheList.add(parkCache);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Collections.sort(parkCacheList, new ParkCacheComparator()); // 排序
        return parkCacheList;
    }

    /**
     * 停车场数据缓存初始化
     */
    public List<Park> parkInit() {
        List<Park> parkList = parkDao.findAllList("park_status = 0 AND park_source = 2", "park_update_time DESC", null);
        if (null != parkList) {
            for (Park park : parkList) {
                try {
                    Object values = redisUtil.hGet("parkInfoList", "park_" + park.getPark_code());
                    if (null == values) {
                        ParkCache parkCache = new ParkCache();
                        parkCache.setParkId(park.getPark_id());
                        parkCache.setName(park.getPark_name());
                        parkCache.setAddress(park.getPark_address());
                        parkCache.setParkCode(park.getPark_code());
                        parkCache.setStatus(park.getPark_status());
                        parkCache.setTotal(park.getPark_total());
                        parkCache.setSurplus(park.getPark_surplus());
                        parkCache.setCountStatus(0);
                        String p = JSON.toJSONString(parkCache);
                        redisUtil.hSet("parkInfoList", "park_" + parkCache.getParkCode(), p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info("停车场数量：" + parkList.size());
        return parkList;
    }

}

class ParkCacheComparator implements Comparator<ParkCache> {

    @Override
    public int compare(ParkCache o1, ParkCache o2) {
        return (o2.getTotal() - o1.getTotal());
    }

}
