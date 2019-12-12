package health_mapper;

import health_pojo.entity.QueryPageBean;
import health_pojo.pojo.CheckGroup;
import health_pojo.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealMapper {
    List<Setmeal> findSetmeal(QueryPageBean queryPageBean);

    void addSetmeal(Setmeal setmeal);

    void addCheckGroups(@Param("groupId") Integer id, @Param("ids") Integer[] itemIds);

    CheckGroup[] findCheckGroup();

    Setmeal[] findAllSetmeal();

    Setmeal findSetmealDetail(@Param("id") int id);

    Setmeal findSetmealDetailById(@Param("id")int id);
}
