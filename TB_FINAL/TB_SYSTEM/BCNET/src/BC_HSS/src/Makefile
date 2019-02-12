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
PROROOT	= ..
include	../Rules.make

LDFLAGS	= -L$(COM_LIB) -L$(SQL_LIB)
PROGLIB	= -lrt -lm -lpthread -lcmlb -ldbmb -lmysqlclient
#PROGLIB	= -lrt -lm -lpthread -lmysqlclient -lcmlb -lpcap -ldbmb

PRODEFS = -Wall -O2 -D_REENTRANT -pedantic -Wpointer-arith\
	  -Wbad-function-cast -Wmissing-prototypes -Wmissing-declarations\
	  -Wstrict-prototypes -Wchar-subscripts -Wformat -Wnested-externs -Wcast-align -pthread\
          -Wno-unused-result -Wno-overlength-strings

PROBOPT = -U__TEST__

COMFLAG	= -o $@

# -------------------------------------------------------------------------
#  File specifications
# -------------------------------------------------------------------------
OBJECTS = $(OBJ_DIR)/hss_main.o\
	  $(OBJ_DIR)/hss_conf.o\
	  $(OBJ_DIR)/hss_task.o


SOURCES = $(OBJECTS:.o=.c)

# -------------------------------------------------------------------------
#  Targets
# -------------------------------------------------------------------------
TARGET  = bcnetHSS
#TARGET  = ntasDBT

all: Flags $(BIN_DIR)/$(TARGET)
$(BIN_DIR)/$(TARGET): $(OBJECTS)
	@$(CC) $(LDFLAGS) $(COMFLAG) $(OBJECTS) $(PROGLIB)
	@$(CP) $(BIN_DIR)/$(TARGET) $(COM_BIN)
	@echo '        ($(COM_BIN)/$(TARGET)) Created'
	@echo '-----------------------------------------------------------------'

Flags:
	@echo '================================================================='
	@echo ' HSS Detail Collector Server '
	@echo '================================================================='
	@echo ' < Compile Flags >'
	@echo '$(CC) -c $(PRODEFS) $(COMFLAG) $(INC) $(PROBOPT)'
	@echo ' < LD Flags >'
	@echo '$(CC) $(LDFLAGS) $(COMFLAG) $(OBJECTS) $(PROGLIB)'
	@echo '-----------------------------------------------------------------'

# -------------------------------------------------------------------------
#  Compile source file
# -------------------------------------------------------------------------
$(OBJ_DIR)/%.o: %.c
	@$(CC) -c $(PRODEFS) $(COMFLAG) $(INC) $(PROBOPT) $<
	@echo 'BC_HSS $(CC) compile...... $< '

# -------------------------------------------------------------------------
#  Delete object, target and library file
# -------------------------------------------------------------------------
clean:
	$(RM) -f $(OBJECTS) $(BIN_DIR)/$(TARGET) $(BIN_DIR)/core
#	$(RM) -f $(OBJECTS) $(BIN_DIR)/$(TARGET) $(BIN_DIR)/core $(SRC_DIR)/tags

# -------------------------------------------------------------------------
#  Copy object, target file
# -------------------------------------------------------------------------
copy:
	$(CP) $(BIN_DIR)/$(TARGET) $(COM_BIN)

# -------------------------------------------------------------------------
#  Copy object, target file
# -------------------------------------------------------------------------
tag:
	ctags -R ./* ../hdr/* ../../CMLB/* ../../DBMB/* ../../../inc/*

# -------------------------------------------------------------------------
#  End of Makefile
# -------------------------------------------------------------------------
