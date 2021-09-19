package aa.my.javaio.rpc.serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * DATE	        java.sql.Date
 * YEAR	        java.sql.Date
 * TIME	        java.sql.TIME
 * DATETIME	    java.sql.Timestamp
 * TIMESTAMP	java.sql.Timestamp

 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    // 版本号，序列化后防止被篡改
    private static final long serialVersionUID = 2411194145042828448L;

    // transient表示该属性不参与序列化
    private transient String username;
    @JSONField(name = "date", format = "yyyy-MM-dd")
    private Date create_date;
    @JSONField(name = "time", format = "HH:mm:ss")
    private Time create_time;
    @JSONField(name = "stamp", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp create_stamp;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}


class DemoMain{
    public static void main(String[] args) {
        User ming = new User("ming", new Date(System.currentTimeMillis()),
                new Time(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        // toString
        System.out.println(ming.toString());
        String jsonString = JSONObject.toJSONString(ming);
        System.out.println(jsonString);
        System.out.println(Arrays.toString(jsonString.getBytes(StandardCharsets.UTF_8)));
    }
}













