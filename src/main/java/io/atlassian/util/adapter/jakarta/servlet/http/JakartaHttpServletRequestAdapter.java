package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletResponseAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.PushBuilder;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpServletRequestAdapter extends JakartaServletRequestAdapter implements HttpServletRequest {

    private final javax.servlet.http.HttpServletRequest delegate;

    public static HttpServletRequest from(javax.servlet.http.HttpServletRequest delegate) {
        if (delegate instanceof javax.servlet.http.HttpServletRequestWrapper castDelegate) {
            return JakartaHttpServletRequestWrapperAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXHttpServletRequestAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpServletRequestAdapter::new);
    }

    protected JakartaHttpServletRequestAdapter(javax.servlet.http.HttpServletRequest delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.HttpServletRequest getDelegate() {
        return delegate;
    }

    @Override
    public String getAuthType() {
        return delegate.getAuthType();
    }

    @Override
    public Cookie[] getCookies() {
        var cookies = delegate.getCookies();
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies).map(JakartaCookieAdapter::from).toArray(Cookie[]::new);
    }

    @Override
    public long getDateHeader(String name) {
        return delegate.getDateHeader(name);
    }

    @Override
    public String getHeader(String name) {
        return delegate.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        return delegate.getHeaders(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return delegate.getHeaderNames();
    }

    @Override
    public int getIntHeader(String name) {
        return delegate.getIntHeader(name);
    }

    @Override
    public HttpServletMapping getHttpServletMapping() {
        return JakartaHttpServletMappingAdapter.from(delegate.getHttpServletMapping());
    }

    @Override
    public String getMethod() {
        return delegate.getMethod();
    }

    @Override
    public String getPathInfo() {
        return delegate.getPathInfo();
    }

    @Override
    public String getPathTranslated() {
        return delegate.getPathTranslated();
    }

    @Override
    public PushBuilder newPushBuilder() {
        return JakartaPushBuilderAdapter.from(delegate.newPushBuilder());
    }

    @Override
    public String getContextPath() {
        return delegate.getContextPath();
    }

    @Override
    public String getQueryString() {
        return delegate.getQueryString();
    }

    @Override
    public String getRemoteUser() {
        return delegate.getRemoteUser();
    }

    @Override
    public boolean isUserInRole(String role) {
        return delegate.isUserInRole(role);
    }

    @Override
    public Principal getUserPrincipal() {
        return delegate.getUserPrincipal();
    }

    @Override
    public String getRequestedSessionId() {
        return delegate.getRequestedSessionId();
    }

    @Override
    public String getRequestURI() {
        return delegate.getRequestURI();
    }

    @Override
    public StringBuffer getRequestURL() {
        return delegate.getRequestURL();
    }

    @Override
    public String getServletPath() {
        return delegate.getServletPath();
    }

    @Override
    public HttpSession getSession(boolean create) {
        return JakartaHttpSessionAdapter.from(delegate.getSession(create));
    }

    @Override
    public HttpSession getSession() {
        return JakartaHttpSessionAdapter.from(delegate.getSession());
    }

    @Override
    public String changeSessionId() {
        return delegate.changeSessionId();
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return delegate.isRequestedSessionIdValid();
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return delegate.isRequestedSessionIdFromCookie();
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return delegate.isRequestedSessionIdFromURL();
    }

    // @Override Servlet API 5.0
    public boolean isRequestedSessionIdFromUrl() {
        return delegate.isRequestedSessionIdFromUrl();
    }

    @Override
    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        try {
            return delegate.authenticate(JavaXHttpServletResponseAdapter.from(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void login(String username, String password) throws ServletException {
        try {
            delegate.login(username, password);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void logout() throws ServletException {
        try {
            delegate.logout();
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        Collection<javax.servlet.http.Part> parts;
        try {
            parts = delegate.getParts();
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
        return transformIfNonNull(parts, JakartaPartAdapter::from);
    }

    @Override
    public Part getPart(String name) throws IOException, ServletException {
        try {
            return JakartaPartAdapter.from(delegate.getPart(name));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        try {
            // ClassCastException likely
            return (T) JakartaHttpUpgradeHandlerAdapter.from(delegate.upgrade((Class) handlerClass));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public Map<String, String> getTrailerFields() {
        return delegate.getTrailerFields();
    }

    @Override
    public boolean isTrailerFieldsReady() {
        return delegate.isTrailerFieldsReady();
    }
}
