package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspEngineInfoAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.jsp.JspEngineInfo;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaJspEngineInfoAdapter extends JspEngineInfo implements Adapted<javax.servlet.jsp.JspEngineInfo> {

    private final javax.servlet.jsp.JspEngineInfo delegate;

    public static JspEngineInfo from(javax.servlet.jsp.JspEngineInfo delegate) {
        if (delegate instanceof JavaXJspEngineInfoAdapter) {
            JavaXJspEngineInfoAdapter castDelegate = (JavaXJspEngineInfoAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaJspEngineInfoAdapter::new);
    }

    protected JakartaJspEngineInfoAdapter(javax.servlet.jsp.JspEngineInfo delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.jsp.JspEngineInfo getDelegate() {
        return delegate;
    }

    @Override
    public String getSpecificationVersion() {
        return delegate.getSpecificationVersion();
    }

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
