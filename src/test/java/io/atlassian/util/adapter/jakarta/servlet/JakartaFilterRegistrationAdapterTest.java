package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXFilterRegistrationAdapter;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaFilterRegistrationAdapterTest {

    @Mock
    private FilterRegistration original;

    private FilterRegistration biAdapted;

    @BeforeEach
    void setUp() throws Exception {
        biAdapted = new JakartaFilterRegistrationAdapter(new JavaXFilterRegistrationAdapter(original));
    }

    @Test
    void addMappingForServletNames() {
        biAdapted.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "servletName");

        verify(original).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "servletName");
    }

    @Test
    void addMappingForServletNames_null() {
        biAdapted.addMappingForServletNames(null, false, (String) null);

        verify(original).addMappingForServletNames(null, false, (String) null);
    }

    @Test
    void getServletNameMappings() {
        when(original.getServletNameMappings()).thenReturn(List.of("servletName"));

        assertEquals(List.of("servletName"), biAdapted.getServletNameMappings());
    }

    @Test
    void addMappingForUrlPatterns() {
        biAdapted.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "urlPattern");

        verify(original).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "urlPattern");
    }

    @Test
    void addMappingForUrlPatterns_null() {
        biAdapted.addMappingForUrlPatterns(null, false, (String) null);

        verify(original).addMappingForUrlPatterns(null, false, (String) null);
    }

    @Test
    void getUrlPatternMappings() {
        when(original.getUrlPatternMappings()).thenReturn(List.of("urlPattern"));

        assertEquals(List.of("urlPattern"), biAdapted.getUrlPatternMappings());
    }
}
