# 
# Common scripting functions for the Spoofax shell scripts
#
# Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
#
# Licensed under the IBM Common Public License, v1.0

SPOOFAX_BIN_DIR=${SPOOFAX_BASE_DIR}/bin
SPOOFAX_LIB_DIR=${SPOOFAX_BASE_DIR}/lib/spoofax
SPOOFAX_SHARE_DIR=${SPOOFAX_BASE_DIR}/share/spoofax
SPOOFAX_TRAFO_DIR=${SPOOFAX_LIB_DIR}/trafos
SPOOFAX_SCRIPTS_DIR=${SPOOFAX_SHARE_DIR}/scripts

function find_file() {
	file=$1
	
	if [ -x "/usr/bin/${file}" ] ; then
		echo "/usr/bin/${file}"
	elif [ -x "/usr/local/bin/${file}" ] ; then
		echo "/usr/local/bin/${file}"
	elif [ -x "${SPOOFAX_BIN_DIR}/${file}" ] ; then
		echo "${SPOOFAX_BIN_DIR}/${file}"
	fi
}

function set_bin_paths() {

	if [ -z "${PARSE_CPP_BIN}" ] ; then
		PARSE_CPP_BIN=$(find_file "codeboost")
	fi

	if [ -z "${UNPARSE_CPP_BIN}" ] ; then
		UNPARSE_CPP_BIN=$(find_file "codeboost")
	fi
	
	if [ -z "${PARSE_JAVA_BIN}" ] ; then
		PARSE_JAVA_BIN=$(find_file "parse-java")
	fi

	if [ -z "${UNPARSE_JAVA_BIN}" ] ; then
		UNPARSE_JAVA_BIN=$(find_file "pp-java")
	fi

	if [ -z "${PARSE_C_BIN}" ] ; then
		PARSE_C_BIN=$(find_file "parse-c")
	fi

	if [ -z "${UNPARSE_C_BIN}" ] ; then
		UNPARSE_C_BIN=$(find_file "pp-c")
	fi
	
	if [ -z "${PARSE_STRATEGO_BIN}" ] ; then
		PARSE_STRATEGO_BIN=$(find_file "parse-stratego")
	fi

	if [ -z "${UNPARSE_STRATEGO_BIN}" ] ; then
		UNPARSE_STRATEGO_BIN=$(find_file "pp-stratego")
	fi

}

function sdebug() {
	echo $* > /dev/stderr
}

function einfo() {
	sdebug $*
}
	
function die() {
	echo $* > /dev/stderr
	exit 1
}