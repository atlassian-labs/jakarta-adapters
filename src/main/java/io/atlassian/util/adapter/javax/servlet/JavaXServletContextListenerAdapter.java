package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextEventAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextListenerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletContextListenerAdapter implements ServletContextListener, Adapted<jakarta.servlet.ServletContextListener> {

    private final jakarta.servlet.ServletContextListener delegate;

    public static ServletContextListener from(jakarta.servlet.ServletContextListener delegate) {
        if (delegate instanceof JakartaServletContextListenerAdapter) {
            JakartaServletContextListenerAdapter castDelegate = (JakartaServletContextListenerAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletContextListenerAdapter::new);
    }

    protected JavaXServletContextListenerAdapter(jakarta.servlet.ServletContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.ServletContextListener getDelegate() {
        return delegate;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        delegate.contextInitialized(JakartaServletContextEventAdapter.from(sce));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        delegate.contextDestroyed(JakartaServletContextEventAdapter.from(sce));
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
