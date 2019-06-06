package com.zycj.tcc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PosExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PosExample() {
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

        public Criteria andSectionIdIsNull() {
            addCriterion("section_id is null");
            return (Criteria) this;
        }

        public Criteria andSectionIdIsNotNull() {
            addCriterion("section_id is not null");
            return (Criteria) this;
        }

        public Criteria andSectionIdEqualTo(Integer value) {
            addCriterion("section_id =", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualTo(Integer value) {
            addCriterion("section_id <>", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThan(Integer value) {
            addCriterion("section_id >", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("section_id >=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThan(Integer value) {
            addCriterion("section_id <", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("section_id <=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdIn(List<Integer> values) {
            addCriterion("section_id in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotIn(List<Integer> values) {
            addCriterion("section_id not in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdBetween(Integer value1, Integer value2) {
            addCriterion("section_id between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("section_id not between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andPosNoIsNull() {
            addCriterion("pos_no is null");
            return (Criteria) this;
        }

        public Criteria andPosNoIsNotNull() {
            addCriterion("pos_no is not null");
            return (Criteria) this;
        }

        public Criteria andPosNoEqualTo(String value) {
            addCriterion("pos_no =", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoNotEqualTo(String value) {
            addCriterion("pos_no <>", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoGreaterThan(String value) {
            addCriterion("pos_no >", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoGreaterThanOrEqualTo(String value) {
            addCriterion("pos_no >=", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoLessThan(String value) {
            addCriterion("pos_no <", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoLessThanOrEqualTo(String value) {
            addCriterion("pos_no <=", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoLike(String value) {
            addCriterion("pos_no like", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoNotLike(String value) {
            addCriterion("pos_no not like", value, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoIn(List<String> values) {
            addCriterion("pos_no in", values, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoNotIn(List<String> values) {
            addCriterion("pos_no not in", values, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoBetween(String value1, String value2) {
            addCriterion("pos_no between", value1, value2, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosNoNotBetween(String value1, String value2) {
            addCriterion("pos_no not between", value1, value2, "posNo");
            return (Criteria) this;
        }

        public Criteria andPosModelIsNull() {
            addCriterion("pos_model is null");
            return (Criteria) this;
        }

        public Criteria andPosModelIsNotNull() {
            addCriterion("pos_model is not null");
            return (Criteria) this;
        }

        public Criteria andPosModelEqualTo(String value) {
            addCriterion("pos_model =", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelNotEqualTo(String value) {
            addCriterion("pos_model <>", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelGreaterThan(String value) {
            addCriterion("pos_model >", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelGreaterThanOrEqualTo(String value) {
            addCriterion("pos_model >=", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelLessThan(String value) {
            addCriterion("pos_model <", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelLessThanOrEqualTo(String value) {
            addCriterion("pos_model <=", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelLike(String value) {
            addCriterion("pos_model like", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelNotLike(String value) {
            addCriterion("pos_model not like", value, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelIn(List<String> values) {
            addCriterion("pos_model in", values, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelNotIn(List<String> values) {
            addCriterion("pos_model not in", values, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelBetween(String value1, String value2) {
            addCriterion("pos_model between", value1, value2, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosModelNotBetween(String value1, String value2) {
            addCriterion("pos_model not between", value1, value2, "posModel");
            return (Criteria) this;
        }

        public Criteria andPosStatusIsNull() {
            addCriterion("pos_status is null");
            return (Criteria) this;
        }

        public Criteria andPosStatusIsNotNull() {
            addCriterion("pos_status is not null");
            return (Criteria) this;
        }

        public Criteria andPosStatusEqualTo(Integer value) {
            addCriterion("pos_status =", value, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusNotEqualTo(Integer value) {
            addCriterion("pos_status <>", value, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusGreaterThan(Integer value) {
            addCriterion("pos_status >", value, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("pos_status >=", value, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusLessThan(Integer value) {
            addCriterion("pos_status <", value, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusLessThanOrEqualTo(Integer value) {
            addCriterion("pos_status <=", value, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusIn(List<Integer> values) {
            addCriterion("pos_status in", values, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusNotIn(List<Integer> values) {
            addCriterion("pos_status not in", values, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusBetween(Integer value1, Integer value2) {
            addCriterion("pos_status between", value1, value2, "posStatus");
            return (Criteria) this;
        }

        public Criteria andPosStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("pos_status not between", value1, value2, "posStatus");
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

        public Criteria andOnlineStatusIsNull() {
            addCriterion("online_status is null");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusIsNotNull() {
            addCriterion("online_status is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusEqualTo(Integer value) {
            addCriterion("online_status =", value, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusNotEqualTo(Integer value) {
            addCriterion("online_status <>", value, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusGreaterThan(Integer value) {
            addCriterion("online_status >", value, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("online_status >=", value, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusLessThan(Integer value) {
            addCriterion("online_status <", value, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusLessThanOrEqualTo(Integer value) {
            addCriterion("online_status <=", value, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusIn(List<Integer> values) {
            addCriterion("online_status in", values, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusNotIn(List<Integer> values) {
            addCriterion("online_status not in", values, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusBetween(Integer value1, Integer value2) {
            addCriterion("online_status between", value1, value2, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("online_status not between", value1, value2, "onlineStatus");
            return (Criteria) this;
        }

        public Criteria andEmpNoIsNull() {
            addCriterion("emp_no is null");
            return (Criteria) this;
        }

        public Criteria andEmpNoIsNotNull() {
            addCriterion("emp_no is not null");
            return (Criteria) this;
        }

        public Criteria andEmpNoEqualTo(String value) {
            addCriterion("emp_no =", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotEqualTo(String value) {
            addCriterion("emp_no <>", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoGreaterThan(String value) {
            addCriterion("emp_no >", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoGreaterThanOrEqualTo(String value) {
            addCriterion("emp_no >=", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoLessThan(String value) {
            addCriterion("emp_no <", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoLessThanOrEqualTo(String value) {
            addCriterion("emp_no <=", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoLike(String value) {
            addCriterion("emp_no like", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotLike(String value) {
            addCriterion("emp_no not like", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoIn(List<String> values) {
            addCriterion("emp_no in", values, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotIn(List<String> values) {
            addCriterion("emp_no not in", values, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoBetween(String value1, String value2) {
            addCriterion("emp_no between", value1, value2, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotBetween(String value1, String value2) {
            addCriterion("emp_no not between", value1, value2, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNull() {
            addCriterion("emp_name is null");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNotNull() {
            addCriterion("emp_name is not null");
            return (Criteria) this;
        }

        public Criteria andEmpNameEqualTo(String value) {
            addCriterion("emp_name =", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotEqualTo(String value) {
            addCriterion("emp_name <>", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThan(String value) {
            addCriterion("emp_name >", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThanOrEqualTo(String value) {
            addCriterion("emp_name >=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThan(String value) {
            addCriterion("emp_name <", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThanOrEqualTo(String value) {
            addCriterion("emp_name <=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLike(String value) {
            addCriterion("emp_name like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotLike(String value) {
            addCriterion("emp_name not like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameIn(List<String> values) {
            addCriterion("emp_name in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotIn(List<String> values) {
            addCriterion("emp_name not in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameBetween(String value1, String value2) {
            addCriterion("emp_name between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotBetween(String value1, String value2) {
            addCriterion("emp_name not between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andSectionNoIsNull() {
            addCriterion("section_no is null");
            return (Criteria) this;
        }

        public Criteria andSectionNoIsNotNull() {
            addCriterion("section_no is not null");
            return (Criteria) this;
        }

        public Criteria andSectionNoEqualTo(String value) {
            addCriterion("section_no =", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoNotEqualTo(String value) {
            addCriterion("section_no <>", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoGreaterThan(String value) {
            addCriterion("section_no >", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoGreaterThanOrEqualTo(String value) {
            addCriterion("section_no >=", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoLessThan(String value) {
            addCriterion("section_no <", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoLessThanOrEqualTo(String value) {
            addCriterion("section_no <=", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoLike(String value) {
            addCriterion("section_no like", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoNotLike(String value) {
            addCriterion("section_no not like", value, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoIn(List<String> values) {
            addCriterion("section_no in", values, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoNotIn(List<String> values) {
            addCriterion("section_no not in", values, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoBetween(String value1, String value2) {
            addCriterion("section_no between", value1, value2, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNoNotBetween(String value1, String value2) {
            addCriterion("section_no not between", value1, value2, "sectionNo");
            return (Criteria) this;
        }

        public Criteria andSectionNameIsNull() {
            addCriterion("section_name is null");
            return (Criteria) this;
        }

        public Criteria andSectionNameIsNotNull() {
            addCriterion("section_name is not null");
            return (Criteria) this;
        }

        public Criteria andSectionNameEqualTo(String value) {
            addCriterion("section_name =", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotEqualTo(String value) {
            addCriterion("section_name <>", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameGreaterThan(String value) {
            addCriterion("section_name >", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("section_name >=", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameLessThan(String value) {
            addCriterion("section_name <", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameLessThanOrEqualTo(String value) {
            addCriterion("section_name <=", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameLike(String value) {
            addCriterion("section_name like", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotLike(String value) {
            addCriterion("section_name not like", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameIn(List<String> values) {
            addCriterion("section_name in", values, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotIn(List<String> values) {
            addCriterion("section_name not in", values, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameBetween(String value1, String value2) {
            addCriterion("section_name between", value1, value2, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotBetween(String value1, String value2) {
            addCriterion("section_name not between", value1, value2, "sectionName");
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