package health_interface;

import health_pojo.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    void importOrderSettings(List<OrderSetting> orderSettings);
}
