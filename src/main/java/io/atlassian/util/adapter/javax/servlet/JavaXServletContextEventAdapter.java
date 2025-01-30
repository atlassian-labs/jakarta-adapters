package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextEventAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.ServletContextEvent;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletContextEventAdapter extends ServletContextEvent implements Adapted<jakarta.servlet.ServletContextEvent> {

    private final jakarta.servlet.ServletContextEvent delegate;

    public static ServletContextEvent from(jakarta.servlet.ServletContextEvent delegate) {
        if (delegate instanceof JakartaServletContextEventAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletContextEventAdapter::new);
    }

    protected JavaXServletContextEventAdapter(jakarta.servlet.ServletContextEvent delegate) {
        super(asJavaX(requireNonNull(delegate).getServletContext()));
        this.delegate = delegate;
    }

    @Override
    public jakarta.servlet.ServletContextEvent getDelegate() {
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
