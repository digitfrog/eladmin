package me.zhengjie.modules.payment.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.payment.domain.PayWalletLog;
import me.zhengjie.modules.payment.service.PayWalletLogService;
import me.zhengjie.modules.payment.service.dto.PayWalletLogQueryCriteria;
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
import me.zhengjie.modules.payment.service.dto.PayWalletLogDto;

/**
* @author bryan
* @date 2024-01-05
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "wallet_log管理")
@RequestMapping("/api/payWalletLog")
public class PayWalletLogController {

    private final PayWalletLogService payWalletLogService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('payWalletLog:list')")
    public void exportPayWalletLog(HttpServletResponse response, PayWalletLogQueryCriteria criteria) throws IOException {
        payWalletLogService.download(payWalletLogService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询wallet_log")
    @ApiOperation("查询wallet_log")
    @PreAuthorize("@el.check('payWalletLog:list')")
    public ResponseEntity<PageResult<PayWalletLogDto>> queryPayWalletLog(PayWalletLogQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(payWalletLogService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增wallet_log")
    @ApiOperation("新增wallet_log")
    @PreAuthorize("@el.check('payWalletLog:add')")
    public ResponseEntity<Object> createPayWalletLog(@Validated @RequestBody PayWalletLog resources){
        payWalletLogService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改wallet_log")
    @ApiOperation("修改wallet_log")
    @PreAuthorize("@el.check('payWalletLog:edit')")
    public ResponseEntity<Object> updatePayWalletLog(@Validated @RequestBody PayWalletLog resources){
        payWalletLogService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除wallet_log")
    @ApiOperation("删除wallet_log")
    @PreAuthorize("@el.check('payWalletLog:del')")
    public ResponseEntity<Object> deletePayWalletLog(@RequestBody Long[] ids) {
        payWalletLogService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}