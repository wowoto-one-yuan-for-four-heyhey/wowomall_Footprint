package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: Tens
 * @Description:
 * @Date: 2019/12/16 22:31
 */
@Service
public interface LogService {
    Log addLog(@RequestBody Log log);
}
