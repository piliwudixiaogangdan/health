package health_mobile_web;

import com.aliyuncs.exceptions.ClientException;
import health_common.SMSUtils;
import health_common.ValidateCodeUtils;
import health_pojo.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/code")
public class ValidateCodeController {


    @RequestMapping("/sendValidateCode")
    //发送验证码
    public Result sendValidateCode(@RequestParam("telephone") String telephone ) {

        //验证码
        String param = ValidateCodeUtils.generateValidateCode(4).toString();

        //向用户发送验证码
        try {
            SMSUtils.sendShortMessage("SMS_180058908" , telephone ,param);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        //向redis中保存验证码
        Jedis resource = new JedisPool().getResource();
        resource.sadd(telephone + "001", param);
        resource.expire(telephone+"001" , 300);
        return new Result(true, "验证码发送成功！");
    }
}
