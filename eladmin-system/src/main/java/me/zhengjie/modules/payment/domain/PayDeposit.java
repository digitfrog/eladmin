package me.zhengjie.modules.payment.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @description /
* @author bryan
* @date 2024-01-05
**/
@Entity
@Data
@Table(name="pay_deposit")
public class PayDeposit implements Serializable {

    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value = "订单号")
    private Long id;

    @Column(name = "`plat_order_id`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "三方单号")
    private String platOrderId;

    @Column(name = "`uid`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "UID")
    private Long uid;

    @Column(name = "`wallet_id`")
    @ApiModelProperty(value = "钱包id")
    private Long walletId;

    @Column(name = "`merchant_id`")
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    @Column(name = "`status`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "状态")
    private Integer status;

    @Column(name = "`currency`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "币种")
    private Integer currency;

    @Column(name = "`pay_amount`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "充值金额")
    private BigDecimal payAmount;

    @Column(name = "`exchange_rate`")
    @ApiModelProperty(value = "汇率")
    private BigDecimal exchangeRate;

    @Column(name = "`real_amount`")
    @ApiModelProperty(value = "到账金额")
    private BigDecimal realAmount;

    @Column(name = "`channel_fee`")
    @ApiModelProperty(value = "渠道手续费")
    private BigDecimal channelFee;

    @Column(name = "`channel_code`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @Column(name = "`channel_name`")
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    @Column(name = "`username`")
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(name = "`mark`")
    @ApiModelProperty(value = "备注")
    private String mark;

    @Column(name = "`create_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "`update_time`")
    @UpdateTimestamp
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;

    public void copy(PayDeposit source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
