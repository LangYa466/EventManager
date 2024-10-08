package cn.langya;

import java.lang.reflect.*;
import java.util.*;

public class EventManager {
    private final Map<Class<?>, ArrayList<EventListener>> listeners = new HashMap<>();

    public void register(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventTarget.class)) {
                EventTarget eventTarget = method.getAnnotation(EventTarget.class);
                listeners.computeIfAbsent(method.getParameterTypes()[0], k -> new ArrayList<>())
                        .add(new EventListener(listener, method, eventTarget.priority()));
            }
        }
    }

    public void unregister(Object listener) {
        for (ArrayList<EventListener> listenerList : listeners.values()) {
            listenerList.removeIf(l -> l.instance == listener);
        }
    }

    public void call(Event event) {
        ArrayList<EventListener> listenerList = listeners.get(event.getClass());
        if (listenerList != null) {
            listenerList.sort(Comparator.comparingInt(EventListener::getPriority).reversed());
            for (EventListener listener : listenerList) {
                try {
                    if (event.isCancelled()) return;
                    listener.method.invoke(listener.instance, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class EventListener {
        private final Object instance;
        private final Method method;
        private final int priority;

        public EventListener(Object instance, Method method, int priority) {
            this.instance = instance;
            this.method = method;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }
}
