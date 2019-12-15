package health_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import health_common.POIUtils;
import health_interface.OrderSettingService;
import health_pojo.entity.Result;
import health_pojo.pojo.OrderSetting;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            if (row[0].equals("") ) {
                continue;
            }
            OrderSetting orderSetting = new OrderSetting();
            orderSetting.setNumber(Integer.parseInt(row[1]));
            orderSetting.setOrderDate(simpleDateFormat.parse(row[0]));
            orderSettings.add(orderSetting);
        }
        ordersettingService.importOrderSettings(orderSettings);
        return new Result(true,"上传文件成功！");
    }

    @RequestMapping("/findOrderSettingByDate")
    public Result findOrderSettingByDate(@RequestParam("year")String year , @RequestParam("month")String month) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        Date parse = simpleDateFormat.parse(year + "/" + month);
        List<OrderSetting> orderSettings =  ordersettingService.findOrderSettingByDate(parse);
        List<Map> res = new ArrayList<Map>();
        for (OrderSetting orderSetting : orderSettings) {
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("date",orderSetting.getOrderDate().getDate());
            objectObjectHashMap.put("number", orderSetting.getNumber());
            objectObjectHashMap.put("reservations", orderSetting.getReservations());
            res.add(objectObjectHashMap);
        }

        return new Result(true, "查询成功", res);
    }

    @RequestMapping("/handleOrderSet")
    public Result handleOrderSet(@RequestBody Date day ,@RequestParam("count")int count) {


        try {
            ordersettingService.handleOrderSet(day, count);
            return new Result(true, "设置成功！");
        } catch (Exception e) {
            return new Result(false, "设置失败！！");
        }

    }
}
