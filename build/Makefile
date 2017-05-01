DIR_INC = ${GCS_HOME}/include
DIR_SRC = ${GCS_HOME}/src
DIR_OBJ = ${GCS_HOME}/build/obj
DIR_BIN = ${GCS_HOME}/bin

SRCFILES = $(wildcard ${DIR_SRC}/*.cc)  
OBJECTS = $(patsubst %.cc, ${DIR_OBJ}/%.o, $(notdir ${SRCFILES})) 

TARGET = $(basename $(notdir ${OBJECTS}))

all:$(TARGET)

$(OBJECTS): $(DIR_OBJ)/%.o : $(DIR_SRC)/%.cc
	g++ -std=c++11 -I$(DIR_INC) -c $< -o $@

$(TARGET): % : $(DIR_OBJ)/%.o
	g++ -g $< -o $(DIR_BIN)/$@

.PHONY: clean all
clean:
	rm $(DIR_OBJ)/*.o
