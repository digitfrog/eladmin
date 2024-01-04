DROP TABLE IF EXISTS `pay_wallet`;
create table pay_wallet
(
    id          bigint auto_increment primary key,
    uid         bigint          default 0                    not null comment '用户id',
    merchant_id bigint          default 0                    not null comment '商户id',
    category    tinyint         default 1                    not null comment '钱包类型:1支付/2订单/3活动/4佣金',
    currency    tinyint         default 1                    not null comment '币种',
    coin        decimal(32, 18) default 0.000000000000000000 not null comment '余额',
    frozen      decimal(32, 18) default 0.000000000000000000 not null comment '冻结金额',
    version     bigint          default 0                    not null comment '版本号',
    create_time datetime     null comment '创建日期',
    update_time datetime     null comment '更新时间',
    constraint uniq_cat_cur unique (uid, category, currency)
)
    comment '钱包' collate = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `pay_wallet_log`;
create table pay_wallet_log
(
    id           bigint                                       not null primary key,
    uid          bigint          default 0                    not null comment 'UID',
    merchant_id  bigint          default 0                    not null comment '商户id',
    wallet_id    bigint          default 0                    not null comment '钱包id',
    category     tinyint         default 0                    not null comment '订单类型:1-存款 2-提款 3-下单 4-结算 5-返现 6-佣金 7-活动 8-调账 9-退款 10-调拨转账 11-小费',
    refer_id     varchar(64)     default ''                   not null comment '关联订单ID',
    out_in       tinyint         default 0                    not null comment '收支类型:0-支出 1-收入',
    currency     tinyint         default 1                    not null comment '币种',
    coin         decimal(32, 18) default 0.000000000000000000 not null comment '金额',
    coin_before  decimal(32, 18) default 0.000000000000000000 not null comment '前余额',
    coin_after   decimal(32, 18) default 0.000000000000000000 not null comment '后余额',
    channel_code varchar(64)     default ''                   not null comment '渠道编码',
    username     varchar(32)     default ''                   not null comment '用户名',
    create_time   datetime                                    null comment '创建时间',
    update_time   datetime                                    null comment '修改时间'
) comment '账变' collate = utf8mb4_unicode_ci;
create index idx_rid
    on pay_wallet_log (refer_id);
create index idx_type_cdate
    on pay_wallet_log (category, create_time);
create index idx_uid_type_status_cdate
    on pay_wallet_log (uid, category, create_time);

DROP TABLE IF EXISTS `pay_deposit`;
create table pay_deposit
(
    id             bigint                                       not null primary key,
    order_id       varchar(64)     default ''                   not null comment '内部订单号',
    plat_order_id  varchar(64)     default ''                   not null comment '三方平台订单号',
    uid            bigint             default 0                 not null comment 'UID',
    wallet_id      bigint             default 0                 not null comment '钱包id',
    merchant_id    bigint             default 0                 not null comment '商户id',
    status         tinyint         default 0                    not null comment '状态: 0-处理中 1-成功 2-失败',
    currency       varchar(64)     default ''                   not null comment '币种',
    pay_amount     decimal(32, 18) default 0.000000000000000000 not null comment '充值金额',
    exchange_rate  decimal(15, 4)  default 0.0000               not null comment '汇率',
    real_amount    decimal(32, 18) default 0.000000000000000000 not null comment '到账金额',
    channel_fee    decimal(32, 18) default 0.000000000000000000 not null comment '渠道手续费',
    channel_code   varchar(64)     default ''                   not null comment '渠道编码',
    channel_name   varchar(100)    default ''                   null comment '渠道名称',
    username       varchar(32)     default ''                   not null comment '用户名',
    mark           varchar(255)    default ''                   not null comment '备注',
    create_time   datetime                                    null comment '创建时间',
    update_time   datetime                                    null comment '修改时间',
    constraint uniq_oid_index
        unique (order_id)
) comment '充值记录' collate = utf8mb4_unicode_ci;
create index idx_stat_cdate
    on pay_deposit (status, create_time);
create index idx_uid
    on pay_deposit (uid);

DROP TABLE IF EXISTS `pay_withdrawal`;
create table pay_withdrawal
(
    id                bigint                                       not null primary key,
    order_id          varchar(64)     default ''                   not null comment '内部订单号',
    plat_order_id     varchar(64)     default ''                   not null comment '三方平台订单号',
    uid               bigint             default 0                    not null comment 'UID',
    username          varchar(32)     default ''                   not null comment '用户名',
    merchant_id       bigint             default 0                    not null comment '租户id',
    wallet_id         bigint             default 0                    not null comment '钱包id',
    currency          varchar(50)                                  not null comment '币种',
    withdrawal_amount decimal(32, 18) default 0.000000000000000000 not null comment '提款金额',
    exchange_rate     decimal(15, 4)  default 0.0000               not null comment '汇率',
    real_amount       decimal(32, 18) default 0.000000000000000000 not null comment '到账金额',
    status            tinyint         default 0                    not null comment '状态: 0-待审核 1-审核通过待支付 2-已支付待三方确认 3-提款成功 4-提款失败 5-审核拒绝',
    channel_fee       decimal(32, 18) default 0.000000000000000000 not null comment '渠道手续费',
    channel_code      varchar(64)     default ''                   not null comment '渠道编码',
    channel_name      varchar(100)    default ''                   not null comment '渠道名称',
    mark              varchar(255)    default ''                   null comment '审核备注',
    audit_type        tinyint         default 0                    not null comment '审核类型 0人工，1自动',
    audit_username    varchar(255)     default ''                  not null comment '操作人',
    create_time        bigint                                       not null,
    update_time        bigint                                       not null,
    constraint uniq_oid_index
        unique (order_id)
) comment '提款记录' collate = utf8mb4_unicode_ci;
create index idx_stat_cdate
    on pay_withdrawal (status, create_time);
create index idx_uid
    on pay_withdrawal (uid);


create table pay_channel
(
    id                int auto_increment comment 'ID' primary key,
    code              varchar(64)  default '' not null comment '渠道编码',
    category          tinyint      default 0  not null comment '渠道类型：1-TRC,2-ERC,3-BANK',
    name              varchar(100) default '' not null comment '渠道名称',
    plat_id           int          default 0  not null comment '平台ID',
    plat_name         varchar(100) default '' not null comment '平台名称',
    plat_nick_name    varchar(100) default '' null comment '平台自定义名称',
    payment_type      tinyint      default 0  not null comment '支付类型；1:代收 2:代付',
    currency          tinyint      default 0 not null comment '币种',
    min_coin          decimal(32, 18)          default 0  not null comment '最小金额',
    max_coin          decimal(32, 18)          default 0  not null comment '最大金额',
    status            tinyint      default 0  not null comment '状态 0-关闭 1-开启',
    request_url       varchar(255) default '' not null comment '请求三方支付地址',
    notify_url        varchar(255) default '' not null comment '回调地址',
    channel_config    varchar(512) default '' null comment '渠道配置参数',
    sort              int          default 30 null comment '排序',
    logo_url          varchar(255) default '' null comment 'LOGO地址',
    channel_show_name varchar(255) default '' null comment '渠道网站名称',
    remark            varchar(255) default '' null comment '备注',
    created_at        datetime                 null comment '创建时间',
    updated_at        datetime                 null comment '修改时间',
    constraint uniq_code_index unique (code)
) comment '支付渠道配置' collate = utf8mb4_unicode_ci;


INSERT INTO eladmin.sys_dict (dict_id, name, description) VALUES (6, 'deposit_status', '充值状态');
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES ( 6, '处理中', '0', 1);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES ( 6, '成功', '1', 2);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES ( 6, '失败', '2', 3);

INSERT INTO eladmin.sys_dict (dict_id, name, description) VALUES (7, 'currency', '币种');
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (7, '美元', 'USD', 2);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (7, '美元', 'USD', 2);

INSERT INTO eladmin.sys_dict (dict_id, name, description) VALUES (8, 'wallet_category', '钱包类型');
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (8, '支付', '1', 1);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (8, '游戏', '2', 2);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (8, '活动', '3', 3);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (8, '佣金', '4', 4);

INSERT INTO eladmin.sys_dict (dict_id, name, description) VALUES (9, 'refer_category', '关联订单类型');
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '存款', '1', 1);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '提款', '2', 2);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '下单', '3', 3);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '结算', '4', 4);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '返现', '5', 5);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '佣金', '6', 6);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '活动', '7', 7);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '调账', '8', 8);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '退款', '9', 9);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '调拨转账', '10', 10);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (9, '小费', '11', 11);

INSERT INTO eladmin.sys_dict (dict_id, name, description) VALUES (10, 'out_in', '收支类型');
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (10, '支出', '1',1);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (10, '收入', '2',2);

INSERT INTO eladmin.sys_dict (dict_id, name, description) VALUES (11, 'withdrawal_status', '提现状态');
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (11, '待审核', '1',1);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (11, '审核通过待支付', '2',2);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (11, '已支付待三方确认', '3',3);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (11, '提款成功', '4',4);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (11, '提款失败', '5',5);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (11, '审核拒绝', '6',6);

INSERT INTO eladmin.sys_dict (dict_id, name, description) VALUES (12, 'audit_type', '审核类型');
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (12, '人工', '1',1);
INSERT INTO eladmin.sys_dict_detail (dict_id, label, value, dict_sort) VALUES (12, '自动', '2',2);



