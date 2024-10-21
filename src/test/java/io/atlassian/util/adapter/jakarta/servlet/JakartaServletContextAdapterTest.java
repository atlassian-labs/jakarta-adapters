package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletContextAdapter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.TaglibDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.net.URL;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.enumeration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaServletContextAdapterTest {

    @Mock
    private ServletContext originalContext;

    private ServletContext biAdaptedContext;

    @BeforeEach
    void setUp() throws Exception {
        biAdaptedContext = new JakartaServletContextAdapter(JavaXServletContextAdapter.from(originalContext));
    }

    @Test
    void getContextPath() {
        when(originalContext.getContextPath()).thenReturn("/context");

        assertEquals("/context", biAdaptedContext.getContextPath());
    }

    @Test
    void getContext() {
        var retValue = mock(ServletContext.class);
        when(retValue.getContextPath()).thenReturn("/anotherContext");
        when(originalContext.getContext("/context")).thenReturn(retValue);

        assertEquals("/anotherContext", biAdaptedContext.getContext("/context").getContextPath());
    }

    @Test
    void getMajorVersion() {
        when(originalContext.getMajorVersion()).thenReturn(3);

        assertEquals(3, biAdaptedContext.getMajorVersion());
    }

    @Test
    void getMinorVersion() {
        when(originalContext.getMinorVersion()).thenReturn(1);

        assertEquals(1, biAdaptedContext.getMinorVersion());
    }

    @Test
    void getEffectiveMajorVersion() {
        when(originalContext.getEffectiveMajorVersion()).thenReturn(4);

        assertEquals(4, biAdaptedContext.getEffectiveMajorVersion());
    }

    @Test
    void getEffectiveMinorVersion() {
        when(originalContext.getEffectiveMinorVersion()).thenReturn(2);

        assertEquals(2, biAdaptedContext.getEffectiveMinorVersion());
    }

    @Test
    void getMimeType() {
        when(originalContext.getMimeType("file.txt")).thenReturn("text/plain");

        assertEquals("text/plain", biAdaptedContext.getMimeType("file.txt"));
    }

    @Test
    void getResourcePaths() {
        when(originalContext.getResourcePaths("/path")).thenReturn(Set.of("/path/file1", "/path/file2"));

        assertEquals(Set.of("/path/file1", "/path/file2"), biAdaptedContext.getResourcePaths("/path"));
    }

    @Test
    void getResource() throws Exception {
        var retValue = mock(URL.class);
        when(originalContext.getResource("/path/file.txt")).thenReturn(retValue);

        assertEquals(retValue, biAdaptedContext.getResource("/path/file.txt"));
    }

    @Test
    void getResourceAsStream() {
        var retValue = mock(InputStream.class);
        when(originalContext.getResourceAsStream("/path/file.txt")).thenReturn(retValue);

        assertEquals(retValue, biAdaptedContext.getResourceAsStream("/path/file.txt"));
    }

    @Test
    void getRequestDispatcher() throws Exception {
        var retValue = mock(RequestDispatcher.class);
        when(originalContext.getRequestDispatcher("/path")).thenReturn(retValue);

        var mockRequest = mock(ServletRequest.class);
        when(mockRequest.getAttribute("test")).thenReturn("value");
        var mockResponse = mock(ServletResponse.class);
        biAdaptedContext.getRequestDispatcher("/path").forward(mockRequest, mockResponse);

        verify(retValue).forward(argThat(r -> r.getAttribute("test").equals("value")), any());
    }

    @Test
    void getNamedDispatcher() throws Exception {
        var retValue = mock(RequestDispatcher.class);
        when(originalContext.getNamedDispatcher("name")).thenReturn(retValue);

        var mockRequest = mock(ServletRequest.class);
        when(mockRequest.getAttribute("test")).thenReturn("value");
        var mockResponse = mock(ServletResponse.class);
        biAdaptedContext.getNamedDispatcher("name").forward(mockRequest, mockResponse);

        verify(retValue).forward(argThat(r -> r.getAttribute("test").equals("value")), any());
    }

    @Test
    void getServlet() throws Exception {
        var retValue = mock(Servlet.class);
        when(retValue.getServletInfo()).thenReturn("info");
        when(originalContext.getServlet("name")).thenReturn(retValue);

        assertEquals("info", biAdaptedContext.getServlet("name").getServletInfo());
    }

    @Test
    void getServlets() {
        var innerServlet = mock(Servlet.class);
        when(innerServlet.getServletInfo()).thenReturn("info");
        when(originalContext.getServlets()).thenReturn(enumeration(List.of(innerServlet)));

        assertEquals("info", biAdaptedContext.getServlets().nextElement().getServletInfo());
    }

    @Test
    void getServletNames() {
        when(originalContext.getServletNames()).thenReturn(enumeration(List.of("value")));

        assertEquals("value", biAdaptedContext.getServletNames().nextElement());
    }

    @Test
    void log() {
        biAdaptedContext.log("message");

        verify(originalContext).log("message");
    }

    @Test
    void testLog() {
        var exception = new Exception();
        biAdaptedContext.log("message", exception);

        verify(originalContext).log("message", exception);
    }

    @Test
    void testLog1() {
        var exception = new Exception();
        biAdaptedContext.log(exception, "message");

        verify(originalContext).log(exception, "message");
    }

    @Test
    void getRealPath() {
        when(originalContext.getRealPath("/path")).thenReturn("/real/path");

        assertEquals("/real/path", biAdaptedContext.getRealPath("/path"));
    }

    @Test
    void getServerInfo() {
        when(originalContext.getServerInfo()).thenReturn("info");

        assertEquals("info", biAdaptedContext.getServerInfo());
    }

    @Test
    void getInitParameter() {
        when(originalContext.getInitParameter("name")).thenReturn("value");

        assertEquals("value", biAdaptedContext.getInitParameter("name"));
    }

    @Test
    void getInitParameterNames() {
        when(originalContext.getInitParameterNames()).thenReturn(enumeration(List.of("name")));

        assertEquals("name", biAdaptedContext.getInitParameterNames().nextElement());
    }

    @Test
    void setInitParameter() {
        biAdaptedContext.setInitParameter("name", "value");

        verify(originalContext).setInitParameter("name", "value");
    }

    @Test
    void getAttribute() {
        when(originalContext.getAttribute("name")).thenReturn("value");

        assertEquals("value", biAdaptedContext.getAttribute("name"));
    }

    @Test
    void getAttributeNames() {
        when(originalContext.getAttributeNames()).thenReturn(enumeration(List.of("name")));

        assertEquals("name", biAdaptedContext.getAttributeNames().nextElement());
    }

    @Test
    void setAttribute() {
        biAdaptedContext.setAttribute("name", "value");

        verify(originalContext).setAttribute("name", "value");
    }

    @Test
    void removeAttribute() {
        biAdaptedContext.removeAttribute("name");

        verify(originalContext).removeAttribute("name");
    }

    @Test
    void getServletContextName() {
        when(originalContext.getServletContextName()).thenReturn("name");

        assertEquals("name", biAdaptedContext.getServletContextName());
    }

    @Test
    void addServlet() {
        var retValue = mock(ServletRegistration.Dynamic.class);
        when(retValue.getName()).thenReturn("name");
        when(originalContext.addServlet("name", "class")).thenReturn(retValue);

        assertEquals("name", biAdaptedContext.addServlet("name", "class").getName());
    }

    @Test
    void testAddServlet() {
        var retValue = mock(ServletRegistration.Dynamic.class);
        when(retValue.getName()).thenReturn("name");
        when(originalContext.addServlet(eq("name"), argThat((Servlet s) -> s.getServletInfo().equals("mine")))).thenReturn(retValue);

        var servlet = mock(Servlet.class);
        when(servlet.getServletInfo()).thenReturn("mine");
        assertEquals("name", biAdaptedContext.addServlet("name", servlet).getName());
    }

    @Test
    void testAddServlet1() {
        var retValue = mock(ServletRegistration.Dynamic.class);
        when(retValue.getName()).thenReturn("name");
        when(originalContext.addServlet("name", Servlet.class)).thenReturn(retValue);

        assertEquals("name", biAdaptedContext.addServlet("name", Servlet.class).getName());
    }

    @Test
    void addJspFile() {
        var retValue = mock(ServletRegistration.Dynamic.class);
        when(retValue.getName()).thenReturn("name");
        when(originalContext.addJspFile("name", "file")).thenReturn(retValue);

        assertEquals("name", biAdaptedContext.addJspFile("name", "file").getName());
    }

    @Test
    void createServlet() throws Exception {
        var retValue = mock(Servlet.class);
        when(retValue.getServletInfo()).thenReturn("info");
        when(originalContext.createServlet(Servlet.class)).thenReturn(retValue);

        assertEquals("info", biAdaptedContext.createServlet(Servlet.class).getServletInfo());
    }

    @Test
    void getServletRegistration() {
        var retValue = mock(ServletRegistration.class);
        when(retValue.getName()).thenReturn("value");
        when(originalContext.getServletRegistration("name")).thenReturn(retValue);

        assertEquals("value", biAdaptedContext.getServletRegistration("name").getName());
    }

    @Test
    void getServletRegistrations() {
        var retValue = mock(ServletRegistration.class);
        when(retValue.getName()).thenReturn("value");
        doReturn(Map.of("key", retValue)).when(originalContext).getServletRegistrations();

        assertEquals("value", biAdaptedContext.getServletRegistrations().get("key").getName());
    }

    @Test
    void getServletRegistrations_null() {
        doReturn(null).when(originalContext).getServletRegistrations();

        assertNull(biAdaptedContext.getServletRegistrations());
    }

    @Test
    void addFilter() {
        var retValue = mock(FilterRegistration.Dynamic.class);
        when(retValue.getName()).thenReturn("value");
        when(originalContext.addFilter("name", "class")).thenReturn(retValue);

        assertEquals("value", biAdaptedContext.addFilter("name", "class").getName());
    }

    @Test
    void testAddFilter() {
        var retValue = mock(FilterRegistration.Dynamic.class);
        when(retValue.getName()).thenReturn("value");
        when(originalContext.addFilter(eq("name"), any(Filter.class))).thenReturn(retValue);

        assertEquals("value", biAdaptedContext.addFilter("name", mock(Filter.class)).getName());
    }

    @Test
    void testAddFilter1() {
        var retValue = mock(FilterRegistration.Dynamic.class);
        when(retValue.getName()).thenReturn("value");
        when(originalContext.addFilter("name", Filter.class)).thenReturn(retValue);

        assertEquals("value", biAdaptedContext.addFilter("name", Filter.class).getName());
    }

    @Test
    void createFilter() throws Exception {
        var retValue = mock(Filter.class);
        when(originalContext.createFilter(Filter.class)).thenReturn(retValue);

        biAdaptedContext.createFilter(Filter.class).destroy();

        verify(retValue).destroy();
    }

    @Test
    void getFilterRegistration() {
        var retValue = mock(FilterRegistration.class);
        when(retValue.getName()).thenReturn("value");
        when(originalContext.getFilterRegistration("name")).thenReturn(retValue);

        assertEquals("value", biAdaptedContext.getFilterRegistration("name").getName());
    }

    @Test
    void getFilterRegistrations() {
        var retValue = mock(FilterRegistration.class);
        when(retValue.getName()).thenReturn("value");
        doReturn(Map.of("key", retValue)).when(originalContext).getFilterRegistrations();

        assertEquals("value", biAdaptedContext.getFilterRegistrations().get("key").getName());
    }

    @Test
    void getFilterRegistrations_null() {
        doReturn(null).when(originalContext).getFilterRegistrations();

        assertNull(biAdaptedContext.getFilterRegistrations());
    }

    @Test
    void getSessionCookieConfig() {
        var retValue = mock(SessionCookieConfig.class);
        when(retValue.getName()).thenReturn("value");
        when(originalContext.getSessionCookieConfig()).thenReturn(retValue);

        assertEquals("value", biAdaptedContext.getSessionCookieConfig().getName());
    }

    @Test
    void setSessionTrackingModes() {
        biAdaptedContext.setSessionTrackingModes(Set.of(SessionTrackingMode.SSL));

        verify(originalContext).setSessionTrackingModes(Set.of(SessionTrackingMode.SSL));
    }

    @Test
    void setSessionTrackingModes_null() {
        biAdaptedContext.setSessionTrackingModes(null);

        verify(originalContext).setSessionTrackingModes(null);
    }

    @Test
    void getDefaultSessionTrackingModes() {
        when(originalContext.getDefaultSessionTrackingModes()).thenReturn(Set.of(SessionTrackingMode.SSL));

        assertEquals(Set.of(SessionTrackingMode.SSL), biAdaptedContext.getDefaultSessionTrackingModes());
    }

    @Test
    void getDefaultSessionTrackingModes_null() {
        when(originalContext.getDefaultSessionTrackingModes()).thenReturn(null);

        assertNull(biAdaptedContext.getDefaultSessionTrackingModes());
    }

    @Test
    void getEffectiveSessionTrackingModes() {
        when(originalContext.getEffectiveSessionTrackingModes()).thenReturn(Set.of(SessionTrackingMode.SSL));

        assertEquals(Set.of(SessionTrackingMode.SSL), biAdaptedContext.getEffectiveSessionTrackingModes());
    }

    @Test
    void getEffectiveSessionTrackingModes_null() {
        when(originalContext.getEffectiveSessionTrackingModes()).thenReturn(null);

        assertNull(biAdaptedContext.getEffectiveSessionTrackingModes());
    }

    @Test
    void addListener() {
        biAdaptedContext.addListener("listener");

        verify(originalContext).addListener("listener");
    }

    @Test
    void testAddListener() {
        biAdaptedContext.addListener(EventListener.class);

        verify(originalContext).addListener(EventListener.class);
    }

    @Test
    void testAddListener1() {
        var retValue = mock(EventListener.class);
        biAdaptedContext.addListener(retValue);

        verify(originalContext).addListener(retValue);
    }

    @Test
    void createListener() throws Exception {
        var retValue = mock(EventListener.class);
        when(originalContext.createListener(EventListener.class)).thenReturn(retValue);

        assertEquals(retValue, biAdaptedContext.createListener(EventListener.class));
    }

    @Test
    void getJspConfigDescriptor() {
        var retValue = mock(JspConfigDescriptor.class);
        var nestedRetValue = mock(TaglibDescriptor.class);
        when(nestedRetValue.getTaglibLocation()).thenReturn("location");
        when(retValue.getTaglibs()).thenReturn(List.of(nestedRetValue));
        when(originalContext.getJspConfigDescriptor()).thenReturn(retValue);

        assertEquals("location", biAdaptedContext.getJspConfigDescriptor().getTaglibs().iterator().next().getTaglibLocation());
    }

    @Test
    void getClassLoader() {
        var retValue = mock(ClassLoader.class);
        when(originalContext.getClassLoader()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedContext.getClassLoader());
    }

    @Test
    void declareRoles() {
        biAdaptedContext.declareRoles("role1", "role2");

        verify(originalContext).declareRoles("role1", "role2");
    }

    @Test
    void getVirtualServerName() {
        when(originalContext.getVirtualServerName()).thenReturn("name");

        assertEquals("name", biAdaptedContext.getVirtualServerName());
    }

    @Test
    void getSessionTimeout() {
        when(originalContext.getSessionTimeout()).thenReturn(100);

        assertEquals(100, biAdaptedContext.getSessionTimeout());
    }

    @Test
    void setSessionTimeout() {
        biAdaptedContext.setSessionTimeout(100);

        verify(originalContext).setSessionTimeout(100);
    }

    @Test
    void getRequestCharacterEncoding() {
        when(originalContext.getRequestCharacterEncoding()).thenReturn("UTF-8");

        assertEquals("UTF-8", biAdaptedContext.getRequestCharacterEncoding());
    }

    @Test
    void setRequestCharacterEncoding() {
        biAdaptedContext.setRequestCharacterEncoding("UTF-8");

        verify(originalContext).setRequestCharacterEncoding("UTF-8");
    }

    @Test
    void getResponseCharacterEncoding() {
        when(originalContext.getResponseCharacterEncoding()).thenReturn("UTF-8");

        assertEquals("UTF-8", biAdaptedContext.getResponseCharacterEncoding());
    }

    @Test
    void setResponseCharacterEncoding() {
        biAdaptedContext.setResponseCharacterEncoding("UTF-8");

        verify(originalContext).setResponseCharacterEncoding("UTF-8");
    }
}
