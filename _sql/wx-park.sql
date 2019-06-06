/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/18 17:38:13                          */
/*==============================================================*/


drop table if exists wx_car;

drop table if exists wx_log;

drop table if exists wx_pay;

drop table if exists wx_user;

/*==============================================================*/
/* Table: wx_car                                                */
/*==============================================================*/
create table wx_car
(
   car_id               int(4) not null auto_increment,
   user_id              int(4) not null,
   car_no               varchar(7) not null,
   car_type             smallint not null default 0 comment '车类型：1-小型车；2-大型车',
   car_create_time      timestamp not null default CURRENT_TIMESTAMP,
   car_bind_status      smallint not null default 0 comment '0-未绑定；1-已绑定;',
   primary key (car_id)
);

/*==============================================================*/
/* Table: wx_log                                                */
/*==============================================================*/
create table wx_log
(
   log_id               int(4) not null auto_increment,
   log_user_id          int not null,
   log_biz              varchar(16) not null comment '绑定车牌号-car；.....',
   log_content          varchar(256) not null,
   log_create_time      timestamp not null,
   primary key (log_id)
);

/*==============================================================*/
/* Table: wx_pay                                                */
/*==============================================================*/
create table wx_pay
(
   pay_id               int(4) not null auto_increment,
   user_id              int(4) not null,
   pay_name             smallint not null default 0 comment '0-停车（默认）；1-补缴',
   pay_trade_no         varchar(64) not null,
   pay_money            float(9,2) not null,
   pay_time             timestamp not null default CURRENT_TIMESTAMP,
   pay_status           smallint not null default 0 comment '0-未支付或支付失败（默认）；1支付成功',
   primary key (pay_id)
);

/*==============================================================*/
/* Table: wx_user                                               */
/*==============================================================*/
create table wx_user
(
   user_id              int(4) not null auto_increment,
   user_open_id         varchar(32) not null,
   user_nickname        varchar(32) not null,
   user_header_img      varchar(256) not null,
   user_subscribe       smallint not null default 0 comment '用户是否关注公众号：0-未关注；1-已关注',
   user_create_time     timestamp not null default CURRENT_TIMESTAMP,
   user_create_ip       varchar(16),
   user_update_time     timestamp default CURRENT_TIMESTAMP comment '修改昵称和头像的时间',
   primary key (user_id)
);

alter table wx_car add constraint FK_User_bind_car_no foreign key (user_id)
      references wx_user (user_id) on delete restrict on update restrict;

alter table wx_pay add constraint FK_User_pay_car foreign key (user_id)
      references wx_user (user_id) on delete restrict on update restrict;

