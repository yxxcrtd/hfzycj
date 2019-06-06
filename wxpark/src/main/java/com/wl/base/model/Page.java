package com.wl.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 分页基本信息
 * @author changwt
 *
 */
public class Page{

	private static final int PAGE_SIZE=20;//默认每页显示20行
	private static final int PAGE_INDEX=1;//默认翻页索引从1开始
	
	public Page(){
		
	}
	/**
	 * 
	 * @param pageIndex 翻页索引
	 * @param pageSize  每页显示行数
	 * @param withTotalCount 是否查询总行数
	 */
	public Page(int pageIndex,int pageSize,boolean withTotalCount){
		if(pageIndex<1){
			this.pageIndex=PAGE_INDEX;
		}else{
			this.pageIndex=pageIndex;
		}
		if(pageSize<1){
			this.pageSize=PAGE_SIZE;
		}else{
			this.pageSize=pageSize;
		}
		//设置起始行,分页起始行=startRow+1
		this.startRow=(this.pageIndex-1)*this.pageSize;
		
		this.withTotalCount=withTotalCount;
	}
	public Page(int pageIndex,int pageSize){
		this(pageIndex, pageSize, true);
	}
	/**
	 * 当前页索引
	 */
	private int pageIndex;
	
	/**
	 * 每页显示行数
	 */
	private int pageSize;
	/**
	 * 总行数
	 */
	private int totalCount;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 返回的对象列表
	 */
	private List<Object> resultList=new ArrayList<Object>();
	/**
	 * 是否查询总行数,默认查询
	 */
	private boolean withTotalCount;
	/**
	 * 起始行
	 */
	private int startRow;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		if(pageIndex<1){
			this.pageIndex=PAGE_INDEX;
		}else{
			this.pageIndex = pageIndex;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<1){
			this.pageSize=PAGE_SIZE;
		}else{
			this.pageSize=pageSize;
		}
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return this.totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage=totalPage;
	}
	
	public List<Object> getResultList() {
		return this.resultList;
	}
	public boolean isWithTotalCount() {
		return withTotalCount;
	}
	public void setWithTotalCount(boolean withTotalCount) {
		this.withTotalCount = withTotalCount;
	}
	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}
	public int getStartRow() {
		return this.startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	/**
	 * 通用分页方法
	 * repeatitems=false的json格式--{total:1,page:1:records:1,rows:[{id:value11,sname:value12},{id:value21,sname:value22}]}
	 * repeatitems=true 的json格式--{total:1,page:1:records:1,rows:[{id:value11,cell:[{value12},{value13}]},{id:value21,cell:[{value22},{value23}]}]}
	 * 本方法的repeatitems=false
	 * 1、list中的对象必须包含id属性,此Id和jsonReader: {repeatitems : false,id: "0"} 中的id对应,并且repeatitems=false
	 * 2、colModel:[{name:'id',index:'id', width:55}] 中的name的值必须和list对象中的属性名一致
	 * 3、index中的值必须和数据库中的列名一致，此值为排序字段
	 * 4、此方法效率较低，推荐用repeatitems=true的json格式
	 */
	public Map<String,Object> getJQGridMap(){
		return formatJQGridMap(this.resultList);
	}
	/**
	 * 把list格式化成jqgrid的要显示的map
	 * repeatitems=true 的json格式--{total:1,page:1:records:1,rows:[{id:value11,cell:[{value12},{value13}]},{id:value21,cell:[{value22},{value23}]}]}
	 * forExample:
	 *  List<User> list=(List)this.baseService.queryForList("com.alx.kxb.system.user.model.User.selectAllUser", null);
	 *	List<User> list=(List)page.getResultList();
	 * 	List dataList=new ArrayList();
     *	for(int i=0;i<list.size();i++){
     *	User user=list.get(i);
     *	Map dataMap=new HashMap();
     *	dataMap.put("id", user.getId());
     *	dataMap.put("cell", new Object[]{
     *			user.getId(),
     *			user.getSname(),
     *			user.getSex(),
     *			user.getBirthday()
     *	});
     *	dataList.add(dataMap);
     *	}
     *	Map map=new HashMap();
     *	map.put("total", page.getTotalPage());
     *	map.put("page", page.getPageIndex());
     *	map.put("records",page.getTotalCount());
     *	map.put("rows", dataList);
	 */
	public Map<String,Object> formatJQGridMap(List<Object> list){
		Map<String,Object> map=new HashMap<String,Object>();
		//map.put("total",this.totalPage);
		map.put("page", this.pageIndex);
		//map.put("records",this.totalCount);
		map.put("rows", list);
		map.put("total",this.totalCount);
		return map;
	}
}
