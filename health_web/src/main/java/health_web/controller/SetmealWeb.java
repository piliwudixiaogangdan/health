package health_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import health_common.ImageUtil;
import health_interface.SetmealService;
import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.entity.Result;
import health_pojo.pojo.CheckGroup;
import health_pojo.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealWeb {

    @Reference
    private SetmealService setMealService;

    @Autowired
    private JedisPool jedisPool;
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
    @RequestMapping("findCheckGroup.do")
    public CheckGroup[] findCheckGroup() {
       return setMealService.findCheckGroup();

    }

    /**
     * 向数据库 添加 套餐信息
     * @param setmeal
     * @return
     */
    @RequestMapping("/addSetmeal.do")
    public Result addSetmeal(@RequestParam("itemIds")Integer[] itemIds,@RequestBody Setmeal setmeal) {
        try {
            setMealService.addSetmeal(setmeal , itemIds);
            jedisPool.getResource().sadd("sjkImg" , setmeal.getImg());
            return new Result(true, "添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
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
                jedisPool.getResource().sadd("qnyImg" , fileName);
                return new Result(true, "图片上传成功！",  fileName);
                //上传图片成功之后向redis数据库保存一份

            } catch (IOException e) {
                return new Result(false, "文件上传失败");
            }
        } else {
            return new Result(false, "文件上传失败");
        }
    }


}
