package health_interface;

import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {

    void addCheckGroup(Integer[] ids, CheckGroup checkGroup);

    PageResult findCheckGroup(QueryPageBean queryPageBean);

    Integer[] findCollection(Integer groupId);

    void deleteGroup(Integer groupId);
}
