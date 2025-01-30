package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaDynamicServletRegistrationAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaMultipartConfigElementAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletSecurityElementAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXDynamicServletRegistrationAdapter extends JavaXServletRegistrationAdapter implements ServletRegistration.Dynamic {

    private final jakarta.servlet.ServletRegistration.Dynamic delegate;

    public static ServletRegistration.Dynamic from(jakarta.servlet.ServletRegistration.Dynamic delegate) {
        if (delegate instanceof JakartaDynamicServletRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXDynamicServletRegistrationAdapter::new);
    }

    protected JavaXDynamicServletRegistrationAdapter(jakarta.servlet.ServletRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.ServletRegistration.Dynamic getDelegate() {
        return delegate;
    }

    @Override
    public void setLoadOnStartup(int loadOnStartup) {
        delegate.setLoadOnStartup(loadOnStartup);
    }

    @Override
    public Set<String> setServletSecurity(ServletSecurityElement constraint) {
        return delegate.setServletSecurity(JakartaServletSecurityElementAdapter.from(constraint));
    }

    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfig) {
        delegate.setMultipartConfig(JakartaMultipartConfigElementAdapter.from(multipartConfig));
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
