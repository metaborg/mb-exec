#! /bin/bash
#
# Main Spoofax CLI driver script
#
# Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
#
# Licensed under the IBM Common Public License, v1.0

if [ -f /etc/spoofax/spoofax.conf ] ; then
	. /etc/spoofax/spoofax.conf
fi

if [ -f ${HOME}/.spoofax/spoofax.conf ] ; then
	. ${HOME}/.spoofax/spoofax.conf
fi

if [ -z ${SPOOFAX_BASE_DIR} ] ; then 
	if [ -f /usr/share/spoofax/scripts/common-functions.sh ] ; then
		SPOOFAX_BASE_DIR=/usr
	elif [ -f /usr/local/share/spoofax/scripts/common-functions.sh ] ; then
		SPOOFAX_BASE_DIR=/usr/local
	fi
fi

if [ -z ${SPOOFAX_BASE_DIR} ] ; then
	echo "Could not find Spoofax share directory!" > /dev/stderr
	exit 2
fi

. ${SPOOFAX_BASE_DIR}/share/spoofax/scripts/common-functions.sh

set_bin_paths

find_parser() {

	case $(get_extension $1) in
		cpp)
			echo ${SPOOFAX_SHARE_DIR}/scripts/cpp2ast.sh
			;;
		c)
			echo ${SPOOFAX_SHARE_DIR}/scripts/c2ast.sh
			;;
		str)
			echo ${SPOOFAX_SHARE_DIR}/scripts/str2ast.sh
			;;
		java)
			echo ${SPOOFAX_SHARE_DIR}/scripts/java2ast.sh
			;;
		*)
			echo ${SPOOFAX_SHARE_DIR}/scripts/fail.sh
	esac
}

find_unparser() {

	case $(get_extension $1) in
		cpp)
			echo ${SPOOFAX_SHARE_DIR}/scripts/ast2cpp.sh
			;;
		c)
			echo ${SPOOFAX_SHARE_DIR}/scripts/ast2c.sh
			;;
		str)
			echo ${SPOOFAX_SHARE_DIR}/scripts/ast2str.sh
			;;
		java)
			echo ${SPOOFAX_SHARE_DIR}/scripts/ast2java.sh
			;;
		*)
			echo ${SPOOFAX_SHARE_DIR}/scripts/fail.sh
	esac
}

usage() {
        cat <<EOF
Usage: spoofax [OPTIONS] file1
Options:
        [-p, --pretty-print]
EOF
        exit $1
}

get_extension() {

	local ext=$(echo $1 | sed -r "s/.*\.(.*)/\1/")
	
	echo ${ext}
	
}

pipetrafo=""
posttrafo=""
trafo=""
inputfile=""
unparse=false

if [ $# == 0 ] ; then
    echo "Usage: spoofax [OPTIONS] [LIBRARIES]"
    exit 1
fi

while test $# -gt 0; do

    case "$1" in
    -*=*) optarg=`echo "$1" | sed 's/[-_a-zA-Z0-9]*=//'` ;;
    *) optarg= ;;
    esac

    case $1 in
        -h|--help)
            usage
        ;;
        -p|--pretty-print)
        	unparse=true
        ;;
        *)
        	file=$1
    esac
    shift
done

if [ ${unparse} == true ]; then
		posttrafo="${posttrafo} | $(find_unparser ${file})"
fi

pretrafo="cat ${file}"
pretrafo="${pretrafo} | $(find_parser ${file})" 

cmd="${pretrafo} ${trafo} ${posttrafo}"
sdebug "${cmd}"
eval ${cmd}