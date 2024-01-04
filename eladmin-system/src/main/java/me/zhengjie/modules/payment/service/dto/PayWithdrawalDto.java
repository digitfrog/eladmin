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
public class PayWithdrawalDto implements Serializable {

    /** 订单号 */
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 三方订单号 */
    private String platOrderId;

    /** 用户ID */
    private Long uid;

    /** 用户名 */
    private String username;

    /** 商户ID */
    private Long merchantId;

    /** 钱包ID */
    private Long walletId;

    /** 币种 */
    private Integer currency;

    /** 提款金额 */
    private BigDecimal withdrawalAmount;

    /** 汇率 */
    private BigDecimal exchangeRate;

    /** 到账金额 */
    private BigDecimal realAmount;

    /** 状态 */
    private Integer status;

    /** 渠道手续费 */
    private BigDecimal channelFee;

    /** 渠道编码 */
    private String channelCode;

    /** 渠道名称 */
    private String channelName;

    /** 审核备注 */
    private String mark;

    /** 审核类型 */
    private Integer auditType;

    /** 操作人 */
    private String auditUsername;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}