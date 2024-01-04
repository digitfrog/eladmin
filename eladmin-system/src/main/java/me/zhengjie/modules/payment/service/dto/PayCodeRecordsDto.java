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
public class PayCodeRecordsDto implements Serializable {

    /** 记录ID */
    private Long id;

    /** UID */
    private Long uid;

    /** 用户名 */
    private String username;

    /** 金额 */
    private BigDecimal coin;

    /** 需求打码量 */
    private BigDecimal codeRequire;

    /** 需求打码倍数 */
    private BigDecimal flowClaim;

    /** 实际打码量 */
    private BigDecimal realCode;

    /** 关联订单类型 */
    private Integer referCategory;

    /** 关联ID */
    private Long referId;

    /** 关联提款ID */
    private Long referWithdrawalId;

    /** 备注 */
    private String remarks;

    /** 状态 */
    private Integer status;

    /** 版本号 */
    private Long version;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 更新时间 */
    private Timestamp updatedAt;
}