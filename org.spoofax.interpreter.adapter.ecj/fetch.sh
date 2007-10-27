#!/bin/bash

ECLIPSE_HOME=${ECLIPSE_HOME:-${HOME}/apps/eclipse-3.3}

jars="org.eclipse.core.contenttype_3.2.0.v20060603.jar \
	org.eclipse.equinox.preferences_3.2.1.R32x_v20060717.jar \
	org.eclipse.core.jobs_3.2.0.v20060603.jar \
	org.eclipse.equinox.registry_3.2.1.R32x_v20060814.jar \
	org.eclipse.core.resources_3.2.2.R32x_v20061218.jar \
	org.eclipse.jdt.core_3.2.3.v_686_R32x.jar \
	org.eclipse.core.runtime_3.2.0.v20060603.jar \
	org.eclipse.osgi_3.2.2.R32x_v20070118.jar \
	org.eclipse.equinox.common_3.2.0.v20060603.jar"

for x in ${jars} ; do 
	cp ${ECLIPSE_HOME}/plugins/${x} lib/
done
