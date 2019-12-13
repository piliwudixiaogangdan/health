package health_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import health_interface.MOrderService;
import health_mapper.MOrderMapper;
import health_pojo.entity.Result;
import health_pojo.pojo.Member;
import health_pojo.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service(interfaceClass = MOrderService.class)
public class MOrderServiceImpl implements MOrderService {

    @Autowired
    private MOrderMapper mOrderMapper;

    /**
     * 判断该用户是否是会员并完成注册
     * @param map
     */
    @Override
    public Result addOrderInfo(Map map) {
        Member member = new Member();
        member.setIdCard((String)map.get("idCard"));
        member.setName((String)map.get("name"));
        String telephone = (String) map.get("telephone");
        member.setPhoneNumber((String)map.get("telephone"));
        member.setSex((String)map.get("sex"));
        member.setRegTime(new Date());

        //在数据库通过电话查询该用户是否存在
       Member memberExist = mOrderMapper.findMemberBytelephone(telephone);
        if (memberExist !=null) {
            //如果该会员存在，完成预约
            //预约前先判断该会员是否已预约，避免重复预约
            Order orderDate = mOrderMapper.findOrderRecord((String) map.get("orderDate"), memberExist.getId());
            if (orderDate != null) {
                return new Result(false, "您已预约过，请在预约详情中查看！");
            }
            order(map, memberExist.getId());
            return new Result(true, "预约成功！" , member.getId());
        } else {
            //如果该会员不存在，注册会员再完成预约
            mOrderMapper.addMember(member);
            order(map , member.getId());
            return new Result(true, "预约成功！" , member.getId());
        }

    }

    @Override
    public Map findOrderDetail(Integer member) {
//         返回的信息：
//         体检人姓名
//         体检套餐
//         体检日期
//         预约类型


        return mOrderMapper.findOrderDetail(member);
    }

    //预约方法
    public void order(Map map , Integer memberId)  {

        Order order = new Order();
        order.setMemberId(memberId);
        try {
            order.setOrderDate( new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get("orderDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setOrderType((String) map.get("orderType"));
        order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
        order.setOrderStatus("否");

        mOrderMapper.addOrderInfo(order);
    }
}
