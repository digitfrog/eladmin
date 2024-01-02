/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
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
* @website https://eladmin.vip
* @author mk
* @date 2024-01-02
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "payment管理")
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
    @Log("查询payment")
    @ApiOperation("查询payment")
    @PreAuthorize("@el.check('payDeposit:list')")
    public ResponseEntity<PageResult<PayDepositDto>> queryPayDeposit(PayDepositQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(payDepositService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增payment")
    @ApiOperation("新增payment")
    @PreAuthorize("@el.check('payDeposit:add')")
    public ResponseEntity<Object> createPayDeposit(@Validated @RequestBody PayDeposit resources){
        payDepositService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改payment")
    @ApiOperation("修改payment")
    @PreAuthorize("@el.check('payDeposit:edit')")
    public ResponseEntity<Object> updatePayDeposit(@Validated @RequestBody PayDeposit resources){
        payDepositService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除payment")
    @ApiOperation("删除payment")
    @PreAuthorize("@el.check('payDeposit:del')")
    public ResponseEntity<Object> deletePayDeposit(@RequestBody Long[] ids) {
        payDepositService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}