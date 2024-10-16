package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.http.JakartaCookieAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletResponseAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpServletResponseAdapter extends JavaXServletResponseAdapter implements HttpServletResponse {

    private final jakarta.servlet.http.HttpServletResponse delegate;

    public JavaXHttpServletResponseAdapter(jakarta.servlet.http.HttpServletResponse delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.http.HttpServletResponse getDelegate() {
        return delegate;
    }

    @Override
    public void addCookie(Cookie cookie) {
        delegate.addCookie(applyIfNonNull(cookie, JakartaCookieAdapter::new));
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

    @Override
    public String encodeUrl(String url) {
        return encodeURL(url);
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return encodeRedirectURL(url);
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

    @Override
    public void setStatus(int sc, String sm) {
        // Unadaptable
        setStatus(sc);
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
