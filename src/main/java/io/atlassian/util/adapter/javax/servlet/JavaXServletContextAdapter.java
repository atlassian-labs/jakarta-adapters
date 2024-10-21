package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextAdapter;
import io.atlassian.util.adapter.java.util.EnumerationAdapter;
import io.atlassian.util.adapter.javax.servlet.descriptor.JavaXJspConfigDescriptorAdapter;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakartaIfJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaXIfJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

public class JavaXServletContextAdapter implements ServletContext, Adapted<jakarta.servlet.ServletContext> {

    private final jakarta.servlet.ServletContext delegate;

    public static ServletContext from(jakarta.servlet.ServletContext delegate) {
        if (delegate instanceof JakartaServletContextAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletContextAdapter::new);
    }

    JavaXServletContextAdapter(jakarta.servlet.ServletContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.ServletContext getDelegate() {
        return delegate;
    }

    @Override
    public String getContextPath() {
        return delegate.getContextPath();
    }

    @Override
    public ServletContext getContext(String uripath) {
        return JavaXServletContextAdapter.from(delegate.getContext(uripath));
    }

    @Override
    public int getMajorVersion() {
        return delegate.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return delegate.getMinorVersion();
    }

    @Override
    public int getEffectiveMajorVersion() {
        return delegate.getEffectiveMajorVersion();
    }

    @Override
    public int getEffectiveMinorVersion() {
        return delegate.getEffectiveMinorVersion();
    }

    @Override
    public String getMimeType(String file) {
        return delegate.getMimeType(file);
    }

    @Override
    public Set<String> getResourcePaths(String path) {
        return delegate.getResourcePaths(path);
    }

    @Override
    public URL getResource(String path) throws MalformedURLException {
        return delegate.getResource(path);
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        return delegate.getResourceAsStream(path);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return JavaXRequestDispatcherAdapter.from(delegate.getRequestDispatcher(path));
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return JavaXRequestDispatcherAdapter.from(delegate.getNamedDispatcher(name));
    }

    @Override
    public Servlet getServlet(String name) throws ServletException {
        try {
            return JavaXServletAdapter.from(delegate.getServlet(name));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public Enumeration<Servlet> getServlets() {
        var servlets = delegate.getServlets();
        if (servlets == null) {
            return null;
        }
        return new EnumerationAdapter<>(delegate.getServlets(), JavaXServletAdapter::from);
    }

    @Override
    public Enumeration<String> getServletNames() {
        return delegate.getServletNames();
    }

    @Override
    public void log(String msg) {
        delegate.log(msg);
    }

    @Override
    public void log(Exception exception, String msg) {
        delegate.log(exception, msg);
    }

    @Override
    public void log(String message, Throwable throwable) {
        delegate.log(message, throwable);
    }

    @Override
    public String getRealPath(String path) {
        return delegate.getRealPath(path);
    }

    @Override
    public String getServerInfo() {
        return delegate.getServerInfo();
    }

    @Override
    public String getInitParameter(String name) {
        return delegate.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return delegate.getInitParameterNames();
    }

    @Override
    public boolean setInitParameter(String name, String value) {
        return delegate.setInitParameter(name, value);
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
    public void setAttribute(String name, Object object) {
        delegate.setAttribute(name, asJakartaIfJavaX(object));
    }

    @Override
    public void removeAttribute(String name) {
        delegate.removeAttribute(name);
    }

    @Override
    public String getServletContextName() {
        return delegate.getServletContextName();
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return JavaXDynamicServletRegistrationAdapter.from(delegate.addServlet(servletName, className));
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
        return JavaXDynamicServletRegistrationAdapter.from(delegate.addServlet(servletName, JakartaServletAdapter.from(servlet)));
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        // ClassCastException likely
        return JavaXDynamicServletRegistrationAdapter.from(delegate.addServlet(servletName, (Class) servletClass));
    }

    @Override
    public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return JavaXDynamicServletRegistrationAdapter.from(delegate.addJspFile(servletName, jspFile));
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) JavaXServletAdapter.from(delegate.createServlet((Class) clazz));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        return JavaXServletRegistrationAdapter.from(delegate.getServletRegistration(servletName));
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        var servletRegistrations = delegate.getServletRegistrations();
        if (servletRegistrations == null) {
            return null;
        }
        return servletRegistrations.entrySet().stream()
                .collect(toMap(Entry::getKey, e -> JavaXServletRegistrationAdapter.from(e.getValue())));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return JavaXDynamicFilterRegistrationAdapter.from(delegate.addFilter(filterName, className));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        return JavaXDynamicFilterRegistrationAdapter.from(delegate.addFilter(filterName, JakartaFilterAdapter.from(filter)));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
        // ClassCastException likely
        return JavaXDynamicFilterRegistrationAdapter.from(delegate.addFilter(filterName, (Class) filterClass));
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) JavaXFilterAdapter.from(delegate.createFilter((Class) clazz));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        return JavaXFilterRegistrationAdapter.from(delegate.getFilterRegistration(filterName));
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        var filterRegistrations = delegate.getFilterRegistrations();
        if (filterRegistrations == null) {
            return null;
        }
        return filterRegistrations.entrySet().stream()
                .collect(toMap(Entry::getKey, e -> JavaXFilterRegistrationAdapter.from(e.getValue())));
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return JavaXSessionCookieConfigAdapter.from(delegate.getSessionCookieConfig());
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        delegate.setSessionTrackingModes(JakartaServletContextAdapter.toJakartaSessionTrackingModeSet(sessionTrackingModes));
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return JavaXServletContextAdapter.toJavaXSessionTrackingModeSet(delegate.getDefaultSessionTrackingModes());
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return JavaXServletContextAdapter.toJavaXSessionTrackingModeSet(delegate.getEffectiveSessionTrackingModes());
    }

    @Override
    public void addListener(String className) {
        delegate.addListener(className);
    }

    @Override
    public <T extends EventListener> void addListener(T t) {
        delegate.addListener(t);
    }

    @Override
    public void addListener(Class<? extends EventListener> listenerClass) {
        delegate.addListener(listenerClass);
    }

    @Override
    public <T extends EventListener> T createListener(Class<T> clazz) throws ServletException {
        try {
            return delegate.createListener(clazz);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return JavaXJspConfigDescriptorAdapter.from(delegate.getJspConfigDescriptor());
    }

    @Override
    public ClassLoader getClassLoader() {
        return delegate.getClassLoader();
    }

    @Override
    public void declareRoles(String... roleNames) {
        delegate.declareRoles(roleNames);
    }

    @Override
    public String getVirtualServerName() {
        return delegate.getVirtualServerName();
    }

    @Override
    public int getSessionTimeout() {
        return delegate.getSessionTimeout();
    }

    @Override
    public void setSessionTimeout(int sessionTimeout) {
        delegate.setSessionTimeout(sessionTimeout);
    }

    @Override
    public String getRequestCharacterEncoding() {
        return delegate.getRequestCharacterEncoding();
    }

    @Override
    public void setRequestCharacterEncoding(String encoding) {
        delegate.setRequestCharacterEncoding(encoding);
    }

    @Override
    public String getResponseCharacterEncoding() {
        return delegate.getResponseCharacterEncoding();
    }

    @Override
    public void setResponseCharacterEncoding(String encoding) {
        delegate.setResponseCharacterEncoding(encoding);
    }

    public static Set<SessionTrackingMode> toJavaXSessionTrackingModeSet(Collection<jakarta.servlet.SessionTrackingMode> sessionTrackingModes) {
        if (sessionTrackingModes == null) {
            return null;
        }
        var result = EnumSet.noneOf(SessionTrackingMode.class);
        for (jakarta.servlet.SessionTrackingMode sessionTrackingMode : sessionTrackingModes) {
            result.add(SessionTrackingMode.valueOf(sessionTrackingMode.name()));
        }
        return result;
    }
}
