package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaSessionCookieConfigAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.SessionCookieConfig;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXSessionCookieConfigAdapter implements SessionCookieConfig, Adapted<jakarta.servlet.SessionCookieConfig> {

    private final jakarta.servlet.SessionCookieConfig delegate;

    public static SessionCookieConfig from(jakarta.servlet.SessionCookieConfig delegate) {
        if (delegate instanceof JakartaSessionCookieConfigAdapter) {
            JakartaSessionCookieConfigAdapter castDelegate = (JakartaSessionCookieConfigAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXSessionCookieConfigAdapter::new);
    }

    protected JavaXSessionCookieConfigAdapter(jakarta.servlet.SessionCookieConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.SessionCookieConfig getDelegate() {
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
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
