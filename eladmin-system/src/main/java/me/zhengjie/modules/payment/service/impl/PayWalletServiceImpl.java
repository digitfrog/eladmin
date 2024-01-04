package me.zhengjie.modules.payment.service.impl;

import me.zhengjie.modules.payment.domain.PayWallet;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.payment.repository.PayWalletRepository;
import me.zhengjie.modules.payment.service.PayWalletService;
import me.zhengjie.modules.payment.service.dto.PayWalletDto;
import me.zhengjie.modules.payment.service.dto.PayWalletQueryCriteria;
import me.zhengjie.modules.payment.service.mapstruct.PayWalletMapper;
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
public class PayWalletServiceImpl implements PayWalletService {

    private final PayWalletRepository payWalletRepository;
    private final PayWalletMapper payWalletMapper;

    @Override
    public PageResult<PayWalletDto> queryAll(PayWalletQueryCriteria criteria, Pageable pageable){
        Page<PayWallet> page = payWalletRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(payWalletMapper::toDto));
    }

    @Override
    public List<PayWalletDto> queryAll(PayWalletQueryCriteria criteria){
        return payWalletMapper.toDto(payWalletRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PayWalletDto findById(Long id) {
        PayWallet payWallet = payWalletRepository.findById(id).orElseGet(PayWallet::new);
        ValidationUtil.isNull(payWallet.getId(),"PayWallet","id",id);
        return payWalletMapper.toDto(payWallet);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PayWallet resources) {
        payWalletRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PayWallet resources) {
        PayWallet payWallet = payWalletRepository.findById(resources.getId()).orElseGet(PayWallet::new);
        ValidationUtil.isNull( payWallet.getId(),"PayWallet","id",resources.getId());
        payWallet.copy(resources);
        payWalletRepository.save(payWallet);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            payWalletRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PayWalletDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PayWalletDto payWallet : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户ID", payWallet.getUid());
            map.put("商户ID", payWallet.getMerchantId());
            map.put("钱包类型", payWallet.getCategory());
            map.put("币种", payWallet.getCurrency());
            map.put("余额", payWallet.getCoin());
            map.put("冻结金额", payWallet.getFrozen());
            map.put("版本号", payWallet.getVersion());
            map.put("创建日期", payWallet.getCreateTime());
            map.put("更新时间", payWallet.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}