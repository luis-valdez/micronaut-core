/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.micronaut.core.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * An annotation that indicates a type should produce a {@link io.micronaut.core.beans.BeanIntrospection} at compilation time.
 *
 * <p>Typically to produce a {@link io.micronaut.core.beans.BeanIntrospection} one simply annotates a class with this annotation.</p>
 *
 * <p>An alternative approach is to use a {@code AnnotationMapper} to enable introspection for existing annotations such as {@code javax.persistence.Entity}.</p>
 *
 * <p>If the classes you wish to introspect are already compiled then this annotation can be used on another class (doesn't matter which, but typically on a configuration class) to specify which existing compiled classes to produce {@link io.micronaut.core.beans.BeanIntrospection} instances for either through the {@link #classes()} method or the {@link #packages()} method. The latter uses compile time package scanning and for the moment is regarded as {@link Experimental}.</p>
 *
 * @author graemerocher
 * @since 1.1
 * @see io.micronaut.core.beans.BeanIntrospection
 * @see io.micronaut.core.beans.BeanIntrospector
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Introspected {

    /**
     * By default {@link Introspected} applies to the class it is applied on. However if classes are specified
     * introspections will instead be generated for each class specified. This is useful in cases where you cannot
     * alter the source code and wish to generate introspections for already compiled classes.
     *
     * @return The classes to generate introspections for
     */
    Class<?>[] classes() default {};


    /**
     * <p>By default {@link Introspected} applies to the class it is applied on. However if packages are specified
     * introspections will instead be generated for each classes in the given package. Note this only applies to already compiled
     * classes, and classpath scanning will be used to find them. If the class is not compiled then apply the annotation directly
     * to the classs instead.</p>
     *
     * <p>Must be specified in combination with {@link #includedAnnotations()}</p>
     *
     * @return The packages to generate introspections for
     */
    @Experimental
    String[] packages() default {};

    /**
     * The property names to include. Defaults to all properties.
     *
     * @return The names of the properties
     */
    String[] includes() default {};

    /**
     * The property names to excludes. Defaults to excluding none.
     *
     * @return The names of the properties
     */
    String[] excludes() default {};

    /**
     * The annotation types that if present on the property cause the property to be excluded from results.
     *
     * @return The annotation types
     */
    Class<? extends Annotation>[] excludedAnnotations() default {};

    /**
     * The annotation types that if present on the property cause only the properties with the specified annotation to be included in the result.
     *
     * @return The annotation types
     */
    Class<? extends Annotation>[] includedAnnotations() default {};

    /**
     * The annotation types that should be indexed for lookup via {@link io.micronaut.core.beans.BeanIntrospection#getBeanProperties(Class)}.
     *
     * @return The indexed annotation types
     */
    Class<? extends Annotation>[] indexed() default {};

    /**
     * Whether annotation metadata should be included in the inspection results.
     *
     * @return True if annotation metadata should be included.
     */
    boolean annotationMetadata() default true;
}
