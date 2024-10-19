# EventManager
# 事件管理系统

一个简单的Java反射事件管理系统

## 添加进项目
IDEA:
项目结构>>库>>新建项目库>>java>>选择https://github.com/LangYa466/EventManager/releases/tag/1.0下载的jar

## 初始化事件管理系统
```java
EventManager eventManager = new EventManager();
```

## 注册模块
```java
Test test = new Test();
eventManager.register(test); // register
eventManager.unregister(test); // unregister
```

## 打卡事件
```java
eventManager.call(new EventTest());
```
