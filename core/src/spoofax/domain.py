#! /usr/bin/python2
#
# Copyright(c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
# 
# Part of Spoofax
#
# Licensed under the IBM Common Public License, v1.0

import os
import time

from common import debug
from common import FatalError

SPOOFAX_DIR =  ".spoofax"
METADATA_DIR = SPOOFAX_DIR + "/metadata"

CACHE_SUFFIX = ".cached"

DEFAULT_FORBIDDEN_PATHS = [ "RCS", "CVS", ".svn" ]
DEFAULT_SUPPORTED_EXTENSIONS = [ ".str", ".c", ".cpp", ".java" ]

domain_registry = {}

def create_domain(basepath, traverser = None):
    
    if basepath in domain_registry.keys():
        return domain_registry[basepath]
    
    domain = Domain(basepath, traverser)
    domain_registry[basepath] = domain
    return domain
    
class Domain:
    def __init__(self, basepath, treetraverser = None):

        if not os.path.isdir(basepath + "/" + SPOOFAX_DIR):
            raise FatalError(basepath + " is not a Spoofax Domain!")
            
        self._basepath = basepath
        self._metadata = Metadata(self)
        
        self._forbidden_paths = DEFAULT_FORBIDDEN_PATHS
        
    def set_forbidden_paths(self, forbidden_paths):
        self._forbidden_paths = forbidden_paths
        
    def _load_config(self):
        if os.path.exists(self._basepath + "/" + SPOOFAX_DIR + "/config"):
            pass

    def get_documents(self):
        return self._metadata.get_documents()
        
    def clear_cache(self):
        self._metadata.clear()
        
    def rescan(self, path = ""):
        self._traverse(path)
        
    def _traverse(self, path):
        
        abspath = self._basepath + "/" + path
        
        debug(3, "Scanning '" + abspath + "'")

        if os.path.islink(abspath) and \
            os.path.isdir(abspath):
                debug(3, "Ignoring linked directory " + abspath)
            
        elif os.path.isdir(abspath) and \
            os.path.basename(abspath) not in self._forbidden_paths:

            contents = os.listdir(abspath)
            for x in contents:
                self._traverse(path + "/" + x)

        elif os.path.isfile(abspath):
            self._file_scanner(path)

    def _file_scanner(self, path):
        
        ext = os.path.splitext(path)[1]
        
        if ext in DEFAULT_SUPPORTED_EXTENSIONS:
            self._spoofax_it(path)

    def _is_cached(self, path):
        
        if self._metadata.exists(path):
            live = os.stat(self._basepath + "/" + path)
            cache = self._metadata.stat(path)
            if cache.st_ctime >= live.st_ctime:
                return True
        return False
            
    def _get_spoofax_path(self):
        # FIXME: user configuration!
        return "/usr/local/apps/spoofax/bin/spoofax"

    def _abspath(self, path):
        return self._basepath + "/" + path
        
    def _spoofax_it(self, path):

        debug(4, "Scanning file " + path)

        abspath = self._abspath(path)
        
        if self._is_cached(path):
            return 

        stdin, stdout = os.popen2(self._get_spoofax_path() + " " + abspath)
        doc = stdout.read()
        #print doc
        
        #tmpfile = "/tmp/metascan-" + str(int(time.time()))
        #ous = open(tmpfile, "w")
        #ous.write("Spoofax woz here")
        #ous.close()

        self._metadata.cache_document(path, doc)
        debug(4, "Running spoofax on " + path)        
            
def find_domain_path(dir):
    if dir == "":
        return ""
    elif os.path.exists(dir + "/" + SPOOFAX_DIR):
        return dir
    else:
        parent = os.path.dirname(dir)
        if parent != "/":
            return find_domain_path(parent)
        else: 
            return None
        
class Metadata:

    def __init__(self, domain):
        self._domain = domain
        self._basepath = domain._basepath + "/" + METADATA_DIR

    def stat(self, path):
        return os.stat(self._basepath + "/" + path + CACHE_SUFFIX)

    def exists(self, path):
        return os.path.exists(self._basepath + "/" + path + CACHE_SUFFIX)

    def _abspath(self, path):
        return self._basepath + "/" + path
        
    def _get_path(self, key):
        return self._abspath(key) + CACHE_SUFFIX
        
    def cache_file(self, key, path):
        cachefile = self._get_path(key)
        
        debug(3, "Caching " + path + " as " + cachefile)

        self._ensure_cache_dir(cachefile)
        os.rename(path, cachefile)

    def _ensure_cache_dir(self, cachefile):
        dir = os.path.dirname(cachefile)
        if not os.path.isdir(dir):
            os.makedirs(dir)

    def get_documents(self, path = ""):
        abspath = self._abspath(path)
        docs = []
        for x in os.listdir(abspath):
            absx = abspath + "/" + x
            if os.path.isdir(absx):
                docs += self.get_documents(path + "/" + x)
            elif os.path.isfile(absx):
                docs.append(absx)
            else:
                raise FatalError(abspath + " is not a file or directory")
        return docs
        
    def cache_document(self, key, doc):
        cachefile = self._get_path(key)
        
        debug(3, "Caching document as " + cachefile)
        
        self._ensure_cache_dir(cachefile)
        
        ous = open(cachefile, "w")
        ous.write(doc)
        ous.close()

    def clear(self, path = ""):
        abspath = self._abspath(path)
        print abspath
        for el in os.listdir(abspath):
            absel = abspath + "/" + el
            if os.path.isdir(absel):
                self.clear(path + "/" + el)
                os.rmdir(absel)
            elif os.path.isfile(absel):
                os.remove(absel)
            else:
                raise FatalError(abspath + " is not a file or directory")
        
if __name__ == "__main__":
    print "Module not intended to be run"        