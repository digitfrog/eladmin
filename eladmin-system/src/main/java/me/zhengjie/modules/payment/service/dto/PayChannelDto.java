package me.zhengjie.modules.payment.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author bryan
* @date 2024-01-05
**/
@Data
public class PayChannelDto implements Serializable {

    /** ID */
    private Integer id;

    /** 渠道编码 */
    private String code;

    /** 渠道类型 */
    private Integer category;

    /** 渠道名称 */
    private String name;

    /** 平台ID */
    private Integer platId;

    /** 平台名称 */
    private String platName;

    /** 平台自定义名称 */
    private String platNickName;

    /** 支付类型；1:代收 2:代付 */
    private Integer paymentType;

    /** 币种 1-USD 2-VND */
    private Integer currency;

    /** 最小金额 */
    private BigDecimal minCoin;

    /** 最大金额 */
    private BigDecimal maxCoin;

    /** 状态 0-关闭 1-开启 */
    private Integer status;

    /** 请求三方支付地址 */
    private String requestUrl;

    /** 回调地址 */
    private String notifyUrl;

    /** 渠道配置参数 */
    private String channelConfig;

    /** 排序 */
    private Integer sort;

    /** LOGO地址 */
    private String logoUrl;

    /** 渠道网站名称 */
    private String channelShowName;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}