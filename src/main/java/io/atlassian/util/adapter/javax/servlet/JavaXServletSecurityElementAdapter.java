package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.HttpMethodConstraintElement;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletSecurityElementAdapter extends ServletSecurityElement {

    private final jakarta.servlet.ServletSecurityElement delegate;

    public JavaXServletSecurityElementAdapter(jakarta.servlet.ServletSecurityElement delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Collection<HttpMethodConstraintElement> getHttpMethodConstraints() {
        return transformIfNonNull(delegate.getHttpMethodConstraints(), JavaXHttpMethodConstraintElementAdapter::new);
    }

    @Override
    public Collection<String> getMethodNames() {
        return delegate.getMethodNames();
    }

    @Override
    public ServletSecurity.EmptyRoleSemantic getEmptyRoleSemantic() {
        return ServletSecurity.EmptyRoleSemantic.valueOf(delegate.getEmptyRoleSemantic().name());
    }

    @Override
    public ServletSecurity.TransportGuarantee getTransportGuarantee() {
        return ServletSecurity.TransportGuarantee.valueOf(delegate.getTransportGuarantee().name());
    }

    @Override
    public String[] getRolesAllowed() {
        return delegate.getRolesAllowed();
    }
}
