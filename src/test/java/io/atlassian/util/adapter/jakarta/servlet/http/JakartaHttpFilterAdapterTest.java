package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpFilterAdapter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaHttpFilterAdapterTest {

    @Mock
    private HttpFilter original;

    private HttpFilter biAdapted;

    @BeforeEach
    void setUp() throws Exception {
        biAdapted = new JakartaHttpFilterAdapter(JavaXHttpFilterAdapter.from(original));
    }

    @Test
    void getDelegate() {
        var adapted = JavaXHttpFilterAdapter.from(original);
        assertEquals(original, ((JavaXHttpFilterAdapter) adapted).getDelegate());

        var javaXOriginal = mock(javax.servlet.http.HttpFilter.class);
        var adapted2 = JakartaHttpFilterAdapter.from(javaXOriginal);
        assertEquals(javaXOriginal, ((JakartaHttpFilterAdapter) adapted2).getDelegate());
    }

    @Test
    void doubleAdaptShortCircuit() {
        var adapted = JavaXHttpFilterAdapter.from(original);
        var biAdapted = JakartaHttpFilterAdapter.from(adapted);
        assertEquals(original, biAdapted);
    }

    @Test
    void doFilter() throws Exception {
        var servletRequest = mock(ServletRequest.class);
        when(servletRequest.getRemoteAddr()).thenReturn("foo");

        biAdapted.doFilter(servletRequest, mock(ServletResponse.class), mock(FilterChain.class));

        verify(original).doFilter(argThat(sR -> sR.getRemoteAddr().equals("foo")), any(), any());
    }

    @Test
    void getInitParameter() {
        when(original.getInitParameter("foo")).thenReturn("bar");

        assertEquals("bar", biAdapted.getInitParameter("foo"));
    }

    @Test
    void getInitParameterNames() {
        when(original.getInitParameterNames()).thenReturn(Collections.enumeration(List.of("foo", "bar")));

        var names = biAdapted.getInitParameterNames();
        assertEquals("foo", names.nextElement());
        assertEquals("bar", names.nextElement());
    }

    @Test
    void getFilterConfig() {
        var retValue = mock(FilterConfig.class);
        when(retValue.getFilterName()).thenReturn("foo");
        when(original.getFilterConfig()).thenReturn(retValue);

        assertEquals("foo", biAdapted.getFilterConfig().getFilterName());
    }

    @Test
    void getServletContext() {
        var retValue = mock(ServletContext.class);
        when(retValue.getContextPath()).thenReturn("test");
        when(original.getServletContext()).thenReturn(retValue);

        assertEquals("test", biAdapted.getServletContext().getContextPath());
    }

    @Test
    void init() throws Exception {
        var filterConfig = mock(FilterConfig.class);
        when(filterConfig.getFilterName()).thenReturn("foo");

        biAdapted.init(filterConfig);

        verify(original).init(argThat(sC -> sC.getFilterName().equals("foo")));
    }

    @Test
    void testInit() throws Exception {
        biAdapted.init();

        verify(original).init();
    }

    @Test
    void getFilterName() {
        when(original.getFilterName()).thenReturn("foo");

        assertEquals("foo", biAdapted.getFilterName());
    }

    @Test
    void destroy() {
        biAdapted.destroy();

        verify(original).destroy();
    }
}
