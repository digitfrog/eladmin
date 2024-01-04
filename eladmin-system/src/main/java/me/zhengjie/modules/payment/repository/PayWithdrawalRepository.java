package me.zhengjie.modules.payment.repository;

import me.zhengjie.modules.payment.domain.PayWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author bryan
* @date 2024-01-05
**/
public interface PayWithdrawalRepository extends JpaRepository<PayWithdrawal, Long>, JpaSpecificationExecutor<PayWithdrawal> {
}