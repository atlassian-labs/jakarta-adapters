package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXFilterRegistrationAdapter;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;

import java.util.Collection;
import java.util.EnumSet;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFilterRegistrationAdapter extends JakartaRegistrationAdapter implements FilterRegistration {

    private final javax.servlet.FilterRegistration delegate;

    public static FilterRegistration from(javax.servlet.FilterRegistration delegate) {
        if (delegate instanceof javax.servlet.FilterRegistration.Dynamic castDelegate) {
            return JakartaDynamicFilterRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXFilterRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaFilterRegistrationAdapter::new);
    }

    JakartaFilterRegistrationAdapter(javax.servlet.FilterRegistration delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.FilterRegistration getDelegate() {
        return delegate;
    }

    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes,
                                          boolean isMatchAfter,
                                          String... servletNames) {
        delegate.addMappingForServletNames(
                JakartaFilterRegistrationAdapter.toJavaXDispatcherTypeSet(dispatcherTypes),
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
                JakartaFilterRegistrationAdapter.toJavaXDispatcherTypeSet(dispatcherTypes),
                isMatchAfter,
                urlPatterns);
    }

    @Override
    public Collection<String> getUrlPatternMappings() {
        return delegate.getUrlPatternMappings();
    }

    private static EnumSet<javax.servlet.DispatcherType> toJavaXDispatcherTypeSet(Collection<DispatcherType> dispatcherTypes) {
        if (dispatcherTypes == null) {
            return null;
        }
        var result = EnumSet.noneOf(javax.servlet.DispatcherType.class);
        for (DispatcherType dispatcherType : dispatcherTypes) {
            result.add(javax.servlet.DispatcherType.valueOf(dispatcherType.name()));
        }
        return result;
    }
}
