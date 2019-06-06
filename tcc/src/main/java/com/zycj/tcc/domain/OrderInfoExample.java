package com.zycj.tcc.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoExample() {
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

        public Criteria andSerialNoIsNull() {
            addCriterion("serial_no is null");
            return (Criteria) this;
        }

        public Criteria andSerialNoIsNotNull() {
            addCriterion("serial_no is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNoEqualTo(String value) {
            addCriterion("serial_no =", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotEqualTo(String value) {
            addCriterion("serial_no <>", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThan(String value) {
            addCriterion("serial_no >", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThanOrEqualTo(String value) {
            addCriterion("serial_no >=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThan(String value) {
            addCriterion("serial_no <", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThanOrEqualTo(String value) {
            addCriterion("serial_no <=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLike(String value) {
            addCriterion("serial_no like", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotLike(String value) {
            addCriterion("serial_no not like", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoIn(List<String> values) {
            addCriterion("serial_no in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotIn(List<String> values) {
            addCriterion("serial_no not in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoBetween(String value1, String value2) {
            addCriterion("serial_no between", value1, value2, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotBetween(String value1, String value2) {
            addCriterion("serial_no not between", value1, value2, "serialNo");
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

        public Criteria andSpaceNoIsNull() {
            addCriterion("space_no is null");
            return (Criteria) this;
        }

        public Criteria andSpaceNoIsNotNull() {
            addCriterion("space_no is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceNoEqualTo(String value) {
            addCriterion("space_no =", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotEqualTo(String value) {
            addCriterion("space_no <>", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoGreaterThan(String value) {
            addCriterion("space_no >", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoGreaterThanOrEqualTo(String value) {
            addCriterion("space_no >=", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoLessThan(String value) {
            addCriterion("space_no <", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoLessThanOrEqualTo(String value) {
            addCriterion("space_no <=", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoLike(String value) {
            addCriterion("space_no like", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotLike(String value) {
            addCriterion("space_no not like", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoIn(List<String> values) {
            addCriterion("space_no in", values, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotIn(List<String> values) {
            addCriterion("space_no not in", values, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoBetween(String value1, String value2) {
            addCriterion("space_no between", value1, value2, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotBetween(String value1, String value2) {
            addCriterion("space_no not between", value1, value2, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNull() {
            addCriterion("car_type is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNotNull() {
            addCriterion("car_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeEqualTo(Integer value) {
            addCriterion("car_type =", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotEqualTo(Integer value) {
            addCriterion("car_type <>", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThan(Integer value) {
            addCriterion("car_type >", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("car_type >=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThan(Integer value) {
            addCriterion("car_type <", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThanOrEqualTo(Integer value) {
            addCriterion("car_type <=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeIn(List<Integer> values) {
            addCriterion("car_type in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotIn(List<Integer> values) {
            addCriterion("car_type not in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeBetween(Integer value1, Integer value2) {
            addCriterion("car_type between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("car_type not between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarNoIsNull() {
            addCriterion("car_no is null");
            return (Criteria) this;
        }

        public Criteria andCarNoIsNotNull() {
            addCriterion("car_no is not null");
            return (Criteria) this;
        }

        public Criteria andCarNoEqualTo(String value) {
            addCriterion("car_no =", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotEqualTo(String value) {
            addCriterion("car_no <>", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoGreaterThan(String value) {
            addCriterion("car_no >", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoGreaterThanOrEqualTo(String value) {
            addCriterion("car_no >=", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLessThan(String value) {
            addCriterion("car_no <", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLessThanOrEqualTo(String value) {
            addCriterion("car_no <=", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLike(String value) {
            addCriterion("car_no like", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotLike(String value) {
            addCriterion("car_no not like", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoIn(List<String> values) {
            addCriterion("car_no in", values, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotIn(List<String> values) {
            addCriterion("car_no not in", values, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoBetween(String value1, String value2) {
            addCriterion("car_no between", value1, value2, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotBetween(String value1, String value2) {
            addCriterion("car_no not between", value1, value2, "carNo");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andInPosIsNull() {
            addCriterion("in_pos is null");
            return (Criteria) this;
        }

        public Criteria andInPosIsNotNull() {
            addCriterion("in_pos is not null");
            return (Criteria) this;
        }

        public Criteria andInPosEqualTo(String value) {
            addCriterion("in_pos =", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosNotEqualTo(String value) {
            addCriterion("in_pos <>", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosGreaterThan(String value) {
            addCriterion("in_pos >", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosGreaterThanOrEqualTo(String value) {
            addCriterion("in_pos >=", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosLessThan(String value) {
            addCriterion("in_pos <", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosLessThanOrEqualTo(String value) {
            addCriterion("in_pos <=", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosLike(String value) {
            addCriterion("in_pos like", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosNotLike(String value) {
            addCriterion("in_pos not like", value, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosIn(List<String> values) {
            addCriterion("in_pos in", values, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosNotIn(List<String> values) {
            addCriterion("in_pos not in", values, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosBetween(String value1, String value2) {
            addCriterion("in_pos between", value1, value2, "inPos");
            return (Criteria) this;
        }

        public Criteria andInPosNotBetween(String value1, String value2) {
            addCriterion("in_pos not between", value1, value2, "inPos");
            return (Criteria) this;
        }

        public Criteria andInEmpIsNull() {
            addCriterion("in_emp is null");
            return (Criteria) this;
        }

        public Criteria andInEmpIsNotNull() {
            addCriterion("in_emp is not null");
            return (Criteria) this;
        }

        public Criteria andInEmpEqualTo(String value) {
            addCriterion("in_emp =", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpNotEqualTo(String value) {
            addCriterion("in_emp <>", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpGreaterThan(String value) {
            addCriterion("in_emp >", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpGreaterThanOrEqualTo(String value) {
            addCriterion("in_emp >=", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpLessThan(String value) {
            addCriterion("in_emp <", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpLessThanOrEqualTo(String value) {
            addCriterion("in_emp <=", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpLike(String value) {
            addCriterion("in_emp like", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpNotLike(String value) {
            addCriterion("in_emp not like", value, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpIn(List<String> values) {
            addCriterion("in_emp in", values, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpNotIn(List<String> values) {
            addCriterion("in_emp not in", values, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpBetween(String value1, String value2) {
            addCriterion("in_emp between", value1, value2, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInEmpNotBetween(String value1, String value2) {
            addCriterion("in_emp not between", value1, value2, "inEmp");
            return (Criteria) this;
        }

        public Criteria andInTimeIsNull() {
            addCriterion("in_time is null");
            return (Criteria) this;
        }

        public Criteria andInTimeIsNotNull() {
            addCriterion("in_time is not null");
            return (Criteria) this;
        }

        public Criteria andInTimeEqualTo(Date value) {
            addCriterion("in_time =", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotEqualTo(Date value) {
            addCriterion("in_time <>", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThan(Date value) {
            addCriterion("in_time >", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("in_time >=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThan(Date value) {
            addCriterion("in_time <", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThanOrEqualTo(Date value) {
            addCriterion("in_time <=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeIn(List<Date> values) {
            addCriterion("in_time in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotIn(List<Date> values) {
            addCriterion("in_time not in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeBetween(Date value1, Date value2) {
            addCriterion("in_time between", value1, value2, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotBetween(Date value1, Date value2) {
            addCriterion("in_time not between", value1, value2, "inTime");
            return (Criteria) this;
        }

        public Criteria andOutPosIsNull() {
            addCriterion("out_pos is null");
            return (Criteria) this;
        }

        public Criteria andOutPosIsNotNull() {
            addCriterion("out_pos is not null");
            return (Criteria) this;
        }

        public Criteria andOutPosEqualTo(String value) {
            addCriterion("out_pos =", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosNotEqualTo(String value) {
            addCriterion("out_pos <>", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosGreaterThan(String value) {
            addCriterion("out_pos >", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosGreaterThanOrEqualTo(String value) {
            addCriterion("out_pos >=", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosLessThan(String value) {
            addCriterion("out_pos <", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosLessThanOrEqualTo(String value) {
            addCriterion("out_pos <=", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosLike(String value) {
            addCriterion("out_pos like", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosNotLike(String value) {
            addCriterion("out_pos not like", value, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosIn(List<String> values) {
            addCriterion("out_pos in", values, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosNotIn(List<String> values) {
            addCriterion("out_pos not in", values, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosBetween(String value1, String value2) {
            addCriterion("out_pos between", value1, value2, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutPosNotBetween(String value1, String value2) {
            addCriterion("out_pos not between", value1, value2, "outPos");
            return (Criteria) this;
        }

        public Criteria andOutEmpIsNull() {
            addCriterion("out_emp is null");
            return (Criteria) this;
        }

        public Criteria andOutEmpIsNotNull() {
            addCriterion("out_emp is not null");
            return (Criteria) this;
        }

        public Criteria andOutEmpEqualTo(String value) {
            addCriterion("out_emp =", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpNotEqualTo(String value) {
            addCriterion("out_emp <>", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpGreaterThan(String value) {
            addCriterion("out_emp >", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpGreaterThanOrEqualTo(String value) {
            addCriterion("out_emp >=", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpLessThan(String value) {
            addCriterion("out_emp <", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpLessThanOrEqualTo(String value) {
            addCriterion("out_emp <=", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpLike(String value) {
            addCriterion("out_emp like", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpNotLike(String value) {
            addCriterion("out_emp not like", value, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpIn(List<String> values) {
            addCriterion("out_emp in", values, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpNotIn(List<String> values) {
            addCriterion("out_emp not in", values, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpBetween(String value1, String value2) {
            addCriterion("out_emp between", value1, value2, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutEmpNotBetween(String value1, String value2) {
            addCriterion("out_emp not between", value1, value2, "outEmp");
            return (Criteria) this;
        }

        public Criteria andOutTimeIsNull() {
            addCriterion("out_time is null");
            return (Criteria) this;
        }

        public Criteria andOutTimeIsNotNull() {
            addCriterion("out_time is not null");
            return (Criteria) this;
        }

        public Criteria andOutTimeEqualTo(Date value) {
            addCriterion("out_time =", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotEqualTo(Date value) {
            addCriterion("out_time <>", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeGreaterThan(Date value) {
            addCriterion("out_time >", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("out_time >=", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeLessThan(Date value) {
            addCriterion("out_time <", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeLessThanOrEqualTo(Date value) {
            addCriterion("out_time <=", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeIn(List<Date> values) {
            addCriterion("out_time in", values, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotIn(List<Date> values) {
            addCriterion("out_time not in", values, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeBetween(Date value1, Date value2) {
            addCriterion("out_time between", value1, value2, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotBetween(Date value1, Date value2) {
            addCriterion("out_time not between", value1, value2, "outTime");
            return (Criteria) this;
        }

        public Criteria andIsFreeIsNull() {
            addCriterion("is_free is null");
            return (Criteria) this;
        }

        public Criteria andIsFreeIsNotNull() {
            addCriterion("is_free is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreeEqualTo(Integer value) {
            addCriterion("is_free =", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotEqualTo(Integer value) {
            addCriterion("is_free <>", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThan(Integer value) {
            addCriterion("is_free >", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_free >=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThan(Integer value) {
            addCriterion("is_free <", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThanOrEqualTo(Integer value) {
            addCriterion("is_free <=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeIn(List<Integer> values) {
            addCriterion("is_free in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotIn(List<Integer> values) {
            addCriterion("is_free not in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeBetween(Integer value1, Integer value2) {
            addCriterion("is_free between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_free not between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andChargeEmpIsNull() {
            addCriterion("charge_emp is null");
            return (Criteria) this;
        }

        public Criteria andChargeEmpIsNotNull() {
            addCriterion("charge_emp is not null");
            return (Criteria) this;
        }

        public Criteria andChargeEmpEqualTo(String value) {
            addCriterion("charge_emp =", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpNotEqualTo(String value) {
            addCriterion("charge_emp <>", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpGreaterThan(String value) {
            addCriterion("charge_emp >", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpGreaterThanOrEqualTo(String value) {
            addCriterion("charge_emp >=", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpLessThan(String value) {
            addCriterion("charge_emp <", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpLessThanOrEqualTo(String value) {
            addCriterion("charge_emp <=", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpLike(String value) {
            addCriterion("charge_emp like", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpNotLike(String value) {
            addCriterion("charge_emp not like", value, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpIn(List<String> values) {
            addCriterion("charge_emp in", values, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpNotIn(List<String> values) {
            addCriterion("charge_emp not in", values, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpBetween(String value1, String value2) {
            addCriterion("charge_emp between", value1, value2, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeEmpNotBetween(String value1, String value2) {
            addCriterion("charge_emp not between", value1, value2, "chargeEmp");
            return (Criteria) this;
        }

        public Criteria andChargeTimeIsNull() {
            addCriterion("charge_time is null");
            return (Criteria) this;
        }

        public Criteria andChargeTimeIsNotNull() {
            addCriterion("charge_time is not null");
            return (Criteria) this;
        }

        public Criteria andChargeTimeEqualTo(Date value) {
            addCriterion("charge_time =", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeNotEqualTo(Date value) {
            addCriterion("charge_time <>", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeGreaterThan(Date value) {
            addCriterion("charge_time >", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("charge_time >=", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeLessThan(Date value) {
            addCriterion("charge_time <", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeLessThanOrEqualTo(Date value) {
            addCriterion("charge_time <=", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeIn(List<Date> values) {
            addCriterion("charge_time in", values, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeNotIn(List<Date> values) {
            addCriterion("charge_time not in", values, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeBetween(Date value1, Date value2) {
            addCriterion("charge_time between", value1, value2, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeNotBetween(Date value1, Date value2) {
            addCriterion("charge_time not between", value1, value2, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargePosIsNull() {
            addCriterion("charge_pos is null");
            return (Criteria) this;
        }

        public Criteria andChargePosIsNotNull() {
            addCriterion("charge_pos is not null");
            return (Criteria) this;
        }

        public Criteria andChargePosEqualTo(String value) {
            addCriterion("charge_pos =", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosNotEqualTo(String value) {
            addCriterion("charge_pos <>", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosGreaterThan(String value) {
            addCriterion("charge_pos >", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosGreaterThanOrEqualTo(String value) {
            addCriterion("charge_pos >=", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosLessThan(String value) {
            addCriterion("charge_pos <", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosLessThanOrEqualTo(String value) {
            addCriterion("charge_pos <=", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosLike(String value) {
            addCriterion("charge_pos like", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosNotLike(String value) {
            addCriterion("charge_pos not like", value, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosIn(List<String> values) {
            addCriterion("charge_pos in", values, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosNotIn(List<String> values) {
            addCriterion("charge_pos not in", values, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosBetween(String value1, String value2) {
            addCriterion("charge_pos between", value1, value2, "chargePos");
            return (Criteria) this;
        }

        public Criteria andChargePosNotBetween(String value1, String value2) {
            addCriterion("charge_pos not between", value1, value2, "chargePos");
            return (Criteria) this;
        }

        public Criteria andFeeTotalIsNull() {
            addCriterion("fee_total is null");
            return (Criteria) this;
        }

        public Criteria andFeeTotalIsNotNull() {
            addCriterion("fee_total is not null");
            return (Criteria) this;
        }

        public Criteria andFeeTotalEqualTo(BigDecimal value) {
            addCriterion("fee_total =", value, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalNotEqualTo(BigDecimal value) {
            addCriterion("fee_total <>", value, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalGreaterThan(BigDecimal value) {
            addCriterion("fee_total >", value, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_total >=", value, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalLessThan(BigDecimal value) {
            addCriterion("fee_total <", value, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_total <=", value, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalIn(List<BigDecimal> values) {
            addCriterion("fee_total in", values, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalNotIn(List<BigDecimal> values) {
            addCriterion("fee_total not in", values, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_total between", value1, value2, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_total not between", value1, value2, "feeTotal");
            return (Criteria) this;
        }

        public Criteria andFeeRealIsNull() {
            addCriterion("fee_real is null");
            return (Criteria) this;
        }

        public Criteria andFeeRealIsNotNull() {
            addCriterion("fee_real is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRealEqualTo(BigDecimal value) {
            addCriterion("fee_real =", value, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealNotEqualTo(BigDecimal value) {
            addCriterion("fee_real <>", value, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealGreaterThan(BigDecimal value) {
            addCriterion("fee_real >", value, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_real >=", value, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealLessThan(BigDecimal value) {
            addCriterion("fee_real <", value, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_real <=", value, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealIn(List<BigDecimal> values) {
            addCriterion("fee_real in", values, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealNotIn(List<BigDecimal> values) {
            addCriterion("fee_real not in", values, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_real between", value1, value2, "feeReal");
            return (Criteria) this;
        }

        public Criteria andFeeRealNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_real not between", value1, value2, "feeReal");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIsNull() {
            addCriterion("invoice_batch is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIsNotNull() {
            addCriterion("invoice_batch is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchEqualTo(String value) {
            addCriterion("invoice_batch =", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchNotEqualTo(String value) {
            addCriterion("invoice_batch <>", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchGreaterThan(String value) {
            addCriterion("invoice_batch >", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_batch >=", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchLessThan(String value) {
            addCriterion("invoice_batch <", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchLessThanOrEqualTo(String value) {
            addCriterion("invoice_batch <=", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchLike(String value) {
            addCriterion("invoice_batch like", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchNotLike(String value) {
            addCriterion("invoice_batch not like", value, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIn(List<String> values) {
            addCriterion("invoice_batch in", values, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchNotIn(List<String> values) {
            addCriterion("invoice_batch not in", values, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchBetween(String value1, String value2) {
            addCriterion("invoice_batch between", value1, value2, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchNotBetween(String value1, String value2) {
            addCriterion("invoice_batch not between", value1, value2, "invoiceBatch");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNull() {
            addCriterion("invoice_no is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNotNull() {
            addCriterion("invoice_no is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoEqualTo(String value) {
            addCriterion("invoice_no =", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotEqualTo(String value) {
            addCriterion("invoice_no <>", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThan(String value) {
            addCriterion("invoice_no >", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_no >=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThan(String value) {
            addCriterion("invoice_no <", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThanOrEqualTo(String value) {
            addCriterion("invoice_no <=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLike(String value) {
            addCriterion("invoice_no like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotLike(String value) {
            addCriterion("invoice_no not like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIn(List<String> values) {
            addCriterion("invoice_no in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotIn(List<String> values) {
            addCriterion("invoice_no not in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoBetween(String value1, String value2) {
            addCriterion("invoice_no between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotBetween(String value1, String value2) {
            addCriterion("invoice_no not between", value1, value2, "invoiceNo");
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

        public Criteria andReqSerialIsNull() {
            addCriterion("req_serial is null");
            return (Criteria) this;
        }

        public Criteria andReqSerialIsNotNull() {
            addCriterion("req_serial is not null");
            return (Criteria) this;
        }

        public Criteria andReqSerialEqualTo(String value) {
            addCriterion("req_serial =", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialNotEqualTo(String value) {
            addCriterion("req_serial <>", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialGreaterThan(String value) {
            addCriterion("req_serial >", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialGreaterThanOrEqualTo(String value) {
            addCriterion("req_serial >=", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialLessThan(String value) {
            addCriterion("req_serial <", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialLessThanOrEqualTo(String value) {
            addCriterion("req_serial <=", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialLike(String value) {
            addCriterion("req_serial like", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialNotLike(String value) {
            addCriterion("req_serial not like", value, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialIn(List<String> values) {
            addCriterion("req_serial in", values, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialNotIn(List<String> values) {
            addCriterion("req_serial not in", values, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialBetween(String value1, String value2) {
            addCriterion("req_serial between", value1, value2, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andReqSerialNotBetween(String value1, String value2) {
            addCriterion("req_serial not between", value1, value2, "reqSerial");
            return (Criteria) this;
        }

        public Criteria andFeeIdIsNull() {
            addCriterion("fee_id is null");
            return (Criteria) this;
        }

        public Criteria andFeeIdIsNotNull() {
            addCriterion("fee_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeeIdEqualTo(Integer value) {
            addCriterion("fee_id =", value, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdNotEqualTo(Integer value) {
            addCriterion("fee_id <>", value, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdGreaterThan(Integer value) {
            addCriterion("fee_id >", value, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee_id >=", value, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdLessThan(Integer value) {
            addCriterion("fee_id <", value, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("fee_id <=", value, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdIn(List<Integer> values) {
            addCriterion("fee_id in", values, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdNotIn(List<Integer> values) {
            addCriterion("fee_id not in", values, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdBetween(Integer value1, Integer value2) {
            addCriterion("fee_id between", value1, value2, "feeId");
            return (Criteria) this;
        }

        public Criteria andFeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fee_id not between", value1, value2, "feeId");
            return (Criteria) this;
        }

        public Criteria andArrearStatusIsNull() {
            addCriterion("arrear_status is null");
            return (Criteria) this;
        }

        public Criteria andArrearStatusIsNotNull() {
            addCriterion("arrear_status is not null");
            return (Criteria) this;
        }

        public Criteria andArrearStatusEqualTo(Integer value) {
            addCriterion("arrear_status =", value, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusNotEqualTo(Integer value) {
            addCriterion("arrear_status <>", value, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusGreaterThan(Integer value) {
            addCriterion("arrear_status >", value, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrear_status >=", value, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusLessThan(Integer value) {
            addCriterion("arrear_status <", value, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusLessThanOrEqualTo(Integer value) {
            addCriterion("arrear_status <=", value, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusIn(List<Integer> values) {
            addCriterion("arrear_status in", values, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusNotIn(List<Integer> values) {
            addCriterion("arrear_status not in", values, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusBetween(Integer value1, Integer value2) {
            addCriterion("arrear_status between", value1, value2, "arrearStatus");
            return (Criteria) this;
        }

        public Criteria andArrearStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("arrear_status not between", value1, value2, "arrearStatus");
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