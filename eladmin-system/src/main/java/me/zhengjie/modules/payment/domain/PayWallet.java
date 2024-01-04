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
@Table(name="pay_wallet")
public class PayWallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "钱包ID")
    private Long id;

    @Column(name = "`uid`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @Column(name = "`merchant_id`")
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @Column(name = "`category`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "钱包类型")
    private Integer category;

    @Column(name = "`currency`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "币种")
    private Integer currency;

    @Column(name = "`coin`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "余额")
    private BigDecimal coin;

    @Column(name = "`frozen`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal frozen;

    @Column(name = "`version`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "版本号")
    private Long version;

    @Column(name = "`create_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @Column(name = "`update_time`")
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    public void copy(PayWallet source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
