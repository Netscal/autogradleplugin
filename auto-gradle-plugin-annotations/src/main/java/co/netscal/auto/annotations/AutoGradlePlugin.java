package co.netscal.auto.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * An annotation for standalone Gradle plugins.
 *
 * <p>The annotated class must conform to the Project provider specification. Specifically, it must:
 *
 * <ul>
 *   <li>be a non-inner, non-anonymous, concrete class
 *   <li>have a publicly accessible no-arg constructor
 * </ul>
 */
@Documented
@Retention(CLASS)
@Target(TYPE)
public @interface AutoGradlePlugin {
    /**
     * Returns the plugin id implemented by this plugin.
     */
    String value();
}
