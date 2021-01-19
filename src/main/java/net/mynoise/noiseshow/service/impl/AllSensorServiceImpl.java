package net.mynoise.noiseshow.service.impl;

import net.mynoise.noiseshow.entity.AllSensor;
import net.mynoise.noiseshow.mapper.AllSensorMapper;
import net.mynoise.noiseshow.service.IAllSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 @author: 余新伟
 @学号:201841882320
 */
@Service
public class AllSensorServiceImpl implements IAllSensorService {
    @Autowired
    private AllSensorMapper allSensorMapper;

    /**
     * @description 温湿度采集 查询
     *
     * @param
     * @return 温湿度采集 列表
     */
    @Override
    public List<AllSensor> querrySensor()
    {
        return allSensorMapper.querrySensor();
    }
    /**
     * @description 温湿度采集 存库
     *
     * @param
     * @return 温湿度采集 存库
     */
    @Override
    public int insertData(AllSensor allSensor){
        return allSensorMapper.insertData(allSensor);
    }

}
