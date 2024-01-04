package me.zhengjie.modules.payment.service;

import me.zhengjie.modules.payment.domain.PayChannel;
import me.zhengjie.modules.payment.service.dto.PayChannelDto;
import me.zhengjie.modules.payment.service.dto.PayChannelQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;

/**
* @description 服务接口
* @author bryan
* @date 2024-01-05
**/
public interface PayChannelService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    PageResult<PayChannelDto> queryAll(PayChannelQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<PayChannelDto>
    */
    List<PayChannelDto> queryAll(PayChannelQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return PayChannelDto
     */
    PayChannelDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    */
    void create(PayChannel resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(PayChannel resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<PayChannelDto> all, HttpServletResponse response) throws IOException;
}