# -------------------------------------------------------------------------
#  File Name  : Makefile
# -------------------------------------------------------------------------

# -------------------------------------------------------------------------
#  Including standard rules
# -------------------------------------------------------------------------
PROROOT	= ..
include	../Rules.make

PRODEFS = -Wall -O2 -D_REENTRANT -m64 -pedantic -Wpointer-arith\
	  -Wwrite-strings -Wbad-function-cast -Wmissing-prototypes\
	  -Wmissing-declarations -Wcast-qual -Wstrict-prototypes

#PRODEFS = -Wall -g -O2 -D_REENTRANT -pedantic -Wpointer-arith\
	  -Wwrite-strings -Wbad-function-cast -Wmissing-prototypes\
	  -Wmissing-declarations -Wcast-qual -Wstrict-prototypes

PROCOPT = -U__WIN32__

COMFLAG	= -g -o $@

# -------------------------------------------------------------------------
#  File specifications
# -------------------------------------------------------------------------
OBJECTS = $(OBJ_DIR)/kutil.o\
	  $(OBJ_DIR)/logdir.o\
	  $(OBJ_DIR)/xprint.o\
	  $(OBJ_DIR)/mqipc.o

SOURCES = $(OBJECTS:.o = .c)

# -------------------------------------------------------------------------
#  Target
# -------------------------------------------------------------------------
TARGET	= libcmlb.a

all: $(LIB_DIR)/$(TARGET)
$(LIB_DIR)/$(TARGET): $(OBJECTS)
	$(AR) $(ARFLAGS) $(LIB_DIR)/$(TARGET) $(OBJECTS)
	$(RANLIB) $(LIB_DIR)/$(TARGET)
	$(CP) $(LIB_DIR)/$(TARGET) $(COM_LIB)

# -------------------------------------------------------------------------
#  Compile source file
# -------------------------------------------------------------------------
$(OBJ_DIR)/%.o: %.c
	$(CC) -c $(PRODEFS) $(COMFLAG) $(INC) $(PROCOPT) $<

#-------------------------------------------------------------------------
# Delete object, target and library file
#-------------------------------------------------------------------------
clean:
	$(RM) -f *.o $(OBJ_DIR)/*.o $(LIB_DIR)/$(TARGET)

# -------------------------------------------------------------------------
#  End of Makefile
# -------------------------------------------------------------------------
