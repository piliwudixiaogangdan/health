package health_interface;

import health_pojo.entity.Result;
import health_pojo.pojo.Order;

import java.util.Map;

public interface MOrderService {

    Result addOrderInfo(Map map);

    Map findOrderDetail(Integer mumberId);
}
