package github.middlewaremagic.redismagic.test.event;

import github.middlewaremagic.redismagic.context.ApplicationListener;
import github.middlewaremagic.redismagic.context.event.ContextRefreshedEvent;

/*
 * ClassName: ContextRefreshedEventListener
 * Description:
 * @Author: zjh
 * @Create: 2023/5/16
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
