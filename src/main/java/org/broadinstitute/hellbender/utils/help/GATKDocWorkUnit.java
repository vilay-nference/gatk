package org.broadinstitute.hellbender.utils.help;

import org.broadinstitute.barclay.help.DocWorkUnit;
import org.broadinstitute.barclay.help.DocWorkUnitHandler;
import org.broadinstitute.barclay.help.DocumentedFeature;
import org.broadinstitute.hellbender.utils.runtime.RuntimeUtils;
import javax.lang.model.element.Element;

/**
 * Custom DocWorkUnit used for generating GATK help/documentation. Overrides the defaults to provide tool
 * names that are annotated with a " (Picard)" suffix for Picard tools.
 *
 * NOTE: Methods in this class are intended to be called by Gradle/Javadoc only, and should not be called
 * by methods that are used by the GATK runtime. This class has a dependency on com.sun.javadoc classes,
 * which may not be present since they're not provided as part of the normal GATK runtime classpath.
 */
@SuppressWarnings("removal")
public class GATKDocWorkUnit extends DocWorkUnit {

    public GATKDocWorkUnit(
            final DocWorkUnitHandler workUnitHandler,
            final Element classElement,
            final Class<?> clazz,
            final DocumentedFeature documentedFeatureAnnotation) {
        super(workUnitHandler, classElement, clazz, documentedFeatureAnnotation);
    }

    @Override
    public String getName() {
        // Override getName to return a display name that annotates Picard tool names with " (Picard)"
        return RuntimeUtils.toolDisplayName(getClazz());
    }

    /**
     * Sort in order of the name of this WorkUnit
     */
    @Override
    public int compareTo(DocWorkUnit other) {
        return this.getName().compareTo(other.getName());
    }
}
