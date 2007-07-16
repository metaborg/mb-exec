STRATEGOXT_BASELINE=

SVN=svn
REPOSHOST=svn.cs.uu.nl
REPOS=https://$(REPOSHOST):12443/repos/StrategoXT/strategoxt/trunk

all: libstratego-lib.ctree libstratego-sglr.ctree libstrc.ctree Stratego.tbl jstrc.ctree

jstrc.ctree: jstrc.str
	strc -F -i $< -o $@

trunk/configure.ac:
	svn co $(REPOS)
	cd trunk && patch -p0 <../makefiles.patch

trunk/configure: trunk/configure.ac
	cd trunk && STRATEGOXT_BASELINE=$(STRATEGOXT_BASELINE) ./bootstrap

trunk/Makefile: trunk/configure
	cd trunk && ./configure --with-strategoxt=$(STRATEGOXT_BASELINE)


TARGETS=trunk/stratego-libraries/lib/spec/libstratego-lib.ctree trunk/stratego-libraries/sglr/lib/libstratego-sglr.ctree trunk/strc-core/lib/libstrc.ctree trunk/stratego-front/syn/Stratego.tbl

$(TARGETS): trunk/Makefile
	cd trunk && $(MAKE)
	for i in $(TARGETS); do						\
	  (cd `dirname $$i` && make `basename $$i`) || exit 1;		\
	done

libstratego-lib.ctree: trunk/stratego-libraries/lib/spec/libstratego-lib.ctree
	ln -sf $< $@

libstratego-sglr.ctree: trunk/stratego-libraries/sglr/lib/libstratego-sglr.ctree
	ln -sf $< $@

libstrc.ctree: trunk/strc-core/lib/libstrc.ctree
	ln -sf $< $@

Stratego.tbl: trunk/stratego-front/syn/Stratego.tbl
	ln -sf $< $@
