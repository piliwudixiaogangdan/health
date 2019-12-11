package health_mapper;

import health_pojo.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSettingMapper {
    void importOrderSettings(@Param("orderSettings") List<OrderSetting> orderSettings);
}
