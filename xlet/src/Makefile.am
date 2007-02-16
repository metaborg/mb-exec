include $(top_srcdir)/Makefile.xt

pkgdata_DATA      = spoofax.rtree-core

.str.rtree-core:
	$(SCOMPILE) $(STRINCLUDES) $(STRCFLAGS) $(SCFLAGS) -i $< -c --library -F | pp-aterm -o $@

SCFLAGS           = -F --library

SUBDIRS           = xlets implode
BOOTCLEAN_SUBDIRS = $(SUBDIRS)
DIST_SUBDIRS      = $(SUBDIRS)
EXTRA_DIST        =
CLEANFILES        = $(pkgdata_DATA) $(wildcard *.dep)
