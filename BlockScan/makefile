TARGET = BlockScan.exe

INC = 
VPATH = src:src/pqcrypto
SRC_PATH = $(foreach dir,$(subst :, ,$(VPATH)),$(wildcard $(dir)/*.cpp))
OBJ_DIR = obj
OBJ_PATH = $(addprefix  $(OBJ_DIR)/,   $(subst :, ,$(VPATH) ))
OBJS = $(addprefix $(OBJ_DIR)/, $(subst .cpp,.o,$(SRC_PATH)))
MAKE_OBJECT_DIR := $(shell mkdir -p $(OBJ_DIR) $(OBJ_PATH))

CXX = g++
CXXFLAGS = -g

all : $(TARGET)
	
$(TARGET) : $(OBJS)
	$(CXX) -o $@ $(OBJS) $(INC) $(CXXFLAGS)

$(OBJ_DIR)/%.o : %.cpp
	$(CXX) -o $@ -c $< $(INC) $(CXXFLAGS)

clean:
	@rm -rf ./obj
	@rm -rf $(TARGET)