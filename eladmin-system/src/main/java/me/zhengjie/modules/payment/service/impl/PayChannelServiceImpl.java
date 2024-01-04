package me.zhengjie.modules.payment.service.impl;

import me.zhengjie.modules.payment.domain.PayChannel;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.payment.repository.PayChannelRepository;
import me.zhengjie.modules.payment.service.PayChannelService;
import me.zhengjie.modules.payment.service.dto.PayChannelDto;
import me.zhengjie.modules.payment.service.dto.PayChannelQueryCriteria;
import me.zhengjie.modules.payment.service.mapstruct.PayChannelMapper;
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
public class PayChannelServiceImpl implements PayChannelService {

    private final PayChannelRepository payChannelRepository;
    private final PayChannelMapper payChannelMapper;

    @Override
    public PageResult<PayChannelDto> queryAll(PayChannelQueryCriteria criteria, Pageable pageable){
        Page<PayChannel> page = payChannelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(payChannelMapper::toDto));
    }

    @Override
    public List<PayChannelDto> queryAll(PayChannelQueryCriteria criteria){
        return payChannelMapper.toDto(payChannelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PayChannelDto findById(Integer id) {
        PayChannel payChannel = payChannelRepository.findById(id).orElseGet(PayChannel::new);
        ValidationUtil.isNull(payChannel.getId(),"PayChannel","id",id);
        return payChannelMapper.toDto(payChannel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PayChannel resources) {
        if(payChannelRepository.findByCode(resources.getCode()) != null){
            throw new EntityExistException(PayChannel.class,"code",resources.getCode());
        }
        payChannelRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PayChannel resources) {
        PayChannel payChannel = payChannelRepository.findById(resources.getId()).orElseGet(PayChannel::new);
        ValidationUtil.isNull( payChannel.getId(),"PayChannel","id",resources.getId());
        PayChannel payChannel1 = null;
        payChannel1 = payChannelRepository.findByCode(resources.getCode());
        if(payChannel1 != null && !payChannel1.getId().equals(payChannel.getId())){
            throw new EntityExistException(PayChannel.class,"code",resources.getCode());
        }
        payChannel.copy(resources);
        payChannelRepository.save(payChannel);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            payChannelRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PayChannelDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PayChannelDto payChannel : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("渠道编码", payChannel.getCode());
            map.put("渠道类型", payChannel.getCategory());
            map.put("渠道名称", payChannel.getName());
            map.put("平台ID", payChannel.getPlatId());
            map.put("平台名称", payChannel.getPlatName());
            map.put("平台自定义名称", payChannel.getPlatNickName());
            map.put("支付类型；1:代收 2:代付", payChannel.getPaymentType());
            map.put("币种 1-USD 2-VND", payChannel.getCurrency());
            map.put("最小金额", payChannel.getMinCoin());
            map.put("最大金额", payChannel.getMaxCoin());
            map.put("状态 0-关闭 1-开启", payChannel.getStatus());
            map.put("请求三方支付地址", payChannel.getRequestUrl());
            map.put("回调地址", payChannel.getNotifyUrl());
            map.put("渠道配置参数", payChannel.getChannelConfig());
            map.put("排序", payChannel.getSort());
            map.put("LOGO地址", payChannel.getLogoUrl());
            map.put("渠道网站名称", payChannel.getChannelShowName());
            map.put("备注", payChannel.getRemark());
            map.put("创建时间", payChannel.getCreatedAt());
            map.put("修改时间", payChannel.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}