package io.atlassian.util.adapter.jakarta;

import io.atlassian.util.adapter.jakarta.el.JakartaELContextAdapter;
import io.atlassian.util.adapter.jakarta.servlet.jsp.JakartaJspFactoryAdapter;

public class JakartaJspAdapters {

    private JakartaJspAdapters() {
    }

    public static jakarta.servlet.jsp.JspFactory asJakartaJsp(javax.servlet.jsp.JspFactory delegate) {
        return JakartaJspFactoryAdapter.from(delegate);
    }

    public static jakarta.el.ELContext asJakartaJsp(javax.el.ELContext delegate) {
        return JakartaELContextAdapter.from(delegate);
    }
}
