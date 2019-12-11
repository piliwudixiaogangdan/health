package health_web;

import com.alibaba.dubbo.config.annotation.Reference;
import health_common.POIUtils;
import health_interface.OrderSettingService;
import health_pojo.entity.Result;
import health_pojo.pojo.OrderSetting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingWeb {

    @Reference
    private OrderSettingService ordersettingService;

    @RequestMapping("/importOrderSettings")
    public Result importOrderSettings(@RequestParam("excelFile")MultipartFile file) throws IOException, ParseException {

        List<String[]> rows = POIUtils.readExcel(file);

        List<OrderSetting> orderSettings = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for (String[] row : rows) {
            OrderSetting orderSetting = new OrderSetting();
            orderSetting.setNumber(Integer.parseInt(row[1]));
            orderSetting.setOrderDate(simpleDateFormat.parse(row[0]));
            orderSettings.add(orderSetting);
        }
        ordersettingService.importOrderSettings(orderSettings);
        return null;
    }
}
