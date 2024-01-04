package me.zhengjie.modules.payment.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.payment.domain.PayWithdrawal;
import me.zhengjie.modules.payment.service.PayWithdrawalService;
import me.zhengjie.modules.payment.service.dto.PayWithdrawalQueryCriteria;
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
import me.zhengjie.modules.payment.service.dto.PayWithdrawalDto;

/**
* @author bryan
* @date 2024-01-05
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "withdrawal管理")
@RequestMapping("/api/payWithdrawal")
public class PayWithdrawalController {

    private final PayWithdrawalService payWithdrawalService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('payWithdrawal:list')")
    public void exportPayWithdrawal(HttpServletResponse response, PayWithdrawalQueryCriteria criteria) throws IOException {
        payWithdrawalService.download(payWithdrawalService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询withdrawal")
    @ApiOperation("查询withdrawal")
    @PreAuthorize("@el.check('payWithdrawal:list')")
    public ResponseEntity<PageResult<PayWithdrawalDto>> queryPayWithdrawal(PayWithdrawalQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(payWithdrawalService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增withdrawal")
    @ApiOperation("新增withdrawal")
    @PreAuthorize("@el.check('payWithdrawal:add')")
    public ResponseEntity<Object> createPayWithdrawal(@Validated @RequestBody PayWithdrawal resources){
        payWithdrawalService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改withdrawal")
    @ApiOperation("修改withdrawal")
    @PreAuthorize("@el.check('payWithdrawal:edit')")
    public ResponseEntity<Object> updatePayWithdrawal(@Validated @RequestBody PayWithdrawal resources){
        payWithdrawalService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除withdrawal")
    @ApiOperation("删除withdrawal")
    @PreAuthorize("@el.check('payWithdrawal:del')")
    public ResponseEntity<Object> deletePayWithdrawal(@RequestBody Long[] ids) {
        payWithdrawalService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}