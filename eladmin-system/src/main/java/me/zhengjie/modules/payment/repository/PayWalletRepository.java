package me.zhengjie.modules.payment.repository;

import me.zhengjie.modules.payment.domain.PayWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author bryan
* @date 2024-01-05
**/
public interface PayWalletRepository extends JpaRepository<PayWallet, Long>, JpaSpecificationExecutor<PayWallet> {
}