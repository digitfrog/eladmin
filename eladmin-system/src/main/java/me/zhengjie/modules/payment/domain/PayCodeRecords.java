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
@Table(name="pay_code_records")
public class PayCodeRecords implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "记录ID")
    private Long id;

    @Column(name = "`uid`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "UID")
    private Long uid;

    @Column(name = "`username`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(name = "`coin`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "金额")
    private BigDecimal coin;

    @Column(name = "`code_require`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "需求打码量")
    private BigDecimal codeRequire;

    @Column(name = "`flow_claim`")
    @ApiModelProperty(value = "需求打码倍数")
    private BigDecimal flowClaim;

    @Column(name = "`real_code`")
    @ApiModelProperty(value = "实际打码量")
    private BigDecimal realCode;

    @Column(name = "`refer_category`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "关联订单类型")
    private Integer referCategory;

    @Column(name = "`refer_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "关联ID")
    private Long referId;

    @Column(name = "`refer_withdrawal_id`")
    @ApiModelProperty(value = "关联提款ID")
    private Long referWithdrawalId;

    @Column(name = "`remarks`")
    @ApiModelProperty(value = "备注")
    private String remarks;

    @Column(name = "`status`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "状态")
    private Integer status;

    @Column(name = "`version`")
    @ApiModelProperty(value = "版本号")
    private Long version;

    @Column(name = "`created_at`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createdAt;

    @Column(name = "`updated_at`")
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间")
    private Timestamp updatedAt;

    public void copy(PayCodeRecords source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
