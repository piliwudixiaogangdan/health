package health_mobile_web;


import com.alibaba.dubbo.config.annotation.Reference;
import health_interface.SetmealService;
import health_pojo.entity.Result;
import health_pojo.pojo.Setmeal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mSetmeal")
public class MSetmealController {

    @Reference
    SetmealService setmealService;

    /**
     * 查询所有的套餐
     * @return
     */
    @RequestMapping("/findAllSetmeal.do")
    public Result findAllSetmeal() {
       Setmeal[] setmeals =  setmealService.findAllSetmeal();
       return new Result(true, "查询成功！", setmeals);
    }

    /**
     * 根据id查询套餐内所有信息
     * @param id
     * @return
     */
    @RequestMapping("/findSetmealDetail")
    public Result findSetmealDetail(@RequestParam("id") int id) {
       Setmeal setmeals =  setmealService.findSetmealDetail(id);
       return new Result(true , "查询成功" , setmeals);
    }

    @RequestMapping("/findSetmealDetailById")
    public Result findSetmealDetailById(@RequestParam("id") int id) {
        Setmeal setmeals =  setmealService.findSetmealDetailById(id);
        return new Result(true , "查询成功" , setmeals);
    }

}
