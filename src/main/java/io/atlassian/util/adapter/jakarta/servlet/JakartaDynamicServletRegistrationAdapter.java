package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXDynamicServletRegistrationAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXMultipartConfigElementAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletSecurityElementAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletSecurityElement;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaDynamicServletRegistrationAdapter extends JakartaServletRegistrationAdapter implements ServletRegistration.Dynamic {

    private final javax.servlet.ServletRegistration.Dynamic delegate;

    public static ServletRegistration.Dynamic from(javax.servlet.ServletRegistration.Dynamic delegate) {
        if (delegate instanceof JavaXDynamicServletRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaDynamicServletRegistrationAdapter::new);
    }

    protected JakartaDynamicServletRegistrationAdapter(javax.servlet.ServletRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.ServletRegistration.Dynamic getDelegate() {
        return delegate;
    }

    @Override
    public void setLoadOnStartup(int loadOnStartup) {
        delegate.setLoadOnStartup(loadOnStartup);
    }

    @Override
    public Set<String> setServletSecurity(ServletSecurityElement constraint) {
        return delegate.setServletSecurity(JavaXServletSecurityElementAdapter.from(constraint));
    }

    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfig) {
        delegate.setMultipartConfig(JavaXMultipartConfigElementAdapter.from(multipartConfig));
    }

    @Override
    public void setRunAsRole(String roleName) {
        delegate.setRunAsRole(roleName);
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        delegate.setAsyncSupported(isAsyncSupported);
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
