#! /usr/bin/python2
#
# Copyright(c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
# 
# Part of Spoofax
#
# Licensed under the IBM Common Public License, v1.0

import os

import common
import domain
import xtc
import optionfile

import generatedconfig

def find_current_domain():
    path = domain.find_domain_path(os.path.abspath("."))
    if os.path.isdir(path):
        return domain.Domain(path)
    return None
    
class Config:
    """Used as namespace for configuration scopes."""
    pass

def init_config():
    
    optreg = optionfile.OptionRegistry()
    Config.optreg = optreg
    
    globals = optionfile.OptionFile(optreg, "GLOBALS", [])
    globals.add_option("spoofax_base_dir", generatedconfig.SPOOFAX_BASE_DIR)
    globals.add_option("spoofax_trafo_dirs", generatedconfig.SPOOFAX_TRAFO_DIRS)
    Config.globals = globals
    globals.dump()
    
    conf = optionfile.OptionFile(optreg, "spoofax", 
        [ generatedconfig.SPOOFAX_BASE_DIR + "/etc/spoofax/spoofax.conf", 
          os.environ["HOME"] + "/.spoofax/spoofax.conf"])
    conf.load()
    Config.config = conf
    conf.dump()

    # FIXME: UGLY! 
    global config
    config = conf
    
init_config()
    

if __name__ == "__main__":
    print "Module not intended to be run"
    
    