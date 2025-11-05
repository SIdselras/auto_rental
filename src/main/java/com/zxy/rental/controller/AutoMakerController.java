package com.zxy.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.rental.entity.AutoMaker;
import com.zxy.rental.service.IAutoMakerService;
import com.zxy.rental.utils.Result;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
@RestController
@RequestMapping("/rental/autoMaker")
public class AutoMakerController {
    @Resource
    private IAutoMakerService autoMakerService;

    @PostMapping("/{start}/{size}")
    @ApiOperation("厂商分页查询")
    public Result<Page> search(@PathVariable int start,
                         @PathVariable int size,
                         @RequestBody AutoMaker autoMaker) {
        Page<AutoMaker> page = autoMakerService.search(start, size, autoMaker);
        return Result.success(page);
    }
}
