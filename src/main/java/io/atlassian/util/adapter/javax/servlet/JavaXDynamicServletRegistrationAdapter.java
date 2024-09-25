package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaMultipartConfigElementAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletSecurityElementAdapter;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXDynamicServletRegistrationAdapter extends JavaXServletRegistrationAdapter implements ServletRegistration.Dynamic {

    private final jakarta.servlet.ServletRegistration.Dynamic delegate;

    public JavaXDynamicServletRegistrationAdapter(jakarta.servlet.ServletRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void setLoadOnStartup(int loadOnStartup) {
        delegate.setLoadOnStartup(loadOnStartup);
    }

    @Override
    public Set<String> setServletSecurity(ServletSecurityElement constraint) {
        return delegate.setServletSecurity(applyIfNonNull(constraint, JakartaServletSecurityElementAdapter::new));
    }

    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfig) {
        delegate.setMultipartConfig(applyIfNonNull(multipartConfig, JakartaMultipartConfigElementAdapter::new));
    }

    @Override
    public void setRunAsRole(String roleName) {
        delegate.setRunAsRole(roleName);
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        delegate.setAsyncSupported(isAsyncSupported);
    }
}
