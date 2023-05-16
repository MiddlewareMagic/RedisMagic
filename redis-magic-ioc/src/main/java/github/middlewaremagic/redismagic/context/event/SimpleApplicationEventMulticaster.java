package github.middlewaremagic.redismagic.context.event;

import github.middlewaremagic.redismagic.beans.factory.BeanFactory;
import github.middlewaremagic.redismagic.context.ApplicationEvent;
import github.middlewaremagic.redismagic.context.ApplicationListener;

/*
 * ClassName: SimpleApplicationEventMulticaster
 * Description:
 * @Author: zjh
 * @Create: 2023/5/16
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
