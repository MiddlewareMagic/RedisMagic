package github.middlewaremagic.redismagic.test.event;

import github.middlewaremagic.redismagic.context.ApplicationListener;
import github.middlewaremagic.redismagic.context.event.ContextClosedEvent;

/*
 * ClassName: ContextClosedEventListener
 * Description:
 * @Author: zjh
 * @Create: 2023/5/16
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
