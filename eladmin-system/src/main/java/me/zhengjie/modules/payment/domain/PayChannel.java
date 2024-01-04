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
* @author bryan
* @date 2024-01-05
**/
@Entity
@Data
@Table(name="pay_channel")
public class PayChannel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "`code`",unique = true,nullable = false)
    @NotBlank
    @ApiModelProperty(value = "渠道编码")
    private String code;

    @Column(name = "`category`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "渠道类型")
    private Integer category;

    @Column(name = "`name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "渠道名称")
    private String name;

    @Column(name = "`plat_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "平台ID")
    private Integer platId;

    @Column(name = "`plat_name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "平台名称")
    private String platName;

    @Column(name = "`plat_nick_name`")
    @ApiModelProperty(value = "平台自定义名称")
    private String platNickName;

    @Column(name = "`payment_type`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "支付类型；1:代收 2:代付")
    private Integer paymentType;

    @Column(name = "`currency`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "币种 1-USD 2-VND")
    private Integer currency;

    @Column(name = "`min_coin`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "最小金额")
    private BigDecimal minCoin;

    @Column(name = "`max_coin`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "最大金额")
    private BigDecimal maxCoin;

    @Column(name = "`status`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "状态 0-关闭 1-开启")
    private Integer status;

    @Column(name = "`request_url`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "请求三方支付地址")
    private String requestUrl;

    @Column(name = "`notify_url`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "回调地址")
    private String notifyUrl;

    @Column(name = "`channel_config`")
    @ApiModelProperty(value = "渠道配置参数")
    private String channelConfig;

    @Column(name = "`sort`")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @Column(name = "`logo_url`")
    @ApiModelProperty(value = "LOGO地址")
    private String logoUrl;

    @Column(name = "`channel_show_name`")
    @ApiModelProperty(value = "渠道网站名称")
    private String channelShowName;

    @Column(name = "`remark`")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "`created_at`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createdAt;

    @Column(name = "`updated_at`")
    @UpdateTimestamp
    @ApiModelProperty(value = "修改时间")
    private Timestamp updatedAt;

    public void copy(PayChannel source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
