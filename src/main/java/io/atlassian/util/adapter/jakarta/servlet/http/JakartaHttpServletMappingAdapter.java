package io.atlassian.util.adapter.jakarta.servlet.http;

import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.MappingMatch;

import static java.util.Objects.requireNonNull;

public class JakartaHttpServletMappingAdapter implements HttpServletMapping {

    private final javax.servlet.http.HttpServletMapping delegate;

    public JakartaHttpServletMappingAdapter(javax.servlet.http.HttpServletMapping delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getMatchValue() {
        return delegate.getMatchValue();
    }

    @Override
    public String getPattern() {
        return delegate.getPattern();
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public MappingMatch getMappingMatch() {
        return MappingMatch.valueOf(delegate.getMappingMatch().name());
    }
}
