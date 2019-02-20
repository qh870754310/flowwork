package com.qh.modules.sys.service.impl;

import com.qh.modules.common.common.Constant;
import com.qh.modules.common.utils.UserUtils;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.sys.dao.MenuDao;
import com.qh.modules.sys.entity.MenuEntity;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.service.MenuService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/4/19.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<MenuEntity> queryList(Map<String, Object> map) {
        return menuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return menuDao.queryTotal(map);
    }

    @Override
    public List<MenuEntity> queryByUserId(String userId) {
        List<MenuEntity> menuEntities = menuDao.queryByUserId(userId);
        for (int i = 0; i < menuEntities.size(); i++) {
            MenuEntity menu = menuEntities.get(i);
            if (menu == null && null == menu.getId()) {
                menuEntities.remove(i);
            }
        }
        return menuEntities;
    }

    @Override
    public List<MenuEntity> queryListParentId(String parentId) {
        return menuDao.queryListParentId(parentId);
    }

    @Override
    public MenuEntity queryObject(String id) {
        return menuDao.queryObject(id);
    }

    @Override
    public String save(MenuEntity menu) {
        UserEntity currentUser = UserUtils.getCurrentUser();
        menu.setId(Utils.uuid());
        menu.setCreateId(currentUser.getId());
        menu.setStatus(Constant.YESNO.NO.getValue());
        menu.setCreateTime(new Date());
        menu.setBapid(currentUser.getBapid());
        menu.setBaid(currentUser.getBaid());
        menuDao.save(menu);
        return menu.getId();
    }

    @Override
    public void update(MenuEntity menu) {
        UserEntity currentUser = UserUtils.getCurrentUser();
        menu.setUpdateId(currentUser.getId());
        menu.setUpdateTime(new Date());
        menuDao.update(menu);

    }

    @Override
    public void delete(String id) {
        menuDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        menuDao.deleteBatch(ids);
    }

    @Override
    public List<MenuEntity> queryListUser(String userId) {
        List<MenuEntity> menuEntities = null;
        if (userId.equals(Constant.SUPERR_USER)) {
            menuEntities = menuDao.queryList(new HashedMap());
        } else {
            menuEntities = menuDao.queryByUserId(userId);
        }
        List<String> menuIds = new ArrayList<>();
        for (MenuEntity menu : menuEntities) {
            if (menu != null && null != menu.getId()) {
                menuIds.add(menu.getId());
            }
        }
        //查询出根菜单
        List<MenuEntity> rootMenus = queryMenuByParentId("0", menuIds);
        //递归查询除所有子资源的子资源
        List<MenuEntity> treeMenus = getTreeMenus(rootMenus, menuIds);
        return treeMenus;
    }

    /**
     * 递归查出所有菜单的子菜单，子菜单的所有子菜单，只包括用户授权的资源
     * @param resources 源菜单
     * @param menuIds   用户所有授权资源
     * @return
     */
    public List<MenuEntity> getTreeMenus(List<MenuEntity> resources, List<String> menuIds) {
        List<MenuEntity> treeMenus = new ArrayList<>();
        for (MenuEntity menu: resources) {
            if (Constant.MenuType.CATALOG.getValue().equals(menu.getType())) {
                List<MenuEntity> childMenus = queryMenuByParentId(menu.getId(), menuIds);
                menu.setList(getTreeMenus(childMenus, menuIds));
            }
            treeMenus.add(menu);
        }
        return treeMenus;
    }

    /**
     * 根据上级父id，查询除下级所有该用户已经授权的资源
     * @param parentId 父id
     * @param menuIds 授权资源ids
     * @return
     */
    public List<MenuEntity> queryMenuByParentId(String parentId, List<String> menuIds) {
        //根据父id，查询所有下级菜单
        List<MenuEntity> menuEntities = menuDao.queryListParentId(parentId);
        List<MenuEntity> reMenus = new ArrayList<>();
        for (MenuEntity menu : menuEntities) {
            //如果下级资源在用户授权资源中，则添加
            if (menuIds.contains(menu.getId())) {
                reMenus.add(menu);
            }
        }
        return reMenus;
    }

    @Override
    public List<MenuEntity> queryNotButtonList() {
        List<MenuEntity> menuList = menuDao.queryNotButtonList(new String[]{Constant.MenuType.CATALOG.getValue(), Constant.MenuType.MENU.getValue()});
        return menuList;

    }
}
