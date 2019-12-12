package health_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import health_interface.OrderSettingService;
import health_mapper.OrderSettingMapper;
import health_pojo.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void importOrderSettings(List<OrderSetting> orderSettings) {
        //判断该日期是否被设置过，存在判断是否有预约，没有时覆盖，
        for (OrderSetting orderSetting : orderSettings) {
          OrderSetting orderSetting1=  orderSettingMapper.findOrderSetting(orderSetting.getOrderDate());
            if (orderSetting != null) {
                orderSettingMapper.update(orderSetting);
            } else {
                orderSettingMapper.addOrderSetting(orderSetting);
            }
        }
        orderSettingMapper.importOrderSettings(orderSettings);
    }

    /**
     * 查找一个月内所有的预约信息
     * @param date
     * @return
     */
    @Override
    public List<OrderSetting> findOrderSettingByDate(Date date) {
        int year = date.getYear()+1900;
        int month = date.getMonth()+1;
        String frontDate = year+"-"+month+"-"+"1";
        String afterDate = year+"-"+month+"-"+"31";
        List<OrderSetting> orderSettings =  orderSettingMapper.findOrderSettingsByDate(frontDate ,afterDate );
        return orderSettings;
    }

    @Override
    public void handleOrderSet(Date day, int count) {
        String sday = ""+(day.getYear()+1900)+"-"+(day.getMonth()+1)+"-"+day.getDate();
        OrderSetting orderSetting = orderSettingMapper.findOrderSetting(day);
        if (orderSetting == null) {
            OrderSetting orderSetting1 = new OrderSetting();
            orderSetting1.setNumber(count);
            orderSetting1.setOrderDate(day);
            orderSettingMapper.addOrderSetting(orderSetting1);
        } else {
            orderSettingMapper.handleOrderSet(sday , count);
        }

    }
}
