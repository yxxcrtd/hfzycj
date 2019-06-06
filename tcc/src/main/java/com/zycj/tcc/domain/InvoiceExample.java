package com.zycj.tcc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceExample() {
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

        public Criteria andInvoiceNameIsNull() {
            addCriterion("invoice_name is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIsNotNull() {
            addCriterion("invoice_name is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameEqualTo(String value) {
            addCriterion("invoice_name =", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotEqualTo(String value) {
            addCriterion("invoice_name <>", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThan(String value) {
            addCriterion("invoice_name >", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_name >=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThan(String value) {
            addCriterion("invoice_name <", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThanOrEqualTo(String value) {
            addCriterion("invoice_name <=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLike(String value) {
            addCriterion("invoice_name like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotLike(String value) {
            addCriterion("invoice_name not like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIn(List<String> values) {
            addCriterion("invoice_name in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotIn(List<String> values) {
            addCriterion("invoice_name not in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameBetween(String value1, String value2) {
            addCriterion("invoice_name between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotBetween(String value1, String value2) {
            addCriterion("invoice_name not between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollIsNull() {
            addCriterion("invoice_roll is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollIsNotNull() {
            addCriterion("invoice_roll is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollEqualTo(Integer value) {
            addCriterion("invoice_roll =", value, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollNotEqualTo(Integer value) {
            addCriterion("invoice_roll <>", value, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollGreaterThan(Integer value) {
            addCriterion("invoice_roll >", value, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_roll >=", value, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollLessThan(Integer value) {
            addCriterion("invoice_roll <", value, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_roll <=", value, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollIn(List<Integer> values) {
            addCriterion("invoice_roll in", values, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollNotIn(List<Integer> values) {
            addCriterion("invoice_roll not in", values, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollBetween(Integer value1, Integer value2) {
            addCriterion("invoice_roll between", value1, value2, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andInvoiceRollNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_roll not between", value1, value2, "invoiceRoll");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
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

        public Criteria andGetTimeIsNull() {
            addCriterion("get_time is null");
            return (Criteria) this;
        }

        public Criteria andGetTimeIsNotNull() {
            addCriterion("get_time is not null");
            return (Criteria) this;
        }

        public Criteria andGetTimeEqualTo(Date value) {
            addCriterion("get_time =", value, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeNotEqualTo(Date value) {
            addCriterion("get_time <>", value, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeGreaterThan(Date value) {
            addCriterion("get_time >", value, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("get_time >=", value, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeLessThan(Date value) {
            addCriterion("get_time <", value, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeLessThanOrEqualTo(Date value) {
            addCriterion("get_time <=", value, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeIn(List<Date> values) {
            addCriterion("get_time in", values, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeNotIn(List<Date> values) {
            addCriterion("get_time not in", values, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeBetween(Date value1, Date value2) {
            addCriterion("get_time between", value1, value2, "getTime");
            return (Criteria) this;
        }

        public Criteria andGetTimeNotBetween(Date value1, Date value2) {
            addCriterion("get_time not between", value1, value2, "getTime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andGiverIsNull() {
            addCriterion("giver is null");
            return (Criteria) this;
        }

        public Criteria andGiverIsNotNull() {
            addCriterion("giver is not null");
            return (Criteria) this;
        }

        public Criteria andGiverEqualTo(String value) {
            addCriterion("giver =", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverNotEqualTo(String value) {
            addCriterion("giver <>", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverGreaterThan(String value) {
            addCriterion("giver >", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverGreaterThanOrEqualTo(String value) {
            addCriterion("giver >=", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverLessThan(String value) {
            addCriterion("giver <", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverLessThanOrEqualTo(String value) {
            addCriterion("giver <=", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverLike(String value) {
            addCriterion("giver like", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverNotLike(String value) {
            addCriterion("giver not like", value, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverIn(List<String> values) {
            addCriterion("giver in", values, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverNotIn(List<String> values) {
            addCriterion("giver not in", values, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverBetween(String value1, String value2) {
            addCriterion("giver between", value1, value2, "giver");
            return (Criteria) this;
        }

        public Criteria andGiverNotBetween(String value1, String value2) {
            addCriterion("giver not between", value1, value2, "giver");
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