package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.jakarta.servlet.descriptor.JakartaJspConfigDescriptorAdapter;
import io.atlassian.util.adapter.java.util.EnumerationAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXFilterAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletContextAdapter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.descriptor.JspConfigDescriptor;

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

public class JakartaServletContextAdapter implements ServletContext {

    private final javax.servlet.ServletContext delegate;

    public JakartaServletContextAdapter(javax.servlet.ServletContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getContextPath() {
        return delegate.getContextPath();
    }

    @Override
    public ServletContext getContext(String uripath) {
        return applyIfNonNull(delegate.getContext(uripath), JakartaServletContextAdapter::new);
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
        return applyIfNonNull(delegate.getRequestDispatcher(path), JakartaRequestDispatcherAdapter::new);
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return applyIfNonNull(delegate.getNamedDispatcher(name), JakartaRequestDispatcherAdapter::new);
    }

    // @Override Servlet API 5.0
    public Servlet getServlet(String name) throws ServletException {
        try {
            return applyIfNonNull(delegate.getServlet(name), JakartaServletAdapter::new);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    // @Override Servlet API 5.0
    public Enumeration<Servlet> getServlets() {
        var servlets = delegate.getServlets();
        if (servlets == null) {
            return null;
        }
        return new EnumerationAdapter<>(delegate.getServlets(), JakartaServletAdapter::new);
    }

    // @Override Servlet API 5.0
    public Enumeration<String> getServletNames() {
        return delegate.getServletNames();
    }

    @Override
    public void log(String msg) {
        delegate.log(msg);
    }

    // @Override Servlet API 5.0
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
        return asJakartaIfJavaX(delegate.getAttribute(name));
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return delegate.getAttributeNames();
    }

    @Override
    public void setAttribute(String name, Object object) {
        delegate.setAttribute(name, asJavaXIfJakarta(object));
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
        return applyIfNonNull(delegate.addServlet(servletName, className), JakartaDynamicServletRegistrationAdapter::new);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
        return applyIfNonNull(
                delegate.addServlet(servletName, applyIfNonNull(servlet, JavaXServletAdapter::new)),
                JakartaDynamicServletRegistrationAdapter::new);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        // ClassCastException likely
        return applyIfNonNull(delegate.addServlet(servletName, (Class) servletClass), JakartaDynamicServletRegistrationAdapter::new);
    }

    @Override
    public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return applyIfNonNull(delegate.addJspFile(servletName, jspFile), JakartaDynamicServletRegistrationAdapter::new);
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) applyIfNonNull(delegate.createServlet((Class) clazz), JakartaServletAdapter::new);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        return applyIfNonNull(delegate.getServletRegistration(servletName), JakartaServletRegistrationAdapter::new);
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        var servletRegistrations = delegate.getServletRegistrations();
        if (servletRegistrations == null) {
            return null;
        }
        return servletRegistrations.entrySet().stream()
                .collect(toMap(Entry::getKey, e -> applyIfNonNull(e.getValue(), JakartaServletRegistrationAdapter::new)));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return applyIfNonNull(delegate.addFilter(filterName, className), JakartaDynamicFilterRegistrationAdapter::new);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        return applyIfNonNull(
                delegate.addFilter(filterName, applyIfNonNull(filter, JavaXFilterAdapter::new)),
                JakartaDynamicFilterRegistrationAdapter::new);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
        // ClassCastException likely
        return applyIfNonNull(delegate.addFilter(filterName, (Class) filterClass), JakartaDynamicFilterRegistrationAdapter::new);
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) applyIfNonNull(delegate.createFilter((Class) clazz), JakartaFilterAdapter::new);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        return applyIfNonNull(delegate.getFilterRegistration(filterName), JakartaFilterRegistrationAdapter::new);
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        var filterRegistrations = delegate.getFilterRegistrations();
        if (filterRegistrations == null) {
            return null;
        }
        return filterRegistrations.entrySet().stream()
                .collect(toMap(Entry::getKey, e -> applyIfNonNull(e.getValue(), JakartaFilterRegistrationAdapter::new)));
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return applyIfNonNull(delegate.getSessionCookieConfig(), JakartaSessionCookieConfigAdapter::new);
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        delegate.setSessionTrackingModes(applyIfNonNull(sessionTrackingModes, JavaXServletContextAdapter::toJavaXSessionTrackingModeSet));
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return applyIfNonNull(delegate.getDefaultSessionTrackingModes(), JakartaServletContextAdapter::toJakartaSessionTrackingModeSet);
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return applyIfNonNull(delegate.getEffectiveSessionTrackingModes(), JakartaServletContextAdapter::toJakartaSessionTrackingModeSet);
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
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return applyIfNonNull(delegate.getJspConfigDescriptor(), JakartaJspConfigDescriptorAdapter::new);
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

    public static Set<SessionTrackingMode> toJakartaSessionTrackingModeSet(Collection<javax.servlet.SessionTrackingMode> sessionTrackingModes) {
        var result = EnumSet.noneOf(SessionTrackingMode.class);
        for (javax.servlet.SessionTrackingMode sessionTrackingMode : sessionTrackingModes) {
            result.add(SessionTrackingMode.valueOf(sessionTrackingMode.name()));
        }
        return result;
    }
}
