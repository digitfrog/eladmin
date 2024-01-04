package me.zhengjie.modules.payment.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author bryan
* @date 2024-01-05
**/
@Data
public class PayChannelQueryCriteria{

    /** 精确 */
    @Query
    private String name;

    /** 精确 */
    @Query
    private String platName;
}