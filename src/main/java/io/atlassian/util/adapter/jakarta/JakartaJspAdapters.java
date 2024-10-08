package io.atlassian.util.adapter.jakarta;

import io.atlassian.util.adapter.jakarta.el.JakartaELContextAdapter;
import io.atlassian.util.adapter.jakarta.servlet.jsp.JakartaJspFactoryAdapter;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaJspAdapters {

    private JakartaJspAdapters() {
    }

    public static jakarta.servlet.jsp.JspFactory asJakartaJsp(javax.servlet.jsp.JspFactory delegate) {
        return applyIfNonNull(delegate, JakartaJspFactoryAdapter::new);
    }

    public static jakarta.el.ELContext asJakartaJsp(javax.el.ELContext delegate) {
        return applyIfNonNull(delegate, JakartaELContextAdapter::new);
    }
}
