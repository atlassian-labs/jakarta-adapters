package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.jakarta.el.JakartaELContextEventAdapter;

import javax.el.ELContextEvent;
import javax.el.ELContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXELContextListenerAdapter implements ELContextListener {

    private final jakarta.el.ELContextListener delegate;

    public JavaXELContextListenerAdapter(jakarta.el.ELContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void contextCreated(ELContextEvent elContextEvent) {
        delegate.contextCreated(applyIfNonNull(elContextEvent, JakartaELContextEventAdapter::new));
    }
}
