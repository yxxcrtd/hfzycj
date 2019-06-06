package com.zycj.tcc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeeRegularExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeeRegularExample() {
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

        public Criteria andRegNameIsNull() {
            addCriterion("reg_name is null");
            return (Criteria) this;
        }

        public Criteria andRegNameIsNotNull() {
            addCriterion("reg_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegNameEqualTo(String value) {
            addCriterion("reg_name =", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotEqualTo(String value) {
            addCriterion("reg_name <>", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameGreaterThan(String value) {
            addCriterion("reg_name >", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameGreaterThanOrEqualTo(String value) {
            addCriterion("reg_name >=", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameLessThan(String value) {
            addCriterion("reg_name <", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameLessThanOrEqualTo(String value) {
            addCriterion("reg_name <=", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameLike(String value) {
            addCriterion("reg_name like", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotLike(String value) {
            addCriterion("reg_name not like", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameIn(List<String> values) {
            addCriterion("reg_name in", values, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotIn(List<String> values) {
            addCriterion("reg_name not in", values, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameBetween(String value1, String value2) {
            addCriterion("reg_name between", value1, value2, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotBetween(String value1, String value2) {
            addCriterion("reg_name not between", value1, value2, "regName");
            return (Criteria) this;
        }

        public Criteria andRegCodeIsNull() {
            addCriterion("reg_code is null");
            return (Criteria) this;
        }

        public Criteria andRegCodeIsNotNull() {
            addCriterion("reg_code is not null");
            return (Criteria) this;
        }

        public Criteria andRegCodeEqualTo(String value) {
            addCriterion("reg_code =", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotEqualTo(String value) {
            addCriterion("reg_code <>", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeGreaterThan(String value) {
            addCriterion("reg_code >", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeGreaterThanOrEqualTo(String value) {
            addCriterion("reg_code >=", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLessThan(String value) {
            addCriterion("reg_code <", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLessThanOrEqualTo(String value) {
            addCriterion("reg_code <=", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLike(String value) {
            addCriterion("reg_code like", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotLike(String value) {
            addCriterion("reg_code not like", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeIn(List<String> values) {
            addCriterion("reg_code in", values, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotIn(List<String> values) {
            addCriterion("reg_code not in", values, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeBetween(String value1, String value2) {
            addCriterion("reg_code between", value1, value2, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotBetween(String value1, String value2) {
            addCriterion("reg_code not between", value1, value2, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegFormulaIsNull() {
            addCriterion("reg_formula is null");
            return (Criteria) this;
        }

        public Criteria andRegFormulaIsNotNull() {
            addCriterion("reg_formula is not null");
            return (Criteria) this;
        }

        public Criteria andRegFormulaEqualTo(String value) {
            addCriterion("reg_formula =", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaNotEqualTo(String value) {
            addCriterion("reg_formula <>", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaGreaterThan(String value) {
            addCriterion("reg_formula >", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaGreaterThanOrEqualTo(String value) {
            addCriterion("reg_formula >=", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaLessThan(String value) {
            addCriterion("reg_formula <", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaLessThanOrEqualTo(String value) {
            addCriterion("reg_formula <=", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaLike(String value) {
            addCriterion("reg_formula like", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaNotLike(String value) {
            addCriterion("reg_formula not like", value, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaIn(List<String> values) {
            addCriterion("reg_formula in", values, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaNotIn(List<String> values) {
            addCriterion("reg_formula not in", values, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaBetween(String value1, String value2) {
            addCriterion("reg_formula between", value1, value2, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegFormulaNotBetween(String value1, String value2) {
            addCriterion("reg_formula not between", value1, value2, "regFormula");
            return (Criteria) this;
        }

        public Criteria andRegExplainIsNull() {
            addCriterion("reg_explain is null");
            return (Criteria) this;
        }

        public Criteria andRegExplainIsNotNull() {
            addCriterion("reg_explain is not null");
            return (Criteria) this;
        }

        public Criteria andRegExplainEqualTo(String value) {
            addCriterion("reg_explain =", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainNotEqualTo(String value) {
            addCriterion("reg_explain <>", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainGreaterThan(String value) {
            addCriterion("reg_explain >", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainGreaterThanOrEqualTo(String value) {
            addCriterion("reg_explain >=", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainLessThan(String value) {
            addCriterion("reg_explain <", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainLessThanOrEqualTo(String value) {
            addCriterion("reg_explain <=", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainLike(String value) {
            addCriterion("reg_explain like", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainNotLike(String value) {
            addCriterion("reg_explain not like", value, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainIn(List<String> values) {
            addCriterion("reg_explain in", values, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainNotIn(List<String> values) {
            addCriterion("reg_explain not in", values, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainBetween(String value1, String value2) {
            addCriterion("reg_explain between", value1, value2, "regExplain");
            return (Criteria) this;
        }

        public Criteria andRegExplainNotBetween(String value1, String value2) {
            addCriterion("reg_explain not between", value1, value2, "regExplain");
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