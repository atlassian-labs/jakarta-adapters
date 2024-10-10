package io.atlassian.util.adapter.javax.el;

import javax.el.ELContextEvent;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;

public class JavaXELContextEventAdapter extends ELContextEvent {

    public JavaXELContextEventAdapter(jakarta.el.ELContextEvent delegate) {
        super(asJavaXJsp(delegate.getELContext()));
    }
}
