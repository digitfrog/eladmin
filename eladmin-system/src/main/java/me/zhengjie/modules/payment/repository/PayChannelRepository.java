package me.zhengjie.modules.payment.repository;

import me.zhengjie.modules.payment.domain.PayChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author bryan
* @date 2024-01-05
**/
public interface PayChannelRepository extends JpaRepository<PayChannel, Integer>, JpaSpecificationExecutor<PayChannel> {
    /**
    * 根据 Code 查询
    * @param code /
    * @return /
    */
    PayChannel findByCode(String code);
}