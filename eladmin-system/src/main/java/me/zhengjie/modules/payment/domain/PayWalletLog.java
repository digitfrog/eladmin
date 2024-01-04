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
@Table(name="pay_wallet_log")
public class PayWalletLog implements Serializable {

    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value = "id")
    private Long id;

    @Column(name = "`uid`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "UID")
    private Long uid;

    @Column(name = "`merchant_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    @Column(name = "`wallet_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "钱包id")
    private Long walletId;

    @Column(name = "`category`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "订单类型")
    private Integer category;

    @Column(name = "`out_in`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "收支类型")
    private Integer outIn;

    @Column(name = "`currency`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "币种")
    private Integer currency;

    @Column(name = "`coin`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "金额")
    private BigDecimal coin;

    @Column(name = "`coin_before`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "前余额")
    private BigDecimal coinBefore;

    @Column(name = "`coin_after`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "后余额")
    private BigDecimal coinAfter;

    @Column(name = "`channel_code`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @Column(name = "`username`")
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(name = "`create_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "`update_time`")
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Column(name = "`order_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "内部订单号")
    private Long orderId;

    public void copy(PayWalletLog source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
