package health_mobile_web;

import com.alibaba.dubbo.config.annotation.Reference;
import health_interface.MOrderService;
import health_pojo.entity.Result;
import health_pojo.pojo.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class MOrderController {

    @Reference
    private MOrderService mOrderService;

    @RequestMapping("/addOrderInfo")
    public Result addOrderInfo(@RequestBody Map map) {
//        //判断验证码
//        String validateCode = (String) map.get("validateCode");
//        String storeValidateCode = new JedisPool().getResource().get((String) map.get("telephone")+"001");
//        if (validateCode == null || storeValidateCode == null || !storeValidateCode.equals(validateCode)) {
//            return new Result(false, "验证码有误，请重新输入！");
//        }


        Result result = mOrderService.addOrderInfo(map);
        return result;
    }

    @RequestMapping("/findOrderDetail")
    public Map findOrderDetail(@RequestParam("id")int mumberId) {
         //返回值类型map
       return mOrderService.findOrderDetail(mumberId);
    }
}
