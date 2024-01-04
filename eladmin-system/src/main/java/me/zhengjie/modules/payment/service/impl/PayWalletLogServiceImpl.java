package me.zhengjie.modules.payment.service.impl;

import me.zhengjie.modules.payment.domain.PayWalletLog;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.payment.repository.PayWalletLogRepository;
import me.zhengjie.modules.payment.service.PayWalletLogService;
import me.zhengjie.modules.payment.service.dto.PayWalletLogDto;
import me.zhengjie.modules.payment.service.dto.PayWalletLogQueryCriteria;
import me.zhengjie.modules.payment.service.mapstruct.PayWalletLogMapper;
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
public class PayWalletLogServiceImpl implements PayWalletLogService {

    private final PayWalletLogRepository payWalletLogRepository;
    private final PayWalletLogMapper payWalletLogMapper;

    @Override
    public PageResult<PayWalletLogDto> queryAll(PayWalletLogQueryCriteria criteria, Pageable pageable){
        Page<PayWalletLog> page = payWalletLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(payWalletLogMapper::toDto));
    }

    @Override
    public List<PayWalletLogDto> queryAll(PayWalletLogQueryCriteria criteria){
        return payWalletLogMapper.toDto(payWalletLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PayWalletLogDto findById(Long id) {
        PayWalletLog payWalletLog = payWalletLogRepository.findById(id).orElseGet(PayWalletLog::new);
        ValidationUtil.isNull(payWalletLog.getId(),"PayWalletLog","id",id);
        return payWalletLogMapper.toDto(payWalletLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PayWalletLog resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId()); 
        payWalletLogRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PayWalletLog resources) {
        PayWalletLog payWalletLog = payWalletLogRepository.findById(resources.getId()).orElseGet(PayWalletLog::new);
        ValidationUtil.isNull( payWalletLog.getId(),"PayWalletLog","id",resources.getId());
        payWalletLog.copy(resources);
        payWalletLogRepository.save(payWalletLog);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            payWalletLogRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PayWalletLogDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PayWalletLogDto payWalletLog : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("UID", payWalletLog.getUid());
            map.put("商户id", payWalletLog.getMerchantId());
            map.put("钱包id", payWalletLog.getWalletId());
            map.put("订单类型", payWalletLog.getCategory());
            map.put("收支类型", payWalletLog.getOutIn());
            map.put("币种", payWalletLog.getCurrency());
            map.put("金额", payWalletLog.getCoin());
            map.put("前余额", payWalletLog.getCoinBefore());
            map.put("后余额", payWalletLog.getCoinAfter());
            map.put("渠道编码", payWalletLog.getChannelCode());
            map.put("用户名", payWalletLog.getUsername());
            map.put("创建时间", payWalletLog.getCreateTime());
            map.put("更新时间", payWalletLog.getUpdateTime());
            map.put("内部订单号", payWalletLog.getOrderId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}