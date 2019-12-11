package health_service;

import com.alibaba.dubbo.config.annotation.Service;
import health_interface.OrderSettingService;
import health_mapper.OrderSettingMapper;
import health_pojo.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void importOrderSettings(List<OrderSetting> orderSettings) {

        orderSettingMapper.importOrderSettings(orderSettings);
    }
}
