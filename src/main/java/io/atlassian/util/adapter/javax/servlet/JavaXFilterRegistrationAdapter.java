package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterRegistrationAdapter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.Collection;
import java.util.EnumSet;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXFilterRegistrationAdapter extends JavaXRegistrationAdapter implements FilterRegistration {

    private final jakarta.servlet.FilterRegistration delegate;

    public static FilterRegistration from(jakarta.servlet.FilterRegistration delegate) {
        if (delegate instanceof jakarta.servlet.FilterRegistration.Dynamic castDelegate) {
            return JavaXDynamicFilterRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof JakartaFilterRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXFilterRegistrationAdapter::new);
    }

    protected JavaXFilterRegistrationAdapter(jakarta.servlet.FilterRegistration delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.FilterRegistration getDelegate() {
        return delegate;
    }

    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes,
                                          boolean isMatchAfter,
                                          String... servletNames) {
        delegate.addMappingForServletNames(
                JavaXFilterRegistrationAdapter.toJakartaDispatcherTypeSet(dispatcherTypes),
                isMatchAfter,
                servletNames);
    }

    @Override
    public Collection<String> getServletNameMappings() {
        return delegate.getServletNameMappings();
    }

    @Override
    public void addMappingForUrlPatterns(EnumSet<DispatcherType> dispatcherTypes,
                                         boolean isMatchAfter,
                                         String... urlPatterns) {
        delegate.addMappingForUrlPatterns(
                JavaXFilterRegistrationAdapter.toJakartaDispatcherTypeSet(dispatcherTypes),
                isMatchAfter,
                urlPatterns);
    }

    @Override
    public Collection<String> getUrlPatternMappings() {
        return delegate.getUrlPatternMappings();
    }

    private static EnumSet<jakarta.servlet.DispatcherType> toJakartaDispatcherTypeSet(Collection<DispatcherType> dispatcherTypes) {
        if (dispatcherTypes == null) {
            return null;
        }
        var result = EnumSet.noneOf(jakarta.servlet.DispatcherType.class);
        for (DispatcherType dispatcherType : dispatcherTypes) {
            result.add(jakarta.servlet.DispatcherType.valueOf(dispatcherType.name()));
        }
        return result;
    }
}
