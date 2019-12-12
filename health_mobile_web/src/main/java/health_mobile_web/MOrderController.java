package health_mobile_web;

import com.alibaba.dubbo.config.annotation.Reference;
import health_interface.MOrderService;
import health_pojo.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class MOrderController {

    @Reference
    private MOrderService mOrderService;

    @RequestMapping("/addOrderInfo")
    public Result addOrderInfo( ) {

        return new Result(true , "预约成功！");
    }
}
