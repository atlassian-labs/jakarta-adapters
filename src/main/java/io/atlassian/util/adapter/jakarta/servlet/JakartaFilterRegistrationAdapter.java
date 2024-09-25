package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;

import java.util.Collection;
import java.util.EnumSet;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFilterRegistrationAdapter extends JakartaRegistrationAdapter implements FilterRegistration {

    private final javax.servlet.FilterRegistration delegate;

    public JakartaFilterRegistrationAdapter(javax.servlet.FilterRegistration delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes,
                                          boolean isMatchAfter,
                                          String... servletNames) {
        delegate.addMappingForServletNames(
                applyIfNonNull(dispatcherTypes, JakartaFilterRegistrationAdapter::toJavaXDispatcherTypeSet),
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
                applyIfNonNull(dispatcherTypes, JakartaFilterRegistrationAdapter::toJavaXDispatcherTypeSet),
                isMatchAfter,
                urlPatterns);
    }

    @Override
    public Collection<String> getUrlPatternMappings() {
        return delegate.getUrlPatternMappings();
    }

    private static EnumSet<javax.servlet.DispatcherType> toJavaXDispatcherTypeSet(Collection<DispatcherType> dispatcherTypes) {
        var result = EnumSet.noneOf(javax.servlet.DispatcherType.class);
        for (DispatcherType dispatcherType : dispatcherTypes) {
            result.add(javax.servlet.DispatcherType.valueOf(dispatcherType.name()));
        }
        return result;
    }
}
