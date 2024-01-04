package me.zhengjie.modules.payment.service.impl;

import me.zhengjie.modules.payment.domain.PayWithdrawal;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.payment.repository.PayWithdrawalRepository;
import me.zhengjie.modules.payment.service.PayWithdrawalService;
import me.zhengjie.modules.payment.service.dto.PayWithdrawalDto;
import me.zhengjie.modules.payment.service.dto.PayWithdrawalQueryCriteria;
import me.zhengjie.modules.payment.service.mapstruct.PayWithdrawalMapper;
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
* @description 服务实现
* @author bryan
* @date 2024-01-05
**/
@Service
@RequiredArgsConstructor
public class PayWithdrawalServiceImpl implements PayWithdrawalService {

    private final PayWithdrawalRepository payWithdrawalRepository;
    private final PayWithdrawalMapper payWithdrawalMapper;

    @Override
    public PageResult<PayWithdrawalDto> queryAll(PayWithdrawalQueryCriteria criteria, Pageable pageable){
        Page<PayWithdrawal> page = payWithdrawalRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(payWithdrawalMapper::toDto));
    }

    @Override
    public List<PayWithdrawalDto> queryAll(PayWithdrawalQueryCriteria criteria){
        return payWithdrawalMapper.toDto(payWithdrawalRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PayWithdrawalDto findById(Long id) {
        PayWithdrawal payWithdrawal = payWithdrawalRepository.findById(id).orElseGet(PayWithdrawal::new);
        ValidationUtil.isNull(payWithdrawal.getId(),"PayWithdrawal","id",id);
        return payWithdrawalMapper.toDto(payWithdrawal);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PayWithdrawal resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId()); 
        payWithdrawalRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PayWithdrawal resources) {
        PayWithdrawal payWithdrawal = payWithdrawalRepository.findById(resources.getId()).orElseGet(PayWithdrawal::new);
        ValidationUtil.isNull( payWithdrawal.getId(),"PayWithdrawal","id",resources.getId());
        payWithdrawal.copy(resources);
        payWithdrawalRepository.save(payWithdrawal);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            payWithdrawalRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PayWithdrawalDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PayWithdrawalDto payWithdrawal : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("三方订单号", payWithdrawal.getPlatOrderId());
            map.put("用户ID", payWithdrawal.getUid());
            map.put("用户名", payWithdrawal.getUsername());
            map.put("商户ID", payWithdrawal.getMerchantId());
            map.put("钱包ID", payWithdrawal.getWalletId());
            map.put("币种", payWithdrawal.getCurrency());
            map.put("提款金额", payWithdrawal.getWithdrawalAmount());
            map.put("汇率", payWithdrawal.getExchangeRate());
            map.put("到账金额", payWithdrawal.getRealAmount());
            map.put("状态", payWithdrawal.getStatus());
            map.put("渠道手续费", payWithdrawal.getChannelFee());
            map.put("渠道编码", payWithdrawal.getChannelCode());
            map.put("渠道名称", payWithdrawal.getChannelName());
            map.put("审核备注", payWithdrawal.getMark());
            map.put("审核类型", payWithdrawal.getAuditType());
            map.put("操作人", payWithdrawal.getAuditUsername());
            map.put("创建时间", payWithdrawal.getCreateTime());
            map.put("更新时间", payWithdrawal.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}