package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.javax.el.JavaXELContextEventAdapter;
import jakarta.el.ELContextEvent;
import jakarta.el.ELContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaELContextListenerAdapter implements ELContextListener {

    private final javax.el.ELContextListener delegate;

    public JakartaELContextListenerAdapter(javax.el.ELContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void contextCreated(ELContextEvent elContextEvent) {
        delegate.contextCreated(applyIfNonNull(elContextEvent, JavaXELContextEventAdapter::new));
    }
}
