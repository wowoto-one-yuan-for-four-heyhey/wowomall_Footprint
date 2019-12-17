package com.xmu.wowomall.footprint.service.impl;

import com.xmu.wowomall.footprint.domain.Log;
import com.xmu.wowomall.footprint.service.LogService;
import com.xmu.wowomall.footprint.service.RemoteLogService;
import com.xmu.wowomall.footprint.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Tens
 * @Description:
 * @Date: 2019/12/16 22:31
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    RemoteLogService remoteLogService;

    @Override
    public Log addLog(Log log) {
        String json = remoteLogService.addLog(log);
        return JacksonUtil.parseObject(json, "data", Log.class);
    }
}
