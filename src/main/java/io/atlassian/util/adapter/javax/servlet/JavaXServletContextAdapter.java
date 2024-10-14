package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextAdapter;
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
import static java.util.Collections.emptyEnumeration;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

public class JavaXServletContextAdapter implements ServletContext {

    private final jakarta.servlet.ServletContext delegate;

    public JavaXServletContextAdapter(jakarta.servlet.ServletContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getContextPath() {
        return delegate.getContextPath();
    }

    @Override
    public ServletContext getContext(String uripath) {
        return applyIfNonNull(delegate.getContext(uripath), JavaXServletContextAdapter::new);
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
        return applyIfNonNull(delegate.getRequestDispatcher(path), JavaXRequestDispatcherAdapter::new);
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return applyIfNonNull(delegate.getNamedDispatcher(name), JavaXRequestDispatcherAdapter::new);
    }

    @Override
    public Servlet getServlet(String name) throws ServletException {
        // Unadaptable
        return null;
    }

    @Override
    public Enumeration<Servlet> getServlets() {
        // Unadaptable
        return emptyEnumeration();
    }

    @Override
    public Enumeration<String> getServletNames() {
        // Unadaptable
        return emptyEnumeration();
    }

    @Override
    public void log(String msg) {
        delegate.log(msg);
    }

    @Override
    public void log(Exception exception, String msg) {
        log(msg, exception);
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
        return applyIfNonNull(delegate.addServlet(servletName, className), JavaXDynamicServletRegistrationAdapter::new);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
        return applyIfNonNull(
                delegate.addServlet(servletName, applyIfNonNull(servlet, JakartaServletAdapter::new)),
                JavaXDynamicServletRegistrationAdapter::new);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        // ClassCastException likely
        return applyIfNonNull(delegate.addServlet(servletName, (Class) servletClass), JavaXDynamicServletRegistrationAdapter::new);
    }

    @Override
    public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return applyIfNonNull(delegate.addJspFile(servletName, jspFile), JavaXDynamicServletRegistrationAdapter::new);
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) applyIfNonNull(delegate.createServlet((Class) clazz), JavaXServletAdapter::new);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        return applyIfNonNull(delegate.getServletRegistration(servletName), JavaXServletRegistrationAdapter::new);
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        var servletRegistrations = delegate.getServletRegistrations();
        if (servletRegistrations == null) {
            return null;
        }
        return servletRegistrations.entrySet().stream()
                .collect(toMap(Entry::getKey, e -> applyIfNonNull(e.getValue(), JavaXServletRegistrationAdapter::new)));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return applyIfNonNull(delegate.addFilter(filterName, className), JavaXDynamicFilterRegistrationAdapter::new);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        return applyIfNonNull(
                delegate.addFilter(filterName, applyIfNonNull(filter, JakartaFilterAdapter::new)),
                JavaXDynamicFilterRegistrationAdapter::new);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
        // ClassCastException likely
        return applyIfNonNull(delegate.addFilter(filterName, (Class) filterClass), JavaXDynamicFilterRegistrationAdapter::new);
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) applyIfNonNull(delegate.createFilter((Class) clazz), JavaXFilterAdapter::new);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        return applyIfNonNull(delegate.getFilterRegistration(filterName), JavaXFilterRegistrationAdapter::new);
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        var filterRegistrations = delegate.getFilterRegistrations();
        if (filterRegistrations == null) {
            return null;
        }
        return filterRegistrations.entrySet().stream()
                .collect(toMap(Entry::getKey, e -> applyIfNonNull(e.getValue(), JavaXFilterRegistrationAdapter::new)));
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return applyIfNonNull(delegate.getSessionCookieConfig(), JavaXSessionCookieConfigAdapter::new);
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        delegate.setSessionTrackingModes(applyIfNonNull(sessionTrackingModes, JakartaServletContextAdapter::toJakartaSessionTrackingModeSet));
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return applyIfNonNull(delegate.getDefaultSessionTrackingModes(), JavaXServletContextAdapter::toJavaXSessionTrackingModeSet);
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return applyIfNonNull(delegate.getEffectiveSessionTrackingModes(), JavaXServletContextAdapter::toJavaXSessionTrackingModeSet);
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
        return applyIfNonNull(delegate.getJspConfigDescriptor(), JavaXJspConfigDescriptorAdapter::new);
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
        var result = EnumSet.noneOf(SessionTrackingMode.class);
        for (jakarta.servlet.SessionTrackingMode sessionTrackingMode : sessionTrackingModes) {
            result.add(SessionTrackingMode.valueOf(sessionTrackingMode.name()));
        }
        return result;
    }
}
