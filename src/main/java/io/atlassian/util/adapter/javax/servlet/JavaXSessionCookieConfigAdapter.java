package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.SessionCookieConfig;

import static java.util.Objects.requireNonNull;

public class JavaXSessionCookieConfigAdapter implements SessionCookieConfig {

    private final jakarta.servlet.SessionCookieConfig delegate;

    public JavaXSessionCookieConfigAdapter(jakarta.servlet.SessionCookieConfig delegate) {
        this.delegate = requireNonNull(delegate);
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
}
