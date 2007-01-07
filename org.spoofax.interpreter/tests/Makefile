
LANG_TEST_SRC=$(wildcard src/lang/*.str)
LIB_TEST_SRC=$(wildcard src/library/*.str)

LANG_TEST_CTREE=$(patsubst src/lang/%.str,data/lang/%.ctree,$(LANG_TEST_SRC))

#$(LANG_TEST_SRC:.str=.ctree) 
#$(LIB_TEST_SRC:.str=.ctree)

all: $(LANG_TEST_CTREE)

data/lang/%.ctree : src/lang/%.str
	strc -i $< -F -o $@
