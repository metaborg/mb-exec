#! /bin/sh

../../org.spoofax.interpreter.core/spoofax \
	-D share.dir=../share \
	-L ../../org.spoofax.jsglr/bin \
	-f org.spoofax.interpreter.adapter.aterm.WrappedATermFactory \
	-L ../../org.spoofax.interpreter.library.jsglr/bin \
	-L ../../org.spoofax.interpreter.adapter.aterm/bin \
	-l org.spoofax.interpreter.library.jsglr.JSGLRLibrary \
	-i fastpack.ctree,libstratego-lib.ctreet,libstratego-sglr.ctreet \
	-- \
	$1 .:/home/karltk/.nix-profile/share 0
