package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXSessionCookieConfigAdapter;
import jakarta.servlet.SessionCookieConfig;

import java.util.Map;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaSessionCookieConfigAdapter implements SessionCookieConfig, Adapted<javax.servlet.SessionCookieConfig> {

    private final javax.servlet.SessionCookieConfig delegate;

    public static SessionCookieConfig from(javax.servlet.SessionCookieConfig delegate) {
        if (delegate instanceof JavaXSessionCookieConfigAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaSessionCookieConfigAdapter::new);
    }

    protected JakartaSessionCookieConfigAdapter(javax.servlet.SessionCookieConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.SessionCookieConfig getDelegate() {
        return delegate;
    }

    @Override
    public void setName(String name) {
        delegate.setName(name);
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public void setDomain(String domain) {
        delegate.setDomain(domain);
    }

    @Override
    public String getDomain() {
        return delegate.getDomain();
    }

    @Override
    public void setPath(String path) {
        delegate.setPath(path);
    }

    @Override
    public String getPath() {
        return delegate.getPath();
    }

    @Override
    public void setComment(String comment) {
        delegate.setComment(comment);
    }

    @Override
    public String getComment() {
        return delegate.getComment();
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        delegate.setHttpOnly(httpOnly);
    }

    @Override
    public boolean isHttpOnly() {
        return delegate.isHttpOnly();
    }

    @Override
    public void setSecure(boolean secure) {
        delegate.setSecure(secure);
    }

    @Override
    public boolean isSecure() {
        return delegate.isSecure();
    }

    @Override
    public void setMaxAge(int maxAge) {
        delegate.setMaxAge(maxAge);
    }

    @Override
    public int getMaxAge() {
        return delegate.getMaxAge();
    }

    @Override
    public void setAttribute(String name, String value) {
        // Unadaptable
    }

    @Override
    public String getAttribute(String name) {
        // Unadaptable
        return null;
    }

    @Override
    public Map<String, String> getAttributes() {
        // Unadaptable
        return Map.of();
    }
}
