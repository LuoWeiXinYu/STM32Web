package net.mynoise.noiseshow.service;

import net.mynoise.noiseshow.entity.AllSensor;

import java.util.List;

/***
 @author: 余新伟
 @学号:201841882320
 */
public interface IAllSensorService {
    public List<AllSensor> querrySensor();

    public int insertData(AllSensor allSensor);


}
