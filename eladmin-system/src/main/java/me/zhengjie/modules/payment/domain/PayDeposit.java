/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
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
* @website https://eladmin.vip
* @description /
* @author mk
* @date 2024-01-02
**/
@Entity
@Data
@Table(name="pay_deposit")
public class PayDeposit implements Serializable {

    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value = "id")
    private Long id;

    @Column(name = "`order_id`",unique = true,nullable = false)
    @NotBlank
    @ApiModelProperty(value = "内部单号")
    private String orderId;

    @Column(name = "`plat_order_id`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "三方单号")
    private String platOrderId;

    @Column(name = "`uid`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "UID")
    private Long uid;

    @Column(name = "`wallet_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "钱包id")
    private Long walletId;

    @Column(name = "`merchant_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "租户id")
    private Long merchantId;

    @Column(name = "`status`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "状态")
    private Integer status;

    @Column(name = "`currency`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "币种")
    private String currency;

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
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;

    public void copy(PayDeposit source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
