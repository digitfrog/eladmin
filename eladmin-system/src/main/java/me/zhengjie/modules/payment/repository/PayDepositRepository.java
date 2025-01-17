package me.zhengjie.modules.payment.repository;

import me.zhengjie.modules.payment.domain.PayDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author bryan
* @date 2024-01-05
**/
public interface PayDepositRepository extends JpaRepository<PayDeposit, Long>, JpaSpecificationExecutor<PayDeposit> {
}