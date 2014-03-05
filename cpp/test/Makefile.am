AUTOMAKE_OPTIONS = foreign

bin_PROGRAMS = linkedListTest

#BOOST_CXXFLAGS = -std=gnu++0x -L/usr/lib64/
BOOST_CXXFLAGS = -L/usr/lib64/
TEST_CXXFLAGS = -I$(top_srcdir)/src
TEST_LIBS = -lboost_unit_test_framework

AM_CXXFLAGS = $(BOOST_CXXFLAGS) $(TEST_CXXFLAGS) -Wall -pipe

linkedListTest_SOURCES = LinkedListTest.cpp
linkedListTest_LDADD = $(TEST_LIBS)

testsubdir = test

TESTS_ENVIRONMENT = top_builddir=$(top_builddir)/test

TESTS = linkedListTest
