#!/bin/bash

ECLIPSE_HOME=${ECLIPSE_HOME:-${HOME}/apps/eclipse-3.3}

if [ ! -f ${ECLIPSE_HOME}/eclipse ] ; then
	echo "The env var ECLIPSE_HOME must point to the directory of an Eclipse 3.3 installation " 2>&1
	exit 1
fi

jars="org.eclipse.core.contenttype
	org.eclipse.equinox.preferences
	org.eclipse.core.jobs
	org.eclipse.equinox.registry
	org.eclipse.core.resources
	org.eclipse.jdt.core
	org.eclipse.core.runtime
	org.eclipse.osgi
	org.eclipse.equinox.common"

function latest() {
	ls ${ECLIPSE_HOME}/plugins/$1_*.jar | sort | tail -n 1
}

for x in ${jars} ; do 
	cp $(latest $x) lib/
done
