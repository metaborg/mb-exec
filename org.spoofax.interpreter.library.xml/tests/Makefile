
TEST_SRC=$(wildcard src/*.str)

TEST_CTREE=$(patsubst src/%.str,data/%.ctree,$(TEST_SRC))

all: $(TEST_CTREE)

data/%.ctree : src/%.str
	strc -i $< -F | baffle -wt -o $@
