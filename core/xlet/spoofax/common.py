#! /usr/bin/python2
#
# Copyright(c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
# 
# Part of Spoofax
#
# Licensed under the IBM Common Public License, v1.0

import sys

class FatalError:
    def __init__(self, string):
        self._string = string
    def __str__(self):
        return "FatalError: " + self._string
        
verbosity = 1

def set_verbosity(verb):
    global verbosity
    verbosity = verb

def debug(n, s):
    if verbosity > n :
        sys.stderr.write(s + "\n")

def error(s):
    sys.stderr.write("!!! " + s + "\n")

def warning(s):
    sys.stderr.write("!!! " + s + "\n")
        
if __name__ == "__main__":
    print "Module not intended to be run"                