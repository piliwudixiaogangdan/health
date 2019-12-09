package health_web;

import com.alibaba.dubbo.config.annotation.Reference;
import health_common.ImageUtil;
import health_interface.SetmealService;
import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.Setmeal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealWeb {

    @Reference
    private SetmealService setMealService;

    /**
     * 查询套餐列表
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findSetmeal.do")
    public Result findSetmeal(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setMealService.findSetmeal(queryPageBean);
        return new Result(true, "查询成功", pageResult);
    }

    /**
     * 查询检查组id列表
     * @return
     */
    public Integer[] findCheckGroup() {
       return setMealService.findCheckGroup();

    }

    /**
     * 添加
     * @param setmeal
     * @return
     */
    @RequestMapping("/addSetmeal.do")
    public Result addSetmeal(@RequestBody Setmeal setmeal) {
        setMealService.addSetmeal(setmeal);
        return new Result(true, "添加成功！");
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("/addImg")
    public Result addImg(@RequestParam("imgFile") MultipartFile file) {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String prefix = originalFilename.substring(i);
            String fileName = UUID.randomUUID().toString() + "." + prefix;
            try {
                ImageUtil.uploadFile(file.getBytes() , fileName);
                return new Result(true, "图片上传成功！",  "http://q271bmd79.bkt.clouddn.com/"+fileName);
            } catch (IOException e) {
                return new Result(false, "文件上传失败");
            }
        } else {
            return new Result(false, "文件上传失败");
        }


    }
}
