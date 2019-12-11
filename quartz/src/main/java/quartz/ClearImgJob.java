package quartz;

import health_common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Set;
@Component
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        Set<String> sdiff = jedisPool.getResource().sdiff( "qnyImg","sjkImg");

        Set<String> sjkImg = jedisPool.getResource().smembers("sjkImg");
        for (String s : sjkImg) {
            System.out.println(s);
        }

        System.out.println("------------------------");

        Set<String> qnyImg = jedisPool.getResource().smembers("qnyImg");
        for (String s : qnyImg) {
            System.out.println(s);
        }

        System.out.println(sdiff.size());
        //遍历差值，获取其中的图片名称
        for (String imageName : sdiff) {
            ImageUtil.deleteFile(imageName);
            System.out.println(imageName);
            jedisPool.getResource().srem("qnyImg",imageName);
        }
    }
}
