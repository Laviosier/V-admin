package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IRoleService;
import com.example.springboot.entity.Role;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xch
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        roleService.removeById(id);
        return Result.success();
    }

    // 批量删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        roleService.removeByIds(ids);
        return Result.success();
    }

    // 查询所有
    @GetMapping
    public Result findAll() {
        return Result.success(roleService.list());
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.getById(id));
    }

    // 分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 绑定角色和菜单的关系
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID数组
     * @return
     */
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        return Result.success(roleService.getRoleMenu(roleId));
    }
}

