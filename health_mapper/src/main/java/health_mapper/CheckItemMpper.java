package health_mapper;

import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemMpper {
    void addCheckItem(CheckItem checkItem);

    List<CheckItem> findCheckItem(@Param("queryString") String queryString, @Param("front") int front, @Param("pageSize") Integer pageSize);

    Long findTotalRecord(@Param("queryString") String queryString);

    void updateCheckItem(CheckItem checkItem);

    void delateCheckItem(CheckItem checkItem);

    List<CheckItem> findAllCheckItem();
}
