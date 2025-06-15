**作者:fzy**

**统一说明，作者:fzy**

-  *Srn全称StringResourceName，即该字段在英文strings.xml的值*
-  *这里的所有字段请到ui_set目录下寻找对应关系*


**主页配置**

说明：mainPageUiSet用于设置主页上有多少个项目

|字段|含义|备注|
|---|---|---|
| air  |空调|参考ui_set/MainAction下的字段|
| setting  |设置|参考ui_set/MainAction下的字段|
| drive_data  |驾驶数据|参考ui_set/MainAction下的字段|
| tire_info  |胎压信息|参考ui_set/MainAction下的字段|
| on_start  |安吉星|参考ui_set/MainAction下的字段|
| hybird  |混合动力|参考ui_set/MainAction下的字段|
| original_car_device  |原车设备|参考ui_set/MainAction下的字段|
| warning  |警告信息|参考ui_set/MainAction下的字段|
| amplifier  |功放|参考ui_set/MainAction下的字段|
| sync  |SYNC|参考ui_set/MainAction下的字段|
| panel_key  |面板按钮|参考ui_set/MainAction下的字段|
| mqb_hybrid  |MQB专用混合动力|参考ui_set/MainAction下的字段|


**空调信息**

说明：airPageUiSet用于控制空调信息页面的所有UI，如果该值为boolean类型，则仅当为true时才写，为false就不需要写。

|字段|含义|备注|
|---|---|---|
| frontArea  |表示前区空调设置||
| rearArea  |表示后区空调设置||
| isHaveRearArea  |表示是否显示后区空调||
| lineBtnAction  |参考ui_set/AirBtnAction下的字段|适用于AC、AUTO、ECO、DUAL、前窗除霜、后窗除霜等等|
| isAllBtnCanClick  |是否所有按钮可控|如有部分可控部分不可控，请联系fzy|
| isShowLeftWheel  |是否显示左边滚轮|一般用于显示左温度|
| isShowCenterWheel  |是否显示中间滚轮|一般用于显示室外温度|
| isShowRightWheel  |是否显示右边滚轮|一般用于显示右温度|
| isCanSetLeftTemp  |滚轮的值是否可被控制|具体表现会在滚轮上下方显示可点击按钮|
| isCanSetCenterTemp  |滚轮的值是否可被控制|具体表现会在滚轮上下方显示可点击按钮|
| isCanSetRightTemp  |滚轮的值是否可被控制|具体表现会在滚轮上下方显示可点击按钮|
| windMaxLevel  |最大风量||
| isShowSeatHeat  |是否显示座椅加热控件|如果设置为true，那么对应的seatHeatSrnArray必须要同步设置|
| isShowSeatCold  |是否显示座椅制冷或通风控件||
| isCanSetSeatHeat  |是否显示座椅加热的控制控件||
| isCanSetSeatCold  |是否显示座椅制冷或通风的控制控件||
| seatHeatSrnArray  |座椅加热所显示的字段|数组0表示加热等级0|
| seatColdSrnArray  |座椅制冷或通风所显示的字段|数组0表示制冷或通风等级|
| onAirBtnClickListeners  |按钮点击事件数组|可参考已做好的协议|
| tempSetViewOnUpDownClickListeners  |滚轮按钮点击事件数组|可参考已做好的协议|
| setWindSpeedViewOnClickListener  |风量按钮点击事件数组|可参考已做好的协议|
| seatHeatColdClickListeners  |座椅加热制冷事件数组|可参考已做好的协议|
| onAirPageStatusListener  |页面打开时回调的事件|可参考已做好的协议|
| onAirSeatClickListener  |座椅点击事件回调|主要用于切换风向|
| onAirSeatClickListener  |座椅点击事件回调|主要用于切换风向|
| disableBtnArray  |设置某个按钮不可控|与 isAllBtnCanClick 一起使用不冲突
**原车设置**

说明：settingPageUiSet用于原车设置页面的UI设置

|字段|含义|备注|
|---|---|---|
| list  |页面列表|整个页面是一个List，右边列表对应左边列表每一项是1：n的关系|
| titleSrn  |左边列表的标题||
| isSelected  |该左边列表项该项是否选中||
| titleSrn  |右边列表项标题||
| style  |右边列表项风格|风格可参考constant/SettingLvItemStyle|
| valueSrnArray  |右边列表项点击时弹出的列表数组|弹出格式为对话框列表形式|
| selectIndex  |右边列表项选项选中的项|在初始化UI时，是默认选中的项，在解析数据后，更新显示时是选中的项|
| value  |右边列表项显示的值|比如显示“打开”、“关闭”|

**驾驶数据**

说明：driverDataPageUiSet用于驾驶数据页面的UI设置，驾驶数据是以ViewPager形式显示，即每个View对应list里的一个项。

|字段|含义|备注|
|---|---|---|
| list  |页面列表|每个列表项对应一个View的数据|
| headTitleSrn  |即页面顶部的标题||
| itemList  |下面显示的列表数据||
| titleSrn  |下面列表项的key||
| defaultValue  |下面列表项的value||

**胎压数据**

说明：tirePageUiSet用于胎压数据页面的UI设置

|字段|含义|备注|
|---|---|---|
| isHaveSpareTire |是否有备胎||
| tireInfoStrList  |所有轮胎数据整合为一个list||
| whichTire |该项数据对应哪一个轮胎|左上角轮胎为0，右上角为1，左下角为2，右下角为3，备胎为4|
| tireStatus |轮胎状态|正常对应0，警告对应1|
| list |该项数据对应轮胎的显示数据|以String数组的形式表示|

**倒车页面数据**

说明：parkPageUiSet用于倒车页面的UI设置

|字段|含义|备注|
|---|---|---|
| isShowRadar |是否显示雷达||
| isShowTrack  |是否显示转角线||
| isShowRadarLocation |雷达显示的是距离||
| isShowRadarLocation |雷达显示的是隔条||
| isShowPanoramic |是否显示右上角通用的全景按钮||
| panoramicBtnList |右上角通用的全景按钮列表||
| isShowCusPanoramicView |是否显示自定义的全景页面||

**功放页面数据**

说明：amplifierPageUiSet用于倒车页面的UI设置

|字段|含义|备注|
|---|---|---|
| seekBarMax |设置高中低频seekBar 最大值||
| bandRange  |设置高中低值的最值||
| balanceRange |设置平衡的距离值||
| isHaveRateControl |是否有频率控制显示||
| isCanBalanceControl |是否能进行频率控制显示||
| isCanSeekBarMinPlus |||
| lineBtnAction |功放页面的按钮列表||
| isVolumeEnabled |音量条是否可以手动控制||
| isHighEnabled |同上isVolumeEnabled||
| isMiddleEnabled |同上isVolumeEnabled||
| isLowEnabled |同上isVolumeEnabled||
