package me.zhengjie.modules.payment.service;

import me.zhengjie.modules.payment.domain.PayWithdrawal;
import me.zhengjie.modules.payment.service.dto.PayWithdrawalDto;
import me.zhengjie.modules.payment.service.dto.PayWithdrawalQueryCriteria;
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
public interface PayWithdrawalService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    PageResult<PayWithdrawalDto> queryAll(PayWithdrawalQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<PayWithdrawalDto>
    */
    List<PayWithdrawalDto> queryAll(PayWithdrawalQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return PayWithdrawalDto
     */
    PayWithdrawalDto findById(Long id);

    /**
    * 创建
    * @param resources /
    */
    void create(PayWithdrawal resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(PayWithdrawal resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<PayWithdrawalDto> all, HttpServletResponse response) throws IOException;
}