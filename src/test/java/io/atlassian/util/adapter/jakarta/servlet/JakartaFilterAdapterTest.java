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
public class JakartaFilterAdapterTest {

    @Mock
    private Filter originalFilter;

    private Filter biAdaptedFilter;

    @BeforeEach
    public void setUp() throws Exception {
        biAdaptedFilter = new JakartaFilterAdapter(new JavaXFilterAdapter(originalFilter));
    }

    @Test
    public void init() throws Exception {
        var filterConfig = mock(FilterConfig.class);
        when(filterConfig.getFilterName()).thenReturn("foo");

        biAdaptedFilter.init(filterConfig);

        verify(originalFilter).init(argThat(fC -> fC.getFilterName().equals("foo")));
    }

    @Test
    public void doFilter() throws Exception {
        var servletRequest = mock(ServletRequest.class);
        when(servletRequest.getRemoteAddr()).thenReturn("foo");

        biAdaptedFilter.doFilter(servletRequest, mock(ServletResponse.class), mock(FilterChain.class));

        verify(originalFilter).doFilter(argThat(sR -> sR.getRemoteAddr().equals("foo")), any(), any());
    }

    @Test
    public void destroy() {
        biAdaptedFilter.destroy();

        verify(originalFilter).destroy();
    }

    @Test
    public void getDelegate() {
        var adaptedFilter = new JavaXFilterAdapter(originalFilter);
        assertEquals(originalFilter, adaptedFilter.getDelegate());

        var biAdaptedFilter = new JakartaFilterAdapter(adaptedFilter);
        assertEquals(adaptedFilter, biAdaptedFilter.getDelegate());
    }
}
