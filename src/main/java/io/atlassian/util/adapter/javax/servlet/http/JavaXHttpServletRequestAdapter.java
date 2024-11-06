package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletRequestAdapter;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletResponseAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpServletRequestAdapter extends JavaXServletRequestAdapter implements HttpServletRequest {

    private final jakarta.servlet.http.HttpServletRequest delegate;

    public static HttpServletRequest from(jakarta.servlet.http.HttpServletRequest delegate) {
        if (delegate instanceof JakartaHttpServletRequestAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpServletRequestAdapter::new);
    }

    JavaXHttpServletRequestAdapter(jakarta.servlet.http.HttpServletRequest delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.http.HttpServletRequest getDelegate() {
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
        return Arrays.stream(cookies).map(JavaXCookieAdapter::from).toArray(Cookie[]::new);
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
        return JavaXHttpServletMappingAdapter.from(delegate.getHttpServletMapping());
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
        return JavaXPushBuilderAdapter.from(delegate.newPushBuilder());
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
        return JavaXHttpSessionAdapter.from(delegate.getSession(create));
    }

    @Override
    public HttpSession getSession() {
        return JavaXHttpSessionAdapter.from(delegate.getSession());
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

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return isRequestedSessionIdFromURL();
    }

    @Override
    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        try {
            return delegate.authenticate(JakartaHttpServletResponseAdapter.from(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void login(String username, String password) throws ServletException {
        try {
            delegate.login(username, password);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void logout() throws ServletException {
        try {
            delegate.logout();
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        Collection<jakarta.servlet.http.Part> parts;
        try {
            parts = delegate.getParts();
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
        return transformIfNonNull(parts, JavaXPartAdapter::from);
    }

    @Override
    public Part getPart(String name) throws IOException, ServletException {
        try {
            return JavaXPartAdapter.from(delegate.getPart(name));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        try {
            // ClassCastException likely
            return (T) JavaXHttpUpgradeHandlerAdapter.from(delegate.upgrade((Class) handlerClass));
        } catch (jakarta.servlet.ServletException e) {
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
