#! /usr/bin/python2
#
# Copyright(c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
# 
# Part of Spoofax
#
# Licensed under the IBM Common Public License, v1.0

import os

import spoofax
from spoofax.common import FatalError

class XTCTrafo:
    """A Python encapsulation of a Spoofax XTC module."""
    def __init__(self, path):
        
        if not ( os.access(path, os.X_OK) and os.path.isfile(path) ):
            raise FatalError(path + " is not an executable file!")
        
        self._path = path
 

    def __call__(self, arg0, arg1):
        stdin, stdout = os.popen2(self._path)
        return stdout.read()
    
def find_trafo(trafoid):
    """Look for a given transformation in the Spoofax XTC repository"""
    
    trafopaths = spoofax.config["spoofax_trafo_dirs"]

    if not trafopaths:
        return None
        
    for x in trafopaths.split(":"):
        p = x + "/" + trafoid
        print p
        if os.path.exists(p):
            return XTCTrafo(p)

    return None
    
if __name__ == "__main__":
    print "Module not intended to be run"    