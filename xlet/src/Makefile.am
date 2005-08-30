include $(top_srcdir)/Makefile.xt

pkgdata_DATA      = spoofax.rtree-core

.str.rtree-core:
	$(SCOMPILE) $(STRINCLUDES) $(STRCFLAGS) $(SCFLAGS) -i $< -o $@ -c --library -F

SCFLAGS           = -F --library

SUBDIRS           = 
BOOTCLEAN_SUBDIRS = $(SUBDIRS)
DIST_SUBDIRS      = $(SUBDIRS)
EXTRA_DIST        =
CLEANFILES        = $(pkgdata_DATA) $(wildcard *.dep)
