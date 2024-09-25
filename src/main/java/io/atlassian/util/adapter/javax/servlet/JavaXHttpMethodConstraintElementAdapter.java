package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.HttpMethodConstraintElement;
import javax.servlet.annotation.ServletSecurity;

import static java.util.Objects.requireNonNull;

public class JavaXHttpMethodConstraintElementAdapter extends HttpMethodConstraintElement {

    private final jakarta.servlet.HttpMethodConstraintElement delegate;

    public JavaXHttpMethodConstraintElementAdapter(jakarta.servlet.HttpMethodConstraintElement delegate) {
        super("null");
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getMethodName() {
        return delegate.getMethodName();
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
