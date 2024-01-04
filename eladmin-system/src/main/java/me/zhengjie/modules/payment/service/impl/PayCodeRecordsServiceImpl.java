package me.zhengjie.modules.payment.service.impl;

import me.zhengjie.modules.payment.domain.PayCodeRecords;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.payment.repository.PayCodeRecordsRepository;
import me.zhengjie.modules.payment.service.PayCodeRecordsService;
import me.zhengjie.modules.payment.service.dto.PayCodeRecordsDto;
import me.zhengjie.modules.payment.service.dto.PayCodeRecordsQueryCriteria;
import me.zhengjie.modules.payment.service.mapstruct.PayCodeRecordsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class PayCodeRecordsServiceImpl implements PayCodeRecordsService {

    private final PayCodeRecordsRepository payCodeRecordsRepository;
    private final PayCodeRecordsMapper payCodeRecordsMapper;

    @Override
    public PageResult<PayCodeRecordsDto> queryAll(PayCodeRecordsQueryCriteria criteria, Pageable pageable){
        Page<PayCodeRecords> page = payCodeRecordsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(payCodeRecordsMapper::toDto));
    }

    @Override
    public List<PayCodeRecordsDto> queryAll(PayCodeRecordsQueryCriteria criteria){
        return payCodeRecordsMapper.toDto(payCodeRecordsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PayCodeRecordsDto findById(Long id) {
        PayCodeRecords payCodeRecords = payCodeRecordsRepository.findById(id).orElseGet(PayCodeRecords::new);
        ValidationUtil.isNull(payCodeRecords.getId(),"PayCodeRecords","id",id);
        return payCodeRecordsMapper.toDto(payCodeRecords);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PayCodeRecords resources) {
        payCodeRecordsRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PayCodeRecords resources) {
        PayCodeRecords payCodeRecords = payCodeRecordsRepository.findById(resources.getId()).orElseGet(PayCodeRecords::new);
        ValidationUtil.isNull( payCodeRecords.getId(),"PayCodeRecords","id",resources.getId());
        payCodeRecords.copy(resources);
        payCodeRecordsRepository.save(payCodeRecords);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            payCodeRecordsRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PayCodeRecordsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PayCodeRecordsDto payCodeRecords : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("UID", payCodeRecords.getUid());
            map.put("用户名", payCodeRecords.getUsername());
            map.put("金额", payCodeRecords.getCoin());
            map.put("需求打码量", payCodeRecords.getCodeRequire());
            map.put("需求打码倍数", payCodeRecords.getFlowClaim());
            map.put("实际打码量", payCodeRecords.getRealCode());
            map.put("关联订单类型", payCodeRecords.getReferCategory());
            map.put("关联ID", payCodeRecords.getReferId());
            map.put("关联提款ID", payCodeRecords.getReferWithdrawalId());
            map.put("备注", payCodeRecords.getRemarks());
            map.put("状态", payCodeRecords.getStatus());
            map.put("版本号", payCodeRecords.getVersion());
            map.put("创建时间", payCodeRecords.getCreatedAt());
            map.put("更新时间", payCodeRecords.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}