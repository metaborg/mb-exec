wanted := \
	bin/allid.ctree \
	bin/all-ctor-debug.ctree

all : $(wanted)

bin/%.ctree : src/test/stratego/%.str
	strc -F -I src/main/stratego -i $< -o $@
