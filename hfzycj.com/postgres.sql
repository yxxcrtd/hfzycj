
-- 1，创建登录的用户角色（包含密码）
DROP USER IF EXISTS hfzycj_com;
CREATE ROLE hfzycj_com WITH LOGIN NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT NOREPLICATION CONNECTION LIMIT -1 PASSWORD 'hfzycj_com';

-- 2，创建表空间（如果出现：﻿ERROR:  could not set permissions on directory "/home/data/hfzycj_com": Operation not permitted，在DOS中执行：chown postgres /home/data/hfzycj_com）
DROP TABLESPACE IF EXISTS hfzycj_com;
CREATE TABLESPACE hfzycj_com OWNER hfzycj_com LOCATION '/Users/young/upload/postgres/hfzycj_com';

-- 3，创建数据库
DROP DATABASE IF EXISTS hfzycj_com;
CREATE DATABASE hfzycj_com WITH OWNER = hfzycj_com ENCODING = 'UTF8' TABLESPACE = hfzycj_com CONNECTION LIMIT = -1;

-- hibernate_sequence
DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
ALTER SEQUENCE public.hibernate_sequence OWNER TO hfzycj_com;


-- 创建用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    id                  SERIAL              NOT NULL,
    username            VARCHAR(32)         NOT NULL,
    password            CHAR(32)            NOT NULL,
    create_time         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_t_user PRIMARY KEY (id)
) TABLESPACE hfzycj_com;

ALTER TABLE t_user OWNER TO hfzycj_com;

-- 创建用户表的主键索引
DROP index IF EXISTS i_t_user;
CREATE UNIQUE INDEX i_t_user ON t_user USING btree (id) TABLESPACE hfzycj_com;

-- 插入初始化数据
INSERT INTO t_user (username, password) VALUES ('admin', '1024c3d5f0bbf726cd9a9c45f8681ce8'); -- admin


-- 创建新闻表
DROP TABLE IF EXISTS t_news;
CREATE TABLE t_news (
    id                  SERIAL                      NOT NULL,
    type                SMALLINT                    NOT NULL, -- 0：公司新闻；1：行业动态
    title               CHARACTER VARYING(64)       NOT NULL,
    content             TEXT                        NOT NULL,
    hit                 INTEGER                     NOT NULL,
    date                DATE                        NOT NULL,
    filename            CHARACTER VARYING(16)       NULL,
    CONSTRAINT pk_t_news PRIMARY KEY (id)
) TABLESPACE hfzycj_com;

ALTER TABLE t_news OWNER TO hfzycj_com;

-- 创建新闻表的主键索引
DROP index IF EXISTS i_t_news;
CREATE UNIQUE INDEX i_t_news ON t_news USING btree (id) TABLESPACE hfzycj_com;



-- 创建友情链接表
DROP TABLE IF EXISTS t_link;
CREATE TABLE t_link (
    id                  SERIAL                  NOT NULL,
    name                CHARACTER VARYING(32)   NOT NULL,
    url                 CHARACTER VARYING(128)  NOT NULL,
    logo                CHARACTER VARYING(32)   NULL,
    status              SMALLINT                NOT NULL, -- 0：显示；1：不显示
    order_by            SMALLINT                NOT NULL,
    CONSTRAINT pk_t_link PRIMARY KEY (id)
) TABLESPACE hfzycj_com;

ALTER TABLE t_link OWNER TO hfzycj_com;

-- 创建友情链接表的主键索引
DROP index IF EXISTS i_t_link;
CREATE UNIQUE INDEX i_t_link ON t_link USING btree (id) TABLESPACE hfzycj_com;
