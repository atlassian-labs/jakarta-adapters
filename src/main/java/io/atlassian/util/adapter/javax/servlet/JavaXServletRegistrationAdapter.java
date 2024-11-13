package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRegistrationAdapter;

import javax.servlet.ServletRegistration;
import java.util.Collection;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletRegistrationAdapter extends JavaXRegistrationAdapter implements ServletRegistration {

    private final jakarta.servlet.ServletRegistration delegate;

    public static ServletRegistration from(jakarta.servlet.ServletRegistration delegate) {
        if (delegate instanceof jakarta.servlet.ServletRegistration.Dynamic castDelegate) {
            return JavaXDynamicServletRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof JakartaServletRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletRegistrationAdapter::new);
    }

    protected JavaXServletRegistrationAdapter(jakarta.servlet.ServletRegistration delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.ServletRegistration getDelegate() {
        return delegate;
    }

    @Override
    public Set<String> addMapping(String... urlPatterns) {
        return delegate.addMapping(urlPatterns);
    }

    @Override
    public Collection<String> getMappings() {
        return delegate.getMappings();
    }

    @Override
    public String getRunAsRole() {
        return delegate.getRunAsRole();
    }
}
