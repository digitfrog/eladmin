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
* @author mk
* @date 2024-01-02
**/
@Data
public class PayDepositDto implements Serializable {

    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 内部单号 */
    private String orderId;

    /** 三方单号 */
    private String platOrderId;

    /** UID */
    private Long uid;

    /** 钱包id */
    private Long walletId;

    /** 租户id */
    private Long merchantId;

    /** 状态 */
    private Integer status;

    /** 币种 */
    private String currency;

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