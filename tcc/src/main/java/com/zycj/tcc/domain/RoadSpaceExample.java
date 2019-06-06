package com.zycj.tcc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoadSpaceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoadSpaceExample() {
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

        public Criteria andSpaceStatusIsNull() {
            addCriterion("space_status is null");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusIsNotNull() {
            addCriterion("space_status is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusEqualTo(Integer value) {
            addCriterion("space_status =", value, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusNotEqualTo(Integer value) {
            addCriterion("space_status <>", value, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusGreaterThan(Integer value) {
            addCriterion("space_status >", value, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("space_status >=", value, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusLessThan(Integer value) {
            addCriterion("space_status <", value, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("space_status <=", value, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusIn(List<Integer> values) {
            addCriterion("space_status in", values, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusNotIn(List<Integer> values) {
            addCriterion("space_status not in", values, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusBetween(Integer value1, Integer value2) {
            addCriterion("space_status between", value1, value2, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("space_status not between", value1, value2, "spaceStatus");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNull() {
            addCriterion("space_type is null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNotNull() {
            addCriterion("space_type is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeEqualTo(Integer value) {
            addCriterion("space_type =", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotEqualTo(Integer value) {
            addCriterion("space_type <>", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThan(Integer value) {
            addCriterion("space_type >", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("space_type >=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThan(Integer value) {
            addCriterion("space_type <", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("space_type <=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIn(List<Integer> values) {
            addCriterion("space_type in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotIn(List<Integer> values) {
            addCriterion("space_type not in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeBetween(Integer value1, Integer value2) {
            addCriterion("space_type between", value1, value2, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("space_type not between", value1, value2, "spaceType");
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

        public Criteria andIsParkedIsNull() {
            addCriterion("is_parked is null");
            return (Criteria) this;
        }

        public Criteria andIsParkedIsNotNull() {
            addCriterion("is_parked is not null");
            return (Criteria) this;
        }

        public Criteria andIsParkedEqualTo(Integer value) {
            addCriterion("is_parked =", value, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedNotEqualTo(Integer value) {
            addCriterion("is_parked <>", value, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedGreaterThan(Integer value) {
            addCriterion("is_parked >", value, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_parked >=", value, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedLessThan(Integer value) {
            addCriterion("is_parked <", value, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedLessThanOrEqualTo(Integer value) {
            addCriterion("is_parked <=", value, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedIn(List<Integer> values) {
            addCriterion("is_parked in", values, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedNotIn(List<Integer> values) {
            addCriterion("is_parked not in", values, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedBetween(Integer value1, Integer value2) {
            addCriterion("is_parked between", value1, value2, "isParked");
            return (Criteria) this;
        }

        public Criteria andIsParkedNotBetween(Integer value1, Integer value2) {
            addCriterion("is_parked not between", value1, value2, "isParked");
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

        public Criteria andParkTimeIsNull() {
            addCriterion("park_time is null");
            return (Criteria) this;
        }

        public Criteria andParkTimeIsNotNull() {
            addCriterion("park_time is not null");
            return (Criteria) this;
        }

        public Criteria andParkTimeEqualTo(Date value) {
            addCriterion("park_time =", value, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeNotEqualTo(Date value) {
            addCriterion("park_time <>", value, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeGreaterThan(Date value) {
            addCriterion("park_time >", value, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("park_time >=", value, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeLessThan(Date value) {
            addCriterion("park_time <", value, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeLessThanOrEqualTo(Date value) {
            addCriterion("park_time <=", value, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeIn(List<Date> values) {
            addCriterion("park_time in", values, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeNotIn(List<Date> values) {
            addCriterion("park_time not in", values, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeBetween(Date value1, Date value2) {
            addCriterion("park_time between", value1, value2, "parkTime");
            return (Criteria) this;
        }

        public Criteria andParkTimeNotBetween(Date value1, Date value2) {
            addCriterion("park_time not between", value1, value2, "parkTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeIsNull() {
            addCriterion("drive_time is null");
            return (Criteria) this;
        }

        public Criteria andDriveTimeIsNotNull() {
            addCriterion("drive_time is not null");
            return (Criteria) this;
        }

        public Criteria andDriveTimeEqualTo(Date value) {
            addCriterion("drive_time =", value, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeNotEqualTo(Date value) {
            addCriterion("drive_time <>", value, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeGreaterThan(Date value) {
            addCriterion("drive_time >", value, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("drive_time >=", value, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeLessThan(Date value) {
            addCriterion("drive_time <", value, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeLessThanOrEqualTo(Date value) {
            addCriterion("drive_time <=", value, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeIn(List<Date> values) {
            addCriterion("drive_time in", values, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeNotIn(List<Date> values) {
            addCriterion("drive_time not in", values, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeBetween(Date value1, Date value2) {
            addCriterion("drive_time between", value1, value2, "driveTime");
            return (Criteria) this;
        }

        public Criteria andDriveTimeNotBetween(Date value1, Date value2) {
            addCriterion("drive_time not between", value1, value2, "driveTime");
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