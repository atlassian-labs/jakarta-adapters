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
        if (delegate instanceof jakarta.servlet.ServletRequest cast) {
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.ServletResponse cast) {
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.ServletContext cast) {
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.Servlet cast) {
            return asJavaX(cast);
        } else if (delegate instanceof jakarta.servlet.Filter cast) {
            return asJavaX(cast);
        }
        return delegate;
    }

    public static javax.servlet.Filter asJavaX(jakarta.servlet.Filter delegate) {
        return applyIfNonNull(delegate, JavaXFilterAdapter::new);
    }

    public static javax.servlet.FilterChain asJavaX(jakarta.servlet.FilterChain delegate) {
        return applyIfNonNull(delegate, JavaXFilterChainAdapter::new);
    }

    public static javax.servlet.FilterConfig asJavaX(jakarta.servlet.FilterConfig delegate) {
        return applyIfNonNull(delegate, JavaXFilterConfigAdapter::new);
    }

    public static javax.servlet.Servlet asJavaX(jakarta.servlet.Servlet delegate) {
        return applyIfNonNull(delegate, JavaXServletAdapter::new);
    }

    public static javax.servlet.ServletConfig asJavaX(jakarta.servlet.ServletConfig delegate) {
        return applyIfNonNull(delegate, JavaXServletConfigAdapter::new);
    }

    public static javax.servlet.http.HttpServletRequest asJavaX(jakarta.servlet.http.HttpServletRequest delegate) {
        return applyIfNonNull(delegate, JavaXHttpServletRequestAdapter::new);
    }

    public static javax.servlet.ServletRequest asJavaX(jakarta.servlet.ServletRequest delegate) {
        return applyIfNonNull(delegate, JavaXServletRequestAdapter::from);
    }

    public static javax.servlet.http.HttpServletResponse asJavaX(jakarta.servlet.http.HttpServletResponse delegate) {
        return applyIfNonNull(delegate, JavaXHttpServletResponseAdapter::new);
    }

    public static javax.servlet.ServletResponse asJavaX(jakarta.servlet.ServletResponse delegate) {
        return applyIfNonNull(delegate, JavaXServletResponseAdapter::from);
    }

    public static javax.servlet.ServletContext asJavaX(jakarta.servlet.ServletContext delegate) {
        return applyIfNonNull(delegate, JavaXServletContextAdapter::new);
    }

    public static javax.servlet.ServletContextListener asJavaX(jakarta.servlet.ServletContextListener delegate) {
        return applyIfNonNull(delegate, JavaXServletContextListenerAdapter::new);
    }
}
