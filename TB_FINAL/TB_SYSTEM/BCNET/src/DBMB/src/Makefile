# -------------------------------------------------------------------------
#   __  __       _         __ _ _
#  |  \/  | __ _| | _____ / _(_) | ___
#  | |\/| |/ _` | |/ / _ \ |_| | |/ _ \
#  | |  | | (_| |   <  __/  _| | |  __/
#  |_|  |_|\__,_|_|\_\___|_| |_|_|\___|
#
# -------------------------------------------------------------------------

# -------------------------------------------------------------------------
#  Including standard rules
# -------------------------------------------------------------------------
PROROOT = ..
include ../Rules.make

LDFLAGS = -L$(COM_LIB) -L$(SQL_LIB)
PROGLIB = -lmysqlclient -lz -lm

PRODEFS = -g -Wall -O2 -D_REENTRANT -Wpointer-arith\
	  -Wbad-function-cast -Wmissing-prototypes -Wmissing-declarations\
	  -Wstrict-prototypes

PROBOPT = -D__DEBUG__ -D__TEST__

COMFLAG = -o $@

# -------------------------------------------------------------------------
#  File specifications
# -------------------------------------------------------------------------
OBJECTS = $(OBJ_DIR)/dbmb_main.o\
	 $(OBJ_DIR)/dbmb_hss.o




#OBJECTS = $(OBJ_DIR)/dbmb_main.o\
	 $(OBJ_DIR)/dbmb_ccs.o\
	 $(OBJ_DIR)/dbmb_cfm.o\
	 $(OBJ_DIR)/dbmb_cts.o\
	 $(OBJ_DIR)/dbmb_dcs.o\
	 $(OBJ_DIR)/dbmb_sta.o\
	 $(OBJ_DIR)/dbmb_sdm.o\
	 $(OBJ_DIR)/dbmb_ipms.o\
	 $(OBJ_DIR)/dbmb_stat.o\
	 $(OBJ_DIR)/dbmb_dump.o\
	 $(OBJ_DIR)/dbmb_util.o

SOURCES = $(OBJECTS:.o=.c)

# -------------------------------------------------------------------------
#  Targets
# -------------------------------------------------------------------------
TARGET  = libdbmb.a

all: $(LIB_DIR)/$(TARGET)
$(LIB_DIR)/$(TARGET): $(OBJECTS)
	$(AR) $(ARFLAGS) $(LIB_DIR)/$(TARGET) $(OBJECTS)
	$(RANLIB) $(LIB_DIR)/$(TARGET)
	$(CP) $(LIB_DIR)/$(TARGET) $(COM_LIB)


# -------------------------------------------------------------------------
#  Compile source file
# -------------------------------------------------------------------------
$(OBJ_DIR)/%.o: %.c
	$(CC) -c $(PRODEFS) $(COMFLAG) $(INC) $(PROBOPT) $<

# -------------------------------------------------------------------------
#  Delete object, target and library file
# -------------------------------------------------------------------------
clean:
	$(RM) $(OBJECTS) $(LIB_DIR)/$(TARGET)

# -------------------------------------------------------------------------
#  Copy object, target file
# -------------------------------------------------------------------------
tag:
	ctags -R ./* ../hdr/* ../../CMLB/src/* ../../../inc/*

# -------------------------------------------------------------------------
#  End of Makefile
# -------------------------------------------------------------------------
