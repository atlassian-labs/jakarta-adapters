package io.atlassian.util.adapter.javax;

import io.atlassian.util.adapter.javax.servlet.JavaXFilterAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXFilterChainAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXFilterConfigAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletConfigAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletContextAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletContextListenerAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletResponseAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletResponseAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpSessionAdapter;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXAdapters {

    private JavaXAdapters() {
    }

    /**
     * Converts a potential Jakarta object to its corresponding JavaX object. Only supports:
     * <ul>
     *     <li>{@link jakarta.servlet.http.HttpServletRequest}</li>
     *     <li>{@link jakarta.servlet.ServletRequest}</li>
     *     <li>{@link jakarta.servlet.http.HttpServletResponse}</li>
     *     <li>{@link jakarta.servlet.ServletResponse}</li>
     *     <li>{@link jakarta.servlet.ServletContext}</li>
     *     <li>{@link jakarta.servlet.Servlet}</li>
     *     <li>{@link jakarta.servlet.Filter}</li>
     * </ul>
     */
    public static Object asJavaXIfJakarta(Object delegate) {
        if (delegate instanceof jakarta.servlet.ServletRequest) {
            jakarta.servlet.ServletRequest cast = (jakarta.servlet.ServletRequest) delegate;
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.ServletResponse) {
            jakarta.servlet.ServletResponse cast = (jakarta.servlet.ServletResponse) delegate;
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.ServletContext) {
            jakarta.servlet.ServletContext cast = (jakarta.servlet.ServletContext) delegate;
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.Servlet) {
            jakarta.servlet.Servlet cast = (jakarta.servlet.Servlet) delegate;
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.Filter) {
            jakarta.servlet.Filter cast = (jakarta.servlet.Filter) delegate;
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.http.HttpSession) {
            jakarta.servlet.http.HttpSession cast = (jakarta.servlet.http.HttpSession) delegate;
            return asJavaX(cast);
        }
        return delegate;
    }

    public static javax.servlet.Filter asJavaX(jakarta.servlet.Filter delegate) {
        return applyIfNonNull(delegate, JavaXFilterAdapter::from);
    }

    public static javax.servlet.FilterChain asJavaX(jakarta.servlet.FilterChain delegate) {
        return applyIfNonNull(delegate, JavaXFilterChainAdapter::from);
    }

    public static javax.servlet.FilterConfig asJavaX(jakarta.servlet.FilterConfig delegate) {
        return applyIfNonNull(delegate, JavaXFilterConfigAdapter::from);
    }

    public static javax.servlet.Servlet asJavaX(jakarta.servlet.Servlet delegate) {
        return applyIfNonNull(delegate, JavaXServletAdapter::from);
    }

    public static javax.servlet.ServletConfig asJavaX(jakarta.servlet.ServletConfig delegate) {
        return applyIfNonNull(delegate, JavaXServletConfigAdapter::from);
    }

    public static javax.servlet.http.HttpSession asJavaX(jakarta.servlet.http.HttpSession delegate) {
        return applyIfNonNull(delegate, JavaXHttpSessionAdapter::from);
    }

    public static javax.servlet.http.HttpServletRequest asJavaX(jakarta.servlet.http.HttpServletRequest delegate) {
        return applyIfNonNull(delegate, JavaXHttpServletRequestAdapter::from);
    }

    public static javax.servlet.ServletRequest asJavaX(jakarta.servlet.ServletRequest delegate) {
        return applyIfNonNull(delegate, JavaXServletRequestAdapter::from);
    }

    public static javax.servlet.http.HttpServletResponse asJavaX(jakarta.servlet.http.HttpServletResponse delegate) {
        return applyIfNonNull(delegate, JavaXHttpServletResponseAdapter::from);
    }

    public static javax.servlet.ServletResponse asJavaX(jakarta.servlet.ServletResponse delegate) {
        return applyIfNonNull(delegate, JavaXServletResponseAdapter::from);
    }

    public static javax.servlet.ServletContext asJavaX(jakarta.servlet.ServletContext delegate) {
        return applyIfNonNull(delegate, JavaXServletContextAdapter::from);
    }

    public static javax.servlet.ServletContextListener asJavaX(jakarta.servlet.ServletContextListener delegate) {
        return applyIfNonNull(delegate, JavaXServletContextListenerAdapter::from);
    }
}
