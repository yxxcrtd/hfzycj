package com.zycj.tcc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppUpdateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppUpdateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinIsNull() {
            addCriterion("version_code_min is null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinIsNotNull() {
            addCriterion("version_code_min is not null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinEqualTo(Integer value) {
            addCriterion("version_code_min =", value, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinNotEqualTo(Integer value) {
            addCriterion("version_code_min <>", value, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinGreaterThan(Integer value) {
            addCriterion("version_code_min >", value, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_code_min >=", value, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinLessThan(Integer value) {
            addCriterion("version_code_min <", value, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinLessThanOrEqualTo(Integer value) {
            addCriterion("version_code_min <=", value, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinIn(List<Integer> values) {
            addCriterion("version_code_min in", values, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinNotIn(List<Integer> values) {
            addCriterion("version_code_min not in", values, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinBetween(Integer value1, Integer value2) {
            addCriterion("version_code_min between", value1, value2, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMinNotBetween(Integer value1, Integer value2) {
            addCriterion("version_code_min not between", value1, value2, "versionCodeMin");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxIsNull() {
            addCriterion("version_code_max is null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxIsNotNull() {
            addCriterion("version_code_max is not null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxEqualTo(Integer value) {
            addCriterion("version_code_max =", value, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxNotEqualTo(Integer value) {
            addCriterion("version_code_max <>", value, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxGreaterThan(Integer value) {
            addCriterion("version_code_max >", value, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_code_max >=", value, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxLessThan(Integer value) {
            addCriterion("version_code_max <", value, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxLessThanOrEqualTo(Integer value) {
            addCriterion("version_code_max <=", value, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxIn(List<Integer> values) {
            addCriterion("version_code_max in", values, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxNotIn(List<Integer> values) {
            addCriterion("version_code_max not in", values, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxBetween(Integer value1, Integer value2) {
            addCriterion("version_code_max between", value1, value2, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionCodeMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("version_code_max not between", value1, value2, "versionCodeMax");
            return (Criteria) this;
        }

        public Criteria andVersionNameIsNull() {
            addCriterion("version_name is null");
            return (Criteria) this;
        }

        public Criteria andVersionNameIsNotNull() {
            addCriterion("version_name is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNameEqualTo(String value) {
            addCriterion("version_name =", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotEqualTo(String value) {
            addCriterion("version_name <>", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameGreaterThan(String value) {
            addCriterion("version_name >", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameGreaterThanOrEqualTo(String value) {
            addCriterion("version_name >=", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameLessThan(String value) {
            addCriterion("version_name <", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameLessThanOrEqualTo(String value) {
            addCriterion("version_name <=", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameLike(String value) {
            addCriterion("version_name like", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotLike(String value) {
            addCriterion("version_name not like", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameIn(List<String> values) {
            addCriterion("version_name in", values, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotIn(List<String> values) {
            addCriterion("version_name not in", values, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameBetween(String value1, String value2) {
            addCriterion("version_name between", value1, value2, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotBetween(String value1, String value2) {
            addCriterion("version_name not between", value1, value2, "versionName");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionIsNull() {
            addCriterion("update_description is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionIsNotNull() {
            addCriterion("update_description is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionEqualTo(String value) {
            addCriterion("update_description =", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionNotEqualTo(String value) {
            addCriterion("update_description <>", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionGreaterThan(String value) {
            addCriterion("update_description >", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("update_description >=", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionLessThan(String value) {
            addCriterion("update_description <", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionLessThanOrEqualTo(String value) {
            addCriterion("update_description <=", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionLike(String value) {
            addCriterion("update_description like", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionNotLike(String value) {
            addCriterion("update_description not like", value, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionIn(List<String> values) {
            addCriterion("update_description in", values, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionNotIn(List<String> values) {
            addCriterion("update_description not in", values, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionBetween(String value1, String value2) {
            addCriterion("update_description between", value1, value2, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andUpdateDescriptionNotBetween(String value1, String value2) {
            addCriterion("update_description not between", value1, value2, "updateDescription");
            return (Criteria) this;
        }

        public Criteria andApkUrlIsNull() {
            addCriterion("apk_url is null");
            return (Criteria) this;
        }

        public Criteria andApkUrlIsNotNull() {
            addCriterion("apk_url is not null");
            return (Criteria) this;
        }

        public Criteria andApkUrlEqualTo(String value) {
            addCriterion("apk_url =", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlNotEqualTo(String value) {
            addCriterion("apk_url <>", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlGreaterThan(String value) {
            addCriterion("apk_url >", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("apk_url >=", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlLessThan(String value) {
            addCriterion("apk_url <", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlLessThanOrEqualTo(String value) {
            addCriterion("apk_url <=", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlLike(String value) {
            addCriterion("apk_url like", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlNotLike(String value) {
            addCriterion("apk_url not like", value, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlIn(List<String> values) {
            addCriterion("apk_url in", values, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlNotIn(List<String> values) {
            addCriterion("apk_url not in", values, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlBetween(String value1, String value2) {
            addCriterion("apk_url between", value1, value2, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkUrlNotBetween(String value1, String value2) {
            addCriterion("apk_url not between", value1, value2, "apkUrl");
            return (Criteria) this;
        }

        public Criteria andApkSizeIsNull() {
            addCriterion("apk_size is null");
            return (Criteria) this;
        }

        public Criteria andApkSizeIsNotNull() {
            addCriterion("apk_size is not null");
            return (Criteria) this;
        }

        public Criteria andApkSizeEqualTo(String value) {
            addCriterion("apk_size =", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeNotEqualTo(String value) {
            addCriterion("apk_size <>", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeGreaterThan(String value) {
            addCriterion("apk_size >", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeGreaterThanOrEqualTo(String value) {
            addCriterion("apk_size >=", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeLessThan(String value) {
            addCriterion("apk_size <", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeLessThanOrEqualTo(String value) {
            addCriterion("apk_size <=", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeLike(String value) {
            addCriterion("apk_size like", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeNotLike(String value) {
            addCriterion("apk_size not like", value, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeIn(List<String> values) {
            addCriterion("apk_size in", values, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeNotIn(List<String> values) {
            addCriterion("apk_size not in", values, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeBetween(String value1, String value2) {
            addCriterion("apk_size between", value1, value2, "apkSize");
            return (Criteria) this;
        }

        public Criteria andApkSizeNotBetween(String value1, String value2) {
            addCriterion("apk_size not between", value1, value2, "apkSize");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIsForceIsNull() {
            addCriterion("is_force is null");
            return (Criteria) this;
        }

        public Criteria andIsForceIsNotNull() {
            addCriterion("is_force is not null");
            return (Criteria) this;
        }

        public Criteria andIsForceEqualTo(Integer value) {
            addCriterion("is_force =", value, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceNotEqualTo(Integer value) {
            addCriterion("is_force <>", value, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceGreaterThan(Integer value) {
            addCriterion("is_force >", value, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_force >=", value, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceLessThan(Integer value) {
            addCriterion("is_force <", value, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceLessThanOrEqualTo(Integer value) {
            addCriterion("is_force <=", value, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceIn(List<Integer> values) {
            addCriterion("is_force in", values, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceNotIn(List<Integer> values) {
            addCriterion("is_force not in", values, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceBetween(Integer value1, Integer value2) {
            addCriterion("is_force between", value1, value2, "isForce");
            return (Criteria) this;
        }

        public Criteria andIsForceNotBetween(Integer value1, Integer value2) {
            addCriterion("is_force not between", value1, value2, "isForce");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNull() {
            addCriterion("app_type is null");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNotNull() {
            addCriterion("app_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppTypeEqualTo(Integer value) {
            addCriterion("app_type =", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotEqualTo(Integer value) {
            addCriterion("app_type <>", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThan(Integer value) {
            addCriterion("app_type >", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_type >=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThan(Integer value) {
            addCriterion("app_type <", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThanOrEqualTo(Integer value) {
            addCriterion("app_type <=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeIn(List<Integer> values) {
            addCriterion("app_type in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotIn(List<Integer> values) {
            addCriterion("app_type not in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeBetween(Integer value1, Integer value2) {
            addCriterion("app_type between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("app_type not between", value1, value2, "appType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}