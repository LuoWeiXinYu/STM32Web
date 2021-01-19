package net.mynoise.noiseshow.entity;

/*** 
 @author: 余新伟
 @学号:201841882320
 */
public class AllSensor {
    /**数据编号*/
    private int id;
    private String temp;
    private String hum;
    private String dist;
    private String people;

    /**ip地址*/
    private String ip;
    /**采集时间*/
    private String time;
    /*是否有效*/
    private char isused;

    @Override
    public String toString() {
        return "AllSensor{" +
                "id=" + id +
                ", temp='" + temp + '\'' +
                ", hum='" + hum + '\'' +
                ", dist='" + dist + '\'' +
                ", people='" + people + '\'' +
                ", ip='" + ip + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTemp() { return temp; }

    public void setTemp(String temp) { this.temp = temp; }

    public String getHum() { return hum; }

    public void setHum(String hum) { this.hum = hum; }

    public String getIp() { return ip; }

    public void setIp(String ip) { this.ip = ip; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getDist() { return dist; }

    public void setDist(String dist) { this.dist = dist; }

    public char getIsused() { return isused; }

    public void setIsused(char isused) { this.isused = isused;}

    public String getPeople() { return people; }

    public void setPeople(String people) { this.people = people; }
}
