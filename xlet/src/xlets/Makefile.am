include $(top_srcdir)/Makefile.xt

pkgdata_DATA      = analyzer.rtree-core outliner.rtree-core

.str.rtree-core:
	$(SCOMPILE) $(STRINCLUDES) $(STRCFLAGS) $(SCFLAGS) -i $< -c --library -F | pp-aterm -o $@

STRINCLUDES       = -I ../
SCFLAGS           = -F --library

SUBDIRS           =
BOOTCLEAN_SUBDIRS = $(SUBDIRS)
DIST_SUBDIRS      = $(SUBDIRS)
EXTRA_DIST        =
CLEANFILES        = $(pkgdata_DATA) $(wildcard *.dep)
