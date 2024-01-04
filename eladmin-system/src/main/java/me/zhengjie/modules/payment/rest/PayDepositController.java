package me.zhengjie.modules.payment.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.payment.domain.PayDeposit;
import me.zhengjie.modules.payment.service.PayDepositService;
import me.zhengjie.modules.payment.service.dto.PayDepositQueryCriteria;
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
import me.zhengjie.modules.payment.service.dto.PayDepositDto;

/**
* @author bryan
* @date 2024-01-05
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "deposit管理")
@RequestMapping("/api/payDeposit")
public class PayDepositController {

    private final PayDepositService payDepositService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('payDeposit:list')")
    public void exportPayDeposit(HttpServletResponse response, PayDepositQueryCriteria criteria) throws IOException {
        payDepositService.download(payDepositService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询deposit")
    @ApiOperation("查询deposit")
    @PreAuthorize("@el.check('payDeposit:list')")
    public ResponseEntity<PageResult<PayDepositDto>> queryPayDeposit(PayDepositQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(payDepositService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增deposit")
    @ApiOperation("新增deposit")
    @PreAuthorize("@el.check('payDeposit:add')")
    public ResponseEntity<Object> createPayDeposit(@Validated @RequestBody PayDeposit resources){
        payDepositService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改deposit")
    @ApiOperation("修改deposit")
    @PreAuthorize("@el.check('payDeposit:edit')")
    public ResponseEntity<Object> updatePayDeposit(@Validated @RequestBody PayDeposit resources){
        payDepositService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除deposit")
    @ApiOperation("删除deposit")
    @PreAuthorize("@el.check('payDeposit:del')")
    public ResponseEntity<Object> deletePayDeposit(@RequestBody Long[] ids) {
        payDepositService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}