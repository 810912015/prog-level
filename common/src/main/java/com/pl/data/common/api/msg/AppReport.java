package com.pl.data.common.api.msg;

/**
 * 他人定义的上报结构--暂未使用
 */
public class AppReport{
    /**
     *
     Field
     Type
     Description
     app_id
     string
     App Code 版本号
     app_name
     string
     应用名称
     app_version
     string
     应用版本号
     package_name
     string
     应用包名
     sdk_channel
     string
     应用渠道号
     sdk_sub_channel
     string
     应用子渠道号
     sdk_version
     string
     Sdk版本号
     app_ext
     string
     扩展预留
     */
    public static class App{
        private String app_ext ;
        public String getApp_ext(){
            return this.app_ext;
        }
        public void setApp_ext(String app_ext){
            this.app_ext=app_ext;
        }
        private String app_name ;
        public String getApp_name(){
            return this.app_name;
        }
        public void setApp_name(String app_name){
            this.app_name=app_name;
        }
        private String sdk_sub_channel ;
        public String getSdk_sub_channel(){
            return this.sdk_sub_channel;
        }
        public void setSdk_sub_channel(String sdk_sub_channel){
            this.sdk_sub_channel=sdk_sub_channel;
        }
        private String app_version ;
        public String getApp_version(){
            return this.app_version;
        }
        public void setApp_version(String app_version){
            this.app_version=app_version;
        }
        private String package_name ;
        public String getPackage_name(){
            return this.package_name;
        }
        public void setPackage_name(String package_name){
            this.package_name=package_name;
        }
        private String sdk_version ;
        public String getSdk_version(){
            return this.sdk_version;
        }
        public void setSdk_version(String sdk_version){
            this.sdk_version=sdk_version;
        }
        private String sdk_channel ;
        public String getSdk_channel(){
            return this.sdk_channel;
        }
        public void setSdk_channel(String sdk_channel){
            this.sdk_channel=sdk_channel;
        }
        private Integer app_id ;
        public Integer getApp_id(){
            return this.app_id;
        }
        public void setApp_id(Integer app_id){
            this.app_id=app_id;
        }
    }

    /**
     *
     Field
     Type
     Description
     os_type
     int
     OSType {OS_UNKNOWN = 0; OS_IOS = 1;OS_ANDROID = 2; OS_WP = 3;}
     device_type
     int
     DeviceType {TABLET = 1;PHONE = 2}
     screen_width
     int
     屏幕宽
     screen_height
     int
     屏幕高
     os_version
     string
     os 版本号
     model
     string
     设备型号
     display
     string
     软件ROM版本号
     product
     string
     厂商
     device
     string
     设备
     board
     string
     主板
     manufacturer
     string
     制造商
     brand
     string
     商标
     hardware
     string
     硬件
     imei
     string
     IMEI
     total_ram
     string
     总RAM大小
     total_rom
     string
     总ROM大小
     left_ram
     string
     剩余RAM大小
     left_rom
     string
     剩余ROM大小
     androidid
     string
     android id
     mac
     string
     Mac地址
     */
    public static class Device{
        private String product ;
        public String getProduct(){
            return this.product;
        }
        public void setProduct(String product){
            this.product=product;
        }
        private Integer screen_width ;
        public Integer getScreen_width(){
            return this.screen_width;
        }
        public void setScreen_width(Integer screen_width){
            this.screen_width=screen_width;
        }
        private String display ;
        public String getDisplay(){
            return this.display;
        }
        public void setDisplay(String display){
            this.display=display;
        }
        private Integer os_version ;
        public Integer getOs_version(){
            return this.os_version;
        }
        public void setOs_version(Integer os_version){
            this.os_version=os_version;
        }
        private String total_rom ;
        public String getTotal_rom(){
            return this.total_rom;
        }
        public void setTotal_rom(String total_rom){
            this.total_rom=total_rom;
        }
        private Integer device_type ;
        public Integer getDevice_type(){
            return this.device_type;
        }
        public void setDevice_type(Integer device_type){
            this.device_type=device_type;
        }
        private String left_rom ;
        public String getLeft_rom(){
            return this.left_rom;
        }
        public void setLeft_rom(String left_rom){
            this.left_rom=left_rom;
        }
        private String mac ;
        public String getMac(){
            return this.mac;
        }
        public void setMac(String mac){
            this.mac=mac;
        }
        private String manufacturer ;
        public String getManufacturer(){
            return this.manufacturer;
        }
        public void setManufacturer(String manufacturer){
            this.manufacturer=manufacturer;
        }
        private Integer screen_height ;
        public Integer getScreen_height(){
            return this.screen_height;
        }
        public void setScreen_height(Integer screen_height){
            this.screen_height=screen_height;
        }
        private String left_ram ;
        public String getLeft_ram(){
            return this.left_ram;
        }
        public void setLeft_ram(String left_ram){
            this.left_ram=left_ram;
        }
        private Integer os_type ;
        public Integer getOs_type(){
            return this.os_type;
        }
        public void setOs_type(Integer os_type){
            this.os_type=os_type;
        }
        private String imei ;
        public String getImei(){
            return this.imei;
        }
        public void setImei(String imei){
            this.imei=imei;
        }
        private String model ;
        public String getModel(){
            return this.model;
        }
        public void setModel(String model){
            this.model=model;
        }
        private String brand ;
        public String getBrand(){
            return this.brand;
        }
        public void setBrand(String brand){
            this.brand=brand;
        }
        private String device ;
        public String getDevice(){
            return this.device;
        }
        public void setDevice(String device){
            this.device=device;
        }
        private String androidid ;
        public String getAndroidid(){
            return this.androidid;
        }
        public void setAndroidid(String androidid){
            this.androidid=androidid;
        }
        private String board ;
        public String getBoard(){
            return this.board;
        }
        public void setBoard(String board){
            this.board=board;
        }
        private String hardware ;
        public String getHardware(){
            return this.hardware;
        }
        public void setHardware(String hardware){
            this.hardware=hardware;
        }
        private String total_ram ;
        public String getTotal_ram(){
            return this.total_ram;
        }
        public void setTotal_ram(String total_ram){
            this.total_ram=total_ram;
        }
    }

    /**
     *
     Field
     Type
     Description
     imsi
     string
     IMSI
     networktype
     int
     网络连接类型-1:unknow 1:wifi,2:2G,3:3G,4:4G
     networksubtype
     int
     网络连接子类型
     lang
     string
     语言
     longitude
     string
     用户位置，经度
     latitude
     string
     用户位置，纬度
     age
     int
     用户年龄
     carrierId
     string
     运营商id
     cellid
     string
     Sim id
     gender
     string
     用户性别
     iccid
     string
     Sim iccid
     orientation
     Int
     屏幕方向-0:未知 1:竖屏 2:横屏
     applist
     JsonArray
     用户已安装应用列表
     */
    public static class User{
        private Integer orientation ;
        public Integer getOrientation(){
            return this.orientation;
        }
        public void setOrientation(Integer orientation){
            this.orientation=orientation;
        }
        private String gender ;
        public String getGender(){
            return this.gender;
        }
        public void setGender(String gender){
            this.gender=gender;
        }
        private Integer networksubtype ;
        public Integer getNetworksubtype(){
            return this.networksubtype;
        }
        public void setNetworksubtype(Integer networksubtype){
            this.networksubtype=networksubtype;
        }
        private Float latitude ;
        public Float getLatitude(){
            return this.latitude;
        }
        public void setLatitude(Float latitude){
            this.latitude=latitude;
        }
        private String imsi ;
        public String getImsi(){
            return this.imsi;
        }
        public void setImsi(String imsi){
            this.imsi=imsi;
        }
        private Integer networktype ;
        public Integer getNetworktype(){
            return this.networktype;
        }
        public void setNetworktype(Integer networktype){
            this.networktype=networktype;
        }
        private String cellid ;
        public String getCellid(){
            return this.cellid;
        }
        public void setCellid(String cellid){
            this.cellid=cellid;
        }
        private String iccid ;
        public String getIccid(){
            return this.iccid;
        }
        public void setIccid(String iccid){
            this.iccid=iccid;
        }
        private String carrierId ;
        public String getCarrierId(){
            return this.carrierId;
        }
        public void setCarrierId(String carrierId){
            this.carrierId=carrierId;
        }
        private String lang ;
        public String getLang(){
            return this.lang;
        }
        public void setLang(String lang){
            this.lang=lang;
        }
        private Integer age ;
        public Integer getAge(){
            return this.age;
        }
        public void setAge(Integer age){
            this.age=age;
        }
        private Float longitude ;
        public Float getLongitude(){
            return this.longitude;
        }
        public void setLongitude(Float longitude){
            this.longitude=longitude;
        }
    }

    /**
     * Field
     * Type
     * Description
     * ad_id
     * string
     * 广告位ID
     * adsrcid
     * string
     * 广告源ID
     * book_id
     * string
     * 书籍ID
     * chapterId
     * string
     * 章节ID
     * errorMsg
     * string
     * 错误信息
     * event_id
     * int
     * 统计信息ID(1:启动应用2:书籍展示等)
     */
    public static class Eventtrackinfo{
        private String ad_id ;
        public String getAd_id(){
            return this.ad_id;
        }
        public void setAd_id(String ad_id){
            this.ad_id=ad_id;
        }
        private Integer event_id ;
        public Integer getEvent_id(){
            return this.event_id;
        }
        public void setEvent_id(Integer event_id){
            this.event_id=event_id;
        }
        private Integer adsrcid ;
        public Integer getAdsrcid(){
            return this.adsrcid;
        }
        public void setAdsrcid(Integer adsrcid){
            this.adsrcid=adsrcid;
        }
        private String chapterId ;
        public String getChapterId(){
            return this.chapterId;
        }
        public void setChapterId(String chapterId){
            this.chapterId=chapterId;
        }
        private String book_id ;
        public String getBook_id(){
            return this.book_id;
        }
        public void setBook_id(String book_id){
            this.book_id=book_id;
        }
        private String errorMsg ;
        public String getErrorMsg(){
            return this.errorMsg;
        }
        public void setErrorMsg(String errorMsg){
            this.errorMsg=errorMsg;
        }
    }
    private App app ;
    public App getApp(){
        return this.app;
    }
    public void setApp(App app){
        this.app=app;
    }
    private Integer Action ;
    public Integer getAction(){
        return this.Action;
    }
    public void setAction(Integer Action){
        this.Action=Action;
    }
    private Device device ;
    public Device getDevice(){
        return this.device;
    }
    public void setDevice(Device device){
        this.device=device;
    }
    private User user ;
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user=user;
    }
    private Eventtrackinfo eventtrackinfo ;
    public Eventtrackinfo getEventtrackinfo(){
        return this.eventtrackinfo;
    }
    public void setEventtrackinfo(Eventtrackinfo eventtrackinfo){
        this.eventtrackinfo=eventtrackinfo;
    }
}
