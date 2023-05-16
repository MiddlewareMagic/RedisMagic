package github.middlewaremagic.redismagic.test.event;

import github.middlewaremagic.redismagic.context.event.ApplicationContextEvent;

/*
 * ClassName: CustomEvent
 * Description:
 * @Author: zjh
 * @Create: 2023/5/16
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
