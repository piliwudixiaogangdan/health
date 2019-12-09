package health_mapper;

import health_pojo.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupMapper {

    void addCheckGroup(CheckGroup checkGroup);

    void addCollection();

    void addGroupAanItems(@Param("groupId")Integer groupId, @Param("itemId") Integer itemId);

    List<CheckGroup> findCheckGroup(@Param("queryString") String queryString);

    Integer[] findCollection(@Param("groupId") Integer groupId);

    void editCheckGroup(CheckGroup checkGroup);

    void clearRelation(@Param("groupId") Integer groupId);

    void deleteGroup(@Param("groupId") Integer groupId);
}
