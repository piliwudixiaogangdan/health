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
        Set<String> sdiff = jedisPool.getResource().sdiff("sjkImg", "qnyImg");
        //遍历差值，获取其中的图片名称
        for (String imageName : sdiff) {
            ImageUtil.deleteFile(imageName);
            jedisPool.getResource().srem("sjkImg",imageName);
        }

        System.out.println("执行完毕！！！");
    }
}
