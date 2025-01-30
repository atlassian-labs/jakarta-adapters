package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletResponseAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXCookieAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletResponseAdapter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpServletResponseAdapter extends JakartaServletResponseAdapter implements HttpServletResponse {

    private final javax.servlet.http.HttpServletResponse delegate;

    public static HttpServletResponse from(javax.servlet.http.HttpServletResponse delegate) {
        if (delegate instanceof JavaXHttpServletResponseAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpServletResponseAdapter::new);
    }

    protected JakartaHttpServletResponseAdapter(javax.servlet.http.HttpServletResponse delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.HttpServletResponse getDelegate() {
        return delegate;
    }

    @Override
    public void addCookie(Cookie cookie) {
        delegate.addCookie(JavaXCookieAdapter.from(cookie));
    }

    @Override
    public boolean containsHeader(String name) {
        return delegate.containsHeader(name);
    }

    @Override
    public String encodeURL(String url) {
        return delegate.encodeURL(url);
    }

    @Override
    public String encodeRedirectURL(String url) {
        return delegate.encodeRedirectURL(url);
    }

    // @Override Servlet API 5.0
    public String encodeUrl(String url) {
        return delegate.encodeUrl(url);
    }

    // @Override Servlet API 5.0
    public String encodeRedirectUrl(String url) {
        return delegate.encodeRedirectUrl(url);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        delegate.sendError(sc, msg);
    }

    @Override
    public void sendError(int sc) throws IOException {
        delegate.sendError(sc);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        delegate.sendRedirect(location);
    }

    @Override
    public void setDateHeader(String name, long date) {
        delegate.setDateHeader(name, date);
    }

    @Override
    public void addDateHeader(String name, long date) {
        delegate.addDateHeader(name, date);
    }

    @Override
    public void setHeader(String name, String value) {
        delegate.setHeader(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        delegate.addHeader(name, value);
    }

    @Override
    public void setIntHeader(String name, int value) {
        delegate.setIntHeader(name, value);
    }

    @Override
    public void addIntHeader(String name, int value) {
        delegate.addIntHeader(name, value);
    }

    @Override
    public void setStatus(int sc) {
        delegate.setStatus(sc);
    }

    // @Override Servlet API 5.0
    public void setStatus(int sc, String sm) {
        delegate.setStatus(sc, sm);
    }

    @Override
    public int getStatus() {
        return delegate.getStatus();
    }

    @Override
    public String getHeader(String name) {
        return delegate.getHeader(name);
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return delegate.getHeaders(name);
    }

    @Override
    public Collection<String> getHeaderNames() {
        return delegate.getHeaderNames();
    }

    @Override
    public void setTrailerFields(Supplier<Map<String, String>> supplier) {
        delegate.setTrailerFields(supplier);
    }

    @Override
    public Supplier<Map<String, String>> getTrailerFields() {
        return delegate.getTrailerFields();
    }
}
