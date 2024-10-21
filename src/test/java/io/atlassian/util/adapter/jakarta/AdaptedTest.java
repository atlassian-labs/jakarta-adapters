package io.atlassian.util.adapter.jakarta;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.JavaXAdapters;
import io.atlassian.util.adapter.javax.JavaXJspAdapters;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpSessionContextAdapter;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.assertj.core.api.Assertions.assertThat;

class AdaptedTest {

    private static final Set<String> ASSERTION_EXEMPT_CLASSES = Set.of(
            JakartaAdapters.class,
            JakartaJspAdapters.class,
            JavaXAdapters.class,
            JavaXJspAdapters.class,
            JavaXHttpSessionContextAdapter.class
    ).stream().map(Class::getName).collect(toUnmodifiableSet());

    @Test
    void allAdaptersImplementAdaptedInterface_jakarta() {
        scanAndAssertPackage("io.atlassian.util.adapter.jakarta");
    }

    @Test
    void allAdaptersImplementAdaptedInterface_javax() {
        scanAndAssertPackage("io.atlassian.util.adapter.javax");
    }

    private void scanAndAssertPackage(String pkg) {
        try (ScanResult scanResult = new ClassGraph().acceptPackages(pkg).scan()) {
            var allClasses = scanResult.getAllClasses();
            assertThat(allClasses).describedAs("Package scanning unexpectedly returned no results").isNotEmpty();
            for (ClassInfo classInfo : allClasses) {
                if (ASSERTION_EXEMPT_CLASSES.contains(classInfo.getName())) {
                    continue;
                }
                assertThat(classInfo.implementsInterface(Adapted.class.getName())).describedAs(
                        classInfo.getName() + " does not implement Adapted interface").isTrue();
            }
        }
    }
}
