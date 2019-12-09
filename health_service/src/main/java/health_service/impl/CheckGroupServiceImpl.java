package health_service.impl;

import com.alibaba.dubbo.config.annotation.Service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import health_interface.CheckGroupService;
import health_mapper.CheckGroupMapper;
import health_pojo.entity.PageResult;
import health_pojo.entity.QueryPageBean;
import health_pojo.pojo.CheckGroup;
import health_pojo.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;
    /**
     * 添加或修改新的检查组
     * @param checkGroup
     */
    @Override
    public void addCheckGroup(Integer[] checkItemIds, CheckGroup checkGroup) {

        if (checkGroup.getId() == null) {
            //向checkGroup表中添加数据并向实体类中添加id值
            checkGroupMapper.addCheckGroup(checkGroup);

            //向groupitem表中添加group和item的关系
            getCollection(checkGroup, checkItemIds);
        } else {
            //修改可以直接将原值覆盖掉
            checkGroupMapper.editCheckGroup(checkGroup);
            //修改检查组和检查项之间的关系需要将原来的关系删除掉
            checkGroupMapper.clearRelation(checkGroup.getId());
            getCollection(checkGroup,checkItemIds ) ;
        }


    }

    /**
     * 查询方法
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findCheckGroup(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

       List<CheckGroup> checkGroups =  checkGroupMapper.findCheckGroup(queryPageBean.getQueryString());
        PageInfo<CheckGroup> checkGroupPageInfo = new PageInfo<>(checkGroups);
        PageResult pageResult = new PageResult(checkGroupPageInfo.getTotal(), checkGroups);
        return pageResult;
    }

    /**
     * 查询该检查组所包含的检查项
     * @param groupId
     * @return
     */
    @Override
    public Integer[] findCollection(Integer groupId) {
       Integer[] itemIds = checkGroupMapper.findCollection(groupId);
        return itemIds;
    }

    @Override
    public void deleteGroup(Integer groupId) {
        checkGroupMapper.deleteGroup(groupId);
        checkGroupMapper.clearRelation(groupId);
    }

    /**
     * 生成两张表中主键关系
     * @param checkGroup
     * @param ids
     * @return
     */
    public void getCollection(CheckGroup checkGroup , Integer[] ids) {
        List<CheckItem> checkItems = new ArrayList<>();
        for (Integer id : ids) {
            CheckItem checkItem = new CheckItem();
            checkItem.setId(id);
            checkItems.add(checkItem);
            checkGroupMapper.addGroupAanItems(checkGroup.getId() , id);
        }


    }
}
