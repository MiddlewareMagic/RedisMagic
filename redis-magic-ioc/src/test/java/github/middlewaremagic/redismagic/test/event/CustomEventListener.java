package github.middlewaremagic.redismagic.test.event;

import github.middlewaremagic.redismagic.context.ApplicationListener;

import java.util.Date;

/*
 * ClassName: CustomEventListener
 * Description:
 * @Author: zjh
 * @Create: 2023/5/16
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
