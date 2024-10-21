package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXServletContextEventAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletContextListenerAdapter;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletContextListenerAdapter implements ServletContextListener, Adapted<javax.servlet.ServletContextListener> {

    private final javax.servlet.ServletContextListener delegate;

    public static ServletContextListener from(javax.servlet.ServletContextListener delegate) {
        if (delegate instanceof JavaXServletContextListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletContextListenerAdapter::new);
    }

    JakartaServletContextListenerAdapter(javax.servlet.ServletContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.ServletContextListener getDelegate() {
        return delegate;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        delegate.contextInitialized(JavaXServletContextEventAdapter.from(sce));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        delegate.contextDestroyed(JavaXServletContextEventAdapter.from(sce));
    }
}
