package io.atlassian.util.adapter.jakarta;

import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterChainAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterConfigAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletConfigAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextListenerAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletResponseAdapter;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletRequestAdapter;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletResponseAdapter;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpSessionAdapter;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public final class JakartaAdapters {

    private JakartaAdapters() {
    }

    /**
     * Converts a potential JavaX object to its corresponding Jakarta object. Only supports:
     * <ul>
     *     <li>{@link javax.servlet.http.HttpServletRequest}</li>
     *     <li>{@link javax.servlet.ServletRequest}</li>
     *     <li>{@link javax.servlet.http.HttpServletResponse}</li>
     *     <li>{@link javax.servlet.ServletResponse}</li>
     *     <li>{@link javax.servlet.ServletContext}</li>
     *     <li>{@link javax.servlet.Servlet}</li>
     *     <li>{@link javax.servlet.Filter}</li>
     * </ul>
     */
    public static Object asJakartaIfJavaX(Object delegate) {
        if (delegate instanceof javax.servlet.ServletRequest) {
            javax.servlet.ServletRequest cast = (javax.servlet.ServletRequest) delegate;
            return asJakarta(cast);
        } else if (delegate instanceof javax.servlet.ServletResponse) {
            javax.servlet.ServletResponse cast = (javax.servlet.ServletResponse) delegate;
            return asJakarta(cast);
        } else if (delegate instanceof javax.servlet.ServletContext) {
            javax.servlet.ServletContext cast = (javax.servlet.ServletContext) delegate;
            return asJakarta(cast);
        } else if (delegate instanceof javax.servlet.Servlet) {
            javax.servlet.Servlet cast = (javax.servlet.Servlet) delegate;
            return asJakarta(cast);
        } else if (delegate instanceof javax.servlet.Filter) {
            javax.servlet.Filter cast = (javax.servlet.Filter) delegate;
            return asJakarta(cast);
        } else if (delegate instanceof javax.servlet.http.HttpSession) {
            javax.servlet.http.HttpSession cast = (javax.servlet.http.HttpSession) delegate;
            return asJakarta(cast);
        }
        return delegate;
    }

    public static jakarta.servlet.Filter asJakarta(javax.servlet.Filter delegate) {
        return applyIfNonNull(delegate, JakartaFilterAdapter::from);
    }

    public static jakarta.servlet.FilterChain asJakarta(javax.servlet.FilterChain delegate) {
        return applyIfNonNull(delegate, JakartaFilterChainAdapter::from);
    }

    public static jakarta.servlet.FilterConfig asJakarta(javax.servlet.FilterConfig delegate) {
        return applyIfNonNull(delegate, JakartaFilterConfigAdapter::from);
    }

    public static jakarta.servlet.Servlet asJakarta(javax.servlet.Servlet delegate) {
        return applyIfNonNull(delegate, JakartaServletAdapter::from);
    }

    public static jakarta.servlet.ServletConfig asJakarta(javax.servlet.ServletConfig delegate) {
        return applyIfNonNull(delegate, JakartaServletConfigAdapter::from);
    }

    public static jakarta.servlet.http.HttpSession asJakarta(javax.servlet.http.HttpSession delegate) {
        return applyIfNonNull(delegate, JakartaHttpSessionAdapter::from);
    }

    public static jakarta.servlet.http.HttpServletRequest asJakarta(javax.servlet.http.HttpServletRequest delegate) {
        return applyIfNonNull(delegate, JakartaHttpServletRequestAdapter::from);
    }

    public static jakarta.servlet.ServletRequest asJakarta(javax.servlet.ServletRequest delegate) {
        return applyIfNonNull(delegate, JakartaServletRequestAdapter::from);
    }

    public static jakarta.servlet.http.HttpServletResponse asJakarta(javax.servlet.http.HttpServletResponse delegate) {
        return applyIfNonNull(delegate, JakartaHttpServletResponseAdapter::from);
    }

    public static jakarta.servlet.ServletResponse asJakarta(javax.servlet.ServletResponse delegate) {
        return applyIfNonNull(delegate, JakartaServletResponseAdapter::from);
    }

    public static jakarta.servlet.ServletContext asJakarta(javax.servlet.ServletContext delegate) {
        return applyIfNonNull(delegate, JakartaServletContextAdapter::from);
    }

    public static jakarta.servlet.ServletContextListener asJakarta(javax.servlet.ServletContextListener delegate) {
        return applyIfNonNull(delegate, JakartaServletContextListenerAdapter::from);
    }
}
