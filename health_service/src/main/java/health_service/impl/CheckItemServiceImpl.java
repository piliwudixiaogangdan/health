package health_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import health_interface.CheckItemService;
import health_mapper.CheckItemMpper;
import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    
    @Autowired
    private CheckItemMpper checkItemMpper;

    /**
     * 添加checkItem
     * @param checkItem
     */
    @Override
    public Result addOrUpdateCheckItem(CheckItem checkItem) {
        Integer id = checkItem.getId();
        if (id == null) {
            checkItemMpper.addCheckItem(checkItem);
            return  new Result(true , "添加数据成功！");
        } else {
            checkItemMpper.updateCheckItem(checkItem);
          return  new Result(true , "修改数据成功！");
        }

    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findCheckItem(QueryPageBean queryPageBean) {
        /*查询总记录数*/
      Long totalRecord = checkItemMpper.findTotalRecord(queryPageBean.getQueryString());
        /*查询分页内容*/
        int front = queryPageBean.getPageSize()*(queryPageBean.getCurrentPage()-1);

        List<CheckItem> checkItem = checkItemMpper.findCheckItem(queryPageBean.getQueryString() , front , queryPageBean.getPageSize());
        return new PageResult(totalRecord , checkItem);
    }

    /*删除checkItem*/
    @Override
    public void delateCheckItem(CheckItem checkItem) {
        checkItemMpper.delateCheckItem(checkItem);
    }

    @Override
    public List<CheckItem> findAllCheckItem() {

        return checkItemMpper.findAllCheckItem();
    }


}
