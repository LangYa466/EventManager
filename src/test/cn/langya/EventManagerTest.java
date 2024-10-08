package cn.langya;

// chatgpt 懒得自己写
public class EventManagerTest {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();

        // 创建监听器
        MyListener listener = new MyListener();

        // 注册监听器
        eventManager.register(listener);

        // 创建事件
        MyEvent event = new MyEvent();

        // 测试循环次数
        int iterations = 10000000;

        // 开始计时
        long startTime = System.currentTimeMillis();

        // 触发事件多次
        for (int i = 0; i < iterations; i++) {
            eventManager.call(event);
        }

        // 结束计时
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // 输出时间
        System.out.println("Time taken for " + iterations + " iterations: " + duration + " ms");

        // 取消事件并再次触发
        event.setCancelled(true);
        eventManager.call(event);

        // 注销监听器
        eventManager.unregister(listener);

        // 尝试再次触发事件，应该没有输出
        eventManager.call(event);
    }
}

class MyListener {
    @EventTarget(priority = 1)
    public void onMyEvent(MyEvent event) {
        int a = 1+2+3;
    }
}

class MyEvent extends Event {
    // 可以添加额外的属性和方法
}
