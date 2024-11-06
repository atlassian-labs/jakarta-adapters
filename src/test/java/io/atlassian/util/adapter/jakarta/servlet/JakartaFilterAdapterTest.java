package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXFilterAdapter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaFilterAdapterTest {

    @Mock
    private Filter originalFilter;

    private Filter biAdaptedFilter;

    @BeforeEach
    void setUp() throws Exception {
        biAdaptedFilter = new JakartaFilterAdapter(JavaXFilterAdapter.from(originalFilter));
    }

    @Test
    void init() throws Exception {
        var filterConfig = mock(FilterConfig.class);
        when(filterConfig.getFilterName()).thenReturn("foo");

        biAdaptedFilter.init(filterConfig);

        verify(originalFilter).init(argThat(fC -> fC.getFilterName().equals("foo")));
    }

    @Test
    void doFilter() throws Exception {
        var servletRequest = mock(ServletRequest.class);
        when(servletRequest.getRemoteAddr()).thenReturn("foo");

        biAdaptedFilter.doFilter(servletRequest, mock(ServletResponse.class), mock(FilterChain.class));

        verify(originalFilter).doFilter(argThat(sR -> sR.getRemoteAddr().equals("foo")), any(), any());
    }

    @Test
    void destroy() {
        biAdaptedFilter.destroy();

        verify(originalFilter).destroy();
    }

    @Test
    void getDelegate() {
        var adaptedFilter = JavaXFilterAdapter.from(originalFilter);
        assertEquals(originalFilter, ((JavaXFilterAdapter) adaptedFilter).getDelegate());

        var javaXOriginal = mock(javax.servlet.Filter.class);
        var adaptedFilter2 = JakartaFilterAdapter.from(javaXOriginal);
        assertEquals(javaXOriginal, ((JakartaFilterAdapter) adaptedFilter2).getDelegate());
    }

    @Test
    void doubleAdaptShortCircuit() {
        var adaptedFilter = JavaXFilterAdapter.from(originalFilter);
        var biAdaptedFilter = JakartaFilterAdapter.from(adaptedFilter);
        assertEquals(originalFilter, biAdaptedFilter);
    }
}
