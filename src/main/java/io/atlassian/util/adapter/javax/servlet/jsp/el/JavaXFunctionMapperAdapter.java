package io.atlassian.util.adapter.javax.servlet.jsp.el;

import javax.servlet.jsp.el.FunctionMapper;
import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

public class JavaXFunctionMapperAdapter implements FunctionMapper {

    private final jakarta.servlet.jsp.el.FunctionMapper delegate;

    public JavaXFunctionMapperAdapter(jakarta.servlet.jsp.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Method resolveFunction(String s, String s1) {
        return delegate.resolveFunction(s, s1);
    }
}
