AC_PREREQ(2.58)
AC_INIT([org.spoofax.xlet],[0.4],[karltk@stratego.org])
AM_INIT_AUTOMAKE([1.7.2 -Wall -Wno-portability])

# set the prefix immediately to the default prefix
test "x$prefix" = xNONE && prefix=$ac_default_prefix

AC_PROG_CC
AC_PROG_LIBTOOL
AC_PROG_INSTALL
AC_PROG_LN_S

XT_SETUP
XT_PRE_RELEASE

XT_USE_XT_PACKAGES
XT_PKG_ATERM
XT_PKG_SDF
XT_PKG_STRATEGOXT
XT_TERM_DEFINE

AC_CONFIG_FILES([
  Makefile
  src/Makefile
  src/xlets/Makefile
  src/tools/Makefile
  src/implode/Makefile
])
AC_OUTPUT
