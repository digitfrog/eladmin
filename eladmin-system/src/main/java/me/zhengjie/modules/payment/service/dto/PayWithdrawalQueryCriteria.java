package me.zhengjie.modules.payment.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author bryan
* @date 2024-01-05
**/
@Data
public class PayWithdrawalQueryCriteria{

    /** 精确 */
    @Query
    private Long id;

    /** 精确 */
    @Query
    private String platOrderId;

    /** 精确 */
    @Query
    private Long uid;

    /** 精确 */
    @Query
    private Integer status;

    /** 精确 */
    @Query
    private String channelCode;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> updateTime;
}