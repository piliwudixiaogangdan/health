package health_web;

import com.alibaba.dubbo.config.annotation.Reference;
import health_interface.CheckGroupService;
import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/checkGroup")
public class CheckGroupWeb {

    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/addCheckGroup")
    public Result addCheckGroup(@RequestParam("checkItemIds") Integer[] checkItemIds, @RequestBody CheckGroup checkGroup) {

        checkGroupService.addCheckGroup(checkItemIds, checkGroup);
        if (checkGroup.getId() == null) {
            return new Result(true, "添加成功");
        } else {
            return new Result(true, "修改成功");
        }

    }

    @RequestMapping("findCheckGroup.do")
    public Result findCheckGroup(@RequestBody QueryPageBean queryPageBean) {
        PageResult checkGroup = checkGroupService.findCheckGroup(queryPageBean);
        Result result = new Result(true, "查询成功", checkGroup);

        return result;
    }

    @RequestMapping("findCollection")
    public Result findCollection (@RequestParam("id") Integer groupId) {
       Integer[] itemIds = checkGroupService.findCollection(groupId);
        return new Result(true, "查询成功",itemIds);

    }

    @RequestMapping("deleteGroup")
    public Result deleteGroup(@RequestParam("id") Integer groupId) {

        try {
            checkGroupService.deleteGroup(groupId);
            return new Result(true, "删除成功！");
        } catch (Exception e) {
            return new Result(false, "该检查组被引用，删除失败！");
        }
    }
}
