package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextAdapter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakartaIfJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaXIfJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpSessionAdapter implements HttpSession {
    private final javax.servlet.http.HttpSession delegate;

    public JakartaHttpSessionAdapter(javax.servlet.http.HttpSession delegate) {
        this.delegate = requireNonNull(delegate);
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
        return applyIfNonNull(delegate.getServletContext(), JakartaServletContextAdapter::new);
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        delegate.setMaxInactiveInterval(interval);
    }

    @Override
    public int getMaxInactiveInterval() {
        return delegate.getMaxInactiveInterval();
    }

    // @Override Servlet API 5.0
    // public jakarta.servlet.http.HttpSessionContext getSessionContext() {
    //     return applyIfNonNull(delegate.getSessionContext(), JakartaHttpSessionContextAdapter::new);
    // }

    @Override
    public Object getAttribute(String name) {
        return asJakartaIfJavaX(delegate.getAttribute(name));
    }

    // @Override Servlet API 5.0
    public Object getValue(String name) {
        return asJakartaIfJavaX(delegate.getValue(name));
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return delegate.getAttributeNames();
    }

    // @Override Servlet API 5.0
    public String[] getValueNames() {
        return delegate.getValueNames();
    }

    @Override
    public void setAttribute(String name, Object value) {
        delegate.setAttribute(name, asJavaXIfJakarta(value));
    }

    // @Override Servlet API 5.0
    public void putValue(String name, Object value) {
        delegate.putValue(name, asJavaXIfJakarta(value));
    }

    @Override
    public void removeAttribute(String name) {
        delegate.removeAttribute(name);
    }

    // @Override Servlet API 5.0
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
