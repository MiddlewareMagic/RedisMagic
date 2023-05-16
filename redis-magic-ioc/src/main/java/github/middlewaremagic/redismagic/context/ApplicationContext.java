package github.middlewaremagic.redismagic.context;

import github.middlewaremagic.redismagic.beans.factory.HierarchicalBeanFactory;
import github.middlewaremagic.redismagic.beans.factory.ListableBeanFactory;
import github.middlewaremagic.redismagic.core.io.ResourceLoader;

/*
 * ClassName: ApplicationContext
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
