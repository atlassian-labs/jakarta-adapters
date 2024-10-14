package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestAdapter;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakartaIfJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaXIfJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletRequestAdapter implements ServletRequest {

    private final jakarta.servlet.ServletRequest delegate;

    public static ServletRequest from(jakarta.servlet.ServletRequest delegate) {
        if (delegate instanceof jakarta.servlet.http.HttpServletRequest castDelegate) {
            return new JavaXHttpServletRequestAdapter(castDelegate);
        }
        return applyIfNonNull(delegate, JavaXServletRequestAdapter::new);
    }

    protected JavaXServletRequestAdapter(jakarta.servlet.ServletRequest delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.ServletRequest getDelegate() {
        return delegate;
    }

    @Override
    public Object getAttribute(String name) {
        return asJavaXIfJakarta(delegate.getAttribute(name));
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return delegate.getAttributeNames();
    }

    @Override
    public String getCharacterEncoding() {
        return delegate.getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        delegate.setCharacterEncoding(env);
    }

    @Override
    public int getContentLength() {
        return delegate.getContentLength();
    }

    @Override
    public long getContentLengthLong() {
        return delegate.getContentLengthLong();
    }

    @Override
    public String getContentType() {
        return delegate.getContentType();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return applyIfNonNull(delegate.getInputStream(), JavaXServletInputStreamAdapter::new);
    }

    @Override
    public String getParameter(String name) {
        return delegate.getParameter(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return delegate.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return delegate.getParameterValues(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return delegate.getParameterMap();
    }

    @Override
    public String getProtocol() {
        return delegate.getProtocol();
    }

    @Override
    public String getScheme() {
        return delegate.getScheme();
    }

    @Override
    public String getServerName() {
        return delegate.getServerName();
    }

    @Override
    public int getServerPort() {
        return delegate.getServerPort();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return delegate.getReader();
    }

    @Override
    public String getRemoteAddr() {
        return delegate.getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {
        return delegate.getRemoteHost();
    }

    @Override
    public void setAttribute(String name, Object o) {
        delegate.setAttribute(name, asJakartaIfJavaX(o));
    }

    @Override
    public void removeAttribute(String name) {
        delegate.removeAttribute(name);
    }

    @Override
    public Locale getLocale() {
        return delegate.getLocale();
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return delegate.getLocales();
    }

    @Override
    public boolean isSecure() {
        return delegate.isSecure();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return applyIfNonNull(delegate.getRequestDispatcher(path), JavaXRequestDispatcherAdapter::new);
    }

    @Override
    public String getRealPath(String path) {
        return delegate.getServletContext().getRealPath(path);
    }

    @Override
    public int getRemotePort() {
        return delegate.getRemotePort();
    }

    @Override
    public String getLocalName() {
        return delegate.getLocalName();
    }

    @Override
    public String getLocalAddr() {
        return delegate.getLocalAddr();
    }

    @Override
    public int getLocalPort() {
        return delegate.getLocalPort();
    }

    @Override
    public ServletContext getServletContext() {
        return applyIfNonNull(delegate.getServletContext(), JavaXServletContextAdapter::new);
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return applyIfNonNull(delegate.startAsync(), JavaXAsyncContextAdapter::new);
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest,
                                   ServletResponse servletResponse) throws IllegalStateException {
        return applyIfNonNull(
                delegate.startAsync(asJakarta(servletRequest), asJakarta(servletResponse)),
                JavaXAsyncContextAdapter::new);
    }

    @Override
    public boolean isAsyncStarted() {
        return delegate.isAsyncStarted();
    }

    @Override
    public boolean isAsyncSupported() {
        return delegate.isAsyncSupported();
    }

    @Override
    public AsyncContext getAsyncContext() {
        return applyIfNonNull(delegate.getAsyncContext(), JavaXAsyncContextAdapter::new);
    }

    @Override
    public DispatcherType getDispatcherType() {
        return DispatcherType.valueOf(delegate.getDispatcherType().name());
    }
}
