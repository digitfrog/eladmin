package me.zhengjie.modules.payment.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.payment.domain.PayChannel;
import me.zhengjie.modules.payment.service.PayChannelService;
import me.zhengjie.modules.payment.service.dto.PayChannelQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;
import me.zhengjie.modules.payment.service.dto.PayChannelDto;

/**
* @author bryan
* @date 2024-01-05
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "channel管理")
@RequestMapping("/api/payChannel")
public class PayChannelController {

    private final PayChannelService payChannelService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('payChannel:list')")
    public void exportPayChannel(HttpServletResponse response, PayChannelQueryCriteria criteria) throws IOException {
        payChannelService.download(payChannelService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询channel")
    @ApiOperation("查询channel")
    @PreAuthorize("@el.check('payChannel:list')")
    public ResponseEntity<PageResult<PayChannelDto>> queryPayChannel(PayChannelQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(payChannelService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增channel")
    @ApiOperation("新增channel")
    @PreAuthorize("@el.check('payChannel:add')")
    public ResponseEntity<Object> createPayChannel(@Validated @RequestBody PayChannel resources){
        payChannelService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改channel")
    @ApiOperation("修改channel")
    @PreAuthorize("@el.check('payChannel:edit')")
    public ResponseEntity<Object> updatePayChannel(@Validated @RequestBody PayChannel resources){
        payChannelService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除channel")
    @ApiOperation("删除channel")
    @PreAuthorize("@el.check('payChannel:del')")
    public ResponseEntity<Object> deletePayChannel(@RequestBody Integer[] ids) {
        payChannelService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}