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
package me.zhengjie.modules.payment.service.impl;

import me.zhengjie.modules.payment.domain.PayDeposit;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.payment.repository.PayDepositRepository;
import me.zhengjie.modules.payment.service.PayDepositService;
import me.zhengjie.modules.payment.service.dto.PayDepositDto;
import me.zhengjie.modules.payment.service.dto.PayDepositQueryCriteria;
import me.zhengjie.modules.payment.service.mapstruct.PayDepositMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author mk
* @date 2024-01-02
**/
@Service
@RequiredArgsConstructor
public class PayDepositServiceImpl implements PayDepositService {

    private final PayDepositRepository payDepositRepository;
    private final PayDepositMapper payDepositMapper;

    @Override
    public PageResult<PayDepositDto> queryAll(PayDepositQueryCriteria criteria, Pageable pageable){
        Page<PayDeposit> page = payDepositRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(payDepositMapper::toDto));
    }

    @Override
    public List<PayDepositDto> queryAll(PayDepositQueryCriteria criteria){
        return payDepositMapper.toDto(payDepositRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PayDepositDto findById(Long id) {
        PayDeposit payDeposit = payDepositRepository.findById(id).orElseGet(PayDeposit::new);
        ValidationUtil.isNull(payDeposit.getId(),"PayDeposit","id",id);
        return payDepositMapper.toDto(payDeposit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PayDeposit resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId()); 
        if(payDepositRepository.findByOrderId(resources.getOrderId()) != null){
            throw new EntityExistException(PayDeposit.class,"order_id",resources.getOrderId());
        }
        payDepositRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PayDeposit resources) {
        PayDeposit payDeposit = payDepositRepository.findById(resources.getId()).orElseGet(PayDeposit::new);
        ValidationUtil.isNull( payDeposit.getId(),"PayDeposit","id",resources.getId());
        PayDeposit payDeposit1 = null;
        payDeposit1 = payDepositRepository.findByOrderId(resources.getOrderId());
        if(payDeposit1 != null && !payDeposit1.getId().equals(payDeposit.getId())){
            throw new EntityExistException(PayDeposit.class,"order_id",resources.getOrderId());
        }
        payDeposit.copy(resources);
        payDepositRepository.save(payDeposit);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            payDepositRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PayDepositDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PayDepositDto payDeposit : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("内部单号", payDeposit.getOrderId());
            map.put("三方单号", payDeposit.getPlatOrderId());
            map.put("UID", payDeposit.getUid());
            map.put("钱包id", payDeposit.getWalletId());
            map.put("租户id", payDeposit.getMerchantId());
            map.put("状态", payDeposit.getStatus());
            map.put("币种", payDeposit.getCurrency());
            map.put("充值金额", payDeposit.getPayAmount());
            map.put("汇率", payDeposit.getExchangeRate());
            map.put("到账金额", payDeposit.getRealAmount());
            map.put("渠道手续费", payDeposit.getChannelFee());
            map.put("渠道编码", payDeposit.getChannelCode());
            map.put("渠道名称", payDeposit.getChannelName());
            map.put("用户名", payDeposit.getUsername());
            map.put("备注", payDeposit.getMark());
            map.put("创建时间", payDeposit.getCreateTime());
            map.put("修改时间", payDeposit.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}