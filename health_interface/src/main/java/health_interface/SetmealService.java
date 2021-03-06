package health_interface;

import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.pojo.CheckGroup;
import health_pojo.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    PageResult findSetmeal(QueryPageBean queryPageBean);

    /**
     * 添加套餐
     * @param setmeal
     */
    void addSetmeal(Setmeal setmeal , Integer[] itemIds);

    CheckGroup[] findCheckGroup();

    Setmeal[] findAllSetmeal();

    Setmeal findSetmealDetail(int id);

    Setmeal findSetmealDetailById(int id);
}
