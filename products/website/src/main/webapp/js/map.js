//定义地图
var map;
var centerLng;//经度中心
var centerLat;//纬度中心
var myLng = 117.2119;//经度
var myLat = 31.8413;//纬度
var marker;//标注
var center;
var placeSearch;
var autocomplete;
var currentCity = "";
var fltname = "";
// var keywords=$("#keyword").val();
// var autoplaceSearch;
// var centerText = '当前中心点坐标：' + center.getLng() + ',' + center.getLat();
//加载地图
function initMap() {
    var point = new AMap.LngLat(myLng, myLat);
    // mapObj = new AMap.Map("iCenter");
    map = new AMap.Map('container', {
        // center: [117.000923, 36.675807],
        // zoom: 18
        center: point, //地图中心点
        level: 12  //地图显示的缩放级别
    });


    // center = map.getCenter();
    // map.plugin(["AMap.ToolBar"], function () {
    //     map.addControl(new AMap.ToolBar());
    // });
    AMap.event.addListener(map, 'click', getLnglat); //点击事件
    //添加搜索输入提示
    var autoOptions = {
        // city: "010", //城市，默认全国
        input: "keyword"
    };
    //TODO 自动搜索提示
    autocomplete = new AMap.Autocomplete(autoOptions);
    // autocomplete.search($("#keyword").val(), function(status, result){
    //     //TODO:开发者使用result自己进行下拉列表的显示与交互功能
    //     console.info("开发者使用result自己进行下拉列表的显示与交互功能");
    // });
    // AMap.event.addListener(autocomplete, "complete", autokeywordSearch_CallBack);//返回地点查询结果
    AMap.event.addListener(autocomplete, "select", select);//注册监听，当选中某条记录时会触发
    //实例搜索类
    placeSearch = new AMap.PlaceSearch({
        map: map
    });
    // autoplaceSearch = new AMap.PlaceSearch({
    //     map: map
    // });
    //构造地点查询类
    AMap.event.addListener(placeSearch, "complete", keywordSearch_CallBack);//返回地点查询结果

    // AMap.event.addListener(placeSearch, "select", select);//注册监听，当选中某条记录时会触发
    // map.clearMap();//清除地图上全部覆盖物，用：
    // 添加事件监听, 使地图自适应显示到合适的范围
    // AMap.event.addDomListener(map, 'click', function() {
    //     var newCenter = map.setFitView();
    //     document.getElementById('maptip').innerHTML = '当前中心点坐标：' + newCenter.getCenter();
    //     // document.getElementById('tips').innerHTML = '通过setFitView，地图自适应显示到合适的范围内,点标记已全部显示在视野中！';
    // });

    // placeSearch = new AMap.PlaceSearch({
    //     map: map
    // });
    // //构造地点查询类
    // AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    // AMap.event.addListener(marker, 'dragging', function(e){
    //     var lat = e.lnglat.lat,
    //         lng = e.lnglat.lng;
    //     marker.setPosition(new AMap.LngLat(lng,lat));
    //
    // });
}

function select(e) {
    placeSearch.setCity(e.poi.adcode);
    placeSearch.search(e.poi.name);  //关键字查询查询
}
function select2(code, name) {
    // console.info("select2.poi.adcode=" + e.poi.adcode);
    // console.info("select2.poi.name=" + e.poi.name);
    console.info("select2==========code=" + code + ",name=" + name);
    document.getElementById('keyword').value = "";
    document.getElementById('poiLists').innerHTML = "";
    $("#poiLists").css('display', 'none');
    placeSearch.setCity(code);
    placeSearch.search(name);  //关键字查询查询
}

// poi搜索获取停车场信息
function placeSearch2() {  //POI搜索，关键字查询
    // $("#keyword").val("a");
    // autocomplete.search($("#keyword").val());
    // return;
    var keyword;
    keyword = document.getElementById("keyword").value;
    if (keyword == "")
        return;
    map.clearMap();
    var MSearch;
    document.getElementById('poiLists').innerHTML = "";
    $("#poiLists").css('display', 'none');


    console.info(keyword);
    console.info("placeSearch2==========keyword=" + keyword);
    document.getElementById('maptip').innerHTML = "关键字查询您输入的是：" + keyword;
    // AMap.event.addListener(autocomplete, "complete", autokeywordSearch_CallBack);//返回地点查询结果
    // autocomplete.search(keyword); //关键字查询
    AMap.plugin(["AMap.PlaceSearch"], function () {    //构造地点查询类
        MSearch = new AMap.PlaceSearch({
            pageSize: 10,
            pageIndex: 1,
            city: currentCity, //城市
            // city: '合肥', //城市
            map: map
            // panel: "poiLists"
        });
        AMap.event.addListener(MSearch, "complete", autokeywordSearch_CallBack);//返回地点查询结果
        MSearch.search(keyword); //关键字查询
    });
}


function autokeywordSearch_CallBack(data) {
    // var poiArr = data.poiList.tips;//data.poiList.pois;
    var poiArr = data.poiList.pois;//data.poiList.pois;
    if (poiArr == "" || typeof(poiArr) == "undefined" || poiArr == undefined || poiArr == "undefined") {
        console.info("poiArr=" + poiArr);
        return;
    }
    var lngX = poiArr[0].location.getLng();
    var latY = poiArr[0].location.getLat();
    centerLng = lngX;//经度
    centerLat = latY;//纬度
    map.setCenter(new AMap.LngLat(lngX, latY));
    document.getElementById('maptip').innerHTML = "关键字查询您输入的是：" + keyword + "lngX:" + lngX + ",latY:" + latY;
    console.info(document.getElementById('maptip').innerHTML);
    // poiLists
    var Htmls = "";
    for (var i = 0; i < poiArr.length; i++) {
        // if(poiArr[i].adcode!="" && poiArr[i].name!="") {
        // Htmls+=poiArr[i].name+"<br />";
        Htmls += "<div class='auto-item' id='amap-sug" + i + "'";
        Htmls += " onclick=select2(";
        Htmls += "'";
        Htmls += poiArr[i].adcode;
        Htmls += "'";
        Htmls += ",";
        Htmls += "'";
        Htmls += poiArr[i].name;
        Htmls += "'";
        Htmls += ")";
        Htmls += ">";
        Htmls += poiArr[i].name;
        Htmls += " <span class='auto-item-span'>" + poiArr[i].address + "</span></div>";
        // }
        // var jsondata = jQuery.parseJSON(poiArr[i]);
        // placeSearch.setCity(e.poi.adcode);
        // placeSearch.search(e.poi.name);  //关键字查询查询
        console.info("name=" + poiArr[i].name + ",poiArr[i].citycode=" + poiArr[i].citycode + ",poiArr[i].adcode=" + poiArr[i].adcode);
    }
    console.info("===========Htmls=" + Htmls);

    document.getElementById('poiLists').innerHTML = Htmls;
    $("#poiLists").css('display', 'block');
    // $("#poiLists").innerHTML=Htmls;
}


//回调函数
function keywordSearch_CallBack(data) {
    // var poiArr = data.poiList.tips;//data.poiList.pois;
    var poiArr = data.poiList.pois;//data.poiList.pois;
    if (poiArr == "" || typeof(poiArr) == "undefined" || poiArr == undefined || poiArr == "undefined") {
        console.info("poiArr=" + poiArr);
        return;
    }
    var lngX = poiArr[0].location.getLng();
    var latY = poiArr[0].location.getLat();
    centerLng = lngX;//经度
    centerLat = latY;//纬度
    map.setCenter(new AMap.LngLat(lngX, latY));
    document.getElementById('maptip').innerHTML = "关键字查询您输入的是：" + keyword + "lngX:" + lngX + ",latY:" + latY;
    console.info(document.getElementById('maptip').innerHTML);
    getParkInfo();
    // $("#poiLists").css('display', 'none');
    // $("#poiLists").innerHTML=Htmls;
}

// 根据当前坐标获取停车场信息
function getParkInfo() {
    console.info("getParkInfo>>清除地图上的覆盖物")
    map.clearMap();
    console.info("getParkInfo>>开始调用获取停车数据接口")
    console.info("centerLng=" + centerLng + ",centerLat" + centerLat);
    $.ajax({
        type: "GET",  //提交方式POST
        url: "/weixin/map/parkInfo",//路径
        data: {
            "longitude": centerLng,
            "latitude": centerLat,
            // "longitude": 117.280157,
            // "latitude": 31.861038,
            "myLng": myLng,
            "myLat": myLat,
            "distance": 500
        },//数据，这里使用的是Json格式进行传输//distance
        success: function (result) {//返回数据根据结果进行相应的处理
            getParkInfo_success(result);
        },
        error: function (result) {//返回数据根据结果进行相应的处理
            console.info("getParkInfo()error:" + result);
        }
    });
}

//获取数据成功后加点
function getParkInfo_success(result) {
    var jsondata = jQuery.parseJSON(result).deptList;
    // console.info(jsondata);
    // for(var i = 0, marker; i < jsondata.length; i++){
    for (var i = 0; i < jsondata.length; i++) {
        //alert(jsondata[i]["parkName"]);
        // console.info(jsondata[i]["longitude"]);
        // console.info(jsondata[i]["latitude"]);
        var parkName = jsondata[i]["parkName"];
        var parkNow = jsondata[i]["parkNow"];
        var parkTotal = jsondata[i]["parkTotal"];
        marker = new AMap.Marker({
            icon: "/img/mark_icon.png",
            position: [jsondata[i]["longitude"], jsondata[i]["latitude"]],
            draggable: true, //点标记可拖拽
            // position: map.gaode.map.getCenter(),
            // cursor:'move',   //鼠标悬停点标记时的鼠标样式
            raiseOnDrag: true, //鼠标拖拽点标记时开启点标记离开地图的效果
            map: map
        });
        marker.setLabel({
            offset: new AMap.Pixel(3, 14),//修改label相对于maker的位置
            content: parkNow
            //content: "(" + parkNow + "/" + parkTotal + ") " + parkName// 点击打开高德地图导航
        });
        marker.setTitle(parkName);
        marker.on('click', function (e) {
            marker.markOnAMAP({
                name: parkName,
                position: marker.getPosition()
            })
        })
        // marker.content='我是第'+i+'个信息窗体的内容';
        // //给Marker绑定单击事件
        // marker.on('click', markerClick);
    }
    map.setFitView();
}

// 定位
function getgprs() {
    var geolocation;
    map.plugin('AMap.Geolocation', function () {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            maximumAge: 1000,  //缓存毫秒数。定位成功后，定位结果的保留时间。默认0。
            buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
            buttonOffset: new AMap.Pixel(20, 80),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            //buttonPosition:'RB'
        });
        //地图移动到当前位置？
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//定位成功后的回调函数返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //定位成功后的回调函数返回定位出错信息
    });
}

//解析定位结果
function onComplete(data) {
    myLng = data.position.getLng();
    myLat = data.position.getLat();

    centerLng = data.position.getLng();
    centerLat = data.position.getLat();
    ;
    console.info(data);
    console.info(data.addressComponent.city);

    // var centerLng;//经度中心
    // var centerLat;//纬度中心
    // var myLng=117.000923;//经度
    // var myLat=36.675807;//纬度
    //

    currentCity = data.addressComponent.city;
    var str = ['定位成功'];
    str.push('城市：' + currentCity);
    str.push('经度：' + data.position.getLng());
    str.push('纬度：' + data.position.getLat());
    str.push('精度：' + data.accuracy + ' 米');
    str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
    // document.getElementById('maptip').innerHTML = str.join('<br>');
    console.info(str);
    //根据坐标获取当前位置的停车场信息
    // getParkInfo();
}
//解析定位错误信息
function onError(data) {
    document.getElementById('maptip').innerHTML = '定位失败';
}

//实时路况图层
var trafficLayer;
function trafficLayers() {
    trafficLayer = new AMap.TileLayer.Traffic({zIndex: 10}); //实时路况图层
    trafficLayer.setMap(map);   //添加实时路况图层
    // trafficLayer.setMap(null);   //隐藏实时路况图层
    // trafficLayer.hide();   //隐藏实时路况图层
}
//鼠标点击，公共资源获取经纬度坐标回调
function getLnglat(e) {
    var x = e.lnglat.getLng();
    var y = e.lnglat.getLat();
    document.getElementById("X").value = x;
    document.getElementById("Y").value = y;
    // document.getElementById("communal_coordinate_x").value = x;
    // document.getElementById("communal_coordinate_y").value = y;
    if (fltname == "CommunalEdit.ftl") {
        $("#communal_coordinate_x").attr("value", x);
        $("#communal_coordinate_y").attr("value", y);
    }
    else {
        $("#park_coordinate_x").attr("value", x);
        $("#park_coordinate_y").attr("value", y);
    }
    // var centerText = '当前中心点坐标：' + center.getLng() + ',' + center.getLat();
    // document.getElementById("x").value = x + "," + y + ",中心坐标：" + centerText;
    // console.info(document.getElementById('maptip').innerHTML);
}
