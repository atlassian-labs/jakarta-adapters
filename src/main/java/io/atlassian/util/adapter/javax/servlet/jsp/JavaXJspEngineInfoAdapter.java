package io.atlassian.util.adapter.javax.servlet.jsp;

import io.atlassian.util.adapter.jakarta.servlet.jsp.JakartaJspEngineInfoAdapter;

import javax.servlet.jsp.JspEngineInfo;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXJspEngineInfoAdapter extends JspEngineInfo {

    private final jakarta.servlet.jsp.JspEngineInfo delegate;

    public static JspEngineInfo from(jakarta.servlet.jsp.JspEngineInfo delegate) {
        if (delegate instanceof JakartaJspEngineInfoAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXJspEngineInfoAdapter::new);
    }

    JavaXJspEngineInfoAdapter(jakarta.servlet.jsp.JspEngineInfo delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.jsp.JspEngineInfo getDelegate() {
        return delegate;
    }

    @Override
    public String getSpecificationVersion() {
        return delegate.getSpecificationVersion();
    }
}
