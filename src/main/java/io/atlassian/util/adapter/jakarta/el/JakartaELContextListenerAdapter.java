package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXELContextEventAdapter;
import io.atlassian.util.adapter.javax.el.JavaXELContextListenerAdapter;
import jakarta.el.ELContextEvent;
import jakarta.el.ELContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaELContextListenerAdapter implements ELContextListener, Adapted<javax.el.ELContextListener> {

    private final javax.el.ELContextListener delegate;

    public static ELContextListener from(javax.el.ELContextListener delegate) {
        if (delegate instanceof JavaXELContextListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaELContextListenerAdapter::new);
    }

    JakartaELContextListenerAdapter(javax.el.ELContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.el.ELContextListener getDelegate() {
        return delegate;
    }

    @Override
    public void contextCreated(ELContextEvent elContextEvent) {
        delegate.contextCreated(JavaXELContextEventAdapter.from(elContextEvent));
    }
}
