package health_interface;

import health_pojo.pojo.OrderSetting;

import java.util.Date;
import java.util.List;

public interface OrderSettingService {
    void importOrderSettings(List<OrderSetting> orderSettings);

    List<OrderSetting> findOrderSettingByDate(Date date);

    void handleOrderSet(Date day, int count);
}
