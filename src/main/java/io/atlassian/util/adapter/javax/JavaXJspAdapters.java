package io.atlassian.util.adapter.javax;

import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspFactoryAdapter;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXJspAdapters {
    private JavaXJspAdapters() {
    }

    public static javax.servlet.jsp.JspFactory asJavaXJsp(jakarta.servlet.jsp.JspFactory delegate) {
        return applyIfNonNull(delegate, JavaXJspFactoryAdapter::new);
    }
}
