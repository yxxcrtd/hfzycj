package com.tcc.park.api.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RoadSectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoadSectionExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
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

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andRoadNameIsNull() {
            addCriterion("road_name is null");
            return (Criteria) this;
        }

        public Criteria andRoadNameIsNotNull() {
            addCriterion("road_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoadNameEqualTo(String value) {
            addCriterion("road_name =", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotEqualTo(String value) {
            addCriterion("road_name <>", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameGreaterThan(String value) {
            addCriterion("road_name >", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameGreaterThanOrEqualTo(String value) {
            addCriterion("road_name >=", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameLessThan(String value) {
            addCriterion("road_name <", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameLessThanOrEqualTo(String value) {
            addCriterion("road_name <=", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameLike(String value) {
            addCriterion("road_name like", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotLike(String value) {
            addCriterion("road_name not like", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameIn(List<String> values) {
            addCriterion("road_name in", values, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotIn(List<String> values) {
            addCriterion("road_name not in", values, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameBetween(String value1, String value2) {
            addCriterion("road_name between", value1, value2, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotBetween(String value1, String value2) {
            addCriterion("road_name not between", value1, value2, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadCodeIsNull() {
            addCriterion("road_code is null");
            return (Criteria) this;
        }

        public Criteria andRoadCodeIsNotNull() {
            addCriterion("road_code is not null");
            return (Criteria) this;
        }

        public Criteria andRoadCodeEqualTo(String value) {
            addCriterion("road_code =", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeNotEqualTo(String value) {
            addCriterion("road_code <>", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeGreaterThan(String value) {
            addCriterion("road_code >", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeGreaterThanOrEqualTo(String value) {
            addCriterion("road_code >=", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeLessThan(String value) {
            addCriterion("road_code <", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeLessThanOrEqualTo(String value) {
            addCriterion("road_code <=", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeLike(String value) {
            addCriterion("road_code like", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeNotLike(String value) {
            addCriterion("road_code not like", value, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeIn(List<String> values) {
            addCriterion("road_code in", values, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeNotIn(List<String> values) {
            addCriterion("road_code not in", values, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeBetween(String value1, String value2) {
            addCriterion("road_code between", value1, value2, "roadCode");
            return (Criteria) this;
        }

        public Criteria andRoadCodeNotBetween(String value1, String value2) {
            addCriterion("road_code not between", value1, value2, "roadCode");
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

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andUsedIsNull() {
            addCriterion("used is null");
            return (Criteria) this;
        }

        public Criteria andUsedIsNotNull() {
            addCriterion("used is not null");
            return (Criteria) this;
        }

        public Criteria andUsedEqualTo(Integer value) {
            addCriterion("used =", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedNotEqualTo(Integer value) {
            addCriterion("used <>", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedGreaterThan(Integer value) {
            addCriterion("used >", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedGreaterThanOrEqualTo(Integer value) {
            addCriterion("used >=", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedLessThan(Integer value) {
            addCriterion("used <", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedLessThanOrEqualTo(Integer value) {
            addCriterion("used <=", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedIn(List<Integer> values) {
            addCriterion("used in", values, "used");
            return (Criteria) this;
        }

        public Criteria andUsedNotIn(List<Integer> values) {
            addCriterion("used not in", values, "used");
            return (Criteria) this;
        }

        public Criteria andUsedBetween(Integer value1, Integer value2) {
            addCriterion("used between", value1, value2, "used");
            return (Criteria) this;
        }

        public Criteria andUsedNotBetween(Integer value1, Integer value2) {
            addCriterion("used not between", value1, value2, "used");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIsNull() {
            addCriterion("fee_type is null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIsNotNull() {
            addCriterion("fee_type is not null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeEqualTo(Integer value) {
            addCriterion("fee_type =", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotEqualTo(Integer value) {
            addCriterion("fee_type <>", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeGreaterThan(Integer value) {
            addCriterion("fee_type >", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee_type >=", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLessThan(Integer value) {
            addCriterion("fee_type <", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fee_type <=", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIn(List<Integer> values) {
            addCriterion("fee_type in", values, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotIn(List<Integer> values) {
            addCriterion("fee_type not in", values, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeBetween(Integer value1, Integer value2) {
            addCriterion("fee_type between", value1, value2, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fee_type not between", value1, value2, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeStartIsNull() {
            addCriterion("fee_start is null");
            return (Criteria) this;
        }

        public Criteria andFeeStartIsNotNull() {
            addCriterion("fee_start is not null");
            return (Criteria) this;
        }

        public Criteria andFeeStartEqualTo(Date value) {
            addCriterionForJDBCTime("fee_start =", value, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartNotEqualTo(Date value) {
            addCriterionForJDBCTime("fee_start <>", value, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartGreaterThan(Date value) {
            addCriterionForJDBCTime("fee_start >", value, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("fee_start >=", value, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartLessThan(Date value) {
            addCriterionForJDBCTime("fee_start <", value, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("fee_start <=", value, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartIn(List<Date> values) {
            addCriterionForJDBCTime("fee_start in", values, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartNotIn(List<Date> values) {
            addCriterionForJDBCTime("fee_start not in", values, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("fee_start between", value1, value2, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeStartNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("fee_start not between", value1, value2, "feeStart");
            return (Criteria) this;
        }

        public Criteria andFeeEndIsNull() {
            addCriterion("fee_end is null");
            return (Criteria) this;
        }

        public Criteria andFeeEndIsNotNull() {
            addCriterion("fee_end is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEndEqualTo(Date value) {
            addCriterionForJDBCTime("fee_end =", value, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndNotEqualTo(Date value) {
            addCriterionForJDBCTime("fee_end <>", value, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndGreaterThan(Date value) {
            addCriterionForJDBCTime("fee_end >", value, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("fee_end >=", value, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndLessThan(Date value) {
            addCriterionForJDBCTime("fee_end <", value, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("fee_end <=", value, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndIn(List<Date> values) {
            addCriterionForJDBCTime("fee_end in", values, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndNotIn(List<Date> values) {
            addCriterionForJDBCTime("fee_end not in", values, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("fee_end between", value1, value2, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andFeeEndNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("fee_end not between", value1, value2, "feeEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceStartIsNull() {
            addCriterion("space_start is null");
            return (Criteria) this;
        }

        public Criteria andSpaceStartIsNotNull() {
            addCriterion("space_start is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceStartEqualTo(String value) {
            addCriterion("space_start =", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartNotEqualTo(String value) {
            addCriterion("space_start <>", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartGreaterThan(String value) {
            addCriterion("space_start >", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartGreaterThanOrEqualTo(String value) {
            addCriterion("space_start >=", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartLessThan(String value) {
            addCriterion("space_start <", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartLessThanOrEqualTo(String value) {
            addCriterion("space_start <=", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartLike(String value) {
            addCriterion("space_start like", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartNotLike(String value) {
            addCriterion("space_start not like", value, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartIn(List<String> values) {
            addCriterion("space_start in", values, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartNotIn(List<String> values) {
            addCriterion("space_start not in", values, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartBetween(String value1, String value2) {
            addCriterion("space_start between", value1, value2, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceStartNotBetween(String value1, String value2) {
            addCriterion("space_start not between", value1, value2, "spaceStart");
            return (Criteria) this;
        }

        public Criteria andSpaceEndIsNull() {
            addCriterion("space_end is null");
            return (Criteria) this;
        }

        public Criteria andSpaceEndIsNotNull() {
            addCriterion("space_end is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceEndEqualTo(String value) {
            addCriterion("space_end =", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndNotEqualTo(String value) {
            addCriterion("space_end <>", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndGreaterThan(String value) {
            addCriterion("space_end >", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndGreaterThanOrEqualTo(String value) {
            addCriterion("space_end >=", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndLessThan(String value) {
            addCriterion("space_end <", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndLessThanOrEqualTo(String value) {
            addCriterion("space_end <=", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndLike(String value) {
            addCriterion("space_end like", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndNotLike(String value) {
            addCriterion("space_end not like", value, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndIn(List<String> values) {
            addCriterion("space_end in", values, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndNotIn(List<String> values) {
            addCriterion("space_end not in", values, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndBetween(String value1, String value2) {
            addCriterion("space_end between", value1, value2, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceEndNotBetween(String value1, String value2) {
            addCriterion("space_end not between", value1, value2, "spaceEnd");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersIsNull() {
            addCriterion("space_others is null");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersIsNotNull() {
            addCriterion("space_others is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersEqualTo(String value) {
            addCriterion("space_others =", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersNotEqualTo(String value) {
            addCriterion("space_others <>", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersGreaterThan(String value) {
            addCriterion("space_others >", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersGreaterThanOrEqualTo(String value) {
            addCriterion("space_others >=", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersLessThan(String value) {
            addCriterion("space_others <", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersLessThanOrEqualTo(String value) {
            addCriterion("space_others <=", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersLike(String value) {
            addCriterion("space_others like", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersNotLike(String value) {
            addCriterion("space_others not like", value, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersIn(List<String> values) {
            addCriterion("space_others in", values, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersNotIn(List<String> values) {
            addCriterion("space_others not in", values, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersBetween(String value1, String value2) {
            addCriterion("space_others between", value1, value2, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andSpaceOthersNotBetween(String value1, String value2) {
            addCriterion("space_others not between", value1, value2, "spaceOthers");
            return (Criteria) this;
        }

        public Criteria andStartLoIsNull() {
            addCriterion("start_lo is null");
            return (Criteria) this;
        }

        public Criteria andStartLoIsNotNull() {
            addCriterion("start_lo is not null");
            return (Criteria) this;
        }

        public Criteria andStartLoEqualTo(BigDecimal value) {
            addCriterion("start_lo =", value, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoNotEqualTo(BigDecimal value) {
            addCriterion("start_lo <>", value, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoGreaterThan(BigDecimal value) {
            addCriterion("start_lo >", value, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("start_lo >=", value, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoLessThan(BigDecimal value) {
            addCriterion("start_lo <", value, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("start_lo <=", value, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoIn(List<BigDecimal> values) {
            addCriterion("start_lo in", values, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoNotIn(List<BigDecimal> values) {
            addCriterion("start_lo not in", values, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_lo between", value1, value2, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_lo not between", value1, value2, "startLo");
            return (Criteria) this;
        }

        public Criteria andStartLaIsNull() {
            addCriterion("start_la is null");
            return (Criteria) this;
        }

        public Criteria andStartLaIsNotNull() {
            addCriterion("start_la is not null");
            return (Criteria) this;
        }

        public Criteria andStartLaEqualTo(BigDecimal value) {
            addCriterion("start_la =", value, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaNotEqualTo(BigDecimal value) {
            addCriterion("start_la <>", value, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaGreaterThan(BigDecimal value) {
            addCriterion("start_la >", value, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("start_la >=", value, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaLessThan(BigDecimal value) {
            addCriterion("start_la <", value, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("start_la <=", value, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaIn(List<BigDecimal> values) {
            addCriterion("start_la in", values, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaNotIn(List<BigDecimal> values) {
            addCriterion("start_la not in", values, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_la between", value1, value2, "startLa");
            return (Criteria) this;
        }

        public Criteria andStartLaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_la not between", value1, value2, "startLa");
            return (Criteria) this;
        }

        public Criteria andEndLoIsNull() {
            addCriterion("end_lo is null");
            return (Criteria) this;
        }

        public Criteria andEndLoIsNotNull() {
            addCriterion("end_lo is not null");
            return (Criteria) this;
        }

        public Criteria andEndLoEqualTo(BigDecimal value) {
            addCriterion("end_lo =", value, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoNotEqualTo(BigDecimal value) {
            addCriterion("end_lo <>", value, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoGreaterThan(BigDecimal value) {
            addCriterion("end_lo >", value, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_lo >=", value, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoLessThan(BigDecimal value) {
            addCriterion("end_lo <", value, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_lo <=", value, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoIn(List<BigDecimal> values) {
            addCriterion("end_lo in", values, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoNotIn(List<BigDecimal> values) {
            addCriterion("end_lo not in", values, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_lo between", value1, value2, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_lo not between", value1, value2, "endLo");
            return (Criteria) this;
        }

        public Criteria andEndLaIsNull() {
            addCriterion("end_la is null");
            return (Criteria) this;
        }

        public Criteria andEndLaIsNotNull() {
            addCriterion("end_la is not null");
            return (Criteria) this;
        }

        public Criteria andEndLaEqualTo(BigDecimal value) {
            addCriterion("end_la =", value, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaNotEqualTo(BigDecimal value) {
            addCriterion("end_la <>", value, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaGreaterThan(BigDecimal value) {
            addCriterion("end_la >", value, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_la >=", value, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaLessThan(BigDecimal value) {
            addCriterion("end_la <", value, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_la <=", value, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaIn(List<BigDecimal> values) {
            addCriterion("end_la in", values, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaNotIn(List<BigDecimal> values) {
            addCriterion("end_la not in", values, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_la between", value1, value2, "endLa");
            return (Criteria) this;
        }

        public Criteria andEndLaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_la not between", value1, value2, "endLa");
            return (Criteria) this;
        }

        public Criteria andPointOthersIsNull() {
            addCriterion("point_others is null");
            return (Criteria) this;
        }

        public Criteria andPointOthersIsNotNull() {
            addCriterion("point_others is not null");
            return (Criteria) this;
        }

        public Criteria andPointOthersEqualTo(String value) {
            addCriterion("point_others =", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersNotEqualTo(String value) {
            addCriterion("point_others <>", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersGreaterThan(String value) {
            addCriterion("point_others >", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersGreaterThanOrEqualTo(String value) {
            addCriterion("point_others >=", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersLessThan(String value) {
            addCriterion("point_others <", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersLessThanOrEqualTo(String value) {
            addCriterion("point_others <=", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersLike(String value) {
            addCriterion("point_others like", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersNotLike(String value) {
            addCriterion("point_others not like", value, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersIn(List<String> values) {
            addCriterion("point_others in", values, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersNotIn(List<String> values) {
            addCriterion("point_others not in", values, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersBetween(String value1, String value2) {
            addCriterion("point_others between", value1, value2, "pointOthers");
            return (Criteria) this;
        }

        public Criteria andPointOthersNotBetween(String value1, String value2) {
            addCriterion("point_others not between", value1, value2, "pointOthers");
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