package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.SessionCookieConfig;

import static java.util.Objects.requireNonNull;

public class JakartaSessionCookieConfigAdapter implements SessionCookieConfig {

    private final javax.servlet.SessionCookieConfig delegate;

    public JakartaSessionCookieConfigAdapter(javax.servlet.SessionCookieConfig delegate) {
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
