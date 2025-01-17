package me.zhengjie.modules.payment.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
* @website https://eladmin.vip
* @description /
* @author bryan
* @date 2024-01-05
**/
@Data
public class PayDepositDto implements Serializable {

    /** 订单号 */
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 三方单号 */
    private String platOrderId;

    /** UID */
    private Long uid;

    /** 钱包id */
    private Long walletId;

    /** 商户id */
    private Long merchantId;

    /** 状态 */
    private Integer status;

    /** 币种 */
    private Integer currency;

    /** 充值金额 */
    private BigDecimal payAmount;

    /** 汇率 */
    private BigDecimal exchangeRate;

    /** 到账金额 */
    private BigDecimal realAmount;

    /** 渠道手续费 */
    private BigDecimal channelFee;

    /** 渠道编码 */
    private String channelCode;

    /** 渠道名称 */
    private String channelName;

    /** 用户名 */
    private String username;

    /** 备注 */
    private String mark;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改时间 */
    private Timestamp updateTime;
}