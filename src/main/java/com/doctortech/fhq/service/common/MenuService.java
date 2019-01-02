package com.doctortech.fhq.service.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.fhq.bean.MenuResTree;
import com.doctortech.fhq.bean.MenuResources;
import com.doctortech.fhq.bean.Router;
import com.doctortech.fhq.bean.TreeNode;
import com.doctortech.fhq.entity.jpa.common.Menu;
import com.doctortech.fhq.repository.mapper.common.MenuMapper;
import com.doctortech.fhq.repository.mapper.common.ResourceMapper;
import com.doctortech.fhq.utils.SqlHelper;
import com.doctortech.framework.common.shiro.ShiroKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuMapper menuDao;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 获取用户菜单
     *
     * @param rolesId
     * @return
     */
    public List<Menu> selectUserMenus(List<Long> rolesId) {
        if (rolesId.isEmpty()) {
            return null;
        }
        return menuDao.selectUserMenus(rolesId);
    }

    /**
     * 获取用户路由
     *
     * @param rolesId
     * @return
     */
    public List<Router> getUserRouters(List<Long> rolesId) {
        List<Menu> menu = selectUserMenus(rolesId);
        List<Router> routers = new ArrayList<>();
        //根节点
        List<Menu> parents = menu.stream().filter(m -> m.getParentId() == null).collect(Collectors.toList());
        //子节点
        List<Menu> child = menu.stream().filter(m -> m.getParentId() != null).collect(Collectors.toList());
        if (parents.isEmpty()) {
            return routers;
        }
        parents.forEach(p -> {
            routers.add(getRouter(p, child));
        });
        return routers;
    }

    private Router getRouter(Menu parent, List<Menu> all) {
        Router router = new Router();
        router.setPath(parent.getPath());
        router.setComponent(parent.getComponent());
        router.setMeta(JSONObject.parseObject(parent.getMeta()));
        router.setName(parent.getCode());
        router.setTitle(parent.getTitle());
        router.setId(parent.getId());
        router.setParentId(parent.getParentId());
        router.setSort(parent.getSort());
        List<Router> children = new ArrayList<>();
        all.forEach(m -> {
            if (parent.getId().longValue() == m.getParentId().longValue()) {
                children.add(getRouter(m, all));
            }
        });
        router.setChildren(children);
        return router;
    }

    /**
     * 菜单列表
     *
     * @return
     */
    public List<Router> getMenuList(String title) {
        List<Router> routers = new ArrayList<>();
        QueryWrapper<Menu> condition = new QueryWrapper<>();
        if (StringUtils.isNotBlank(title)) {
            condition.apply(SqlHelper.getSqlLike(), SqlHelper.getSqlLikeParams(title));
        }
        condition.orderByAsc("sort");
        List<Menu> list = menuDao.selectList(condition);
        //根节点
        List<Menu> parents = list.stream().filter(m -> m.getParentId() == null).collect(Collectors.toList());
        //子节点
        List<Menu> child = list.stream().filter(m -> m.getParentId() != null).collect(Collectors.toList());
        if (parents.isEmpty()) {
            return routers;
        }
        parents.forEach(p -> {
            routers.add(getRouter(p, child));
        });
        return routers;
    }

    /**
     * get zTree nodes
     *
     * @return
     */
    public List<TreeNode> getMenuTree() {

        List<TreeNode> nodes = new ArrayList<>();
        QueryWrapper<Menu> condition = new QueryWrapper<>();
        condition.orderByAsc("sort");
        List<Menu> list = menuDao.selectList(condition);
        //根节点
        List<Menu> parents = list.stream().filter(m -> m.getParentId() == null).collect(Collectors.toList());
        //子节点
        List<Menu> child = list.stream().filter(m -> m.getParentId() != null).collect(Collectors.toList());
        if (parents.isEmpty()) {
            return nodes;
        }
        parents.forEach(p -> {
            nodes.add(getTreeNode(p, child));
        });
        return nodes;
    }

    public List<TreeNode> getMenuResTree() {
        List<TreeNode> nodes = new ArrayList<>();
        List<MenuResTree> tree = menuDao.selectMenuResTree();
        List<TreeNode> menuNodes = groupRes(tree);
        //根节点
        List<TreeNode> parents = menuNodes.stream().filter(m -> m.getParentId() == null).collect(Collectors.toList());
        //子节点
        List<TreeNode> child = menuNodes.stream().filter(m -> m.getParentId() != null).collect(Collectors.toList());
        if (parents.isEmpty()) {
            return nodes;
        }
        parents.forEach(p -> {
            nodes.add(getTreeResNode(p, child));
        });
        return nodes;
    }

    private List<TreeNode> groupRes(List<MenuResTree> res) {
        long flag = -1;
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode node = null;
        for (MenuResTree tree : res) {
            if (flag == tree.getId().longValue()) {
                newResTreeNode(node,tree);
            } else {
                node = new TreeNode();
                node.setName(tree.getName());
                node.setParentId(tree.getParentId());
                node.setId(tree.getId());
                node.setChkDisabled(true);
                if (tree.getResId() != null) {
                    newResTreeNode(node,tree);
                }
                treeNodes.add(node);
            }
            flag = tree.getId();
        }
        return treeNodes;
    }

    private TreeNode newResTreeNode(TreeNode node,MenuResTree res) {
        TreeNode resource = new TreeNode();
        resource.setId(-res.getResId());
        resource.setName(res.getResName());
        resource.setParentId(res.getId());
        node.getChildren().add(resource);
        return node;
    }

    private TreeNode getTreeResNode(TreeNode parent, List<TreeNode> all) {
        List<TreeNode> children = parent.getChildren();
        int index = 0;
        for (TreeNode t : all) {
            if (parent.getId().longValue() == t.getParentId().longValue()) {
                children.add(index, getTreeResNode(t, all));
                index++;
            }
        }
        return parent;
    }

    private TreeNode getTreeNode(Menu parent, List<Menu> all) {
        TreeNode node = new TreeNode();
        node.setName(parent.getTitle());
        node.setParentId(parent.getParentId());
        node.setId(parent.getId());
        List<TreeNode> children = node.getChildren();
        all.forEach(m -> {
            if (parent.getId().longValue() == m.getParentId().longValue()) {
                children.add(getTreeNode(m, all));
            }
        });
        return node;
    }

    public void saveOrUpdateMenu(Router r) {
        if (r.getId() != null) {
            Menu m = menuDao.selectById(r.getId());
            if (m != null) {
                m.setCode(m.getCode());
                m.setMeta(m.getMeta());
                m.setSort(r.getSort());
                m.setComponent(r.getComponent());
                m.setParentId(r.getParentId());
                m.setPath(r.getPath());
                m.setTitle(r.getTitle());
                m.setUpdateTime(new Date());
                m.setUpdateUserId(ShiroKit.getUser().getId());
                menuDao.updateById(m);
            }
        } else {
            Menu menu = new Menu();
            BeanUtils.copyProperties(r, menu);
            menu.setMeta(r.getMeta().toJSONString());
            menu.setCode(r.getName());
            menu.setCreateTime(new Date());
            menu.setCreateUserId(ShiroKit.getUser().getId());
            menuDao.insert(menu);
        }
    }

    public void delete(Long id) {
        menuDao.deleteById(id);
    }


    public IPage<MenuResources> getMenuResources(MenuResources res, Page<MenuResources> page) {
        res.setMenuName(SqlHelper.getSqlLikeParams(res.getMenuName()));
        res.setUrl(SqlHelper.getSqlLikeParams(res.getUrl()));
        res.setCode(SqlHelper.getSqlLikeParams(res.getCode()));
        res.setName(SqlHelper.getSqlLikeParams(res.getName()));
        return resourceMapper.getMenuRes(page, res);
    }
}
