package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpSessionAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakartaIfJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaXIfJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpSessionAdapter implements HttpSession {
    private final jakarta.servlet.http.HttpSession delegate;

    public static HttpSession from(jakarta.servlet.http.HttpSession delegate) {
        if (delegate instanceof JakartaHttpSessionAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpSessionAdapter::new);
    }

    JavaXHttpSessionAdapter(jakarta.servlet.http.HttpSession delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.http.HttpSession getDelegate() {
        return delegate;
    }

    @Override
    public long getCreationTime() {
        return delegate.getCreationTime();
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public long getLastAccessedTime() {
        return delegate.getLastAccessedTime();
    }

    @Override
    public ServletContext getServletContext() {
        return asJavaX(delegate.getServletContext());
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        delegate.setMaxInactiveInterval(interval);
    }

    @Override
    public int getMaxInactiveInterval() {
        return delegate.getMaxInactiveInterval();
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return JavaXHttpSessionContextAdapter.from(delegate.getSessionContext());
    }

    @Override
    public Object getAttribute(String name) {
        return asJavaXIfJakarta(delegate.getAttribute(name));
    }

    @Override
    public Object getValue(String name) {
        return asJavaXIfJakarta(delegate.getValue(name));
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return delegate.getAttributeNames();
    }

    @Override
    public String[] getValueNames() {
        return delegate.getValueNames();
    }

    @Override
    public void setAttribute(String name, Object value) {
        delegate.setAttribute(name, asJakartaIfJavaX(value));
    }

    @Override
    public void putValue(String name, Object value) {
        delegate.putValue(name, asJakartaIfJavaX(value));
    }

    @Override
    public void removeAttribute(String name) {
        delegate.removeAttribute(name);
    }

    @Override
    public void removeValue(String name) {
        delegate.removeAttribute(name);
    }

    @Override
    public void invalidate() {
        delegate.invalidate();
    }

    @Override
    public boolean isNew() {
        return delegate.isNew();
    }
}
