package com.zycj.tcc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceSettingExample() {
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

        public Criteria andInvoiceStartIsNull() {
            addCriterion("invoice_start is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartIsNotNull() {
            addCriterion("invoice_start is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartEqualTo(String value) {
            addCriterion("invoice_start =", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartNotEqualTo(String value) {
            addCriterion("invoice_start <>", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartGreaterThan(String value) {
            addCriterion("invoice_start >", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_start >=", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartLessThan(String value) {
            addCriterion("invoice_start <", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartLessThanOrEqualTo(String value) {
            addCriterion("invoice_start <=", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartLike(String value) {
            addCriterion("invoice_start like", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartNotLike(String value) {
            addCriterion("invoice_start not like", value, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartIn(List<String> values) {
            addCriterion("invoice_start in", values, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartNotIn(List<String> values) {
            addCriterion("invoice_start not in", values, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartBetween(String value1, String value2) {
            addCriterion("invoice_start between", value1, value2, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceStartNotBetween(String value1, String value2) {
            addCriterion("invoice_start not between", value1, value2, "invoiceStart");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndIsNull() {
            addCriterion("invoice_end is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndIsNotNull() {
            addCriterion("invoice_end is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndEqualTo(String value) {
            addCriterion("invoice_end =", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndNotEqualTo(String value) {
            addCriterion("invoice_end <>", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndGreaterThan(String value) {
            addCriterion("invoice_end >", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_end >=", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndLessThan(String value) {
            addCriterion("invoice_end <", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndLessThanOrEqualTo(String value) {
            addCriterion("invoice_end <=", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndLike(String value) {
            addCriterion("invoice_end like", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndNotLike(String value) {
            addCriterion("invoice_end not like", value, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndIn(List<String> values) {
            addCriterion("invoice_end in", values, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndNotIn(List<String> values) {
            addCriterion("invoice_end not in", values, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndBetween(String value1, String value2) {
            addCriterion("invoice_end between", value1, value2, "invoiceEnd");
            return (Criteria) this;
        }

        public Criteria andInvoiceEndNotBetween(String value1, String value2) {
            addCriterion("invoice_end not between", value1, value2, "invoiceEnd");
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

        public Criteria andSetTimeIsNull() {
            addCriterion("set_time is null");
            return (Criteria) this;
        }

        public Criteria andSetTimeIsNotNull() {
            addCriterion("set_time is not null");
            return (Criteria) this;
        }

        public Criteria andSetTimeEqualTo(Date value) {
            addCriterion("set_time =", value, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeNotEqualTo(Date value) {
            addCriterion("set_time <>", value, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeGreaterThan(Date value) {
            addCriterion("set_time >", value, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("set_time >=", value, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeLessThan(Date value) {
            addCriterion("set_time <", value, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeLessThanOrEqualTo(Date value) {
            addCriterion("set_time <=", value, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeIn(List<Date> values) {
            addCriterion("set_time in", values, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeNotIn(List<Date> values) {
            addCriterion("set_time not in", values, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeBetween(Date value1, Date value2) {
            addCriterion("set_time between", value1, value2, "setTime");
            return (Criteria) this;
        }

        public Criteria andSetTimeNotBetween(Date value1, Date value2) {
            addCriterion("set_time not between", value1, value2, "setTime");
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