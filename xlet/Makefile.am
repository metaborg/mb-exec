include $(top_srcdir)/Makefile.xt

ACLOCAL_AMGFLAGS=-I .

SUBDIRS      = src
pkgdata_DATA = 

DISTCHECK_CONFIGURE_FLAGS = \
  --with-strategoxt=$(STRATEGOXT) \
  --with-sglr=$(SGLR) \
  --with-pgen=$(PGEN) \
  --with-pt-support=$(PT_SUPPORT) \
  --with-asf-library=$(ASF_LIBRARY) \
  --with-aterm=$(ATERM)

ignore :
	svn propset -R svn:ignore -F $(srcdir)/svn-ignore $(srcdir)

