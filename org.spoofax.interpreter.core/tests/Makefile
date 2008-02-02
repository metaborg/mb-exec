
LANG_TEST_SRC=$(wildcard src/lang/*.str)
LIB_TEST_SRC=$(wildcard src/library/*.str)

LANG_TEST_CTREE=$(patsubst src/lang/%.str,data/lang/%.ctree,$(LANG_TEST_SRC))
LIB_TEST_CTREE=$(patsubst src/library/%.str,data/library/%.ctree,$(LIB_TEST_SRC))

#$(LANG_TEST_SRC:.str=.ctree) 
#$(LIB_TEST_SRC:.str=.ctree)

all: $(LANG_TEST_CTREE) $(LIB_TEST_CTREE)

data/lang/%.ctree : src/lang/%.str
	strc -i $< -F | baffle -wt -o $@

data/library/%.ctree : src/library/%.str
	strc -i $< -F | baffle -wt -o $@

data/java-library/%.ctree : src/java-library/%.str
	strc -i $< -F | baffle -wt -o $@

data/library/system-io-file-test.str:
	touch data/library/system-io-file-test.str
