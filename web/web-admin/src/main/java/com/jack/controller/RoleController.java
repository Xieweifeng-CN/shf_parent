package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jack.base.BaseController;
import com.jack.entity.Role;
import com.jack.service.PermissionService;
import com.jack.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/5
 * @Description :
 **/
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Reference
    private RoleService roleService;

    @Reference
    private PermissionService permissionService;

    private final static String LIST_ACTION = "redirect:/role";
    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/successPage";
    private final static String PAGE_ASSIGN_SHOW = "role/assignShow";

    /**
     * 进入分配权限页面
     */
    @GetMapping("/assignShow/{roleId}")
    @PreAuthorize("hasAuthority('role.assgin')")
    public String assignShow(ModelMap model,@PathVariable Long roleId) {
        //调用PermissionService中查询所有权限的方法
        List<Map<String,Object>> zNodes = permissionService.findPermissionByRoleId(roleId);
        model.addAttribute("zNodes", zNodes);
        model.addAttribute("roleId", roleId);
        return PAGE_ASSIGN_SHOW;
    }

    /**
     * 给角色分配权限
     */
    @PostMapping("/assignPermission")
    @PreAuthorize("hasAuthority('role.assgin')")
    public String assignPermission(Long roleId,Long[] permissionIds) {
        //调用PermissionService中分配权限的方法
        permissionService.saveRolePermissionRealtionShip(roleId, permissionIds);
        return PAGE_SUCCESS;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('role.delete')")
    public String delete(@PathVariable Long id){
        roleService.delete(id);
        return LIST_ACTION;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('role.edit')")
    public String update(Role role){
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('role.edit')")
    public String edit(ModelMap model, @PathVariable Long id){
        Role role = roleService.getById(id);
        model.addAttribute("role",role);
        return PAGE_EDIT;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('role.create')")
    public String save(Role role) throws IOException {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('role.create')")
    public String create(ModelMap model) {
        return PAGE_CREATE;
    }

    @RequestMapping
    @PreAuthorize("hasAuthority('role.show')")
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String,Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }
}
