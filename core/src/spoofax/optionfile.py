#
# Parser for Gentoolkit option files
#
# Copyright (c) 2004, Karl Trygve Kalleberg <karltk@gentoo.org>

import sys
import string

class Scope:
    def __init__(self, name, parent):
        self._name = name
        self._options = {}
        self._kids = {}

    def dump(self, ous, indent=0):
        baseindent = " " * indent
        optindent = " " * (indent + 4)
        ous.write(baseindent + self._name + " {\n")
        for x in self._options.keys():
            ous.write(optindent + x + " = " + self._options[x] + "\n")
        for x in self._kids.values():
            x.dump(ous, indent + 4)
        ous.write(baseindent + "}\n")
        
    def add_subscope(self, scope):
        self._kids.append(scope)
        scope._parent = self
    
    def add_option(self, option, value):
        toks = option.split(".")
        if len(toks) > 1:
            scopename = toks[0]
            subscope_var = string.join(toks[1:], ".")
            if scopename in self._kids.keys():
                self._kids[scopename].add_option(subscope_var, value)
            else:
                scope = Scope(scopename, self)
                self._kids[scopename] = scope
                scope.add_option(subscope_var, value)
        elif len(toks) == 1:
            self._options[toks[0]] = value
        else:
            raise "Invalid option name!"

    def lookup(self, var):
        #print self._name + ":Scope.lookup(" + var + ")"
        toks = var.split(".")
        
        if len(toks) > 1:
            #print "More than one..."
            if toks[0] in self._kids.keys():
                self._kids[toks[0]].lookup(string.join(toks[1:]), ".")
        elif len(toks) == 1:
            #print "Only one..."
            if toks[0] in self._options.keys():
                return self._options[toks[0]]
        else:
            print "WHAT?"
        return None

class OptionRegistry:
    def __init__(self):
        self._optionfiles = {}
        pass

    def attach_file(self, optionfile):
        self._optionfiles[optionfile._name] = optionfile

    def detach_file(self, id):
        self._optionfiles[id] = None
    

    def has_file(self, id):
        return id in self._optionfiles.keys()

    def get_file(self, id):
        if id in self._optionfiles.keys():
            return self._optionfiles[id]
        
class OptionFile:
    def __init__(self, registry, name, filenames):
        self._name = name
        self._filenames = filenames
        self._scope = Scope(name, self) 
        self._registry = registry
        registry.attach_file(self)

    def _internal_lookup(self, varname):
        #print self._name + ":internal_lookup(" + varname + ")"
        toks = varname.split(".")
        return self._scope.lookup(varname)

    def _lookup(self, varname):
        """Look up a varname in this optionfile. Try the GLOBALS optionfile if no hits found."""
        #print self._name + ":lookup(" + varname + ")"
        r = self._internal_lookup(varname)
        toks = varname.split(".")
        if r: 
            return r

        #print "Not internal..."
        f = self._registry.get_file(toks[0])
        if f: 
            return f._internal_lookup(string.join(toks[1:]))

        #print "Not in registry..."
        f = self._registry.get_file("GLOBALS")
        if f: 
            return f._internal_lookup(varname)

        #print "Not in GLOBALS..."
        return None

    def _eval(self, expr):
        in_var = 0
        res = ""
        for i in range(len(expr)):
            x = expr[i]
            if x == '$' and prev != '\\':
                in_var = i
            elif in_var:
                if x == '{':
                    start_var = i
                if x == '}':
                    var = expr[start_var+1:i]
                    in_var = 0
                    res += self._lookup(var)
            else:
                res += x
            prev = x
        return res.strip()
        
    def _parse_file(self, lines):
        scopes = []
        for x in lines:
            s = x.strip()
            if len(s) and s[0] == "#":
                continue
            if len(s) == 0:
                continue
            parts = s.split("=")
            if len(parts) == 1:
                if s[-1] == '{':
                    scopes.append(s[:-1].strip())
                elif s[-1] == '}':
                    scopes = scopes[:-1]
                else:
                    raise "Unknown token '" + s + "'"
            else:
                scope_prefix = string.join(scopes, ".")
                if len(scope_prefix): 
                    scope_prefix += "."
                option =  scope_prefix + parts[0].strip().lower()
                value = string.join(parts[1:])
                #print "] " + option
                self.add_option(option, self._eval(value))

    def add_filename(self, filename):
        self._filenames.append(filename)
        
    def load(self):
        #print "Loader?", self._filenames
        for x in self._filenames:
            #print "Loading from " + x + "..."
            try:
                ins = open(x)
                self._parse_file(ins.readlines())
                #print "Parsed."
                ins.close()
            except IOError:
                pass
        
    def has_option(self, option):
        return self._scope.has_option(option)

    def add_option(self, option, value):
        return self._scope.add_option(option, value)
        
    def get_option(self, option):
        return self._scope.lookup(option)

    def __getitem__(self, option):
        return self._lookup(option)
        
    def dump(self, ous=sys.stdout):
        self._scope.dump(ous)            

if __name__ == "__main__":
    print "Module not intended to be run"        