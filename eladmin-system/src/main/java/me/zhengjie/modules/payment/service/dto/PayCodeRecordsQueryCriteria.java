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
public class PayCodeRecordsQueryCriteria{

    /** 精确 */
    @Query
    private Long id;

    /** 精确 */
    @Query
    private Long uid;

    /** 精确 */
    @Query
    private String username;

    /** 精确 */
    @Query
    private Integer referCategory;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createdAt;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> updatedAt;
}