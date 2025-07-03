package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaELContextEventAdapter;
import io.atlassian.util.adapter.jakarta.el.JakartaELContextListenerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.ELContextEvent;
import javax.el.ELContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXELContextListenerAdapter implements ELContextListener, Adapted<jakarta.el.ELContextListener> {

    private final jakarta.el.ELContextListener delegate;

    public static ELContextListener from(jakarta.el.ELContextListener delegate) {
        if (delegate instanceof JakartaELContextListenerAdapter) {
            JakartaELContextListenerAdapter castDelegate = (JakartaELContextListenerAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXELContextListenerAdapter::new);
    }

    protected JavaXELContextListenerAdapter(jakarta.el.ELContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.ELContextListener getDelegate() {
        return delegate;
    }

    @Override
    public void contextCreated(ELContextEvent elContextEvent) {
        delegate.contextCreated(JakartaELContextEventAdapter.from(elContextEvent));
    }

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
