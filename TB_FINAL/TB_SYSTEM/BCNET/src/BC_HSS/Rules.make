#   ____        _                             _
#  |  _ \ _   _| | ___  ___   _ __ ___   __ _| | _____
#  | |_) | | | | |/ _ \/ __| | '_ ` _ \ / _` | |/ / _ \
#  |  _ <| |_| | |  __/\__ \_| | | | | | (_| |   <  __/
#  |_| \_\\__,_|_|\___||___(_)_| |_| |_|\__,_|_|\_\___|
#
# -----------------------------------------------------------------------------

# -----------------------------------------------------------------------------
#  Directory path
# -----------------------------------------------------------------------------
ROOTDIR = $(PROROOT)

SRC_DIR = $(ROOTDIR)/src
HDR_DIR = $(ROOTDIR)/hdr
OBJ_DIR = $(ROOTDIR)/obj
BIN_DIR = $(ROOTDIR)/bin

COM_INC = $(ROOTDIR)/../../inc
COM_LIB = $(ROOTDIR)/../../lib
COM_BIN = $(ROOTDIR)/../../bin

SQL_INC = /usr/include/mysql
SQL_LIB = /usr/lib/x86_64-linux-gnu

COM_HDR = -I$(COM_INC)/cmlb -I$(COM_INC)/dbmb

# -----------------------------------------------------------------------------
#  Command macros
# -----------------------------------------------------------------------------
CC      = gcc
CXX     = g++

AR      = ar
RANLIB  = ranlib
ARFLAGS = crv

RM      = rm
MV      = mv
CP      = cp

INC     = -I. -I$(HDR_DIR) -I$(COM_INC) $(COM_HDR)

# -----------------------------------------------------------------------------
#  End of Rules.make
# -----------------------------------------------------------------------------
