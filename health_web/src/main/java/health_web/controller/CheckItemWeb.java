package health_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import health_interface.CheckItemService;
import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.CheckItem;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/checkItem")
public class CheckItemWeb {

    @Reference
    private CheckItemService checkItemService;
    
    @RequestMapping("addCheckItem")
    public Result addCheckItem(@RequestBody CheckItem checkItem) {
      return checkItemService.addOrUpdateCheckItem(checkItem);

    }

    /**
     * 分页查询检查项
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findCheckItem")
    public Result findCheckItem(@RequestBody QueryPageBean queryPageBean) {
        PageResult checkItem = checkItemService.findCheckItem(queryPageBean);
        return new Result(true,"查询成功！！！" ,checkItem);
    }

    /**
     * 删除检查项
     * @param checkItem
     * @return
     */
    @RequestMapping("/delateCheckItem.do")
    public Result delateCheckItem(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.delateCheckItem(checkItem);
        } catch (Exception e) {
            return new Result(false,"次检查项已被关联，删除失败！！！");
        }
        return new Result(true,"删除数据成功！");
    }

    @RequestMapping("findAllCheckItem.do")
    public Result findAllCheckItem() {
       List<CheckItem> checkItems =  checkItemService.findAllCheckItem();
        return new Result(true , "查询成功", checkItems);
    }
}
