package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXServletContextEventAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.ServletContextEvent;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletContextEventAdapter extends ServletContextEvent implements Adapted<javax.servlet.ServletContextEvent> {

    private final javax.servlet.ServletContextEvent delegate;

    public static ServletContextEvent from(javax.servlet.ServletContextEvent delegate) {
        if (delegate instanceof JavaXServletContextEventAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletContextEventAdapter::new);
    }

    protected JakartaServletContextEventAdapter(javax.servlet.ServletContextEvent delegate) {
        super(asJakarta(requireNonNull(delegate).getServletContext()));
        this.delegate = delegate;
    }

    @Override
    public javax.servlet.ServletContextEvent getDelegate() {
        return delegate;
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
