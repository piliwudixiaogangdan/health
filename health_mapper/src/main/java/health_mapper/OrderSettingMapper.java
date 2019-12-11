package health_mapper;

import health_pojo.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {
    void importOrderSettings(@Param("orderSettings") List<OrderSetting> orderSettings);

    /**
     * 通过日期查找预约信息
     * @param orderDate
     * @return
     */
    OrderSetting findOrderSetting(@Param("orderDate") Date orderDate);

    void update(OrderSetting orderSetting);

    void addOrderSetting(OrderSetting orderSetting);

    List<OrderSetting> findOrderSettingsByDate(@Param("frontDate") String frontDate ,@Param("afterDate") String afterDate);

    void handleOrderSet(@Param("day") String day,@Param("count") int count);
}
