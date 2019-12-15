package health_mapper;

import health_pojo.pojo.Member;
import health_pojo.pojo.Order;
import org.apache.ibatis.annotations.Param;


import java.util.Map;

public interface MOrderMapper {

    void addOrderInfo(Order order);

    Member findMemberBytelephone(@Param("telephone") String telephone);

    void addMember(Member member);

    Order findOrderRecord(@Param("orderDate") String orderDate , @Param("memberId") Integer id);

    Map findOrderDetail(@Param("memberId") Integer member);
}
