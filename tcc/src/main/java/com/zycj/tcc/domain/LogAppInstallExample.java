package com.zycj.tcc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogAppInstallExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogAppInstallExample() {
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

        public Criteria andCurrentCodeIsNull() {
            addCriterion("current_code is null");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeIsNotNull() {
            addCriterion("current_code is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeEqualTo(Integer value) {
            addCriterion("current_code =", value, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeNotEqualTo(Integer value) {
            addCriterion("current_code <>", value, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeGreaterThan(Integer value) {
            addCriterion("current_code >", value, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_code >=", value, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeLessThan(Integer value) {
            addCriterion("current_code <", value, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeLessThanOrEqualTo(Integer value) {
            addCriterion("current_code <=", value, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeIn(List<Integer> values) {
            addCriterion("current_code in", values, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeNotIn(List<Integer> values) {
            addCriterion("current_code not in", values, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeBetween(Integer value1, Integer value2) {
            addCriterion("current_code between", value1, value2, "currentCode");
            return (Criteria) this;
        }

        public Criteria andCurrentCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("current_code not between", value1, value2, "currentCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeIsNull() {
            addCriterion("former_code is null");
            return (Criteria) this;
        }

        public Criteria andFormerCodeIsNotNull() {
            addCriterion("former_code is not null");
            return (Criteria) this;
        }

        public Criteria andFormerCodeEqualTo(Integer value) {
            addCriterion("former_code =", value, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeNotEqualTo(Integer value) {
            addCriterion("former_code <>", value, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeGreaterThan(Integer value) {
            addCriterion("former_code >", value, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("former_code >=", value, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeLessThan(Integer value) {
            addCriterion("former_code <", value, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeLessThanOrEqualTo(Integer value) {
            addCriterion("former_code <=", value, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeIn(List<Integer> values) {
            addCriterion("former_code in", values, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeNotIn(List<Integer> values) {
            addCriterion("former_code not in", values, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeBetween(Integer value1, Integer value2) {
            addCriterion("former_code between", value1, value2, "formerCode");
            return (Criteria) this;
        }

        public Criteria andFormerCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("former_code not between", value1, value2, "formerCode");
            return (Criteria) this;
        }

        public Criteria andInstallTimeIsNull() {
            addCriterion("install_time is null");
            return (Criteria) this;
        }

        public Criteria andInstallTimeIsNotNull() {
            addCriterion("install_time is not null");
            return (Criteria) this;
        }

        public Criteria andInstallTimeEqualTo(Date value) {
            addCriterion("install_time =", value, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeNotEqualTo(Date value) {
            addCriterion("install_time <>", value, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeGreaterThan(Date value) {
            addCriterion("install_time >", value, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("install_time >=", value, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeLessThan(Date value) {
            addCriterion("install_time <", value, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeLessThanOrEqualTo(Date value) {
            addCriterion("install_time <=", value, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeIn(List<Date> values) {
            addCriterion("install_time in", values, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeNotIn(List<Date> values) {
            addCriterion("install_time not in", values, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeBetween(Date value1, Date value2) {
            addCriterion("install_time between", value1, value2, "installTime");
            return (Criteria) this;
        }

        public Criteria andInstallTimeNotBetween(Date value1, Date value2) {
            addCriterion("install_time not between", value1, value2, "installTime");
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