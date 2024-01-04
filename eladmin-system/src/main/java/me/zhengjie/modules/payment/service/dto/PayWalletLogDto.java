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
public class PayWalletLogDto implements Serializable {

    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** UID */
    private Long uid;

    /** 商户id */
    private Long merchantId;

    /** 钱包id */
    private Long walletId;

    /** 订单类型 */
    private Integer category;

    /** 收支类型 */
    private Integer outIn;

    /** 币种 */
    private Integer currency;

    /** 金额 */
    private BigDecimal coin;

    /** 前余额 */
    private BigDecimal coinBefore;

    /** 后余额 */
    private BigDecimal coinAfter;

    /** 渠道编码 */
    private String channelCode;

    /** 用户名 */
    private String username;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 内部订单号 */
    private Long orderId;
}