
-- 1，创建登录的用户角色（包含密码）
-- DROP USER IF EXISTS website;
-- CREATE ROLE website WITH LOGIN NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT NOREPLICATION CONNECTION LIMIT -1 PASSWORD 'website';

-- 2，创建表空间
-- DROP TABLESPACE IF EXISTS website;
-- CREATE TABLESPACE website OWNER website LOCATION '/usr/local/var/postgres';

-- 3，创建数据库
-- DROP DATABASE IF EXISTS website;
-- CREATE DATABASE website WITH OWNER = website ENCODING = 'UTF8' TABLESPACE = website CONNECTION LIMIT = -1;



-- 创建用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    user_id           SERIAL            NOT NULL,
    user_name         VARCHAR(32)       NOT NULL,
    user_password     CHAR(32)          NOT NULL,
    user_role         INT2              NOT NULL, -- 0：默认普通用户；1：系统管理员
    user_status       INT2              NOT NULL, -- 0：正常；1：关闭
    user_seo          BOOL              NOT NULL,
    CONSTRAINT pk_t_user PRIMARY KEY (user_id)
) TABLESPACE website;

ALTER TABLE t_user OWNER TO website;

-- 创建用户表的主键索引
DROP index IF EXISTS i_t_user;
CREATE UNIQUE INDEX i_t_user ON t_user USING btree (user_id) TABLESPACE website;

-- 插入初始化数据
INSERT INTO t_user (user_name, user_password, user_role, user_status, user_seo) VALUES ('admin', '80b0052e8b6e3e6ea65065b83e6e2847', 1, 0, TRUE);


-- 创建日志表
DROP TABLE IF EXISTS t_log;
CREATE TABLE t_log (
    log_id              SERIAL          NOT NULL,
    log_model           VARCHAR(8)      NOT NULL, -- 模块
    log_description     VARCHAR(32)     NOT NULL, -- 描述
    log_user_id         INTEGER         NOT NULL,
    log_user_name       VARCHAR(32)     NOT NULL,
    log_ip              VARCHAR(15)     NOT NULL,
    log_url             VARCHAR(128)    NOT NULL,
    log_method          VARCHAR(128)    NOT NULL,
    log_response_time   INTEGER         NOT NULL,
    log_detail          TEXT            NOT NULL,
    log_create_time     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_t_log PRIMARY KEY (log_id)
) TABLESPACE website;

ALTER TABLE t_log OWNER TO website;

-- 创建日志表的主键索引
DROP index IF EXISTS i_t_log;
CREATE UNIQUE INDEX i_t_log ON t_log USING btree (log_id) TABLESPACE website;


-- 创建SEO表
DROP TABLE IF EXISTS t_seo;
CREATE TABLE t_seo (
    seo_id              SERIAL            NOT NULL,
    seo_title           VARCHAR(32)       NOT NULL,
    seo_keywords        VARCHAR(128)      NOT NULL,
    seo_describe        VARCHAR(128)      NOT NULL,
    seo_icon            VARCHAR(32)       NOT NULL,
    CONSTRAINT pk_t_seo PRIMARY KEY (seo_id)
) TABLESPACE website;

ALTER TABLE t_seo OWNER TO website;

-- 创建SEO表的主键索引
DROP index IF EXISTS i_t_seo;
CREATE UNIQUE INDEX i_t_seo ON t_seo USING btree (seo_id) TABLESPACE website;



-- 创建友情链接表
DROP TABLE IF EXISTS t_link;
CREATE TABLE t_link (
    link_id             SERIAL                  NOT NULL,
    link_name           CHARACTER VARYING(32)   NOT NULL,
    link_url            CHARACTER VARYING(128)  NOT NULL,
    link_logo           CHARACTER VARYING(32)   NULL,
    link_status         SMALLINT                NOT NULL, -- 0：显示；1：不显示
    link_order_by       SMALLINT                NOT NULL,
    CONSTRAINT pk_t_link PRIMARY KEY (link_id)
) TABLESPACE website;

ALTER TABLE t_link OWNER TO website;

-- 创建友情链接表的主键索引
DROP index IF EXISTS i_t_link;
CREATE UNIQUE INDEX i_t_link ON t_link USING btree (link_id) TABLESPACE website;


-- 创建资讯表
DROP TABLE IF EXISTS t_information;
CREATE TABLE t_information (
    information_id                  SERIAL                      NOT NULL,
    information_type                CHARACTER VARYING(32)       NOT NULL, -- 类型（新闻资讯、通知公告、政策法规等）
    information_rule                SMALLINT                    NOT NULL DEFAULT 0, -- 法规类型（0：地方法规；1：国家法规等）
    information_title               CHARACTER VARYING(64)       NOT NULL,
    information_summary             CHARACTER VARYING(256)      NOT NULL,
    information_content             TEXT                        NOT NULL,
    information_order_by            SMALLINT                    NOT NULL, -- 置顶排序
    information_status              SMALLINT                    NOT NULL, -- 0：显示；1：不显示
    information_hit                 INTEGER                     NOT NULL,
    information_attachment_name     CHARACTER VARYING(128)      NOT NULL,
    information_attachment_url      CHARACTER VARYING(32)       NOT NULL,
    information_create_time         TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_t_information PRIMARY KEY (information_id)
) TABLESPACE website;

ALTER TABLE t_information OWNER TO website;

-- 创建资讯表的主键索引
DROP index IF EXISTS i_t_information;
CREATE UNIQUE INDEX i_t_information ON t_information USING btree (information_id) TABLESPACE website;


-- 创建附件表
DROP TABLE IF EXISTS t_attachment;
CREATE TABLE t_attachment (
    attachment_id               SERIAL              NOT NULL,
    attachment_obj_id           BIGINT              NOT NULL,
    attachment_name             VARCHAR(128)        NOT NULL,
    attachment_url              VARCHAR(32)         NOT NULL,
    attachment_create_time      TIMESTAMP           NOT NULL DEFAULT  CURRENT_TIMESTAMP,
    CONSTRAINT pk_t_attachment PRIMARY KEY (attachment_id)
) TABLESPACE website;

ALTER TABLE t_attachment OWNER TO website;

-- 创建附件表的主键索引
DROP INDEX IF EXISTS i_t_information;
CREATE UNIQUE INDEX i_t_attachment ON t_attachment USING btree (attachment_id) TABLESPACE website;


-- 创建反馈表
DROP TABLE IF EXISTS t_feedback;
CREATE TABLE t_feedback (
    feedback_id                  SERIAL                     NOT NULL,
    feedback_name                CHARACTER VARYING(32)      NOT NULL,
    feedback_tel                 CHARACTER VARYING(32)      NULL,
    feedback_content             CHARACTER VARYING(256)     NOT NULL,
    feedback_reply_content       CHARACTER VARYING(256)     NULL,
    feedback_approve_status      SMALLINT                   NOT NULL DEFAULT 0, -- 0：待审核；1：审核通过
    feedback_category            SMALLINT                   NOT NULL, -- 0：停车场反馈信息；1：其他（来自页面表单）
    feedback_create_time         TIMESTAMP                  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_t_feedback PRIMARY KEY (feedback_id)
) TABLESPACE website;

ALTER TABLE t_feedback OWNER TO website;

-- 创建反馈表的主键索引
DROP INDEX IF EXISTS i_t_feedback;
CREATE UNIQUE INDEX i_t_feedback ON t_feedback USING btree (feedback_id) TABLESPACE website;


-- 创建停车场基础表
DROP TABLE IF EXISTS t_park;
CREATE TABLE t_park (
    park_id                  SERIAL            NOT NULL,
    park_name                VARCHAR(32)       NOT NULL, -- 停车场名称
    park_code                VARCHAR(32)       NOT NULL, -- 停车场编码
    park_total               INTEGER           NOT NULL, -- 总车位数
    park_surplus             INTEGER           NOT NULL, -- 剩余车位数
    park_coordinate_x        VARCHAR(128)      NOT NULL, -- 经度
    park_coordinate_y        VARCHAR(128)      NOT NULL, -- 纬度
    park_type                INT2              NOT NULL, -- 停车场类型0地下，1地上
    park_status              INT2              NOT NULL, -- 状态0隐藏，1显示
    park_source              INT2              NOT NULL, -- 来源1外部停车场，2内部停车场
    park_address             VARCHAR(128)      NOT NULL, -- 停车场地址
    park_charge_code         VARCHAR(32)       NULL, -- 停车场收费编码
    park_describe            VARCHAR(128)      NOT NULL, -- 停车场收费说明
    park_operating_company   VARCHAR(128)      NULL, -- 停车场经营公司名称
    park_create_time         TIMESTAMP         NOT NULL, -- 停车场创建时间
    park_tel                 VARCHAR(32)       NOT NULL, -- 停车场联系电话
    park_contact_name        VARCHAR(32)       NOT NULL, -- 停车场联系人
    park_update_time         TIMESTAMP         NOT NULL,
    park_logo                VARCHAR(32)       NOT NULL,
    CONSTRAINT pk_t_park PRIMARY KEY (park_id)
) TABLESPACE website;

ALTER TABLE t_park OWNER TO website;

-- 创建反馈表的主键索引
DROP index IF EXISTS i_t_park;
CREATE UNIQUE INDEX i_t_park ON t_park USING btree (park_id) TABLESPACE website;



-- 创建LOGO表
DROP TABLE IF EXISTS t_logo;
CREATE TABLE t_logo (
    logo_id           SERIAL            NOT NULL,
    logo_title        VARCHAR(32)       NOT NULL,
    logo_url          VARCHAR(128)      NOT NULL,
    logo_logo         VARCHAR(32)       NOT NULL,
    CONSTRAINT pk_t_logo PRIMARY KEY (logo_id)
) TABLESPACE website;

ALTER TABLE t_logo OWNER TO website;

-- 创建LOGO表的主键索引
DROP index IF EXISTS i_t_logo;
CREATE UNIQUE INDEX i_t_logo ON t_logo USING btree (logo_id) TABLESPACE website;


-- 创建版权信息表
DROP TABLE IF EXISTS t_copyright;
CREATE TABLE t_copyright (
    copyright_id          SERIAL            NOT NULL,
    copyright_content     VARCHAR(1024)     NOT NULL,
    CONSTRAINT pk_t_copyright PRIMARY KEY (copyright_id)
) TABLESPACE website;

ALTER TABLE t_copyright OWNER TO website;

-- 创建版权信息表的主键索引
DROP index IF EXISTS i_t_copyright;
CREATE UNIQUE INDEX i_t_copyright ON t_copyright USING btree (copyright_id) TABLESPACE website;


-- 创建菜单分类表
DROP TABLE IF EXISTS t_menu;
CREATE TABLE t_menu
(
    menu_id SERIAL            NOT NULL,
    menu_parentid INTEGER NOT NULL,
    menu_title VARCHAR(32)  NOT NULL,
    menu_url VARCHAR(128) NOT NULL,
    menu_target VARCHAR(32)  NOT NULL,
    menu_describe VARCHAR(128)  NOT NULL,
    menu_status INTEGER NOT NULL,
    menu_createtime   TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP,
    menu_userid INTEGER NOT NULL,
    menu_orderby INTEGER NOT NULL,
    CONSTRAINT pk_t_menu PRIMARY KEY (menu_id)
);

ALTER TABLE t_menu OWNER TO website;


-- 创建菜单表的主键索引
DROP index IF EXISTS i_t_menu_id;
CREATE UNIQUE INDEX i_t_menu_id ON t_menu USING btree (menu_id) TABLESPACE website;




-- 创建字典信息表
DROP TABLE IF EXISTS t_dictionary;
CREATE TABLE t_dictionary
(
    dictionary_id SERIAL            NOT NULL,
    dictionary_key VARCHAR(20) NOT NULL,
    dictionary_value VARCHAR(200) NOT NULL,
    dictionary_describe VARCHAR(128) NOT NULL DEFAULT '',
    dictionary_status INT2 NOT NULL DEFAULT '0',
    CONSTRAINT pk_t_dictionary PRIMARY KEY (dictionary_id)
);

ALTER TABLE t_dictionary OWNER TO website;


-- 创建字典信息表的主键索引
DROP index IF EXISTS i_t_dictionary_id;
CREATE UNIQUE INDEX i_t_dictionary_id ON t_dictionary USING btree (dictionary_id) TABLESPACE website;




insert into t_dictionary
(
dictionary_key
,dictionary_value
,dictionary_describe
,dictionary_status
)values
('menu_target','_blank','在新窗口中打开被链接文档。' , 1),
('menu_target','_self','默认。在相同的框架中打开被链接文档' , 1),
('menu_target','_parent','在父框架集中打开被链接文档' , 1),
('menu_target','_top','	在整个窗口中打开被链接文档。' , 1),
('menu_target','framename','在指定的框架中打开被链接文档' , 1);

insert into t_dictionary
(
dictionary_key
,dictionary_value
,dictionary_describe
,dictionary_status
)values
('menu_category','100000','顶部导航菜单' , 1),
('menu_category','200000','公共资源菜单' , 1);

select * from t_dictionary;



-- -- 创建公共资源分类和停车场关联表
-- DROP TABLE IF EXISTS t_relation;
-- CREATE TABLE t_relation
-- (
--     relation_id               SERIAL            NOT NULL,
--     relation_communal_id      INTEGER           NOT NULL,
--     relation_park_id          INTEGER           NOT NULL,
--     CONSTRAINT pk_t_relation PRIMARY KEY (relation_id)
-- );
--
-- ALTER TABLE t_relation OWNER TO website;
-- -- 创建公共资源分类表的主键索引
-- DROP index IF EXISTS i_t_relation_id;
-- CREATE UNIQUE INDEX i_t_relation_id ON t_relation USING btree (relation_id) TABLESPACE website;



-- 创建公共资源分类表
DROP TABLE IF EXISTS t_communal;
CREATE TABLE t_communal
(
    communal_id SERIAL            NOT NULL,
    communal_menuid INTEGER NOT NULL,
    communal_name VARCHAR(128)  NOT NULL,
    communal_address VARCHAR(128) NOT NULL,
    communal_coordinate_x VARCHAR(128) NOT NULL,
    communal_coordinate_y VARCHAR(128) NOT NULL,
    communal_createtime   TIMESTAMP         NOT NULL DEFAULT  CURRENT_TIMESTAMP,
    communal_userid INTEGER NOT NULL,
    communal_status INTEGER NOT NULL,
    communal_orderby INTEGER NOT NULL,
    CONSTRAINT pk_t_communal PRIMARY KEY (communal_id)
);

ALTER TABLE t_communal OWNER TO website;
-- 创建公共资源分类表的主键索引
DROP index IF EXISTS i_t_communal_id;
CREATE UNIQUE INDEX i_t_communal_id ON t_communal USING btree (communal_id) TABLESPACE website;


-- 创建停车场标签关联表
DROP TABLE t_parkcommunal;
CREATE TABLE t_parkcommunal
(
    parkcommunal_id SERIAL            NOT NULL,
    parkcommunal_parkid INTEGER NOT NULL,
    parkcommunal_communalid INTEGER NOT NULL,
    CONSTRAINT pk_t_parkcommunal PRIMARY KEY (parkcommunal_id)
);

ALTER TABLE t_parkcommunal OWNER TO website;
-- 创建停车场标签关联表的主键索引
-- DROP index i_t_parkcommunal_id;
CREATE UNIQUE INDEX i_t_parkcommunal_id ON t_parkcommunal USING btree (parkcommunal_id) TABLESPACE website;


-- 创建发起投票表
DROP TABLE IF EXISTS t_vote;
CREATE TABLE t_vote (
    vote_id                    SERIAL            NOT NULL,
    vote_title                 VARCHAR(32)       NOT NULL, -- 投票主题
    vote_description           VARCHAR(256)      NOT NULL, -- 投票说明
    vote_type                  INT2              NOT NULL, -- 选择模式（0:单选；1：多选）
    vote_status                INT2              NOT NULL, -- 投票状态 (0:显示；1：隐藏)
    vote_user_counts           INTEGER           NOT NULL, -- 投票人数
    vote_create_time           TIMESTAMP         NOT NULL, -- 发起投票时间
    vote_end_time              TIMESTAMP         NULL, --     投票截止时间
    CONSTRAINT pk_t_vote PRIMARY KEY (vote_id)
) TABLESPACE website;

ALTER TABLE t_vote OWNER TO website;

-- 创建投票表的主键索引
DROP index IF EXISTS i_t_vote;
CREATE UNIQUE INDEX i_t_vote ON t_vote USING btree (vote_id) TABLESPACE website;


-- 创建投票选项表
DROP TABLE IF EXISTS t_item ;
CREATE TABLE t_item (
    item_id                       SERIAL            NOT NULL,
    item_vote_id                  INTEGER           NOT NULL, -- 投票id
    item_name                     VARCHAR(32)       NOT NULL, -- 投票选项主题
    item_vote_user_counts         INTEGER           NOT NULL, -- 投票选项的投票人数
    item_index                    INTEGER           NOT NULL, -- 投票选项的下标
    CONSTRAINT pk_t_item PRIMARY KEY (item_id)
) TABLESPACE website;

ALTER TABLE t_item OWNER TO website;

-- 创建投票表的主键索引
DROP index IF EXISTS i_t_item;
CREATE UNIQUE INDEX i_t_item ON t_item USING btree (item_id) TABLESPACE website;


-- 版权信息
-- 主办方：北京市丰台区管理委员会
-- 运营方：中科高新（北京）科技有限公司
-- Copyright &copy; 京ICP备17003417号 版权所有
