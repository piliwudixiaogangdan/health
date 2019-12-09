package health_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import health_interface.SetmealService;
import health_mapper.SetmealMapper;
import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public PageResult findSetmeal(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
       List<Setmeal> setmeals = setmealMapper.findSetmeal(queryPageBean);
        PageInfo<Setmeal>  setmealPageInfo = new PageInfo<>(setmeals);
        long total = setmealPageInfo.getTotal();
        return new PageResult(total , setmealPageInfo.getList());
    }
    /**
     * 添加套餐
     * @param setmeal
     */
    @Override
    public void addSetmeal(Setmeal setmeal) {
        //添加套餐内容
        setmealMapper.addSetmeal(setmeal);
        //添加套餐中所包含的检查组
        if (setmeal.getCheckGroups() != null) {
            setmealMapper.addCheckGroups(setmeal.getId(), setmeal.getCheckGroups());
        } else {
            System.out.println("没有检查组！添加失败！");
        }

    }

    @Override
    public Integer[] findCheckGroup() {

        return new Integer[0];
    }
}
