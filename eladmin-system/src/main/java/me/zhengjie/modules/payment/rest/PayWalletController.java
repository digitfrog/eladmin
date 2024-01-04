package me.zhengjie.modules.payment.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.payment.domain.PayWallet;
import me.zhengjie.modules.payment.service.PayWalletService;
import me.zhengjie.modules.payment.service.dto.PayWalletQueryCriteria;
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
import me.zhengjie.modules.payment.service.dto.PayWalletDto;

/**
* @author bryan
* @date 2024-01-05
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "wallet管理")
@RequestMapping("/api/payWallet")
public class PayWalletController {

    private final PayWalletService payWalletService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('payWallet:list')")
    public void exportPayWallet(HttpServletResponse response, PayWalletQueryCriteria criteria) throws IOException {
        payWalletService.download(payWalletService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询wallet")
    @ApiOperation("查询wallet")
    @PreAuthorize("@el.check('payWallet:list')")
    public ResponseEntity<PageResult<PayWalletDto>> queryPayWallet(PayWalletQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(payWalletService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增wallet")
    @ApiOperation("新增wallet")
    @PreAuthorize("@el.check('payWallet:add')")
    public ResponseEntity<Object> createPayWallet(@Validated @RequestBody PayWallet resources){
        payWalletService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改wallet")
    @ApiOperation("修改wallet")
    @PreAuthorize("@el.check('payWallet:edit')")
    public ResponseEntity<Object> updatePayWallet(@Validated @RequestBody PayWallet resources){
        payWalletService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除wallet")
    @ApiOperation("删除wallet")
    @PreAuthorize("@el.check('payWallet:del')")
    public ResponseEntity<Object> deletePayWallet(@RequestBody Long[] ids) {
        payWalletService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}