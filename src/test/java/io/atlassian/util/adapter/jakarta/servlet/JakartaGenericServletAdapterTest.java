package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXGenericServletAdapter;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaGenericServletAdapterTest {

    @Mock
    private GenericServlet original;

    private GenericServlet biAdapted;

    @BeforeEach
    void setUp() throws Exception {
        biAdapted = JakartaGenericServletAdapter.from(JavaXGenericServletAdapter.from(original));
    }

    @Test
    void from() {
        original = mock(HttpServlet.class);
        biAdapted = JakartaGenericServletAdapter.from(JavaXGenericServletAdapter.from(original));

        assertThat(biAdapted).isInstanceOf(HttpServlet.class);
    }

    @Test
    void getDelegate() {
        var adapted = (JavaXGenericServletAdapter) JavaXGenericServletAdapter.from(original);
        assertEquals(original, adapted.getDelegate());

        var javaXOriginal = mock(javax.servlet.GenericServlet.class);
        var adaptedRequest = (JakartaGenericServletAdapter) JakartaGenericServletAdapter.from(javaXOriginal);
        assertEquals(javaXOriginal, adaptedRequest.getDelegate());
    }

    @Test
    void doubleAdaptShortCircuit() {
        var adapted = (JavaXGenericServletAdapter) JavaXGenericServletAdapter.from(original);
        var biAdaptedRequest = JakartaGenericServletAdapter.from(adapted);
        assertEquals(original, biAdaptedRequest);
    }

    @Test
    void service() throws Exception {
        var servletRequest = mock(ServletRequest.class);
        when(servletRequest.getRemoteAddr()).thenReturn("foo");

        biAdapted.service(servletRequest, mock(ServletResponse.class));

        verify(original).service(argThat(sR -> sR.getRemoteAddr().equals("foo")), any());
    }

    @Test
    void getServletName() {
        when(original.getServletName()).thenReturn("foo");

        assertEquals("foo", biAdapted.getServletName());
    }

    @Test
    void log() {
        biAdapted.log("foo");

        verify(original).log("foo");
    }

    @Test
    void testLog() {
        var e = new Exception();
        biAdapted.log("foo", e);

        verify(original).log("foo", e);
    }

    @Test
    void init() throws Exception {
        var servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletName()).thenReturn("foo");

        biAdapted.init(servletConfig);

        verify(original).init(argThat(sC -> sC.getServletName().equals("foo")));
    }

    @Test
    void testInit() throws Exception {
        biAdapted.init();

        verify(original).init();
    }

    @Test
    void getServletInfo() {
        when(original.getServletInfo()).thenReturn("foo");

        assertEquals("foo", biAdapted.getServletInfo());
    }

    @Test
    void getServletContext() {
        var retValue = mock(ServletContext.class);
        when(retValue.getContextPath()).thenReturn("test");
        when(original.getServletContext()).thenReturn(retValue);

        assertEquals("test", biAdapted.getServletContext().getContextPath());
    }

    @Test
    void getServletConfig() {
        var servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletName()).thenReturn("foo");
        when(original.getServletConfig()).thenReturn(servletConfig);

        assertEquals("foo", biAdapted.getServletConfig().getServletName());
    }

    @Test
    void getInitParameterNames() {
        when(original.getInitParameterNames()).thenReturn(Collections.enumeration(List.of("foo", "bar")));

        var names = biAdapted.getInitParameterNames();
        assertEquals("foo", names.nextElement());
        assertEquals("bar", names.nextElement());
    }

    @Test
    void getInitParameter() {
        when(original.getInitParameter("foo")).thenReturn("bar");

        assertEquals("bar", biAdapted.getInitParameter("foo"));
    }

    @Test
    void destroy() {
        biAdapted.destroy();

        verify(original).destroy();
    }
}
