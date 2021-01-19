package net.mynoise.noiseshow.mapper;
import net.mynoise.noiseshow.entity.AllSensor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
/*** 
 @author: 余新伟
 @学号:201841882320
 */
@Repository
public interface AllSensorMapper {
    @Select("SELECT * FROM rs485_data")
    public List<AllSensor> querrySensor();

    @Insert({ "insert into " +
            "rs485_data(id,temp,hum,dist,people,ip,time) " +
            "values(#{id},#{temp},#{hum},#{dist},#{people},#{ip},#{time})" })
    public int insertData(AllSensor allSensor);

    @Delete({"delete from rs485_data where id=#{id}"})
    int deleteData(long id);

    @Update({"UPDATE rs485_data set temp=#{temp},hum=#{hum},dist=#{dist},people=#{people},ip=#{ip},time=NOW() where id=#{id}\n"})
    int updataData(long id,String temp,String hum,String dist,String people,String ip);
}
