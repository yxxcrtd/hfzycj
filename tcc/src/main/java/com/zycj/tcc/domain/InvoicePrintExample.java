package com.zycj.tcc.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoicePrintExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoicePrintExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andInvoiceAmountIsNull() {
            addCriterion("invoice_amount is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIsNotNull() {
            addCriterion("invoice_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountEqualTo(BigDecimal value) {
            addCriterion("invoice_amount =", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotEqualTo(BigDecimal value) {
            addCriterion("invoice_amount <>", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThan(BigDecimal value) {
            addCriterion("invoice_amount >", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_amount >=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThan(BigDecimal value) {
            addCriterion("invoice_amount <", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_amount <=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIn(List<BigDecimal> values) {
            addCriterion("invoice_amount in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotIn(List<BigDecimal> values) {
            addCriterion("invoice_amount not in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_amount between", value1, value2, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_amount not between", value1, value2, "invoiceAmount");
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

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andArrearIdIsNull() {
            addCriterion("arrear_id is null");
            return (Criteria) this;
        }

        public Criteria andArrearIdIsNotNull() {
            addCriterion("arrear_id is not null");
            return (Criteria) this;
        }

        public Criteria andArrearIdEqualTo(Integer value) {
            addCriterion("arrear_id =", value, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdNotEqualTo(Integer value) {
            addCriterion("arrear_id <>", value, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdGreaterThan(Integer value) {
            addCriterion("arrear_id >", value, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrear_id >=", value, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdLessThan(Integer value) {
            addCriterion("arrear_id <", value, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdLessThanOrEqualTo(Integer value) {
            addCriterion("arrear_id <=", value, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdIn(List<Integer> values) {
            addCriterion("arrear_id in", values, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdNotIn(List<Integer> values) {
            addCriterion("arrear_id not in", values, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdBetween(Integer value1, Integer value2) {
            addCriterion("arrear_id between", value1, value2, "arrearId");
            return (Criteria) this;
        }

        public Criteria andArrearIdNotBetween(Integer value1, Integer value2) {
            addCriterion("arrear_id not between", value1, value2, "arrearId");
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

        public Criteria andPrintTimesIsNull() {
            addCriterion("print_times is null");
            return (Criteria) this;
        }

        public Criteria andPrintTimesIsNotNull() {
            addCriterion("print_times is not null");
            return (Criteria) this;
        }

        public Criteria andPrintTimesEqualTo(Integer value) {
            addCriterion("print_times =", value, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesNotEqualTo(Integer value) {
            addCriterion("print_times <>", value, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesGreaterThan(Integer value) {
            addCriterion("print_times >", value, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("print_times >=", value, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesLessThan(Integer value) {
            addCriterion("print_times <", value, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesLessThanOrEqualTo(Integer value) {
            addCriterion("print_times <=", value, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesIn(List<Integer> values) {
            addCriterion("print_times in", values, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesNotIn(List<Integer> values) {
            addCriterion("print_times not in", values, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesBetween(Integer value1, Integer value2) {
            addCriterion("print_times between", value1, value2, "printTimes");
            return (Criteria) this;
        }

        public Criteria andPrintTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("print_times not between", value1, value2, "printTimes");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNull() {
            addCriterion("check_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("check_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Date value) {
            addCriterionForJDBCDate("check_date =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("check_date <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Date value) {
            addCriterionForJDBCDate("check_date >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_date >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Date value) {
            addCriterionForJDBCDate("check_date <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_date <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Date> values) {
            addCriterionForJDBCDate("check_date in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("check_date not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_date between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_date not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckIdIsNull() {
            addCriterion("check_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckIdIsNotNull() {
            addCriterion("check_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckIdEqualTo(Integer value) {
            addCriterion("check_id =", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotEqualTo(Integer value) {
            addCriterion("check_id <>", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdGreaterThan(Integer value) {
            addCriterion("check_id >", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_id >=", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdLessThan(Integer value) {
            addCriterion("check_id <", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdLessThanOrEqualTo(Integer value) {
            addCriterion("check_id <=", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdIn(List<Integer> values) {
            addCriterion("check_id in", values, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotIn(List<Integer> values) {
            addCriterion("check_id not in", values, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdBetween(Integer value1, Integer value2) {
            addCriterion("check_id between", value1, value2, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotBetween(Integer value1, Integer value2) {
            addCriterion("check_id not between", value1, value2, "checkId");
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