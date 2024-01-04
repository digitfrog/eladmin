package me.zhengjie.modules.payment.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.payment.domain.PayWallet;
import me.zhengjie.modules.payment.service.dto.PayWalletDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @website https://eladmin.vip
* @author bryan
* @date 2024-01-05
**/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayWalletMapper extends BaseMapper<PayWalletDto, PayWallet> {

}