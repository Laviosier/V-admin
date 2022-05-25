package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.ICourseService;
import com.example.springboot.entity.Course;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xch
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/course")
        public class CourseController {
    
@Resource
private ICourseService courseService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Course course){
    courseService.saveOrUpdate(course);
        return Result.success();
        }

// 删除
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
    courseService.removeById(id);
        return Result.success();
        }

// 批量删除
@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids){
    courseService.removeByIds(ids);
        return Result.success();
        }

// 查询所有
@GetMapping
public Result findAll(){
        return Result.success(courseService.list());
        }

// 根据ID查询
@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id){
        return Result.success(courseService.getById(id));
        }

// 分页查询
@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize){
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        return Result.success(courseService.page(new Page<>(pageNum,pageSize),queryWrapper));
        }
        }

