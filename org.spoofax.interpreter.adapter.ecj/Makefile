wanted := \
	bin/topdown-id.ctree \
    bin/topdown-deconstruct.ctree \
	bin/collect-all-ctors.ctree \
    bin/collect-all-typedeclarations.ctree \

all : $(wanted)

bin/%.ctree : src/test/stratego/%.str
	strc -F -I src/main/stratego -i $< -o $@
