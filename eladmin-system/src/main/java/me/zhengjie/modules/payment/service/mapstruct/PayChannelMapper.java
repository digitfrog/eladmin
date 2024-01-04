package me.zhengjie.modules.payment.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.payment.domain.PayChannel;
import me.zhengjie.modules.payment.service.dto.PayChannelDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author bryan
* @date 2024-01-05
**/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayChannelMapper extends BaseMapper<PayChannelDto, PayChannel> {

}