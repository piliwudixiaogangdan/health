package health_interface;

import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    Result addOrUpdateCheckItem(CheckItem checkItem);

    PageResult findCheckItem(QueryPageBean queryPageBean);

    void delateCheckItem(CheckItem checkItem);

    List<CheckItem> findAllCheckItem();
}
