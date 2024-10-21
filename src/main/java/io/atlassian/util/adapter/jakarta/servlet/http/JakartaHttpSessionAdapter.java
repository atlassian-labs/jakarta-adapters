package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpSessionAdapter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakartaIfJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaXIfJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpSessionAdapter implements HttpSession, Adapted<javax.servlet.http.HttpSession> {
    private final javax.servlet.http.HttpSession delegate;

    public static HttpSession from(javax.servlet.http.HttpSession delegate) {
        if (delegate instanceof JavaXHttpSessionAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpSessionAdapter::new);
    }

    JakartaHttpSessionAdapter(javax.servlet.http.HttpSession delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.HttpSession getDelegate() {
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
        return asJakarta(delegate.getServletContext());
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
        return JakartaHttpSessionContextAdapter.from(delegate.getSessionContext());
    }

    @Override
    public Object getAttribute(String name) {
        return asJakartaIfJavaX(delegate.getAttribute(name));
    }

    @Override
    public Object getValue(String name) {
        return asJakartaIfJavaX(delegate.getValue(name));
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
        delegate.setAttribute(name, asJavaXIfJakarta(value));
    }

    @Override
    public void putValue(String name, Object value) {
        delegate.putValue(name, asJavaXIfJakarta(value));
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
