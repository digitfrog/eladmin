package me.zhengjie.modules.payment.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.payment.domain.PayCodeRecords;
import me.zhengjie.modules.payment.service.PayCodeRecordsService;
import me.zhengjie.modules.payment.service.dto.PayCodeRecordsQueryCriteria;
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
import me.zhengjie.modules.payment.service.dto.PayCodeRecordsDto;

/**
* @author bryan
* @date 2024-01-05
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "code_records管理")
@RequestMapping("/api/payCodeRecords")
public class PayCodeRecordsController {

    private final PayCodeRecordsService payCodeRecordsService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('payCodeRecords:list')")
    public void exportPayCodeRecords(HttpServletResponse response, PayCodeRecordsQueryCriteria criteria) throws IOException {
        payCodeRecordsService.download(payCodeRecordsService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询code_records")
    @ApiOperation("查询code_records")
    @PreAuthorize("@el.check('payCodeRecords:list')")
    public ResponseEntity<PageResult<PayCodeRecordsDto>> queryPayCodeRecords(PayCodeRecordsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(payCodeRecordsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增code_records")
    @ApiOperation("新增code_records")
    @PreAuthorize("@el.check('payCodeRecords:add')")
    public ResponseEntity<Object> createPayCodeRecords(@Validated @RequestBody PayCodeRecords resources){
        payCodeRecordsService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改code_records")
    @ApiOperation("修改code_records")
    @PreAuthorize("@el.check('payCodeRecords:edit')")
    public ResponseEntity<Object> updatePayCodeRecords(@Validated @RequestBody PayCodeRecords resources){
        payCodeRecordsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除code_records")
    @ApiOperation("删除code_records")
    @PreAuthorize("@el.check('payCodeRecords:del')")
    public ResponseEntity<Object> deletePayCodeRecords(@RequestBody Long[] ids) {
        payCodeRecordsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}