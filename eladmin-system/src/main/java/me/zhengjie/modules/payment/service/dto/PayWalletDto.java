package me.zhengjie.modules.payment.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author bryan
* @date 2024-01-05
**/
@Data
public class PayWalletDto implements Serializable {

    /** 钱包ID */
    private Long id;

    /** 用户ID */
    private Long uid;

    /** 商户ID */
    private Long merchantId;

    /** 钱包类型 */
    private Integer category;

    /** 币种 */
    private Integer currency;

    /** 余额 */
    private BigDecimal coin;

    /** 冻结金额 */
    private BigDecimal frozen;

    /** 版本号 */
    private Long version;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}