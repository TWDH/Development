- https://www.cnblogs.com/Dahlia/p/10516405.html

1、安装appium需要node.js环境，所以要先安装配置好node.js

https://nodejs.org/en/download/

在如上node官网下载对象版本的node.js并安装，参考链接：https://www.cnblogs.com/zhouyu2017/p/6485265.html

2、命令行安装appium

```
npm ``install` `appium -g --chromedriver_cdnurl=http:``//cdn``.npm.taobao.org``/dist/chromedriver
```

3、安装appium-doctor

```
npm ``install` `appium-doctor -g
```

4、检查环境配置

```
apium-doctor
```

5、启动appium，有多种方法：（只试过命令行安装的appium可以用命令行启动，桌面版的appium desktop貌似还要手动点击启动页按钮启动）

```
appium
appium ``-``a ``127.0``.``0.1` `-``p ``4723` `-``-``session``-``override
```

-a 是指定监听的ip（也可写成 --address），后面“127.0.0.1”可以改为你需要的ip地址；

-p 是指定监听的端口（也可写成 --port），也可以修改为你需要的端口；

--session-override 是指覆盖之前的session；

```
appium ``-``a ``127.0``.``0.1` `-``p ``4723` `-``bp ``4728` `-``-``chromedriver``-``port ``9519` `-``U xiaomi ``-``-``session``-``override
```

-bp 是连接Android设备bootstrap的端口号，默认是4724（也可写成--bootstrap-port）

--chromedriver-port 是chromedriver运行需要指定的端口号，默认是9515

-U 是连接的设备名称，如"adb devices"获取的设备标识（也可写成--udid）

6、卸载命令行安装的appium

```
npm uninstall -g appium
```

 7、卸载appium-doctor

```
npm uninstall -g appium-doctor
```