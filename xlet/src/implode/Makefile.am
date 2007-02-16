include $(top_srcdir)/Makefile.xt
-include imploder.c.dep
-include pretty-print.c.dep

noinst_PROGRAMS = imploder pretty-print

nodist_imploder_SOURCE		= imploder.c
nodist_pretty_print_SOURCE	= pretty-print.c

SCFLAGS		= -m $*

EXTRA_DIST	= imploder.str	\
		  pretty-print.str

LDADD	+= $(STRATEGO_GPP_LIBS) $(STRATEGO_SGLR_LIBS)

CLEANFILES	= imploder.{c,c.dep} pretty-print.{c,c.dep}
